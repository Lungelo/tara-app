package com.tara.org.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.tara.org.domain.Authority;
import com.tara.org.domain.Company;
import com.tara.org.domain.StopChargingTool;
import com.tara.org.domain.User;
import com.tara.org.domain.UserExtra;

import javax.inject.Inject;

import com.tara.org.repository.StopChargingToolRepository;
import com.tara.org.repository.UserExtraRepository;
import com.tara.org.repository.search.StopChargingToolSearchRepository;
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
 * REST controller for managing StopChargingTool.
 */
@RestController
@RequestMapping("/api")
public class StopChargingToolResource {

    private final Logger log = LoggerFactory.getLogger(StopChargingToolResource.class);

    private static final String ENTITY_NAME = "stopChargingTool";

    private final StopChargingToolRepository stopChargingToolRepository;

    private final StopChargingToolSearchRepository stopChargingToolSearchRepository;
    
    @Inject
    private UserService userService;
    
    @Inject
    private UserExtraRepository userExtraRepository;

    public StopChargingToolResource(StopChargingToolRepository stopChargingToolRepository, StopChargingToolSearchRepository stopChargingToolSearchRepository) {
        this.stopChargingToolRepository = stopChargingToolRepository;
        this.stopChargingToolSearchRepository = stopChargingToolSearchRepository;
    }

    /**
     * POST  /stop-charging-tools : Create a new stopChargingTool.
     *
     * @param stopChargingTool the stopChargingTool to create
     * @return the ResponseEntity with status 201 (Created) and with body the new stopChargingTool, or with status 400 (Bad Request) if the stopChargingTool has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/stop-charging-tools")
    @Timed
    public ResponseEntity<StopChargingTool> createStopChargingTool(@Valid @RequestBody StopChargingTool stopChargingTool) throws URISyntaxException {
        log.debug("REST request to save StopChargingTool : {}", stopChargingTool);
        if (stopChargingTool.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new stopChargingTool cannot already have an ID")).body(null);
        }
        User user =userService.getUserWithAuthorities();
        UserExtra userExtra=userExtraRepository.findByUser(user);
        Company company=userExtra.getCompany();
        stopChargingTool.setAddedBy(user);
        stopChargingTool.setOemName(company);
        StopChargingTool result = stopChargingToolRepository.save(stopChargingTool);
        stopChargingToolSearchRepository.save(result);
        return ResponseEntity.created(new URI("/api/stop-charging-tools/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /stop-charging-tools : Updates an existing stopChargingTool.
     *
     * @param stopChargingTool the stopChargingTool to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated stopChargingTool,
     * or with status 400 (Bad Request) if the stopChargingTool is not valid,
     * or with status 500 (Internal Server Error) if the stopChargingTool couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/stop-charging-tools")
    @Timed
    public ResponseEntity<StopChargingTool> updateStopChargingTool(@Valid @RequestBody StopChargingTool stopChargingTool) throws URISyntaxException {
        log.debug("REST request to update StopChargingTool : {}", stopChargingTool);
        if (stopChargingTool.getId() == null) {
            return createStopChargingTool(stopChargingTool);
        }
        StopChargingTool result = stopChargingToolRepository.save(stopChargingTool);
        stopChargingToolSearchRepository.save(result);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, stopChargingTool.getId().toString()))
            .body(result);
    }

    /**
     * GET  /stop-charging-tools : get all the stopChargingTools.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of stopChargingTools in body
     */
    @GetMapping("/stop-charging-tools")
    @Timed
    public ResponseEntity<List<StopChargingTool>> getAllStopChargingTools(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of StopChargingTools");
        
        
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
        	
        	Page<StopChargingTool> page = stopChargingToolRepository.findAll(pageable);
            HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/stop-charging-tools");
            return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
        	
        }else {
        	
        	Page<StopChargingTool> page = stopChargingToolRepository.findByAddedByIsCurrentUser(pageable);
            HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/stop-charging-tools");
            return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
        	
        }
        
        
    }

    /**
     * GET  /stop-charging-tools/:id : get the "id" stopChargingTool.
     *
     * @param id the id of the stopChargingTool to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the stopChargingTool, or with status 404 (Not Found)
     */
    @GetMapping("/stop-charging-tools/{id}")
    @Timed
    public ResponseEntity<StopChargingTool> getStopChargingTool(@PathVariable Long id) {
        log.debug("REST request to get StopChargingTool : {}", id);
        StopChargingTool stopChargingTool = stopChargingToolRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(stopChargingTool));
    }

    /**
     * DELETE  /stop-charging-tools/:id : delete the "id" stopChargingTool.
     *
     * @param id the id of the stopChargingTool to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/stop-charging-tools/{id}")
    @Timed
    public ResponseEntity<Void> deleteStopChargingTool(@PathVariable Long id) {
        log.debug("REST request to delete StopChargingTool : {}", id);
        stopChargingToolRepository.delete(id);
        stopChargingToolSearchRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/stop-charging-tools?query=:query : search for the stopChargingTool corresponding
     * to the query.
     *
     * @param query the query of the stopChargingTool search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/stop-charging-tools")
    @Timed
    public ResponseEntity<List<StopChargingTool>> searchStopChargingTools(@RequestParam String query, @ApiParam Pageable pageable) {
        log.debug("REST request to search for a page of StopChargingTools for query {}", query);
        Page<StopChargingTool> page = stopChargingToolSearchRepository.search(queryStringQuery(query), pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/stop-charging-tools");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}
