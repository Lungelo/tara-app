package com.tara.org.web.rest;

import com.tara.org.TaraApp;

import com.tara.org.domain.PositioningSystem;
import com.tara.org.repository.PositioningSystemRepository;
import com.tara.org.repository.search.PositioningSystemSearchRepository;
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

import com.tara.org.domain.enumeration.MarketMaturity;
/**
 * Test class for the PositioningSystemResource REST controller.
 *
 * @see PositioningSystemResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TaraApp.class)
public class PositioningSystemResourceIntTest {

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

    private static final Float DEFAULT_ACCURACY = 1F;
    private static final Float UPDATED_ACCURACY = 2F;

    private static final String DEFAULT_COVERAGE_AREA = "AAAAAAAAAA";
    private static final String UPDATED_COVERAGE_AREA = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_COST = new BigDecimal(1);
    private static final BigDecimal UPDATED_COST = new BigDecimal(2);

    private static final String DEFAULT_REQUIRED_INFRASTRUCTURE = "AAAAAAAAAA";
    private static final String UPDATED_REQUIRED_INFRASTRUCTURE = "BBBBBBBBBB";

    private static final MarketMaturity DEFAULT_MARKET_MATURITY = MarketMaturity.CONCEPT;
    private static final MarketMaturity UPDATED_MARKET_MATURITY = MarketMaturity.DEVELOPMENT;

    private static final String DEFAULT_OUTPUT_DATA = "AAAAAAAAAA";
    private static final String UPDATED_OUTPUT_DATA = "BBBBBBBBBB";

    private static final String DEFAULT_PRIVACY = "AAAAAAAAAA";
    private static final String UPDATED_PRIVACY = "BBBBBBBBBB";

    private static final Float DEFAULT_UPDATE_RATE = 1F;
    private static final Float UPDATED_UPDATE_RATE = 2F;

    private static final String DEFAULT_SYSTEM_LATENCY = "AAAAAAAAAA";
    private static final String UPDATED_SYSTEM_LATENCY = "BBBBBBBBBB";

    private static final String DEFAULT_INTERFACE = "AAAAAAAAAA";
    private static final String UPDATED_INTERFACE = "BBBBBBBBBB";

    private static final String DEFAULT_SYSTEM_INTEGRITY = "AAAAAAAAAA";
    private static final String UPDATED_SYSTEM_INTEGRITY = "BBBBBBBBBB";

    private static final String DEFAULT_ROBUSTNESS = "AAAAAAAAAA";
    private static final String UPDATED_ROBUSTNESS = "BBBBBBBBBB";

    private static final Float DEFAULT_AVAILABILITY = 1F;
    private static final Float UPDATED_AVAILABILITY = 2F;

    private static final Float DEFAULT_CONTINUITY = 1F;
    private static final Float UPDATED_CONTINUITY = 2F;

    private static final String DEFAULT_SCALABILITY = "AAAAAAAAAA";
    private static final String UPDATED_SCALABILITY = "BBBBBBBBBB";

    private static final String DEFAULT_NUMBER_OF_USERS = "AAAAAAAAAA";
    private static final String UPDATED_NUMBER_OF_USERS = "BBBBBBBBBB";

    private static final String DEFAULT_APPROVAL = "AAAAAAAAAA";
    private static final String UPDATED_APPROVAL = "BBBBBBBBBB";

    private static final String DEFAULT_LEVEL_OF_HYBRIDISATION = "AAAAAAAAAA";
    private static final String UPDATED_LEVEL_OF_HYBRIDISATION = "BBBBBBBBBB";

    private static final String DEFAULT_TECHNOLOGY = "AAAAAAAAAA";
    private static final String UPDATED_TECHNOLOGY = "BBBBBBBBBB";

    private static final String DEFAULT_MEASURED_QUANTITY = "AAAAAAAAAA";
    private static final String UPDATED_MEASURED_QUANTITY = "BBBBBBBBBB";

    private static final String DEFAULT_BASIC_MEASURING_PRINCIPLE = "AAAAAAAAAA";
    private static final String UPDATED_BASIC_MEASURING_PRINCIPLE = "BBBBBBBBBB";

    private static final String DEFAULT_POSITION_ALGORITHM = "AAAAAAAAAA";
    private static final String UPDATED_POSITION_ALGORITHM = "BBBBBBBBBB";

    private static final String DEFAULT_SIGNALTYPE = "AAAAAAAAAA";
    private static final String UPDATED_SIGNALTYPE = "BBBBBBBBBB";

    private static final String DEFAULT_SIGNAL_WAVELENGTH = "AAAAAAAAAA";
    private static final String UPDATED_SIGNAL_WAVELENGTH = "BBBBBBBBBB";

    private static final String DEFAULT_SYSTEM_ARCHITECTURE = "AAAAAAAAAA";
    private static final String UPDATED_SYSTEM_ARCHITECTURE = "BBBBBBBBBB";

    private static final String DEFAULT_APPLICATION = "AAAAAAAAAA";
    private static final String UPDATED_APPLICATION = "BBBBBBBBBB";

    private static final String DEFAULT_COORDINATE_REFERENCE = "AAAAAAAAAA";
    private static final String UPDATED_COORDINATE_REFERENCE = "BBBBBBBBBB";

    @Autowired
    private PositioningSystemRepository positioningSystemRepository;

    @Autowired
    private PositioningSystemSearchRepository positioningSystemSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restPositioningSystemMockMvc;

    private PositioningSystem positioningSystem;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final PositioningSystemResource positioningSystemResource = new PositioningSystemResource(positioningSystemRepository, positioningSystemSearchRepository);
        this.restPositioningSystemMockMvc = MockMvcBuilders.standaloneSetup(positioningSystemResource)
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
    public static PositioningSystem createEntity(EntityManager em) {
        PositioningSystem positioningSystem = new PositioningSystem()
            .name(DEFAULT_NAME)
            .technologyType(DEFAULT_TECHNOLOGY_TYPE)
            .trl(DEFAULT_TRL)
            .photo(DEFAULT_PHOTO)
            .photoContentType(DEFAULT_PHOTO_CONTENT_TYPE)
            .datasheet(DEFAULT_DATASHEET)
            .datasheetContentType(DEFAULT_DATASHEET_CONTENT_TYPE)
            .accuracy(DEFAULT_ACCURACY)
            .coverageArea(DEFAULT_COVERAGE_AREA)
            .cost(DEFAULT_COST)
            .requiredInfrastructure(DEFAULT_REQUIRED_INFRASTRUCTURE)
            .marketMaturity(DEFAULT_MARKET_MATURITY)
            .outputData(DEFAULT_OUTPUT_DATA)
            .privacy(DEFAULT_PRIVACY)
            .updateRate(DEFAULT_UPDATE_RATE)
            .systemLatency(DEFAULT_SYSTEM_LATENCY)
            ._interface(DEFAULT_INTERFACE)
            .systemIntegrity(DEFAULT_SYSTEM_INTEGRITY)
            .robustness(DEFAULT_ROBUSTNESS)
            .availability(DEFAULT_AVAILABILITY)
            .continuity(DEFAULT_CONTINUITY)
            .scalability(DEFAULT_SCALABILITY)
            .numberOfUsers(DEFAULT_NUMBER_OF_USERS)
            .approval(DEFAULT_APPROVAL)
            .levelOfHybridisation(DEFAULT_LEVEL_OF_HYBRIDISATION)
            .technology(DEFAULT_TECHNOLOGY)
            .measuredQuantity(DEFAULT_MEASURED_QUANTITY)
            .basicMeasuringPrinciple(DEFAULT_BASIC_MEASURING_PRINCIPLE)
            .positionAlgorithm(DEFAULT_POSITION_ALGORITHM)
            .signaltype(DEFAULT_SIGNALTYPE)
            .signalWavelength(DEFAULT_SIGNAL_WAVELENGTH)
            .systemArchitecture(DEFAULT_SYSTEM_ARCHITECTURE)
            .application(DEFAULT_APPLICATION)
            .coordinateReference(DEFAULT_COORDINATE_REFERENCE);
        return positioningSystem;
    }

    @Before
    public void initTest() {
        positioningSystemSearchRepository.deleteAll();
        positioningSystem = createEntity(em);
    }

    @Test
    @Transactional
    public void createPositioningSystem() throws Exception {
        int databaseSizeBeforeCreate = positioningSystemRepository.findAll().size();

        // Create the PositioningSystem
        restPositioningSystemMockMvc.perform(post("/api/positioning-systems")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(positioningSystem)))
            .andExpect(status().isCreated());

        // Validate the PositioningSystem in the database
        List<PositioningSystem> positioningSystemList = positioningSystemRepository.findAll();
        assertThat(positioningSystemList).hasSize(databaseSizeBeforeCreate + 1);
        PositioningSystem testPositioningSystem = positioningSystemList.get(positioningSystemList.size() - 1);
        assertThat(testPositioningSystem.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testPositioningSystem.getTechnologyType()).isEqualTo(DEFAULT_TECHNOLOGY_TYPE);
        assertThat(testPositioningSystem.getTrl()).isEqualTo(DEFAULT_TRL);
        assertThat(testPositioningSystem.getPhoto()).isEqualTo(DEFAULT_PHOTO);
        assertThat(testPositioningSystem.getPhotoContentType()).isEqualTo(DEFAULT_PHOTO_CONTENT_TYPE);
        assertThat(testPositioningSystem.getDatasheet()).isEqualTo(DEFAULT_DATASHEET);
        assertThat(testPositioningSystem.getDatasheetContentType()).isEqualTo(DEFAULT_DATASHEET_CONTENT_TYPE);
        assertThat(testPositioningSystem.getAccuracy()).isEqualTo(DEFAULT_ACCURACY);
        assertThat(testPositioningSystem.getCoverageArea()).isEqualTo(DEFAULT_COVERAGE_AREA);
        assertThat(testPositioningSystem.getCost()).isEqualTo(DEFAULT_COST);
        assertThat(testPositioningSystem.getRequiredInfrastructure()).isEqualTo(DEFAULT_REQUIRED_INFRASTRUCTURE);
        assertThat(testPositioningSystem.getMarketMaturity()).isEqualTo(DEFAULT_MARKET_MATURITY);
        assertThat(testPositioningSystem.getOutputData()).isEqualTo(DEFAULT_OUTPUT_DATA);
        assertThat(testPositioningSystem.getPrivacy()).isEqualTo(DEFAULT_PRIVACY);
        assertThat(testPositioningSystem.getUpdateRate()).isEqualTo(DEFAULT_UPDATE_RATE);
        assertThat(testPositioningSystem.getSystemLatency()).isEqualTo(DEFAULT_SYSTEM_LATENCY);
        assertThat(testPositioningSystem.get_interface()).isEqualTo(DEFAULT_INTERFACE);
        assertThat(testPositioningSystem.getSystemIntegrity()).isEqualTo(DEFAULT_SYSTEM_INTEGRITY);
        assertThat(testPositioningSystem.getRobustness()).isEqualTo(DEFAULT_ROBUSTNESS);
        assertThat(testPositioningSystem.getAvailability()).isEqualTo(DEFAULT_AVAILABILITY);
        assertThat(testPositioningSystem.getContinuity()).isEqualTo(DEFAULT_CONTINUITY);
        assertThat(testPositioningSystem.getScalability()).isEqualTo(DEFAULT_SCALABILITY);
        assertThat(testPositioningSystem.getNumberOfUsers()).isEqualTo(DEFAULT_NUMBER_OF_USERS);
        assertThat(testPositioningSystem.getApproval()).isEqualTo(DEFAULT_APPROVAL);
        assertThat(testPositioningSystem.getLevelOfHybridisation()).isEqualTo(DEFAULT_LEVEL_OF_HYBRIDISATION);
        assertThat(testPositioningSystem.getTechnology()).isEqualTo(DEFAULT_TECHNOLOGY);
        assertThat(testPositioningSystem.getMeasuredQuantity()).isEqualTo(DEFAULT_MEASURED_QUANTITY);
        assertThat(testPositioningSystem.getBasicMeasuringPrinciple()).isEqualTo(DEFAULT_BASIC_MEASURING_PRINCIPLE);
        assertThat(testPositioningSystem.getPositionAlgorithm()).isEqualTo(DEFAULT_POSITION_ALGORITHM);
        assertThat(testPositioningSystem.getSignaltype()).isEqualTo(DEFAULT_SIGNALTYPE);
        assertThat(testPositioningSystem.getSignalWavelength()).isEqualTo(DEFAULT_SIGNAL_WAVELENGTH);
        assertThat(testPositioningSystem.getSystemArchitecture()).isEqualTo(DEFAULT_SYSTEM_ARCHITECTURE);
        assertThat(testPositioningSystem.getApplication()).isEqualTo(DEFAULT_APPLICATION);
        assertThat(testPositioningSystem.getCoordinateReference()).isEqualTo(DEFAULT_COORDINATE_REFERENCE);

        // Validate the PositioningSystem in Elasticsearch
        PositioningSystem positioningSystemEs = positioningSystemSearchRepository.findOne(testPositioningSystem.getId());
        assertThat(positioningSystemEs).isEqualToComparingFieldByField(testPositioningSystem);
    }

    @Test
    @Transactional
    public void createPositioningSystemWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = positioningSystemRepository.findAll().size();

        // Create the PositioningSystem with an existing ID
        positioningSystem.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPositioningSystemMockMvc.perform(post("/api/positioning-systems")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(positioningSystem)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<PositioningSystem> positioningSystemList = positioningSystemRepository.findAll();
        assertThat(positioningSystemList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = positioningSystemRepository.findAll().size();
        // set the field null
        positioningSystem.setName(null);

        // Create the PositioningSystem, which fails.

        restPositioningSystemMockMvc.perform(post("/api/positioning-systems")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(positioningSystem)))
            .andExpect(status().isBadRequest());

        List<PositioningSystem> positioningSystemList = positioningSystemRepository.findAll();
        assertThat(positioningSystemList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTechnologyTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = positioningSystemRepository.findAll().size();
        // set the field null
        positioningSystem.setTechnologyType(null);

        // Create the PositioningSystem, which fails.

        restPositioningSystemMockMvc.perform(post("/api/positioning-systems")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(positioningSystem)))
            .andExpect(status().isBadRequest());

        List<PositioningSystem> positioningSystemList = positioningSystemRepository.findAll();
        assertThat(positioningSystemList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTrlIsRequired() throws Exception {
        int databaseSizeBeforeTest = positioningSystemRepository.findAll().size();
        // set the field null
        positioningSystem.setTrl(null);

        // Create the PositioningSystem, which fails.

        restPositioningSystemMockMvc.perform(post("/api/positioning-systems")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(positioningSystem)))
            .andExpect(status().isBadRequest());

        List<PositioningSystem> positioningSystemList = positioningSystemRepository.findAll();
        assertThat(positioningSystemList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAccuracyIsRequired() throws Exception {
        int databaseSizeBeforeTest = positioningSystemRepository.findAll().size();
        // set the field null
        positioningSystem.setAccuracy(null);

        // Create the PositioningSystem, which fails.

        restPositioningSystemMockMvc.perform(post("/api/positioning-systems")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(positioningSystem)))
            .andExpect(status().isBadRequest());

        List<PositioningSystem> positioningSystemList = positioningSystemRepository.findAll();
        assertThat(positioningSystemList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCoverageAreaIsRequired() throws Exception {
        int databaseSizeBeforeTest = positioningSystemRepository.findAll().size();
        // set the field null
        positioningSystem.setCoverageArea(null);

        // Create the PositioningSystem, which fails.

        restPositioningSystemMockMvc.perform(post("/api/positioning-systems")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(positioningSystem)))
            .andExpect(status().isBadRequest());

        List<PositioningSystem> positioningSystemList = positioningSystemRepository.findAll();
        assertThat(positioningSystemList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCostIsRequired() throws Exception {
        int databaseSizeBeforeTest = positioningSystemRepository.findAll().size();
        // set the field null
        positioningSystem.setCost(null);

        // Create the PositioningSystem, which fails.

        restPositioningSystemMockMvc.perform(post("/api/positioning-systems")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(positioningSystem)))
            .andExpect(status().isBadRequest());

        List<PositioningSystem> positioningSystemList = positioningSystemRepository.findAll();
        assertThat(positioningSystemList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMarketMaturityIsRequired() throws Exception {
        int databaseSizeBeforeTest = positioningSystemRepository.findAll().size();
        // set the field null
        positioningSystem.setMarketMaturity(null);

        // Create the PositioningSystem, which fails.

        restPositioningSystemMockMvc.perform(post("/api/positioning-systems")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(positioningSystem)))
            .andExpect(status().isBadRequest());

        List<PositioningSystem> positioningSystemList = positioningSystemRepository.findAll();
        assertThat(positioningSystemList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkOutputDataIsRequired() throws Exception {
        int databaseSizeBeforeTest = positioningSystemRepository.findAll().size();
        // set the field null
        positioningSystem.setOutputData(null);

        // Create the PositioningSystem, which fails.

        restPositioningSystemMockMvc.perform(post("/api/positioning-systems")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(positioningSystem)))
            .andExpect(status().isBadRequest());

        List<PositioningSystem> positioningSystemList = positioningSystemRepository.findAll();
        assertThat(positioningSystemList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPrivacyIsRequired() throws Exception {
        int databaseSizeBeforeTest = positioningSystemRepository.findAll().size();
        // set the field null
        positioningSystem.setPrivacy(null);

        // Create the PositioningSystem, which fails.

        restPositioningSystemMockMvc.perform(post("/api/positioning-systems")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(positioningSystem)))
            .andExpect(status().isBadRequest());

        List<PositioningSystem> positioningSystemList = positioningSystemRepository.findAll();
        assertThat(positioningSystemList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAvailabilityIsRequired() throws Exception {
        int databaseSizeBeforeTest = positioningSystemRepository.findAll().size();
        // set the field null
        positioningSystem.setAvailability(null);

        // Create the PositioningSystem, which fails.

        restPositioningSystemMockMvc.perform(post("/api/positioning-systems")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(positioningSystem)))
            .andExpect(status().isBadRequest());

        List<PositioningSystem> positioningSystemList = positioningSystemRepository.findAll();
        assertThat(positioningSystemList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllPositioningSystems() throws Exception {
        // Initialize the database
        positioningSystemRepository.saveAndFlush(positioningSystem);

        // Get all the positioningSystemList
        restPositioningSystemMockMvc.perform(get("/api/positioning-systems?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(positioningSystem.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].technologyType").value(hasItem(DEFAULT_TECHNOLOGY_TYPE.toString())))
            .andExpect(jsonPath("$.[*].trl").value(hasItem(DEFAULT_TRL)))
            .andExpect(jsonPath("$.[*].photoContentType").value(hasItem(DEFAULT_PHOTO_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].photo").value(hasItem(Base64Utils.encodeToString(DEFAULT_PHOTO))))
            .andExpect(jsonPath("$.[*].datasheetContentType").value(hasItem(DEFAULT_DATASHEET_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].datasheet").value(hasItem(Base64Utils.encodeToString(DEFAULT_DATASHEET))))
            .andExpect(jsonPath("$.[*].accuracy").value(hasItem(DEFAULT_ACCURACY.doubleValue())))
            .andExpect(jsonPath("$.[*].coverageArea").value(hasItem(DEFAULT_COVERAGE_AREA.toString())))
            .andExpect(jsonPath("$.[*].cost").value(hasItem(DEFAULT_COST.intValue())))
            .andExpect(jsonPath("$.[*].requiredInfrastructure").value(hasItem(DEFAULT_REQUIRED_INFRASTRUCTURE.toString())))
            .andExpect(jsonPath("$.[*].marketMaturity").value(hasItem(DEFAULT_MARKET_MATURITY.toString())))
            .andExpect(jsonPath("$.[*].outputData").value(hasItem(DEFAULT_OUTPUT_DATA.toString())))
            .andExpect(jsonPath("$.[*].privacy").value(hasItem(DEFAULT_PRIVACY.toString())))
            .andExpect(jsonPath("$.[*].updateRate").value(hasItem(DEFAULT_UPDATE_RATE.doubleValue())))
            .andExpect(jsonPath("$.[*].systemLatency").value(hasItem(DEFAULT_SYSTEM_LATENCY.toString())))
            .andExpect(jsonPath("$.[*]._interface").value(hasItem(DEFAULT_INTERFACE.toString())))
            .andExpect(jsonPath("$.[*].systemIntegrity").value(hasItem(DEFAULT_SYSTEM_INTEGRITY.toString())))
            .andExpect(jsonPath("$.[*].robustness").value(hasItem(DEFAULT_ROBUSTNESS.toString())))
            .andExpect(jsonPath("$.[*].availability").value(hasItem(DEFAULT_AVAILABILITY.doubleValue())))
            .andExpect(jsonPath("$.[*].continuity").value(hasItem(DEFAULT_CONTINUITY.doubleValue())))
            .andExpect(jsonPath("$.[*].scalability").value(hasItem(DEFAULT_SCALABILITY.toString())))
            .andExpect(jsonPath("$.[*].numberOfUsers").value(hasItem(DEFAULT_NUMBER_OF_USERS.toString())))
            .andExpect(jsonPath("$.[*].approval").value(hasItem(DEFAULT_APPROVAL.toString())))
            .andExpect(jsonPath("$.[*].levelOfHybridisation").value(hasItem(DEFAULT_LEVEL_OF_HYBRIDISATION.toString())))
            .andExpect(jsonPath("$.[*].technology").value(hasItem(DEFAULT_TECHNOLOGY.toString())))
            .andExpect(jsonPath("$.[*].measuredQuantity").value(hasItem(DEFAULT_MEASURED_QUANTITY.toString())))
            .andExpect(jsonPath("$.[*].basicMeasuringPrinciple").value(hasItem(DEFAULT_BASIC_MEASURING_PRINCIPLE.toString())))
            .andExpect(jsonPath("$.[*].positionAlgorithm").value(hasItem(DEFAULT_POSITION_ALGORITHM.toString())))
            .andExpect(jsonPath("$.[*].signaltype").value(hasItem(DEFAULT_SIGNALTYPE.toString())))
            .andExpect(jsonPath("$.[*].signalWavelength").value(hasItem(DEFAULT_SIGNAL_WAVELENGTH.toString())))
            .andExpect(jsonPath("$.[*].systemArchitecture").value(hasItem(DEFAULT_SYSTEM_ARCHITECTURE.toString())))
            .andExpect(jsonPath("$.[*].application").value(hasItem(DEFAULT_APPLICATION.toString())))
            .andExpect(jsonPath("$.[*].coordinateReference").value(hasItem(DEFAULT_COORDINATE_REFERENCE.toString())));
    }

    @Test
    @Transactional
    public void getPositioningSystem() throws Exception {
        // Initialize the database
        positioningSystemRepository.saveAndFlush(positioningSystem);

        // Get the positioningSystem
        restPositioningSystemMockMvc.perform(get("/api/positioning-systems/{id}", positioningSystem.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(positioningSystem.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.technologyType").value(DEFAULT_TECHNOLOGY_TYPE.toString()))
            .andExpect(jsonPath("$.trl").value(DEFAULT_TRL))
            .andExpect(jsonPath("$.photoContentType").value(DEFAULT_PHOTO_CONTENT_TYPE))
            .andExpect(jsonPath("$.photo").value(Base64Utils.encodeToString(DEFAULT_PHOTO)))
            .andExpect(jsonPath("$.datasheetContentType").value(DEFAULT_DATASHEET_CONTENT_TYPE))
            .andExpect(jsonPath("$.datasheet").value(Base64Utils.encodeToString(DEFAULT_DATASHEET)))
            .andExpect(jsonPath("$.accuracy").value(DEFAULT_ACCURACY.doubleValue()))
            .andExpect(jsonPath("$.coverageArea").value(DEFAULT_COVERAGE_AREA.toString()))
            .andExpect(jsonPath("$.cost").value(DEFAULT_COST.intValue()))
            .andExpect(jsonPath("$.requiredInfrastructure").value(DEFAULT_REQUIRED_INFRASTRUCTURE.toString()))
            .andExpect(jsonPath("$.marketMaturity").value(DEFAULT_MARKET_MATURITY.toString()))
            .andExpect(jsonPath("$.outputData").value(DEFAULT_OUTPUT_DATA.toString()))
            .andExpect(jsonPath("$.privacy").value(DEFAULT_PRIVACY.toString()))
            .andExpect(jsonPath("$.updateRate").value(DEFAULT_UPDATE_RATE.doubleValue()))
            .andExpect(jsonPath("$.systemLatency").value(DEFAULT_SYSTEM_LATENCY.toString()))
            .andExpect(jsonPath("$._interface").value(DEFAULT_INTERFACE.toString()))
            .andExpect(jsonPath("$.systemIntegrity").value(DEFAULT_SYSTEM_INTEGRITY.toString()))
            .andExpect(jsonPath("$.robustness").value(DEFAULT_ROBUSTNESS.toString()))
            .andExpect(jsonPath("$.availability").value(DEFAULT_AVAILABILITY.doubleValue()))
            .andExpect(jsonPath("$.continuity").value(DEFAULT_CONTINUITY.doubleValue()))
            .andExpect(jsonPath("$.scalability").value(DEFAULT_SCALABILITY.toString()))
            .andExpect(jsonPath("$.numberOfUsers").value(DEFAULT_NUMBER_OF_USERS.toString()))
            .andExpect(jsonPath("$.approval").value(DEFAULT_APPROVAL.toString()))
            .andExpect(jsonPath("$.levelOfHybridisation").value(DEFAULT_LEVEL_OF_HYBRIDISATION.toString()))
            .andExpect(jsonPath("$.technology").value(DEFAULT_TECHNOLOGY.toString()))
            .andExpect(jsonPath("$.measuredQuantity").value(DEFAULT_MEASURED_QUANTITY.toString()))
            .andExpect(jsonPath("$.basicMeasuringPrinciple").value(DEFAULT_BASIC_MEASURING_PRINCIPLE.toString()))
            .andExpect(jsonPath("$.positionAlgorithm").value(DEFAULT_POSITION_ALGORITHM.toString()))
            .andExpect(jsonPath("$.signaltype").value(DEFAULT_SIGNALTYPE.toString()))
            .andExpect(jsonPath("$.signalWavelength").value(DEFAULT_SIGNAL_WAVELENGTH.toString()))
            .andExpect(jsonPath("$.systemArchitecture").value(DEFAULT_SYSTEM_ARCHITECTURE.toString()))
            .andExpect(jsonPath("$.application").value(DEFAULT_APPLICATION.toString()))
            .andExpect(jsonPath("$.coordinateReference").value(DEFAULT_COORDINATE_REFERENCE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingPositioningSystem() throws Exception {
        // Get the positioningSystem
        restPositioningSystemMockMvc.perform(get("/api/positioning-systems/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePositioningSystem() throws Exception {
        // Initialize the database
        positioningSystemRepository.saveAndFlush(positioningSystem);
        positioningSystemSearchRepository.save(positioningSystem);
        int databaseSizeBeforeUpdate = positioningSystemRepository.findAll().size();

        // Update the positioningSystem
        PositioningSystem updatedPositioningSystem = positioningSystemRepository.findOne(positioningSystem.getId());
        updatedPositioningSystem
            .name(UPDATED_NAME)
            .technologyType(UPDATED_TECHNOLOGY_TYPE)
            .trl(UPDATED_TRL)
            .photo(UPDATED_PHOTO)
            .photoContentType(UPDATED_PHOTO_CONTENT_TYPE)
            .datasheet(UPDATED_DATASHEET)
            .datasheetContentType(UPDATED_DATASHEET_CONTENT_TYPE)
            .accuracy(UPDATED_ACCURACY)
            .coverageArea(UPDATED_COVERAGE_AREA)
            .cost(UPDATED_COST)
            .requiredInfrastructure(UPDATED_REQUIRED_INFRASTRUCTURE)
            .marketMaturity(UPDATED_MARKET_MATURITY)
            .outputData(UPDATED_OUTPUT_DATA)
            .privacy(UPDATED_PRIVACY)
            .updateRate(UPDATED_UPDATE_RATE)
            .systemLatency(UPDATED_SYSTEM_LATENCY)
            ._interface(UPDATED_INTERFACE)
            .systemIntegrity(UPDATED_SYSTEM_INTEGRITY)
            .robustness(UPDATED_ROBUSTNESS)
            .availability(UPDATED_AVAILABILITY)
            .continuity(UPDATED_CONTINUITY)
            .scalability(UPDATED_SCALABILITY)
            .numberOfUsers(UPDATED_NUMBER_OF_USERS)
            .approval(UPDATED_APPROVAL)
            .levelOfHybridisation(UPDATED_LEVEL_OF_HYBRIDISATION)
            .technology(UPDATED_TECHNOLOGY)
            .measuredQuantity(UPDATED_MEASURED_QUANTITY)
            .basicMeasuringPrinciple(UPDATED_BASIC_MEASURING_PRINCIPLE)
            .positionAlgorithm(UPDATED_POSITION_ALGORITHM)
            .signaltype(UPDATED_SIGNALTYPE)
            .signalWavelength(UPDATED_SIGNAL_WAVELENGTH)
            .systemArchitecture(UPDATED_SYSTEM_ARCHITECTURE)
            .application(UPDATED_APPLICATION)
            .coordinateReference(UPDATED_COORDINATE_REFERENCE);

        restPositioningSystemMockMvc.perform(put("/api/positioning-systems")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedPositioningSystem)))
            .andExpect(status().isOk());

        // Validate the PositioningSystem in the database
        List<PositioningSystem> positioningSystemList = positioningSystemRepository.findAll();
        assertThat(positioningSystemList).hasSize(databaseSizeBeforeUpdate);
        PositioningSystem testPositioningSystem = positioningSystemList.get(positioningSystemList.size() - 1);
        assertThat(testPositioningSystem.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testPositioningSystem.getTechnologyType()).isEqualTo(UPDATED_TECHNOLOGY_TYPE);
        assertThat(testPositioningSystem.getTrl()).isEqualTo(UPDATED_TRL);
        assertThat(testPositioningSystem.getPhoto()).isEqualTo(UPDATED_PHOTO);
        assertThat(testPositioningSystem.getPhotoContentType()).isEqualTo(UPDATED_PHOTO_CONTENT_TYPE);
        assertThat(testPositioningSystem.getDatasheet()).isEqualTo(UPDATED_DATASHEET);
        assertThat(testPositioningSystem.getDatasheetContentType()).isEqualTo(UPDATED_DATASHEET_CONTENT_TYPE);
        assertThat(testPositioningSystem.getAccuracy()).isEqualTo(UPDATED_ACCURACY);
        assertThat(testPositioningSystem.getCoverageArea()).isEqualTo(UPDATED_COVERAGE_AREA);
        assertThat(testPositioningSystem.getCost()).isEqualTo(UPDATED_COST);
        assertThat(testPositioningSystem.getRequiredInfrastructure()).isEqualTo(UPDATED_REQUIRED_INFRASTRUCTURE);
        assertThat(testPositioningSystem.getMarketMaturity()).isEqualTo(UPDATED_MARKET_MATURITY);
        assertThat(testPositioningSystem.getOutputData()).isEqualTo(UPDATED_OUTPUT_DATA);
        assertThat(testPositioningSystem.getPrivacy()).isEqualTo(UPDATED_PRIVACY);
        assertThat(testPositioningSystem.getUpdateRate()).isEqualTo(UPDATED_UPDATE_RATE);
        assertThat(testPositioningSystem.getSystemLatency()).isEqualTo(UPDATED_SYSTEM_LATENCY);
        assertThat(testPositioningSystem.get_interface()).isEqualTo(UPDATED_INTERFACE);
        assertThat(testPositioningSystem.getSystemIntegrity()).isEqualTo(UPDATED_SYSTEM_INTEGRITY);
        assertThat(testPositioningSystem.getRobustness()).isEqualTo(UPDATED_ROBUSTNESS);
        assertThat(testPositioningSystem.getAvailability()).isEqualTo(UPDATED_AVAILABILITY);
        assertThat(testPositioningSystem.getContinuity()).isEqualTo(UPDATED_CONTINUITY);
        assertThat(testPositioningSystem.getScalability()).isEqualTo(UPDATED_SCALABILITY);
        assertThat(testPositioningSystem.getNumberOfUsers()).isEqualTo(UPDATED_NUMBER_OF_USERS);
        assertThat(testPositioningSystem.getApproval()).isEqualTo(UPDATED_APPROVAL);
        assertThat(testPositioningSystem.getLevelOfHybridisation()).isEqualTo(UPDATED_LEVEL_OF_HYBRIDISATION);
        assertThat(testPositioningSystem.getTechnology()).isEqualTo(UPDATED_TECHNOLOGY);
        assertThat(testPositioningSystem.getMeasuredQuantity()).isEqualTo(UPDATED_MEASURED_QUANTITY);
        assertThat(testPositioningSystem.getBasicMeasuringPrinciple()).isEqualTo(UPDATED_BASIC_MEASURING_PRINCIPLE);
        assertThat(testPositioningSystem.getPositionAlgorithm()).isEqualTo(UPDATED_POSITION_ALGORITHM);
        assertThat(testPositioningSystem.getSignaltype()).isEqualTo(UPDATED_SIGNALTYPE);
        assertThat(testPositioningSystem.getSignalWavelength()).isEqualTo(UPDATED_SIGNAL_WAVELENGTH);
        assertThat(testPositioningSystem.getSystemArchitecture()).isEqualTo(UPDATED_SYSTEM_ARCHITECTURE);
        assertThat(testPositioningSystem.getApplication()).isEqualTo(UPDATED_APPLICATION);
        assertThat(testPositioningSystem.getCoordinateReference()).isEqualTo(UPDATED_COORDINATE_REFERENCE);

        // Validate the PositioningSystem in Elasticsearch
        PositioningSystem positioningSystemEs = positioningSystemSearchRepository.findOne(testPositioningSystem.getId());
        assertThat(positioningSystemEs).isEqualToComparingFieldByField(testPositioningSystem);
    }

    @Test
    @Transactional
    public void updateNonExistingPositioningSystem() throws Exception {
        int databaseSizeBeforeUpdate = positioningSystemRepository.findAll().size();

        // Create the PositioningSystem

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restPositioningSystemMockMvc.perform(put("/api/positioning-systems")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(positioningSystem)))
            .andExpect(status().isCreated());

        // Validate the PositioningSystem in the database
        List<PositioningSystem> positioningSystemList = positioningSystemRepository.findAll();
        assertThat(positioningSystemList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deletePositioningSystem() throws Exception {
        // Initialize the database
        positioningSystemRepository.saveAndFlush(positioningSystem);
        positioningSystemSearchRepository.save(positioningSystem);
        int databaseSizeBeforeDelete = positioningSystemRepository.findAll().size();

        // Get the positioningSystem
        restPositioningSystemMockMvc.perform(delete("/api/positioning-systems/{id}", positioningSystem.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate Elasticsearch is empty
        boolean positioningSystemExistsInEs = positioningSystemSearchRepository.exists(positioningSystem.getId());
        assertThat(positioningSystemExistsInEs).isFalse();

        // Validate the database is empty
        List<PositioningSystem> positioningSystemList = positioningSystemRepository.findAll();
        assertThat(positioningSystemList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void searchPositioningSystem() throws Exception {
        // Initialize the database
        positioningSystemRepository.saveAndFlush(positioningSystem);
        positioningSystemSearchRepository.save(positioningSystem);

        // Search the positioningSystem
        restPositioningSystemMockMvc.perform(get("/api/_search/positioning-systems?query=id:" + positioningSystem.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(positioningSystem.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].technologyType").value(hasItem(DEFAULT_TECHNOLOGY_TYPE.toString())))
            .andExpect(jsonPath("$.[*].trl").value(hasItem(DEFAULT_TRL)))
            .andExpect(jsonPath("$.[*].photoContentType").value(hasItem(DEFAULT_PHOTO_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].photo").value(hasItem(Base64Utils.encodeToString(DEFAULT_PHOTO))))
            .andExpect(jsonPath("$.[*].datasheetContentType").value(hasItem(DEFAULT_DATASHEET_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].datasheet").value(hasItem(Base64Utils.encodeToString(DEFAULT_DATASHEET))))
            .andExpect(jsonPath("$.[*].accuracy").value(hasItem(DEFAULT_ACCURACY.doubleValue())))
            .andExpect(jsonPath("$.[*].coverageArea").value(hasItem(DEFAULT_COVERAGE_AREA.toString())))
            .andExpect(jsonPath("$.[*].cost").value(hasItem(DEFAULT_COST.intValue())))
            .andExpect(jsonPath("$.[*].requiredInfrastructure").value(hasItem(DEFAULT_REQUIRED_INFRASTRUCTURE.toString())))
            .andExpect(jsonPath("$.[*].marketMaturity").value(hasItem(DEFAULT_MARKET_MATURITY.toString())))
            .andExpect(jsonPath("$.[*].outputData").value(hasItem(DEFAULT_OUTPUT_DATA.toString())))
            .andExpect(jsonPath("$.[*].privacy").value(hasItem(DEFAULT_PRIVACY.toString())))
            .andExpect(jsonPath("$.[*].updateRate").value(hasItem(DEFAULT_UPDATE_RATE.doubleValue())))
            .andExpect(jsonPath("$.[*].systemLatency").value(hasItem(DEFAULT_SYSTEM_LATENCY.toString())))
            .andExpect(jsonPath("$.[*]._interface").value(hasItem(DEFAULT_INTERFACE.toString())))
            .andExpect(jsonPath("$.[*].systemIntegrity").value(hasItem(DEFAULT_SYSTEM_INTEGRITY.toString())))
            .andExpect(jsonPath("$.[*].robustness").value(hasItem(DEFAULT_ROBUSTNESS.toString())))
            .andExpect(jsonPath("$.[*].availability").value(hasItem(DEFAULT_AVAILABILITY.doubleValue())))
            .andExpect(jsonPath("$.[*].continuity").value(hasItem(DEFAULT_CONTINUITY.doubleValue())))
            .andExpect(jsonPath("$.[*].scalability").value(hasItem(DEFAULT_SCALABILITY.toString())))
            .andExpect(jsonPath("$.[*].numberOfUsers").value(hasItem(DEFAULT_NUMBER_OF_USERS.toString())))
            .andExpect(jsonPath("$.[*].approval").value(hasItem(DEFAULT_APPROVAL.toString())))
            .andExpect(jsonPath("$.[*].levelOfHybridisation").value(hasItem(DEFAULT_LEVEL_OF_HYBRIDISATION.toString())))
            .andExpect(jsonPath("$.[*].technology").value(hasItem(DEFAULT_TECHNOLOGY.toString())))
            .andExpect(jsonPath("$.[*].measuredQuantity").value(hasItem(DEFAULT_MEASURED_QUANTITY.toString())))
            .andExpect(jsonPath("$.[*].basicMeasuringPrinciple").value(hasItem(DEFAULT_BASIC_MEASURING_PRINCIPLE.toString())))
            .andExpect(jsonPath("$.[*].positionAlgorithm").value(hasItem(DEFAULT_POSITION_ALGORITHM.toString())))
            .andExpect(jsonPath("$.[*].signaltype").value(hasItem(DEFAULT_SIGNALTYPE.toString())))
            .andExpect(jsonPath("$.[*].signalWavelength").value(hasItem(DEFAULT_SIGNAL_WAVELENGTH.toString())))
            .andExpect(jsonPath("$.[*].systemArchitecture").value(hasItem(DEFAULT_SYSTEM_ARCHITECTURE.toString())))
            .andExpect(jsonPath("$.[*].application").value(hasItem(DEFAULT_APPLICATION.toString())))
            .andExpect(jsonPath("$.[*].coordinateReference").value(hasItem(DEFAULT_COORDINATE_REFERENCE.toString())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PositioningSystem.class);
        PositioningSystem positioningSystem1 = new PositioningSystem();
        positioningSystem1.setId(1L);
        PositioningSystem positioningSystem2 = new PositioningSystem();
        positioningSystem2.setId(positioningSystem1.getId());
        assertThat(positioningSystem1).isEqualTo(positioningSystem2);
        positioningSystem2.setId(2L);
        assertThat(positioningSystem1).isNotEqualTo(positioningSystem2);
        positioningSystem1.setId(null);
        assertThat(positioningSystem1).isNotEqualTo(positioningSystem2);
    }
}
