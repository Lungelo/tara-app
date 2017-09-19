package com.tara.org.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.tara.org.domain.Authority;
import com.tara.org.domain.Company;
import com.tara.org.domain.DevDrillingTool;
import com.tara.org.domain.User;
import com.tara.org.domain.UserExtra;
import com.tara.org.repository.DevDrillingToolRepository;
import com.tara.org.repository.UserExtraRepository;
import com.tara.org.repository.search.DevDrillingToolSearchRepository;
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
 * REST controller for managing DevDrillingTool.
 */
@RestController
@RequestMapping("/api")
public class DevDrillingToolResource {

    private final Logger log = LoggerFactory.getLogger(DevDrillingToolResource.class);

    private static final String ENTITY_NAME = "devDrillingTool";

    private final DevDrillingToolRepository devDrillingToolRepository;
    
    @Inject
    private UserService userService;
    
    @Inject
    private UserExtraRepository userExtraRepository;

    private final DevDrillingToolSearchRepository devDrillingToolSearchRepository;
    public DevDrillingToolResource(DevDrillingToolRepository devDrillingToolRepository, DevDrillingToolSearchRepository devDrillingToolSearchRepository) {
        this.devDrillingToolRepository = devDrillingToolRepository;
        this.devDrillingToolSearchRepository = devDrillingToolSearchRepository;
    }

    /**
     * POST  /dev-drilling-tools : Create a new devDrillingTool.
     *
     * @param devDrillingTool the devDrillingTool to create
     * @return the ResponseEntity with status 201 (Created) and with body the new devDrillingTool, or with status 400 (Bad Request) if the devDrillingTool has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/dev-drilling-tools")
    @Timed
    public ResponseEntity<DevDrillingTool> createDevDrillingTool(@Valid @RequestBody DevDrillingTool devDrillingTool) throws URISyntaxException {
        log.debug("REST request to save DevDrillingTool : {}", devDrillingTool);
        if (devDrillingTool.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new devDrillingTool cannot already have an ID")).body(null);
        }
        
        User user =userService.getUserWithAuthorities();
        UserExtra userExtra=userExtraRepository.findByUser(user);
        Company company=userExtra.getCompany();
        devDrillingTool.setAddedBy(user);
        devDrillingTool.setOemName(company);
        DevDrillingTool result = devDrillingToolRepository.save(devDrillingTool);
        devDrillingToolSearchRepository.save(result);
        return ResponseEntity.created(new URI("/api/dev-drilling-tools/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /dev-drilling-tools : Updates an existing devDrillingTool.
     *
     * @param devDrillingTool the devDrillingTool to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated devDrillingTool,
     * or with status 400 (Bad Request) if the devDrillingTool is not valid,
     * or with status 500 (Internal Server Error) if the devDrillingTool couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/dev-drilling-tools")
    @Timed
    public ResponseEntity<DevDrillingTool> updateDevDrillingTool(@Valid @RequestBody DevDrillingTool devDrillingTool) throws URISyntaxException {
        log.debug("REST request to update DevDrillingTool : {}", devDrillingTool);
        if (devDrillingTool.getId() == null) {
            return createDevDrillingTool(devDrillingTool);
        }
        DevDrillingTool result = devDrillingToolRepository.save(devDrillingTool);
        devDrillingToolSearchRepository.save(result);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, devDrillingTool.getId().toString()))
            .body(result);
    }

    /**
     * GET  /dev-drilling-tools : get all the devDrillingTools.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of devDrillingTools in body
     */
    @GetMapping("/dev-drilling-tools")
    @Timed
    public ResponseEntity<List<DevDrillingTool>> getAllDevDrillingTools(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of DevDrillingTools");
        
        
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
        	
        	Page<DevDrillingTool> page = devDrillingToolRepository.findAll(pageable);
            HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/dev-drilling-tools");
            return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
        	
        }else {
        	
        	Page<DevDrillingTool> page = devDrillingToolRepository.findByAddedByIsCurrentUser(pageable);
            HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/dev-drilling-tools");
            return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
        	
        }
        
        
    }

    /**
     * GET  /dev-drilling-tools/:id : get the "id" devDrillingTool.
     *
     * @param id the id of the devDrillingTool to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the devDrillingTool, or with status 404 (Not Found)
     */
    @GetMapping("/dev-drilling-tools/{id}")
    @Timed
    public ResponseEntity<DevDrillingTool> getDevDrillingTool(@PathVariable Long id) {
        log.debug("REST request to get DevDrillingTool : {}", id);
        DevDrillingTool devDrillingTool = devDrillingToolRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(devDrillingTool));
    }

    /**
     * DELETE  /dev-drilling-tools/:id : delete the "id" devDrillingTool.
     *
     * @param id the id of the devDrillingTool to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/dev-drilling-tools/{id}")
    @Timed
    public ResponseEntity<Void> deleteDevDrillingTool(@PathVariable Long id) {
        log.debug("REST request to delete DevDrillingTool : {}", id);
        devDrillingToolRepository.delete(id);
        devDrillingToolSearchRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/dev-drilling-tools?query=:query : search for the devDrillingTool corresponding
     * to the query.
     *
     * @param query the query of the devDrillingTool search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/dev-drilling-tools")
    @Timed
    public ResponseEntity<List<DevDrillingTool>> searchDevDrillingTools(@RequestParam String query, @ApiParam Pageable pageable) {
        log.debug("REST request to search for a page of DevDrillingTools for query {}", query);
        Page<DevDrillingTool> page = devDrillingToolSearchRepository.search(queryStringQuery(query), pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/dev-drilling-tools");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}
