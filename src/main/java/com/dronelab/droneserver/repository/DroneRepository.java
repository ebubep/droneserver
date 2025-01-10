
package com.dronelab.droneserver.repository;

import java.util.List;

import com.dronelab.droneserver.entities.Drone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface DroneRepository extends JpaRepository<Drone, Long> {

    @Query(value = "select * from drone d where d.battery > 25 AND d.drone_state=1", nativeQuery = true)
    List<Drone> findAvailableDrones();
}

