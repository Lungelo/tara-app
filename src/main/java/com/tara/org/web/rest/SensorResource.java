package com.tara.org.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.tara.org.domain.Authority;
import com.tara.org.domain.Company;
import com.tara.org.domain.Sensor;
import com.tara.org.domain.User;
import com.tara.org.domain.UserExtra;

import javax.inject.Inject;

import com.tara.org.repository.SensorRepository;
import com.tara.org.repository.UserExtraRepository;
import com.tara.org.repository.search.SensorSearchRepository;
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
 * REST controller for managing Sensor.
 */
@RestController
@RequestMapping("/api")
public class SensorResource {

	private final Logger log = LoggerFactory.getLogger(SensorResource.class);

	private static final String ENTITY_NAME = "sensor";

	private final SensorRepository sensorRepository;

	private final SensorSearchRepository sensorSearchRepository;

	@Inject
	private UserService userService;

	@Inject
	private UserExtraRepository userExtraRepository;

	public SensorResource(SensorRepository sensorRepository, SensorSearchRepository sensorSearchRepository) {
		this.sensorRepository = sensorRepository;
		this.sensorSearchRepository = sensorSearchRepository;
	}

	/**
	 * POST /sensors : Create a new sensor.
	 *
	 * @param sensor
	 *            the sensor to create
	 * @return the ResponseEntity with status 201 (Created) and with body the new
	 *         sensor, or with status 400 (Bad Request) if the sensor has already an
	 *         ID
	 * @throws URISyntaxException
	 *             if the Location URI syntax is incorrect
	 */
	@PostMapping("/sensors")
	@Timed
	public ResponseEntity<Sensor> createSensor(@Valid @RequestBody Sensor sensor) throws URISyntaxException {
		log.debug("REST request to save Sensor : {}", sensor);
		if (sensor.getId() != null) {
			return ResponseEntity.badRequest().headers(
					HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new sensor cannot already have an ID"))
					.body(null);
		}
		User user = userService.getUserWithAuthorities();
		UserExtra userExtra = userExtraRepository.findByUser(user);
		Company company = userExtra.getCompany();
		sensor.setAddedBy(user);
		sensor.setOemName(company);
		Sensor result = sensorRepository.save(sensor);
		sensorSearchRepository.save(result);
		return ResponseEntity.created(new URI("/api/sensors/" + result.getId()))
				.headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString())).body(result);
	}

	/**
	 * PUT /sensors : Updates an existing sensor.
	 *
	 * @param sensor
	 *            the sensor to update
	 * @return the ResponseEntity with status 200 (OK) and with body the updated
	 *         sensor, or with status 400 (Bad Request) if the sensor is not valid,
	 *         or with status 500 (Internal Server Error) if the sensor couldn't be
	 *         updated
	 * @throws URISyntaxException
	 *             if the Location URI syntax is incorrect
	 */
	@PutMapping("/sensors")
	@Timed
	public ResponseEntity<Sensor> updateSensor(@Valid @RequestBody Sensor sensor) throws URISyntaxException {
		log.debug("REST request to update Sensor : {}", sensor);
		if (sensor.getId() == null) {
			return createSensor(sensor);
		}
		Sensor result = sensorRepository.save(sensor);
		sensorSearchRepository.save(result);
		return ResponseEntity.ok().headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, sensor.getId().toString()))
				.body(result);
	}

	/**
	 * GET /sensors : get all the sensors.
	 *
	 * @param pageable
	 *            the pagination information
	 * @return the ResponseEntity with status 200 (OK) and the list of sensors in
	 *         body
	 */
	@GetMapping("/sensors")
	@Timed
	public ResponseEntity<List<Sensor>> getAllSensors(@ApiParam Pageable pageable) {
		log.debug("REST request to get a page of Sensors");

		User user = userService.getUserWithAuthorities();
		Set<Authority> authorities = user.getAuthorities();
		Authority authority = new Authority();
		String Role = "GENERAL_USER";
		authority.setName(Role);

		Set<Authority> authorities2 = new HashSet<>();
		authorities2.add(authority);

		// check the existence of element
		boolean exist = authorities.contains(authority);

		if (exist == true) {

			Page<Sensor> page = sensorRepository.findAll(pageable);
			HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/sensors");
			return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
		} else {

			Page<Sensor> page = sensorRepository.findByAddedByIsCurrentUser(pageable);
			HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/sensors");
			return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
		}

	}

	/**
	 * GET /sensors/:id : get the "id" sensor.
	 *
	 * @param id
	 *            the id of the sensor to retrieve
	 * @return the ResponseEntity with status 200 (OK) and with body the sensor, or
	 *         with status 404 (Not Found)
	 */
	@GetMapping("/sensors/{id}")
	@Timed
	public ResponseEntity<Sensor> getSensor(@PathVariable Long id) {
		log.debug("REST request to get Sensor : {}", id);
		Sensor sensor = sensorRepository.findOne(id);
		return ResponseUtil.wrapOrNotFound(Optional.ofNullable(sensor));
	}

	/**
	 * DELETE /sensors/:id : delete the "id" sensor.
	 *
	 * @param id
	 *            the id of the sensor to delete
	 * @return the ResponseEntity with status 200 (OK)
	 */
	@DeleteMapping("/sensors/{id}")
	@Timed
	public ResponseEntity<Void> deleteSensor(@PathVariable Long id) {
		log.debug("REST request to delete Sensor : {}", id);
		sensorRepository.delete(id);
		sensorSearchRepository.delete(id);
		return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
	}

	/**
	 * SEARCH /_search/sensors?query=:query : search for the sensor corresponding to
	 * the query.
	 *
	 * @param query
	 *            the query of the sensor search
	 * @param pageable
	 *            the pagination information
	 * @return the result of the search
	 */
	@GetMapping("/_search/sensors")
	@Timed
	public ResponseEntity<List<Sensor>> searchSensors(@RequestParam String query, @ApiParam Pageable pageable) {
		log.debug("REST request to search for a page of Sensors for query {}", query);
		Page<Sensor> page = sensorSearchRepository.search(queryStringQuery(query), pageable);
		HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/sensors");
		return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
	}

}
