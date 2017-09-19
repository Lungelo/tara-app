package com.tara.org.web.rest;

import com.tara.org.TaraApp;

import com.tara.org.domain.Sensor;
import com.tara.org.repository.SensorRepository;
import com.tara.org.repository.search.SensorSearchRepository;
import com.tara.org.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Base64Utils;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the SensorResource REST controller.
 *
 * @see SensorResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TaraApp.class)
public class SensorResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_TECHNOLOGY_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_TECHNOLOGY_TYPE = "BBBBBBBBBB";

    private static final Integer DEFAULT_TRL = 1;
    private static final Integer UPDATED_TRL = 2;

    private static final byte[] DEFAULT_PHOTO = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_PHOTO = TestUtil.createByteArray(2, "1");
    private static final String DEFAULT_PHOTO_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_PHOTO_CONTENT_TYPE = "image/png";

    private static final byte[] DEFAULT_DATASHEET = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_DATASHEET = TestUtil.createByteArray(2, "1");
    private static final String DEFAULT_DATASHEET_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_DATASHEET_CONTENT_TYPE = "image/png";

    private static final Float DEFAULT_SENSITIVITY = 1F;
    private static final Float UPDATED_SENSITIVITY = 2F;

    private static final Float DEFAULT_STABILITY = 1F;
    private static final Float UPDATED_STABILITY = 2F;

    private static final Float DEFAULT_ACCURACY = 1F;
    private static final Float UPDATED_ACCURACY = 2F;

    private static final Float DEFAULT_HYSTERESIS = 1F;
    private static final Float UPDATED_HYSTERESIS = 2F;

    private static final Float DEFAULT_DRIFT = 1F;
    private static final Float UPDATED_DRIFT = 2F;

    private static final BigDecimal DEFAULT_COST = new BigDecimal(1);
    private static final BigDecimal UPDATED_COST = new BigDecimal(2);

    private static final Float DEFAULT_SIZE = 1F;
    private static final Float UPDATED_SIZE = 2F;

    private static final Float DEFAULT_WEIGHT = 1F;
    private static final Float UPDATED_WEIGHT = 2F;

    private static final String DEFAULT_RANGE_SPAN = "AAAAAAAAAA";
    private static final String UPDATED_RANGE_SPAN = "BBBBBBBBBB";

    private static final Float DEFAULT_RESOLUTION = 1F;
    private static final Float UPDATED_RESOLUTION = 2F;

    private static final Float DEFAULT_LINEARITY = 1F;
    private static final Float UPDATED_LINEARITY = 2F;

    private static final Float DEFAULT_RESPONSE_TIME = 1F;
    private static final Float UPDATED_RESPONSE_TIME = 2F;

    private static final Float DEFAULT_PRECISION = 1F;
    private static final Float UPDATED_PRECISION = 2F;

    private static final Float DEFAULT_SIGNAL_TO_NOISE_RATIO = 1F;
    private static final Float UPDATED_SIGNAL_TO_NOISE_RATIO = 2F;

    private static final Float DEFAULT_TEMPERATURE = 1F;
    private static final Float UPDATED_TEMPERATURE = 2F;

    @Autowired
    private SensorRepository sensorRepository;

    @Autowired
    private SensorSearchRepository sensorSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restSensorMockMvc;

    private Sensor sensor;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SensorResource sensorResource = new SensorResource(sensorRepository, sensorSearchRepository);
        this.restSensorMockMvc = MockMvcBuilders.standaloneSetup(sensorResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Sensor createEntity(EntityManager em) {
        Sensor sensor = new Sensor()
            .name(DEFAULT_NAME)
            .technologyType(DEFAULT_TECHNOLOGY_TYPE)
            .trl(DEFAULT_TRL)
            .photo(DEFAULT_PHOTO)
            .photoContentType(DEFAULT_PHOTO_CONTENT_TYPE)
            .datasheet(DEFAULT_DATASHEET)
            .datasheetContentType(DEFAULT_DATASHEET_CONTENT_TYPE)
            .sensitivity(DEFAULT_SENSITIVITY)
            .stability(DEFAULT_STABILITY)
            .accuracy(DEFAULT_ACCURACY)
            .hysteresis(DEFAULT_HYSTERESIS)
            .drift(DEFAULT_DRIFT)
            .cost(DEFAULT_COST)
            .size(DEFAULT_SIZE)
            .weight(DEFAULT_WEIGHT)
            .rangeSpan(DEFAULT_RANGE_SPAN)
            .resolution(DEFAULT_RESOLUTION)
            .linearity(DEFAULT_LINEARITY)
            .responseTime(DEFAULT_RESPONSE_TIME)
            .precision(DEFAULT_PRECISION)
            .signalToNoiseRatio(DEFAULT_SIGNAL_TO_NOISE_RATIO)
            .temperature(DEFAULT_TEMPERATURE);
        return sensor;
    }

    @Before
    public void initTest() {
        sensorSearchRepository.deleteAll();
        sensor = createEntity(em);
    }

    @Test
    @Transactional
    public void createSensor() throws Exception {
        int databaseSizeBeforeCreate = sensorRepository.findAll().size();

        // Create the Sensor
        restSensorMockMvc.perform(post("/api/sensors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sensor)))
            .andExpect(status().isCreated());

        // Validate the Sensor in the database
        List<Sensor> sensorList = sensorRepository.findAll();
        assertThat(sensorList).hasSize(databaseSizeBeforeCreate + 1);
        Sensor testSensor = sensorList.get(sensorList.size() - 1);
        assertThat(testSensor.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testSensor.getTechnologyType()).isEqualTo(DEFAULT_TECHNOLOGY_TYPE);
        assertThat(testSensor.getTrl()).isEqualTo(DEFAULT_TRL);
        assertThat(testSensor.getPhoto()).isEqualTo(DEFAULT_PHOTO);
        assertThat(testSensor.getPhotoContentType()).isEqualTo(DEFAULT_PHOTO_CONTENT_TYPE);
        assertThat(testSensor.getDatasheet()).isEqualTo(DEFAULT_DATASHEET);
        assertThat(testSensor.getDatasheetContentType()).isEqualTo(DEFAULT_DATASHEET_CONTENT_TYPE);
        assertThat(testSensor.getSensitivity()).isEqualTo(DEFAULT_SENSITIVITY);
        assertThat(testSensor.getStability()).isEqualTo(DEFAULT_STABILITY);
        assertThat(testSensor.getAccuracy()).isEqualTo(DEFAULT_ACCURACY);
        assertThat(testSensor.getHysteresis()).isEqualTo(DEFAULT_HYSTERESIS);
        assertThat(testSensor.getDrift()).isEqualTo(DEFAULT_DRIFT);
        assertThat(testSensor.getCost()).isEqualTo(DEFAULT_COST);
        assertThat(testSensor.getSize()).isEqualTo(DEFAULT_SIZE);
        assertThat(testSensor.getWeight()).isEqualTo(DEFAULT_WEIGHT);
        assertThat(testSensor.getRangeSpan()).isEqualTo(DEFAULT_RANGE_SPAN);
        assertThat(testSensor.getResolution()).isEqualTo(DEFAULT_RESOLUTION);
        assertThat(testSensor.getLinearity()).isEqualTo(DEFAULT_LINEARITY);
        assertThat(testSensor.getResponseTime()).isEqualTo(DEFAULT_RESPONSE_TIME);
        assertThat(testSensor.getPrecision()).isEqualTo(DEFAULT_PRECISION);
        assertThat(testSensor.getSignalToNoiseRatio()).isEqualTo(DEFAULT_SIGNAL_TO_NOISE_RATIO);
        assertThat(testSensor.getTemperature()).isEqualTo(DEFAULT_TEMPERATURE);

        // Validate the Sensor in Elasticsearch
        Sensor sensorEs = sensorSearchRepository.findOne(testSensor.getId());
        assertThat(sensorEs).isEqualToComparingFieldByField(testSensor);
    }

    @Test
    @Transactional
    public void createSensorWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = sensorRepository.findAll().size();

        // Create the Sensor with an existing ID
        sensor.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSensorMockMvc.perform(post("/api/sensors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sensor)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Sensor> sensorList = sensorRepository.findAll();
        assertThat(sensorList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = sensorRepository.findAll().size();
        // set the field null
        sensor.setName(null);

        // Create the Sensor, which fails.

        restSensorMockMvc.perform(post("/api/sensors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sensor)))
            .andExpect(status().isBadRequest());

        List<Sensor> sensorList = sensorRepository.findAll();
        assertThat(sensorList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTechnologyTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = sensorRepository.findAll().size();
        // set the field null
        sensor.setTechnologyType(null);

        // Create the Sensor, which fails.

        restSensorMockMvc.perform(post("/api/sensors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sensor)))
            .andExpect(status().isBadRequest());

        List<Sensor> sensorList = sensorRepository.findAll();
        assertThat(sensorList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTrlIsRequired() throws Exception {
        int databaseSizeBeforeTest = sensorRepository.findAll().size();
        // set the field null
        sensor.setTrl(null);

        // Create the Sensor, which fails.

        restSensorMockMvc.perform(post("/api/sensors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sensor)))
            .andExpect(status().isBadRequest());

        List<Sensor> sensorList = sensorRepository.findAll();
        assertThat(sensorList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSensitivityIsRequired() throws Exception {
        int databaseSizeBeforeTest = sensorRepository.findAll().size();
        // set the field null
        sensor.setSensitivity(null);

        // Create the Sensor, which fails.

        restSensorMockMvc.perform(post("/api/sensors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sensor)))
            .andExpect(status().isBadRequest());

        List<Sensor> sensorList = sensorRepository.findAll();
        assertThat(sensorList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStabilityIsRequired() throws Exception {
        int databaseSizeBeforeTest = sensorRepository.findAll().size();
        // set the field null
        sensor.setStability(null);

        // Create the Sensor, which fails.

        restSensorMockMvc.perform(post("/api/sensors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sensor)))
            .andExpect(status().isBadRequest());

        List<Sensor> sensorList = sensorRepository.findAll();
        assertThat(sensorList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAccuracyIsRequired() throws Exception {
        int databaseSizeBeforeTest = sensorRepository.findAll().size();
        // set the field null
        sensor.setAccuracy(null);

        // Create the Sensor, which fails.

        restSensorMockMvc.perform(post("/api/sensors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sensor)))
            .andExpect(status().isBadRequest());

        List<Sensor> sensorList = sensorRepository.findAll();
        assertThat(sensorList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkHysteresisIsRequired() throws Exception {
        int databaseSizeBeforeTest = sensorRepository.findAll().size();
        // set the field null
        sensor.setHysteresis(null);

        // Create the Sensor, which fails.

        restSensorMockMvc.perform(post("/api/sensors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sensor)))
            .andExpect(status().isBadRequest());

        List<Sensor> sensorList = sensorRepository.findAll();
        assertThat(sensorList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDriftIsRequired() throws Exception {
        int databaseSizeBeforeTest = sensorRepository.findAll().size();
        // set the field null
        sensor.setDrift(null);

        // Create the Sensor, which fails.

        restSensorMockMvc.perform(post("/api/sensors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sensor)))
            .andExpect(status().isBadRequest());

        List<Sensor> sensorList = sensorRepository.findAll();
        assertThat(sensorList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCostIsRequired() throws Exception {
        int databaseSizeBeforeTest = sensorRepository.findAll().size();
        // set the field null
        sensor.setCost(null);

        // Create the Sensor, which fails.

        restSensorMockMvc.perform(post("/api/sensors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sensor)))
            .andExpect(status().isBadRequest());

        List<Sensor> sensorList = sensorRepository.findAll();
        assertThat(sensorList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSizeIsRequired() throws Exception {
        int databaseSizeBeforeTest = sensorRepository.findAll().size();
        // set the field null
        sensor.setSize(null);

        // Create the Sensor, which fails.

        restSensorMockMvc.perform(post("/api/sensors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sensor)))
            .andExpect(status().isBadRequest());

        List<Sensor> sensorList = sensorRepository.findAll();
        assertThat(sensorList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkWeightIsRequired() throws Exception {
        int databaseSizeBeforeTest = sensorRepository.findAll().size();
        // set the field null
        sensor.setWeight(null);

        // Create the Sensor, which fails.

        restSensorMockMvc.perform(post("/api/sensors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sensor)))
            .andExpect(status().isBadRequest());

        List<Sensor> sensorList = sensorRepository.findAll();
        assertThat(sensorList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkRangeSpanIsRequired() throws Exception {
        int databaseSizeBeforeTest = sensorRepository.findAll().size();
        // set the field null
        sensor.setRangeSpan(null);

        // Create the Sensor, which fails.

        restSensorMockMvc.perform(post("/api/sensors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sensor)))
            .andExpect(status().isBadRequest());

        List<Sensor> sensorList = sensorRepository.findAll();
        assertThat(sensorList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkResolutionIsRequired() throws Exception {
        int databaseSizeBeforeTest = sensorRepository.findAll().size();
        // set the field null
        sensor.setResolution(null);

        // Create the Sensor, which fails.

        restSensorMockMvc.perform(post("/api/sensors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sensor)))
            .andExpect(status().isBadRequest());

        List<Sensor> sensorList = sensorRepository.findAll();
        assertThat(sensorList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLinearityIsRequired() throws Exception {
        int databaseSizeBeforeTest = sensorRepository.findAll().size();
        // set the field null
        sensor.setLinearity(null);

        // Create the Sensor, which fails.

        restSensorMockMvc.perform(post("/api/sensors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sensor)))
            .andExpect(status().isBadRequest());

        List<Sensor> sensorList = sensorRepository.findAll();
        assertThat(sensorList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkResponseTimeIsRequired() throws Exception {
        int databaseSizeBeforeTest = sensorRepository.findAll().size();
        // set the field null
        sensor.setResponseTime(null);

        // Create the Sensor, which fails.

        restSensorMockMvc.perform(post("/api/sensors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sensor)))
            .andExpect(status().isBadRequest());

        List<Sensor> sensorList = sensorRepository.findAll();
        assertThat(sensorList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPrecisionIsRequired() throws Exception {
        int databaseSizeBeforeTest = sensorRepository.findAll().size();
        // set the field null
        sensor.setPrecision(null);

        // Create the Sensor, which fails.

        restSensorMockMvc.perform(post("/api/sensors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sensor)))
            .andExpect(status().isBadRequest());

        List<Sensor> sensorList = sensorRepository.findAll();
        assertThat(sensorList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSignalToNoiseRatioIsRequired() throws Exception {
        int databaseSizeBeforeTest = sensorRepository.findAll().size();
        // set the field null
        sensor.setSignalToNoiseRatio(null);

        // Create the Sensor, which fails.

        restSensorMockMvc.perform(post("/api/sensors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sensor)))
            .andExpect(status().isBadRequest());

        List<Sensor> sensorList = sensorRepository.findAll();
        assertThat(sensorList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTemperatureIsRequired() throws Exception {
        int databaseSizeBeforeTest = sensorRepository.findAll().size();
        // set the field null
        sensor.setTemperature(null);

        // Create the Sensor, which fails.

        restSensorMockMvc.perform(post("/api/sensors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sensor)))
            .andExpect(status().isBadRequest());

        List<Sensor> sensorList = sensorRepository.findAll();
        assertThat(sensorList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllSensors() throws Exception {
        // Initialize the database
        sensorRepository.saveAndFlush(sensor);

        // Get all the sensorList
        restSensorMockMvc.perform(get("/api/sensors?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sensor.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].technologyType").value(hasItem(DEFAULT_TECHNOLOGY_TYPE.toString())))
            .andExpect(jsonPath("$.[*].trl").value(hasItem(DEFAULT_TRL)))
            .andExpect(jsonPath("$.[*].photoContentType").value(hasItem(DEFAULT_PHOTO_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].photo").value(hasItem(Base64Utils.encodeToString(DEFAULT_PHOTO))))
            .andExpect(jsonPath("$.[*].datasheetContentType").value(hasItem(DEFAULT_DATASHEET_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].datasheet").value(hasItem(Base64Utils.encodeToString(DEFAULT_DATASHEET))))
            .andExpect(jsonPath("$.[*].sensitivity").value(hasItem(DEFAULT_SENSITIVITY.doubleValue())))
            .andExpect(jsonPath("$.[*].stability").value(hasItem(DEFAULT_STABILITY.doubleValue())))
            .andExpect(jsonPath("$.[*].accuracy").value(hasItem(DEFAULT_ACCURACY.doubleValue())))
            .andExpect(jsonPath("$.[*].hysteresis").value(hasItem(DEFAULT_HYSTERESIS.doubleValue())))
            .andExpect(jsonPath("$.[*].drift").value(hasItem(DEFAULT_DRIFT.doubleValue())))
            .andExpect(jsonPath("$.[*].cost").value(hasItem(DEFAULT_COST.intValue())))
            .andExpect(jsonPath("$.[*].size").value(hasItem(DEFAULT_SIZE.doubleValue())))
            .andExpect(jsonPath("$.[*].weight").value(hasItem(DEFAULT_WEIGHT.doubleValue())))
            .andExpect(jsonPath("$.[*].rangeSpan").value(hasItem(DEFAULT_RANGE_SPAN.toString())))
            .andExpect(jsonPath("$.[*].resolution").value(hasItem(DEFAULT_RESOLUTION.doubleValue())))
            .andExpect(jsonPath("$.[*].linearity").value(hasItem(DEFAULT_LINEARITY.doubleValue())))
            .andExpect(jsonPath("$.[*].responseTime").value(hasItem(DEFAULT_RESPONSE_TIME.doubleValue())))
            .andExpect(jsonPath("$.[*].precision").value(hasItem(DEFAULT_PRECISION.doubleValue())))
            .andExpect(jsonPath("$.[*].signalToNoiseRatio").value(hasItem(DEFAULT_SIGNAL_TO_NOISE_RATIO.doubleValue())))
            .andExpect(jsonPath("$.[*].temperature").value(hasItem(DEFAULT_TEMPERATURE.doubleValue())));
    }

    @Test
    @Transactional
    public void getSensor() throws Exception {
        // Initialize the database
        sensorRepository.saveAndFlush(sensor);

        // Get the sensor
        restSensorMockMvc.perform(get("/api/sensors/{id}", sensor.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(sensor.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.technologyType").value(DEFAULT_TECHNOLOGY_TYPE.toString()))
            .andExpect(jsonPath("$.trl").value(DEFAULT_TRL))
            .andExpect(jsonPath("$.photoContentType").value(DEFAULT_PHOTO_CONTENT_TYPE))
            .andExpect(jsonPath("$.photo").value(Base64Utils.encodeToString(DEFAULT_PHOTO)))
            .andExpect(jsonPath("$.datasheetContentType").value(DEFAULT_DATASHEET_CONTENT_TYPE))
            .andExpect(jsonPath("$.datasheet").value(Base64Utils.encodeToString(DEFAULT_DATASHEET)))
            .andExpect(jsonPath("$.sensitivity").value(DEFAULT_SENSITIVITY.doubleValue()))
            .andExpect(jsonPath("$.stability").value(DEFAULT_STABILITY.doubleValue()))
            .andExpect(jsonPath("$.accuracy").value(DEFAULT_ACCURACY.doubleValue()))
            .andExpect(jsonPath("$.hysteresis").value(DEFAULT_HYSTERESIS.doubleValue()))
            .andExpect(jsonPath("$.drift").value(DEFAULT_DRIFT.doubleValue()))
            .andExpect(jsonPath("$.cost").value(DEFAULT_COST.intValue()))
            .andExpect(jsonPath("$.size").value(DEFAULT_SIZE.doubleValue()))
            .andExpect(jsonPath("$.weight").value(DEFAULT_WEIGHT.doubleValue()))
            .andExpect(jsonPath("$.rangeSpan").value(DEFAULT_RANGE_SPAN.toString()))
            .andExpect(jsonPath("$.resolution").value(DEFAULT_RESOLUTION.doubleValue()))
            .andExpect(jsonPath("$.linearity").value(DEFAULT_LINEARITY.doubleValue()))
            .andExpect(jsonPath("$.responseTime").value(DEFAULT_RESPONSE_TIME.doubleValue()))
            .andExpect(jsonPath("$.precision").value(DEFAULT_PRECISION.doubleValue()))
            .andExpect(jsonPath("$.signalToNoiseRatio").value(DEFAULT_SIGNAL_TO_NOISE_RATIO.doubleValue()))
            .andExpect(jsonPath("$.temperature").value(DEFAULT_TEMPERATURE.doubleValue()));
    }

    @Test
    @Transactional
    public void getNonExistingSensor() throws Exception {
        // Get the sensor
        restSensorMockMvc.perform(get("/api/sensors/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSensor() throws Exception {
        // Initialize the database
        sensorRepository.saveAndFlush(sensor);
        sensorSearchRepository.save(sensor);
        int databaseSizeBeforeUpdate = sensorRepository.findAll().size();

        // Update the sensor
        Sensor updatedSensor = sensorRepository.findOne(sensor.getId());
        updatedSensor
            .name(UPDATED_NAME)
            .technologyType(UPDATED_TECHNOLOGY_TYPE)
            .trl(UPDATED_TRL)
            .photo(UPDATED_PHOTO)
            .photoContentType(UPDATED_PHOTO_CONTENT_TYPE)
            .datasheet(UPDATED_DATASHEET)
            .datasheetContentType(UPDATED_DATASHEET_CONTENT_TYPE)
            .sensitivity(UPDATED_SENSITIVITY)
            .stability(UPDATED_STABILITY)
            .accuracy(UPDATED_ACCURACY)
            .hysteresis(UPDATED_HYSTERESIS)
            .drift(UPDATED_DRIFT)
            .cost(UPDATED_COST)
            .size(UPDATED_SIZE)
            .weight(UPDATED_WEIGHT)
            .rangeSpan(UPDATED_RANGE_SPAN)
            .resolution(UPDATED_RESOLUTION)
            .linearity(UPDATED_LINEARITY)
            .responseTime(UPDATED_RESPONSE_TIME)
            .precision(UPDATED_PRECISION)
            .signalToNoiseRatio(UPDATED_SIGNAL_TO_NOISE_RATIO)
            .temperature(UPDATED_TEMPERATURE);

        restSensorMockMvc.perform(put("/api/sensors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedSensor)))
            .andExpect(status().isOk());

        // Validate the Sensor in the database
        List<Sensor> sensorList = sensorRepository.findAll();
        assertThat(sensorList).hasSize(databaseSizeBeforeUpdate);
        Sensor testSensor = sensorList.get(sensorList.size() - 1);
        assertThat(testSensor.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testSensor.getTechnologyType()).isEqualTo(UPDATED_TECHNOLOGY_TYPE);
        assertThat(testSensor.getTrl()).isEqualTo(UPDATED_TRL);
        assertThat(testSensor.getPhoto()).isEqualTo(UPDATED_PHOTO);
        assertThat(testSensor.getPhotoContentType()).isEqualTo(UPDATED_PHOTO_CONTENT_TYPE);
        assertThat(testSensor.getDatasheet()).isEqualTo(UPDATED_DATASHEET);
        assertThat(testSensor.getDatasheetContentType()).isEqualTo(UPDATED_DATASHEET_CONTENT_TYPE);
        assertThat(testSensor.getSensitivity()).isEqualTo(UPDATED_SENSITIVITY);
        assertThat(testSensor.getStability()).isEqualTo(UPDATED_STABILITY);
        assertThat(testSensor.getAccuracy()).isEqualTo(UPDATED_ACCURACY);
        assertThat(testSensor.getHysteresis()).isEqualTo(UPDATED_HYSTERESIS);
        assertThat(testSensor.getDrift()).isEqualTo(UPDATED_DRIFT);
        assertThat(testSensor.getCost()).isEqualTo(UPDATED_COST);
        assertThat(testSensor.getSize()).isEqualTo(UPDATED_SIZE);
        assertThat(testSensor.getWeight()).isEqualTo(UPDATED_WEIGHT);
        assertThat(testSensor.getRangeSpan()).isEqualTo(UPDATED_RANGE_SPAN);
        assertThat(testSensor.getResolution()).isEqualTo(UPDATED_RESOLUTION);
        assertThat(testSensor.getLinearity()).isEqualTo(UPDATED_LINEARITY);
        assertThat(testSensor.getResponseTime()).isEqualTo(UPDATED_RESPONSE_TIME);
        assertThat(testSensor.getPrecision()).isEqualTo(UPDATED_PRECISION);
        assertThat(testSensor.getSignalToNoiseRatio()).isEqualTo(UPDATED_SIGNAL_TO_NOISE_RATIO);
        assertThat(testSensor.getTemperature()).isEqualTo(UPDATED_TEMPERATURE);

        // Validate the Sensor in Elasticsearch
        Sensor sensorEs = sensorSearchRepository.findOne(testSensor.getId());
        assertThat(sensorEs).isEqualToComparingFieldByField(testSensor);
    }

    @Test
    @Transactional
    public void updateNonExistingSensor() throws Exception {
        int databaseSizeBeforeUpdate = sensorRepository.findAll().size();

        // Create the Sensor

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restSensorMockMvc.perform(put("/api/sensors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sensor)))
            .andExpect(status().isCreated());

        // Validate the Sensor in the database
        List<Sensor> sensorList = sensorRepository.findAll();
        assertThat(sensorList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteSensor() throws Exception {
        // Initialize the database
        sensorRepository.saveAndFlush(sensor);
        sensorSearchRepository.save(sensor);
        int databaseSizeBeforeDelete = sensorRepository.findAll().size();

        // Get the sensor
        restSensorMockMvc.perform(delete("/api/sensors/{id}", sensor.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate Elasticsearch is empty
        boolean sensorExistsInEs = sensorSearchRepository.exists(sensor.getId());
        assertThat(sensorExistsInEs).isFalse();

        // Validate the database is empty
        List<Sensor> sensorList = sensorRepository.findAll();
        assertThat(sensorList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void searchSensor() throws Exception {
        // Initialize the database
        sensorRepository.saveAndFlush(sensor);
        sensorSearchRepository.save(sensor);

        // Search the sensor
        restSensorMockMvc.perform(get("/api/_search/sensors?query=id:" + sensor.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sensor.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].technologyType").value(hasItem(DEFAULT_TECHNOLOGY_TYPE.toString())))
            .andExpect(jsonPath("$.[*].trl").value(hasItem(DEFAULT_TRL)))
            .andExpect(jsonPath("$.[*].photoContentType").value(hasItem(DEFAULT_PHOTO_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].photo").value(hasItem(Base64Utils.encodeToString(DEFAULT_PHOTO))))
            .andExpect(jsonPath("$.[*].datasheetContentType").value(hasItem(DEFAULT_DATASHEET_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].datasheet").value(hasItem(Base64Utils.encodeToString(DEFAULT_DATASHEET))))
            .andExpect(jsonPath("$.[*].sensitivity").value(hasItem(DEFAULT_SENSITIVITY.doubleValue())))
            .andExpect(jsonPath("$.[*].stability").value(hasItem(DEFAULT_STABILITY.doubleValue())))
            .andExpect(jsonPath("$.[*].accuracy").value(hasItem(DEFAULT_ACCURACY.doubleValue())))
            .andExpect(jsonPath("$.[*].hysteresis").value(hasItem(DEFAULT_HYSTERESIS.doubleValue())))
            .andExpect(jsonPath("$.[*].drift").value(hasItem(DEFAULT_DRIFT.doubleValue())))
            .andExpect(jsonPath("$.[*].cost").value(hasItem(DEFAULT_COST.intValue())))
            .andExpect(jsonPath("$.[*].size").value(hasItem(DEFAULT_SIZE.doubleValue())))
            .andExpect(jsonPath("$.[*].weight").value(hasItem(DEFAULT_WEIGHT.doubleValue())))
            .andExpect(jsonPath("$.[*].rangeSpan").value(hasItem(DEFAULT_RANGE_SPAN.toString())))
            .andExpect(jsonPath("$.[*].resolution").value(hasItem(DEFAULT_RESOLUTION.doubleValue())))
            .andExpect(jsonPath("$.[*].linearity").value(hasItem(DEFAULT_LINEARITY.doubleValue())))
            .andExpect(jsonPath("$.[*].responseTime").value(hasItem(DEFAULT_RESPONSE_TIME.doubleValue())))
            .andExpect(jsonPath("$.[*].precision").value(hasItem(DEFAULT_PRECISION.doubleValue())))
            .andExpect(jsonPath("$.[*].signalToNoiseRatio").value(hasItem(DEFAULT_SIGNAL_TO_NOISE_RATIO.doubleValue())))
            .andExpect(jsonPath("$.[*].temperature").value(hasItem(DEFAULT_TEMPERATURE.doubleValue())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Sensor.class);
        Sensor sensor1 = new Sensor();
        sensor1.setId(1L);
        Sensor sensor2 = new Sensor();
        sensor2.setId(sensor1.getId());
        assertThat(sensor1).isEqualTo(sensor2);
        sensor2.setId(2L);
        assertThat(sensor1).isNotEqualTo(sensor2);
        sensor1.setId(null);
        assertThat(sensor1).isNotEqualTo(sensor2);
    }
}
