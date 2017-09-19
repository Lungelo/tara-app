package com.tara.org.web.rest;

import com.tara.org.TaraApp;

import com.tara.org.domain.StopSupportingTool;
import com.tara.org.repository.StopSupportingToolRepository;
import com.tara.org.repository.search.StopSupportingToolSearchRepository;
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

/**
 * Test class for the StopSupportingToolResource REST controller.
 *
 * @see StopSupportingToolResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TaraApp.class)
public class StopSupportingToolResourceIntTest {

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

    private static final String DEFAULT_TYPE_OF_MACHINE = "AAAAAAAAAA";
    private static final String UPDATED_TYPE_OF_MACHINE = "BBBBBBBBBB";

    private static final String DEFAULT_TESTED_TYPE_OF_MACHINE = "AAAAAAAAAA";
    private static final String UPDATED_TESTED_TYPE_OF_MACHINE = "BBBBBBBBBB";

    private static final Float DEFAULT_WEIGHT = 1F;
    private static final Float UPDATED_WEIGHT = 2F;

    private static final Float DEFAULT_TESTED_WEIGHT = 1F;
    private static final Float UPDATED_TESTED_WEIGHT = 2F;

    private static final Float DEFAULT_LENGTH = 1F;
    private static final Float UPDATED_LENGTH = 2F;

    private static final Float DEFAULT_TESTED_LENGTH = 1F;
    private static final Float UPDATED_TESTED_LENGTH = 2F;

    private static final Float DEFAULT_WIDTH = 1F;
    private static final Float UPDATED_WIDTH = 2F;

    private static final Float DEFAULT_TESTED_WIDTH = 1F;
    private static final Float UPDATED_TESTED_WIDTH = 2F;

    private static final Float DEFAULT_HOLE_SIZE = 1F;
    private static final Float UPDATED_HOLE_SIZE = 2F;

    private static final Float DEFAULT_TESTED_HOLE_SIZE = 1F;
    private static final Float UPDATED_TESTED_HOLE_SIZE = 2F;

    private static final Float DEFAULT_DRILL_WATER_CONSUMPTION = 1F;
    private static final Float UPDATED_DRILL_WATER_CONSUMPTION = 2F;

    private static final Float DEFAULT_TESTED_DRILL_WATER_CONSUMPTION = 1F;
    private static final Float UPDATED_TESTED_DRILL_WATER_CONSUMPTION = 2F;

    private static final Float DEFAULT_CYCLE_TIME_BOLTING = 1F;
    private static final Float UPDATED_CYCLE_TIME_BOLTING = 2F;

    private static final Float DEFAULT_TESTED_CYCLE_TIME_BOLTING = 1F;
    private static final Float UPDATED_TESTED_CYCLE_TIME_BOLTING = 2F;

    private static final Float DEFAULT_WATER_CONSUMPTION_PER_METRE_DRILLED = 1F;
    private static final Float UPDATED_WATER_CONSUMPTION_PER_METRE_DRILLED = 2F;

    private static final Float DEFAULT_TESTED_WATER_CONSUMPTION_PER_METRE_DRILLED = 1F;
    private static final Float UPDATED_TESTED_WATER_CONSUMPTION_PER_METRE_DRILLED = 2F;

    private static final String DEFAULT_POWER_SOURCE = "AAAAAAAAAA";
    private static final String UPDATED_POWER_SOURCE = "BBBBBBBBBB";

    private static final String DEFAULT_TESTED_POWER_SOURCE = "AAAAAAAAAA";
    private static final String UPDATED_TESTED_POWER_SOURCE = "BBBBBBBBBB";

    private static final Float DEFAULT_TRAMMING_SPEED = 1F;
    private static final Float UPDATED_TRAMMING_SPEED = 2F;

    private static final Float DEFAULT_TESTED_TRAMMING_SPEED = 1F;
    private static final Float UPDATED_TESTED_TRAMMING_SPEED = 2F;

    private static final String DEFAULT_BOLT_LENGTH_RANGE = "AAAAAAAAAA";
    private static final String UPDATED_BOLT_LENGTH_RANGE = "BBBBBBBBBB";

    private static final String DEFAULT_TESTED_BOLT_LENGTH_RANGE = "AAAAAAAAAA";
    private static final String UPDATED_TESTED_BOLT_LENGTH_RANGE = "BBBBBBBBBB";

    private static final Float DEFAULT_DRILL_SPEED = 1F;
    private static final Float UPDATED_DRILL_SPEED = 2F;

    private static final Float DEFAULT_TESTED_DRILL_SPEED = 1F;
    private static final Float UPDATED_TESTED_DRILL_SPEED = 2F;

    private static final Float DEFAULT_GRADEABILITY = 1F;
    private static final Float UPDATED_GRADEABILITY = 2F;

    private static final Float DEFAULT_TESTED_GRADEABILITY = 1F;
    private static final Float UPDATED_TESTED_GRADEABILITY = 2F;

    private static final Float DEFAULT_NUMBER_OF_BOOMS = 1F;
    private static final Float UPDATED_NUMBER_OF_BOOMS = 2F;

    private static final Float DEFAULT_TESTED_NUMBER_OF_BOOMS = 1F;
    private static final Float UPDATED_TESTED_NUMBER_OF_BOOMS = 2F;

    private static final String DEFAULT_TYPE_OF_BOOM = "AAAAAAAAAA";
    private static final String UPDATED_TYPE_OF_BOOM = "BBBBBBBBBB";

    private static final String DEFAULT_TESTED_TYPE_OF_BOOM = "AAAAAAAAAA";
    private static final String UPDATED_TESTED_TYPE_OF_BOOM = "BBBBBBBBBB";

    private static final Float DEFAULT_OUTER_TURNING_RADIUS = 1F;
    private static final Float UPDATED_OUTER_TURNING_RADIUS = 2F;

    private static final Float DEFAULT_TESTED_OUTER_TURNING_RADIUS = 1F;
    private static final Float UPDATED_TESTED_OUTER_TURNING_RADIUS = 2F;

    private static final Float DEFAULT_INNER_TURNING_RADIUS = 1F;
    private static final Float UPDATED_INNER_TURNING_RADIUS = 2F;

    private static final Float DEFAULT_TESTED_INNER_TURNING_RADIUS = 1F;
    private static final Float UPDATED_TESTED_INNER_TURNING_RADIUS = 2F;

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
    private StopSupportingToolRepository stopSupportingToolRepository;

    @Autowired
    private StopSupportingToolSearchRepository stopSupportingToolSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restStopSupportingToolMockMvc;

    private StopSupportingTool stopSupportingTool;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final StopSupportingToolResource stopSupportingToolResource = new StopSupportingToolResource(stopSupportingToolRepository, stopSupportingToolSearchRepository);
        this.restStopSupportingToolMockMvc = MockMvcBuilders.standaloneSetup(stopSupportingToolResource)
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
    public static StopSupportingTool createEntity(EntityManager em) {
        StopSupportingTool stopSupportingTool = new StopSupportingTool()
            .name(DEFAULT_NAME)
            .model(DEFAULT_MODEL)
            .technologyType(DEFAULT_TECHNOLOGY_TYPE)
            .trl(DEFAULT_TRL)
            .photo(DEFAULT_PHOTO)
            .photoContentType(DEFAULT_PHOTO_CONTENT_TYPE)
            .datasheet(DEFAULT_DATASHEET)
            .datasheetContentType(DEFAULT_DATASHEET_CONTENT_TYPE)
            .typeOfMachine(DEFAULT_TYPE_OF_MACHINE)
            .testedTypeOfMachine(DEFAULT_TESTED_TYPE_OF_MACHINE)
            .weight(DEFAULT_WEIGHT)
            .testedWeight(DEFAULT_TESTED_WEIGHT)
            .length(DEFAULT_LENGTH)
            .testedLength(DEFAULT_TESTED_LENGTH)
            .width(DEFAULT_WIDTH)
            .testedWidth(DEFAULT_TESTED_WIDTH)
            .holeSize(DEFAULT_HOLE_SIZE)
            .testedHoleSize(DEFAULT_TESTED_HOLE_SIZE)
            .drillWaterConsumption(DEFAULT_DRILL_WATER_CONSUMPTION)
            .testedDrillWaterConsumption(DEFAULT_TESTED_DRILL_WATER_CONSUMPTION)
            .cycleTimeBolting(DEFAULT_CYCLE_TIME_BOLTING)
            .testedCycleTimeBolting(DEFAULT_TESTED_CYCLE_TIME_BOLTING)
            .waterConsumptionPerMetreDrilled(DEFAULT_WATER_CONSUMPTION_PER_METRE_DRILLED)
            .testedWaterConsumptionPerMetreDrilled(DEFAULT_TESTED_WATER_CONSUMPTION_PER_METRE_DRILLED)
            .powerSource(DEFAULT_POWER_SOURCE)
            .testedPowerSource(DEFAULT_TESTED_POWER_SOURCE)
            .trammingSpeed(DEFAULT_TRAMMING_SPEED)
            .testedTrammingSpeed(DEFAULT_TESTED_TRAMMING_SPEED)
            .boltLengthRange(DEFAULT_BOLT_LENGTH_RANGE)
            .testedBoltLengthRange(DEFAULT_TESTED_BOLT_LENGTH_RANGE)
            .drillSpeed(DEFAULT_DRILL_SPEED)
            .testedDrillSpeed(DEFAULT_TESTED_DRILL_SPEED)
            .gradeability(DEFAULT_GRADEABILITY)
            .testedGradeability(DEFAULT_TESTED_GRADEABILITY)
            .numberOfBooms(DEFAULT_NUMBER_OF_BOOMS)
            .testedNumberOfBooms(DEFAULT_TESTED_NUMBER_OF_BOOMS)
            .typeOfBoom(DEFAULT_TYPE_OF_BOOM)
            .testedTypeOfBoom(DEFAULT_TESTED_TYPE_OF_BOOM)
            .outerTurningRadius(DEFAULT_OUTER_TURNING_RADIUS)
            .testedOuterTurningRadius(DEFAULT_TESTED_OUTER_TURNING_RADIUS)
            .innerTurningRadius(DEFAULT_INNER_TURNING_RADIUS)
            .testedInnerTurningRadius(DEFAULT_TESTED_INNER_TURNING_RADIUS)
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
        return stopSupportingTool;
    }

    @Before
    public void initTest() {
        stopSupportingToolSearchRepository.deleteAll();
        stopSupportingTool = createEntity(em);
    }

    @Test
    @Transactional
    public void createStopSupportingTool() throws Exception {
        int databaseSizeBeforeCreate = stopSupportingToolRepository.findAll().size();

        // Create the StopSupportingTool
        restStopSupportingToolMockMvc.perform(post("/api/stop-supporting-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(stopSupportingTool)))
            .andExpect(status().isCreated());

        // Validate the StopSupportingTool in the database
        List<StopSupportingTool> stopSupportingToolList = stopSupportingToolRepository.findAll();
        assertThat(stopSupportingToolList).hasSize(databaseSizeBeforeCreate + 1);
        StopSupportingTool testStopSupportingTool = stopSupportingToolList.get(stopSupportingToolList.size() - 1);
        assertThat(testStopSupportingTool.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testStopSupportingTool.getModel()).isEqualTo(DEFAULT_MODEL);
        assertThat(testStopSupportingTool.getTechnologyType()).isEqualTo(DEFAULT_TECHNOLOGY_TYPE);
        assertThat(testStopSupportingTool.getTrl()).isEqualTo(DEFAULT_TRL);
        assertThat(testStopSupportingTool.getPhoto()).isEqualTo(DEFAULT_PHOTO);
        assertThat(testStopSupportingTool.getPhotoContentType()).isEqualTo(DEFAULT_PHOTO_CONTENT_TYPE);
        assertThat(testStopSupportingTool.getDatasheet()).isEqualTo(DEFAULT_DATASHEET);
        assertThat(testStopSupportingTool.getDatasheetContentType()).isEqualTo(DEFAULT_DATASHEET_CONTENT_TYPE);
        assertThat(testStopSupportingTool.getTypeOfMachine()).isEqualTo(DEFAULT_TYPE_OF_MACHINE);
        assertThat(testStopSupportingTool.getTestedTypeOfMachine()).isEqualTo(DEFAULT_TESTED_TYPE_OF_MACHINE);
        assertThat(testStopSupportingTool.getWeight()).isEqualTo(DEFAULT_WEIGHT);
        assertThat(testStopSupportingTool.getTestedWeight()).isEqualTo(DEFAULT_TESTED_WEIGHT);
        assertThat(testStopSupportingTool.getLength()).isEqualTo(DEFAULT_LENGTH);
        assertThat(testStopSupportingTool.getTestedLength()).isEqualTo(DEFAULT_TESTED_LENGTH);
        assertThat(testStopSupportingTool.getWidth()).isEqualTo(DEFAULT_WIDTH);
        assertThat(testStopSupportingTool.getTestedWidth()).isEqualTo(DEFAULT_TESTED_WIDTH);
        assertThat(testStopSupportingTool.getHoleSize()).isEqualTo(DEFAULT_HOLE_SIZE);
        assertThat(testStopSupportingTool.getTestedHoleSize()).isEqualTo(DEFAULT_TESTED_HOLE_SIZE);
        assertThat(testStopSupportingTool.getDrillWaterConsumption()).isEqualTo(DEFAULT_DRILL_WATER_CONSUMPTION);
        assertThat(testStopSupportingTool.getTestedDrillWaterConsumption()).isEqualTo(DEFAULT_TESTED_DRILL_WATER_CONSUMPTION);
        assertThat(testStopSupportingTool.getCycleTimeBolting()).isEqualTo(DEFAULT_CYCLE_TIME_BOLTING);
        assertThat(testStopSupportingTool.getTestedCycleTimeBolting()).isEqualTo(DEFAULT_TESTED_CYCLE_TIME_BOLTING);
        assertThat(testStopSupportingTool.getWaterConsumptionPerMetreDrilled()).isEqualTo(DEFAULT_WATER_CONSUMPTION_PER_METRE_DRILLED);
        assertThat(testStopSupportingTool.getTestedWaterConsumptionPerMetreDrilled()).isEqualTo(DEFAULT_TESTED_WATER_CONSUMPTION_PER_METRE_DRILLED);
        assertThat(testStopSupportingTool.getPowerSource()).isEqualTo(DEFAULT_POWER_SOURCE);
        assertThat(testStopSupportingTool.getTestedPowerSource()).isEqualTo(DEFAULT_TESTED_POWER_SOURCE);
        assertThat(testStopSupportingTool.getTrammingSpeed()).isEqualTo(DEFAULT_TRAMMING_SPEED);
        assertThat(testStopSupportingTool.getTestedTrammingSpeed()).isEqualTo(DEFAULT_TESTED_TRAMMING_SPEED);
        assertThat(testStopSupportingTool.getBoltLengthRange()).isEqualTo(DEFAULT_BOLT_LENGTH_RANGE);
        assertThat(testStopSupportingTool.getTestedBoltLengthRange()).isEqualTo(DEFAULT_TESTED_BOLT_LENGTH_RANGE);
        assertThat(testStopSupportingTool.getDrillSpeed()).isEqualTo(DEFAULT_DRILL_SPEED);
        assertThat(testStopSupportingTool.getTestedDrillSpeed()).isEqualTo(DEFAULT_TESTED_DRILL_SPEED);
        assertThat(testStopSupportingTool.getGradeability()).isEqualTo(DEFAULT_GRADEABILITY);
        assertThat(testStopSupportingTool.getTestedGradeability()).isEqualTo(DEFAULT_TESTED_GRADEABILITY);
        assertThat(testStopSupportingTool.getNumberOfBooms()).isEqualTo(DEFAULT_NUMBER_OF_BOOMS);
        assertThat(testStopSupportingTool.getTestedNumberOfBooms()).isEqualTo(DEFAULT_TESTED_NUMBER_OF_BOOMS);
        assertThat(testStopSupportingTool.getTypeOfBoom()).isEqualTo(DEFAULT_TYPE_OF_BOOM);
        assertThat(testStopSupportingTool.getTestedTypeOfBoom()).isEqualTo(DEFAULT_TESTED_TYPE_OF_BOOM);
        assertThat(testStopSupportingTool.getOuterTurningRadius()).isEqualTo(DEFAULT_OUTER_TURNING_RADIUS);
        assertThat(testStopSupportingTool.getTestedOuterTurningRadius()).isEqualTo(DEFAULT_TESTED_OUTER_TURNING_RADIUS);
        assertThat(testStopSupportingTool.getInnerTurningRadius()).isEqualTo(DEFAULT_INNER_TURNING_RADIUS);
        assertThat(testStopSupportingTool.getTestedInnerTurningRadius()).isEqualTo(DEFAULT_TESTED_INNER_TURNING_RADIUS);
        assertThat(testStopSupportingTool.getObservations1()).isEqualTo(DEFAULT_OBSERVATIONS_1);
        assertThat(testStopSupportingTool.getTestedObservations1()).isEqualTo(DEFAULT_TESTED_OBSERVATIONS_1);
        assertThat(testStopSupportingTool.getObservations2()).isEqualTo(DEFAULT_OBSERVATIONS_2);
        assertThat(testStopSupportingTool.getTestedObservations2()).isEqualTo(DEFAULT_TESTED_OBSERVATIONS_2);
        assertThat(testStopSupportingTool.getObservations3()).isEqualTo(DEFAULT_OBSERVATIONS_3);
        assertThat(testStopSupportingTool.getTestedObservations3()).isEqualTo(DEFAULT_TESTED_OBSERVATIONS_3);
        assertThat(testStopSupportingTool.getObservations4()).isEqualTo(DEFAULT_OBSERVATIONS_4);
        assertThat(testStopSupportingTool.getTestedObservations4()).isEqualTo(DEFAULT_TESTED_OBSERVATIONS_4);
        assertThat(testStopSupportingTool.getObservations5()).isEqualTo(DEFAULT_OBSERVATIONS_5);
        assertThat(testStopSupportingTool.getTestedObservations5()).isEqualTo(DEFAULT_TESTED_OBSERVATIONS_5);
        assertThat(testStopSupportingTool.getObservations6()).isEqualTo(DEFAULT_OBSERVATIONS_6);
        assertThat(testStopSupportingTool.getTestedObservations6()).isEqualTo(DEFAULT_TESTED_OBSERVATIONS_6);
        assertThat(testStopSupportingTool.getOperators_Comments()).isEqualTo(DEFAULT_OPERATORS_COMMENTS);
        assertThat(testStopSupportingTool.getTestedOperators_Comments()).isEqualTo(DEFAULT_TESTED_OPERATORS_COMMENTS);

        // Validate the StopSupportingTool in Elasticsearch
        StopSupportingTool stopSupportingToolEs = stopSupportingToolSearchRepository.findOne(testStopSupportingTool.getId());
        assertThat(stopSupportingToolEs).isEqualToComparingFieldByField(testStopSupportingTool);
    }

    @Test
    @Transactional
    public void createStopSupportingToolWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = stopSupportingToolRepository.findAll().size();

        // Create the StopSupportingTool with an existing ID
        stopSupportingTool.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restStopSupportingToolMockMvc.perform(post("/api/stop-supporting-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(stopSupportingTool)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<StopSupportingTool> stopSupportingToolList = stopSupportingToolRepository.findAll();
        assertThat(stopSupportingToolList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = stopSupportingToolRepository.findAll().size();
        // set the field null
        stopSupportingTool.setName(null);

        // Create the StopSupportingTool, which fails.

        restStopSupportingToolMockMvc.perform(post("/api/stop-supporting-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(stopSupportingTool)))
            .andExpect(status().isBadRequest());

        List<StopSupportingTool> stopSupportingToolList = stopSupportingToolRepository.findAll();
        assertThat(stopSupportingToolList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkModelIsRequired() throws Exception {
        int databaseSizeBeforeTest = stopSupportingToolRepository.findAll().size();
        // set the field null
        stopSupportingTool.setModel(null);

        // Create the StopSupportingTool, which fails.

        restStopSupportingToolMockMvc.perform(post("/api/stop-supporting-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(stopSupportingTool)))
            .andExpect(status().isBadRequest());

        List<StopSupportingTool> stopSupportingToolList = stopSupportingToolRepository.findAll();
        assertThat(stopSupportingToolList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTechnologyTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = stopSupportingToolRepository.findAll().size();
        // set the field null
        stopSupportingTool.setTechnologyType(null);

        // Create the StopSupportingTool, which fails.

        restStopSupportingToolMockMvc.perform(post("/api/stop-supporting-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(stopSupportingTool)))
            .andExpect(status().isBadRequest());

        List<StopSupportingTool> stopSupportingToolList = stopSupportingToolRepository.findAll();
        assertThat(stopSupportingToolList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTrlIsRequired() throws Exception {
        int databaseSizeBeforeTest = stopSupportingToolRepository.findAll().size();
        // set the field null
        stopSupportingTool.setTrl(null);

        // Create the StopSupportingTool, which fails.

        restStopSupportingToolMockMvc.perform(post("/api/stop-supporting-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(stopSupportingTool)))
            .andExpect(status().isBadRequest());

        List<StopSupportingTool> stopSupportingToolList = stopSupportingToolRepository.findAll();
        assertThat(stopSupportingToolList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTypeOfMachineIsRequired() throws Exception {
        int databaseSizeBeforeTest = stopSupportingToolRepository.findAll().size();
        // set the field null
        stopSupportingTool.setTypeOfMachine(null);

        // Create the StopSupportingTool, which fails.

        restStopSupportingToolMockMvc.perform(post("/api/stop-supporting-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(stopSupportingTool)))
            .andExpect(status().isBadRequest());

        List<StopSupportingTool> stopSupportingToolList = stopSupportingToolRepository.findAll();
        assertThat(stopSupportingToolList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkWeightIsRequired() throws Exception {
        int databaseSizeBeforeTest = stopSupportingToolRepository.findAll().size();
        // set the field null
        stopSupportingTool.setWeight(null);

        // Create the StopSupportingTool, which fails.

        restStopSupportingToolMockMvc.perform(post("/api/stop-supporting-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(stopSupportingTool)))
            .andExpect(status().isBadRequest());

        List<StopSupportingTool> stopSupportingToolList = stopSupportingToolRepository.findAll();
        assertThat(stopSupportingToolList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLengthIsRequired() throws Exception {
        int databaseSizeBeforeTest = stopSupportingToolRepository.findAll().size();
        // set the field null
        stopSupportingTool.setLength(null);

        // Create the StopSupportingTool, which fails.

        restStopSupportingToolMockMvc.perform(post("/api/stop-supporting-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(stopSupportingTool)))
            .andExpect(status().isBadRequest());

        List<StopSupportingTool> stopSupportingToolList = stopSupportingToolRepository.findAll();
        assertThat(stopSupportingToolList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkWidthIsRequired() throws Exception {
        int databaseSizeBeforeTest = stopSupportingToolRepository.findAll().size();
        // set the field null
        stopSupportingTool.setWidth(null);

        // Create the StopSupportingTool, which fails.

        restStopSupportingToolMockMvc.perform(post("/api/stop-supporting-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(stopSupportingTool)))
            .andExpect(status().isBadRequest());

        List<StopSupportingTool> stopSupportingToolList = stopSupportingToolRepository.findAll();
        assertThat(stopSupportingToolList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkHoleSizeIsRequired() throws Exception {
        int databaseSizeBeforeTest = stopSupportingToolRepository.findAll().size();
        // set the field null
        stopSupportingTool.setHoleSize(null);

        // Create the StopSupportingTool, which fails.

        restStopSupportingToolMockMvc.perform(post("/api/stop-supporting-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(stopSupportingTool)))
            .andExpect(status().isBadRequest());

        List<StopSupportingTool> stopSupportingToolList = stopSupportingToolRepository.findAll();
        assertThat(stopSupportingToolList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDrillWaterConsumptionIsRequired() throws Exception {
        int databaseSizeBeforeTest = stopSupportingToolRepository.findAll().size();
        // set the field null
        stopSupportingTool.setDrillWaterConsumption(null);

        // Create the StopSupportingTool, which fails.

        restStopSupportingToolMockMvc.perform(post("/api/stop-supporting-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(stopSupportingTool)))
            .andExpect(status().isBadRequest());

        List<StopSupportingTool> stopSupportingToolList = stopSupportingToolRepository.findAll();
        assertThat(stopSupportingToolList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCycleTimeBoltingIsRequired() throws Exception {
        int databaseSizeBeforeTest = stopSupportingToolRepository.findAll().size();
        // set the field null
        stopSupportingTool.setCycleTimeBolting(null);

        // Create the StopSupportingTool, which fails.

        restStopSupportingToolMockMvc.perform(post("/api/stop-supporting-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(stopSupportingTool)))
            .andExpect(status().isBadRequest());

        List<StopSupportingTool> stopSupportingToolList = stopSupportingToolRepository.findAll();
        assertThat(stopSupportingToolList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkWaterConsumptionPerMetreDrilledIsRequired() throws Exception {
        int databaseSizeBeforeTest = stopSupportingToolRepository.findAll().size();
        // set the field null
        stopSupportingTool.setWaterConsumptionPerMetreDrilled(null);

        // Create the StopSupportingTool, which fails.

        restStopSupportingToolMockMvc.perform(post("/api/stop-supporting-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(stopSupportingTool)))
            .andExpect(status().isBadRequest());

        List<StopSupportingTool> stopSupportingToolList = stopSupportingToolRepository.findAll();
        assertThat(stopSupportingToolList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPowerSourceIsRequired() throws Exception {
        int databaseSizeBeforeTest = stopSupportingToolRepository.findAll().size();
        // set the field null
        stopSupportingTool.setPowerSource(null);

        // Create the StopSupportingTool, which fails.

        restStopSupportingToolMockMvc.perform(post("/api/stop-supporting-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(stopSupportingTool)))
            .andExpect(status().isBadRequest());

        List<StopSupportingTool> stopSupportingToolList = stopSupportingToolRepository.findAll();
        assertThat(stopSupportingToolList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTrammingSpeedIsRequired() throws Exception {
        int databaseSizeBeforeTest = stopSupportingToolRepository.findAll().size();
        // set the field null
        stopSupportingTool.setTrammingSpeed(null);

        // Create the StopSupportingTool, which fails.

        restStopSupportingToolMockMvc.perform(post("/api/stop-supporting-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(stopSupportingTool)))
            .andExpect(status().isBadRequest());

        List<StopSupportingTool> stopSupportingToolList = stopSupportingToolRepository.findAll();
        assertThat(stopSupportingToolList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkBoltLengthRangeIsRequired() throws Exception {
        int databaseSizeBeforeTest = stopSupportingToolRepository.findAll().size();
        // set the field null
        stopSupportingTool.setBoltLengthRange(null);

        // Create the StopSupportingTool, which fails.

        restStopSupportingToolMockMvc.perform(post("/api/stop-supporting-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(stopSupportingTool)))
            .andExpect(status().isBadRequest());

        List<StopSupportingTool> stopSupportingToolList = stopSupportingToolRepository.findAll();
        assertThat(stopSupportingToolList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDrillSpeedIsRequired() throws Exception {
        int databaseSizeBeforeTest = stopSupportingToolRepository.findAll().size();
        // set the field null
        stopSupportingTool.setDrillSpeed(null);

        // Create the StopSupportingTool, which fails.

        restStopSupportingToolMockMvc.perform(post("/api/stop-supporting-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(stopSupportingTool)))
            .andExpect(status().isBadRequest());

        List<StopSupportingTool> stopSupportingToolList = stopSupportingToolRepository.findAll();
        assertThat(stopSupportingToolList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkGradeabilityIsRequired() throws Exception {
        int databaseSizeBeforeTest = stopSupportingToolRepository.findAll().size();
        // set the field null
        stopSupportingTool.setGradeability(null);

        // Create the StopSupportingTool, which fails.

        restStopSupportingToolMockMvc.perform(post("/api/stop-supporting-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(stopSupportingTool)))
            .andExpect(status().isBadRequest());

        List<StopSupportingTool> stopSupportingToolList = stopSupportingToolRepository.findAll();
        assertThat(stopSupportingToolList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNumberOfBoomsIsRequired() throws Exception {
        int databaseSizeBeforeTest = stopSupportingToolRepository.findAll().size();
        // set the field null
        stopSupportingTool.setNumberOfBooms(null);

        // Create the StopSupportingTool, which fails.

        restStopSupportingToolMockMvc.perform(post("/api/stop-supporting-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(stopSupportingTool)))
            .andExpect(status().isBadRequest());

        List<StopSupportingTool> stopSupportingToolList = stopSupportingToolRepository.findAll();
        assertThat(stopSupportingToolList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTypeOfBoomIsRequired() throws Exception {
        int databaseSizeBeforeTest = stopSupportingToolRepository.findAll().size();
        // set the field null
        stopSupportingTool.setTypeOfBoom(null);

        // Create the StopSupportingTool, which fails.

        restStopSupportingToolMockMvc.perform(post("/api/stop-supporting-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(stopSupportingTool)))
            .andExpect(status().isBadRequest());

        List<StopSupportingTool> stopSupportingToolList = stopSupportingToolRepository.findAll();
        assertThat(stopSupportingToolList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkOuterTurningRadiusIsRequired() throws Exception {
        int databaseSizeBeforeTest = stopSupportingToolRepository.findAll().size();
        // set the field null
        stopSupportingTool.setOuterTurningRadius(null);

        // Create the StopSupportingTool, which fails.

        restStopSupportingToolMockMvc.perform(post("/api/stop-supporting-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(stopSupportingTool)))
            .andExpect(status().isBadRequest());

        List<StopSupportingTool> stopSupportingToolList = stopSupportingToolRepository.findAll();
        assertThat(stopSupportingToolList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkInnerTurningRadiusIsRequired() throws Exception {
        int databaseSizeBeforeTest = stopSupportingToolRepository.findAll().size();
        // set the field null
        stopSupportingTool.setInnerTurningRadius(null);

        // Create the StopSupportingTool, which fails.

        restStopSupportingToolMockMvc.perform(post("/api/stop-supporting-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(stopSupportingTool)))
            .andExpect(status().isBadRequest());

        List<StopSupportingTool> stopSupportingToolList = stopSupportingToolRepository.findAll();
        assertThat(stopSupportingToolList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkObservations1IsRequired() throws Exception {
        int databaseSizeBeforeTest = stopSupportingToolRepository.findAll().size();
        // set the field null
        stopSupportingTool.setObservations1(null);

        // Create the StopSupportingTool, which fails.

        restStopSupportingToolMockMvc.perform(post("/api/stop-supporting-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(stopSupportingTool)))
            .andExpect(status().isBadRequest());

        List<StopSupportingTool> stopSupportingToolList = stopSupportingToolRepository.findAll();
        assertThat(stopSupportingToolList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkObservations2IsRequired() throws Exception {
        int databaseSizeBeforeTest = stopSupportingToolRepository.findAll().size();
        // set the field null
        stopSupportingTool.setObservations2(null);

        // Create the StopSupportingTool, which fails.

        restStopSupportingToolMockMvc.perform(post("/api/stop-supporting-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(stopSupportingTool)))
            .andExpect(status().isBadRequest());

        List<StopSupportingTool> stopSupportingToolList = stopSupportingToolRepository.findAll();
        assertThat(stopSupportingToolList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkObservations3IsRequired() throws Exception {
        int databaseSizeBeforeTest = stopSupportingToolRepository.findAll().size();
        // set the field null
        stopSupportingTool.setObservations3(null);

        // Create the StopSupportingTool, which fails.

        restStopSupportingToolMockMvc.perform(post("/api/stop-supporting-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(stopSupportingTool)))
            .andExpect(status().isBadRequest());

        List<StopSupportingTool> stopSupportingToolList = stopSupportingToolRepository.findAll();
        assertThat(stopSupportingToolList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkObservations4IsRequired() throws Exception {
        int databaseSizeBeforeTest = stopSupportingToolRepository.findAll().size();
        // set the field null
        stopSupportingTool.setObservations4(null);

        // Create the StopSupportingTool, which fails.

        restStopSupportingToolMockMvc.perform(post("/api/stop-supporting-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(stopSupportingTool)))
            .andExpect(status().isBadRequest());

        List<StopSupportingTool> stopSupportingToolList = stopSupportingToolRepository.findAll();
        assertThat(stopSupportingToolList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkObservations5IsRequired() throws Exception {
        int databaseSizeBeforeTest = stopSupportingToolRepository.findAll().size();
        // set the field null
        stopSupportingTool.setObservations5(null);

        // Create the StopSupportingTool, which fails.

        restStopSupportingToolMockMvc.perform(post("/api/stop-supporting-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(stopSupportingTool)))
            .andExpect(status().isBadRequest());

        List<StopSupportingTool> stopSupportingToolList = stopSupportingToolRepository.findAll();
        assertThat(stopSupportingToolList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkObservations6IsRequired() throws Exception {
        int databaseSizeBeforeTest = stopSupportingToolRepository.findAll().size();
        // set the field null
        stopSupportingTool.setObservations6(null);

        // Create the StopSupportingTool, which fails.

        restStopSupportingToolMockMvc.perform(post("/api/stop-supporting-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(stopSupportingTool)))
            .andExpect(status().isBadRequest());

        List<StopSupportingTool> stopSupportingToolList = stopSupportingToolRepository.findAll();
        assertThat(stopSupportingToolList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkOperators_CommentsIsRequired() throws Exception {
        int databaseSizeBeforeTest = stopSupportingToolRepository.findAll().size();
        // set the field null
        stopSupportingTool.setOperators_Comments(null);

        // Create the StopSupportingTool, which fails.

        restStopSupportingToolMockMvc.perform(post("/api/stop-supporting-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(stopSupportingTool)))
            .andExpect(status().isBadRequest());

        List<StopSupportingTool> stopSupportingToolList = stopSupportingToolRepository.findAll();
        assertThat(stopSupportingToolList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllStopSupportingTools() throws Exception {
        // Initialize the database
        stopSupportingToolRepository.saveAndFlush(stopSupportingTool);

        // Get all the stopSupportingToolList
        restStopSupportingToolMockMvc.perform(get("/api/stop-supporting-tools?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(stopSupportingTool.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].model").value(hasItem(DEFAULT_MODEL.toString())))
            .andExpect(jsonPath("$.[*].technologyType").value(hasItem(DEFAULT_TECHNOLOGY_TYPE.toString())))
            .andExpect(jsonPath("$.[*].trl").value(hasItem(DEFAULT_TRL)))
            .andExpect(jsonPath("$.[*].photoContentType").value(hasItem(DEFAULT_PHOTO_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].photo").value(hasItem(Base64Utils.encodeToString(DEFAULT_PHOTO))))
            .andExpect(jsonPath("$.[*].datasheetContentType").value(hasItem(DEFAULT_DATASHEET_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].datasheet").value(hasItem(Base64Utils.encodeToString(DEFAULT_DATASHEET))))
            .andExpect(jsonPath("$.[*].typeOfMachine").value(hasItem(DEFAULT_TYPE_OF_MACHINE.toString())))
            .andExpect(jsonPath("$.[*].testedTypeOfMachine").value(hasItem(DEFAULT_TESTED_TYPE_OF_MACHINE.toString())))
            .andExpect(jsonPath("$.[*].weight").value(hasItem(DEFAULT_WEIGHT.doubleValue())))
            .andExpect(jsonPath("$.[*].testedWeight").value(hasItem(DEFAULT_TESTED_WEIGHT.doubleValue())))
            .andExpect(jsonPath("$.[*].length").value(hasItem(DEFAULT_LENGTH.doubleValue())))
            .andExpect(jsonPath("$.[*].testedLength").value(hasItem(DEFAULT_TESTED_LENGTH.doubleValue())))
            .andExpect(jsonPath("$.[*].width").value(hasItem(DEFAULT_WIDTH.doubleValue())))
            .andExpect(jsonPath("$.[*].testedWidth").value(hasItem(DEFAULT_TESTED_WIDTH.doubleValue())))
            .andExpect(jsonPath("$.[*].holeSize").value(hasItem(DEFAULT_HOLE_SIZE.doubleValue())))
            .andExpect(jsonPath("$.[*].testedHoleSize").value(hasItem(DEFAULT_TESTED_HOLE_SIZE.doubleValue())))
            .andExpect(jsonPath("$.[*].drillWaterConsumption").value(hasItem(DEFAULT_DRILL_WATER_CONSUMPTION.doubleValue())))
            .andExpect(jsonPath("$.[*].testedDrillWaterConsumption").value(hasItem(DEFAULT_TESTED_DRILL_WATER_CONSUMPTION.doubleValue())))
            .andExpect(jsonPath("$.[*].cycleTimeBolting").value(hasItem(DEFAULT_CYCLE_TIME_BOLTING.doubleValue())))
            .andExpect(jsonPath("$.[*].testedCycleTimeBolting").value(hasItem(DEFAULT_TESTED_CYCLE_TIME_BOLTING.doubleValue())))
            .andExpect(jsonPath("$.[*].waterConsumptionPerMetreDrilled").value(hasItem(DEFAULT_WATER_CONSUMPTION_PER_METRE_DRILLED.doubleValue())))
            .andExpect(jsonPath("$.[*].testedWaterConsumptionPerMetreDrilled").value(hasItem(DEFAULT_TESTED_WATER_CONSUMPTION_PER_METRE_DRILLED.doubleValue())))
            .andExpect(jsonPath("$.[*].powerSource").value(hasItem(DEFAULT_POWER_SOURCE.toString())))
            .andExpect(jsonPath("$.[*].testedPowerSource").value(hasItem(DEFAULT_TESTED_POWER_SOURCE.toString())))
            .andExpect(jsonPath("$.[*].trammingSpeed").value(hasItem(DEFAULT_TRAMMING_SPEED.doubleValue())))
            .andExpect(jsonPath("$.[*].testedTrammingSpeed").value(hasItem(DEFAULT_TESTED_TRAMMING_SPEED.doubleValue())))
            .andExpect(jsonPath("$.[*].boltLengthRange").value(hasItem(DEFAULT_BOLT_LENGTH_RANGE.toString())))
            .andExpect(jsonPath("$.[*].testedBoltLengthRange").value(hasItem(DEFAULT_TESTED_BOLT_LENGTH_RANGE.toString())))
            .andExpect(jsonPath("$.[*].drillSpeed").value(hasItem(DEFAULT_DRILL_SPEED.doubleValue())))
            .andExpect(jsonPath("$.[*].testedDrillSpeed").value(hasItem(DEFAULT_TESTED_DRILL_SPEED.doubleValue())))
            .andExpect(jsonPath("$.[*].gradeability").value(hasItem(DEFAULT_GRADEABILITY.doubleValue())))
            .andExpect(jsonPath("$.[*].testedGradeability").value(hasItem(DEFAULT_TESTED_GRADEABILITY.doubleValue())))
            .andExpect(jsonPath("$.[*].numberOfBooms").value(hasItem(DEFAULT_NUMBER_OF_BOOMS.doubleValue())))
            .andExpect(jsonPath("$.[*].testedNumberOfBooms").value(hasItem(DEFAULT_TESTED_NUMBER_OF_BOOMS.doubleValue())))
            .andExpect(jsonPath("$.[*].typeOfBoom").value(hasItem(DEFAULT_TYPE_OF_BOOM.toString())))
            .andExpect(jsonPath("$.[*].testedTypeOfBoom").value(hasItem(DEFAULT_TESTED_TYPE_OF_BOOM.toString())))
            .andExpect(jsonPath("$.[*].outerTurningRadius").value(hasItem(DEFAULT_OUTER_TURNING_RADIUS.doubleValue())))
            .andExpect(jsonPath("$.[*].testedOuterTurningRadius").value(hasItem(DEFAULT_TESTED_OUTER_TURNING_RADIUS.doubleValue())))
            .andExpect(jsonPath("$.[*].innerTurningRadius").value(hasItem(DEFAULT_INNER_TURNING_RADIUS.doubleValue())))
            .andExpect(jsonPath("$.[*].testedInnerTurningRadius").value(hasItem(DEFAULT_TESTED_INNER_TURNING_RADIUS.doubleValue())))
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
    public void getStopSupportingTool() throws Exception {
        // Initialize the database
        stopSupportingToolRepository.saveAndFlush(stopSupportingTool);

        // Get the stopSupportingTool
        restStopSupportingToolMockMvc.perform(get("/api/stop-supporting-tools/{id}", stopSupportingTool.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(stopSupportingTool.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.model").value(DEFAULT_MODEL.toString()))
            .andExpect(jsonPath("$.technologyType").value(DEFAULT_TECHNOLOGY_TYPE.toString()))
            .andExpect(jsonPath("$.trl").value(DEFAULT_TRL))
            .andExpect(jsonPath("$.photoContentType").value(DEFAULT_PHOTO_CONTENT_TYPE))
            .andExpect(jsonPath("$.photo").value(Base64Utils.encodeToString(DEFAULT_PHOTO)))
            .andExpect(jsonPath("$.datasheetContentType").value(DEFAULT_DATASHEET_CONTENT_TYPE))
            .andExpect(jsonPath("$.datasheet").value(Base64Utils.encodeToString(DEFAULT_DATASHEET)))
            .andExpect(jsonPath("$.typeOfMachine").value(DEFAULT_TYPE_OF_MACHINE.toString()))
            .andExpect(jsonPath("$.testedTypeOfMachine").value(DEFAULT_TESTED_TYPE_OF_MACHINE.toString()))
            .andExpect(jsonPath("$.weight").value(DEFAULT_WEIGHT.doubleValue()))
            .andExpect(jsonPath("$.testedWeight").value(DEFAULT_TESTED_WEIGHT.doubleValue()))
            .andExpect(jsonPath("$.length").value(DEFAULT_LENGTH.doubleValue()))
            .andExpect(jsonPath("$.testedLength").value(DEFAULT_TESTED_LENGTH.doubleValue()))
            .andExpect(jsonPath("$.width").value(DEFAULT_WIDTH.doubleValue()))
            .andExpect(jsonPath("$.testedWidth").value(DEFAULT_TESTED_WIDTH.doubleValue()))
            .andExpect(jsonPath("$.holeSize").value(DEFAULT_HOLE_SIZE.doubleValue()))
            .andExpect(jsonPath("$.testedHoleSize").value(DEFAULT_TESTED_HOLE_SIZE.doubleValue()))
            .andExpect(jsonPath("$.drillWaterConsumption").value(DEFAULT_DRILL_WATER_CONSUMPTION.doubleValue()))
            .andExpect(jsonPath("$.testedDrillWaterConsumption").value(DEFAULT_TESTED_DRILL_WATER_CONSUMPTION.doubleValue()))
            .andExpect(jsonPath("$.cycleTimeBolting").value(DEFAULT_CYCLE_TIME_BOLTING.doubleValue()))
            .andExpect(jsonPath("$.testedCycleTimeBolting").value(DEFAULT_TESTED_CYCLE_TIME_BOLTING.doubleValue()))
            .andExpect(jsonPath("$.waterConsumptionPerMetreDrilled").value(DEFAULT_WATER_CONSUMPTION_PER_METRE_DRILLED.doubleValue()))
            .andExpect(jsonPath("$.testedWaterConsumptionPerMetreDrilled").value(DEFAULT_TESTED_WATER_CONSUMPTION_PER_METRE_DRILLED.doubleValue()))
            .andExpect(jsonPath("$.powerSource").value(DEFAULT_POWER_SOURCE.toString()))
            .andExpect(jsonPath("$.testedPowerSource").value(DEFAULT_TESTED_POWER_SOURCE.toString()))
            .andExpect(jsonPath("$.trammingSpeed").value(DEFAULT_TRAMMING_SPEED.doubleValue()))
            .andExpect(jsonPath("$.testedTrammingSpeed").value(DEFAULT_TESTED_TRAMMING_SPEED.doubleValue()))
            .andExpect(jsonPath("$.boltLengthRange").value(DEFAULT_BOLT_LENGTH_RANGE.toString()))
            .andExpect(jsonPath("$.testedBoltLengthRange").value(DEFAULT_TESTED_BOLT_LENGTH_RANGE.toString()))
            .andExpect(jsonPath("$.drillSpeed").value(DEFAULT_DRILL_SPEED.doubleValue()))
            .andExpect(jsonPath("$.testedDrillSpeed").value(DEFAULT_TESTED_DRILL_SPEED.doubleValue()))
            .andExpect(jsonPath("$.gradeability").value(DEFAULT_GRADEABILITY.doubleValue()))
            .andExpect(jsonPath("$.testedGradeability").value(DEFAULT_TESTED_GRADEABILITY.doubleValue()))
            .andExpect(jsonPath("$.numberOfBooms").value(DEFAULT_NUMBER_OF_BOOMS.doubleValue()))
            .andExpect(jsonPath("$.testedNumberOfBooms").value(DEFAULT_TESTED_NUMBER_OF_BOOMS.doubleValue()))
            .andExpect(jsonPath("$.typeOfBoom").value(DEFAULT_TYPE_OF_BOOM.toString()))
            .andExpect(jsonPath("$.testedTypeOfBoom").value(DEFAULT_TESTED_TYPE_OF_BOOM.toString()))
            .andExpect(jsonPath("$.outerTurningRadius").value(DEFAULT_OUTER_TURNING_RADIUS.doubleValue()))
            .andExpect(jsonPath("$.testedOuterTurningRadius").value(DEFAULT_TESTED_OUTER_TURNING_RADIUS.doubleValue()))
            .andExpect(jsonPath("$.innerTurningRadius").value(DEFAULT_INNER_TURNING_RADIUS.doubleValue()))
            .andExpect(jsonPath("$.testedInnerTurningRadius").value(DEFAULT_TESTED_INNER_TURNING_RADIUS.doubleValue()))
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
    public void getNonExistingStopSupportingTool() throws Exception {
        // Get the stopSupportingTool
        restStopSupportingToolMockMvc.perform(get("/api/stop-supporting-tools/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateStopSupportingTool() throws Exception {
        // Initialize the database
        stopSupportingToolRepository.saveAndFlush(stopSupportingTool);
        stopSupportingToolSearchRepository.save(stopSupportingTool);
        int databaseSizeBeforeUpdate = stopSupportingToolRepository.findAll().size();

        // Update the stopSupportingTool
        StopSupportingTool updatedStopSupportingTool = stopSupportingToolRepository.findOne(stopSupportingTool.getId());
        updatedStopSupportingTool
            .name(UPDATED_NAME)
            .model(UPDATED_MODEL)
            .technologyType(UPDATED_TECHNOLOGY_TYPE)
            .trl(UPDATED_TRL)
            .photo(UPDATED_PHOTO)
            .photoContentType(UPDATED_PHOTO_CONTENT_TYPE)
            .datasheet(UPDATED_DATASHEET)
            .datasheetContentType(UPDATED_DATASHEET_CONTENT_TYPE)
            .typeOfMachine(UPDATED_TYPE_OF_MACHINE)
            .testedTypeOfMachine(UPDATED_TESTED_TYPE_OF_MACHINE)
            .weight(UPDATED_WEIGHT)
            .testedWeight(UPDATED_TESTED_WEIGHT)
            .length(UPDATED_LENGTH)
            .testedLength(UPDATED_TESTED_LENGTH)
            .width(UPDATED_WIDTH)
            .testedWidth(UPDATED_TESTED_WIDTH)
            .holeSize(UPDATED_HOLE_SIZE)
            .testedHoleSize(UPDATED_TESTED_HOLE_SIZE)
            .drillWaterConsumption(UPDATED_DRILL_WATER_CONSUMPTION)
            .testedDrillWaterConsumption(UPDATED_TESTED_DRILL_WATER_CONSUMPTION)
            .cycleTimeBolting(UPDATED_CYCLE_TIME_BOLTING)
            .testedCycleTimeBolting(UPDATED_TESTED_CYCLE_TIME_BOLTING)
            .waterConsumptionPerMetreDrilled(UPDATED_WATER_CONSUMPTION_PER_METRE_DRILLED)
            .testedWaterConsumptionPerMetreDrilled(UPDATED_TESTED_WATER_CONSUMPTION_PER_METRE_DRILLED)
            .powerSource(UPDATED_POWER_SOURCE)
            .testedPowerSource(UPDATED_TESTED_POWER_SOURCE)
            .trammingSpeed(UPDATED_TRAMMING_SPEED)
            .testedTrammingSpeed(UPDATED_TESTED_TRAMMING_SPEED)
            .boltLengthRange(UPDATED_BOLT_LENGTH_RANGE)
            .testedBoltLengthRange(UPDATED_TESTED_BOLT_LENGTH_RANGE)
            .drillSpeed(UPDATED_DRILL_SPEED)
            .testedDrillSpeed(UPDATED_TESTED_DRILL_SPEED)
            .gradeability(UPDATED_GRADEABILITY)
            .testedGradeability(UPDATED_TESTED_GRADEABILITY)
            .numberOfBooms(UPDATED_NUMBER_OF_BOOMS)
            .testedNumberOfBooms(UPDATED_TESTED_NUMBER_OF_BOOMS)
            .typeOfBoom(UPDATED_TYPE_OF_BOOM)
            .testedTypeOfBoom(UPDATED_TESTED_TYPE_OF_BOOM)
            .outerTurningRadius(UPDATED_OUTER_TURNING_RADIUS)
            .testedOuterTurningRadius(UPDATED_TESTED_OUTER_TURNING_RADIUS)
            .innerTurningRadius(UPDATED_INNER_TURNING_RADIUS)
            .testedInnerTurningRadius(UPDATED_TESTED_INNER_TURNING_RADIUS)
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

        restStopSupportingToolMockMvc.perform(put("/api/stop-supporting-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedStopSupportingTool)))
            .andExpect(status().isOk());

        // Validate the StopSupportingTool in the database
        List<StopSupportingTool> stopSupportingToolList = stopSupportingToolRepository.findAll();
        assertThat(stopSupportingToolList).hasSize(databaseSizeBeforeUpdate);
        StopSupportingTool testStopSupportingTool = stopSupportingToolList.get(stopSupportingToolList.size() - 1);
        assertThat(testStopSupportingTool.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testStopSupportingTool.getModel()).isEqualTo(UPDATED_MODEL);
        assertThat(testStopSupportingTool.getTechnologyType()).isEqualTo(UPDATED_TECHNOLOGY_TYPE);
        assertThat(testStopSupportingTool.getTrl()).isEqualTo(UPDATED_TRL);
        assertThat(testStopSupportingTool.getPhoto()).isEqualTo(UPDATED_PHOTO);
        assertThat(testStopSupportingTool.getPhotoContentType()).isEqualTo(UPDATED_PHOTO_CONTENT_TYPE);
        assertThat(testStopSupportingTool.getDatasheet()).isEqualTo(UPDATED_DATASHEET);
        assertThat(testStopSupportingTool.getDatasheetContentType()).isEqualTo(UPDATED_DATASHEET_CONTENT_TYPE);
        assertThat(testStopSupportingTool.getTypeOfMachine()).isEqualTo(UPDATED_TYPE_OF_MACHINE);
        assertThat(testStopSupportingTool.getTestedTypeOfMachine()).isEqualTo(UPDATED_TESTED_TYPE_OF_MACHINE);
        assertThat(testStopSupportingTool.getWeight()).isEqualTo(UPDATED_WEIGHT);
        assertThat(testStopSupportingTool.getTestedWeight()).isEqualTo(UPDATED_TESTED_WEIGHT);
        assertThat(testStopSupportingTool.getLength()).isEqualTo(UPDATED_LENGTH);
        assertThat(testStopSupportingTool.getTestedLength()).isEqualTo(UPDATED_TESTED_LENGTH);
        assertThat(testStopSupportingTool.getWidth()).isEqualTo(UPDATED_WIDTH);
        assertThat(testStopSupportingTool.getTestedWidth()).isEqualTo(UPDATED_TESTED_WIDTH);
        assertThat(testStopSupportingTool.getHoleSize()).isEqualTo(UPDATED_HOLE_SIZE);
        assertThat(testStopSupportingTool.getTestedHoleSize()).isEqualTo(UPDATED_TESTED_HOLE_SIZE);
        assertThat(testStopSupportingTool.getDrillWaterConsumption()).isEqualTo(UPDATED_DRILL_WATER_CONSUMPTION);
        assertThat(testStopSupportingTool.getTestedDrillWaterConsumption()).isEqualTo(UPDATED_TESTED_DRILL_WATER_CONSUMPTION);
        assertThat(testStopSupportingTool.getCycleTimeBolting()).isEqualTo(UPDATED_CYCLE_TIME_BOLTING);
        assertThat(testStopSupportingTool.getTestedCycleTimeBolting()).isEqualTo(UPDATED_TESTED_CYCLE_TIME_BOLTING);
        assertThat(testStopSupportingTool.getWaterConsumptionPerMetreDrilled()).isEqualTo(UPDATED_WATER_CONSUMPTION_PER_METRE_DRILLED);
        assertThat(testStopSupportingTool.getTestedWaterConsumptionPerMetreDrilled()).isEqualTo(UPDATED_TESTED_WATER_CONSUMPTION_PER_METRE_DRILLED);
        assertThat(testStopSupportingTool.getPowerSource()).isEqualTo(UPDATED_POWER_SOURCE);
        assertThat(testStopSupportingTool.getTestedPowerSource()).isEqualTo(UPDATED_TESTED_POWER_SOURCE);
        assertThat(testStopSupportingTool.getTrammingSpeed()).isEqualTo(UPDATED_TRAMMING_SPEED);
        assertThat(testStopSupportingTool.getTestedTrammingSpeed()).isEqualTo(UPDATED_TESTED_TRAMMING_SPEED);
        assertThat(testStopSupportingTool.getBoltLengthRange()).isEqualTo(UPDATED_BOLT_LENGTH_RANGE);
        assertThat(testStopSupportingTool.getTestedBoltLengthRange()).isEqualTo(UPDATED_TESTED_BOLT_LENGTH_RANGE);
        assertThat(testStopSupportingTool.getDrillSpeed()).isEqualTo(UPDATED_DRILL_SPEED);
        assertThat(testStopSupportingTool.getTestedDrillSpeed()).isEqualTo(UPDATED_TESTED_DRILL_SPEED);
        assertThat(testStopSupportingTool.getGradeability()).isEqualTo(UPDATED_GRADEABILITY);
        assertThat(testStopSupportingTool.getTestedGradeability()).isEqualTo(UPDATED_TESTED_GRADEABILITY);
        assertThat(testStopSupportingTool.getNumberOfBooms()).isEqualTo(UPDATED_NUMBER_OF_BOOMS);
        assertThat(testStopSupportingTool.getTestedNumberOfBooms()).isEqualTo(UPDATED_TESTED_NUMBER_OF_BOOMS);
        assertThat(testStopSupportingTool.getTypeOfBoom()).isEqualTo(UPDATED_TYPE_OF_BOOM);
        assertThat(testStopSupportingTool.getTestedTypeOfBoom()).isEqualTo(UPDATED_TESTED_TYPE_OF_BOOM);
        assertThat(testStopSupportingTool.getOuterTurningRadius()).isEqualTo(UPDATED_OUTER_TURNING_RADIUS);
        assertThat(testStopSupportingTool.getTestedOuterTurningRadius()).isEqualTo(UPDATED_TESTED_OUTER_TURNING_RADIUS);
        assertThat(testStopSupportingTool.getInnerTurningRadius()).isEqualTo(UPDATED_INNER_TURNING_RADIUS);
        assertThat(testStopSupportingTool.getTestedInnerTurningRadius()).isEqualTo(UPDATED_TESTED_INNER_TURNING_RADIUS);
        assertThat(testStopSupportingTool.getObservations1()).isEqualTo(UPDATED_OBSERVATIONS_1);
        assertThat(testStopSupportingTool.getTestedObservations1()).isEqualTo(UPDATED_TESTED_OBSERVATIONS_1);
        assertThat(testStopSupportingTool.getObservations2()).isEqualTo(UPDATED_OBSERVATIONS_2);
        assertThat(testStopSupportingTool.getTestedObservations2()).isEqualTo(UPDATED_TESTED_OBSERVATIONS_2);
        assertThat(testStopSupportingTool.getObservations3()).isEqualTo(UPDATED_OBSERVATIONS_3);
        assertThat(testStopSupportingTool.getTestedObservations3()).isEqualTo(UPDATED_TESTED_OBSERVATIONS_3);
        assertThat(testStopSupportingTool.getObservations4()).isEqualTo(UPDATED_OBSERVATIONS_4);
        assertThat(testStopSupportingTool.getTestedObservations4()).isEqualTo(UPDATED_TESTED_OBSERVATIONS_4);
        assertThat(testStopSupportingTool.getObservations5()).isEqualTo(UPDATED_OBSERVATIONS_5);
        assertThat(testStopSupportingTool.getTestedObservations5()).isEqualTo(UPDATED_TESTED_OBSERVATIONS_5);
        assertThat(testStopSupportingTool.getObservations6()).isEqualTo(UPDATED_OBSERVATIONS_6);
        assertThat(testStopSupportingTool.getTestedObservations6()).isEqualTo(UPDATED_TESTED_OBSERVATIONS_6);
        assertThat(testStopSupportingTool.getOperators_Comments()).isEqualTo(UPDATED_OPERATORS_COMMENTS);
        assertThat(testStopSupportingTool.getTestedOperators_Comments()).isEqualTo(UPDATED_TESTED_OPERATORS_COMMENTS);

        // Validate the StopSupportingTool in Elasticsearch
        StopSupportingTool stopSupportingToolEs = stopSupportingToolSearchRepository.findOne(testStopSupportingTool.getId());
        assertThat(stopSupportingToolEs).isEqualToComparingFieldByField(testStopSupportingTool);
    }

    @Test
    @Transactional
    public void updateNonExistingStopSupportingTool() throws Exception {
        int databaseSizeBeforeUpdate = stopSupportingToolRepository.findAll().size();

        // Create the StopSupportingTool

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restStopSupportingToolMockMvc.perform(put("/api/stop-supporting-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(stopSupportingTool)))
            .andExpect(status().isCreated());

        // Validate the StopSupportingTool in the database
        List<StopSupportingTool> stopSupportingToolList = stopSupportingToolRepository.findAll();
        assertThat(stopSupportingToolList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteStopSupportingTool() throws Exception {
        // Initialize the database
        stopSupportingToolRepository.saveAndFlush(stopSupportingTool);
        stopSupportingToolSearchRepository.save(stopSupportingTool);
        int databaseSizeBeforeDelete = stopSupportingToolRepository.findAll().size();

        // Get the stopSupportingTool
        restStopSupportingToolMockMvc.perform(delete("/api/stop-supporting-tools/{id}", stopSupportingTool.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate Elasticsearch is empty
        boolean stopSupportingToolExistsInEs = stopSupportingToolSearchRepository.exists(stopSupportingTool.getId());
        assertThat(stopSupportingToolExistsInEs).isFalse();

        // Validate the database is empty
        List<StopSupportingTool> stopSupportingToolList = stopSupportingToolRepository.findAll();
        assertThat(stopSupportingToolList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void searchStopSupportingTool() throws Exception {
        // Initialize the database
        stopSupportingToolRepository.saveAndFlush(stopSupportingTool);
        stopSupportingToolSearchRepository.save(stopSupportingTool);

        // Search the stopSupportingTool
        restStopSupportingToolMockMvc.perform(get("/api/_search/stop-supporting-tools?query=id:" + stopSupportingTool.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(stopSupportingTool.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].model").value(hasItem(DEFAULT_MODEL.toString())))
            .andExpect(jsonPath("$.[*].technologyType").value(hasItem(DEFAULT_TECHNOLOGY_TYPE.toString())))
            .andExpect(jsonPath("$.[*].trl").value(hasItem(DEFAULT_TRL)))
            .andExpect(jsonPath("$.[*].photoContentType").value(hasItem(DEFAULT_PHOTO_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].photo").value(hasItem(Base64Utils.encodeToString(DEFAULT_PHOTO))))
            .andExpect(jsonPath("$.[*].datasheetContentType").value(hasItem(DEFAULT_DATASHEET_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].datasheet").value(hasItem(Base64Utils.encodeToString(DEFAULT_DATASHEET))))
            .andExpect(jsonPath("$.[*].typeOfMachine").value(hasItem(DEFAULT_TYPE_OF_MACHINE.toString())))
            .andExpect(jsonPath("$.[*].testedTypeOfMachine").value(hasItem(DEFAULT_TESTED_TYPE_OF_MACHINE.toString())))
            .andExpect(jsonPath("$.[*].weight").value(hasItem(DEFAULT_WEIGHT.doubleValue())))
            .andExpect(jsonPath("$.[*].testedWeight").value(hasItem(DEFAULT_TESTED_WEIGHT.doubleValue())))
            .andExpect(jsonPath("$.[*].length").value(hasItem(DEFAULT_LENGTH.doubleValue())))
            .andExpect(jsonPath("$.[*].testedLength").value(hasItem(DEFAULT_TESTED_LENGTH.doubleValue())))
            .andExpect(jsonPath("$.[*].width").value(hasItem(DEFAULT_WIDTH.doubleValue())))
            .andExpect(jsonPath("$.[*].testedWidth").value(hasItem(DEFAULT_TESTED_WIDTH.doubleValue())))
            .andExpect(jsonPath("$.[*].holeSize").value(hasItem(DEFAULT_HOLE_SIZE.doubleValue())))
            .andExpect(jsonPath("$.[*].testedHoleSize").value(hasItem(DEFAULT_TESTED_HOLE_SIZE.doubleValue())))
            .andExpect(jsonPath("$.[*].drillWaterConsumption").value(hasItem(DEFAULT_DRILL_WATER_CONSUMPTION.doubleValue())))
            .andExpect(jsonPath("$.[*].testedDrillWaterConsumption").value(hasItem(DEFAULT_TESTED_DRILL_WATER_CONSUMPTION.doubleValue())))
            .andExpect(jsonPath("$.[*].cycleTimeBolting").value(hasItem(DEFAULT_CYCLE_TIME_BOLTING.doubleValue())))
            .andExpect(jsonPath("$.[*].testedCycleTimeBolting").value(hasItem(DEFAULT_TESTED_CYCLE_TIME_BOLTING.doubleValue())))
            .andExpect(jsonPath("$.[*].waterConsumptionPerMetreDrilled").value(hasItem(DEFAULT_WATER_CONSUMPTION_PER_METRE_DRILLED.doubleValue())))
            .andExpect(jsonPath("$.[*].testedWaterConsumptionPerMetreDrilled").value(hasItem(DEFAULT_TESTED_WATER_CONSUMPTION_PER_METRE_DRILLED.doubleValue())))
            .andExpect(jsonPath("$.[*].powerSource").value(hasItem(DEFAULT_POWER_SOURCE.toString())))
            .andExpect(jsonPath("$.[*].testedPowerSource").value(hasItem(DEFAULT_TESTED_POWER_SOURCE.toString())))
            .andExpect(jsonPath("$.[*].trammingSpeed").value(hasItem(DEFAULT_TRAMMING_SPEED.doubleValue())))
            .andExpect(jsonPath("$.[*].testedTrammingSpeed").value(hasItem(DEFAULT_TESTED_TRAMMING_SPEED.doubleValue())))
            .andExpect(jsonPath("$.[*].boltLengthRange").value(hasItem(DEFAULT_BOLT_LENGTH_RANGE.toString())))
            .andExpect(jsonPath("$.[*].testedBoltLengthRange").value(hasItem(DEFAULT_TESTED_BOLT_LENGTH_RANGE.toString())))
            .andExpect(jsonPath("$.[*].drillSpeed").value(hasItem(DEFAULT_DRILL_SPEED.doubleValue())))
            .andExpect(jsonPath("$.[*].testedDrillSpeed").value(hasItem(DEFAULT_TESTED_DRILL_SPEED.doubleValue())))
            .andExpect(jsonPath("$.[*].gradeability").value(hasItem(DEFAULT_GRADEABILITY.doubleValue())))
            .andExpect(jsonPath("$.[*].testedGradeability").value(hasItem(DEFAULT_TESTED_GRADEABILITY.doubleValue())))
            .andExpect(jsonPath("$.[*].numberOfBooms").value(hasItem(DEFAULT_NUMBER_OF_BOOMS.doubleValue())))
            .andExpect(jsonPath("$.[*].testedNumberOfBooms").value(hasItem(DEFAULT_TESTED_NUMBER_OF_BOOMS.doubleValue())))
            .andExpect(jsonPath("$.[*].typeOfBoom").value(hasItem(DEFAULT_TYPE_OF_BOOM.toString())))
            .andExpect(jsonPath("$.[*].testedTypeOfBoom").value(hasItem(DEFAULT_TESTED_TYPE_OF_BOOM.toString())))
            .andExpect(jsonPath("$.[*].outerTurningRadius").value(hasItem(DEFAULT_OUTER_TURNING_RADIUS.doubleValue())))
            .andExpect(jsonPath("$.[*].testedOuterTurningRadius").value(hasItem(DEFAULT_TESTED_OUTER_TURNING_RADIUS.doubleValue())))
            .andExpect(jsonPath("$.[*].innerTurningRadius").value(hasItem(DEFAULT_INNER_TURNING_RADIUS.doubleValue())))
            .andExpect(jsonPath("$.[*].testedInnerTurningRadius").value(hasItem(DEFAULT_TESTED_INNER_TURNING_RADIUS.doubleValue())))
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
        TestUtil.equalsVerifier(StopSupportingTool.class);
        StopSupportingTool stopSupportingTool1 = new StopSupportingTool();
        stopSupportingTool1.setId(1L);
        StopSupportingTool stopSupportingTool2 = new StopSupportingTool();
        stopSupportingTool2.setId(stopSupportingTool1.getId());
        assertThat(stopSupportingTool1).isEqualTo(stopSupportingTool2);
        stopSupportingTool2.setId(2L);
        assertThat(stopSupportingTool1).isNotEqualTo(stopSupportingTool2);
        stopSupportingTool1.setId(null);
        assertThat(stopSupportingTool1).isNotEqualTo(stopSupportingTool2);
    }
}
