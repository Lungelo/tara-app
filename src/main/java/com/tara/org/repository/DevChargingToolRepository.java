package com.tara.org.repository;

import com.tara.org.domain.DevChargingTool;
import com.tara.org.domain.DevDrillingTool;

import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import java.util.List;

/**
 * Spring Data JPA repository for the DevChargingTool entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DevChargingToolRepository extends JpaRepository<DevChargingTool, Long> {

    @Query("select dev_charging_tool from DevChargingTool dev_charging_tool where dev_charging_tool.addedBy.login = ?#{principal.username}")
    Page<DevChargingTool> findByAddedByIsCurrentUser(Pageable pageable);


}
