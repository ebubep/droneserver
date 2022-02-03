
package com.musalasoft.droneserver.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.musalasoft.droneserver.DroneManager;
import com.musalasoft.droneserver.entities.Drone;
import com.musalasoft.droneserver.entities.DroneRepo;
import com.musalasoft.droneserver.entities.MedRepo;
import com.musalasoft.droneserver.entities.Medication;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;


public class DroneSafety {

    public static boolean canCarry(DroneRepo repo ,MedRepo medrepo, long droneID, JsonNode payload){
        boolean retValue = false ;
        int battery = 0;
        int weight_limit = 0;
        int medWeightSum = 0 ;
        
        //get the drone 
        Optional<Drone> result = repo.findById(droneID);
        if (result.isPresent()) {
                battery = result.get().getBattery();
                weight_limit = result.get().getWeight();
            }
        
        for(JsonNode elm : payload){
            String medCode = elm.get("med_code").asText();
            long qty = elm.get("qty").asLong();
            //get the medication weight
            Optional<Medication> medResult = medrepo.findByCode(medCode);
            if (medResult.isPresent()) {
                medWeightSum += medResult.get().getWeight() * qty;
            }
        }
        if(battery > 25 && weight_limit > medWeightSum && medWeightSum < 500)retValue = true ;
        
        return retValue ;
    }
}
