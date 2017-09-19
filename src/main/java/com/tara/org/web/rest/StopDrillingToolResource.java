package com.tara.org.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.tara.org.domain.Authority;
import com.tara.org.domain.Company;
import com.tara.org.domain.StopDrillingTool;
import com.tara.org.domain.User;
import com.tara.org.domain.UserExtra;
import com.tara.org.repository.StopDrillingToolRepository;
import com.tara.org.repository.UserExtraRepository;
import com.tara.org.repository.search.StopDrillingToolSearchRepository;
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
 * REST controller for managing StopDrillingTool.
 */
@RestController
@RequestMapping("/api")
public class StopDrillingToolResource {

    private final Logger log = LoggerFactory.getLogger(StopDrillingToolResource.class);

    private static final String ENTITY_NAME = "stopDrillingTool";

    private final StopDrillingToolRepository stopDrillingToolRepository;

    private final StopDrillingToolSearchRepository stopDrillingToolSearchRepository;
    
    @Inject
    private UserService userService;
    
    @Inject
    private UserExtraRepository userExtraRepository;

    public StopDrillingToolResource(StopDrillingToolRepository stopDrillingToolRepository, StopDrillingToolSearchRepository stopDrillingToolSearchRepository) {
        this.stopDrillingToolRepository = stopDrillingToolRepository;
        this.stopDrillingToolSearchRepository = stopDrillingToolSearchRepository;
    }

    /**
     * POST  /stop-drilling-tools : Create a new stopDrillingTool.
     *
     * @param stopDrillingTool the stopDrillingTool to create
     * @return the ResponseEntity with status 201 (Created) and with body the new stopDrillingTool, or with status 400 (Bad Request) if the stopDrillingTool has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/stop-drilling-tools")
    @Timed
    public ResponseEntity<StopDrillingTool> createStopDrillingTool(@Valid @RequestBody StopDrillingTool stopDrillingTool) throws URISyntaxException {
        log.debug("REST request to save StopDrillingTool : {}", stopDrillingTool);
        if (stopDrillingTool.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new stopDrillingTool cannot already have an ID")).body(null);
        }
        User user =userService.getUserWithAuthorities();
        UserExtra userExtra=userExtraRepository.findByUser(user);
        Company company=userExtra.getCompany();
        stopDrillingTool.setAddedBy(user);
        stopDrillingTool.setOemName(company);
        StopDrillingTool result = stopDrillingToolRepository.save(stopDrillingTool);
        stopDrillingToolSearchRepository.save(result);
        return ResponseEntity.created(new URI("/api/stop-drilling-tools/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /stop-drilling-tools : Updates an existing stopDrillingTool.
     *
     * @param stopDrillingTool the stopDrillingTool to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated stopDrillingTool,
     * or with status 400 (Bad Request) if the stopDrillingTool is not valid,
     * or with status 500 (Internal Server Error) if the stopDrillingTool couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/stop-drilling-tools")
    @Timed
    public ResponseEntity<StopDrillingTool> updateStopDrillingTool(@Valid @RequestBody StopDrillingTool stopDrillingTool) throws URISyntaxException {
        log.debug("REST request to update StopDrillingTool : {}", stopDrillingTool);
        if (stopDrillingTool.getId() == null) {
            return createStopDrillingTool(stopDrillingTool);
        }
        StopDrillingTool result = stopDrillingToolRepository.save(stopDrillingTool);
        stopDrillingToolSearchRepository.save(result);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, stopDrillingTool.getId().toString()))
            .body(result);
    }

    /**
     * GET  /stop-drilling-tools : get all the stopDrillingTools.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of stopDrillingTools in body
     */
    @GetMapping("/stop-drilling-tools")
    @Timed
    public ResponseEntity<List<StopDrillingTool>> getAllStopDrillingTools(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of StopDrillingTools");
        
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
        	
        	Page<StopDrillingTool> page = stopDrillingToolRepository.findAll(pageable);
            HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/stop-drilling-tools");
            return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
        	
        }else {
        	
        	Page<StopDrillingTool> page = stopDrillingToolRepository.findByAddedByIsCurrentUser(pageable);
            HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/stop-drilling-tools");
            return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
        }
        
        
        
    }

    /**
     * GET  /stop-drilling-tools/:id : get the "id" stopDrillingTool.
     *
     * @param id the id of the stopDrillingTool to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the stopDrillingTool, or with status 404 (Not Found)
     */
    @GetMapping("/stop-drilling-tools/{id}")
    @Timed
    public ResponseEntity<StopDrillingTool> getStopDrillingTool(@PathVariable Long id) {
        log.debug("REST request to get StopDrillingTool : {}", id);
        StopDrillingTool stopDrillingTool = stopDrillingToolRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(stopDrillingTool));
    }

    /**
     * DELETE  /stop-drilling-tools/:id : delete the "id" stopDrillingTool.
     *
     * @param id the id of the stopDrillingTool to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/stop-drilling-tools/{id}")
    @Timed
    public ResponseEntity<Void> deleteStopDrillingTool(@PathVariable Long id) {
        log.debug("REST request to delete StopDrillingTool : {}", id);
        stopDrillingToolRepository.delete(id);
        stopDrillingToolSearchRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/stop-drilling-tools?query=:query : search for the stopDrillingTool corresponding
     * to the query.
     *
     * @param query the query of the stopDrillingTool search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/stop-drilling-tools")
    @Timed
    public ResponseEntity<List<StopDrillingTool>> searchStopDrillingTools(@RequestParam String query, @ApiParam Pageable pageable) {
        log.debug("REST request to search for a page of StopDrillingTools for query {}", query);
        Page<StopDrillingTool> page = stopDrillingToolSearchRepository.search(queryStringQuery(query), pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/stop-drilling-tools");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}
