package com.tara.org.web.rest;

import com.tara.org.TaraApp;

import com.tara.org.domain.StopCleaningTool;
import com.tara.org.repository.StopCleaningToolRepository;
import com.tara.org.repository.search.StopCleaningToolSearchRepository;
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
 * Test class for the StopCleaningToolResource REST controller.
 *
 * @see StopCleaningToolResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TaraApp.class)
public class StopCleaningToolResourceIntTest {

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

    private static final Float DEFAULT_TESTED_FUEL_CONSUMPTION_PER_TONNE_HAULED = 1F;
    private static final Float UPDATED_TESTED_FUEL_CONSUMPTION_PER_TONNE_HAULED = 2F;

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
    private StopCleaningToolRepository stopCleaningToolRepository;

    @Autowired
    private StopCleaningToolSearchRepository stopCleaningToolSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restStopCleaningToolMockMvc;

    private StopCleaningTool stopCleaningTool;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final StopCleaningToolResource stopCleaningToolResource = new StopCleaningToolResource(stopCleaningToolRepository, stopCleaningToolSearchRepository);
        this.restStopCleaningToolMockMvc = MockMvcBuilders.standaloneSetup(stopCleaningToolResource)
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
    public static StopCleaningTool createEntity(EntityManager em) {
        StopCleaningTool stopCleaningTool = new StopCleaningTool()
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
            .testedFuelConsumptionPerTonneHauled(DEFAULT_TESTED_FUEL_CONSUMPTION_PER_TONNE_HAULED)
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
        return stopCleaningTool;
    }

    @Before
    public void initTest() {
        stopCleaningToolSearchRepository.deleteAll();
        stopCleaningTool = createEntity(em);
    }

    @Test
    @Transactional
    public void createStopCleaningTool() throws Exception {
        int databaseSizeBeforeCreate = stopCleaningToolRepository.findAll().size();

        // Create the StopCleaningTool
        restStopCleaningToolMockMvc.perform(post("/api/stop-cleaning-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(stopCleaningTool)))
            .andExpect(status().isCreated());

        // Validate the StopCleaningTool in the database
        List<StopCleaningTool> stopCleaningToolList = stopCleaningToolRepository.findAll();
        assertThat(stopCleaningToolList).hasSize(databaseSizeBeforeCreate + 1);
        StopCleaningTool testStopCleaningTool = stopCleaningToolList.get(stopCleaningToolList.size() - 1);
        assertThat(testStopCleaningTool.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testStopCleaningTool.getModel()).isEqualTo(DEFAULT_MODEL);
        assertThat(testStopCleaningTool.getTechnologyType()).isEqualTo(DEFAULT_TECHNOLOGY_TYPE);
        assertThat(testStopCleaningTool.getTrl()).isEqualTo(DEFAULT_TRL);
        assertThat(testStopCleaningTool.getPhoto()).isEqualTo(DEFAULT_PHOTO);
        assertThat(testStopCleaningTool.getPhotoContentType()).isEqualTo(DEFAULT_PHOTO_CONTENT_TYPE);
        assertThat(testStopCleaningTool.getDatasheet()).isEqualTo(DEFAULT_DATASHEET);
        assertThat(testStopCleaningTool.getDatasheetContentType()).isEqualTo(DEFAULT_DATASHEET_CONTENT_TYPE);
        assertThat(testStopCleaningTool.getTrammingCapacity()).isEqualTo(DEFAULT_TRAMMING_CAPACITY);
        assertThat(testStopCleaningTool.getTestedTrammingCapacity()).isEqualTo(DEFAULT_TESTED_TRAMMING_CAPACITY);
        assertThat(testStopCleaningTool.getHeight()).isEqualTo(DEFAULT_HEIGHT);
        assertThat(testStopCleaningTool.getTestedHeight()).isEqualTo(DEFAULT_TESTED_HEIGHT);
        assertThat(testStopCleaningTool.getWeight()).isEqualTo(DEFAULT_WEIGHT);
        assertThat(testStopCleaningTool.getTestedWeight()).isEqualTo(DEFAULT_TESTED_WEIGHT);
        assertThat(testStopCleaningTool.getLength()).isEqualTo(DEFAULT_LENGTH);
        assertThat(testStopCleaningTool.getTestedLength()).isEqualTo(DEFAULT_TESTED_LENGTH);
        assertThat(testStopCleaningTool.getRpmOutput()).isEqualTo(DEFAULT_RPM_OUTPUT);
        assertThat(testStopCleaningTool.getTestedRpmOutput()).isEqualTo(DEFAULT_TESTED_RPM_OUTPUT);
        assertThat(testStopCleaningTool.getTorque()).isEqualTo(DEFAULT_TORQUE);
        assertThat(testStopCleaningTool.getTestedTorque()).isEqualTo(DEFAULT_TESTED_TORQUE);
        assertThat(testStopCleaningTool.getTankRange()).isEqualTo(DEFAULT_TANK_RANGE);
        assertThat(testStopCleaningTool.getTestedTankRange()).isEqualTo(DEFAULT_TESTED_TANK_RANGE);
        assertThat(testStopCleaningTool.getFuelConsumption()).isEqualTo(DEFAULT_FUEL_CONSUMPTION);
        assertThat(testStopCleaningTool.getTestedFuelConsumption()).isEqualTo(DEFAULT_TESTED_FUEL_CONSUMPTION);
        assertThat(testStopCleaningTool.getAvailability()).isEqualTo(DEFAULT_AVAILABILITY);
        assertThat(testStopCleaningTool.getTestedAvailability()).isEqualTo(DEFAULT_TESTED_AVAILABILITY);
        assertThat(testStopCleaningTool.getOperatingCostPerTonne()).isEqualTo(DEFAULT_OPERATING_COST_PER_TONNE);
        assertThat(testStopCleaningTool.getTestedOperatingCostPerTonne()).isEqualTo(DEFAULT_TESTED_OPERATING_COST_PER_TONNE);
        assertThat(testStopCleaningTool.getFuelConsumptionPerTonneHauled()).isEqualTo(DEFAULT_FUEL_CONSUMPTION_PER_TONNE_HAULED);
        assertThat(testStopCleaningTool.getTestedFuelConsumptionPerTonneHauled()).isEqualTo(DEFAULT_TESTED_FUEL_CONSUMPTION_PER_TONNE_HAULED);
        assertThat(testStopCleaningTool.getControlSystem()).isEqualTo(DEFAULT_CONTROL_SYSTEM);
        assertThat(testStopCleaningTool.getTestedControlSystem()).isEqualTo(DEFAULT_TESTED_CONTROL_SYSTEM);
        assertThat(testStopCleaningTool.getCycleTime()).isEqualTo(DEFAULT_CYCLE_TIME);
        assertThat(testStopCleaningTool.getTestedCycleTime()).isEqualTo(DEFAULT_TESTED_CYCLE_TIME);
        assertThat(testStopCleaningTool.getTurningRadiusInner()).isEqualTo(DEFAULT_TURNING_RADIUS_INNER);
        assertThat(testStopCleaningTool.getTestedTurningRadiusInner()).isEqualTo(DEFAULT_TESTED_TURNING_RADIUS_INNER);
        assertThat(testStopCleaningTool.getTurningRadiusOuter()).isEqualTo(DEFAULT_TURNING_RADIUS_OUTER);
        assertThat(testStopCleaningTool.getTestedTurningRadiusOuter()).isEqualTo(DEFAULT_TESTED_TURNING_RADIUS_OUTER);
        assertThat(testStopCleaningTool.getLubricationType()).isEqualTo(DEFAULT_LUBRICATION_TYPE);
        assertThat(testStopCleaningTool.getTestedLubricationType()).isEqualTo(DEFAULT_TESTED_LUBRICATION_TYPE);
        assertThat(testStopCleaningTool.getTemperatureAtAmbient()).isEqualTo(DEFAULT_TEMPERATURE_AT_AMBIENT);
        assertThat(testStopCleaningTool.getTestedTemperatureAtAmbient()).isEqualTo(DEFAULT_TESTED_TEMPERATURE_AT_AMBIENT);
        assertThat(testStopCleaningTool.getObservations1()).isEqualTo(DEFAULT_OBSERVATIONS_1);
        assertThat(testStopCleaningTool.getTestedObservations1()).isEqualTo(DEFAULT_TESTED_OBSERVATIONS_1);
        assertThat(testStopCleaningTool.getObservations2()).isEqualTo(DEFAULT_OBSERVATIONS_2);
        assertThat(testStopCleaningTool.getTestedObservations2()).isEqualTo(DEFAULT_TESTED_OBSERVATIONS_2);
        assertThat(testStopCleaningTool.getObservations3()).isEqualTo(DEFAULT_OBSERVATIONS_3);
        assertThat(testStopCleaningTool.getTestedObservations3()).isEqualTo(DEFAULT_TESTED_OBSERVATIONS_3);
        assertThat(testStopCleaningTool.getObservations4()).isEqualTo(DEFAULT_OBSERVATIONS_4);
        assertThat(testStopCleaningTool.getTestedObservations4()).isEqualTo(DEFAULT_TESTED_OBSERVATIONS_4);
        assertThat(testStopCleaningTool.getObservations5()).isEqualTo(DEFAULT_OBSERVATIONS_5);
        assertThat(testStopCleaningTool.getTestedObservations5()).isEqualTo(DEFAULT_TESTED_OBSERVATIONS_5);
        assertThat(testStopCleaningTool.getObservations6()).isEqualTo(DEFAULT_OBSERVATIONS_6);
        assertThat(testStopCleaningTool.getTestedObservations6()).isEqualTo(DEFAULT_TESTED_OBSERVATIONS_6);
        assertThat(testStopCleaningTool.getOperators_Comments()).isEqualTo(DEFAULT_OPERATORS_COMMENTS);
        assertThat(testStopCleaningTool.getTestedOperators_Comments()).isEqualTo(DEFAULT_TESTED_OPERATORS_COMMENTS);

        // Validate the StopCleaningTool in Elasticsearch
        StopCleaningTool stopCleaningToolEs = stopCleaningToolSearchRepository.findOne(testStopCleaningTool.getId());
        assertThat(stopCleaningToolEs).isEqualToComparingFieldByField(testStopCleaningTool);
    }

    @Test
    @Transactional
    public void createStopCleaningToolWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = stopCleaningToolRepository.findAll().size();

        // Create the StopCleaningTool with an existing ID
        stopCleaningTool.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restStopCleaningToolMockMvc.perform(post("/api/stop-cleaning-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(stopCleaningTool)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<StopCleaningTool> stopCleaningToolList = stopCleaningToolRepository.findAll();
        assertThat(stopCleaningToolList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = stopCleaningToolRepository.findAll().size();
        // set the field null
        stopCleaningTool.setName(null);

        // Create the StopCleaningTool, which fails.

        restStopCleaningToolMockMvc.perform(post("/api/stop-cleaning-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(stopCleaningTool)))
            .andExpect(status().isBadRequest());

        List<StopCleaningTool> stopCleaningToolList = stopCleaningToolRepository.findAll();
        assertThat(stopCleaningToolList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkModelIsRequired() throws Exception {
        int databaseSizeBeforeTest = stopCleaningToolRepository.findAll().size();
        // set the field null
        stopCleaningTool.setModel(null);

        // Create the StopCleaningTool, which fails.

        restStopCleaningToolMockMvc.perform(post("/api/stop-cleaning-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(stopCleaningTool)))
            .andExpect(status().isBadRequest());

        List<StopCleaningTool> stopCleaningToolList = stopCleaningToolRepository.findAll();
        assertThat(stopCleaningToolList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTechnologyTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = stopCleaningToolRepository.findAll().size();
        // set the field null
        stopCleaningTool.setTechnologyType(null);

        // Create the StopCleaningTool, which fails.

        restStopCleaningToolMockMvc.perform(post("/api/stop-cleaning-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(stopCleaningTool)))
            .andExpect(status().isBadRequest());

        List<StopCleaningTool> stopCleaningToolList = stopCleaningToolRepository.findAll();
        assertThat(stopCleaningToolList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTrlIsRequired() throws Exception {
        int databaseSizeBeforeTest = stopCleaningToolRepository.findAll().size();
        // set the field null
        stopCleaningTool.setTrl(null);

        // Create the StopCleaningTool, which fails.

        restStopCleaningToolMockMvc.perform(post("/api/stop-cleaning-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(stopCleaningTool)))
            .andExpect(status().isBadRequest());

        List<StopCleaningTool> stopCleaningToolList = stopCleaningToolRepository.findAll();
        assertThat(stopCleaningToolList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTrammingCapacityIsRequired() throws Exception {
        int databaseSizeBeforeTest = stopCleaningToolRepository.findAll().size();
        // set the field null
        stopCleaningTool.setTrammingCapacity(null);

        // Create the StopCleaningTool, which fails.

        restStopCleaningToolMockMvc.perform(post("/api/stop-cleaning-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(stopCleaningTool)))
            .andExpect(status().isBadRequest());

        List<StopCleaningTool> stopCleaningToolList = stopCleaningToolRepository.findAll();
        assertThat(stopCleaningToolList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkHeightIsRequired() throws Exception {
        int databaseSizeBeforeTest = stopCleaningToolRepository.findAll().size();
        // set the field null
        stopCleaningTool.setHeight(null);

        // Create the StopCleaningTool, which fails.

        restStopCleaningToolMockMvc.perform(post("/api/stop-cleaning-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(stopCleaningTool)))
            .andExpect(status().isBadRequest());

        List<StopCleaningTool> stopCleaningToolList = stopCleaningToolRepository.findAll();
        assertThat(stopCleaningToolList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkWeightIsRequired() throws Exception {
        int databaseSizeBeforeTest = stopCleaningToolRepository.findAll().size();
        // set the field null
        stopCleaningTool.setWeight(null);

        // Create the StopCleaningTool, which fails.

        restStopCleaningToolMockMvc.perform(post("/api/stop-cleaning-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(stopCleaningTool)))
            .andExpect(status().isBadRequest());

        List<StopCleaningTool> stopCleaningToolList = stopCleaningToolRepository.findAll();
        assertThat(stopCleaningToolList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLengthIsRequired() throws Exception {
        int databaseSizeBeforeTest = stopCleaningToolRepository.findAll().size();
        // set the field null
        stopCleaningTool.setLength(null);

        // Create the StopCleaningTool, which fails.

        restStopCleaningToolMockMvc.perform(post("/api/stop-cleaning-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(stopCleaningTool)))
            .andExpect(status().isBadRequest());

        List<StopCleaningTool> stopCleaningToolList = stopCleaningToolRepository.findAll();
        assertThat(stopCleaningToolList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTorqueIsRequired() throws Exception {
        int databaseSizeBeforeTest = stopCleaningToolRepository.findAll().size();
        // set the field null
        stopCleaningTool.setTorque(null);

        // Create the StopCleaningTool, which fails.

        restStopCleaningToolMockMvc.perform(post("/api/stop-cleaning-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(stopCleaningTool)))
            .andExpect(status().isBadRequest());

        List<StopCleaningTool> stopCleaningToolList = stopCleaningToolRepository.findAll();
        assertThat(stopCleaningToolList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFuelConsumptionIsRequired() throws Exception {
        int databaseSizeBeforeTest = stopCleaningToolRepository.findAll().size();
        // set the field null
        stopCleaningTool.setFuelConsumption(null);

        // Create the StopCleaningTool, which fails.

        restStopCleaningToolMockMvc.perform(post("/api/stop-cleaning-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(stopCleaningTool)))
            .andExpect(status().isBadRequest());

        List<StopCleaningTool> stopCleaningToolList = stopCleaningToolRepository.findAll();
        assertThat(stopCleaningToolList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAvailabilityIsRequired() throws Exception {
        int databaseSizeBeforeTest = stopCleaningToolRepository.findAll().size();
        // set the field null
        stopCleaningTool.setAvailability(null);

        // Create the StopCleaningTool, which fails.

        restStopCleaningToolMockMvc.perform(post("/api/stop-cleaning-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(stopCleaningTool)))
            .andExpect(status().isBadRequest());

        List<StopCleaningTool> stopCleaningToolList = stopCleaningToolRepository.findAll();
        assertThat(stopCleaningToolList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkOperatingCostPerTonneIsRequired() throws Exception {
        int databaseSizeBeforeTest = stopCleaningToolRepository.findAll().size();
        // set the field null
        stopCleaningTool.setOperatingCostPerTonne(null);

        // Create the StopCleaningTool, which fails.

        restStopCleaningToolMockMvc.perform(post("/api/stop-cleaning-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(stopCleaningTool)))
            .andExpect(status().isBadRequest());

        List<StopCleaningTool> stopCleaningToolList = stopCleaningToolRepository.findAll();
        assertThat(stopCleaningToolList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTurningRadiusOuterIsRequired() throws Exception {
        int databaseSizeBeforeTest = stopCleaningToolRepository.findAll().size();
        // set the field null
        stopCleaningTool.setTurningRadiusOuter(null);

        // Create the StopCleaningTool, which fails.

        restStopCleaningToolMockMvc.perform(post("/api/stop-cleaning-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(stopCleaningTool)))
            .andExpect(status().isBadRequest());

        List<StopCleaningTool> stopCleaningToolList = stopCleaningToolRepository.findAll();
        assertThat(stopCleaningToolList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLubricationTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = stopCleaningToolRepository.findAll().size();
        // set the field null
        stopCleaningTool.setLubricationType(null);

        // Create the StopCleaningTool, which fails.

        restStopCleaningToolMockMvc.perform(post("/api/stop-cleaning-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(stopCleaningTool)))
            .andExpect(status().isBadRequest());

        List<StopCleaningTool> stopCleaningToolList = stopCleaningToolRepository.findAll();
        assertThat(stopCleaningToolList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkObservations1IsRequired() throws Exception {
        int databaseSizeBeforeTest = stopCleaningToolRepository.findAll().size();
        // set the field null
        stopCleaningTool.setObservations1(null);

        // Create the StopCleaningTool, which fails.

        restStopCleaningToolMockMvc.perform(post("/api/stop-cleaning-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(stopCleaningTool)))
            .andExpect(status().isBadRequest());

        List<StopCleaningTool> stopCleaningToolList = stopCleaningToolRepository.findAll();
        assertThat(stopCleaningToolList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkObservations2IsRequired() throws Exception {
        int databaseSizeBeforeTest = stopCleaningToolRepository.findAll().size();
        // set the field null
        stopCleaningTool.setObservations2(null);

        // Create the StopCleaningTool, which fails.

        restStopCleaningToolMockMvc.perform(post("/api/stop-cleaning-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(stopCleaningTool)))
            .andExpect(status().isBadRequest());

        List<StopCleaningTool> stopCleaningToolList = stopCleaningToolRepository.findAll();
        assertThat(stopCleaningToolList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkObservations3IsRequired() throws Exception {
        int databaseSizeBeforeTest = stopCleaningToolRepository.findAll().size();
        // set the field null
        stopCleaningTool.setObservations3(null);

        // Create the StopCleaningTool, which fails.

        restStopCleaningToolMockMvc.perform(post("/api/stop-cleaning-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(stopCleaningTool)))
            .andExpect(status().isBadRequest());

        List<StopCleaningTool> stopCleaningToolList = stopCleaningToolRepository.findAll();
        assertThat(stopCleaningToolList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkOperators_CommentsIsRequired() throws Exception {
        int databaseSizeBeforeTest = stopCleaningToolRepository.findAll().size();
        // set the field null
        stopCleaningTool.setOperators_Comments(null);

        // Create the StopCleaningTool, which fails.

        restStopCleaningToolMockMvc.perform(post("/api/stop-cleaning-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(stopCleaningTool)))
            .andExpect(status().isBadRequest());

        List<StopCleaningTool> stopCleaningToolList = stopCleaningToolRepository.findAll();
        assertThat(stopCleaningToolList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllStopCleaningTools() throws Exception {
        // Initialize the database
        stopCleaningToolRepository.saveAndFlush(stopCleaningTool);

        // Get all the stopCleaningToolList
        restStopCleaningToolMockMvc.perform(get("/api/stop-cleaning-tools?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(stopCleaningTool.getId().intValue())))
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
            .andExpect(jsonPath("$.[*].testedFuelConsumptionPerTonneHauled").value(hasItem(DEFAULT_TESTED_FUEL_CONSUMPTION_PER_TONNE_HAULED.doubleValue())))
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
    public void getStopCleaningTool() throws Exception {
        // Initialize the database
        stopCleaningToolRepository.saveAndFlush(stopCleaningTool);

        // Get the stopCleaningTool
        restStopCleaningToolMockMvc.perform(get("/api/stop-cleaning-tools/{id}", stopCleaningTool.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(stopCleaningTool.getId().intValue()))
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
            .andExpect(jsonPath("$.testedFuelConsumptionPerTonneHauled").value(DEFAULT_TESTED_FUEL_CONSUMPTION_PER_TONNE_HAULED.doubleValue()))
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
    public void getNonExistingStopCleaningTool() throws Exception {
        // Get the stopCleaningTool
        restStopCleaningToolMockMvc.perform(get("/api/stop-cleaning-tools/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateStopCleaningTool() throws Exception {
        // Initialize the database
        stopCleaningToolRepository.saveAndFlush(stopCleaningTool);
        stopCleaningToolSearchRepository.save(stopCleaningTool);
        int databaseSizeBeforeUpdate = stopCleaningToolRepository.findAll().size();

        // Update the stopCleaningTool
        StopCleaningTool updatedStopCleaningTool = stopCleaningToolRepository.findOne(stopCleaningTool.getId());
        updatedStopCleaningTool
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
            .testedFuelConsumptionPerTonneHauled(UPDATED_TESTED_FUEL_CONSUMPTION_PER_TONNE_HAULED)
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

        restStopCleaningToolMockMvc.perform(put("/api/stop-cleaning-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedStopCleaningTool)))
            .andExpect(status().isOk());

        // Validate the StopCleaningTool in the database
        List<StopCleaningTool> stopCleaningToolList = stopCleaningToolRepository.findAll();
        assertThat(stopCleaningToolList).hasSize(databaseSizeBeforeUpdate);
        StopCleaningTool testStopCleaningTool = stopCleaningToolList.get(stopCleaningToolList.size() - 1);
        assertThat(testStopCleaningTool.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testStopCleaningTool.getModel()).isEqualTo(UPDATED_MODEL);
        assertThat(testStopCleaningTool.getTechnologyType()).isEqualTo(UPDATED_TECHNOLOGY_TYPE);
        assertThat(testStopCleaningTool.getTrl()).isEqualTo(UPDATED_TRL);
        assertThat(testStopCleaningTool.getPhoto()).isEqualTo(UPDATED_PHOTO);
        assertThat(testStopCleaningTool.getPhotoContentType()).isEqualTo(UPDATED_PHOTO_CONTENT_TYPE);
        assertThat(testStopCleaningTool.getDatasheet()).isEqualTo(UPDATED_DATASHEET);
        assertThat(testStopCleaningTool.getDatasheetContentType()).isEqualTo(UPDATED_DATASHEET_CONTENT_TYPE);
        assertThat(testStopCleaningTool.getTrammingCapacity()).isEqualTo(UPDATED_TRAMMING_CAPACITY);
        assertThat(testStopCleaningTool.getTestedTrammingCapacity()).isEqualTo(UPDATED_TESTED_TRAMMING_CAPACITY);
        assertThat(testStopCleaningTool.getHeight()).isEqualTo(UPDATED_HEIGHT);
        assertThat(testStopCleaningTool.getTestedHeight()).isEqualTo(UPDATED_TESTED_HEIGHT);
        assertThat(testStopCleaningTool.getWeight()).isEqualTo(UPDATED_WEIGHT);
        assertThat(testStopCleaningTool.getTestedWeight()).isEqualTo(UPDATED_TESTED_WEIGHT);
        assertThat(testStopCleaningTool.getLength()).isEqualTo(UPDATED_LENGTH);
        assertThat(testStopCleaningTool.getTestedLength()).isEqualTo(UPDATED_TESTED_LENGTH);
        assertThat(testStopCleaningTool.getRpmOutput()).isEqualTo(UPDATED_RPM_OUTPUT);
        assertThat(testStopCleaningTool.getTestedRpmOutput()).isEqualTo(UPDATED_TESTED_RPM_OUTPUT);
        assertThat(testStopCleaningTool.getTorque()).isEqualTo(UPDATED_TORQUE);
        assertThat(testStopCleaningTool.getTestedTorque()).isEqualTo(UPDATED_TESTED_TORQUE);
        assertThat(testStopCleaningTool.getTankRange()).isEqualTo(UPDATED_TANK_RANGE);
        assertThat(testStopCleaningTool.getTestedTankRange()).isEqualTo(UPDATED_TESTED_TANK_RANGE);
        assertThat(testStopCleaningTool.getFuelConsumption()).isEqualTo(UPDATED_FUEL_CONSUMPTION);
        assertThat(testStopCleaningTool.getTestedFuelConsumption()).isEqualTo(UPDATED_TESTED_FUEL_CONSUMPTION);
        assertThat(testStopCleaningTool.getAvailability()).isEqualTo(UPDATED_AVAILABILITY);
        assertThat(testStopCleaningTool.getTestedAvailability()).isEqualTo(UPDATED_TESTED_AVAILABILITY);
        assertThat(testStopCleaningTool.getOperatingCostPerTonne()).isEqualTo(UPDATED_OPERATING_COST_PER_TONNE);
        assertThat(testStopCleaningTool.getTestedOperatingCostPerTonne()).isEqualTo(UPDATED_TESTED_OPERATING_COST_PER_TONNE);
        assertThat(testStopCleaningTool.getFuelConsumptionPerTonneHauled()).isEqualTo(UPDATED_FUEL_CONSUMPTION_PER_TONNE_HAULED);
        assertThat(testStopCleaningTool.getTestedFuelConsumptionPerTonneHauled()).isEqualTo(UPDATED_TESTED_FUEL_CONSUMPTION_PER_TONNE_HAULED);
        assertThat(testStopCleaningTool.getControlSystem()).isEqualTo(UPDATED_CONTROL_SYSTEM);
        assertThat(testStopCleaningTool.getTestedControlSystem()).isEqualTo(UPDATED_TESTED_CONTROL_SYSTEM);
        assertThat(testStopCleaningTool.getCycleTime()).isEqualTo(UPDATED_CYCLE_TIME);
        assertThat(testStopCleaningTool.getTestedCycleTime()).isEqualTo(UPDATED_TESTED_CYCLE_TIME);
        assertThat(testStopCleaningTool.getTurningRadiusInner()).isEqualTo(UPDATED_TURNING_RADIUS_INNER);
        assertThat(testStopCleaningTool.getTestedTurningRadiusInner()).isEqualTo(UPDATED_TESTED_TURNING_RADIUS_INNER);
        assertThat(testStopCleaningTool.getTurningRadiusOuter()).isEqualTo(UPDATED_TURNING_RADIUS_OUTER);
        assertThat(testStopCleaningTool.getTestedTurningRadiusOuter()).isEqualTo(UPDATED_TESTED_TURNING_RADIUS_OUTER);
        assertThat(testStopCleaningTool.getLubricationType()).isEqualTo(UPDATED_LUBRICATION_TYPE);
        assertThat(testStopCleaningTool.getTestedLubricationType()).isEqualTo(UPDATED_TESTED_LUBRICATION_TYPE);
        assertThat(testStopCleaningTool.getTemperatureAtAmbient()).isEqualTo(UPDATED_TEMPERATURE_AT_AMBIENT);
        assertThat(testStopCleaningTool.getTestedTemperatureAtAmbient()).isEqualTo(UPDATED_TESTED_TEMPERATURE_AT_AMBIENT);
        assertThat(testStopCleaningTool.getObservations1()).isEqualTo(UPDATED_OBSERVATIONS_1);
        assertThat(testStopCleaningTool.getTestedObservations1()).isEqualTo(UPDATED_TESTED_OBSERVATIONS_1);
        assertThat(testStopCleaningTool.getObservations2()).isEqualTo(UPDATED_OBSERVATIONS_2);
        assertThat(testStopCleaningTool.getTestedObservations2()).isEqualTo(UPDATED_TESTED_OBSERVATIONS_2);
        assertThat(testStopCleaningTool.getObservations3()).isEqualTo(UPDATED_OBSERVATIONS_3);
        assertThat(testStopCleaningTool.getTestedObservations3()).isEqualTo(UPDATED_TESTED_OBSERVATIONS_3);
        assertThat(testStopCleaningTool.getObservations4()).isEqualTo(UPDATED_OBSERVATIONS_4);
        assertThat(testStopCleaningTool.getTestedObservations4()).isEqualTo(UPDATED_TESTED_OBSERVATIONS_4);
        assertThat(testStopCleaningTool.getObservations5()).isEqualTo(UPDATED_OBSERVATIONS_5);
        assertThat(testStopCleaningTool.getTestedObservations5()).isEqualTo(UPDATED_TESTED_OBSERVATIONS_5);
        assertThat(testStopCleaningTool.getObservations6()).isEqualTo(UPDATED_OBSERVATIONS_6);
        assertThat(testStopCleaningTool.getTestedObservations6()).isEqualTo(UPDATED_TESTED_OBSERVATIONS_6);
        assertThat(testStopCleaningTool.getOperators_Comments()).isEqualTo(UPDATED_OPERATORS_COMMENTS);
        assertThat(testStopCleaningTool.getTestedOperators_Comments()).isEqualTo(UPDATED_TESTED_OPERATORS_COMMENTS);

        // Validate the StopCleaningTool in Elasticsearch
        StopCleaningTool stopCleaningToolEs = stopCleaningToolSearchRepository.findOne(testStopCleaningTool.getId());
        assertThat(stopCleaningToolEs).isEqualToComparingFieldByField(testStopCleaningTool);
    }

    @Test
    @Transactional
    public void updateNonExistingStopCleaningTool() throws Exception {
        int databaseSizeBeforeUpdate = stopCleaningToolRepository.findAll().size();

        // Create the StopCleaningTool

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restStopCleaningToolMockMvc.perform(put("/api/stop-cleaning-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(stopCleaningTool)))
            .andExpect(status().isCreated());

        // Validate the StopCleaningTool in the database
        List<StopCleaningTool> stopCleaningToolList = stopCleaningToolRepository.findAll();
        assertThat(stopCleaningToolList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteStopCleaningTool() throws Exception {
        // Initialize the database
        stopCleaningToolRepository.saveAndFlush(stopCleaningTool);
        stopCleaningToolSearchRepository.save(stopCleaningTool);
        int databaseSizeBeforeDelete = stopCleaningToolRepository.findAll().size();

        // Get the stopCleaningTool
        restStopCleaningToolMockMvc.perform(delete("/api/stop-cleaning-tools/{id}", stopCleaningTool.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate Elasticsearch is empty
        boolean stopCleaningToolExistsInEs = stopCleaningToolSearchRepository.exists(stopCleaningTool.getId());
        assertThat(stopCleaningToolExistsInEs).isFalse();

        // Validate the database is empty
        List<StopCleaningTool> stopCleaningToolList = stopCleaningToolRepository.findAll();
        assertThat(stopCleaningToolList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void searchStopCleaningTool() throws Exception {
        // Initialize the database
        stopCleaningToolRepository.saveAndFlush(stopCleaningTool);
        stopCleaningToolSearchRepository.save(stopCleaningTool);

        // Search the stopCleaningTool
        restStopCleaningToolMockMvc.perform(get("/api/_search/stop-cleaning-tools?query=id:" + stopCleaningTool.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(stopCleaningTool.getId().intValue())))
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
            .andExpect(jsonPath("$.[*].testedFuelConsumptionPerTonneHauled").value(hasItem(DEFAULT_TESTED_FUEL_CONSUMPTION_PER_TONNE_HAULED.doubleValue())))
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
        TestUtil.equalsVerifier(StopCleaningTool.class);
        StopCleaningTool stopCleaningTool1 = new StopCleaningTool();
        stopCleaningTool1.setId(1L);
        StopCleaningTool stopCleaningTool2 = new StopCleaningTool();
        stopCleaningTool2.setId(stopCleaningTool1.getId());
        assertThat(stopCleaningTool1).isEqualTo(stopCleaningTool2);
        stopCleaningTool2.setId(2L);
        assertThat(stopCleaningTool1).isNotEqualTo(stopCleaningTool2);
        stopCleaningTool1.setId(null);
        assertThat(stopCleaningTool1).isNotEqualTo(stopCleaningTool2);
    }
}
