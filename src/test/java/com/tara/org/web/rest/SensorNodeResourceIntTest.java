package com.tara.org.web.rest;

import com.tara.org.TaraApp;

import com.tara.org.domain.SensorNode;
import com.tara.org.repository.SensorNodeRepository;
import com.tara.org.repository.search.SensorNodeSearchRepository;
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

import com.tara.org.domain.enumeration.Choice;
import com.tara.org.domain.enumeration.Choice;
import com.tara.org.domain.enumeration.Choice;
import com.tara.org.domain.enumeration.Choice;
/**
 * Test class for the SensorNodeResource REST controller.
 *
 * @see SensorNodeResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TaraApp.class)
public class SensorNodeResourceIntTest {

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

    private static final Float DEFAULT_SIZE = 1F;
    private static final Float UPDATED_SIZE = 2F;

    private static final BigDecimal DEFAULT_COST = new BigDecimal(1);
    private static final BigDecimal UPDATED_COST = new BigDecimal(2);

    private static final Float DEFAULT_BANDWIDTH = 1F;
    private static final Float UPDATED_BANDWIDTH = 2F;

    private static final Float DEFAULT_DATA_RATE = 1F;
    private static final Float UPDATED_DATA_RATE = 2F;

    private static final Float DEFAULT_FLASH_MEMORY = 1F;
    private static final Float UPDATED_FLASH_MEMORY = 2F;

    private static final String DEFAULT_RAM = "AAAAAAAAAA";
    private static final String UPDATED_RAM = "BBBBBBBBBB";

    private static final Float DEFAULT_ENERGY_USAGE = 1F;
    private static final Float UPDATED_ENERGY_USAGE = 2F;

    private static final Float DEFAULT_SLEEP_ENERGY = 1F;
    private static final Float UPDATED_SLEEP_ENERGY = 2F;

    private static final String DEFAULT_DUTY_CYCLE = "AAAAAAAAAA";
    private static final String UPDATED_DUTY_CYCLE = "BBBBBBBBBB";

    private static final Float DEFAULT_FREQUENCY = 1F;
    private static final Float UPDATED_FREQUENCY = 2F;

    private static final String DEFAULT_RANGE = "AAAAAAAAAA";
    private static final String UPDATED_RANGE = "BBBBBBBBBB";

    private static final Choice DEFAULT_MOBILITY = Choice.YES;
    private static final Choice UPDATED_MOBILITY = Choice.NO;

    private static final Float DEFAULT_RESILIENCE = 1F;
    private static final Float UPDATED_RESILIENCE = 2F;

    private static final Choice DEFAULT_SELF_TESTING = Choice.YES;
    private static final Choice UPDATED_SELF_TESTING = Choice.NO;

    private static final Choice DEFAULT_SELF_CALIBRATION = Choice.YES;
    private static final Choice UPDATED_SELF_CALIBRATION = Choice.NO;

    private static final Choice DEFAULT_SELF_REPAIR = Choice.YES;
    private static final Choice UPDATED_SELF_REPAIR = Choice.NO;

    private static final Float DEFAULT_TRANSMISSION_POWER = 1F;
    private static final Float UPDATED_TRANSMISSION_POWER = 2F;

    @Autowired
    private SensorNodeRepository sensorNodeRepository;

    @Autowired
    private SensorNodeSearchRepository sensorNodeSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restSensorNodeMockMvc;

    private SensorNode sensorNode;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SensorNodeResource sensorNodeResource = new SensorNodeResource(sensorNodeRepository, sensorNodeSearchRepository);
        this.restSensorNodeMockMvc = MockMvcBuilders.standaloneSetup(sensorNodeResource)
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
    public static SensorNode createEntity(EntityManager em) {
        SensorNode sensorNode = new SensorNode()
            .name(DEFAULT_NAME)
            .technologyType(DEFAULT_TECHNOLOGY_TYPE)
            .trl(DEFAULT_TRL)
            .photo(DEFAULT_PHOTO)
            .photoContentType(DEFAULT_PHOTO_CONTENT_TYPE)
            .datasheet(DEFAULT_DATASHEET)
            .datasheetContentType(DEFAULT_DATASHEET_CONTENT_TYPE)
            .size(DEFAULT_SIZE)
            .cost(DEFAULT_COST)
            .bandwidth(DEFAULT_BANDWIDTH)
            .dataRate(DEFAULT_DATA_RATE)
            .flashMemory(DEFAULT_FLASH_MEMORY)
            .ram(DEFAULT_RAM)
            .energyUsage(DEFAULT_ENERGY_USAGE)
            .sleepEnergy(DEFAULT_SLEEP_ENERGY)
            .dutyCycle(DEFAULT_DUTY_CYCLE)
            .frequency(DEFAULT_FREQUENCY)
            .range(DEFAULT_RANGE)
            .mobility(DEFAULT_MOBILITY)
            .resilience(DEFAULT_RESILIENCE)
            .selfTesting(DEFAULT_SELF_TESTING)
            .selfCalibration(DEFAULT_SELF_CALIBRATION)
            .selfRepair(DEFAULT_SELF_REPAIR)
            .transmissionPower(DEFAULT_TRANSMISSION_POWER);
        return sensorNode;
    }

    @Before
    public void initTest() {
        sensorNodeSearchRepository.deleteAll();
        sensorNode = createEntity(em);
    }

    @Test
    @Transactional
    public void createSensorNode() throws Exception {
        int databaseSizeBeforeCreate = sensorNodeRepository.findAll().size();

        // Create the SensorNode
        restSensorNodeMockMvc.perform(post("/api/sensor-nodes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sensorNode)))
            .andExpect(status().isCreated());

        // Validate the SensorNode in the database
        List<SensorNode> sensorNodeList = sensorNodeRepository.findAll();
        assertThat(sensorNodeList).hasSize(databaseSizeBeforeCreate + 1);
        SensorNode testSensorNode = sensorNodeList.get(sensorNodeList.size() - 1);
        assertThat(testSensorNode.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testSensorNode.getTechnologyType()).isEqualTo(DEFAULT_TECHNOLOGY_TYPE);
        assertThat(testSensorNode.getTrl()).isEqualTo(DEFAULT_TRL);
        assertThat(testSensorNode.getPhoto()).isEqualTo(DEFAULT_PHOTO);
        assertThat(testSensorNode.getPhotoContentType()).isEqualTo(DEFAULT_PHOTO_CONTENT_TYPE);
        assertThat(testSensorNode.getDatasheet()).isEqualTo(DEFAULT_DATASHEET);
        assertThat(testSensorNode.getDatasheetContentType()).isEqualTo(DEFAULT_DATASHEET_CONTENT_TYPE);
        assertThat(testSensorNode.getSize()).isEqualTo(DEFAULT_SIZE);
        assertThat(testSensorNode.getCost()).isEqualTo(DEFAULT_COST);
        assertThat(testSensorNode.getBandwidth()).isEqualTo(DEFAULT_BANDWIDTH);
        assertThat(testSensorNode.getDataRate()).isEqualTo(DEFAULT_DATA_RATE);
        assertThat(testSensorNode.getFlashMemory()).isEqualTo(DEFAULT_FLASH_MEMORY);
        assertThat(testSensorNode.getRam()).isEqualTo(DEFAULT_RAM);
        assertThat(testSensorNode.getEnergyUsage()).isEqualTo(DEFAULT_ENERGY_USAGE);
        assertThat(testSensorNode.getSleepEnergy()).isEqualTo(DEFAULT_SLEEP_ENERGY);
        assertThat(testSensorNode.getDutyCycle()).isEqualTo(DEFAULT_DUTY_CYCLE);
        assertThat(testSensorNode.getFrequency()).isEqualTo(DEFAULT_FREQUENCY);
        assertThat(testSensorNode.getRange()).isEqualTo(DEFAULT_RANGE);
        assertThat(testSensorNode.getMobility()).isEqualTo(DEFAULT_MOBILITY);
        assertThat(testSensorNode.getResilience()).isEqualTo(DEFAULT_RESILIENCE);
        assertThat(testSensorNode.getSelfTesting()).isEqualTo(DEFAULT_SELF_TESTING);
        assertThat(testSensorNode.getSelfCalibration()).isEqualTo(DEFAULT_SELF_CALIBRATION);
        assertThat(testSensorNode.getSelfRepair()).isEqualTo(DEFAULT_SELF_REPAIR);
        assertThat(testSensorNode.getTransmissionPower()).isEqualTo(DEFAULT_TRANSMISSION_POWER);

        // Validate the SensorNode in Elasticsearch
        SensorNode sensorNodeEs = sensorNodeSearchRepository.findOne(testSensorNode.getId());
        assertThat(sensorNodeEs).isEqualToComparingFieldByField(testSensorNode);
    }

    @Test
    @Transactional
    public void createSensorNodeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = sensorNodeRepository.findAll().size();

        // Create the SensorNode with an existing ID
        sensorNode.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSensorNodeMockMvc.perform(post("/api/sensor-nodes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sensorNode)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<SensorNode> sensorNodeList = sensorNodeRepository.findAll();
        assertThat(sensorNodeList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = sensorNodeRepository.findAll().size();
        // set the field null
        sensorNode.setName(null);

        // Create the SensorNode, which fails.

        restSensorNodeMockMvc.perform(post("/api/sensor-nodes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sensorNode)))
            .andExpect(status().isBadRequest());

        List<SensorNode> sensorNodeList = sensorNodeRepository.findAll();
        assertThat(sensorNodeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTechnologyTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = sensorNodeRepository.findAll().size();
        // set the field null
        sensorNode.setTechnologyType(null);

        // Create the SensorNode, which fails.

        restSensorNodeMockMvc.perform(post("/api/sensor-nodes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sensorNode)))
            .andExpect(status().isBadRequest());

        List<SensorNode> sensorNodeList = sensorNodeRepository.findAll();
        assertThat(sensorNodeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTrlIsRequired() throws Exception {
        int databaseSizeBeforeTest = sensorNodeRepository.findAll().size();
        // set the field null
        sensorNode.setTrl(null);

        // Create the SensorNode, which fails.

        restSensorNodeMockMvc.perform(post("/api/sensor-nodes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sensorNode)))
            .andExpect(status().isBadRequest());

        List<SensorNode> sensorNodeList = sensorNodeRepository.findAll();
        assertThat(sensorNodeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSizeIsRequired() throws Exception {
        int databaseSizeBeforeTest = sensorNodeRepository.findAll().size();
        // set the field null
        sensorNode.setSize(null);

        // Create the SensorNode, which fails.

        restSensorNodeMockMvc.perform(post("/api/sensor-nodes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sensorNode)))
            .andExpect(status().isBadRequest());

        List<SensorNode> sensorNodeList = sensorNodeRepository.findAll();
        assertThat(sensorNodeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCostIsRequired() throws Exception {
        int databaseSizeBeforeTest = sensorNodeRepository.findAll().size();
        // set the field null
        sensorNode.setCost(null);

        // Create the SensorNode, which fails.

        restSensorNodeMockMvc.perform(post("/api/sensor-nodes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sensorNode)))
            .andExpect(status().isBadRequest());

        List<SensorNode> sensorNodeList = sensorNodeRepository.findAll();
        assertThat(sensorNodeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkBandwidthIsRequired() throws Exception {
        int databaseSizeBeforeTest = sensorNodeRepository.findAll().size();
        // set the field null
        sensorNode.setBandwidth(null);

        // Create the SensorNode, which fails.

        restSensorNodeMockMvc.perform(post("/api/sensor-nodes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sensorNode)))
            .andExpect(status().isBadRequest());

        List<SensorNode> sensorNodeList = sensorNodeRepository.findAll();
        assertThat(sensorNodeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDataRateIsRequired() throws Exception {
        int databaseSizeBeforeTest = sensorNodeRepository.findAll().size();
        // set the field null
        sensorNode.setDataRate(null);

        // Create the SensorNode, which fails.

        restSensorNodeMockMvc.perform(post("/api/sensor-nodes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sensorNode)))
            .andExpect(status().isBadRequest());

        List<SensorNode> sensorNodeList = sensorNodeRepository.findAll();
        assertThat(sensorNodeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFlashMemoryIsRequired() throws Exception {
        int databaseSizeBeforeTest = sensorNodeRepository.findAll().size();
        // set the field null
        sensorNode.setFlashMemory(null);

        // Create the SensorNode, which fails.

        restSensorNodeMockMvc.perform(post("/api/sensor-nodes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sensorNode)))
            .andExpect(status().isBadRequest());

        List<SensorNode> sensorNodeList = sensorNodeRepository.findAll();
        assertThat(sensorNodeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkRamIsRequired() throws Exception {
        int databaseSizeBeforeTest = sensorNodeRepository.findAll().size();
        // set the field null
        sensorNode.setRam(null);

        // Create the SensorNode, which fails.

        restSensorNodeMockMvc.perform(post("/api/sensor-nodes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sensorNode)))
            .andExpect(status().isBadRequest());

        List<SensorNode> sensorNodeList = sensorNodeRepository.findAll();
        assertThat(sensorNodeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEnergyUsageIsRequired() throws Exception {
        int databaseSizeBeforeTest = sensorNodeRepository.findAll().size();
        // set the field null
        sensorNode.setEnergyUsage(null);

        // Create the SensorNode, which fails.

        restSensorNodeMockMvc.perform(post("/api/sensor-nodes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sensorNode)))
            .andExpect(status().isBadRequest());

        List<SensorNode> sensorNodeList = sensorNodeRepository.findAll();
        assertThat(sensorNodeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSleepEnergyIsRequired() throws Exception {
        int databaseSizeBeforeTest = sensorNodeRepository.findAll().size();
        // set the field null
        sensorNode.setSleepEnergy(null);

        // Create the SensorNode, which fails.

        restSensorNodeMockMvc.perform(post("/api/sensor-nodes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sensorNode)))
            .andExpect(status().isBadRequest());

        List<SensorNode> sensorNodeList = sensorNodeRepository.findAll();
        assertThat(sensorNodeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDutyCycleIsRequired() throws Exception {
        int databaseSizeBeforeTest = sensorNodeRepository.findAll().size();
        // set the field null
        sensorNode.setDutyCycle(null);

        // Create the SensorNode, which fails.

        restSensorNodeMockMvc.perform(post("/api/sensor-nodes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sensorNode)))
            .andExpect(status().isBadRequest());

        List<SensorNode> sensorNodeList = sensorNodeRepository.findAll();
        assertThat(sensorNodeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFrequencyIsRequired() throws Exception {
        int databaseSizeBeforeTest = sensorNodeRepository.findAll().size();
        // set the field null
        sensorNode.setFrequency(null);

        // Create the SensorNode, which fails.

        restSensorNodeMockMvc.perform(post("/api/sensor-nodes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sensorNode)))
            .andExpect(status().isBadRequest());

        List<SensorNode> sensorNodeList = sensorNodeRepository.findAll();
        assertThat(sensorNodeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkRangeIsRequired() throws Exception {
        int databaseSizeBeforeTest = sensorNodeRepository.findAll().size();
        // set the field null
        sensorNode.setRange(null);

        // Create the SensorNode, which fails.

        restSensorNodeMockMvc.perform(post("/api/sensor-nodes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sensorNode)))
            .andExpect(status().isBadRequest());

        List<SensorNode> sensorNodeList = sensorNodeRepository.findAll();
        assertThat(sensorNodeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMobilityIsRequired() throws Exception {
        int databaseSizeBeforeTest = sensorNodeRepository.findAll().size();
        // set the field null
        sensorNode.setMobility(null);

        // Create the SensorNode, which fails.

        restSensorNodeMockMvc.perform(post("/api/sensor-nodes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sensorNode)))
            .andExpect(status().isBadRequest());

        List<SensorNode> sensorNodeList = sensorNodeRepository.findAll();
        assertThat(sensorNodeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkResilienceIsRequired() throws Exception {
        int databaseSizeBeforeTest = sensorNodeRepository.findAll().size();
        // set the field null
        sensorNode.setResilience(null);

        // Create the SensorNode, which fails.

        restSensorNodeMockMvc.perform(post("/api/sensor-nodes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sensorNode)))
            .andExpect(status().isBadRequest());

        List<SensorNode> sensorNodeList = sensorNodeRepository.findAll();
        assertThat(sensorNodeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSelfTestingIsRequired() throws Exception {
        int databaseSizeBeforeTest = sensorNodeRepository.findAll().size();
        // set the field null
        sensorNode.setSelfTesting(null);

        // Create the SensorNode, which fails.

        restSensorNodeMockMvc.perform(post("/api/sensor-nodes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sensorNode)))
            .andExpect(status().isBadRequest());

        List<SensorNode> sensorNodeList = sensorNodeRepository.findAll();
        assertThat(sensorNodeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSelfCalibrationIsRequired() throws Exception {
        int databaseSizeBeforeTest = sensorNodeRepository.findAll().size();
        // set the field null
        sensorNode.setSelfCalibration(null);

        // Create the SensorNode, which fails.

        restSensorNodeMockMvc.perform(post("/api/sensor-nodes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sensorNode)))
            .andExpect(status().isBadRequest());

        List<SensorNode> sensorNodeList = sensorNodeRepository.findAll();
        assertThat(sensorNodeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSelfRepairIsRequired() throws Exception {
        int databaseSizeBeforeTest = sensorNodeRepository.findAll().size();
        // set the field null
        sensorNode.setSelfRepair(null);

        // Create the SensorNode, which fails.

        restSensorNodeMockMvc.perform(post("/api/sensor-nodes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sensorNode)))
            .andExpect(status().isBadRequest());

        List<SensorNode> sensorNodeList = sensorNodeRepository.findAll();
        assertThat(sensorNodeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllSensorNodes() throws Exception {
        // Initialize the database
        sensorNodeRepository.saveAndFlush(sensorNode);

        // Get all the sensorNodeList
        restSensorNodeMockMvc.perform(get("/api/sensor-nodes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sensorNode.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].technologyType").value(hasItem(DEFAULT_TECHNOLOGY_TYPE.toString())))
            .andExpect(jsonPath("$.[*].trl").value(hasItem(DEFAULT_TRL)))
            .andExpect(jsonPath("$.[*].photoContentType").value(hasItem(DEFAULT_PHOTO_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].photo").value(hasItem(Base64Utils.encodeToString(DEFAULT_PHOTO))))
            .andExpect(jsonPath("$.[*].datasheetContentType").value(hasItem(DEFAULT_DATASHEET_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].datasheet").value(hasItem(Base64Utils.encodeToString(DEFAULT_DATASHEET))))
            .andExpect(jsonPath("$.[*].size").value(hasItem(DEFAULT_SIZE.doubleValue())))
            .andExpect(jsonPath("$.[*].cost").value(hasItem(DEFAULT_COST.intValue())))
            .andExpect(jsonPath("$.[*].bandwidth").value(hasItem(DEFAULT_BANDWIDTH.doubleValue())))
            .andExpect(jsonPath("$.[*].dataRate").value(hasItem(DEFAULT_DATA_RATE.doubleValue())))
            .andExpect(jsonPath("$.[*].flashMemory").value(hasItem(DEFAULT_FLASH_MEMORY.doubleValue())))
            .andExpect(jsonPath("$.[*].ram").value(hasItem(DEFAULT_RAM.toString())))
            .andExpect(jsonPath("$.[*].energyUsage").value(hasItem(DEFAULT_ENERGY_USAGE.doubleValue())))
            .andExpect(jsonPath("$.[*].sleepEnergy").value(hasItem(DEFAULT_SLEEP_ENERGY.doubleValue())))
            .andExpect(jsonPath("$.[*].dutyCycle").value(hasItem(DEFAULT_DUTY_CYCLE.toString())))
            .andExpect(jsonPath("$.[*].frequency").value(hasItem(DEFAULT_FREQUENCY.doubleValue())))
            .andExpect(jsonPath("$.[*].range").value(hasItem(DEFAULT_RANGE.toString())))
            .andExpect(jsonPath("$.[*].mobility").value(hasItem(DEFAULT_MOBILITY.toString())))
            .andExpect(jsonPath("$.[*].resilience").value(hasItem(DEFAULT_RESILIENCE.doubleValue())))
            .andExpect(jsonPath("$.[*].selfTesting").value(hasItem(DEFAULT_SELF_TESTING.toString())))
            .andExpect(jsonPath("$.[*].selfCalibration").value(hasItem(DEFAULT_SELF_CALIBRATION.toString())))
            .andExpect(jsonPath("$.[*].selfRepair").value(hasItem(DEFAULT_SELF_REPAIR.toString())))
            .andExpect(jsonPath("$.[*].transmissionPower").value(hasItem(DEFAULT_TRANSMISSION_POWER.doubleValue())));
    }

    @Test
    @Transactional
    public void getSensorNode() throws Exception {
        // Initialize the database
        sensorNodeRepository.saveAndFlush(sensorNode);

        // Get the sensorNode
        restSensorNodeMockMvc.perform(get("/api/sensor-nodes/{id}", sensorNode.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(sensorNode.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.technologyType").value(DEFAULT_TECHNOLOGY_TYPE.toString()))
            .andExpect(jsonPath("$.trl").value(DEFAULT_TRL))
            .andExpect(jsonPath("$.photoContentType").value(DEFAULT_PHOTO_CONTENT_TYPE))
            .andExpect(jsonPath("$.photo").value(Base64Utils.encodeToString(DEFAULT_PHOTO)))
            .andExpect(jsonPath("$.datasheetContentType").value(DEFAULT_DATASHEET_CONTENT_TYPE))
            .andExpect(jsonPath("$.datasheet").value(Base64Utils.encodeToString(DEFAULT_DATASHEET)))
            .andExpect(jsonPath("$.size").value(DEFAULT_SIZE.doubleValue()))
            .andExpect(jsonPath("$.cost").value(DEFAULT_COST.intValue()))
            .andExpect(jsonPath("$.bandwidth").value(DEFAULT_BANDWIDTH.doubleValue()))
            .andExpect(jsonPath("$.dataRate").value(DEFAULT_DATA_RATE.doubleValue()))
            .andExpect(jsonPath("$.flashMemory").value(DEFAULT_FLASH_MEMORY.doubleValue()))
            .andExpect(jsonPath("$.ram").value(DEFAULT_RAM.toString()))
            .andExpect(jsonPath("$.energyUsage").value(DEFAULT_ENERGY_USAGE.doubleValue()))
            .andExpect(jsonPath("$.sleepEnergy").value(DEFAULT_SLEEP_ENERGY.doubleValue()))
            .andExpect(jsonPath("$.dutyCycle").value(DEFAULT_DUTY_CYCLE.toString()))
            .andExpect(jsonPath("$.frequency").value(DEFAULT_FREQUENCY.doubleValue()))
            .andExpect(jsonPath("$.range").value(DEFAULT_RANGE.toString()))
            .andExpect(jsonPath("$.mobility").value(DEFAULT_MOBILITY.toString()))
            .andExpect(jsonPath("$.resilience").value(DEFAULT_RESILIENCE.doubleValue()))
            .andExpect(jsonPath("$.selfTesting").value(DEFAULT_SELF_TESTING.toString()))
            .andExpect(jsonPath("$.selfCalibration").value(DEFAULT_SELF_CALIBRATION.toString()))
            .andExpect(jsonPath("$.selfRepair").value(DEFAULT_SELF_REPAIR.toString()))
            .andExpect(jsonPath("$.transmissionPower").value(DEFAULT_TRANSMISSION_POWER.doubleValue()));
    }

    @Test
    @Transactional
    public void getNonExistingSensorNode() throws Exception {
        // Get the sensorNode
        restSensorNodeMockMvc.perform(get("/api/sensor-nodes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSensorNode() throws Exception {
        // Initialize the database
        sensorNodeRepository.saveAndFlush(sensorNode);
        sensorNodeSearchRepository.save(sensorNode);
        int databaseSizeBeforeUpdate = sensorNodeRepository.findAll().size();

        // Update the sensorNode
        SensorNode updatedSensorNode = sensorNodeRepository.findOne(sensorNode.getId());
        updatedSensorNode
            .name(UPDATED_NAME)
            .technologyType(UPDATED_TECHNOLOGY_TYPE)
            .trl(UPDATED_TRL)
            .photo(UPDATED_PHOTO)
            .photoContentType(UPDATED_PHOTO_CONTENT_TYPE)
            .datasheet(UPDATED_DATASHEET)
            .datasheetContentType(UPDATED_DATASHEET_CONTENT_TYPE)
            .size(UPDATED_SIZE)
            .cost(UPDATED_COST)
            .bandwidth(UPDATED_BANDWIDTH)
            .dataRate(UPDATED_DATA_RATE)
            .flashMemory(UPDATED_FLASH_MEMORY)
            .ram(UPDATED_RAM)
            .energyUsage(UPDATED_ENERGY_USAGE)
            .sleepEnergy(UPDATED_SLEEP_ENERGY)
            .dutyCycle(UPDATED_DUTY_CYCLE)
            .frequency(UPDATED_FREQUENCY)
            .range(UPDATED_RANGE)
            .mobility(UPDATED_MOBILITY)
            .resilience(UPDATED_RESILIENCE)
            .selfTesting(UPDATED_SELF_TESTING)
            .selfCalibration(UPDATED_SELF_CALIBRATION)
            .selfRepair(UPDATED_SELF_REPAIR)
            .transmissionPower(UPDATED_TRANSMISSION_POWER);

        restSensorNodeMockMvc.perform(put("/api/sensor-nodes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedSensorNode)))
            .andExpect(status().isOk());

        // Validate the SensorNode in the database
        List<SensorNode> sensorNodeList = sensorNodeRepository.findAll();
        assertThat(sensorNodeList).hasSize(databaseSizeBeforeUpdate);
        SensorNode testSensorNode = sensorNodeList.get(sensorNodeList.size() - 1);
        assertThat(testSensorNode.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testSensorNode.getTechnologyType()).isEqualTo(UPDATED_TECHNOLOGY_TYPE);
        assertThat(testSensorNode.getTrl()).isEqualTo(UPDATED_TRL);
        assertThat(testSensorNode.getPhoto()).isEqualTo(UPDATED_PHOTO);
        assertThat(testSensorNode.getPhotoContentType()).isEqualTo(UPDATED_PHOTO_CONTENT_TYPE);
        assertThat(testSensorNode.getDatasheet()).isEqualTo(UPDATED_DATASHEET);
        assertThat(testSensorNode.getDatasheetContentType()).isEqualTo(UPDATED_DATASHEET_CONTENT_TYPE);
        assertThat(testSensorNode.getSize()).isEqualTo(UPDATED_SIZE);
        assertThat(testSensorNode.getCost()).isEqualTo(UPDATED_COST);
        assertThat(testSensorNode.getBandwidth()).isEqualTo(UPDATED_BANDWIDTH);
        assertThat(testSensorNode.getDataRate()).isEqualTo(UPDATED_DATA_RATE);
        assertThat(testSensorNode.getFlashMemory()).isEqualTo(UPDATED_FLASH_MEMORY);
        assertThat(testSensorNode.getRam()).isEqualTo(UPDATED_RAM);
        assertThat(testSensorNode.getEnergyUsage()).isEqualTo(UPDATED_ENERGY_USAGE);
        assertThat(testSensorNode.getSleepEnergy()).isEqualTo(UPDATED_SLEEP_ENERGY);
        assertThat(testSensorNode.getDutyCycle()).isEqualTo(UPDATED_DUTY_CYCLE);
        assertThat(testSensorNode.getFrequency()).isEqualTo(UPDATED_FREQUENCY);
        assertThat(testSensorNode.getRange()).isEqualTo(UPDATED_RANGE);
        assertThat(testSensorNode.getMobility()).isEqualTo(UPDATED_MOBILITY);
        assertThat(testSensorNode.getResilience()).isEqualTo(UPDATED_RESILIENCE);
        assertThat(testSensorNode.getSelfTesting()).isEqualTo(UPDATED_SELF_TESTING);
        assertThat(testSensorNode.getSelfCalibration()).isEqualTo(UPDATED_SELF_CALIBRATION);
        assertThat(testSensorNode.getSelfRepair()).isEqualTo(UPDATED_SELF_REPAIR);
        assertThat(testSensorNode.getTransmissionPower()).isEqualTo(UPDATED_TRANSMISSION_POWER);

        // Validate the SensorNode in Elasticsearch
        SensorNode sensorNodeEs = sensorNodeSearchRepository.findOne(testSensorNode.getId());
        assertThat(sensorNodeEs).isEqualToComparingFieldByField(testSensorNode);
    }

    @Test
    @Transactional
    public void updateNonExistingSensorNode() throws Exception {
        int databaseSizeBeforeUpdate = sensorNodeRepository.findAll().size();

        // Create the SensorNode

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restSensorNodeMockMvc.perform(put("/api/sensor-nodes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(sensorNode)))
            .andExpect(status().isCreated());

        // Validate the SensorNode in the database
        List<SensorNode> sensorNodeList = sensorNodeRepository.findAll();
        assertThat(sensorNodeList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteSensorNode() throws Exception {
        // Initialize the database
        sensorNodeRepository.saveAndFlush(sensorNode);
        sensorNodeSearchRepository.save(sensorNode);
        int databaseSizeBeforeDelete = sensorNodeRepository.findAll().size();

        // Get the sensorNode
        restSensorNodeMockMvc.perform(delete("/api/sensor-nodes/{id}", sensorNode.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate Elasticsearch is empty
        boolean sensorNodeExistsInEs = sensorNodeSearchRepository.exists(sensorNode.getId());
        assertThat(sensorNodeExistsInEs).isFalse();

        // Validate the database is empty
        List<SensorNode> sensorNodeList = sensorNodeRepository.findAll();
        assertThat(sensorNodeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void searchSensorNode() throws Exception {
        // Initialize the database
        sensorNodeRepository.saveAndFlush(sensorNode);
        sensorNodeSearchRepository.save(sensorNode);

        // Search the sensorNode
        restSensorNodeMockMvc.perform(get("/api/_search/sensor-nodes?query=id:" + sensorNode.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(sensorNode.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].technologyType").value(hasItem(DEFAULT_TECHNOLOGY_TYPE.toString())))
            .andExpect(jsonPath("$.[*].trl").value(hasItem(DEFAULT_TRL)))
            .andExpect(jsonPath("$.[*].photoContentType").value(hasItem(DEFAULT_PHOTO_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].photo").value(hasItem(Base64Utils.encodeToString(DEFAULT_PHOTO))))
            .andExpect(jsonPath("$.[*].datasheetContentType").value(hasItem(DEFAULT_DATASHEET_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].datasheet").value(hasItem(Base64Utils.encodeToString(DEFAULT_DATASHEET))))
            .andExpect(jsonPath("$.[*].size").value(hasItem(DEFAULT_SIZE.doubleValue())))
            .andExpect(jsonPath("$.[*].cost").value(hasItem(DEFAULT_COST.intValue())))
            .andExpect(jsonPath("$.[*].bandwidth").value(hasItem(DEFAULT_BANDWIDTH.doubleValue())))
            .andExpect(jsonPath("$.[*].dataRate").value(hasItem(DEFAULT_DATA_RATE.doubleValue())))
            .andExpect(jsonPath("$.[*].flashMemory").value(hasItem(DEFAULT_FLASH_MEMORY.doubleValue())))
            .andExpect(jsonPath("$.[*].ram").value(hasItem(DEFAULT_RAM.toString())))
            .andExpect(jsonPath("$.[*].energyUsage").value(hasItem(DEFAULT_ENERGY_USAGE.doubleValue())))
            .andExpect(jsonPath("$.[*].sleepEnergy").value(hasItem(DEFAULT_SLEEP_ENERGY.doubleValue())))
            .andExpect(jsonPath("$.[*].dutyCycle").value(hasItem(DEFAULT_DUTY_CYCLE.toString())))
            .andExpect(jsonPath("$.[*].frequency").value(hasItem(DEFAULT_FREQUENCY.doubleValue())))
            .andExpect(jsonPath("$.[*].range").value(hasItem(DEFAULT_RANGE.toString())))
            .andExpect(jsonPath("$.[*].mobility").value(hasItem(DEFAULT_MOBILITY.toString())))
            .andExpect(jsonPath("$.[*].resilience").value(hasItem(DEFAULT_RESILIENCE.doubleValue())))
            .andExpect(jsonPath("$.[*].selfTesting").value(hasItem(DEFAULT_SELF_TESTING.toString())))
            .andExpect(jsonPath("$.[*].selfCalibration").value(hasItem(DEFAULT_SELF_CALIBRATION.toString())))
            .andExpect(jsonPath("$.[*].selfRepair").value(hasItem(DEFAULT_SELF_REPAIR.toString())))
            .andExpect(jsonPath("$.[*].transmissionPower").value(hasItem(DEFAULT_TRANSMISSION_POWER.doubleValue())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SensorNode.class);
        SensorNode sensorNode1 = new SensorNode();
        sensorNode1.setId(1L);
        SensorNode sensorNode2 = new SensorNode();
        sensorNode2.setId(sensorNode1.getId());
        assertThat(sensorNode1).isEqualTo(sensorNode2);
        sensorNode2.setId(2L);
        assertThat(sensorNode1).isNotEqualTo(sensorNode2);
        sensorNode1.setId(null);
        assertThat(sensorNode1).isNotEqualTo(sensorNode2);
    }
}
