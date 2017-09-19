package com.tara.org.repository;

import com.tara.org.domain.DevCleaningTool;
import com.tara.org.domain.WirelessSensorNetwork;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import java.util.List;

/**
 * Spring Data JPA repository for the WirelessSensorNetwork entity.
 */
@SuppressWarnings("unused")
@Repository
public interface WirelessSensorNetworkRepository extends JpaRepository<WirelessSensorNetwork,Long> {

    @Query("select wireless_sensor_network from WirelessSensorNetwork wireless_sensor_network where wireless_sensor_network.addedBy.login = ?#{principal.username}")
    Page<WirelessSensorNetwork> findByAddedByIsCurrentUser(Pageable pageable);

    
}
