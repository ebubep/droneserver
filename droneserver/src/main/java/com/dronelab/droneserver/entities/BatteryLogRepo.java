
package com.dronelab.droneserver.entities;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface BatteryLogRepo extends JpaRepository<BatteryLog, Long> {
    
    @Query(value = "select * from battery_log d where d.drone_id = :droneID", nativeQuery = true)
    Optional<BatteryLog> findBatteryLogInstance(@Param("droneID") Long droneID);
}

