
package com.dronelab.droneserver.util;

import com.dronelab.droneserver.dtos.request.PayloadBodyDTO;
import com.dronelab.droneserver.entities.Drone;
import com.dronelab.droneserver.repository.DroneRepository;
import com.dronelab.droneserver.repository.MedicineRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DroneSafety {

    private DroneRepository droneRepository;
    private MedicineRepository medicineRepository;

    public DroneSafety(DroneRepository droneRepository, MedicineRepository medicineRepository) {
        this.droneRepository = droneRepository;
        this.medicineRepository = medicineRepository;
    }

    public boolean canCarry(Drone drone, List<PayloadBodyDTO> payloadBodyList) {

        Integer medicineWeightSum = payloadBodyList.stream().mapToInt(
                payload ->
                        medicineRepository
                                .findByCode(payload.medicineCode())
                                .get()
                                .getWeight() * payload.quantity()


        ).sum();
        if (drone.getBattery() > 25 && drone.getWeight() > medicineWeightSum && medicineWeightSum < 500) return true;

        return false;
    }
}
