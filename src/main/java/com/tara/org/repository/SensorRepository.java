package com.tara.org.repository;

import com.tara.org.domain.DevCleaningTool;
import com.tara.org.domain.Sensor;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import java.util.List;

/**
 * Spring Data JPA repository for the Sensor entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SensorRepository extends JpaRepository<Sensor,Long> {

    @Query("select sensor from Sensor sensor where sensor.addedBy.login = ?#{principal.username}")
    Page<Sensor> findByAddedByIsCurrentUser(Pageable pageable);

    
}
