
package com.musalasoft.droneserver.entities;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface LoadEventRepo extends JpaRepository<LoadEvent, Long> {
    
     @Query(value = "select * from load_event d where d.drone_id = :droneID", nativeQuery = true)
    List<LoadEvent> findLoadEventsByDroneID(@Param("droneID") Long droneID);
}

