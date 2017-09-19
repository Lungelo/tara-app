package com.tara.org.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.tara.org.domain.Authority;
import com.tara.org.domain.Company;
import com.tara.org.domain.DevSupportingTool;
import com.tara.org.domain.User;
import com.tara.org.domain.UserExtra;

import javax.inject.Inject;

import com.tara.org.repository.DevSupportingToolRepository;
import com.tara.org.repository.UserExtraRepository;
import com.tara.org.repository.search.DevSupportingToolSearchRepository;
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
 * REST controller for managing DevSupportingTool.
 */
@RestController
@RequestMapping("/api")
public class DevSupportingToolResource {

    private final Logger log = LoggerFactory.getLogger(DevSupportingToolResource.class);

    private static final String ENTITY_NAME = "devSupportingTool";

    private final DevSupportingToolRepository devSupportingToolRepository;

    private final DevSupportingToolSearchRepository devSupportingToolSearchRepository;
    
    @Inject
    private UserService userService;
    
    @Inject
    private UserExtraRepository userExtraRepository;

    public DevSupportingToolResource(DevSupportingToolRepository devSupportingToolRepository, DevSupportingToolSearchRepository devSupportingToolSearchRepository) {
        this.devSupportingToolRepository = devSupportingToolRepository;
        this.devSupportingToolSearchRepository = devSupportingToolSearchRepository;
    }

    /**
     * POST  /dev-supporting-tools : Create a new devSupportingTool.
     *
     * @param devSupportingTool the devSupportingTool to create
     * @return the ResponseEntity with status 201 (Created) and with body the new devSupportingTool, or with status 400 (Bad Request) if the devSupportingTool has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/dev-supporting-tools")
    @Timed
    public ResponseEntity<DevSupportingTool> createDevSupportingTool(@Valid @RequestBody DevSupportingTool devSupportingTool) throws URISyntaxException {
        log.debug("REST request to save DevSupportingTool : {}", devSupportingTool);
        if (devSupportingTool.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new devSupportingTool cannot already have an ID")).body(null);
        }
        User user =userService.getUserWithAuthorities();
        UserExtra userExtra=userExtraRepository.findByUser(user);
        Company company=userExtra.getCompany();
        devSupportingTool.setAddedBy(user);
        devSupportingTool.setOemName(company);
        DevSupportingTool result = devSupportingToolRepository.save(devSupportingTool);
        devSupportingToolSearchRepository.save(result);
        return ResponseEntity.created(new URI("/api/dev-supporting-tools/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /dev-supporting-tools : Updates an existing devSupportingTool.
     *
     * @param devSupportingTool the devSupportingTool to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated devSupportingTool,
     * or with status 400 (Bad Request) if the devSupportingTool is not valid,
     * or with status 500 (Internal Server Error) if the devSupportingTool couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/dev-supporting-tools")
    @Timed
    public ResponseEntity<DevSupportingTool> updateDevSupportingTool(@Valid @RequestBody DevSupportingTool devSupportingTool) throws URISyntaxException {
        log.debug("REST request to update DevSupportingTool : {}", devSupportingTool);
        if (devSupportingTool.getId() == null) {
            return createDevSupportingTool(devSupportingTool);
        }
        DevSupportingTool result = devSupportingToolRepository.save(devSupportingTool);
        devSupportingToolSearchRepository.save(result);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, devSupportingTool.getId().toString()))
            .body(result);
    }

    /**
     * GET  /dev-supporting-tools : get all the devSupportingTools.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of devSupportingTools in body
     */
    @GetMapping("/dev-supporting-tools")
    @Timed
    public ResponseEntity<List<DevSupportingTool>> getAllDevSupportingTools(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of DevSupportingTools");
        
        
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
        	
        	Page<DevSupportingTool> page = devSupportingToolRepository.findAll(pageable);
            HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/dev-supporting-tools");
            return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
        	
        }else {
        	
        	Page<DevSupportingTool> page = devSupportingToolRepository.findByAddedByIsCurrentUser(pageable);
            HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/dev-supporting-tools");
            return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
        }
        
        
        
    }

    /**
     * GET  /dev-supporting-tools/:id : get the "id" devSupportingTool.
     *
     * @param id the id of the devSupportingTool to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the devSupportingTool, or with status 404 (Not Found)
     */
    @GetMapping("/dev-supporting-tools/{id}")
    @Timed
    public ResponseEntity<DevSupportingTool> getDevSupportingTool(@PathVariable Long id) {
        log.debug("REST request to get DevSupportingTool : {}", id);
        DevSupportingTool devSupportingTool = devSupportingToolRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(devSupportingTool));
    }

    /**
     * DELETE  /dev-supporting-tools/:id : delete the "id" devSupportingTool.
     *
     * @param id the id of the devSupportingTool to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/dev-supporting-tools/{id}")
    @Timed
    public ResponseEntity<Void> deleteDevSupportingTool(@PathVariable Long id) {
        log.debug("REST request to delete DevSupportingTool : {}", id);
        devSupportingToolRepository.delete(id);
        devSupportingToolSearchRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/dev-supporting-tools?query=:query : search for the devSupportingTool corresponding
     * to the query.
     *
     * @param query the query of the devSupportingTool search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/dev-supporting-tools")
    @Timed
    public ResponseEntity<List<DevSupportingTool>> searchDevSupportingTools(@RequestParam String query, @ApiParam Pageable pageable) {
        log.debug("REST request to search for a page of DevSupportingTools for query {}", query);
        Page<DevSupportingTool> page = devSupportingToolSearchRepository.search(queryStringQuery(query), pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/dev-supporting-tools");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}
