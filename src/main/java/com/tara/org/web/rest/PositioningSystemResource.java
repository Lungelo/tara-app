package com.tara.org.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.tara.org.domain.Authority;
import com.tara.org.domain.Company;
import com.tara.org.domain.PositioningSystem;
import com.tara.org.domain.User;
import com.tara.org.domain.UserExtra;

import javax.inject.Inject;

import com.tara.org.repository.PositioningSystemRepository;
import com.tara.org.repository.UserExtraRepository;
import com.tara.org.repository.search.PositioningSystemSearchRepository;
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
 * REST controller for managing PositioningSystem.
 */
@RestController
@RequestMapping("/api")
public class PositioningSystemResource {

    private final Logger log = LoggerFactory.getLogger(PositioningSystemResource.class);

    private static final String ENTITY_NAME = "positioningSystem";

    private final PositioningSystemRepository positioningSystemRepository;

    private final PositioningSystemSearchRepository positioningSystemSearchRepository;
    
    @Inject
    private UserService userService;
    
    @Inject
    private UserExtraRepository userExtraRepository;

    public PositioningSystemResource(PositioningSystemRepository positioningSystemRepository, PositioningSystemSearchRepository positioningSystemSearchRepository) {
        this.positioningSystemRepository = positioningSystemRepository;
        this.positioningSystemSearchRepository = positioningSystemSearchRepository;
    }

    /**
     * POST  /positioning-systems : Create a new positioningSystem.
     *
     * @param positioningSystem the positioningSystem to create
     * @return the ResponseEntity with status 201 (Created) and with body the new positioningSystem, or with status 400 (Bad Request) if the positioningSystem has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/positioning-systems")
    @Timed
    public ResponseEntity<PositioningSystem> createPositioningSystem(@Valid @RequestBody PositioningSystem positioningSystem) throws URISyntaxException {
        log.debug("REST request to save PositioningSystem : {}", positioningSystem);
        if (positioningSystem.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new positioningSystem cannot already have an ID")).body(null);
        }
        User user =userService.getUserWithAuthorities();
        UserExtra userExtra=userExtraRepository.findByUser(user);
        Company company=userExtra.getCompany();
        positioningSystem.setAddedBy(user);
        positioningSystem.setOemName(company);
        PositioningSystem result = positioningSystemRepository.save(positioningSystem);
        positioningSystemSearchRepository.save(result);
        return ResponseEntity.created(new URI("/api/positioning-systems/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /positioning-systems : Updates an existing positioningSystem.
     *
     * @param positioningSystem the positioningSystem to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated positioningSystem,
     * or with status 400 (Bad Request) if the positioningSystem is not valid,
     * or with status 500 (Internal Server Error) if the positioningSystem couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/positioning-systems")
    @Timed
    public ResponseEntity<PositioningSystem> updatePositioningSystem(@Valid @RequestBody PositioningSystem positioningSystem) throws URISyntaxException {
        log.debug("REST request to update PositioningSystem : {}", positioningSystem);
        if (positioningSystem.getId() == null) {
            return createPositioningSystem(positioningSystem);
        }
        PositioningSystem result = positioningSystemRepository.save(positioningSystem);
        positioningSystemSearchRepository.save(result);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, positioningSystem.getId().toString()))
            .body(result);
    }

    /**
     * GET  /positioning-systems : get all the positioningSystems.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of positioningSystems in body
     */
    @GetMapping("/positioning-systems")
    @Timed
    public ResponseEntity<List<PositioningSystem>> getAllPositioningSystems(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of PositioningSystems");
        
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
        	
        	 Page<PositioningSystem> page = positioningSystemRepository.findAll(pageable);
             HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/positioning-systems");
             return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
        	
        }else {
        	
        	 Page<PositioningSystem> page = positioningSystemRepository.findByAddedByIsCurrentUser(pageable);
             HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/positioning-systems");
             return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
        	
        }
        
        
       
    }

    /**
     * GET  /positioning-systems/:id : get the "id" positioningSystem.
     *
     * @param id the id of the positioningSystem to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the positioningSystem, or with status 404 (Not Found)
     */
    @GetMapping("/positioning-systems/{id}")
    @Timed
    public ResponseEntity<PositioningSystem> getPositioningSystem(@PathVariable Long id) {
        log.debug("REST request to get PositioningSystem : {}", id);
        PositioningSystem positioningSystem = positioningSystemRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(positioningSystem));
    }

    /**
     * DELETE  /positioning-systems/:id : delete the "id" positioningSystem.
     *
     * @param id the id of the positioningSystem to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/positioning-systems/{id}")
    @Timed
    public ResponseEntity<Void> deletePositioningSystem(@PathVariable Long id) {
        log.debug("REST request to delete PositioningSystem : {}", id);
        positioningSystemRepository.delete(id);
        positioningSystemSearchRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/positioning-systems?query=:query : search for the positioningSystem corresponding
     * to the query.
     *
     * @param query the query of the positioningSystem search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/positioning-systems")
    @Timed
    public ResponseEntity<List<PositioningSystem>> searchPositioningSystems(@RequestParam String query, @ApiParam Pageable pageable) {
        log.debug("REST request to search for a page of PositioningSystems for query {}", query);
        Page<PositioningSystem> page = positioningSystemSearchRepository.search(queryStringQuery(query), pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/positioning-systems");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}
