package com.tara.org.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.tara.org.domain.Authority;
import com.tara.org.domain.Company;
import com.tara.org.domain.StopSupportingTool;
import com.tara.org.domain.User;
import com.tara.org.domain.UserExtra;
import com.tara.org.repository.StopSupportingToolRepository;
import com.tara.org.repository.UserExtraRepository;
import com.tara.org.repository.search.StopSupportingToolSearchRepository;
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
 * REST controller for managing StopSupportingTool.
 */
@RestController
@RequestMapping("/api")
public class StopSupportingToolResource {

    private final Logger log = LoggerFactory.getLogger(StopSupportingToolResource.class);

    private static final String ENTITY_NAME = "stopSupportingTool";

    private final StopSupportingToolRepository stopSupportingToolRepository;

    private final StopSupportingToolSearchRepository stopSupportingToolSearchRepository;
    
    @Inject
    private UserService userService;
    
    @Inject
    private UserExtraRepository userExtraRepository;

    public StopSupportingToolResource(StopSupportingToolRepository stopSupportingToolRepository, StopSupportingToolSearchRepository stopSupportingToolSearchRepository) {
        this.stopSupportingToolRepository = stopSupportingToolRepository;
        this.stopSupportingToolSearchRepository = stopSupportingToolSearchRepository;
    }

    /**
     * POST  /stop-supporting-tools : Create a new stopSupportingTool.
     *
     * @param stopSupportingTool the stopSupportingTool to create
     * @return the ResponseEntity with status 201 (Created) and with body the new stopSupportingTool, or with status 400 (Bad Request) if the stopSupportingTool has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/stop-supporting-tools")
    @Timed
    public ResponseEntity<StopSupportingTool> createStopSupportingTool(@Valid @RequestBody StopSupportingTool stopSupportingTool) throws URISyntaxException {
        log.debug("REST request to save StopSupportingTool : {}", stopSupportingTool);
        if (stopSupportingTool.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new stopSupportingTool cannot already have an ID")).body(null);
        }
        User user =userService.getUserWithAuthorities();
        UserExtra userExtra=userExtraRepository.findByUser(user);
        Company company=userExtra.getCompany();
        stopSupportingTool.setAddedBy(user);
        stopSupportingTool.setOemName(company);
        StopSupportingTool result = stopSupportingToolRepository.save(stopSupportingTool);
        stopSupportingToolSearchRepository.save(result);
        return ResponseEntity.created(new URI("/api/stop-supporting-tools/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /stop-supporting-tools : Updates an existing stopSupportingTool.
     *
     * @param stopSupportingTool the stopSupportingTool to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated stopSupportingTool,
     * or with status 400 (Bad Request) if the stopSupportingTool is not valid,
     * or with status 500 (Internal Server Error) if the stopSupportingTool couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/stop-supporting-tools")
    @Timed
    public ResponseEntity<StopSupportingTool> updateStopSupportingTool(@Valid @RequestBody StopSupportingTool stopSupportingTool) throws URISyntaxException {
        log.debug("REST request to update StopSupportingTool : {}", stopSupportingTool);
        if (stopSupportingTool.getId() == null) {
            return createStopSupportingTool(stopSupportingTool);
        }
        StopSupportingTool result = stopSupportingToolRepository.save(stopSupportingTool);
        stopSupportingToolSearchRepository.save(result);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, stopSupportingTool.getId().toString()))
            .body(result);
    }

    /**
     * GET  /stop-supporting-tools : get all the stopSupportingTools.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of stopSupportingTools in body
     */
    @GetMapping("/stop-supporting-tools")
    @Timed
    public ResponseEntity<List<StopSupportingTool>> getAllStopSupportingTools(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of StopSupportingTools");
        
        
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
        	
        	 Page<StopSupportingTool> page = stopSupportingToolRepository.findAll(pageable);
             HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/stop-supporting-tools");
             return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
        	
        }else {
        	
        	
        	 Page<StopSupportingTool> page = stopSupportingToolRepository.findByAddedByIsCurrentUser(pageable);
             HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/stop-supporting-tools");
             return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
        }
        
       
    }

    /**
     * GET  /stop-supporting-tools/:id : get the "id" stopSupportingTool.
     *
     * @param id the id of the stopSupportingTool to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the stopSupportingTool, or with status 404 (Not Found)
     */
    @GetMapping("/stop-supporting-tools/{id}")
    @Timed
    public ResponseEntity<StopSupportingTool> getStopSupportingTool(@PathVariable Long id) {
        log.debug("REST request to get StopSupportingTool : {}", id);
        StopSupportingTool stopSupportingTool = stopSupportingToolRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(stopSupportingTool));
    }

    /**
     * DELETE  /stop-supporting-tools/:id : delete the "id" stopSupportingTool.
     *
     * @param id the id of the stopSupportingTool to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/stop-supporting-tools/{id}")
    @Timed
    public ResponseEntity<Void> deleteStopSupportingTool(@PathVariable Long id) {
        log.debug("REST request to delete StopSupportingTool : {}", id);
        stopSupportingToolRepository.delete(id);
        stopSupportingToolSearchRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/stop-supporting-tools?query=:query : search for the stopSupportingTool corresponding
     * to the query.
     *
     * @param query the query of the stopSupportingTool search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/stop-supporting-tools")
    @Timed
    public ResponseEntity<List<StopSupportingTool>> searchStopSupportingTools(@RequestParam String query, @ApiParam Pageable pageable) {
        log.debug("REST request to search for a page of StopSupportingTools for query {}", query);
        Page<StopSupportingTool> page = stopSupportingToolSearchRepository.search(queryStringQuery(query), pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/stop-supporting-tools");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}
