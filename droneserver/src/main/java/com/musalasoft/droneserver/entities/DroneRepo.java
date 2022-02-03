
package com.musalasoft.droneserver.entities;

import com.musalasoft.droneserver.entities.Drone;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


public interface DroneRepo extends JpaRepository<Drone, Long> {
    
    @Query(value = "select * from drone d where d.battery > 25 AND d.drone_state=1", nativeQuery = true)
    List<Drone> findAvailableDrones();
//     
//
//    @Query(value = "select * from drones where battery > 25 AND state=1", nativeQuery = true)
//    List<Drone> findLoadedItems(String serialNumber);
//    
    //Drone findBySerialNumber(String serial_number);
    
    
}

