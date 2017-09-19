package com.tara.org.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.tara.org.domain.Authority;
import com.tara.org.domain.Company;
import com.tara.org.domain.DevChargingTool;
import com.tara.org.domain.User;
import com.tara.org.domain.UserExtra;
import com.tara.org.repository.DevChargingToolRepository;
import com.tara.org.repository.UserExtraRepository;
import com.tara.org.repository.search.DevChargingToolSearchRepository;
import com.tara.org.service.UserService;
import com.tara.org.web.rest.util.HeaderUtil;
import com.tara.org.web.rest.util.PaginationUtil;
import io.swagger.annotations.ApiParam;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * REST controller for managing DevChargingTool.
 */
@RestController
@RequestMapping("/api")
public class DevChargingToolResource {

    private final Logger log = LoggerFactory.getLogger(DevChargingToolResource.class);

    private static final String ENTITY_NAME = "devChargingTool";

    private final DevChargingToolRepository devChargingToolRepository;
    
    @Inject
    private UserService userService;
    
    @Inject
    private UserExtraRepository userExtraRepository;

    private final DevChargingToolSearchRepository devChargingToolSearchRepository;
    public DevChargingToolResource(DevChargingToolRepository devChargingToolRepository, DevChargingToolSearchRepository devChargingToolSearchRepository) {
        this.devChargingToolRepository = devChargingToolRepository;
        this.devChargingToolSearchRepository = devChargingToolSearchRepository;
    }

    /**
     * POST  /dev-charging-tools : Create a new devChargingTool.
     *
     * @param devChargingTool the devChargingTool to create
     * @return the ResponseEntity with status 201 (Created) and with body the new devChargingTool, or with status 400 (Bad Request) if the devChargingTool has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/dev-charging-tools")
    @Timed
    public ResponseEntity<DevChargingTool> createDevChargingTool(@Valid @RequestBody DevChargingTool devChargingTool) throws URISyntaxException {
        log.debug("REST request to save DevChargingTool : {}", devChargingTool);
        if (devChargingTool.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new devChargingTool cannot already have an ID")).body(null);
        }
        User user =userService.getUserWithAuthorities();
        UserExtra userExtra=userExtraRepository.findByUser(user);
        Company company=userExtra.getCompany();
        devChargingTool.setAddedBy(user);
        devChargingTool.setOemName(company);
        
        DevChargingTool result = devChargingToolRepository.save(devChargingTool);
        devChargingToolSearchRepository.save(result);
        return ResponseEntity.created(new URI("/api/dev-charging-tools/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /dev-charging-tools : Updates an existing devChargingTool.
     *
     * @param devChargingTool the devChargingTool to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated devChargingTool,
     * or with status 400 (Bad Request) if the devChargingTool is not valid,
     * or with status 500 (Internal Server Error) if the devChargingTool couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/dev-charging-tools")
    @Timed
    public ResponseEntity<DevChargingTool> updateDevChargingTool(@Valid @RequestBody DevChargingTool devChargingTool) throws URISyntaxException {
        log.debug("REST request to update DevChargingTool : {}", devChargingTool);
        if (devChargingTool.getId() == null) {
            return createDevChargingTool(devChargingTool);
        }
        DevChargingTool result = devChargingToolRepository.save(devChargingTool);
        devChargingToolSearchRepository.save(result);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, devChargingTool.getId().toString()))
            .body(result);
    }

    /**
     * GET  /dev-charging-tools : get all the devChargingTools.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of devChargingTools in body
     */
    @GetMapping("/dev-charging-tools")
    @Timed
    public ResponseEntity<List<DevChargingTool>> getAllDevChargingTools(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of DevChargingTools");
        
        User user =userService.getUserWithAuthorities();
        Set<Authority> authorities=user.getAuthorities();
        Authority authority =new Authority();
        String Role="GENERAL_USER";	
        authority.setName(Role);
        
        Set<Authority> authorities2= new HashSet<>();
        authorities2.add(authority);
        
     // check the existence of element
        boolean exist=authorities.contains(authority);
        
        if(exist==true) {
        	
        Page<DevChargingTool> page = devChargingToolRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/dev-charging-tools");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
        }
        else 
        {
        	
        Page<DevChargingTool> page = devChargingToolRepository.findByAddedByIsCurrentUser(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/dev-charging-tools");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);}
        
    }

    /**
     * GET  /dev-charging-tools/:id : get the "id" devChargingTool.
     *
     * @param id the id of the devChargingTool to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the devChargingTool, or with status 404 (Not Found)
     */
    @GetMapping("/dev-charging-tools/{id}")
    @Timed
    public ResponseEntity<DevChargingTool> getDevChargingTool(@PathVariable Long id) {
        log.debug("REST request to get DevChargingTool : {}", id);
        DevChargingTool devChargingTool = devChargingToolRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(devChargingTool));
    }

    /**
     * DELETE  /dev-charging-tools/:id : delete the "id" devChargingTool.
     *
     * @param id the id of the devChargingTool to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/dev-charging-tools/{id}")
    @Timed
    public ResponseEntity<Void> deleteDevChargingTool(@PathVariable Long id) {
        log.debug("REST request to delete DevChargingTool : {}", id);
        devChargingToolRepository.delete(id);
        devChargingToolSearchRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/dev-charging-tools?query=:query : search for the devChargingTool corresponding
     * to the query.
     *
     * @param query the query of the devChargingTool search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/dev-charging-tools")
    @Timed
    public ResponseEntity<List<DevChargingTool>> searchDevChargingTools(@RequestParam String query, @ApiParam Pageable pageable) {
        log.debug("REST request to search for a page of DevChargingTools for query {}", query);
        Page<DevChargingTool> page = devChargingToolSearchRepository.search(queryStringQuery(query), pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/dev-charging-tools");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}
