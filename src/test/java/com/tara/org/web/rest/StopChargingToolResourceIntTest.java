package com.tara.org.web.rest;

import com.tara.org.TaraApp;

import com.tara.org.domain.StopChargingTool;
import com.tara.org.repository.StopChargingToolRepository;
import com.tara.org.repository.search.StopChargingToolSearchRepository;
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
 * Test class for the StopChargingToolResource REST controller.
 *
 * @see StopChargingToolResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TaraApp.class)
public class StopChargingToolResourceIntTest {

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

    private static final Float DEFAULT_HOSE_LENGTH = 1F;
    private static final Float UPDATED_HOSE_LENGTH = 2F;

    private static final Float DEFAULT_TESTED_HOSE_LENGTH = 1F;
    private static final Float UPDATED_TESTED_HOSE_LENGTH = 2F;

    private static final Float DEFAULT_HOLE_DIAMETER = 1F;
    private static final Float UPDATED_HOLE_DIAMETER = 2F;

    private static final Float DEFAULT_TESTED_HOLE_DIAMETER = 1F;
    private static final Float UPDATED_TESTED_HOLE_DIAMETER = 2F;

    private static final String DEFAULT_EXPLOSIVE_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_EXPLOSIVE_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_TESTED_EXPLOSIVE_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_TESTED_EXPLOSIVE_TYPE = "BBBBBBBBBB";

    private static final Float DEFAULT_TANK_CAPACITY = 1F;
    private static final Float UPDATED_TANK_CAPACITY = 2F;

    private static final Float DEFAULT_TESTED_TANK_CAPACITY = 1F;
    private static final Float UPDATED_TESTED_TANK_CAPACITY = 2F;

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

    private static final Float DEFAULT_FUEL_CONSUMPTION_PER_EXPLOSIVE_KG_CHARGED = 1F;
    private static final Float UPDATED_FUEL_CONSUMPTION_PER_EXPLOSIVE_KG_CHARGED = 2F;

    private static final Float DEFAULT_TESTED_FUEL_CONSUMPTION_PER_EXPLOSIVE_KG_CHARGED = 1F;
    private static final Float UPDATED_TESTED_FUEL_CONSUMPTION_PER_EXPLOSIVE_KG_CHARGED = 2F;

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
    private StopChargingToolRepository stopChargingToolRepository;

    @Autowired
    private StopChargingToolSearchRepository stopChargingToolSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restStopChargingToolMockMvc;

    private StopChargingTool stopChargingTool;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final StopChargingToolResource stopChargingToolResource = new StopChargingToolResource(stopChargingToolRepository, stopChargingToolSearchRepository);
        this.restStopChargingToolMockMvc = MockMvcBuilders.standaloneSetup(stopChargingToolResource)
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
    public static StopChargingTool createEntity(EntityManager em) {
        StopChargingTool stopChargingTool = new StopChargingTool()
            .name(DEFAULT_NAME)
            .model(DEFAULT_MODEL)
            .technologyType(DEFAULT_TECHNOLOGY_TYPE)
            .trl(DEFAULT_TRL)
            .photo(DEFAULT_PHOTO)
            .photoContentType(DEFAULT_PHOTO_CONTENT_TYPE)
            .datasheet(DEFAULT_DATASHEET)
            .datasheetContentType(DEFAULT_DATASHEET_CONTENT_TYPE)
            .hoseLength(DEFAULT_HOSE_LENGTH)
            .testedHoseLength(DEFAULT_TESTED_HOSE_LENGTH)
            .holeDiameter(DEFAULT_HOLE_DIAMETER)
            .testedHoleDiameter(DEFAULT_TESTED_HOLE_DIAMETER)
            .explosiveType(DEFAULT_EXPLOSIVE_TYPE)
            .testedExplosiveType(DEFAULT_TESTED_EXPLOSIVE_TYPE)
            .TankCapacity(DEFAULT_TANK_CAPACITY)
            .testedTankCapacity(DEFAULT_TESTED_TANK_CAPACITY)
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
            .fuelConsumptionPerExplosiveKgCharged(DEFAULT_FUEL_CONSUMPTION_PER_EXPLOSIVE_KG_CHARGED)
            .testedFuelConsumptionPerExplosiveKgCharged(DEFAULT_TESTED_FUEL_CONSUMPTION_PER_EXPLOSIVE_KG_CHARGED)
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
        return stopChargingTool;
    }

    @Before
    public void initTest() {
        stopChargingToolSearchRepository.deleteAll();
        stopChargingTool = createEntity(em);
    }

    @Test
    @Transactional
    public void createStopChargingTool() throws Exception {
        int databaseSizeBeforeCreate = stopChargingToolRepository.findAll().size();

        // Create the StopChargingTool
        restStopChargingToolMockMvc.perform(post("/api/stop-charging-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(stopChargingTool)))
            .andExpect(status().isCreated());

        // Validate the StopChargingTool in the database
        List<StopChargingTool> stopChargingToolList = stopChargingToolRepository.findAll();
        assertThat(stopChargingToolList).hasSize(databaseSizeBeforeCreate + 1);
        StopChargingTool testStopChargingTool = stopChargingToolList.get(stopChargingToolList.size() - 1);
        assertThat(testStopChargingTool.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testStopChargingTool.getModel()).isEqualTo(DEFAULT_MODEL);
        assertThat(testStopChargingTool.getTechnologyType()).isEqualTo(DEFAULT_TECHNOLOGY_TYPE);
        assertThat(testStopChargingTool.getTrl()).isEqualTo(DEFAULT_TRL);
        assertThat(testStopChargingTool.getPhoto()).isEqualTo(DEFAULT_PHOTO);
        assertThat(testStopChargingTool.getPhotoContentType()).isEqualTo(DEFAULT_PHOTO_CONTENT_TYPE);
        assertThat(testStopChargingTool.getDatasheet()).isEqualTo(DEFAULT_DATASHEET);
        assertThat(testStopChargingTool.getDatasheetContentType()).isEqualTo(DEFAULT_DATASHEET_CONTENT_TYPE);
        assertThat(testStopChargingTool.getHoseLength()).isEqualTo(DEFAULT_HOSE_LENGTH);
        assertThat(testStopChargingTool.getTestedHoseLength()).isEqualTo(DEFAULT_TESTED_HOSE_LENGTH);
        assertThat(testStopChargingTool.getHoleDiameter()).isEqualTo(DEFAULT_HOLE_DIAMETER);
        assertThat(testStopChargingTool.getTestedHoleDiameter()).isEqualTo(DEFAULT_TESTED_HOLE_DIAMETER);
        assertThat(testStopChargingTool.getExplosiveType()).isEqualTo(DEFAULT_EXPLOSIVE_TYPE);
        assertThat(testStopChargingTool.getTestedExplosiveType()).isEqualTo(DEFAULT_TESTED_EXPLOSIVE_TYPE);
        assertThat(testStopChargingTool.getTankCapacity()).isEqualTo(DEFAULT_TANK_CAPACITY);
        assertThat(testStopChargingTool.getTestedTankCapacity()).isEqualTo(DEFAULT_TESTED_TANK_CAPACITY);
        assertThat(testStopChargingTool.getHeight()).isEqualTo(DEFAULT_HEIGHT);
        assertThat(testStopChargingTool.getTestedHeight()).isEqualTo(DEFAULT_TESTED_HEIGHT);
        assertThat(testStopChargingTool.getWeight()).isEqualTo(DEFAULT_WEIGHT);
        assertThat(testStopChargingTool.getTestedWeight()).isEqualTo(DEFAULT_TESTED_WEIGHT);
        assertThat(testStopChargingTool.getLength()).isEqualTo(DEFAULT_LENGTH);
        assertThat(testStopChargingTool.getTestedLength()).isEqualTo(DEFAULT_TESTED_LENGTH);
        assertThat(testStopChargingTool.getRpmOutput()).isEqualTo(DEFAULT_RPM_OUTPUT);
        assertThat(testStopChargingTool.getTestedRpmOutput()).isEqualTo(DEFAULT_TESTED_RPM_OUTPUT);
        assertThat(testStopChargingTool.getTorque()).isEqualTo(DEFAULT_TORQUE);
        assertThat(testStopChargingTool.getTestedTorque()).isEqualTo(DEFAULT_TESTED_TORQUE);
        assertThat(testStopChargingTool.getTankRange()).isEqualTo(DEFAULT_TANK_RANGE);
        assertThat(testStopChargingTool.getTestedTankRange()).isEqualTo(DEFAULT_TESTED_TANK_RANGE);
        assertThat(testStopChargingTool.getFuelConsumption()).isEqualTo(DEFAULT_FUEL_CONSUMPTION);
        assertThat(testStopChargingTool.getTestedFuelConsumption()).isEqualTo(DEFAULT_TESTED_FUEL_CONSUMPTION);
        assertThat(testStopChargingTool.getAvailability()).isEqualTo(DEFAULT_AVAILABILITY);
        assertThat(testStopChargingTool.getTestedAvailability()).isEqualTo(DEFAULT_TESTED_AVAILABILITY);
        assertThat(testStopChargingTool.getOperatingCostPerTonne()).isEqualTo(DEFAULT_OPERATING_COST_PER_TONNE);
        assertThat(testStopChargingTool.getTestedOperatingCostPerTonne()).isEqualTo(DEFAULT_TESTED_OPERATING_COST_PER_TONNE);
        assertThat(testStopChargingTool.getFuelConsumptionPerExplosiveKgCharged()).isEqualTo(DEFAULT_FUEL_CONSUMPTION_PER_EXPLOSIVE_KG_CHARGED);
        assertThat(testStopChargingTool.getTestedFuelConsumptionPerExplosiveKgCharged()).isEqualTo(DEFAULT_TESTED_FUEL_CONSUMPTION_PER_EXPLOSIVE_KG_CHARGED);
        assertThat(testStopChargingTool.getControlSystem()).isEqualTo(DEFAULT_CONTROL_SYSTEM);
        assertThat(testStopChargingTool.getTestedControlSystem()).isEqualTo(DEFAULT_TESTED_CONTROL_SYSTEM);
        assertThat(testStopChargingTool.getCycleTime()).isEqualTo(DEFAULT_CYCLE_TIME);
        assertThat(testStopChargingTool.getTestedCycleTime()).isEqualTo(DEFAULT_TESTED_CYCLE_TIME);
        assertThat(testStopChargingTool.getTurningRadiusInner()).isEqualTo(DEFAULT_TURNING_RADIUS_INNER);
        assertThat(testStopChargingTool.getTestedTurningRadiusInner()).isEqualTo(DEFAULT_TESTED_TURNING_RADIUS_INNER);
        assertThat(testStopChargingTool.getTurningRadiusOuter()).isEqualTo(DEFAULT_TURNING_RADIUS_OUTER);
        assertThat(testStopChargingTool.getTestedTurningRadiusOuter()).isEqualTo(DEFAULT_TESTED_TURNING_RADIUS_OUTER);
        assertThat(testStopChargingTool.getLubricationType()).isEqualTo(DEFAULT_LUBRICATION_TYPE);
        assertThat(testStopChargingTool.getTestedLubricationType()).isEqualTo(DEFAULT_TESTED_LUBRICATION_TYPE);
        assertThat(testStopChargingTool.getTemperatureAtAmbient()).isEqualTo(DEFAULT_TEMPERATURE_AT_AMBIENT);
        assertThat(testStopChargingTool.getTestedTemperatureAtAmbient()).isEqualTo(DEFAULT_TESTED_TEMPERATURE_AT_AMBIENT);
        assertThat(testStopChargingTool.getObservations1()).isEqualTo(DEFAULT_OBSERVATIONS_1);
        assertThat(testStopChargingTool.getTestedObservations1()).isEqualTo(DEFAULT_TESTED_OBSERVATIONS_1);
        assertThat(testStopChargingTool.getObservations2()).isEqualTo(DEFAULT_OBSERVATIONS_2);
        assertThat(testStopChargingTool.getTestedObservations2()).isEqualTo(DEFAULT_TESTED_OBSERVATIONS_2);
        assertThat(testStopChargingTool.getObservations3()).isEqualTo(DEFAULT_OBSERVATIONS_3);
        assertThat(testStopChargingTool.getTestedObservations3()).isEqualTo(DEFAULT_TESTED_OBSERVATIONS_3);
        assertThat(testStopChargingTool.getObservations4()).isEqualTo(DEFAULT_OBSERVATIONS_4);
        assertThat(testStopChargingTool.getTestedObservations4()).isEqualTo(DEFAULT_TESTED_OBSERVATIONS_4);
        assertThat(testStopChargingTool.getObservations5()).isEqualTo(DEFAULT_OBSERVATIONS_5);
        assertThat(testStopChargingTool.getTestedObservations5()).isEqualTo(DEFAULT_TESTED_OBSERVATIONS_5);
        assertThat(testStopChargingTool.getObservations6()).isEqualTo(DEFAULT_OBSERVATIONS_6);
        assertThat(testStopChargingTool.getTestedObservations6()).isEqualTo(DEFAULT_TESTED_OBSERVATIONS_6);
        assertThat(testStopChargingTool.getOperators_Comments()).isEqualTo(DEFAULT_OPERATORS_COMMENTS);
        assertThat(testStopChargingTool.getTestedOperators_Comments()).isEqualTo(DEFAULT_TESTED_OPERATORS_COMMENTS);

        // Validate the StopChargingTool in Elasticsearch
        StopChargingTool stopChargingToolEs = stopChargingToolSearchRepository.findOne(testStopChargingTool.getId());
        assertThat(stopChargingToolEs).isEqualToComparingFieldByField(testStopChargingTool);
    }

    @Test
    @Transactional
    public void createStopChargingToolWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = stopChargingToolRepository.findAll().size();

        // Create the StopChargingTool with an existing ID
        stopChargingTool.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restStopChargingToolMockMvc.perform(post("/api/stop-charging-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(stopChargingTool)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<StopChargingTool> stopChargingToolList = stopChargingToolRepository.findAll();
        assertThat(stopChargingToolList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = stopChargingToolRepository.findAll().size();
        // set the field null
        stopChargingTool.setName(null);

        // Create the StopChargingTool, which fails.

        restStopChargingToolMockMvc.perform(post("/api/stop-charging-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(stopChargingTool)))
            .andExpect(status().isBadRequest());

        List<StopChargingTool> stopChargingToolList = stopChargingToolRepository.findAll();
        assertThat(stopChargingToolList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkModelIsRequired() throws Exception {
        int databaseSizeBeforeTest = stopChargingToolRepository.findAll().size();
        // set the field null
        stopChargingTool.setModel(null);

        // Create the StopChargingTool, which fails.

        restStopChargingToolMockMvc.perform(post("/api/stop-charging-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(stopChargingTool)))
            .andExpect(status().isBadRequest());

        List<StopChargingTool> stopChargingToolList = stopChargingToolRepository.findAll();
        assertThat(stopChargingToolList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTechnologyTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = stopChargingToolRepository.findAll().size();
        // set the field null
        stopChargingTool.setTechnologyType(null);

        // Create the StopChargingTool, which fails.

        restStopChargingToolMockMvc.perform(post("/api/stop-charging-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(stopChargingTool)))
            .andExpect(status().isBadRequest());

        List<StopChargingTool> stopChargingToolList = stopChargingToolRepository.findAll();
        assertThat(stopChargingToolList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTrlIsRequired() throws Exception {
        int databaseSizeBeforeTest = stopChargingToolRepository.findAll().size();
        // set the field null
        stopChargingTool.setTrl(null);

        // Create the StopChargingTool, which fails.

        restStopChargingToolMockMvc.perform(post("/api/stop-charging-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(stopChargingTool)))
            .andExpect(status().isBadRequest());

        List<StopChargingTool> stopChargingToolList = stopChargingToolRepository.findAll();
        assertThat(stopChargingToolList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkHoseLengthIsRequired() throws Exception {
        int databaseSizeBeforeTest = stopChargingToolRepository.findAll().size();
        // set the field null
        stopChargingTool.setHoseLength(null);

        // Create the StopChargingTool, which fails.

        restStopChargingToolMockMvc.perform(post("/api/stop-charging-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(stopChargingTool)))
            .andExpect(status().isBadRequest());

        List<StopChargingTool> stopChargingToolList = stopChargingToolRepository.findAll();
        assertThat(stopChargingToolList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkHoleDiameterIsRequired() throws Exception {
        int databaseSizeBeforeTest = stopChargingToolRepository.findAll().size();
        // set the field null
        stopChargingTool.setHoleDiameter(null);

        // Create the StopChargingTool, which fails.

        restStopChargingToolMockMvc.perform(post("/api/stop-charging-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(stopChargingTool)))
            .andExpect(status().isBadRequest());

        List<StopChargingTool> stopChargingToolList = stopChargingToolRepository.findAll();
        assertThat(stopChargingToolList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkExplosiveTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = stopChargingToolRepository.findAll().size();
        // set the field null
        stopChargingTool.setExplosiveType(null);

        // Create the StopChargingTool, which fails.

        restStopChargingToolMockMvc.perform(post("/api/stop-charging-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(stopChargingTool)))
            .andExpect(status().isBadRequest());

        List<StopChargingTool> stopChargingToolList = stopChargingToolRepository.findAll();
        assertThat(stopChargingToolList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkHeightIsRequired() throws Exception {
        int databaseSizeBeforeTest = stopChargingToolRepository.findAll().size();
        // set the field null
        stopChargingTool.setHeight(null);

        // Create the StopChargingTool, which fails.

        restStopChargingToolMockMvc.perform(post("/api/stop-charging-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(stopChargingTool)))
            .andExpect(status().isBadRequest());

        List<StopChargingTool> stopChargingToolList = stopChargingToolRepository.findAll();
        assertThat(stopChargingToolList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkWeightIsRequired() throws Exception {
        int databaseSizeBeforeTest = stopChargingToolRepository.findAll().size();
        // set the field null
        stopChargingTool.setWeight(null);

        // Create the StopChargingTool, which fails.

        restStopChargingToolMockMvc.perform(post("/api/stop-charging-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(stopChargingTool)))
            .andExpect(status().isBadRequest());

        List<StopChargingTool> stopChargingToolList = stopChargingToolRepository.findAll();
        assertThat(stopChargingToolList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLengthIsRequired() throws Exception {
        int databaseSizeBeforeTest = stopChargingToolRepository.findAll().size();
        // set the field null
        stopChargingTool.setLength(null);

        // Create the StopChargingTool, which fails.

        restStopChargingToolMockMvc.perform(post("/api/stop-charging-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(stopChargingTool)))
            .andExpect(status().isBadRequest());

        List<StopChargingTool> stopChargingToolList = stopChargingToolRepository.findAll();
        assertThat(stopChargingToolList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTorqueIsRequired() throws Exception {
        int databaseSizeBeforeTest = stopChargingToolRepository.findAll().size();
        // set the field null
        stopChargingTool.setTorque(null);

        // Create the StopChargingTool, which fails.

        restStopChargingToolMockMvc.perform(post("/api/stop-charging-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(stopChargingTool)))
            .andExpect(status().isBadRequest());

        List<StopChargingTool> stopChargingToolList = stopChargingToolRepository.findAll();
        assertThat(stopChargingToolList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFuelConsumptionIsRequired() throws Exception {
        int databaseSizeBeforeTest = stopChargingToolRepository.findAll().size();
        // set the field null
        stopChargingTool.setFuelConsumption(null);

        // Create the StopChargingTool, which fails.

        restStopChargingToolMockMvc.perform(post("/api/stop-charging-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(stopChargingTool)))
            .andExpect(status().isBadRequest());

        List<StopChargingTool> stopChargingToolList = stopChargingToolRepository.findAll();
        assertThat(stopChargingToolList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAvailabilityIsRequired() throws Exception {
        int databaseSizeBeforeTest = stopChargingToolRepository.findAll().size();
        // set the field null
        stopChargingTool.setAvailability(null);

        // Create the StopChargingTool, which fails.

        restStopChargingToolMockMvc.perform(post("/api/stop-charging-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(stopChargingTool)))
            .andExpect(status().isBadRequest());

        List<StopChargingTool> stopChargingToolList = stopChargingToolRepository.findAll();
        assertThat(stopChargingToolList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkOperatingCostPerTonneIsRequired() throws Exception {
        int databaseSizeBeforeTest = stopChargingToolRepository.findAll().size();
        // set the field null
        stopChargingTool.setOperatingCostPerTonne(null);

        // Create the StopChargingTool, which fails.

        restStopChargingToolMockMvc.perform(post("/api/stop-charging-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(stopChargingTool)))
            .andExpect(status().isBadRequest());

        List<StopChargingTool> stopChargingToolList = stopChargingToolRepository.findAll();
        assertThat(stopChargingToolList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkControlSystemIsRequired() throws Exception {
        int databaseSizeBeforeTest = stopChargingToolRepository.findAll().size();
        // set the field null
        stopChargingTool.setControlSystem(null);

        // Create the StopChargingTool, which fails.

        restStopChargingToolMockMvc.perform(post("/api/stop-charging-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(stopChargingTool)))
            .andExpect(status().isBadRequest());

        List<StopChargingTool> stopChargingToolList = stopChargingToolRepository.findAll();
        assertThat(stopChargingToolList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTurningRadiusInnerIsRequired() throws Exception {
        int databaseSizeBeforeTest = stopChargingToolRepository.findAll().size();
        // set the field null
        stopChargingTool.setTurningRadiusInner(null);

        // Create the StopChargingTool, which fails.

        restStopChargingToolMockMvc.perform(post("/api/stop-charging-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(stopChargingTool)))
            .andExpect(status().isBadRequest());

        List<StopChargingTool> stopChargingToolList = stopChargingToolRepository.findAll();
        assertThat(stopChargingToolList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTurningRadiusOuterIsRequired() throws Exception {
        int databaseSizeBeforeTest = stopChargingToolRepository.findAll().size();
        // set the field null
        stopChargingTool.setTurningRadiusOuter(null);

        // Create the StopChargingTool, which fails.

        restStopChargingToolMockMvc.perform(post("/api/stop-charging-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(stopChargingTool)))
            .andExpect(status().isBadRequest());

        List<StopChargingTool> stopChargingToolList = stopChargingToolRepository.findAll();
        assertThat(stopChargingToolList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLubricationTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = stopChargingToolRepository.findAll().size();
        // set the field null
        stopChargingTool.setLubricationType(null);

        // Create the StopChargingTool, which fails.

        restStopChargingToolMockMvc.perform(post("/api/stop-charging-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(stopChargingTool)))
            .andExpect(status().isBadRequest());

        List<StopChargingTool> stopChargingToolList = stopChargingToolRepository.findAll();
        assertThat(stopChargingToolList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkObservations1IsRequired() throws Exception {
        int databaseSizeBeforeTest = stopChargingToolRepository.findAll().size();
        // set the field null
        stopChargingTool.setObservations1(null);

        // Create the StopChargingTool, which fails.

        restStopChargingToolMockMvc.perform(post("/api/stop-charging-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(stopChargingTool)))
            .andExpect(status().isBadRequest());

        List<StopChargingTool> stopChargingToolList = stopChargingToolRepository.findAll();
        assertThat(stopChargingToolList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkObservations2IsRequired() throws Exception {
        int databaseSizeBeforeTest = stopChargingToolRepository.findAll().size();
        // set the field null
        stopChargingTool.setObservations2(null);

        // Create the StopChargingTool, which fails.

        restStopChargingToolMockMvc.perform(post("/api/stop-charging-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(stopChargingTool)))
            .andExpect(status().isBadRequest());

        List<StopChargingTool> stopChargingToolList = stopChargingToolRepository.findAll();
        assertThat(stopChargingToolList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkObservations3IsRequired() throws Exception {
        int databaseSizeBeforeTest = stopChargingToolRepository.findAll().size();
        // set the field null
        stopChargingTool.setObservations3(null);

        // Create the StopChargingTool, which fails.

        restStopChargingToolMockMvc.perform(post("/api/stop-charging-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(stopChargingTool)))
            .andExpect(status().isBadRequest());

        List<StopChargingTool> stopChargingToolList = stopChargingToolRepository.findAll();
        assertThat(stopChargingToolList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkObservations4IsRequired() throws Exception {
        int databaseSizeBeforeTest = stopChargingToolRepository.findAll().size();
        // set the field null
        stopChargingTool.setObservations4(null);

        // Create the StopChargingTool, which fails.

        restStopChargingToolMockMvc.perform(post("/api/stop-charging-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(stopChargingTool)))
            .andExpect(status().isBadRequest());

        List<StopChargingTool> stopChargingToolList = stopChargingToolRepository.findAll();
        assertThat(stopChargingToolList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkObservations5IsRequired() throws Exception {
        int databaseSizeBeforeTest = stopChargingToolRepository.findAll().size();
        // set the field null
        stopChargingTool.setObservations5(null);

        // Create the StopChargingTool, which fails.

        restStopChargingToolMockMvc.perform(post("/api/stop-charging-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(stopChargingTool)))
            .andExpect(status().isBadRequest());

        List<StopChargingTool> stopChargingToolList = stopChargingToolRepository.findAll();
        assertThat(stopChargingToolList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkObservations6IsRequired() throws Exception {
        int databaseSizeBeforeTest = stopChargingToolRepository.findAll().size();
        // set the field null
        stopChargingTool.setObservations6(null);

        // Create the StopChargingTool, which fails.

        restStopChargingToolMockMvc.perform(post("/api/stop-charging-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(stopChargingTool)))
            .andExpect(status().isBadRequest());

        List<StopChargingTool> stopChargingToolList = stopChargingToolRepository.findAll();
        assertThat(stopChargingToolList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkOperators_CommentsIsRequired() throws Exception {
        int databaseSizeBeforeTest = stopChargingToolRepository.findAll().size();
        // set the field null
        stopChargingTool.setOperators_Comments(null);

        // Create the StopChargingTool, which fails.

        restStopChargingToolMockMvc.perform(post("/api/stop-charging-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(stopChargingTool)))
            .andExpect(status().isBadRequest());

        List<StopChargingTool> stopChargingToolList = stopChargingToolRepository.findAll();
        assertThat(stopChargingToolList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllStopChargingTools() throws Exception {
        // Initialize the database
        stopChargingToolRepository.saveAndFlush(stopChargingTool);

        // Get all the stopChargingToolList
        restStopChargingToolMockMvc.perform(get("/api/stop-charging-tools?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(stopChargingTool.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].model").value(hasItem(DEFAULT_MODEL.toString())))
            .andExpect(jsonPath("$.[*].technologyType").value(hasItem(DEFAULT_TECHNOLOGY_TYPE.toString())))
            .andExpect(jsonPath("$.[*].trl").value(hasItem(DEFAULT_TRL)))
            .andExpect(jsonPath("$.[*].photoContentType").value(hasItem(DEFAULT_PHOTO_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].photo").value(hasItem(Base64Utils.encodeToString(DEFAULT_PHOTO))))
            .andExpect(jsonPath("$.[*].datasheetContentType").value(hasItem(DEFAULT_DATASHEET_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].datasheet").value(hasItem(Base64Utils.encodeToString(DEFAULT_DATASHEET))))
            .andExpect(jsonPath("$.[*].hoseLength").value(hasItem(DEFAULT_HOSE_LENGTH.doubleValue())))
            .andExpect(jsonPath("$.[*].testedHoseLength").value(hasItem(DEFAULT_TESTED_HOSE_LENGTH.doubleValue())))
            .andExpect(jsonPath("$.[*].holeDiameter").value(hasItem(DEFAULT_HOLE_DIAMETER.doubleValue())))
            .andExpect(jsonPath("$.[*].testedHoleDiameter").value(hasItem(DEFAULT_TESTED_HOLE_DIAMETER.doubleValue())))
            .andExpect(jsonPath("$.[*].explosiveType").value(hasItem(DEFAULT_EXPLOSIVE_TYPE.toString())))
            .andExpect(jsonPath("$.[*].testedExplosiveType").value(hasItem(DEFAULT_TESTED_EXPLOSIVE_TYPE.toString())))
            .andExpect(jsonPath("$.[*].TankCapacity").value(hasItem(DEFAULT_TANK_CAPACITY.doubleValue())))
            .andExpect(jsonPath("$.[*].testedTankCapacity").value(hasItem(DEFAULT_TESTED_TANK_CAPACITY.doubleValue())))
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
            .andExpect(jsonPath("$.[*].fuelConsumptionPerExplosiveKgCharged").value(hasItem(DEFAULT_FUEL_CONSUMPTION_PER_EXPLOSIVE_KG_CHARGED.doubleValue())))
            .andExpect(jsonPath("$.[*].testedFuelConsumptionPerExplosiveKgCharged").value(hasItem(DEFAULT_TESTED_FUEL_CONSUMPTION_PER_EXPLOSIVE_KG_CHARGED.doubleValue())))
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
    public void getStopChargingTool() throws Exception {
        // Initialize the database
        stopChargingToolRepository.saveAndFlush(stopChargingTool);

        // Get the stopChargingTool
        restStopChargingToolMockMvc.perform(get("/api/stop-charging-tools/{id}", stopChargingTool.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(stopChargingTool.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.model").value(DEFAULT_MODEL.toString()))
            .andExpect(jsonPath("$.technologyType").value(DEFAULT_TECHNOLOGY_TYPE.toString()))
            .andExpect(jsonPath("$.trl").value(DEFAULT_TRL))
            .andExpect(jsonPath("$.photoContentType").value(DEFAULT_PHOTO_CONTENT_TYPE))
            .andExpect(jsonPath("$.photo").value(Base64Utils.encodeToString(DEFAULT_PHOTO)))
            .andExpect(jsonPath("$.datasheetContentType").value(DEFAULT_DATASHEET_CONTENT_TYPE))
            .andExpect(jsonPath("$.datasheet").value(Base64Utils.encodeToString(DEFAULT_DATASHEET)))
            .andExpect(jsonPath("$.hoseLength").value(DEFAULT_HOSE_LENGTH.doubleValue()))
            .andExpect(jsonPath("$.testedHoseLength").value(DEFAULT_TESTED_HOSE_LENGTH.doubleValue()))
            .andExpect(jsonPath("$.holeDiameter").value(DEFAULT_HOLE_DIAMETER.doubleValue()))
            .andExpect(jsonPath("$.testedHoleDiameter").value(DEFAULT_TESTED_HOLE_DIAMETER.doubleValue()))
            .andExpect(jsonPath("$.explosiveType").value(DEFAULT_EXPLOSIVE_TYPE.toString()))
            .andExpect(jsonPath("$.testedExplosiveType").value(DEFAULT_TESTED_EXPLOSIVE_TYPE.toString()))
            .andExpect(jsonPath("$.TankCapacity").value(DEFAULT_TANK_CAPACITY.doubleValue()))
            .andExpect(jsonPath("$.testedTankCapacity").value(DEFAULT_TESTED_TANK_CAPACITY.doubleValue()))
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
            .andExpect(jsonPath("$.fuelConsumptionPerExplosiveKgCharged").value(DEFAULT_FUEL_CONSUMPTION_PER_EXPLOSIVE_KG_CHARGED.doubleValue()))
            .andExpect(jsonPath("$.testedFuelConsumptionPerExplosiveKgCharged").value(DEFAULT_TESTED_FUEL_CONSUMPTION_PER_EXPLOSIVE_KG_CHARGED.doubleValue()))
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
    public void getNonExistingStopChargingTool() throws Exception {
        // Get the stopChargingTool
        restStopChargingToolMockMvc.perform(get("/api/stop-charging-tools/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateStopChargingTool() throws Exception {
        // Initialize the database
        stopChargingToolRepository.saveAndFlush(stopChargingTool);
        stopChargingToolSearchRepository.save(stopChargingTool);
        int databaseSizeBeforeUpdate = stopChargingToolRepository.findAll().size();

        // Update the stopChargingTool
        StopChargingTool updatedStopChargingTool = stopChargingToolRepository.findOne(stopChargingTool.getId());
        updatedStopChargingTool
            .name(UPDATED_NAME)
            .model(UPDATED_MODEL)
            .technologyType(UPDATED_TECHNOLOGY_TYPE)
            .trl(UPDATED_TRL)
            .photo(UPDATED_PHOTO)
            .photoContentType(UPDATED_PHOTO_CONTENT_TYPE)
            .datasheet(UPDATED_DATASHEET)
            .datasheetContentType(UPDATED_DATASHEET_CONTENT_TYPE)
            .hoseLength(UPDATED_HOSE_LENGTH)
            .testedHoseLength(UPDATED_TESTED_HOSE_LENGTH)
            .holeDiameter(UPDATED_HOLE_DIAMETER)
            .testedHoleDiameter(UPDATED_TESTED_HOLE_DIAMETER)
            .explosiveType(UPDATED_EXPLOSIVE_TYPE)
            .testedExplosiveType(UPDATED_TESTED_EXPLOSIVE_TYPE)
            .TankCapacity(UPDATED_TANK_CAPACITY)
            .testedTankCapacity(UPDATED_TESTED_TANK_CAPACITY)
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
            .fuelConsumptionPerExplosiveKgCharged(UPDATED_FUEL_CONSUMPTION_PER_EXPLOSIVE_KG_CHARGED)
            .testedFuelConsumptionPerExplosiveKgCharged(UPDATED_TESTED_FUEL_CONSUMPTION_PER_EXPLOSIVE_KG_CHARGED)
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

        restStopChargingToolMockMvc.perform(put("/api/stop-charging-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedStopChargingTool)))
            .andExpect(status().isOk());

        // Validate the StopChargingTool in the database
        List<StopChargingTool> stopChargingToolList = stopChargingToolRepository.findAll();
        assertThat(stopChargingToolList).hasSize(databaseSizeBeforeUpdate);
        StopChargingTool testStopChargingTool = stopChargingToolList.get(stopChargingToolList.size() - 1);
        assertThat(testStopChargingTool.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testStopChargingTool.getModel()).isEqualTo(UPDATED_MODEL);
        assertThat(testStopChargingTool.getTechnologyType()).isEqualTo(UPDATED_TECHNOLOGY_TYPE);
        assertThat(testStopChargingTool.getTrl()).isEqualTo(UPDATED_TRL);
        assertThat(testStopChargingTool.getPhoto()).isEqualTo(UPDATED_PHOTO);
        assertThat(testStopChargingTool.getPhotoContentType()).isEqualTo(UPDATED_PHOTO_CONTENT_TYPE);
        assertThat(testStopChargingTool.getDatasheet()).isEqualTo(UPDATED_DATASHEET);
        assertThat(testStopChargingTool.getDatasheetContentType()).isEqualTo(UPDATED_DATASHEET_CONTENT_TYPE);
        assertThat(testStopChargingTool.getHoseLength()).isEqualTo(UPDATED_HOSE_LENGTH);
        assertThat(testStopChargingTool.getTestedHoseLength()).isEqualTo(UPDATED_TESTED_HOSE_LENGTH);
        assertThat(testStopChargingTool.getHoleDiameter()).isEqualTo(UPDATED_HOLE_DIAMETER);
        assertThat(testStopChargingTool.getTestedHoleDiameter()).isEqualTo(UPDATED_TESTED_HOLE_DIAMETER);
        assertThat(testStopChargingTool.getExplosiveType()).isEqualTo(UPDATED_EXPLOSIVE_TYPE);
        assertThat(testStopChargingTool.getTestedExplosiveType()).isEqualTo(UPDATED_TESTED_EXPLOSIVE_TYPE);
        assertThat(testStopChargingTool.getTankCapacity()).isEqualTo(UPDATED_TANK_CAPACITY);
        assertThat(testStopChargingTool.getTestedTankCapacity()).isEqualTo(UPDATED_TESTED_TANK_CAPACITY);
        assertThat(testStopChargingTool.getHeight()).isEqualTo(UPDATED_HEIGHT);
        assertThat(testStopChargingTool.getTestedHeight()).isEqualTo(UPDATED_TESTED_HEIGHT);
        assertThat(testStopChargingTool.getWeight()).isEqualTo(UPDATED_WEIGHT);
        assertThat(testStopChargingTool.getTestedWeight()).isEqualTo(UPDATED_TESTED_WEIGHT);
        assertThat(testStopChargingTool.getLength()).isEqualTo(UPDATED_LENGTH);
        assertThat(testStopChargingTool.getTestedLength()).isEqualTo(UPDATED_TESTED_LENGTH);
        assertThat(testStopChargingTool.getRpmOutput()).isEqualTo(UPDATED_RPM_OUTPUT);
        assertThat(testStopChargingTool.getTestedRpmOutput()).isEqualTo(UPDATED_TESTED_RPM_OUTPUT);
        assertThat(testStopChargingTool.getTorque()).isEqualTo(UPDATED_TORQUE);
        assertThat(testStopChargingTool.getTestedTorque()).isEqualTo(UPDATED_TESTED_TORQUE);
        assertThat(testStopChargingTool.getTankRange()).isEqualTo(UPDATED_TANK_RANGE);
        assertThat(testStopChargingTool.getTestedTankRange()).isEqualTo(UPDATED_TESTED_TANK_RANGE);
        assertThat(testStopChargingTool.getFuelConsumption()).isEqualTo(UPDATED_FUEL_CONSUMPTION);
        assertThat(testStopChargingTool.getTestedFuelConsumption()).isEqualTo(UPDATED_TESTED_FUEL_CONSUMPTION);
        assertThat(testStopChargingTool.getAvailability()).isEqualTo(UPDATED_AVAILABILITY);
        assertThat(testStopChargingTool.getTestedAvailability()).isEqualTo(UPDATED_TESTED_AVAILABILITY);
        assertThat(testStopChargingTool.getOperatingCostPerTonne()).isEqualTo(UPDATED_OPERATING_COST_PER_TONNE);
        assertThat(testStopChargingTool.getTestedOperatingCostPerTonne()).isEqualTo(UPDATED_TESTED_OPERATING_COST_PER_TONNE);
        assertThat(testStopChargingTool.getFuelConsumptionPerExplosiveKgCharged()).isEqualTo(UPDATED_FUEL_CONSUMPTION_PER_EXPLOSIVE_KG_CHARGED);
        assertThat(testStopChargingTool.getTestedFuelConsumptionPerExplosiveKgCharged()).isEqualTo(UPDATED_TESTED_FUEL_CONSUMPTION_PER_EXPLOSIVE_KG_CHARGED);
        assertThat(testStopChargingTool.getControlSystem()).isEqualTo(UPDATED_CONTROL_SYSTEM);
        assertThat(testStopChargingTool.getTestedControlSystem()).isEqualTo(UPDATED_TESTED_CONTROL_SYSTEM);
        assertThat(testStopChargingTool.getCycleTime()).isEqualTo(UPDATED_CYCLE_TIME);
        assertThat(testStopChargingTool.getTestedCycleTime()).isEqualTo(UPDATED_TESTED_CYCLE_TIME);
        assertThat(testStopChargingTool.getTurningRadiusInner()).isEqualTo(UPDATED_TURNING_RADIUS_INNER);
        assertThat(testStopChargingTool.getTestedTurningRadiusInner()).isEqualTo(UPDATED_TESTED_TURNING_RADIUS_INNER);
        assertThat(testStopChargingTool.getTurningRadiusOuter()).isEqualTo(UPDATED_TURNING_RADIUS_OUTER);
        assertThat(testStopChargingTool.getTestedTurningRadiusOuter()).isEqualTo(UPDATED_TESTED_TURNING_RADIUS_OUTER);
        assertThat(testStopChargingTool.getLubricationType()).isEqualTo(UPDATED_LUBRICATION_TYPE);
        assertThat(testStopChargingTool.getTestedLubricationType()).isEqualTo(UPDATED_TESTED_LUBRICATION_TYPE);
        assertThat(testStopChargingTool.getTemperatureAtAmbient()).isEqualTo(UPDATED_TEMPERATURE_AT_AMBIENT);
        assertThat(testStopChargingTool.getTestedTemperatureAtAmbient()).isEqualTo(UPDATED_TESTED_TEMPERATURE_AT_AMBIENT);
        assertThat(testStopChargingTool.getObservations1()).isEqualTo(UPDATED_OBSERVATIONS_1);
        assertThat(testStopChargingTool.getTestedObservations1()).isEqualTo(UPDATED_TESTED_OBSERVATIONS_1);
        assertThat(testStopChargingTool.getObservations2()).isEqualTo(UPDATED_OBSERVATIONS_2);
        assertThat(testStopChargingTool.getTestedObservations2()).isEqualTo(UPDATED_TESTED_OBSERVATIONS_2);
        assertThat(testStopChargingTool.getObservations3()).isEqualTo(UPDATED_OBSERVATIONS_3);
        assertThat(testStopChargingTool.getTestedObservations3()).isEqualTo(UPDATED_TESTED_OBSERVATIONS_3);
        assertThat(testStopChargingTool.getObservations4()).isEqualTo(UPDATED_OBSERVATIONS_4);
        assertThat(testStopChargingTool.getTestedObservations4()).isEqualTo(UPDATED_TESTED_OBSERVATIONS_4);
        assertThat(testStopChargingTool.getObservations5()).isEqualTo(UPDATED_OBSERVATIONS_5);
        assertThat(testStopChargingTool.getTestedObservations5()).isEqualTo(UPDATED_TESTED_OBSERVATIONS_5);
        assertThat(testStopChargingTool.getObservations6()).isEqualTo(UPDATED_OBSERVATIONS_6);
        assertThat(testStopChargingTool.getTestedObservations6()).isEqualTo(UPDATED_TESTED_OBSERVATIONS_6);
        assertThat(testStopChargingTool.getOperators_Comments()).isEqualTo(UPDATED_OPERATORS_COMMENTS);
        assertThat(testStopChargingTool.getTestedOperators_Comments()).isEqualTo(UPDATED_TESTED_OPERATORS_COMMENTS);

        // Validate the StopChargingTool in Elasticsearch
        StopChargingTool stopChargingToolEs = stopChargingToolSearchRepository.findOne(testStopChargingTool.getId());
        assertThat(stopChargingToolEs).isEqualToComparingFieldByField(testStopChargingTool);
    }

    @Test
    @Transactional
    public void updateNonExistingStopChargingTool() throws Exception {
        int databaseSizeBeforeUpdate = stopChargingToolRepository.findAll().size();

        // Create the StopChargingTool

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restStopChargingToolMockMvc.perform(put("/api/stop-charging-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(stopChargingTool)))
            .andExpect(status().isCreated());

        // Validate the StopChargingTool in the database
        List<StopChargingTool> stopChargingToolList = stopChargingToolRepository.findAll();
        assertThat(stopChargingToolList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteStopChargingTool() throws Exception {
        // Initialize the database
        stopChargingToolRepository.saveAndFlush(stopChargingTool);
        stopChargingToolSearchRepository.save(stopChargingTool);
        int databaseSizeBeforeDelete = stopChargingToolRepository.findAll().size();

        // Get the stopChargingTool
        restStopChargingToolMockMvc.perform(delete("/api/stop-charging-tools/{id}", stopChargingTool.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate Elasticsearch is empty
        boolean stopChargingToolExistsInEs = stopChargingToolSearchRepository.exists(stopChargingTool.getId());
        assertThat(stopChargingToolExistsInEs).isFalse();

        // Validate the database is empty
        List<StopChargingTool> stopChargingToolList = stopChargingToolRepository.findAll();
        assertThat(stopChargingToolList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void searchStopChargingTool() throws Exception {
        // Initialize the database
        stopChargingToolRepository.saveAndFlush(stopChargingTool);
        stopChargingToolSearchRepository.save(stopChargingTool);

        // Search the stopChargingTool
        restStopChargingToolMockMvc.perform(get("/api/_search/stop-charging-tools?query=id:" + stopChargingTool.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(stopChargingTool.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].model").value(hasItem(DEFAULT_MODEL.toString())))
            .andExpect(jsonPath("$.[*].technologyType").value(hasItem(DEFAULT_TECHNOLOGY_TYPE.toString())))
            .andExpect(jsonPath("$.[*].trl").value(hasItem(DEFAULT_TRL)))
            .andExpect(jsonPath("$.[*].photoContentType").value(hasItem(DEFAULT_PHOTO_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].photo").value(hasItem(Base64Utils.encodeToString(DEFAULT_PHOTO))))
            .andExpect(jsonPath("$.[*].datasheetContentType").value(hasItem(DEFAULT_DATASHEET_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].datasheet").value(hasItem(Base64Utils.encodeToString(DEFAULT_DATASHEET))))
            .andExpect(jsonPath("$.[*].hoseLength").value(hasItem(DEFAULT_HOSE_LENGTH.doubleValue())))
            .andExpect(jsonPath("$.[*].testedHoseLength").value(hasItem(DEFAULT_TESTED_HOSE_LENGTH.doubleValue())))
            .andExpect(jsonPath("$.[*].holeDiameter").value(hasItem(DEFAULT_HOLE_DIAMETER.doubleValue())))
            .andExpect(jsonPath("$.[*].testedHoleDiameter").value(hasItem(DEFAULT_TESTED_HOLE_DIAMETER.doubleValue())))
            .andExpect(jsonPath("$.[*].explosiveType").value(hasItem(DEFAULT_EXPLOSIVE_TYPE.toString())))
            .andExpect(jsonPath("$.[*].testedExplosiveType").value(hasItem(DEFAULT_TESTED_EXPLOSIVE_TYPE.toString())))
            .andExpect(jsonPath("$.[*].TankCapacity").value(hasItem(DEFAULT_TANK_CAPACITY.doubleValue())))
            .andExpect(jsonPath("$.[*].testedTankCapacity").value(hasItem(DEFAULT_TESTED_TANK_CAPACITY.doubleValue())))
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
            .andExpect(jsonPath("$.[*].fuelConsumptionPerExplosiveKgCharged").value(hasItem(DEFAULT_FUEL_CONSUMPTION_PER_EXPLOSIVE_KG_CHARGED.doubleValue())))
            .andExpect(jsonPath("$.[*].testedFuelConsumptionPerExplosiveKgCharged").value(hasItem(DEFAULT_TESTED_FUEL_CONSUMPTION_PER_EXPLOSIVE_KG_CHARGED.doubleValue())))
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
        TestUtil.equalsVerifier(StopChargingTool.class);
        StopChargingTool stopChargingTool1 = new StopChargingTool();
        stopChargingTool1.setId(1L);
        StopChargingTool stopChargingTool2 = new StopChargingTool();
        stopChargingTool2.setId(stopChargingTool1.getId());
        assertThat(stopChargingTool1).isEqualTo(stopChargingTool2);
        stopChargingTool2.setId(2L);
        assertThat(stopChargingTool1).isNotEqualTo(stopChargingTool2);
        stopChargingTool1.setId(null);
        assertThat(stopChargingTool1).isNotEqualTo(stopChargingTool2);
    }
}
