package com.tara.org.web.rest;

import com.tara.org.TaraApp;

import com.tara.org.domain.DevChargingTool;
import com.tara.org.repository.DevChargingToolRepository;
import com.tara.org.repository.search.DevChargingToolSearchRepository;
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
 * Test class for the DevChargingToolResource REST controller.
 *
 * @see DevChargingToolResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TaraApp.class)
public class DevChargingToolResourceIntTest {

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

    private static final String DEFAULT_TESDED_EXPLOSIVE_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_TESDED_EXPLOSIVE_TYPE = "BBBBBBBBBB";

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
    private DevChargingToolRepository devChargingToolRepository;

    @Autowired
    private DevChargingToolSearchRepository devChargingToolSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restDevChargingToolMockMvc;

    private DevChargingTool devChargingTool;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DevChargingToolResource devChargingToolResource = new DevChargingToolResource(devChargingToolRepository, devChargingToolSearchRepository);
        this.restDevChargingToolMockMvc = MockMvcBuilders.standaloneSetup(devChargingToolResource)
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
    public static DevChargingTool createEntity(EntityManager em) {
        DevChargingTool devChargingTool = new DevChargingTool()
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
            .tesdedExplosiveType(DEFAULT_TESDED_EXPLOSIVE_TYPE)
            .tankCapacity(DEFAULT_TANK_CAPACITY)
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
        return devChargingTool;
    }

    @Before
    public void initTest() {
        devChargingToolSearchRepository.deleteAll();
        devChargingTool = createEntity(em);
    }

    @Test
    @Transactional
    public void createDevChargingTool() throws Exception {
        int databaseSizeBeforeCreate = devChargingToolRepository.findAll().size();

        // Create the DevChargingTool
        restDevChargingToolMockMvc.perform(post("/api/dev-charging-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(devChargingTool)))
            .andExpect(status().isCreated());

        // Validate the DevChargingTool in the database
        List<DevChargingTool> devChargingToolList = devChargingToolRepository.findAll();
        assertThat(devChargingToolList).hasSize(databaseSizeBeforeCreate + 1);
        DevChargingTool testDevChargingTool = devChargingToolList.get(devChargingToolList.size() - 1);
        assertThat(testDevChargingTool.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testDevChargingTool.getModel()).isEqualTo(DEFAULT_MODEL);
        assertThat(testDevChargingTool.getTechnologyType()).isEqualTo(DEFAULT_TECHNOLOGY_TYPE);
        assertThat(testDevChargingTool.getTrl()).isEqualTo(DEFAULT_TRL);
        assertThat(testDevChargingTool.getPhoto()).isEqualTo(DEFAULT_PHOTO);
        assertThat(testDevChargingTool.getPhotoContentType()).isEqualTo(DEFAULT_PHOTO_CONTENT_TYPE);
        assertThat(testDevChargingTool.getDatasheet()).isEqualTo(DEFAULT_DATASHEET);
        assertThat(testDevChargingTool.getDatasheetContentType()).isEqualTo(DEFAULT_DATASHEET_CONTENT_TYPE);
        assertThat(testDevChargingTool.getHoseLength()).isEqualTo(DEFAULT_HOSE_LENGTH);
        assertThat(testDevChargingTool.getTestedHoseLength()).isEqualTo(DEFAULT_TESTED_HOSE_LENGTH);
        assertThat(testDevChargingTool.getHoleDiameter()).isEqualTo(DEFAULT_HOLE_DIAMETER);
        assertThat(testDevChargingTool.getTestedHoleDiameter()).isEqualTo(DEFAULT_TESTED_HOLE_DIAMETER);
        assertThat(testDevChargingTool.getExplosiveType()).isEqualTo(DEFAULT_EXPLOSIVE_TYPE);
        assertThat(testDevChargingTool.getTesdedExplosiveType()).isEqualTo(DEFAULT_TESDED_EXPLOSIVE_TYPE);
        assertThat(testDevChargingTool.getTankCapacity()).isEqualTo(DEFAULT_TANK_CAPACITY);
        assertThat(testDevChargingTool.getTestedTankCapacity()).isEqualTo(DEFAULT_TESTED_TANK_CAPACITY);
        assertThat(testDevChargingTool.getHeight()).isEqualTo(DEFAULT_HEIGHT);
        assertThat(testDevChargingTool.getTestedHeight()).isEqualTo(DEFAULT_TESTED_HEIGHT);
        assertThat(testDevChargingTool.getWeight()).isEqualTo(DEFAULT_WEIGHT);
        assertThat(testDevChargingTool.getTestedWeight()).isEqualTo(DEFAULT_TESTED_WEIGHT);
        assertThat(testDevChargingTool.getLength()).isEqualTo(DEFAULT_LENGTH);
        assertThat(testDevChargingTool.getTestedLength()).isEqualTo(DEFAULT_TESTED_LENGTH);
        assertThat(testDevChargingTool.getRpmOutput()).isEqualTo(DEFAULT_RPM_OUTPUT);
        assertThat(testDevChargingTool.getTestedRpmOutput()).isEqualTo(DEFAULT_TESTED_RPM_OUTPUT);
        assertThat(testDevChargingTool.getTorque()).isEqualTo(DEFAULT_TORQUE);
        assertThat(testDevChargingTool.getTestedTorque()).isEqualTo(DEFAULT_TESTED_TORQUE);
        assertThat(testDevChargingTool.getTankRange()).isEqualTo(DEFAULT_TANK_RANGE);
        assertThat(testDevChargingTool.getTestedTankRange()).isEqualTo(DEFAULT_TESTED_TANK_RANGE);
        assertThat(testDevChargingTool.getFuelConsumption()).isEqualTo(DEFAULT_FUEL_CONSUMPTION);
        assertThat(testDevChargingTool.getTestedFuelConsumption()).isEqualTo(DEFAULT_TESTED_FUEL_CONSUMPTION);
        assertThat(testDevChargingTool.getAvailability()).isEqualTo(DEFAULT_AVAILABILITY);
        assertThat(testDevChargingTool.getTestedAvailability()).isEqualTo(DEFAULT_TESTED_AVAILABILITY);
        assertThat(testDevChargingTool.getOperatingCostPerTonne()).isEqualTo(DEFAULT_OPERATING_COST_PER_TONNE);
        assertThat(testDevChargingTool.getTestedOperatingCostPerTonne()).isEqualTo(DEFAULT_TESTED_OPERATING_COST_PER_TONNE);
        assertThat(testDevChargingTool.getFuelConsumptionPerExplosiveKgCharged()).isEqualTo(DEFAULT_FUEL_CONSUMPTION_PER_EXPLOSIVE_KG_CHARGED);
        assertThat(testDevChargingTool.getTestedFuelConsumptionPerExplosiveKgCharged()).isEqualTo(DEFAULT_TESTED_FUEL_CONSUMPTION_PER_EXPLOSIVE_KG_CHARGED);
        assertThat(testDevChargingTool.getControlSystem()).isEqualTo(DEFAULT_CONTROL_SYSTEM);
        assertThat(testDevChargingTool.getTestedControlSystem()).isEqualTo(DEFAULT_TESTED_CONTROL_SYSTEM);
        assertThat(testDevChargingTool.getCycleTime()).isEqualTo(DEFAULT_CYCLE_TIME);
        assertThat(testDevChargingTool.getTestedCycleTime()).isEqualTo(DEFAULT_TESTED_CYCLE_TIME);
        assertThat(testDevChargingTool.getTurningRadiusInner()).isEqualTo(DEFAULT_TURNING_RADIUS_INNER);
        assertThat(testDevChargingTool.getTestedTurningRadiusInner()).isEqualTo(DEFAULT_TESTED_TURNING_RADIUS_INNER);
        assertThat(testDevChargingTool.getTurningRadiusOuter()).isEqualTo(DEFAULT_TURNING_RADIUS_OUTER);
        assertThat(testDevChargingTool.getTestedTurningRadiusOuter()).isEqualTo(DEFAULT_TESTED_TURNING_RADIUS_OUTER);
        assertThat(testDevChargingTool.getLubricationType()).isEqualTo(DEFAULT_LUBRICATION_TYPE);
        assertThat(testDevChargingTool.getTestedLubricationType()).isEqualTo(DEFAULT_TESTED_LUBRICATION_TYPE);
        assertThat(testDevChargingTool.getTemperatureAtAmbient()).isEqualTo(DEFAULT_TEMPERATURE_AT_AMBIENT);
        assertThat(testDevChargingTool.getTestedTemperatureAtAmbient()).isEqualTo(DEFAULT_TESTED_TEMPERATURE_AT_AMBIENT);
        assertThat(testDevChargingTool.getObservations1()).isEqualTo(DEFAULT_OBSERVATIONS_1);
        assertThat(testDevChargingTool.getTestedObservations1()).isEqualTo(DEFAULT_TESTED_OBSERVATIONS_1);
        assertThat(testDevChargingTool.getObservations2()).isEqualTo(DEFAULT_OBSERVATIONS_2);
        assertThat(testDevChargingTool.getTestedObservations2()).isEqualTo(DEFAULT_TESTED_OBSERVATIONS_2);
        assertThat(testDevChargingTool.getObservations3()).isEqualTo(DEFAULT_OBSERVATIONS_3);
        assertThat(testDevChargingTool.getTestedObservations3()).isEqualTo(DEFAULT_TESTED_OBSERVATIONS_3);
        assertThat(testDevChargingTool.getObservations4()).isEqualTo(DEFAULT_OBSERVATIONS_4);
        assertThat(testDevChargingTool.getTestedObservations4()).isEqualTo(DEFAULT_TESTED_OBSERVATIONS_4);
        assertThat(testDevChargingTool.getObservations5()).isEqualTo(DEFAULT_OBSERVATIONS_5);
        assertThat(testDevChargingTool.getTestedObservations5()).isEqualTo(DEFAULT_TESTED_OBSERVATIONS_5);
        assertThat(testDevChargingTool.getObservations6()).isEqualTo(DEFAULT_OBSERVATIONS_6);
        assertThat(testDevChargingTool.getTestedObservations6()).isEqualTo(DEFAULT_TESTED_OBSERVATIONS_6);
        assertThat(testDevChargingTool.getOperators_Comments()).isEqualTo(DEFAULT_OPERATORS_COMMENTS);
        assertThat(testDevChargingTool.getTestedOperators_Comments()).isEqualTo(DEFAULT_TESTED_OPERATORS_COMMENTS);

        // Validate the DevChargingTool in Elasticsearch
        DevChargingTool devChargingToolEs = devChargingToolSearchRepository.findOne(testDevChargingTool.getId());
        assertThat(devChargingToolEs).isEqualToComparingFieldByField(testDevChargingTool);
    }

    @Test
    @Transactional
    public void createDevChargingToolWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = devChargingToolRepository.findAll().size();

        // Create the DevChargingTool with an existing ID
        devChargingTool.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDevChargingToolMockMvc.perform(post("/api/dev-charging-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(devChargingTool)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<DevChargingTool> devChargingToolList = devChargingToolRepository.findAll();
        assertThat(devChargingToolList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = devChargingToolRepository.findAll().size();
        // set the field null
        devChargingTool.setName(null);

        // Create the DevChargingTool, which fails.

        restDevChargingToolMockMvc.perform(post("/api/dev-charging-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(devChargingTool)))
            .andExpect(status().isBadRequest());

        List<DevChargingTool> devChargingToolList = devChargingToolRepository.findAll();
        assertThat(devChargingToolList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkModelIsRequired() throws Exception {
        int databaseSizeBeforeTest = devChargingToolRepository.findAll().size();
        // set the field null
        devChargingTool.setModel(null);

        // Create the DevChargingTool, which fails.

        restDevChargingToolMockMvc.perform(post("/api/dev-charging-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(devChargingTool)))
            .andExpect(status().isBadRequest());

        List<DevChargingTool> devChargingToolList = devChargingToolRepository.findAll();
        assertThat(devChargingToolList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTechnologyTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = devChargingToolRepository.findAll().size();
        // set the field null
        devChargingTool.setTechnologyType(null);

        // Create the DevChargingTool, which fails.

        restDevChargingToolMockMvc.perform(post("/api/dev-charging-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(devChargingTool)))
            .andExpect(status().isBadRequest());

        List<DevChargingTool> devChargingToolList = devChargingToolRepository.findAll();
        assertThat(devChargingToolList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTrlIsRequired() throws Exception {
        int databaseSizeBeforeTest = devChargingToolRepository.findAll().size();
        // set the field null
        devChargingTool.setTrl(null);

        // Create the DevChargingTool, which fails.

        restDevChargingToolMockMvc.perform(post("/api/dev-charging-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(devChargingTool)))
            .andExpect(status().isBadRequest());

        List<DevChargingTool> devChargingToolList = devChargingToolRepository.findAll();
        assertThat(devChargingToolList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkHoseLengthIsRequired() throws Exception {
        int databaseSizeBeforeTest = devChargingToolRepository.findAll().size();
        // set the field null
        devChargingTool.setHoseLength(null);

        // Create the DevChargingTool, which fails.

        restDevChargingToolMockMvc.perform(post("/api/dev-charging-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(devChargingTool)))
            .andExpect(status().isBadRequest());

        List<DevChargingTool> devChargingToolList = devChargingToolRepository.findAll();
        assertThat(devChargingToolList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkHoleDiameterIsRequired() throws Exception {
        int databaseSizeBeforeTest = devChargingToolRepository.findAll().size();
        // set the field null
        devChargingTool.setHoleDiameter(null);

        // Create the DevChargingTool, which fails.

        restDevChargingToolMockMvc.perform(post("/api/dev-charging-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(devChargingTool)))
            .andExpect(status().isBadRequest());

        List<DevChargingTool> devChargingToolList = devChargingToolRepository.findAll();
        assertThat(devChargingToolList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkExplosiveTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = devChargingToolRepository.findAll().size();
        // set the field null
        devChargingTool.setExplosiveType(null);

        // Create the DevChargingTool, which fails.

        restDevChargingToolMockMvc.perform(post("/api/dev-charging-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(devChargingTool)))
            .andExpect(status().isBadRequest());

        List<DevChargingTool> devChargingToolList = devChargingToolRepository.findAll();
        assertThat(devChargingToolList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTankCapacityIsRequired() throws Exception {
        int databaseSizeBeforeTest = devChargingToolRepository.findAll().size();
        // set the field null
        devChargingTool.setTankCapacity(null);

        // Create the DevChargingTool, which fails.

        restDevChargingToolMockMvc.perform(post("/api/dev-charging-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(devChargingTool)))
            .andExpect(status().isBadRequest());

        List<DevChargingTool> devChargingToolList = devChargingToolRepository.findAll();
        assertThat(devChargingToolList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkHeightIsRequired() throws Exception {
        int databaseSizeBeforeTest = devChargingToolRepository.findAll().size();
        // set the field null
        devChargingTool.setHeight(null);

        // Create the DevChargingTool, which fails.

        restDevChargingToolMockMvc.perform(post("/api/dev-charging-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(devChargingTool)))
            .andExpect(status().isBadRequest());

        List<DevChargingTool> devChargingToolList = devChargingToolRepository.findAll();
        assertThat(devChargingToolList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkWeightIsRequired() throws Exception {
        int databaseSizeBeforeTest = devChargingToolRepository.findAll().size();
        // set the field null
        devChargingTool.setWeight(null);

        // Create the DevChargingTool, which fails.

        restDevChargingToolMockMvc.perform(post("/api/dev-charging-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(devChargingTool)))
            .andExpect(status().isBadRequest());

        List<DevChargingTool> devChargingToolList = devChargingToolRepository.findAll();
        assertThat(devChargingToolList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLengthIsRequired() throws Exception {
        int databaseSizeBeforeTest = devChargingToolRepository.findAll().size();
        // set the field null
        devChargingTool.setLength(null);

        // Create the DevChargingTool, which fails.

        restDevChargingToolMockMvc.perform(post("/api/dev-charging-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(devChargingTool)))
            .andExpect(status().isBadRequest());

        List<DevChargingTool> devChargingToolList = devChargingToolRepository.findAll();
        assertThat(devChargingToolList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTorqueIsRequired() throws Exception {
        int databaseSizeBeforeTest = devChargingToolRepository.findAll().size();
        // set the field null
        devChargingTool.setTorque(null);

        // Create the DevChargingTool, which fails.

        restDevChargingToolMockMvc.perform(post("/api/dev-charging-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(devChargingTool)))
            .andExpect(status().isBadRequest());

        List<DevChargingTool> devChargingToolList = devChargingToolRepository.findAll();
        assertThat(devChargingToolList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFuelConsumptionIsRequired() throws Exception {
        int databaseSizeBeforeTest = devChargingToolRepository.findAll().size();
        // set the field null
        devChargingTool.setFuelConsumption(null);

        // Create the DevChargingTool, which fails.

        restDevChargingToolMockMvc.perform(post("/api/dev-charging-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(devChargingTool)))
            .andExpect(status().isBadRequest());

        List<DevChargingTool> devChargingToolList = devChargingToolRepository.findAll();
        assertThat(devChargingToolList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAvailabilityIsRequired() throws Exception {
        int databaseSizeBeforeTest = devChargingToolRepository.findAll().size();
        // set the field null
        devChargingTool.setAvailability(null);

        // Create the DevChargingTool, which fails.

        restDevChargingToolMockMvc.perform(post("/api/dev-charging-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(devChargingTool)))
            .andExpect(status().isBadRequest());

        List<DevChargingTool> devChargingToolList = devChargingToolRepository.findAll();
        assertThat(devChargingToolList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkOperatingCostPerTonneIsRequired() throws Exception {
        int databaseSizeBeforeTest = devChargingToolRepository.findAll().size();
        // set the field null
        devChargingTool.setOperatingCostPerTonne(null);

        // Create the DevChargingTool, which fails.

        restDevChargingToolMockMvc.perform(post("/api/dev-charging-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(devChargingTool)))
            .andExpect(status().isBadRequest());

        List<DevChargingTool> devChargingToolList = devChargingToolRepository.findAll();
        assertThat(devChargingToolList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkControlSystemIsRequired() throws Exception {
        int databaseSizeBeforeTest = devChargingToolRepository.findAll().size();
        // set the field null
        devChargingTool.setControlSystem(null);

        // Create the DevChargingTool, which fails.

        restDevChargingToolMockMvc.perform(post("/api/dev-charging-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(devChargingTool)))
            .andExpect(status().isBadRequest());

        List<DevChargingTool> devChargingToolList = devChargingToolRepository.findAll();
        assertThat(devChargingToolList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTurningRadiusInnerIsRequired() throws Exception {
        int databaseSizeBeforeTest = devChargingToolRepository.findAll().size();
        // set the field null
        devChargingTool.setTurningRadiusInner(null);

        // Create the DevChargingTool, which fails.

        restDevChargingToolMockMvc.perform(post("/api/dev-charging-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(devChargingTool)))
            .andExpect(status().isBadRequest());

        List<DevChargingTool> devChargingToolList = devChargingToolRepository.findAll();
        assertThat(devChargingToolList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTurningRadiusOuterIsRequired() throws Exception {
        int databaseSizeBeforeTest = devChargingToolRepository.findAll().size();
        // set the field null
        devChargingTool.setTurningRadiusOuter(null);

        // Create the DevChargingTool, which fails.

        restDevChargingToolMockMvc.perform(post("/api/dev-charging-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(devChargingTool)))
            .andExpect(status().isBadRequest());

        List<DevChargingTool> devChargingToolList = devChargingToolRepository.findAll();
        assertThat(devChargingToolList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLubricationTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = devChargingToolRepository.findAll().size();
        // set the field null
        devChargingTool.setLubricationType(null);

        // Create the DevChargingTool, which fails.

        restDevChargingToolMockMvc.perform(post("/api/dev-charging-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(devChargingTool)))
            .andExpect(status().isBadRequest());

        List<DevChargingTool> devChargingToolList = devChargingToolRepository.findAll();
        assertThat(devChargingToolList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkObservations1IsRequired() throws Exception {
        int databaseSizeBeforeTest = devChargingToolRepository.findAll().size();
        // set the field null
        devChargingTool.setObservations1(null);

        // Create the DevChargingTool, which fails.

        restDevChargingToolMockMvc.perform(post("/api/dev-charging-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(devChargingTool)))
            .andExpect(status().isBadRequest());

        List<DevChargingTool> devChargingToolList = devChargingToolRepository.findAll();
        assertThat(devChargingToolList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkObservations2IsRequired() throws Exception {
        int databaseSizeBeforeTest = devChargingToolRepository.findAll().size();
        // set the field null
        devChargingTool.setObservations2(null);

        // Create the DevChargingTool, which fails.

        restDevChargingToolMockMvc.perform(post("/api/dev-charging-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(devChargingTool)))
            .andExpect(status().isBadRequest());

        List<DevChargingTool> devChargingToolList = devChargingToolRepository.findAll();
        assertThat(devChargingToolList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkObservations3IsRequired() throws Exception {
        int databaseSizeBeforeTest = devChargingToolRepository.findAll().size();
        // set the field null
        devChargingTool.setObservations3(null);

        // Create the DevChargingTool, which fails.

        restDevChargingToolMockMvc.perform(post("/api/dev-charging-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(devChargingTool)))
            .andExpect(status().isBadRequest());

        List<DevChargingTool> devChargingToolList = devChargingToolRepository.findAll();
        assertThat(devChargingToolList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkObservations4IsRequired() throws Exception {
        int databaseSizeBeforeTest = devChargingToolRepository.findAll().size();
        // set the field null
        devChargingTool.setObservations4(null);

        // Create the DevChargingTool, which fails.

        restDevChargingToolMockMvc.perform(post("/api/dev-charging-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(devChargingTool)))
            .andExpect(status().isBadRequest());

        List<DevChargingTool> devChargingToolList = devChargingToolRepository.findAll();
        assertThat(devChargingToolList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkObservations5IsRequired() throws Exception {
        int databaseSizeBeforeTest = devChargingToolRepository.findAll().size();
        // set the field null
        devChargingTool.setObservations5(null);

        // Create the DevChargingTool, which fails.

        restDevChargingToolMockMvc.perform(post("/api/dev-charging-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(devChargingTool)))
            .andExpect(status().isBadRequest());

        List<DevChargingTool> devChargingToolList = devChargingToolRepository.findAll();
        assertThat(devChargingToolList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkObservations6IsRequired() throws Exception {
        int databaseSizeBeforeTest = devChargingToolRepository.findAll().size();
        // set the field null
        devChargingTool.setObservations6(null);

        // Create the DevChargingTool, which fails.

        restDevChargingToolMockMvc.perform(post("/api/dev-charging-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(devChargingTool)))
            .andExpect(status().isBadRequest());

        List<DevChargingTool> devChargingToolList = devChargingToolRepository.findAll();
        assertThat(devChargingToolList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkOperators_CommentsIsRequired() throws Exception {
        int databaseSizeBeforeTest = devChargingToolRepository.findAll().size();
        // set the field null
        devChargingTool.setOperators_Comments(null);

        // Create the DevChargingTool, which fails.

        restDevChargingToolMockMvc.perform(post("/api/dev-charging-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(devChargingTool)))
            .andExpect(status().isBadRequest());

        List<DevChargingTool> devChargingToolList = devChargingToolRepository.findAll();
        assertThat(devChargingToolList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllDevChargingTools() throws Exception {
        // Initialize the database
        devChargingToolRepository.saveAndFlush(devChargingTool);

        // Get all the devChargingToolList
        restDevChargingToolMockMvc.perform(get("/api/dev-charging-tools?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(devChargingTool.getId().intValue())))
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
            .andExpect(jsonPath("$.[*].tesdedExplosiveType").value(hasItem(DEFAULT_TESDED_EXPLOSIVE_TYPE.toString())))
            .andExpect(jsonPath("$.[*].tankCapacity").value(hasItem(DEFAULT_TANK_CAPACITY.doubleValue())))
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
    public void getDevChargingTool() throws Exception {
        // Initialize the database
        devChargingToolRepository.saveAndFlush(devChargingTool);

        // Get the devChargingTool
        restDevChargingToolMockMvc.perform(get("/api/dev-charging-tools/{id}", devChargingTool.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(devChargingTool.getId().intValue()))
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
            .andExpect(jsonPath("$.tesdedExplosiveType").value(DEFAULT_TESDED_EXPLOSIVE_TYPE.toString()))
            .andExpect(jsonPath("$.tankCapacity").value(DEFAULT_TANK_CAPACITY.doubleValue()))
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
    public void getNonExistingDevChargingTool() throws Exception {
        // Get the devChargingTool
        restDevChargingToolMockMvc.perform(get("/api/dev-charging-tools/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDevChargingTool() throws Exception {
        // Initialize the database
        devChargingToolRepository.saveAndFlush(devChargingTool);
        devChargingToolSearchRepository.save(devChargingTool);
        int databaseSizeBeforeUpdate = devChargingToolRepository.findAll().size();

        // Update the devChargingTool
        DevChargingTool updatedDevChargingTool = devChargingToolRepository.findOne(devChargingTool.getId());
        updatedDevChargingTool
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
            .tesdedExplosiveType(UPDATED_TESDED_EXPLOSIVE_TYPE)
            .tankCapacity(UPDATED_TANK_CAPACITY)
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

        restDevChargingToolMockMvc.perform(put("/api/dev-charging-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedDevChargingTool)))
            .andExpect(status().isOk());

        // Validate the DevChargingTool in the database
        List<DevChargingTool> devChargingToolList = devChargingToolRepository.findAll();
        assertThat(devChargingToolList).hasSize(databaseSizeBeforeUpdate);
        DevChargingTool testDevChargingTool = devChargingToolList.get(devChargingToolList.size() - 1);
        assertThat(testDevChargingTool.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testDevChargingTool.getModel()).isEqualTo(UPDATED_MODEL);
        assertThat(testDevChargingTool.getTechnologyType()).isEqualTo(UPDATED_TECHNOLOGY_TYPE);
        assertThat(testDevChargingTool.getTrl()).isEqualTo(UPDATED_TRL);
        assertThat(testDevChargingTool.getPhoto()).isEqualTo(UPDATED_PHOTO);
        assertThat(testDevChargingTool.getPhotoContentType()).isEqualTo(UPDATED_PHOTO_CONTENT_TYPE);
        assertThat(testDevChargingTool.getDatasheet()).isEqualTo(UPDATED_DATASHEET);
        assertThat(testDevChargingTool.getDatasheetContentType()).isEqualTo(UPDATED_DATASHEET_CONTENT_TYPE);
        assertThat(testDevChargingTool.getHoseLength()).isEqualTo(UPDATED_HOSE_LENGTH);
        assertThat(testDevChargingTool.getTestedHoseLength()).isEqualTo(UPDATED_TESTED_HOSE_LENGTH);
        assertThat(testDevChargingTool.getHoleDiameter()).isEqualTo(UPDATED_HOLE_DIAMETER);
        assertThat(testDevChargingTool.getTestedHoleDiameter()).isEqualTo(UPDATED_TESTED_HOLE_DIAMETER);
        assertThat(testDevChargingTool.getExplosiveType()).isEqualTo(UPDATED_EXPLOSIVE_TYPE);
        assertThat(testDevChargingTool.getTesdedExplosiveType()).isEqualTo(UPDATED_TESDED_EXPLOSIVE_TYPE);
        assertThat(testDevChargingTool.getTankCapacity()).isEqualTo(UPDATED_TANK_CAPACITY);
        assertThat(testDevChargingTool.getTestedTankCapacity()).isEqualTo(UPDATED_TESTED_TANK_CAPACITY);
        assertThat(testDevChargingTool.getHeight()).isEqualTo(UPDATED_HEIGHT);
        assertThat(testDevChargingTool.getTestedHeight()).isEqualTo(UPDATED_TESTED_HEIGHT);
        assertThat(testDevChargingTool.getWeight()).isEqualTo(UPDATED_WEIGHT);
        assertThat(testDevChargingTool.getTestedWeight()).isEqualTo(UPDATED_TESTED_WEIGHT);
        assertThat(testDevChargingTool.getLength()).isEqualTo(UPDATED_LENGTH);
        assertThat(testDevChargingTool.getTestedLength()).isEqualTo(UPDATED_TESTED_LENGTH);
        assertThat(testDevChargingTool.getRpmOutput()).isEqualTo(UPDATED_RPM_OUTPUT);
        assertThat(testDevChargingTool.getTestedRpmOutput()).isEqualTo(UPDATED_TESTED_RPM_OUTPUT);
        assertThat(testDevChargingTool.getTorque()).isEqualTo(UPDATED_TORQUE);
        assertThat(testDevChargingTool.getTestedTorque()).isEqualTo(UPDATED_TESTED_TORQUE);
        assertThat(testDevChargingTool.getTankRange()).isEqualTo(UPDATED_TANK_RANGE);
        assertThat(testDevChargingTool.getTestedTankRange()).isEqualTo(UPDATED_TESTED_TANK_RANGE);
        assertThat(testDevChargingTool.getFuelConsumption()).isEqualTo(UPDATED_FUEL_CONSUMPTION);
        assertThat(testDevChargingTool.getTestedFuelConsumption()).isEqualTo(UPDATED_TESTED_FUEL_CONSUMPTION);
        assertThat(testDevChargingTool.getAvailability()).isEqualTo(UPDATED_AVAILABILITY);
        assertThat(testDevChargingTool.getTestedAvailability()).isEqualTo(UPDATED_TESTED_AVAILABILITY);
        assertThat(testDevChargingTool.getOperatingCostPerTonne()).isEqualTo(UPDATED_OPERATING_COST_PER_TONNE);
        assertThat(testDevChargingTool.getTestedOperatingCostPerTonne()).isEqualTo(UPDATED_TESTED_OPERATING_COST_PER_TONNE);
        assertThat(testDevChargingTool.getFuelConsumptionPerExplosiveKgCharged()).isEqualTo(UPDATED_FUEL_CONSUMPTION_PER_EXPLOSIVE_KG_CHARGED);
        assertThat(testDevChargingTool.getTestedFuelConsumptionPerExplosiveKgCharged()).isEqualTo(UPDATED_TESTED_FUEL_CONSUMPTION_PER_EXPLOSIVE_KG_CHARGED);
        assertThat(testDevChargingTool.getControlSystem()).isEqualTo(UPDATED_CONTROL_SYSTEM);
        assertThat(testDevChargingTool.getTestedControlSystem()).isEqualTo(UPDATED_TESTED_CONTROL_SYSTEM);
        assertThat(testDevChargingTool.getCycleTime()).isEqualTo(UPDATED_CYCLE_TIME);
        assertThat(testDevChargingTool.getTestedCycleTime()).isEqualTo(UPDATED_TESTED_CYCLE_TIME);
        assertThat(testDevChargingTool.getTurningRadiusInner()).isEqualTo(UPDATED_TURNING_RADIUS_INNER);
        assertThat(testDevChargingTool.getTestedTurningRadiusInner()).isEqualTo(UPDATED_TESTED_TURNING_RADIUS_INNER);
        assertThat(testDevChargingTool.getTurningRadiusOuter()).isEqualTo(UPDATED_TURNING_RADIUS_OUTER);
        assertThat(testDevChargingTool.getTestedTurningRadiusOuter()).isEqualTo(UPDATED_TESTED_TURNING_RADIUS_OUTER);
        assertThat(testDevChargingTool.getLubricationType()).isEqualTo(UPDATED_LUBRICATION_TYPE);
        assertThat(testDevChargingTool.getTestedLubricationType()).isEqualTo(UPDATED_TESTED_LUBRICATION_TYPE);
        assertThat(testDevChargingTool.getTemperatureAtAmbient()).isEqualTo(UPDATED_TEMPERATURE_AT_AMBIENT);
        assertThat(testDevChargingTool.getTestedTemperatureAtAmbient()).isEqualTo(UPDATED_TESTED_TEMPERATURE_AT_AMBIENT);
        assertThat(testDevChargingTool.getObservations1()).isEqualTo(UPDATED_OBSERVATIONS_1);
        assertThat(testDevChargingTool.getTestedObservations1()).isEqualTo(UPDATED_TESTED_OBSERVATIONS_1);
        assertThat(testDevChargingTool.getObservations2()).isEqualTo(UPDATED_OBSERVATIONS_2);
        assertThat(testDevChargingTool.getTestedObservations2()).isEqualTo(UPDATED_TESTED_OBSERVATIONS_2);
        assertThat(testDevChargingTool.getObservations3()).isEqualTo(UPDATED_OBSERVATIONS_3);
        assertThat(testDevChargingTool.getTestedObservations3()).isEqualTo(UPDATED_TESTED_OBSERVATIONS_3);
        assertThat(testDevChargingTool.getObservations4()).isEqualTo(UPDATED_OBSERVATIONS_4);
        assertThat(testDevChargingTool.getTestedObservations4()).isEqualTo(UPDATED_TESTED_OBSERVATIONS_4);
        assertThat(testDevChargingTool.getObservations5()).isEqualTo(UPDATED_OBSERVATIONS_5);
        assertThat(testDevChargingTool.getTestedObservations5()).isEqualTo(UPDATED_TESTED_OBSERVATIONS_5);
        assertThat(testDevChargingTool.getObservations6()).isEqualTo(UPDATED_OBSERVATIONS_6);
        assertThat(testDevChargingTool.getTestedObservations6()).isEqualTo(UPDATED_TESTED_OBSERVATIONS_6);
        assertThat(testDevChargingTool.getOperators_Comments()).isEqualTo(UPDATED_OPERATORS_COMMENTS);
        assertThat(testDevChargingTool.getTestedOperators_Comments()).isEqualTo(UPDATED_TESTED_OPERATORS_COMMENTS);

        // Validate the DevChargingTool in Elasticsearch
        DevChargingTool devChargingToolEs = devChargingToolSearchRepository.findOne(testDevChargingTool.getId());
        assertThat(devChargingToolEs).isEqualToComparingFieldByField(testDevChargingTool);
    }

    @Test
    @Transactional
    public void updateNonExistingDevChargingTool() throws Exception {
        int databaseSizeBeforeUpdate = devChargingToolRepository.findAll().size();

        // Create the DevChargingTool

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restDevChargingToolMockMvc.perform(put("/api/dev-charging-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(devChargingTool)))
            .andExpect(status().isCreated());

        // Validate the DevChargingTool in the database
        List<DevChargingTool> devChargingToolList = devChargingToolRepository.findAll();
        assertThat(devChargingToolList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteDevChargingTool() throws Exception {
        // Initialize the database
        devChargingToolRepository.saveAndFlush(devChargingTool);
        devChargingToolSearchRepository.save(devChargingTool);
        int databaseSizeBeforeDelete = devChargingToolRepository.findAll().size();

        // Get the devChargingTool
        restDevChargingToolMockMvc.perform(delete("/api/dev-charging-tools/{id}", devChargingTool.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate Elasticsearch is empty
        boolean devChargingToolExistsInEs = devChargingToolSearchRepository.exists(devChargingTool.getId());
        assertThat(devChargingToolExistsInEs).isFalse();

        // Validate the database is empty
        List<DevChargingTool> devChargingToolList = devChargingToolRepository.findAll();
        assertThat(devChargingToolList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void searchDevChargingTool() throws Exception {
        // Initialize the database
        devChargingToolRepository.saveAndFlush(devChargingTool);
        devChargingToolSearchRepository.save(devChargingTool);

        // Search the devChargingTool
        restDevChargingToolMockMvc.perform(get("/api/_search/dev-charging-tools?query=id:" + devChargingTool.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(devChargingTool.getId().intValue())))
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
            .andExpect(jsonPath("$.[*].tesdedExplosiveType").value(hasItem(DEFAULT_TESDED_EXPLOSIVE_TYPE.toString())))
            .andExpect(jsonPath("$.[*].tankCapacity").value(hasItem(DEFAULT_TANK_CAPACITY.doubleValue())))
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
        TestUtil.equalsVerifier(DevChargingTool.class);
        DevChargingTool devChargingTool1 = new DevChargingTool();
        devChargingTool1.setId(1L);
        DevChargingTool devChargingTool2 = new DevChargingTool();
        devChargingTool2.setId(devChargingTool1.getId());
        assertThat(devChargingTool1).isEqualTo(devChargingTool2);
        devChargingTool2.setId(2L);
        assertThat(devChargingTool1).isNotEqualTo(devChargingTool2);
        devChargingTool1.setId(null);
        assertThat(devChargingTool1).isNotEqualTo(devChargingTool2);
    }
}
