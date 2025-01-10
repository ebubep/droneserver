package com.dronelab.droneserver.services;

import com.dronelab.droneserver.dtos.request.DroneCheckDTO;
import com.dronelab.droneserver.dtos.request.DroneDTO;
import com.dronelab.droneserver.dtos.request.PayloadBodyDTO;
import com.dronelab.droneserver.dtos.request.PayloadMetaDTO;
import com.dronelab.droneserver.dtos.response.DroneResponseDTO;
import com.dronelab.droneserver.dtos.response.PayloadResponseMetaDTO;
import com.dronelab.droneserver.entities.Drone;
import com.dronelab.droneserver.entities.LoadEvent;
import com.dronelab.droneserver.entities.LoadItems;
import com.dronelab.droneserver.repository.BatteryLogRepository;
import com.dronelab.droneserver.repository.DroneRepository;
import com.dronelab.droneserver.repository.LoadEventRepo;
import com.dronelab.droneserver.repository.LoadItemsRepo;
import com.dronelab.droneserver.repository.MedicineRepository;
import com.dronelab.droneserver.util.DroneSafety;
import com.dronelab.droneserver.util.StatusCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DroneService {

    private DroneRepository droneRepository;
    private DroneSafety droneSafety;
    private BatteryLogRepository batteryLogRepo;

    private LoadEventRepo loadEventRepo;

    private LoadItemsRepo loadItemsRepo;

    private MedicineRepository medrepo;

    Logger logger = LoggerFactory.getLogger(DroneService.class);

    public DroneService(DroneRepository droneRepository, DroneSafety droneSafety) {
        this.droneRepository = droneRepository;
        this.droneSafety = droneSafety;
    }

    public DroneResponseDTO register(List<DroneDTO> droneList) {
        try {
            LocalDateTime currTS = LocalDateTime.now();
            droneList.stream()
                    .forEach(
                            droneDTO -> {
                                Drone drone = new Drone(
                                        droneDTO.serial(),
                                        droneDTO.model(),
                                        droneDTO.weightLimit(),
                                        droneDTO.battery(),
                                        droneDTO.state(),
                                        currTS,
                                        currTS);
                                droneRepository.save(drone);
                            }
                    );

        } catch (Exception e) {
            logger.error("Registration failed at {}", e.getMessage());
            //TODO thow a DroneEXception
        }
        return new DroneResponseDTO(StatusCode.SUCCESS);
    }

    public DroneResponseDTO load(@RequestBody PayloadMetaDTO payloadMetaDTO) {

        Drone drone = droneRepository.findById(payloadMetaDTO.droneId()).get();
        if (droneSafety.canCarry(drone, payloadMetaDTO.payloadList()) == false) {
            return new DroneResponseDTO(StatusCode.OVERLOAD);
        }

        updateDroneStatus(drone, StatusCode.LOADING);
        LocalDateTime currTS = LocalDateTime.now();

        LoadEvent le = new LoadEvent(drone.getId(), payloadMetaDTO.operatorName(), currTS, currTS);
        loadEventRepo.save(le);
        long loadEventID = le.getId();

        payloadMetaDTO.payloadList().stream().forEach(
                payloadBodyDTO ->
                        loadItemsRepo.save(
                                new LoadItems(loadEventID, payloadBodyDTO.medicineCode(), payloadBodyDTO.quantity(), currTS)
                        )
        );
        LocalDateTime loadEndDate = LocalDateTime.now();
        le.setEndDate(loadEndDate);
        loadEventRepo.save(le);

        updateDroneStatus(drone, StatusCode.LOADED);
        return new DroneResponseDTO(StatusCode.SUCCESS);
    }


    public void updateDroneStatus(Drone drone, int status) {
        drone.setState(status);
        droneRepository.save(drone);
    }

    @GetMapping("/check_loaded_items")
    public PayloadResponseMetaDTO checkLoadedItems(@RequestBody DroneCheckDTO droneCheckDTO) {

        Drone drone = droneRepository.findById(droneCheckDTO.droneId()).get();

        LoadEvent loadEvent = loadEventRepo.findLoadEventsByDroneID(drone.getId());

        long leID = loadEvent.getId();

        List<LoadItems> liResult = loadItemsRepo.findLoadItemsByEventID(leID);

        List<PayloadBodyDTO> payloadList = liResult
                .stream()
                .map(
                        loadItem -> new PayloadBodyDTO(
                                loadItem.getMedCode(),
                                loadItem.getQty()
                        )
                ).collect(Collectors.toUnmodifiableList());
        ;

        return new PayloadResponseMetaDTO(
                drone.getId(),
                loadEvent.getOperator_on_duty(),
                drone.getBattery(),
                drone.getModel(),
                loadEvent.getEndDate(),
                payloadList
        );

    }


    public List<DroneDTO> getAllDrones() {

        List l = new ArrayList();
        l.add(new DroneDTO("DRD933", 1, 1, 95, 500));
        l.add(new DroneDTO("SLC933", 2, 1, 70, 500));
        l.add(new DroneDTO("GPG933", 3, 1, 95, 500));
        l.add(new DroneDTO("KSK933", 4, 1, 95, 500));
        return l;
    }
}
