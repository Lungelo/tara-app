package com.tara.org.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.tara.org.domain.Authority;
import com.tara.org.domain.Company;
import com.tara.org.domain.DevCleaningTool;
import com.tara.org.domain.User;
import com.tara.org.domain.UserExtra;

import javax.inject.Inject;

import com.tara.org.repository.DevCleaningToolRepository;
import com.tara.org.repository.UserExtraRepository;
import com.tara.org.repository.search.DevCleaningToolSearchRepository;
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
 * REST controller for managing DevCleaningTool.
 */
@RestController
@RequestMapping("/api")
public class DevCleaningToolResource {

    private final Logger log = LoggerFactory.getLogger(DevCleaningToolResource.class);

    private static final String ENTITY_NAME = "devCleaningTool";

    private final DevCleaningToolRepository devCleaningToolRepository;

    private final DevCleaningToolSearchRepository devCleaningToolSearchRepository;
    
    @Inject
    private UserService userService;
    
    @Inject
    private UserExtraRepository userExtraRepository;

    public DevCleaningToolResource(DevCleaningToolRepository devCleaningToolRepository, DevCleaningToolSearchRepository devCleaningToolSearchRepository) {
        this.devCleaningToolRepository = devCleaningToolRepository;
        this.devCleaningToolSearchRepository = devCleaningToolSearchRepository;
    }

    /**
     * POST  /dev-cleaning-tools : Create a new devCleaningTool.
     *
     * @param devCleaningTool the devCleaningTool to create
     * @return the ResponseEntity with status 201 (Created) and with body the new devCleaningTool, or with status 400 (Bad Request) if the devCleaningTool has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/dev-cleaning-tools")
    @Timed
    public ResponseEntity<DevCleaningTool> createDevCleaningTool(@Valid @RequestBody DevCleaningTool devCleaningTool) throws URISyntaxException {
        log.debug("REST request to save DevCleaningTool : {}", devCleaningTool);
        if (devCleaningTool.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new devCleaningTool cannot already have an ID")).body(null);
        }
        
        User user =userService.getUserWithAuthorities();
        UserExtra userExtra=userExtraRepository.findByUser(user);
        Company company=userExtra.getCompany();
        devCleaningTool.setAddedBy(user);
        devCleaningTool.setOemName(company);
        DevCleaningTool result = devCleaningToolRepository.save(devCleaningTool);
        devCleaningToolSearchRepository.save(result);
        return ResponseEntity.created(new URI("/api/dev-cleaning-tools/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /dev-cleaning-tools : Updates an existing devCleaningTool.
     *
     * @param devCleaningTool the devCleaningTool to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated devCleaningTool,
     * or with status 400 (Bad Request) if the devCleaningTool is not valid,
     * or with status 500 (Internal Server Error) if the devCleaningTool couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/dev-cleaning-tools")
    @Timed
    public ResponseEntity<DevCleaningTool> updateDevCleaningTool(@Valid @RequestBody DevCleaningTool devCleaningTool) throws URISyntaxException {
        log.debug("REST request to update DevCleaningTool : {}", devCleaningTool);
        if (devCleaningTool.getId() == null) {
            return createDevCleaningTool(devCleaningTool);
        }
        DevCleaningTool result = devCleaningToolRepository.save(devCleaningTool);
        devCleaningToolSearchRepository.save(result);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, devCleaningTool.getId().toString()))
            .body(result);
    }

    /**
     * GET  /dev-cleaning-tools : get all the devCleaningTools.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of devCleaningTools in body
     */
    @GetMapping("/dev-cleaning-tools")
    @Timed
    public ResponseEntity<List<DevCleaningTool>> getAllDevCleaningTools(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of DevCleaningTools");
        
        
        
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
        	
        	Page<DevCleaningTool> page = devCleaningToolRepository.findAll(pageable);
            HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/dev-cleaning-tools");
            return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
        }else {
        	
        	Page<DevCleaningTool> page = devCleaningToolRepository.findByAddedByIsCurrentUser(pageable);
            HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/dev-cleaning-tools");
            return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
        }
        
        
        
        
    }

    /**
     * GET  /dev-cleaning-tools/:id : get the "id" devCleaningTool.
     *
     * @param id the id of the devCleaningTool to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the devCleaningTool, or with status 404 (Not Found)
     */
    @GetMapping("/dev-cleaning-tools/{id}")
    @Timed
    public ResponseEntity<DevCleaningTool> getDevCleaningTool(@PathVariable Long id) {
        log.debug("REST request to get DevCleaningTool : {}", id);
        DevCleaningTool devCleaningTool = devCleaningToolRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(devCleaningTool));
    }

    /**
     * DELETE  /dev-cleaning-tools/:id : delete the "id" devCleaningTool.
     *
     * @param id the id of the devCleaningTool to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/dev-cleaning-tools/{id}")
    @Timed
    public ResponseEntity<Void> deleteDevCleaningTool(@PathVariable Long id) {
        log.debug("REST request to delete DevCleaningTool : {}", id);
        devCleaningToolRepository.delete(id);
        devCleaningToolSearchRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/dev-cleaning-tools?query=:query : search for the devCleaningTool corresponding
     * to the query.
     *
     * @param query the query of the devCleaningTool search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/dev-cleaning-tools")
    @Timed
    public ResponseEntity<List<DevCleaningTool>> searchDevCleaningTools(@RequestParam String query, @ApiParam Pageable pageable) {
        log.debug("REST request to search for a page of DevCleaningTools for query {}", query);
        Page<DevCleaningTool> page = devCleaningToolSearchRepository.search(queryStringQuery(query), pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/dev-cleaning-tools");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}
