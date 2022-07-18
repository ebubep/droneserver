package com.dronelab.droneserver;

import com.dronelab.droneserver.util.DateUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.dronelab.droneserver.entities.Drone;
import com.fasterxml.jackson.databind.JsonNode ;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.dronelab.droneserver.entities.BatteryLog;
import com.dronelab.droneserver.entities.BatteryLogRepo;
import com.dronelab.droneserver.entities.DroneRepo;
import com.dronelab.droneserver.entities.LoadEvent;
import com.dronelab.droneserver.entities.LoadEventRepo;
import com.dronelab.droneserver.entities.LoadItems;
import com.dronelab.droneserver.entities.LoadItemsRepo;
import com.dronelab.droneserver.entities.MedRepo;
import com.dronelab.droneserver.util.DroneSafety;
import com.dronelab.droneserver.util.StatusCode;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestBody;


@Configuration
@EnableScheduling
@RestController
public class DroneManager {


        /*
    #### Functional requirements
    
    Non-functional requirements
- Your project must have a README file with build/run/test instructions (use DB that can be run locally, e.g. in-memory, via container);
- Required data must be preloaded in the database.
- JUnit tests are optional but advisable (if you have time);
- Advice: Show us how you work through your commit history.
        */
    @Autowired
    private  DroneRepo repo ;
    
    @Autowired
    private BatteryLogRepo blRepo;
    
    @Autowired
    private LoadEventRepo leRepo;
    
    @Autowired
    private LoadItemsRepo liRepo;
    
    @Autowired
    private MedRepo medrepo ;
    
    
    @GetMapping(value = "/register", consumes = "application/json", produces = "application/json")
	public int register(@RequestBody ArrayNode droneArray) {
            try{
                for(JsonNode droneData : droneArray){
                    ObjectNode droneDataObjNode = (ObjectNode)droneData ;

                    String currTS = DateUtil.getCurrentTimeStamp() ;
                    Drone dr = new Drone(droneDataObjNode.get("serial_number").asText() ,
                                          droneDataObjNode.get("model").asInt() , 
                                          droneDataObjNode.get("weight_limit").asInt() ,
                                          droneDataObjNode.get("battery").asInt() ,
                                          droneDataObjNode.get("state").asInt() ,
                                          currTS,
                                          currTS); 
                    repo.save(dr);

                }
            }catch(Exception e){
                e.printStackTrace();
                return StatusCode.UNKNOWN_ERROR;
            }
            return StatusCode.SUCCESS;
	}
        
        /*
        loading a drone with medication items;
        ** Prevent the drone from being loaded with more weight that it can carry;
            *dropp all payload if total payload is > 500
        Prevent the drone from being in LOADING state if the battery level is **below 25%**;
        */
        @GetMapping("/load")
	public int load(@RequestBody JsonNode droneData) {
            
	    long droneId = droneData.get("drone_id").asLong();
            String operatorOnDuty = droneData.get("operator_on_duty").asText();
            
            JsonNode payloadArray = droneData.get("payload");
            if(DroneSafety.canCarry(repo,medrepo,droneId, payloadArray) == false) return StatusCode.OVERLOAD;
            
            updateDroneStatus(droneId , StatusCode.LOADING);
            String currTS = DateUtil.getCurrentTimeStamp() ;
            
            LoadEvent le = new LoadEvent(droneId,operatorOnDuty,currTS,currTS);
            leRepo.save(le);
            long loadEventID = le.getId();
             
            for(JsonNode elm : payloadArray){
                String medCode = elm.get("med_code").asText();
                int qty = elm.get("qty").asInt();
                String createdAt = DateUtil.getCurrentTimeStamp() ;
                
                liRepo.save(new LoadItems(loadEventID,medCode,qty,createdAt));
            }
            String loadEndDate = DateUtil.getCurrentTimeStamp() ;
            le.setEndDate(loadEndDate);
            leRepo.save(le);
            
            //set drone state to LOADED
            updateDroneStatus(droneId , StatusCode.LOADED);
            return StatusCode.SUCCESS;
	}
        
        public void updateDroneStatus(long droneID , int status){
            Drone drone ;
            //set drone state to LOADING
            Optional<Drone> droneRes = repo.findById(droneID);
            if(droneRes.isPresent()){
                drone = droneRes.get();
                drone.setState(status);
                repo.save(drone);
            }
        }
        
        /*
         checking loaded medication items for a given drone;
    means fetching records from drone_load table using serial_number
        */
        @GetMapping("/check_loaded_items")
	public ObjectNode checkLoadedItems(@RequestBody JsonNode droneData) {
            
            ObjectNode droneNode = JsonNodeFactory.instance.objectNode();
            long droneID = droneData.get("drone_id").asLong();
            Drone drone ;
            //set drone state to LOADING
            Optional<Drone> droneRes = repo.findById(droneID);
            if(droneRes.isPresent()){
                drone = droneRes.get();
                droneNode.put("drone_id", droneID);
                droneNode.put("battery", drone.getBattery());
                droneNode.put("model", drone.getModel());
                
                List<LoadEvent> loadEvents = leRepo.findLoadEventsByDroneID(droneID);
                for(LoadEvent leRow : loadEvents){
                    long leID = leRow.getId();
                    droneNode.put("operator_on_duty", leRow.getOperator_on_duty());
                    
                    
                    List<LoadItems> liResult = liRepo.findLoadItemsByEventID(leID);
                    
                    List<ObjectNode> objectNodeArray = new ArrayList<ObjectNode>();
                    for(LoadItems liRow : liResult){
                        ObjectNode loadNode = JsonNodeFactory.instance.objectNode();
                        loadNode.put("med_code",liRow.getMedCode());
                        loadNode.put("qty",liRow.getQty());
                        loadNode.put("created_at",liRow.getQty());
                        objectNodeArray.add(loadNode);
                    }
                    droneNode.putPOJO("payload",objectNodeArray);
//.put("payload",objectNodeArray);
                }
            }
		return droneNode;
	}
        
        /*
         checking available drones for loading;
    get drones that are not in drone_load table,and meet criteria 
        */
        @GetMapping("/check_available_drones")
	public List<Drone> checkAvailableDrones() {
            return repo.findAvailableDrones() ;
		
	}
        
        @GetMapping("/view_all_drones")
	public List<Drone> viewAllDrones() {
            return repo.findAll() ;
		
	}
        /*
        - check drone battery level for a given drone;
    get drone battery level for a giveen drone 
    
        - Introduce a periodic task to check drones battery levels and create history/audit event log for this.
    cronjob per minute 
    create table drone_battery_log
        */
        @GetMapping("/check_battery_level")
	public int checkBatteryLevel(@RequestBody JsonNode droneData) {
            
            int batteryLevel = 0;
            Optional<Drone> result = repo.findById( droneData.get("drone_id").asLong());
            if (result.isPresent()) {
                batteryLevel = result.get().getBattery();
            }
		return batteryLevel;
	}
        
        
        @Scheduled(fixedRate = 10000)
        public void mointorBattery(){
            
            //get all drones
            List<Drone> drones = repo.findAll() ;
            //for each drone save battery level in table battery_level
            for(Drone dr : drones){
                long droneID = dr.getId();
                int batteryLevel = dr.getBattery();
                Optional<BatteryLog> blResult = blRepo.findBatteryLogInstance(droneID);
                if(blResult.isPresent()){
                    BatteryLog bl = blResult.get();
                    bl.setBatteryLevel(batteryLevel);
                    blRepo.save(bl);
                }else     blRepo.save(new BatteryLog(droneID ,batteryLevel));
                
            }
            
        }
        
        @GetMapping("/view_battery_levels")
	public List<BatteryLog> viewAllBatteryLogs() {
            return blRepo.findAll() ;
		
	}
}