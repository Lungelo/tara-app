package com.tara.org.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.tara.org.domain.Authority;
import com.tara.org.domain.Company;
import com.tara.org.domain.User;
import com.tara.org.domain.UserExtra;
import com.tara.org.domain.WirelessSensorNetwork;
import com.tara.org.repository.UserExtraRepository;
import com.tara.org.repository.WirelessSensorNetworkRepository;
import com.tara.org.repository.search.WirelessSensorNetworkSearchRepository;
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
 * REST controller for managing WirelessSensorNetwork.
 */
@RestController
@RequestMapping("/api")
public class WirelessSensorNetworkResource {

    private final Logger log = LoggerFactory.getLogger(WirelessSensorNetworkResource.class);

    private static final String ENTITY_NAME = "wirelessSensorNetwork";

    private final WirelessSensorNetworkRepository wirelessSensorNetworkRepository;

    private final WirelessSensorNetworkSearchRepository wirelessSensorNetworkSearchRepository;
    
    @Inject
    private UserService userService;
    
    @Inject
    private UserExtraRepository userExtraRepository;

    public WirelessSensorNetworkResource(WirelessSensorNetworkRepository wirelessSensorNetworkRepository, WirelessSensorNetworkSearchRepository wirelessSensorNetworkSearchRepository) {
        this.wirelessSensorNetworkRepository = wirelessSensorNetworkRepository;
        this.wirelessSensorNetworkSearchRepository = wirelessSensorNetworkSearchRepository;
    }

    /**
     * POST  /wireless-sensor-networks : Create a new wirelessSensorNetwork.
     *
     * @param wirelessSensorNetwork the wirelessSensorNetwork to create
     * @return the ResponseEntity with status 201 (Created) and with body the new wirelessSensorNetwork, or with status 400 (Bad Request) if the wirelessSensorNetwork has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/wireless-sensor-networks")
    @Timed
    public ResponseEntity<WirelessSensorNetwork> createWirelessSensorNetwork(@Valid @RequestBody WirelessSensorNetwork wirelessSensorNetwork) throws URISyntaxException {
        log.debug("REST request to save WirelessSensorNetwork : {}", wirelessSensorNetwork);
        if (wirelessSensorNetwork.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new wirelessSensorNetwork cannot already have an ID")).body(null);
        }
        User user =userService.getUserWithAuthorities();
        UserExtra userExtra=userExtraRepository.findByUser(user);
        Company company=userExtra.getCompany();
        wirelessSensorNetwork.setAddedBy(user);
        wirelessSensorNetwork.setOemName(company);
        WirelessSensorNetwork result = wirelessSensorNetworkRepository.save(wirelessSensorNetwork);
        wirelessSensorNetworkSearchRepository.save(result);
        return ResponseEntity.created(new URI("/api/wireless-sensor-networks/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /wireless-sensor-networks : Updates an existing wirelessSensorNetwork.
     *
     * @param wirelessSensorNetwork the wirelessSensorNetwork to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated wirelessSensorNetwork,
     * or with status 400 (Bad Request) if the wirelessSensorNetwork is not valid,
     * or with status 500 (Internal Server Error) if the wirelessSensorNetwork couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/wireless-sensor-networks")
    @Timed
    public ResponseEntity<WirelessSensorNetwork> updateWirelessSensorNetwork(@Valid @RequestBody WirelessSensorNetwork wirelessSensorNetwork) throws URISyntaxException {
        log.debug("REST request to update WirelessSensorNetwork : {}", wirelessSensorNetwork);
        if (wirelessSensorNetwork.getId() == null) {
            return createWirelessSensorNetwork(wirelessSensorNetwork);
        }
        WirelessSensorNetwork result = wirelessSensorNetworkRepository.save(wirelessSensorNetwork);
        wirelessSensorNetworkSearchRepository.save(result);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, wirelessSensorNetwork.getId().toString()))
            .body(result);
    }

    /**
     * GET  /wireless-sensor-networks : get all the wirelessSensorNetworks.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of wirelessSensorNetworks in body
     */
    @GetMapping("/wireless-sensor-networks")
    @Timed
    public ResponseEntity<List<WirelessSensorNetwork>> getAllWirelessSensorNetworks(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of WirelessSensorNetworks");
        
        
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
        	
        	 Page<WirelessSensorNetwork> page = wirelessSensorNetworkRepository.findAll(pageable);
             HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/wireless-sensor-networks");
             return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
        	
        }else {
        	
        	 Page<WirelessSensorNetwork> page = wirelessSensorNetworkRepository.findByAddedByIsCurrentUser(pageable);
             HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/wireless-sensor-networks");
             return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
        }
        
       
    }

    /**
     * GET  /wireless-sensor-networks/:id : get the "id" wirelessSensorNetwork.
     *
     * @param id the id of the wirelessSensorNetwork to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the wirelessSensorNetwork, or with status 404 (Not Found)
     */
    @GetMapping("/wireless-sensor-networks/{id}")
    @Timed
    public ResponseEntity<WirelessSensorNetwork> getWirelessSensorNetwork(@PathVariable Long id) {
        log.debug("REST request to get WirelessSensorNetwork : {}", id);
        WirelessSensorNetwork wirelessSensorNetwork = wirelessSensorNetworkRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(wirelessSensorNetwork));
    }

    /**
     * DELETE  /wireless-sensor-networks/:id : delete the "id" wirelessSensorNetwork.
     *
     * @param id the id of the wirelessSensorNetwork to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/wireless-sensor-networks/{id}")
    @Timed
    public ResponseEntity<Void> deleteWirelessSensorNetwork(@PathVariable Long id) {
        log.debug("REST request to delete WirelessSensorNetwork : {}", id);
        wirelessSensorNetworkRepository.delete(id);
        wirelessSensorNetworkSearchRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/wireless-sensor-networks?query=:query : search for the wirelessSensorNetwork corresponding
     * to the query.
     *
     * @param query the query of the wirelessSensorNetwork search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/wireless-sensor-networks")
    @Timed
    public ResponseEntity<List<WirelessSensorNetwork>> searchWirelessSensorNetworks(@RequestParam String query, @ApiParam Pageable pageable) {
        log.debug("REST request to search for a page of WirelessSensorNetworks for query {}", query);
        Page<WirelessSensorNetwork> page = wirelessSensorNetworkSearchRepository.search(queryStringQuery(query), pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/wireless-sensor-networks");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}
