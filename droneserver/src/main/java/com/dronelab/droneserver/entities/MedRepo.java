
package com.dronelab.droneserver.entities;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface MedRepo extends JpaRepository<Medication, Long> {
    
    @Query(value = "select * from medication d where d.code = :medCode", nativeQuery = true)
    Optional<Medication> findByCode(@Param("medCode") String medCode);
}

