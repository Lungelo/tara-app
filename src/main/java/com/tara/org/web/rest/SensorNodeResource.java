package com.tara.org.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.tara.org.domain.Authority;
import com.tara.org.domain.Company;
import com.tara.org.domain.SensorNode;
import com.tara.org.domain.User;
import com.tara.org.domain.UserExtra;
import com.tara.org.repository.SensorNodeRepository;
import com.tara.org.repository.UserExtraRepository;
import com.tara.org.repository.search.SensorNodeSearchRepository;
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
 * REST controller for managing SensorNode.
 */
@RestController
@RequestMapping("/api")
public class SensorNodeResource {

    private final Logger log = LoggerFactory.getLogger(SensorNodeResource.class);

    private static final String ENTITY_NAME = "sensorNode";

    private final SensorNodeRepository sensorNodeRepository;

    private final SensorNodeSearchRepository sensorNodeSearchRepository;
    
    @Inject
    private UserService userService;
    
    @Inject
    private UserExtraRepository userExtraRepository;

    public SensorNodeResource(SensorNodeRepository sensorNodeRepository, SensorNodeSearchRepository sensorNodeSearchRepository) {
        this.sensorNodeRepository = sensorNodeRepository;
        this.sensorNodeSearchRepository = sensorNodeSearchRepository;
    }

    /**
     * POST  /sensor-nodes : Create a new sensorNode.
     *
     * @param sensorNode the sensorNode to create
     * @return the ResponseEntity with status 201 (Created) and with body the new sensorNode, or with status 400 (Bad Request) if the sensorNode has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/sensor-nodes")
    @Timed
    public ResponseEntity<SensorNode> createSensorNode(@Valid @RequestBody SensorNode sensorNode) throws URISyntaxException {
        log.debug("REST request to save SensorNode : {}", sensorNode);
        if (sensorNode.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new sensorNode cannot already have an ID")).body(null);
        }
        User user =userService.getUserWithAuthorities();
        UserExtra userExtra=userExtraRepository.findByUser(user);
        Company company=userExtra.getCompany();
        sensorNode.setAddedBy(user);
        sensorNode.setOemName(company);
        SensorNode result = sensorNodeRepository.save(sensorNode);
        sensorNodeSearchRepository.save(result);
        return ResponseEntity.created(new URI("/api/sensor-nodes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /sensor-nodes : Updates an existing sensorNode.
     *
     * @param sensorNode the sensorNode to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated sensorNode,
     * or with status 400 (Bad Request) if the sensorNode is not valid,
     * or with status 500 (Internal Server Error) if the sensorNode couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/sensor-nodes")
    @Timed
    public ResponseEntity<SensorNode> updateSensorNode(@Valid @RequestBody SensorNode sensorNode) throws URISyntaxException {
        log.debug("REST request to update SensorNode : {}", sensorNode);
        if (sensorNode.getId() == null) {
            return createSensorNode(sensorNode);
        }
        SensorNode result = sensorNodeRepository.save(sensorNode);
        sensorNodeSearchRepository.save(result);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, sensorNode.getId().toString()))
            .body(result);
    }

    /**
     * GET  /sensor-nodes : get all the sensorNodes.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of sensorNodes in body
     */
    @GetMapping("/sensor-nodes")
    @Timed
    public ResponseEntity<List<SensorNode>> getAllSensorNodes(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of SensorNodes");
        
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
        	
        	 Page<SensorNode> page = sensorNodeRepository.findAll(pageable);
             HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/sensor-nodes");
             return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
        	
        }else {
        	
        	 Page<SensorNode> page = sensorNodeRepository.findByAddedByIsCurrentUser(pageable);
             HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/sensor-nodes");
             return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
        	
        }
        
       
    }

    /**
     * GET  /sensor-nodes/:id : get the "id" sensorNode.
     *
     * @param id the id of the sensorNode to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the sensorNode, or with status 404 (Not Found)
     */
    @GetMapping("/sensor-nodes/{id}")
    @Timed
    public ResponseEntity<SensorNode> getSensorNode(@PathVariable Long id) {
        log.debug("REST request to get SensorNode : {}", id);
        SensorNode sensorNode = sensorNodeRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(sensorNode));
    }

    /**
     * DELETE  /sensor-nodes/:id : delete the "id" sensorNode.
     *
     * @param id the id of the sensorNode to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/sensor-nodes/{id}")
    @Timed
    public ResponseEntity<Void> deleteSensorNode(@PathVariable Long id) {
        log.debug("REST request to delete SensorNode : {}", id);
        sensorNodeRepository.delete(id);
        sensorNodeSearchRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/sensor-nodes?query=:query : search for the sensorNode corresponding
     * to the query.
     *
     * @param query the query of the sensorNode search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/sensor-nodes")
    @Timed
    public ResponseEntity<List<SensorNode>> searchSensorNodes(@RequestParam String query, @ApiParam Pageable pageable) {
        log.debug("REST request to search for a page of SensorNodes for query {}", query);
        Page<SensorNode> page = sensorNodeSearchRepository.search(queryStringQuery(query), pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/sensor-nodes");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}
