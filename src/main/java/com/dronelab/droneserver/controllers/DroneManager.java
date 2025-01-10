package com.dronelab.droneserver.controllers;

import com.dronelab.droneserver.dtos.request.DroneCheckDTO;
import com.dronelab.droneserver.dtos.request.DroneDTO;
import com.dronelab.droneserver.dtos.response.DroneResponseDTO;
import com.dronelab.droneserver.dtos.request.PayloadMetaDTO;
import com.dronelab.droneserver.dtos.response.PayloadResponseMetaDTO;
import com.dronelab.droneserver.entities.BatteryLog;
import com.dronelab.droneserver.entities.Drone;
import com.dronelab.droneserver.repository.BatteryLogRepository;
import com.dronelab.droneserver.repository.DroneRepository;
import com.dronelab.droneserver.services.DroneService;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;


@EnableScheduling
@RestController
public class DroneManager {

    private DroneRepository droneRepository;
    private DroneService droneService;
    private BatteryLogRepository batteryLogRepository;

    public DroneManager(DroneService droneService, DroneRepository droneRepository, BatteryLogRepository batteryLogRepository) {

        this.droneService = droneService;
        this.droneRepository = droneRepository;
        this.batteryLogRepository = batteryLogRepository;
    }

    @PostMapping(value = "/register", consumes = "application/json", produces = "application/json")
    public ResponseEntity<DroneResponseDTO> register(@RequestBody List<DroneDTO> droneList) {
        return ResponseEntity.status(HttpStatus.CREATED).body(droneService.register(droneList));
    }

    @PostMapping("/load")
    public ResponseEntity<DroneResponseDTO> load(@RequestBody PayloadMetaDTO payloadDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(droneService.load(payloadDTO));

    }

    @GetMapping("/check_loaded_items")
    public ResponseEntity<PayloadResponseMetaDTO> checkLoadedItems(@RequestBody DroneCheckDTO droneCheckDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(droneService.checkLoadedItems(droneCheckDTO));
    }

    @GetMapping("/check_available_drones")
    public List<Drone> checkAvailableDrones() {
        return droneRepository.findAvailableDrones();

    }

    @GetMapping("/view_all_drones")
    public List<Drone> viewAllDrones() {
        return droneRepository.findAll();
    }


    @GetMapping("/check_battery_level")
    public int checkBatteryLevel(@RequestBody JsonNode droneData) {

        int batteryLevel = 0;
        Optional<Drone> result = droneRepository.findById(droneData.get("drone_id").asLong());
        if (result.isPresent()) {
            batteryLevel = result.get().getBattery();
        }
        return batteryLevel;
    }


    @Scheduled(fixedRate = 10000)
    public void mointorBattery() {

        List<Drone> drones = droneRepository.findAll();

        for (Drone dr : drones) {
            long droneID = dr.getId();
            int batteryLevel = dr.getBattery();
            Optional<BatteryLog> blResult = batteryLogRepository.findBatteryLogInstance(droneID);
            if (blResult.isPresent()) {
                BatteryLog bl = blResult.get();
                bl.setBatteryLevel(batteryLevel);
                batteryLogRepository.save(bl);
            } else batteryLogRepository.save(new BatteryLog(droneID, batteryLevel));

        }

    }

    @GetMapping("/view_battery_levels")
    public List<BatteryLog> viewAllBatteryLogs() {
        return batteryLogRepository.findAll();

    }
}