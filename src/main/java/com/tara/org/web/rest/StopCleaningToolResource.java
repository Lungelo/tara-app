package com.tara.org.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.tara.org.domain.Authority;
import com.tara.org.domain.Company;
import com.tara.org.domain.StopCleaningTool;
import com.tara.org.domain.User;
import com.tara.org.domain.UserExtra;
import com.tara.org.repository.StopCleaningToolRepository;
import com.tara.org.repository.UserExtraRepository;
import com.tara.org.repository.search.StopCleaningToolSearchRepository;
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
 * REST controller for managing StopCleaningTool.
 */
@RestController
@RequestMapping("/api")
public class StopCleaningToolResource {

    private final Logger log = LoggerFactory.getLogger(StopCleaningToolResource.class);

    private static final String ENTITY_NAME = "stopCleaningTool";

    private final StopCleaningToolRepository stopCleaningToolRepository;

    private final StopCleaningToolSearchRepository stopCleaningToolSearchRepository;
    
    @Inject
    private UserService userService;
    
    @Inject
    private UserExtraRepository userExtraRepository;

    public StopCleaningToolResource(StopCleaningToolRepository stopCleaningToolRepository, StopCleaningToolSearchRepository stopCleaningToolSearchRepository) {
        this.stopCleaningToolRepository = stopCleaningToolRepository;
        this.stopCleaningToolSearchRepository = stopCleaningToolSearchRepository;
    }

    /**
     * POST  /stop-cleaning-tools : Create a new stopCleaningTool.
     *
     * @param stopCleaningTool the stopCleaningTool to create
     * @return the ResponseEntity with status 201 (Created) and with body the new stopCleaningTool, or with status 400 (Bad Request) if the stopCleaningTool has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/stop-cleaning-tools")
    @Timed
    public ResponseEntity<StopCleaningTool> createStopCleaningTool(@Valid @RequestBody StopCleaningTool stopCleaningTool) throws URISyntaxException {
        log.debug("REST request to save StopCleaningTool : {}", stopCleaningTool);
        if (stopCleaningTool.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new stopCleaningTool cannot already have an ID")).body(null);
        }
        User user =userService.getUserWithAuthorities();
        UserExtra userExtra=userExtraRepository.findByUser(user);
        Company company=userExtra.getCompany();
        stopCleaningTool.setAddedBy(user);
        stopCleaningTool.setOemName(company);
        StopCleaningTool result = stopCleaningToolRepository.save(stopCleaningTool);
        stopCleaningToolSearchRepository.save(result);
        return ResponseEntity.created(new URI("/api/stop-cleaning-tools/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /stop-cleaning-tools : Updates an existing stopCleaningTool.
     *
     * @param stopCleaningTool the stopCleaningTool to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated stopCleaningTool,
     * or with status 400 (Bad Request) if the stopCleaningTool is not valid,
     * or with status 500 (Internal Server Error) if the stopCleaningTool couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/stop-cleaning-tools")
    @Timed
    public ResponseEntity<StopCleaningTool> updateStopCleaningTool(@Valid @RequestBody StopCleaningTool stopCleaningTool) throws URISyntaxException {
        log.debug("REST request to update StopCleaningTool : {}", stopCleaningTool);
        if (stopCleaningTool.getId() == null) {
            return createStopCleaningTool(stopCleaningTool);
        }
        StopCleaningTool result = stopCleaningToolRepository.save(stopCleaningTool);
        stopCleaningToolSearchRepository.save(result);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, stopCleaningTool.getId().toString()))
            .body(result);
    }

    /**
     * GET  /stop-cleaning-tools : get all the stopCleaningTools.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of stopCleaningTools in body
     */
    @GetMapping("/stop-cleaning-tools")
    @Timed
    public ResponseEntity<List<StopCleaningTool>> getAllStopCleaningTools(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of StopCleaningTools");
        
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
        	
        	 Page<StopCleaningTool> page = stopCleaningToolRepository.findAll(pageable);
             HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/stop-cleaning-tools");
             return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
        	
        }else {
        	
        	
        	 Page<StopCleaningTool> page = stopCleaningToolRepository.findByAddedByIsCurrentUser(pageable);
             HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/stop-cleaning-tools");
             return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
        }
        
       
    }

    /**
     * GET  /stop-cleaning-tools/:id : get the "id" stopCleaningTool.
     *
     * @param id the id of the stopCleaningTool to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the stopCleaningTool, or with status 404 (Not Found)
     */
    @GetMapping("/stop-cleaning-tools/{id}")
    @Timed
    public ResponseEntity<StopCleaningTool> getStopCleaningTool(@PathVariable Long id) {
        log.debug("REST request to get StopCleaningTool : {}", id);
        StopCleaningTool stopCleaningTool = stopCleaningToolRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(stopCleaningTool));
    }

    /**
     * DELETE  /stop-cleaning-tools/:id : delete the "id" stopCleaningTool.
     *
     * @param id the id of the stopCleaningTool to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/stop-cleaning-tools/{id}")
    @Timed
    public ResponseEntity<Void> deleteStopCleaningTool(@PathVariable Long id) {
        log.debug("REST request to delete StopCleaningTool : {}", id);
        stopCleaningToolRepository.delete(id);
        stopCleaningToolSearchRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/stop-cleaning-tools?query=:query : search for the stopCleaningTool corresponding
     * to the query.
     *
     * @param query the query of the stopCleaningTool search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/stop-cleaning-tools")
    @Timed
    public ResponseEntity<List<StopCleaningTool>> searchStopCleaningTools(@RequestParam String query, @ApiParam Pageable pageable) {
        log.debug("REST request to search for a page of StopCleaningTools for query {}", query);
        Page<StopCleaningTool> page = stopCleaningToolSearchRepository.search(queryStringQuery(query), pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/stop-cleaning-tools");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}
