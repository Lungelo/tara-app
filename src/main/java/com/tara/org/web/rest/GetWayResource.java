package com.tara.org.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.tara.org.domain.Authority;
import com.tara.org.domain.Company;
import com.tara.org.domain.GetWay;
import com.tara.org.domain.User;
import com.tara.org.domain.UserExtra;

import javax.inject.Inject;

import com.tara.org.repository.GetWayRepository;
import com.tara.org.repository.UserExtraRepository;
import com.tara.org.repository.search.GetWaySearchRepository;
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
 * REST controller for managing GetWay.
 */
@RestController
@RequestMapping("/api")
public class GetWayResource {

    private final Logger log = LoggerFactory.getLogger(GetWayResource.class);

    private static final String ENTITY_NAME = "getWay";

    private final GetWayRepository getWayRepository;

    private final GetWaySearchRepository getWaySearchRepository;
    
    @Inject
    private UserService userService;
    
    @Inject
    private UserExtraRepository userExtraRepository;

    public GetWayResource(GetWayRepository getWayRepository, GetWaySearchRepository getWaySearchRepository) {
        this.getWayRepository = getWayRepository;
        this.getWaySearchRepository = getWaySearchRepository;
    }

    /**
     * POST  /get-ways : Create a new getWay.
     *
     * @param getWay the getWay to create
     * @return the ResponseEntity with status 201 (Created) and with body the new getWay, or with status 400 (Bad Request) if the getWay has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/get-ways")
    @Timed
    public ResponseEntity<GetWay> createGetWay(@Valid @RequestBody GetWay getWay) throws URISyntaxException {
        log.debug("REST request to save GetWay : {}", getWay);
        if (getWay.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new getWay cannot already have an ID")).body(null);
        }
        User user =userService.getUserWithAuthorities();
        UserExtra userExtra=userExtraRepository.findByUser(user);
        Company company=userExtra.getCompany();
        getWay.setAddedBy(user);
        getWay.setOemName(company);
        GetWay result = getWayRepository.save(getWay);
        getWaySearchRepository.save(result);
        return ResponseEntity.created(new URI("/api/get-ways/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /get-ways : Updates an existing getWay.
     *
     * @param getWay the getWay to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated getWay,
     * or with status 400 (Bad Request) if the getWay is not valid,
     * or with status 500 (Internal Server Error) if the getWay couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/get-ways")
    @Timed
    public ResponseEntity<GetWay> updateGetWay(@Valid @RequestBody GetWay getWay) throws URISyntaxException {
        log.debug("REST request to update GetWay : {}", getWay);
        if (getWay.getId() == null) {
            return createGetWay(getWay);
        }
        GetWay result = getWayRepository.save(getWay);
        getWaySearchRepository.save(result);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, getWay.getId().toString()))
            .body(result);
    }

    /**
     * GET  /get-ways : get all the getWays.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of getWays in body
     */
    @GetMapping("/get-ways")
    @Timed
    public ResponseEntity<List<GetWay>> getAllGetWays(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of GetWays");
        
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
        	
        	 Page<GetWay> page = getWayRepository.findAll(pageable);
             HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/get-ways");
             return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
        	
        }else {
        	
        	
        	 Page<GetWay> page = getWayRepository.findByAddedByIsCurrentUser(pageable);
             HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/get-ways");
             return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
        }
        
       
    }

    /**
     * GET  /get-ways/:id : get the "id" getWay.
     *
     * @param id the id of the getWay to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the getWay, or with status 404 (Not Found)
     */
    @GetMapping("/get-ways/{id}")
    @Timed
    public ResponseEntity<GetWay> getGetWay(@PathVariable Long id) {
        log.debug("REST request to get GetWay : {}", id);
        GetWay getWay = getWayRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(getWay));
    }

    /**
     * DELETE  /get-ways/:id : delete the "id" getWay.
     *
     * @param id the id of the getWay to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/get-ways/{id}")
    @Timed
    public ResponseEntity<Void> deleteGetWay(@PathVariable Long id) {
        log.debug("REST request to delete GetWay : {}", id);
        getWayRepository.delete(id);
        getWaySearchRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/get-ways?query=:query : search for the getWay corresponding
     * to the query.
     *
     * @param query the query of the getWay search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/get-ways")
    @Timed
    public ResponseEntity<List<GetWay>> searchGetWays(@RequestParam String query, @ApiParam Pageable pageable) {
        log.debug("REST request to search for a page of GetWays for query {}", query);
        Page<GetWay> page = getWaySearchRepository.search(queryStringQuery(query), pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/get-ways");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}
