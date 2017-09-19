package com.tara.org.web.rest;

import com.tara.org.TaraApp;

import com.tara.org.domain.DevCleaningTool;
import com.tara.org.repository.DevCleaningToolRepository;
import com.tara.org.repository.search.DevCleaningToolSearchRepository;
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
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.tara.org.domain.enumeration.LubricationType;
import com.tara.org.domain.enumeration.LubricationType;
/**
 * Test class for the DevCleaningToolResource REST controller.
 *
 * @see DevCleaningToolResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TaraApp.class)
public class DevCleaningToolResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_MODEL = "AAAAAAAAAA";
    private static final String UPDATED_MODEL = "BBBBBBBBBB";

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

    private static final Float DEFAULT_TRAMMING_CAPACITY = 1F;
    private static final Float UPDATED_TRAMMING_CAPACITY = 2F;

    private static final Float DEFAULT_TESTED_TRAMMING_CAPACITY = 1F;
    private static final Float UPDATED_TESTED_TRAMMING_CAPACITY = 2F;

    private static final Float DEFAULT_HEIGHT = 1F;
    private static final Float UPDATED_HEIGHT = 2F;

    private static final Float DEFAULT_TESTED_HEIGHT = 1F;
    private static final Float UPDATED_TESTED_HEIGHT = 2F;

    private static final Float DEFAULT_WEIGHT = 1F;
    private static final Float UPDATED_WEIGHT = 2F;

    private static final Float DEFAULT_TESTED_WEIGHT = 1F;
    private static final Float UPDATED_TESTED_WEIGHT = 2F;

    private static final Float DEFAULT_LENGTH = 1F;
    private static final Float UPDATED_LENGTH = 2F;

    private static final Float DEFAULT_TESTED_LENGTH = 1F;
    private static final Float UPDATED_TESTED_LENGTH = 2F;

    private static final Float DEFAULT_RPM_OUTPUT = 1F;
    private static final Float UPDATED_RPM_OUTPUT = 2F;

    private static final Float DEFAULT_TESTED_RPM_OUTPUT = 1F;
    private static final Float UPDATED_TESTED_RPM_OUTPUT = 2F;

    private static final Float DEFAULT_TORQUE = 1F;
    private static final Float UPDATED_TORQUE = 2F;

    private static final Float DEFAULT_TESTED_TORQUE = 1F;
    private static final Float UPDATED_TESTED_TORQUE = 2F;

    private static final String DEFAULT_TANK_RANGE = "AAAAAAAAAA";
    private static final String UPDATED_TANK_RANGE = "BBBBBBBBBB";

    private static final String DEFAULT_TESTED_TANK_RANGE = "AAAAAAAAAA";
    private static final String UPDATED_TESTED_TANK_RANGE = "BBBBBBBBBB";

    private static final Float DEFAULT_FUEL_CONSUMPTION = 1F;
    private static final Float UPDATED_FUEL_CONSUMPTION = 2F;

    private static final Float DEFAULT_TESTED_FUEL_CONSUMPTION = 1F;
    private static final Float UPDATED_TESTED_FUEL_CONSUMPTION = 2F;

    private static final Float DEFAULT_AVAILABILITY = 1F;
    private static final Float UPDATED_AVAILABILITY = 2F;

    private static final Float DEFAULT_TESTED_AVAILABILITY = 1F;
    private static final Float UPDATED_TESTED_AVAILABILITY = 2F;

    private static final Float DEFAULT_OPERATING_COST_PER_TONNE = 1F;
    private static final Float UPDATED_OPERATING_COST_PER_TONNE = 2F;

    private static final Float DEFAULT_TESTED_OPERATING_COST_PER_TONNE = 1F;
    private static final Float UPDATED_TESTED_OPERATING_COST_PER_TONNE = 2F;

    private static final Float DEFAULT_FUEL_CONSUMPTION_PER_TONNE_HAULED = 1F;
    private static final Float UPDATED_FUEL_CONSUMPTION_PER_TONNE_HAULED = 2F;

    private static final Float DEFAULT_TEST_FUEL_CONSUMPTION_PER_TONNE_HAULED = 1F;
    private static final Float UPDATED_TEST_FUEL_CONSUMPTION_PER_TONNE_HAULED = 2F;

    private static final String DEFAULT_CONTROL_SYSTEM = "AAAAAAAAAA";
    private static final String UPDATED_CONTROL_SYSTEM = "BBBBBBBBBB";

    private static final String DEFAULT_TESTED_CONTROL_SYSTEM = "AAAAAAAAAA";
    private static final String UPDATED_TESTED_CONTROL_SYSTEM = "BBBBBBBBBB";

    private static final Float DEFAULT_CYCLE_TIME = 1F;
    private static final Float UPDATED_CYCLE_TIME = 2F;

    private static final Float DEFAULT_TESTED_CYCLE_TIME = 1F;
    private static final Float UPDATED_TESTED_CYCLE_TIME = 2F;

    private static final Float DEFAULT_TURNING_RADIUS_INNER = 1F;
    private static final Float UPDATED_TURNING_RADIUS_INNER = 2F;

    private static final Float DEFAULT_TESTED_TURNING_RADIUS_INNER = 1F;
    private static final Float UPDATED_TESTED_TURNING_RADIUS_INNER = 2F;

    private static final Float DEFAULT_TURNING_RADIUS_OUTER = 1F;
    private static final Float UPDATED_TURNING_RADIUS_OUTER = 2F;

    private static final Float DEFAULT_TESTED_TURNING_RADIUS_OUTER = 1F;
    private static final Float UPDATED_TESTED_TURNING_RADIUS_OUTER = 2F;

    private static final LubricationType DEFAULT_LUBRICATION_TYPE = LubricationType.OIL_BASED;
    private static final LubricationType UPDATED_LUBRICATION_TYPE = LubricationType.WATER_BASED;

    private static final LubricationType DEFAULT_TESTED_LUBRICATION_TYPE = LubricationType.OIL_BASED;
    private static final LubricationType UPDATED_TESTED_LUBRICATION_TYPE = LubricationType.WATER_BASED;

    private static final Float DEFAULT_TEMPERATURE_AT_AMBIENT = 1F;
    private static final Float UPDATED_TEMPERATURE_AT_AMBIENT = 2F;

    private static final Float DEFAULT_TESTED_TEMPERATURE_AT_AMBIENT = 1F;
    private static final Float UPDATED_TESTED_TEMPERATURE_AT_AMBIENT = 2F;

    private static final String DEFAULT_OBSERVATIONS_1 = "AAAAAAAAAA";
    private static final String UPDATED_OBSERVATIONS_1 = "BBBBBBBBBB";

    private static final String DEFAULT_TESTED_OBSERVATIONS_1 = "AAAAAAAAAA";
    private static final String UPDATED_TESTED_OBSERVATIONS_1 = "BBBBBBBBBB";

    private static final String DEFAULT_OBSERVATIONS_2 = "AAAAAAAAAA";
    private static final String UPDATED_OBSERVATIONS_2 = "BBBBBBBBBB";

    private static final String DEFAULT_TESTED_OBSERVATIONS_2 = "AAAAAAAAAA";
    private static final String UPDATED_TESTED_OBSERVATIONS_2 = "BBBBBBBBBB";

    private static final String DEFAULT_OBSERVATIONS_3 = "AAAAAAAAAA";
    private static final String UPDATED_OBSERVATIONS_3 = "BBBBBBBBBB";

    private static final String DEFAULT_TESTED_OBSERVATIONS_3 = "AAAAAAAAAA";
    private static final String UPDATED_TESTED_OBSERVATIONS_3 = "BBBBBBBBBB";

    private static final String DEFAULT_OBSERVATIONS_4 = "AAAAAAAAAA";
    private static final String UPDATED_OBSERVATIONS_4 = "BBBBBBBBBB";

    private static final String DEFAULT_TESTED_OBSERVATIONS_4 = "AAAAAAAAAA";
    private static final String UPDATED_TESTED_OBSERVATIONS_4 = "BBBBBBBBBB";

    private static final String DEFAULT_OBSERVATIONS_5 = "AAAAAAAAAA";
    private static final String UPDATED_OBSERVATIONS_5 = "BBBBBBBBBB";

    private static final String DEFAULT_TESTED_OBSERVATIONS_5 = "AAAAAAAAAA";
    private static final String UPDATED_TESTED_OBSERVATIONS_5 = "BBBBBBBBBB";

    private static final String DEFAULT_OBSERVATIONS_6 = "AAAAAAAAAA";
    private static final String UPDATED_OBSERVATIONS_6 = "BBBBBBBBBB";

    private static final String DEFAULT_TESTED_OBSERVATIONS_6 = "AAAAAAAAAA";
    private static final String UPDATED_TESTED_OBSERVATIONS_6 = "BBBBBBBBBB";

    private static final String DEFAULT_OPERATORS_COMMENTS = "AAAAAAAAAA";
    private static final String UPDATED_OPERATORS_COMMENTS = "BBBBBBBBBB";

    private static final String DEFAULT_TESTED_OPERATORS_COMMENTS = "AAAAAAAAAA";
    private static final String UPDATED_TESTED_OPERATORS_COMMENTS = "BBBBBBBBBB";

    @Autowired
    private DevCleaningToolRepository devCleaningToolRepository;

    @Autowired
    private DevCleaningToolSearchRepository devCleaningToolSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restDevCleaningToolMockMvc;

    private DevCleaningTool devCleaningTool;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DevCleaningToolResource devCleaningToolResource = new DevCleaningToolResource(devCleaningToolRepository, devCleaningToolSearchRepository);
        this.restDevCleaningToolMockMvc = MockMvcBuilders.standaloneSetup(devCleaningToolResource)
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
    public static DevCleaningTool createEntity(EntityManager em) {
        DevCleaningTool devCleaningTool = new DevCleaningTool()
            .name(DEFAULT_NAME)
            .model(DEFAULT_MODEL)
            .technologyType(DEFAULT_TECHNOLOGY_TYPE)
            .trl(DEFAULT_TRL)
            .photo(DEFAULT_PHOTO)
            .photoContentType(DEFAULT_PHOTO_CONTENT_TYPE)
            .datasheet(DEFAULT_DATASHEET)
            .datasheetContentType(DEFAULT_DATASHEET_CONTENT_TYPE)
            .trammingCapacity(DEFAULT_TRAMMING_CAPACITY)
            .testedTrammingCapacity(DEFAULT_TESTED_TRAMMING_CAPACITY)
            .height(DEFAULT_HEIGHT)
            .testedHeight(DEFAULT_TESTED_HEIGHT)
            .weight(DEFAULT_WEIGHT)
            .testedWeight(DEFAULT_TESTED_WEIGHT)
            .length(DEFAULT_LENGTH)
            .testedLength(DEFAULT_TESTED_LENGTH)
            .rpmOutput(DEFAULT_RPM_OUTPUT)
            .testedRpmOutput(DEFAULT_TESTED_RPM_OUTPUT)
            .torque(DEFAULT_TORQUE)
            .testedTorque(DEFAULT_TESTED_TORQUE)
            .tankRange(DEFAULT_TANK_RANGE)
            .testedTankRange(DEFAULT_TESTED_TANK_RANGE)
            .fuelConsumption(DEFAULT_FUEL_CONSUMPTION)
            .testedFuelConsumption(DEFAULT_TESTED_FUEL_CONSUMPTION)
            .availability(DEFAULT_AVAILABILITY)
            .testedAvailability(DEFAULT_TESTED_AVAILABILITY)
            .operatingCostPerTonne(DEFAULT_OPERATING_COST_PER_TONNE)
            .testedOperatingCostPerTonne(DEFAULT_TESTED_OPERATING_COST_PER_TONNE)
            .fuelConsumptionPerTonneHauled(DEFAULT_FUEL_CONSUMPTION_PER_TONNE_HAULED)
            .testFuelConsumptionPerTonneHauled(DEFAULT_TEST_FUEL_CONSUMPTION_PER_TONNE_HAULED)
            .controlSystem(DEFAULT_CONTROL_SYSTEM)
            .testedControlSystem(DEFAULT_TESTED_CONTROL_SYSTEM)
            .cycleTime(DEFAULT_CYCLE_TIME)
            .testedCycleTime(DEFAULT_TESTED_CYCLE_TIME)
            .turningRadiusInner(DEFAULT_TURNING_RADIUS_INNER)
            .testedTurningRadiusInner(DEFAULT_TESTED_TURNING_RADIUS_INNER)
            .turningRadiusOuter(DEFAULT_TURNING_RADIUS_OUTER)
            .testedTurningRadiusOuter(DEFAULT_TESTED_TURNING_RADIUS_OUTER)
            .lubricationType(DEFAULT_LUBRICATION_TYPE)
            .testedLubricationType(DEFAULT_TESTED_LUBRICATION_TYPE)
            .temperatureAtAmbient(DEFAULT_TEMPERATURE_AT_AMBIENT)
            .testedTemperatureAtAmbient(DEFAULT_TESTED_TEMPERATURE_AT_AMBIENT)
            .observations1(DEFAULT_OBSERVATIONS_1)
            .testedObservations1(DEFAULT_TESTED_OBSERVATIONS_1)
            .observations2(DEFAULT_OBSERVATIONS_2)
            .testedObservations2(DEFAULT_TESTED_OBSERVATIONS_2)
            .observations3(DEFAULT_OBSERVATIONS_3)
            .testedObservations3(DEFAULT_TESTED_OBSERVATIONS_3)
            .observations4(DEFAULT_OBSERVATIONS_4)
            .testedObservations4(DEFAULT_TESTED_OBSERVATIONS_4)
            .observations5(DEFAULT_OBSERVATIONS_5)
            .testedObservations5(DEFAULT_TESTED_OBSERVATIONS_5)
            .observations6(DEFAULT_OBSERVATIONS_6)
            .testedObservations6(DEFAULT_TESTED_OBSERVATIONS_6)
            .operators_Comments(DEFAULT_OPERATORS_COMMENTS)
            .testedOperators_Comments(DEFAULT_TESTED_OPERATORS_COMMENTS);
        return devCleaningTool;
    }

    @Before
    public void initTest() {
        devCleaningToolSearchRepository.deleteAll();
        devCleaningTool = createEntity(em);
    }

    @Test
    @Transactional
    public void createDevCleaningTool() throws Exception {
        int databaseSizeBeforeCreate = devCleaningToolRepository.findAll().size();

        // Create the DevCleaningTool
        restDevCleaningToolMockMvc.perform(post("/api/dev-cleaning-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(devCleaningTool)))
            .andExpect(status().isCreated());

        // Validate the DevCleaningTool in the database
        List<DevCleaningTool> devCleaningToolList = devCleaningToolRepository.findAll();
        assertThat(devCleaningToolList).hasSize(databaseSizeBeforeCreate + 1);
        DevCleaningTool testDevCleaningTool = devCleaningToolList.get(devCleaningToolList.size() - 1);
        assertThat(testDevCleaningTool.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testDevCleaningTool.getModel()).isEqualTo(DEFAULT_MODEL);
        assertThat(testDevCleaningTool.getTechnologyType()).isEqualTo(DEFAULT_TECHNOLOGY_TYPE);
        assertThat(testDevCleaningTool.getTrl()).isEqualTo(DEFAULT_TRL);
        assertThat(testDevCleaningTool.getPhoto()).isEqualTo(DEFAULT_PHOTO);
        assertThat(testDevCleaningTool.getPhotoContentType()).isEqualTo(DEFAULT_PHOTO_CONTENT_TYPE);
        assertThat(testDevCleaningTool.getDatasheet()).isEqualTo(DEFAULT_DATASHEET);
        assertThat(testDevCleaningTool.getDatasheetContentType()).isEqualTo(DEFAULT_DATASHEET_CONTENT_TYPE);
        assertThat(testDevCleaningTool.getTrammingCapacity()).isEqualTo(DEFAULT_TRAMMING_CAPACITY);
        assertThat(testDevCleaningTool.getTestedTrammingCapacity()).isEqualTo(DEFAULT_TESTED_TRAMMING_CAPACITY);
        assertThat(testDevCleaningTool.getHeight()).isEqualTo(DEFAULT_HEIGHT);
        assertThat(testDevCleaningTool.getTestedHeight()).isEqualTo(DEFAULT_TESTED_HEIGHT);
        assertThat(testDevCleaningTool.getWeight()).isEqualTo(DEFAULT_WEIGHT);
        assertThat(testDevCleaningTool.getTestedWeight()).isEqualTo(DEFAULT_TESTED_WEIGHT);
        assertThat(testDevCleaningTool.getLength()).isEqualTo(DEFAULT_LENGTH);
        assertThat(testDevCleaningTool.getTestedLength()).isEqualTo(DEFAULT_TESTED_LENGTH);
        assertThat(testDevCleaningTool.getRpmOutput()).isEqualTo(DEFAULT_RPM_OUTPUT);
        assertThat(testDevCleaningTool.getTestedRpmOutput()).isEqualTo(DEFAULT_TESTED_RPM_OUTPUT);
        assertThat(testDevCleaningTool.getTorque()).isEqualTo(DEFAULT_TORQUE);
        assertThat(testDevCleaningTool.getTestedTorque()).isEqualTo(DEFAULT_TESTED_TORQUE);
        assertThat(testDevCleaningTool.getTankRange()).isEqualTo(DEFAULT_TANK_RANGE);
        assertThat(testDevCleaningTool.getTestedTankRange()).isEqualTo(DEFAULT_TESTED_TANK_RANGE);
        assertThat(testDevCleaningTool.getFuelConsumption()).isEqualTo(DEFAULT_FUEL_CONSUMPTION);
        assertThat(testDevCleaningTool.getTestedFuelConsumption()).isEqualTo(DEFAULT_TESTED_FUEL_CONSUMPTION);
        assertThat(testDevCleaningTool.getAvailability()).isEqualTo(DEFAULT_AVAILABILITY);
        assertThat(testDevCleaningTool.getTestedAvailability()).isEqualTo(DEFAULT_TESTED_AVAILABILITY);
        assertThat(testDevCleaningTool.getOperatingCostPerTonne()).isEqualTo(DEFAULT_OPERATING_COST_PER_TONNE);
        assertThat(testDevCleaningTool.getTestedOperatingCostPerTonne()).isEqualTo(DEFAULT_TESTED_OPERATING_COST_PER_TONNE);
        assertThat(testDevCleaningTool.getFuelConsumptionPerTonneHauled()).isEqualTo(DEFAULT_FUEL_CONSUMPTION_PER_TONNE_HAULED);
        assertThat(testDevCleaningTool.getTestFuelConsumptionPerTonneHauled()).isEqualTo(DEFAULT_TEST_FUEL_CONSUMPTION_PER_TONNE_HAULED);
        assertThat(testDevCleaningTool.getControlSystem()).isEqualTo(DEFAULT_CONTROL_SYSTEM);
        assertThat(testDevCleaningTool.getTestedControlSystem()).isEqualTo(DEFAULT_TESTED_CONTROL_SYSTEM);
        assertThat(testDevCleaningTool.getCycleTime()).isEqualTo(DEFAULT_CYCLE_TIME);
        assertThat(testDevCleaningTool.getTestedCycleTime()).isEqualTo(DEFAULT_TESTED_CYCLE_TIME);
        assertThat(testDevCleaningTool.getTurningRadiusInner()).isEqualTo(DEFAULT_TURNING_RADIUS_INNER);
        assertThat(testDevCleaningTool.getTestedTurningRadiusInner()).isEqualTo(DEFAULT_TESTED_TURNING_RADIUS_INNER);
        assertThat(testDevCleaningTool.getTurningRadiusOuter()).isEqualTo(DEFAULT_TURNING_RADIUS_OUTER);
        assertThat(testDevCleaningTool.getTestedTurningRadiusOuter()).isEqualTo(DEFAULT_TESTED_TURNING_RADIUS_OUTER);
        assertThat(testDevCleaningTool.getLubricationType()).isEqualTo(DEFAULT_LUBRICATION_TYPE);
        assertThat(testDevCleaningTool.getTestedLubricationType()).isEqualTo(DEFAULT_TESTED_LUBRICATION_TYPE);
        assertThat(testDevCleaningTool.getTemperatureAtAmbient()).isEqualTo(DEFAULT_TEMPERATURE_AT_AMBIENT);
        assertThat(testDevCleaningTool.getTestedTemperatureAtAmbient()).isEqualTo(DEFAULT_TESTED_TEMPERATURE_AT_AMBIENT);
        assertThat(testDevCleaningTool.getObservations1()).isEqualTo(DEFAULT_OBSERVATIONS_1);
        assertThat(testDevCleaningTool.getTestedObservations1()).isEqualTo(DEFAULT_TESTED_OBSERVATIONS_1);
        assertThat(testDevCleaningTool.getObservations2()).isEqualTo(DEFAULT_OBSERVATIONS_2);
        assertThat(testDevCleaningTool.getTestedObservations2()).isEqualTo(DEFAULT_TESTED_OBSERVATIONS_2);
        assertThat(testDevCleaningTool.getObservations3()).isEqualTo(DEFAULT_OBSERVATIONS_3);
        assertThat(testDevCleaningTool.getTestedObservations3()).isEqualTo(DEFAULT_TESTED_OBSERVATIONS_3);
        assertThat(testDevCleaningTool.getObservations4()).isEqualTo(DEFAULT_OBSERVATIONS_4);
        assertThat(testDevCleaningTool.getTestedObservations4()).isEqualTo(DEFAULT_TESTED_OBSERVATIONS_4);
        assertThat(testDevCleaningTool.getObservations5()).isEqualTo(DEFAULT_OBSERVATIONS_5);
        assertThat(testDevCleaningTool.getTestedObservations5()).isEqualTo(DEFAULT_TESTED_OBSERVATIONS_5);
        assertThat(testDevCleaningTool.getObservations6()).isEqualTo(DEFAULT_OBSERVATIONS_6);
        assertThat(testDevCleaningTool.getTestedObservations6()).isEqualTo(DEFAULT_TESTED_OBSERVATIONS_6);
        assertThat(testDevCleaningTool.getOperators_Comments()).isEqualTo(DEFAULT_OPERATORS_COMMENTS);
        assertThat(testDevCleaningTool.getTestedOperators_Comments()).isEqualTo(DEFAULT_TESTED_OPERATORS_COMMENTS);

        // Validate the DevCleaningTool in Elasticsearch
        DevCleaningTool devCleaningToolEs = devCleaningToolSearchRepository.findOne(testDevCleaningTool.getId());
        assertThat(devCleaningToolEs).isEqualToComparingFieldByField(testDevCleaningTool);
    }

    @Test
    @Transactional
    public void createDevCleaningToolWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = devCleaningToolRepository.findAll().size();

        // Create the DevCleaningTool with an existing ID
        devCleaningTool.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDevCleaningToolMockMvc.perform(post("/api/dev-cleaning-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(devCleaningTool)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<DevCleaningTool> devCleaningToolList = devCleaningToolRepository.findAll();
        assertThat(devCleaningToolList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = devCleaningToolRepository.findAll().size();
        // set the field null
        devCleaningTool.setName(null);

        // Create the DevCleaningTool, which fails.

        restDevCleaningToolMockMvc.perform(post("/api/dev-cleaning-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(devCleaningTool)))
            .andExpect(status().isBadRequest());

        List<DevCleaningTool> devCleaningToolList = devCleaningToolRepository.findAll();
        assertThat(devCleaningToolList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkModelIsRequired() throws Exception {
        int databaseSizeBeforeTest = devCleaningToolRepository.findAll().size();
        // set the field null
        devCleaningTool.setModel(null);

        // Create the DevCleaningTool, which fails.

        restDevCleaningToolMockMvc.perform(post("/api/dev-cleaning-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(devCleaningTool)))
            .andExpect(status().isBadRequest());

        List<DevCleaningTool> devCleaningToolList = devCleaningToolRepository.findAll();
        assertThat(devCleaningToolList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTechnologyTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = devCleaningToolRepository.findAll().size();
        // set the field null
        devCleaningTool.setTechnologyType(null);

        // Create the DevCleaningTool, which fails.

        restDevCleaningToolMockMvc.perform(post("/api/dev-cleaning-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(devCleaningTool)))
            .andExpect(status().isBadRequest());

        List<DevCleaningTool> devCleaningToolList = devCleaningToolRepository.findAll();
        assertThat(devCleaningToolList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTrlIsRequired() throws Exception {
        int databaseSizeBeforeTest = devCleaningToolRepository.findAll().size();
        // set the field null
        devCleaningTool.setTrl(null);

        // Create the DevCleaningTool, which fails.

        restDevCleaningToolMockMvc.perform(post("/api/dev-cleaning-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(devCleaningTool)))
            .andExpect(status().isBadRequest());

        List<DevCleaningTool> devCleaningToolList = devCleaningToolRepository.findAll();
        assertThat(devCleaningToolList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTrammingCapacityIsRequired() throws Exception {
        int databaseSizeBeforeTest = devCleaningToolRepository.findAll().size();
        // set the field null
        devCleaningTool.setTrammingCapacity(null);

        // Create the DevCleaningTool, which fails.

        restDevCleaningToolMockMvc.perform(post("/api/dev-cleaning-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(devCleaningTool)))
            .andExpect(status().isBadRequest());

        List<DevCleaningTool> devCleaningToolList = devCleaningToolRepository.findAll();
        assertThat(devCleaningToolList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkHeightIsRequired() throws Exception {
        int databaseSizeBeforeTest = devCleaningToolRepository.findAll().size();
        // set the field null
        devCleaningTool.setHeight(null);

        // Create the DevCleaningTool, which fails.

        restDevCleaningToolMockMvc.perform(post("/api/dev-cleaning-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(devCleaningTool)))
            .andExpect(status().isBadRequest());

        List<DevCleaningTool> devCleaningToolList = devCleaningToolRepository.findAll();
        assertThat(devCleaningToolList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkWeightIsRequired() throws Exception {
        int databaseSizeBeforeTest = devCleaningToolRepository.findAll().size();
        // set the field null
        devCleaningTool.setWeight(null);

        // Create the DevCleaningTool, which fails.

        restDevCleaningToolMockMvc.perform(post("/api/dev-cleaning-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(devCleaningTool)))
            .andExpect(status().isBadRequest());

        List<DevCleaningTool> devCleaningToolList = devCleaningToolRepository.findAll();
        assertThat(devCleaningToolList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLengthIsRequired() throws Exception {
        int databaseSizeBeforeTest = devCleaningToolRepository.findAll().size();
        // set the field null
        devCleaningTool.setLength(null);

        // Create the DevCleaningTool, which fails.

        restDevCleaningToolMockMvc.perform(post("/api/dev-cleaning-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(devCleaningTool)))
            .andExpect(status().isBadRequest());

        List<DevCleaningTool> devCleaningToolList = devCleaningToolRepository.findAll();
        assertThat(devCleaningToolList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTorqueIsRequired() throws Exception {
        int databaseSizeBeforeTest = devCleaningToolRepository.findAll().size();
        // set the field null
        devCleaningTool.setTorque(null);

        // Create the DevCleaningTool, which fails.

        restDevCleaningToolMockMvc.perform(post("/api/dev-cleaning-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(devCleaningTool)))
            .andExpect(status().isBadRequest());

        List<DevCleaningTool> devCleaningToolList = devCleaningToolRepository.findAll();
        assertThat(devCleaningToolList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFuelConsumptionIsRequired() throws Exception {
        int databaseSizeBeforeTest = devCleaningToolRepository.findAll().size();
        // set the field null
        devCleaningTool.setFuelConsumption(null);

        // Create the DevCleaningTool, which fails.

        restDevCleaningToolMockMvc.perform(post("/api/dev-cleaning-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(devCleaningTool)))
            .andExpect(status().isBadRequest());

        List<DevCleaningTool> devCleaningToolList = devCleaningToolRepository.findAll();
        assertThat(devCleaningToolList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAvailabilityIsRequired() throws Exception {
        int databaseSizeBeforeTest = devCleaningToolRepository.findAll().size();
        // set the field null
        devCleaningTool.setAvailability(null);

        // Create the DevCleaningTool, which fails.

        restDevCleaningToolMockMvc.perform(post("/api/dev-cleaning-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(devCleaningTool)))
            .andExpect(status().isBadRequest());

        List<DevCleaningTool> devCleaningToolList = devCleaningToolRepository.findAll();
        assertThat(devCleaningToolList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkOperatingCostPerTonneIsRequired() throws Exception {
        int databaseSizeBeforeTest = devCleaningToolRepository.findAll().size();
        // set the field null
        devCleaningTool.setOperatingCostPerTonne(null);

        // Create the DevCleaningTool, which fails.

        restDevCleaningToolMockMvc.perform(post("/api/dev-cleaning-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(devCleaningTool)))
            .andExpect(status().isBadRequest());

        List<DevCleaningTool> devCleaningToolList = devCleaningToolRepository.findAll();
        assertThat(devCleaningToolList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkControlSystemIsRequired() throws Exception {
        int databaseSizeBeforeTest = devCleaningToolRepository.findAll().size();
        // set the field null
        devCleaningTool.setControlSystem(null);

        // Create the DevCleaningTool, which fails.

        restDevCleaningToolMockMvc.perform(post("/api/dev-cleaning-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(devCleaningTool)))
            .andExpect(status().isBadRequest());

        List<DevCleaningTool> devCleaningToolList = devCleaningToolRepository.findAll();
        assertThat(devCleaningToolList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTurningRadiusInnerIsRequired() throws Exception {
        int databaseSizeBeforeTest = devCleaningToolRepository.findAll().size();
        // set the field null
        devCleaningTool.setTurningRadiusInner(null);

        // Create the DevCleaningTool, which fails.

        restDevCleaningToolMockMvc.perform(post("/api/dev-cleaning-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(devCleaningTool)))
            .andExpect(status().isBadRequest());

        List<DevCleaningTool> devCleaningToolList = devCleaningToolRepository.findAll();
        assertThat(devCleaningToolList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTurningRadiusOuterIsRequired() throws Exception {
        int databaseSizeBeforeTest = devCleaningToolRepository.findAll().size();
        // set the field null
        devCleaningTool.setTurningRadiusOuter(null);

        // Create the DevCleaningTool, which fails.

        restDevCleaningToolMockMvc.perform(post("/api/dev-cleaning-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(devCleaningTool)))
            .andExpect(status().isBadRequest());

        List<DevCleaningTool> devCleaningToolList = devCleaningToolRepository.findAll();
        assertThat(devCleaningToolList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLubricationTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = devCleaningToolRepository.findAll().size();
        // set the field null
        devCleaningTool.setLubricationType(null);

        // Create the DevCleaningTool, which fails.

        restDevCleaningToolMockMvc.perform(post("/api/dev-cleaning-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(devCleaningTool)))
            .andExpect(status().isBadRequest());

        List<DevCleaningTool> devCleaningToolList = devCleaningToolRepository.findAll();
        assertThat(devCleaningToolList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkObservations1IsRequired() throws Exception {
        int databaseSizeBeforeTest = devCleaningToolRepository.findAll().size();
        // set the field null
        devCleaningTool.setObservations1(null);

        // Create the DevCleaningTool, which fails.

        restDevCleaningToolMockMvc.perform(post("/api/dev-cleaning-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(devCleaningTool)))
            .andExpect(status().isBadRequest());

        List<DevCleaningTool> devCleaningToolList = devCleaningToolRepository.findAll();
        assertThat(devCleaningToolList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkObservations2IsRequired() throws Exception {
        int databaseSizeBeforeTest = devCleaningToolRepository.findAll().size();
        // set the field null
        devCleaningTool.setObservations2(null);

        // Create the DevCleaningTool, which fails.

        restDevCleaningToolMockMvc.perform(post("/api/dev-cleaning-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(devCleaningTool)))
            .andExpect(status().isBadRequest());

        List<DevCleaningTool> devCleaningToolList = devCleaningToolRepository.findAll();
        assertThat(devCleaningToolList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkObservations3IsRequired() throws Exception {
        int databaseSizeBeforeTest = devCleaningToolRepository.findAll().size();
        // set the field null
        devCleaningTool.setObservations3(null);

        // Create the DevCleaningTool, which fails.

        restDevCleaningToolMockMvc.perform(post("/api/dev-cleaning-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(devCleaningTool)))
            .andExpect(status().isBadRequest());

        List<DevCleaningTool> devCleaningToolList = devCleaningToolRepository.findAll();
        assertThat(devCleaningToolList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkObservations4IsRequired() throws Exception {
        int databaseSizeBeforeTest = devCleaningToolRepository.findAll().size();
        // set the field null
        devCleaningTool.setObservations4(null);

        // Create the DevCleaningTool, which fails.

        restDevCleaningToolMockMvc.perform(post("/api/dev-cleaning-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(devCleaningTool)))
            .andExpect(status().isBadRequest());

        List<DevCleaningTool> devCleaningToolList = devCleaningToolRepository.findAll();
        assertThat(devCleaningToolList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkObservations5IsRequired() throws Exception {
        int databaseSizeBeforeTest = devCleaningToolRepository.findAll().size();
        // set the field null
        devCleaningTool.setObservations5(null);

        // Create the DevCleaningTool, which fails.

        restDevCleaningToolMockMvc.perform(post("/api/dev-cleaning-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(devCleaningTool)))
            .andExpect(status().isBadRequest());

        List<DevCleaningTool> devCleaningToolList = devCleaningToolRepository.findAll();
        assertThat(devCleaningToolList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkObservations6IsRequired() throws Exception {
        int databaseSizeBeforeTest = devCleaningToolRepository.findAll().size();
        // set the field null
        devCleaningTool.setObservations6(null);

        // Create the DevCleaningTool, which fails.

        restDevCleaningToolMockMvc.perform(post("/api/dev-cleaning-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(devCleaningTool)))
            .andExpect(status().isBadRequest());

        List<DevCleaningTool> devCleaningToolList = devCleaningToolRepository.findAll();
        assertThat(devCleaningToolList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkOperators_CommentsIsRequired() throws Exception {
        int databaseSizeBeforeTest = devCleaningToolRepository.findAll().size();
        // set the field null
        devCleaningTool.setOperators_Comments(null);

        // Create the DevCleaningTool, which fails.

        restDevCleaningToolMockMvc.perform(post("/api/dev-cleaning-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(devCleaningTool)))
            .andExpect(status().isBadRequest());

        List<DevCleaningTool> devCleaningToolList = devCleaningToolRepository.findAll();
        assertThat(devCleaningToolList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllDevCleaningTools() throws Exception {
        // Initialize the database
        devCleaningToolRepository.saveAndFlush(devCleaningTool);

        // Get all the devCleaningToolList
        restDevCleaningToolMockMvc.perform(get("/api/dev-cleaning-tools?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(devCleaningTool.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].model").value(hasItem(DEFAULT_MODEL.toString())))
            .andExpect(jsonPath("$.[*].technologyType").value(hasItem(DEFAULT_TECHNOLOGY_TYPE.toString())))
            .andExpect(jsonPath("$.[*].trl").value(hasItem(DEFAULT_TRL)))
            .andExpect(jsonPath("$.[*].photoContentType").value(hasItem(DEFAULT_PHOTO_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].photo").value(hasItem(Base64Utils.encodeToString(DEFAULT_PHOTO))))
            .andExpect(jsonPath("$.[*].datasheetContentType").value(hasItem(DEFAULT_DATASHEET_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].datasheet").value(hasItem(Base64Utils.encodeToString(DEFAULT_DATASHEET))))
            .andExpect(jsonPath("$.[*].trammingCapacity").value(hasItem(DEFAULT_TRAMMING_CAPACITY.doubleValue())))
            .andExpect(jsonPath("$.[*].testedTrammingCapacity").value(hasItem(DEFAULT_TESTED_TRAMMING_CAPACITY.doubleValue())))
            .andExpect(jsonPath("$.[*].height").value(hasItem(DEFAULT_HEIGHT.doubleValue())))
            .andExpect(jsonPath("$.[*].testedHeight").value(hasItem(DEFAULT_TESTED_HEIGHT.doubleValue())))
            .andExpect(jsonPath("$.[*].weight").value(hasItem(DEFAULT_WEIGHT.doubleValue())))
            .andExpect(jsonPath("$.[*].testedWeight").value(hasItem(DEFAULT_TESTED_WEIGHT.doubleValue())))
            .andExpect(jsonPath("$.[*].length").value(hasItem(DEFAULT_LENGTH.doubleValue())))
            .andExpect(jsonPath("$.[*].testedLength").value(hasItem(DEFAULT_TESTED_LENGTH.doubleValue())))
            .andExpect(jsonPath("$.[*].rpmOutput").value(hasItem(DEFAULT_RPM_OUTPUT.doubleValue())))
            .andExpect(jsonPath("$.[*].testedRpmOutput").value(hasItem(DEFAULT_TESTED_RPM_OUTPUT.doubleValue())))
            .andExpect(jsonPath("$.[*].torque").value(hasItem(DEFAULT_TORQUE.doubleValue())))
            .andExpect(jsonPath("$.[*].testedTorque").value(hasItem(DEFAULT_TESTED_TORQUE.doubleValue())))
            .andExpect(jsonPath("$.[*].tankRange").value(hasItem(DEFAULT_TANK_RANGE.toString())))
            .andExpect(jsonPath("$.[*].testedTankRange").value(hasItem(DEFAULT_TESTED_TANK_RANGE.toString())))
            .andExpect(jsonPath("$.[*].fuelConsumption").value(hasItem(DEFAULT_FUEL_CONSUMPTION.doubleValue())))
            .andExpect(jsonPath("$.[*].testedFuelConsumption").value(hasItem(DEFAULT_TESTED_FUEL_CONSUMPTION.doubleValue())))
            .andExpect(jsonPath("$.[*].availability").value(hasItem(DEFAULT_AVAILABILITY.doubleValue())))
            .andExpect(jsonPath("$.[*].testedAvailability").value(hasItem(DEFAULT_TESTED_AVAILABILITY.doubleValue())))
            .andExpect(jsonPath("$.[*].operatingCostPerTonne").value(hasItem(DEFAULT_OPERATING_COST_PER_TONNE.doubleValue())))
            .andExpect(jsonPath("$.[*].testedOperatingCostPerTonne").value(hasItem(DEFAULT_TESTED_OPERATING_COST_PER_TONNE.doubleValue())))
            .andExpect(jsonPath("$.[*].fuelConsumptionPerTonneHauled").value(hasItem(DEFAULT_FUEL_CONSUMPTION_PER_TONNE_HAULED.doubleValue())))
            .andExpect(jsonPath("$.[*].testFuelConsumptionPerTonneHauled").value(hasItem(DEFAULT_TEST_FUEL_CONSUMPTION_PER_TONNE_HAULED.doubleValue())))
            .andExpect(jsonPath("$.[*].controlSystem").value(hasItem(DEFAULT_CONTROL_SYSTEM.toString())))
            .andExpect(jsonPath("$.[*].testedControlSystem").value(hasItem(DEFAULT_TESTED_CONTROL_SYSTEM.toString())))
            .andExpect(jsonPath("$.[*].cycleTime").value(hasItem(DEFAULT_CYCLE_TIME.doubleValue())))
            .andExpect(jsonPath("$.[*].testedCycleTime").value(hasItem(DEFAULT_TESTED_CYCLE_TIME.doubleValue())))
            .andExpect(jsonPath("$.[*].turningRadiusInner").value(hasItem(DEFAULT_TURNING_RADIUS_INNER.doubleValue())))
            .andExpect(jsonPath("$.[*].testedTurningRadiusInner").value(hasItem(DEFAULT_TESTED_TURNING_RADIUS_INNER.doubleValue())))
            .andExpect(jsonPath("$.[*].turningRadiusOuter").value(hasItem(DEFAULT_TURNING_RADIUS_OUTER.doubleValue())))
            .andExpect(jsonPath("$.[*].testedTurningRadiusOuter").value(hasItem(DEFAULT_TESTED_TURNING_RADIUS_OUTER.doubleValue())))
            .andExpect(jsonPath("$.[*].lubricationType").value(hasItem(DEFAULT_LUBRICATION_TYPE.toString())))
            .andExpect(jsonPath("$.[*].testedLubricationType").value(hasItem(DEFAULT_TESTED_LUBRICATION_TYPE.toString())))
            .andExpect(jsonPath("$.[*].temperatureAtAmbient").value(hasItem(DEFAULT_TEMPERATURE_AT_AMBIENT.doubleValue())))
            .andExpect(jsonPath("$.[*].testedTemperatureAtAmbient").value(hasItem(DEFAULT_TESTED_TEMPERATURE_AT_AMBIENT.doubleValue())))
            .andExpect(jsonPath("$.[*].observations1").value(hasItem(DEFAULT_OBSERVATIONS_1.toString())))
            .andExpect(jsonPath("$.[*].testedObservations1").value(hasItem(DEFAULT_TESTED_OBSERVATIONS_1.toString())))
            .andExpect(jsonPath("$.[*].observations2").value(hasItem(DEFAULT_OBSERVATIONS_2.toString())))
            .andExpect(jsonPath("$.[*].testedObservations2").value(hasItem(DEFAULT_TESTED_OBSERVATIONS_2.toString())))
            .andExpect(jsonPath("$.[*].observations3").value(hasItem(DEFAULT_OBSERVATIONS_3.toString())))
            .andExpect(jsonPath("$.[*].testedObservations3").value(hasItem(DEFAULT_TESTED_OBSERVATIONS_3.toString())))
            .andExpect(jsonPath("$.[*].observations4").value(hasItem(DEFAULT_OBSERVATIONS_4.toString())))
            .andExpect(jsonPath("$.[*].testedObservations4").value(hasItem(DEFAULT_TESTED_OBSERVATIONS_4.toString())))
            .andExpect(jsonPath("$.[*].observations5").value(hasItem(DEFAULT_OBSERVATIONS_5.toString())))
            .andExpect(jsonPath("$.[*].testedObservations5").value(hasItem(DEFAULT_TESTED_OBSERVATIONS_5.toString())))
            .andExpect(jsonPath("$.[*].observations6").value(hasItem(DEFAULT_OBSERVATIONS_6.toString())))
            .andExpect(jsonPath("$.[*].testedObservations6").value(hasItem(DEFAULT_TESTED_OBSERVATIONS_6.toString())))
            .andExpect(jsonPath("$.[*].operators_Comments").value(hasItem(DEFAULT_OPERATORS_COMMENTS.toString())))
            .andExpect(jsonPath("$.[*].testedOperators_Comments").value(hasItem(DEFAULT_TESTED_OPERATORS_COMMENTS.toString())));
    }

    @Test
    @Transactional
    public void getDevCleaningTool() throws Exception {
        // Initialize the database
        devCleaningToolRepository.saveAndFlush(devCleaningTool);

        // Get the devCleaningTool
        restDevCleaningToolMockMvc.perform(get("/api/dev-cleaning-tools/{id}", devCleaningTool.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(devCleaningTool.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.model").value(DEFAULT_MODEL.toString()))
            .andExpect(jsonPath("$.technologyType").value(DEFAULT_TECHNOLOGY_TYPE.toString()))
            .andExpect(jsonPath("$.trl").value(DEFAULT_TRL))
            .andExpect(jsonPath("$.photoContentType").value(DEFAULT_PHOTO_CONTENT_TYPE))
            .andExpect(jsonPath("$.photo").value(Base64Utils.encodeToString(DEFAULT_PHOTO)))
            .andExpect(jsonPath("$.datasheetContentType").value(DEFAULT_DATASHEET_CONTENT_TYPE))
            .andExpect(jsonPath("$.datasheet").value(Base64Utils.encodeToString(DEFAULT_DATASHEET)))
            .andExpect(jsonPath("$.trammingCapacity").value(DEFAULT_TRAMMING_CAPACITY.doubleValue()))
            .andExpect(jsonPath("$.testedTrammingCapacity").value(DEFAULT_TESTED_TRAMMING_CAPACITY.doubleValue()))
            .andExpect(jsonPath("$.height").value(DEFAULT_HEIGHT.doubleValue()))
            .andExpect(jsonPath("$.testedHeight").value(DEFAULT_TESTED_HEIGHT.doubleValue()))
            .andExpect(jsonPath("$.weight").value(DEFAULT_WEIGHT.doubleValue()))
            .andExpect(jsonPath("$.testedWeight").value(DEFAULT_TESTED_WEIGHT.doubleValue()))
            .andExpect(jsonPath("$.length").value(DEFAULT_LENGTH.doubleValue()))
            .andExpect(jsonPath("$.testedLength").value(DEFAULT_TESTED_LENGTH.doubleValue()))
            .andExpect(jsonPath("$.rpmOutput").value(DEFAULT_RPM_OUTPUT.doubleValue()))
            .andExpect(jsonPath("$.testedRpmOutput").value(DEFAULT_TESTED_RPM_OUTPUT.doubleValue()))
            .andExpect(jsonPath("$.torque").value(DEFAULT_TORQUE.doubleValue()))
            .andExpect(jsonPath("$.testedTorque").value(DEFAULT_TESTED_TORQUE.doubleValue()))
            .andExpect(jsonPath("$.tankRange").value(DEFAULT_TANK_RANGE.toString()))
            .andExpect(jsonPath("$.testedTankRange").value(DEFAULT_TESTED_TANK_RANGE.toString()))
            .andExpect(jsonPath("$.fuelConsumption").value(DEFAULT_FUEL_CONSUMPTION.doubleValue()))
            .andExpect(jsonPath("$.testedFuelConsumption").value(DEFAULT_TESTED_FUEL_CONSUMPTION.doubleValue()))
            .andExpect(jsonPath("$.availability").value(DEFAULT_AVAILABILITY.doubleValue()))
            .andExpect(jsonPath("$.testedAvailability").value(DEFAULT_TESTED_AVAILABILITY.doubleValue()))
            .andExpect(jsonPath("$.operatingCostPerTonne").value(DEFAULT_OPERATING_COST_PER_TONNE.doubleValue()))
            .andExpect(jsonPath("$.testedOperatingCostPerTonne").value(DEFAULT_TESTED_OPERATING_COST_PER_TONNE.doubleValue()))
            .andExpect(jsonPath("$.fuelConsumptionPerTonneHauled").value(DEFAULT_FUEL_CONSUMPTION_PER_TONNE_HAULED.doubleValue()))
            .andExpect(jsonPath("$.testFuelConsumptionPerTonneHauled").value(DEFAULT_TEST_FUEL_CONSUMPTION_PER_TONNE_HAULED.doubleValue()))
            .andExpect(jsonPath("$.controlSystem").value(DEFAULT_CONTROL_SYSTEM.toString()))
            .andExpect(jsonPath("$.testedControlSystem").value(DEFAULT_TESTED_CONTROL_SYSTEM.toString()))
            .andExpect(jsonPath("$.cycleTime").value(DEFAULT_CYCLE_TIME.doubleValue()))
            .andExpect(jsonPath("$.testedCycleTime").value(DEFAULT_TESTED_CYCLE_TIME.doubleValue()))
            .andExpect(jsonPath("$.turningRadiusInner").value(DEFAULT_TURNING_RADIUS_INNER.doubleValue()))
            .andExpect(jsonPath("$.testedTurningRadiusInner").value(DEFAULT_TESTED_TURNING_RADIUS_INNER.doubleValue()))
            .andExpect(jsonPath("$.turningRadiusOuter").value(DEFAULT_TURNING_RADIUS_OUTER.doubleValue()))
            .andExpect(jsonPath("$.testedTurningRadiusOuter").value(DEFAULT_TESTED_TURNING_RADIUS_OUTER.doubleValue()))
            .andExpect(jsonPath("$.lubricationType").value(DEFAULT_LUBRICATION_TYPE.toString()))
            .andExpect(jsonPath("$.testedLubricationType").value(DEFAULT_TESTED_LUBRICATION_TYPE.toString()))
            .andExpect(jsonPath("$.temperatureAtAmbient").value(DEFAULT_TEMPERATURE_AT_AMBIENT.doubleValue()))
            .andExpect(jsonPath("$.testedTemperatureAtAmbient").value(DEFAULT_TESTED_TEMPERATURE_AT_AMBIENT.doubleValue()))
            .andExpect(jsonPath("$.observations1").value(DEFAULT_OBSERVATIONS_1.toString()))
            .andExpect(jsonPath("$.testedObservations1").value(DEFAULT_TESTED_OBSERVATIONS_1.toString()))
            .andExpect(jsonPath("$.observations2").value(DEFAULT_OBSERVATIONS_2.toString()))
            .andExpect(jsonPath("$.testedObservations2").value(DEFAULT_TESTED_OBSERVATIONS_2.toString()))
            .andExpect(jsonPath("$.observations3").value(DEFAULT_OBSERVATIONS_3.toString()))
            .andExpect(jsonPath("$.testedObservations3").value(DEFAULT_TESTED_OBSERVATIONS_3.toString()))
            .andExpect(jsonPath("$.observations4").value(DEFAULT_OBSERVATIONS_4.toString()))
            .andExpect(jsonPath("$.testedObservations4").value(DEFAULT_TESTED_OBSERVATIONS_4.toString()))
            .andExpect(jsonPath("$.observations5").value(DEFAULT_OBSERVATIONS_5.toString()))
            .andExpect(jsonPath("$.testedObservations5").value(DEFAULT_TESTED_OBSERVATIONS_5.toString()))
            .andExpect(jsonPath("$.observations6").value(DEFAULT_OBSERVATIONS_6.toString()))
            .andExpect(jsonPath("$.testedObservations6").value(DEFAULT_TESTED_OBSERVATIONS_6.toString()))
            .andExpect(jsonPath("$.operators_Comments").value(DEFAULT_OPERATORS_COMMENTS.toString()))
            .andExpect(jsonPath("$.testedOperators_Comments").value(DEFAULT_TESTED_OPERATORS_COMMENTS.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingDevCleaningTool() throws Exception {
        // Get the devCleaningTool
        restDevCleaningToolMockMvc.perform(get("/api/dev-cleaning-tools/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDevCleaningTool() throws Exception {
        // Initialize the database
        devCleaningToolRepository.saveAndFlush(devCleaningTool);
        devCleaningToolSearchRepository.save(devCleaningTool);
        int databaseSizeBeforeUpdate = devCleaningToolRepository.findAll().size();

        // Update the devCleaningTool
        DevCleaningTool updatedDevCleaningTool = devCleaningToolRepository.findOne(devCleaningTool.getId());
        updatedDevCleaningTool
            .name(UPDATED_NAME)
            .model(UPDATED_MODEL)
            .technologyType(UPDATED_TECHNOLOGY_TYPE)
            .trl(UPDATED_TRL)
            .photo(UPDATED_PHOTO)
            .photoContentType(UPDATED_PHOTO_CONTENT_TYPE)
            .datasheet(UPDATED_DATASHEET)
            .datasheetContentType(UPDATED_DATASHEET_CONTENT_TYPE)
            .trammingCapacity(UPDATED_TRAMMING_CAPACITY)
            .testedTrammingCapacity(UPDATED_TESTED_TRAMMING_CAPACITY)
            .height(UPDATED_HEIGHT)
            .testedHeight(UPDATED_TESTED_HEIGHT)
            .weight(UPDATED_WEIGHT)
            .testedWeight(UPDATED_TESTED_WEIGHT)
            .length(UPDATED_LENGTH)
            .testedLength(UPDATED_TESTED_LENGTH)
            .rpmOutput(UPDATED_RPM_OUTPUT)
            .testedRpmOutput(UPDATED_TESTED_RPM_OUTPUT)
            .torque(UPDATED_TORQUE)
            .testedTorque(UPDATED_TESTED_TORQUE)
            .tankRange(UPDATED_TANK_RANGE)
            .testedTankRange(UPDATED_TESTED_TANK_RANGE)
            .fuelConsumption(UPDATED_FUEL_CONSUMPTION)
            .testedFuelConsumption(UPDATED_TESTED_FUEL_CONSUMPTION)
            .availability(UPDATED_AVAILABILITY)
            .testedAvailability(UPDATED_TESTED_AVAILABILITY)
            .operatingCostPerTonne(UPDATED_OPERATING_COST_PER_TONNE)
            .testedOperatingCostPerTonne(UPDATED_TESTED_OPERATING_COST_PER_TONNE)
            .fuelConsumptionPerTonneHauled(UPDATED_FUEL_CONSUMPTION_PER_TONNE_HAULED)
            .testFuelConsumptionPerTonneHauled(UPDATED_TEST_FUEL_CONSUMPTION_PER_TONNE_HAULED)
            .controlSystem(UPDATED_CONTROL_SYSTEM)
            .testedControlSystem(UPDATED_TESTED_CONTROL_SYSTEM)
            .cycleTime(UPDATED_CYCLE_TIME)
            .testedCycleTime(UPDATED_TESTED_CYCLE_TIME)
            .turningRadiusInner(UPDATED_TURNING_RADIUS_INNER)
            .testedTurningRadiusInner(UPDATED_TESTED_TURNING_RADIUS_INNER)
            .turningRadiusOuter(UPDATED_TURNING_RADIUS_OUTER)
            .testedTurningRadiusOuter(UPDATED_TESTED_TURNING_RADIUS_OUTER)
            .lubricationType(UPDATED_LUBRICATION_TYPE)
            .testedLubricationType(UPDATED_TESTED_LUBRICATION_TYPE)
            .temperatureAtAmbient(UPDATED_TEMPERATURE_AT_AMBIENT)
            .testedTemperatureAtAmbient(UPDATED_TESTED_TEMPERATURE_AT_AMBIENT)
            .observations1(UPDATED_OBSERVATIONS_1)
            .testedObservations1(UPDATED_TESTED_OBSERVATIONS_1)
            .observations2(UPDATED_OBSERVATIONS_2)
            .testedObservations2(UPDATED_TESTED_OBSERVATIONS_2)
            .observations3(UPDATED_OBSERVATIONS_3)
            .testedObservations3(UPDATED_TESTED_OBSERVATIONS_3)
            .observations4(UPDATED_OBSERVATIONS_4)
            .testedObservations4(UPDATED_TESTED_OBSERVATIONS_4)
            .observations5(UPDATED_OBSERVATIONS_5)
            .testedObservations5(UPDATED_TESTED_OBSERVATIONS_5)
            .observations6(UPDATED_OBSERVATIONS_6)
            .testedObservations6(UPDATED_TESTED_OBSERVATIONS_6)
            .operators_Comments(UPDATED_OPERATORS_COMMENTS)
            .testedOperators_Comments(UPDATED_TESTED_OPERATORS_COMMENTS);

        restDevCleaningToolMockMvc.perform(put("/api/dev-cleaning-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedDevCleaningTool)))
            .andExpect(status().isOk());

        // Validate the DevCleaningTool in the database
        List<DevCleaningTool> devCleaningToolList = devCleaningToolRepository.findAll();
        assertThat(devCleaningToolList).hasSize(databaseSizeBeforeUpdate);
        DevCleaningTool testDevCleaningTool = devCleaningToolList.get(devCleaningToolList.size() - 1);
        assertThat(testDevCleaningTool.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testDevCleaningTool.getModel()).isEqualTo(UPDATED_MODEL);
        assertThat(testDevCleaningTool.getTechnologyType()).isEqualTo(UPDATED_TECHNOLOGY_TYPE);
        assertThat(testDevCleaningTool.getTrl()).isEqualTo(UPDATED_TRL);
        assertThat(testDevCleaningTool.getPhoto()).isEqualTo(UPDATED_PHOTO);
        assertThat(testDevCleaningTool.getPhotoContentType()).isEqualTo(UPDATED_PHOTO_CONTENT_TYPE);
        assertThat(testDevCleaningTool.getDatasheet()).isEqualTo(UPDATED_DATASHEET);
        assertThat(testDevCleaningTool.getDatasheetContentType()).isEqualTo(UPDATED_DATASHEET_CONTENT_TYPE);
        assertThat(testDevCleaningTool.getTrammingCapacity()).isEqualTo(UPDATED_TRAMMING_CAPACITY);
        assertThat(testDevCleaningTool.getTestedTrammingCapacity()).isEqualTo(UPDATED_TESTED_TRAMMING_CAPACITY);
        assertThat(testDevCleaningTool.getHeight()).isEqualTo(UPDATED_HEIGHT);
        assertThat(testDevCleaningTool.getTestedHeight()).isEqualTo(UPDATED_TESTED_HEIGHT);
        assertThat(testDevCleaningTool.getWeight()).isEqualTo(UPDATED_WEIGHT);
        assertThat(testDevCleaningTool.getTestedWeight()).isEqualTo(UPDATED_TESTED_WEIGHT);
        assertThat(testDevCleaningTool.getLength()).isEqualTo(UPDATED_LENGTH);
        assertThat(testDevCleaningTool.getTestedLength()).isEqualTo(UPDATED_TESTED_LENGTH);
        assertThat(testDevCleaningTool.getRpmOutput()).isEqualTo(UPDATED_RPM_OUTPUT);
        assertThat(testDevCleaningTool.getTestedRpmOutput()).isEqualTo(UPDATED_TESTED_RPM_OUTPUT);
        assertThat(testDevCleaningTool.getTorque()).isEqualTo(UPDATED_TORQUE);
        assertThat(testDevCleaningTool.getTestedTorque()).isEqualTo(UPDATED_TESTED_TORQUE);
        assertThat(testDevCleaningTool.getTankRange()).isEqualTo(UPDATED_TANK_RANGE);
        assertThat(testDevCleaningTool.getTestedTankRange()).isEqualTo(UPDATED_TESTED_TANK_RANGE);
        assertThat(testDevCleaningTool.getFuelConsumption()).isEqualTo(UPDATED_FUEL_CONSUMPTION);
        assertThat(testDevCleaningTool.getTestedFuelConsumption()).isEqualTo(UPDATED_TESTED_FUEL_CONSUMPTION);
        assertThat(testDevCleaningTool.getAvailability()).isEqualTo(UPDATED_AVAILABILITY);
        assertThat(testDevCleaningTool.getTestedAvailability()).isEqualTo(UPDATED_TESTED_AVAILABILITY);
        assertThat(testDevCleaningTool.getOperatingCostPerTonne()).isEqualTo(UPDATED_OPERATING_COST_PER_TONNE);
        assertThat(testDevCleaningTool.getTestedOperatingCostPerTonne()).isEqualTo(UPDATED_TESTED_OPERATING_COST_PER_TONNE);
        assertThat(testDevCleaningTool.getFuelConsumptionPerTonneHauled()).isEqualTo(UPDATED_FUEL_CONSUMPTION_PER_TONNE_HAULED);
        assertThat(testDevCleaningTool.getTestFuelConsumptionPerTonneHauled()).isEqualTo(UPDATED_TEST_FUEL_CONSUMPTION_PER_TONNE_HAULED);
        assertThat(testDevCleaningTool.getControlSystem()).isEqualTo(UPDATED_CONTROL_SYSTEM);
        assertThat(testDevCleaningTool.getTestedControlSystem()).isEqualTo(UPDATED_TESTED_CONTROL_SYSTEM);
        assertThat(testDevCleaningTool.getCycleTime()).isEqualTo(UPDATED_CYCLE_TIME);
        assertThat(testDevCleaningTool.getTestedCycleTime()).isEqualTo(UPDATED_TESTED_CYCLE_TIME);
        assertThat(testDevCleaningTool.getTurningRadiusInner()).isEqualTo(UPDATED_TURNING_RADIUS_INNER);
        assertThat(testDevCleaningTool.getTestedTurningRadiusInner()).isEqualTo(UPDATED_TESTED_TURNING_RADIUS_INNER);
        assertThat(testDevCleaningTool.getTurningRadiusOuter()).isEqualTo(UPDATED_TURNING_RADIUS_OUTER);
        assertThat(testDevCleaningTool.getTestedTurningRadiusOuter()).isEqualTo(UPDATED_TESTED_TURNING_RADIUS_OUTER);
        assertThat(testDevCleaningTool.getLubricationType()).isEqualTo(UPDATED_LUBRICATION_TYPE);
        assertThat(testDevCleaningTool.getTestedLubricationType()).isEqualTo(UPDATED_TESTED_LUBRICATION_TYPE);
        assertThat(testDevCleaningTool.getTemperatureAtAmbient()).isEqualTo(UPDATED_TEMPERATURE_AT_AMBIENT);
        assertThat(testDevCleaningTool.getTestedTemperatureAtAmbient()).isEqualTo(UPDATED_TESTED_TEMPERATURE_AT_AMBIENT);
        assertThat(testDevCleaningTool.getObservations1()).isEqualTo(UPDATED_OBSERVATIONS_1);
        assertThat(testDevCleaningTool.getTestedObservations1()).isEqualTo(UPDATED_TESTED_OBSERVATIONS_1);
        assertThat(testDevCleaningTool.getObservations2()).isEqualTo(UPDATED_OBSERVATIONS_2);
        assertThat(testDevCleaningTool.getTestedObservations2()).isEqualTo(UPDATED_TESTED_OBSERVATIONS_2);
        assertThat(testDevCleaningTool.getObservations3()).isEqualTo(UPDATED_OBSERVATIONS_3);
        assertThat(testDevCleaningTool.getTestedObservations3()).isEqualTo(UPDATED_TESTED_OBSERVATIONS_3);
        assertThat(testDevCleaningTool.getObservations4()).isEqualTo(UPDATED_OBSERVATIONS_4);
        assertThat(testDevCleaningTool.getTestedObservations4()).isEqualTo(UPDATED_TESTED_OBSERVATIONS_4);
        assertThat(testDevCleaningTool.getObservations5()).isEqualTo(UPDATED_OBSERVATIONS_5);
        assertThat(testDevCleaningTool.getTestedObservations5()).isEqualTo(UPDATED_TESTED_OBSERVATIONS_5);
        assertThat(testDevCleaningTool.getObservations6()).isEqualTo(UPDATED_OBSERVATIONS_6);
        assertThat(testDevCleaningTool.getTestedObservations6()).isEqualTo(UPDATED_TESTED_OBSERVATIONS_6);
        assertThat(testDevCleaningTool.getOperators_Comments()).isEqualTo(UPDATED_OPERATORS_COMMENTS);
        assertThat(testDevCleaningTool.getTestedOperators_Comments()).isEqualTo(UPDATED_TESTED_OPERATORS_COMMENTS);

        // Validate the DevCleaningTool in Elasticsearch
        DevCleaningTool devCleaningToolEs = devCleaningToolSearchRepository.findOne(testDevCleaningTool.getId());
        assertThat(devCleaningToolEs).isEqualToComparingFieldByField(testDevCleaningTool);
    }

    @Test
    @Transactional
    public void updateNonExistingDevCleaningTool() throws Exception {
        int databaseSizeBeforeUpdate = devCleaningToolRepository.findAll().size();

        // Create the DevCleaningTool

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restDevCleaningToolMockMvc.perform(put("/api/dev-cleaning-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(devCleaningTool)))
            .andExpect(status().isCreated());

        // Validate the DevCleaningTool in the database
        List<DevCleaningTool> devCleaningToolList = devCleaningToolRepository.findAll();
        assertThat(devCleaningToolList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteDevCleaningTool() throws Exception {
        // Initialize the database
        devCleaningToolRepository.saveAndFlush(devCleaningTool);
        devCleaningToolSearchRepository.save(devCleaningTool);
        int databaseSizeBeforeDelete = devCleaningToolRepository.findAll().size();

        // Get the devCleaningTool
        restDevCleaningToolMockMvc.perform(delete("/api/dev-cleaning-tools/{id}", devCleaningTool.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate Elasticsearch is empty
        boolean devCleaningToolExistsInEs = devCleaningToolSearchRepository.exists(devCleaningTool.getId());
        assertThat(devCleaningToolExistsInEs).isFalse();

        // Validate the database is empty
        List<DevCleaningTool> devCleaningToolList = devCleaningToolRepository.findAll();
        assertThat(devCleaningToolList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void searchDevCleaningTool() throws Exception {
        // Initialize the database
        devCleaningToolRepository.saveAndFlush(devCleaningTool);
        devCleaningToolSearchRepository.save(devCleaningTool);

        // Search the devCleaningTool
        restDevCleaningToolMockMvc.perform(get("/api/_search/dev-cleaning-tools?query=id:" + devCleaningTool.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(devCleaningTool.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].model").value(hasItem(DEFAULT_MODEL.toString())))
            .andExpect(jsonPath("$.[*].technologyType").value(hasItem(DEFAULT_TECHNOLOGY_TYPE.toString())))
            .andExpect(jsonPath("$.[*].trl").value(hasItem(DEFAULT_TRL)))
            .andExpect(jsonPath("$.[*].photoContentType").value(hasItem(DEFAULT_PHOTO_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].photo").value(hasItem(Base64Utils.encodeToString(DEFAULT_PHOTO))))
            .andExpect(jsonPath("$.[*].datasheetContentType").value(hasItem(DEFAULT_DATASHEET_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].datasheet").value(hasItem(Base64Utils.encodeToString(DEFAULT_DATASHEET))))
            .andExpect(jsonPath("$.[*].trammingCapacity").value(hasItem(DEFAULT_TRAMMING_CAPACITY.doubleValue())))
            .andExpect(jsonPath("$.[*].testedTrammingCapacity").value(hasItem(DEFAULT_TESTED_TRAMMING_CAPACITY.doubleValue())))
            .andExpect(jsonPath("$.[*].height").value(hasItem(DEFAULT_HEIGHT.doubleValue())))
            .andExpect(jsonPath("$.[*].testedHeight").value(hasItem(DEFAULT_TESTED_HEIGHT.doubleValue())))
            .andExpect(jsonPath("$.[*].weight").value(hasItem(DEFAULT_WEIGHT.doubleValue())))
            .andExpect(jsonPath("$.[*].testedWeight").value(hasItem(DEFAULT_TESTED_WEIGHT.doubleValue())))
            .andExpect(jsonPath("$.[*].length").value(hasItem(DEFAULT_LENGTH.doubleValue())))
            .andExpect(jsonPath("$.[*].testedLength").value(hasItem(DEFAULT_TESTED_LENGTH.doubleValue())))
            .andExpect(jsonPath("$.[*].rpmOutput").value(hasItem(DEFAULT_RPM_OUTPUT.doubleValue())))
            .andExpect(jsonPath("$.[*].testedRpmOutput").value(hasItem(DEFAULT_TESTED_RPM_OUTPUT.doubleValue())))
            .andExpect(jsonPath("$.[*].torque").value(hasItem(DEFAULT_TORQUE.doubleValue())))
            .andExpect(jsonPath("$.[*].testedTorque").value(hasItem(DEFAULT_TESTED_TORQUE.doubleValue())))
            .andExpect(jsonPath("$.[*].tankRange").value(hasItem(DEFAULT_TANK_RANGE.toString())))
            .andExpect(jsonPath("$.[*].testedTankRange").value(hasItem(DEFAULT_TESTED_TANK_RANGE.toString())))
            .andExpect(jsonPath("$.[*].fuelConsumption").value(hasItem(DEFAULT_FUEL_CONSUMPTION.doubleValue())))
            .andExpect(jsonPath("$.[*].testedFuelConsumption").value(hasItem(DEFAULT_TESTED_FUEL_CONSUMPTION.doubleValue())))
            .andExpect(jsonPath("$.[*].availability").value(hasItem(DEFAULT_AVAILABILITY.doubleValue())))
            .andExpect(jsonPath("$.[*].testedAvailability").value(hasItem(DEFAULT_TESTED_AVAILABILITY.doubleValue())))
            .andExpect(jsonPath("$.[*].operatingCostPerTonne").value(hasItem(DEFAULT_OPERATING_COST_PER_TONNE.doubleValue())))
            .andExpect(jsonPath("$.[*].testedOperatingCostPerTonne").value(hasItem(DEFAULT_TESTED_OPERATING_COST_PER_TONNE.doubleValue())))
            .andExpect(jsonPath("$.[*].fuelConsumptionPerTonneHauled").value(hasItem(DEFAULT_FUEL_CONSUMPTION_PER_TONNE_HAULED.doubleValue())))
            .andExpect(jsonPath("$.[*].testFuelConsumptionPerTonneHauled").value(hasItem(DEFAULT_TEST_FUEL_CONSUMPTION_PER_TONNE_HAULED.doubleValue())))
            .andExpect(jsonPath("$.[*].controlSystem").value(hasItem(DEFAULT_CONTROL_SYSTEM.toString())))
            .andExpect(jsonPath("$.[*].testedControlSystem").value(hasItem(DEFAULT_TESTED_CONTROL_SYSTEM.toString())))
            .andExpect(jsonPath("$.[*].cycleTime").value(hasItem(DEFAULT_CYCLE_TIME.doubleValue())))
            .andExpect(jsonPath("$.[*].testedCycleTime").value(hasItem(DEFAULT_TESTED_CYCLE_TIME.doubleValue())))
            .andExpect(jsonPath("$.[*].turningRadiusInner").value(hasItem(DEFAULT_TURNING_RADIUS_INNER.doubleValue())))
            .andExpect(jsonPath("$.[*].testedTurningRadiusInner").value(hasItem(DEFAULT_TESTED_TURNING_RADIUS_INNER.doubleValue())))
            .andExpect(jsonPath("$.[*].turningRadiusOuter").value(hasItem(DEFAULT_TURNING_RADIUS_OUTER.doubleValue())))
            .andExpect(jsonPath("$.[*].testedTurningRadiusOuter").value(hasItem(DEFAULT_TESTED_TURNING_RADIUS_OUTER.doubleValue())))
            .andExpect(jsonPath("$.[*].lubricationType").value(hasItem(DEFAULT_LUBRICATION_TYPE.toString())))
            .andExpect(jsonPath("$.[*].testedLubricationType").value(hasItem(DEFAULT_TESTED_LUBRICATION_TYPE.toString())))
            .andExpect(jsonPath("$.[*].temperatureAtAmbient").value(hasItem(DEFAULT_TEMPERATURE_AT_AMBIENT.doubleValue())))
            .andExpect(jsonPath("$.[*].testedTemperatureAtAmbient").value(hasItem(DEFAULT_TESTED_TEMPERATURE_AT_AMBIENT.doubleValue())))
            .andExpect(jsonPath("$.[*].observations1").value(hasItem(DEFAULT_OBSERVATIONS_1.toString())))
            .andExpect(jsonPath("$.[*].testedObservations1").value(hasItem(DEFAULT_TESTED_OBSERVATIONS_1.toString())))
            .andExpect(jsonPath("$.[*].observations2").value(hasItem(DEFAULT_OBSERVATIONS_2.toString())))
            .andExpect(jsonPath("$.[*].testedObservations2").value(hasItem(DEFAULT_TESTED_OBSERVATIONS_2.toString())))
            .andExpect(jsonPath("$.[*].observations3").value(hasItem(DEFAULT_OBSERVATIONS_3.toString())))
            .andExpect(jsonPath("$.[*].testedObservations3").value(hasItem(DEFAULT_TESTED_OBSERVATIONS_3.toString())))
            .andExpect(jsonPath("$.[*].observations4").value(hasItem(DEFAULT_OBSERVATIONS_4.toString())))
            .andExpect(jsonPath("$.[*].testedObservations4").value(hasItem(DEFAULT_TESTED_OBSERVATIONS_4.toString())))
            .andExpect(jsonPath("$.[*].observations5").value(hasItem(DEFAULT_OBSERVATIONS_5.toString())))
            .andExpect(jsonPath("$.[*].testedObservations5").value(hasItem(DEFAULT_TESTED_OBSERVATIONS_5.toString())))
            .andExpect(jsonPath("$.[*].observations6").value(hasItem(DEFAULT_OBSERVATIONS_6.toString())))
            .andExpect(jsonPath("$.[*].testedObservations6").value(hasItem(DEFAULT_TESTED_OBSERVATIONS_6.toString())))
            .andExpect(jsonPath("$.[*].operators_Comments").value(hasItem(DEFAULT_OPERATORS_COMMENTS.toString())))
            .andExpect(jsonPath("$.[*].testedOperators_Comments").value(hasItem(DEFAULT_TESTED_OPERATORS_COMMENTS.toString())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DevCleaningTool.class);
        DevCleaningTool devCleaningTool1 = new DevCleaningTool();
        devCleaningTool1.setId(1L);
        DevCleaningTool devCleaningTool2 = new DevCleaningTool();
        devCleaningTool2.setId(devCleaningTool1.getId());
        assertThat(devCleaningTool1).isEqualTo(devCleaningTool2);
        devCleaningTool2.setId(2L);
        assertThat(devCleaningTool1).isNotEqualTo(devCleaningTool2);
        devCleaningTool1.setId(null);
        assertThat(devCleaningTool1).isNotEqualTo(devCleaningTool2);
    }
}
