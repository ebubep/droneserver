
package com.musalasoft.droneserver.entities;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface LoadItemsRepo extends JpaRepository<LoadItems, Long> {
    
    @Query(value = "select * from load_items d where d.event_id = :eventID", nativeQuery = true)
    List<LoadItems> findLoadItemsByEventID(@Param("eventID") Long eventID);
}

