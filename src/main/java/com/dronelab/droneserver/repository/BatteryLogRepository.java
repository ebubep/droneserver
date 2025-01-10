
package com.dronelab.droneserver.repository;

import java.util.Optional;

import com.dronelab.droneserver.entities.BatteryLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface BatteryLogRepository extends JpaRepository<BatteryLog, Long> {

    @Query(value = "select * from battery_log d where d.drone_id = :droneID", nativeQuery = true)
    Optional<BatteryLog> findBatteryLogInstance(@Param("droneID") Long droneID);
}

