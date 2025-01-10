
package com.dronelab.droneserver.repository;

import java.util.Optional;

import com.dronelab.droneserver.entities.Medication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface MedicineRepository extends JpaRepository<Medication, Long> {

    @Query(value = "select * from medication d where d.code = :medCode", nativeQuery = true)
    Optional<Medication> findByCode(@Param("medCode") String medCode);
}

