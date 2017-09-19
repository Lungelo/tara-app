package com.tara.org.web.rest;

import com.tara.org.TaraApp;

import com.tara.org.domain.StopDrillingTool;
import com.tara.org.repository.StopDrillingToolRepository;
import com.tara.org.repository.search.StopDrillingToolSearchRepository;
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
 * Test class for the StopDrillingToolResource REST controller.
 *
 * @see StopDrillingToolResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TaraApp.class)
public class StopDrillingToolResourceIntTest {

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

    private static final Float DEFAULT_WEIGHT = 1F;
    private static final Float UPDATED_WEIGHT = 2F;

    private static final Float DEFAULT_TESTED_WEIGHT = 1F;
    private static final Float UPDATED_TESTED_WEIGHT = 2F;

    private static final Float DEFAULT_LENGTH = 1F;
    private static final Float UPDATED_LENGTH = 2F;

    private static final Float DEFAULT_TESTED_LENGTH = 1F;
    private static final Float UPDATED_TESTED_LENGTH = 2F;

    private static final Float DEFAULT_DRILLING_RATE = 1F;
    private static final Float UPDATED_DRILLING_RATE = 2F;

    private static final Float DEFAULT_TESTED_DRILLING_RATE = 1F;
    private static final Float UPDATED_TESTED_DRILLING_RATE = 2F;

    private static final Float DEFAULT_NOISE_LEVEL = 1F;
    private static final Float UPDATED_NOISE_LEVEL = 2F;

    private static final Float DEFAULT_TESTED_NOISE_LEVEL = 1F;
    private static final Float UPDATED_TESTED_NOISE_LEVEL = 2F;

    private static final Float DEFAULT_SETTING_UP_TIME = 1F;
    private static final Float UPDATED_SETTING_UP_TIME = 2F;

    private static final Float DEFAULT_TESTED_SETTING_UP_TIME = 1F;
    private static final Float UPDATED_TESTED_SETTING_UP_TIME = 2F;

    private static final Float DEFAULT_DISMANTLING_TIME = 1F;
    private static final Float UPDATED_DISMANTLING_TIME = 2F;

    private static final Float DEFAULT_TESTED_DISMANTLING_TIME = 1F;
    private static final Float UPDATED_TESTED_DISMANTLING_TIME = 2F;

    private static final Float DEFAULT_WATER_USE_PER_METRE_DRILLED = 1F;
    private static final Float UPDATED_WATER_USE_PER_METRE_DRILLED = 2F;

    private static final Float DEFAULT_TESTED_WATER_USE_PER_METRE_DRILLED = 1F;
    private static final Float UPDATED_TESTED_WATER_USE_PER_METRE_DRILLED = 2F;

    private static final Float DEFAULT_AVAILABILITY = 1F;
    private static final Float UPDATED_AVAILABILITY = 2F;

    private static final Float DEFAULT_TESTED_AVAILABILITY = 1F;
    private static final Float UPDATED_TESTED_AVAILABILITY = 2F;

    private static final Float DEFAULT_COST_PER_METER_DRILLED = 1F;
    private static final Float UPDATED_COST_PER_METER_DRILLED = 2F;

    private static final Float DEFAULT_TESTED_COST_PER_METER_DRILLED = 1F;
    private static final Float UPDATED_TESTED_COST_PER_METER_DRILLED = 2F;

    private static final String DEFAULT_HOLE_SIZE_RANGE = "AAAAAAAAAA";
    private static final String UPDATED_HOLE_SIZE_RANGE = "BBBBBBBBBB";

    private static final String DEFAULT_TESTED_HOLE_SIZE_RANGE = "AAAAAAAAAA";
    private static final String UPDATED_TESTED_HOLE_SIZE_RANGE = "BBBBBBBBBB";

    private static final String DEFAULT_POWER_SOURCE = "AAAAAAAAAA";
    private static final String UPDATED_POWER_SOURCE = "BBBBBBBBBB";

    private static final String DEFAULT_TESTED_POWER_SOURCE = "AAAAAAAAAA";
    private static final String UPDATED_TESTED_POWER_SOURCE = "BBBBBBBBBB";

    private static final Float DEFAULT_POWER_RATING = 1F;
    private static final Float UPDATED_POWER_RATING = 2F;

    private static final Float DEFAULT_TESTED_POWER_RATING = 1F;
    private static final Float UPDATED_TESTED_POWER_RATING = 2F;

    private static final BigDecimal DEFAULT_COST = new BigDecimal(1);
    private static final BigDecimal UPDATED_COST = new BigDecimal(2);

    private static final BigDecimal DEFAULT_TESTED_COST = new BigDecimal(1);
    private static final BigDecimal UPDATED_TESTED_COST = new BigDecimal(2);

    private static final Float DEFAULT_TRAMMING_SPEED = 1F;
    private static final Float UPDATED_TRAMMING_SPEED = 2F;

    private static final Float DEFAULT_TESTED_TRAMMING_SPEED = 1F;
    private static final Float UPDATED_TESTED_TRAMMING_SPEED = 2F;

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
    private StopDrillingToolRepository stopDrillingToolRepository;

    @Autowired
    private StopDrillingToolSearchRepository stopDrillingToolSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restStopDrillingToolMockMvc;

    private StopDrillingTool stopDrillingTool;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final StopDrillingToolResource stopDrillingToolResource = new StopDrillingToolResource(stopDrillingToolRepository, stopDrillingToolSearchRepository);
        this.restStopDrillingToolMockMvc = MockMvcBuilders.standaloneSetup(stopDrillingToolResource)
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
    public static StopDrillingTool createEntity(EntityManager em) {
        StopDrillingTool stopDrillingTool = new StopDrillingTool()
            .name(DEFAULT_NAME)
            .model(DEFAULT_MODEL)
            .technologyType(DEFAULT_TECHNOLOGY_TYPE)
            .trl(DEFAULT_TRL)
            .photo(DEFAULT_PHOTO)
            .photoContentType(DEFAULT_PHOTO_CONTENT_TYPE)
            .datasheet(DEFAULT_DATASHEET)
            .datasheetContentType(DEFAULT_DATASHEET_CONTENT_TYPE)
            .weight(DEFAULT_WEIGHT)
            .testedWeight(DEFAULT_TESTED_WEIGHT)
            .length(DEFAULT_LENGTH)
            .testedLength(DEFAULT_TESTED_LENGTH)
            .drillingRate(DEFAULT_DRILLING_RATE)
            .testedDrillingRate(DEFAULT_TESTED_DRILLING_RATE)
            .noiseLevel(DEFAULT_NOISE_LEVEL)
            .testedNoiseLevel(DEFAULT_TESTED_NOISE_LEVEL)
            .settingUpTime(DEFAULT_SETTING_UP_TIME)
            .testedSettingUpTime(DEFAULT_TESTED_SETTING_UP_TIME)
            .dismantlingTime(DEFAULT_DISMANTLING_TIME)
            .testedDismantlingTime(DEFAULT_TESTED_DISMANTLING_TIME)
            .waterUsePerMetreDrilled(DEFAULT_WATER_USE_PER_METRE_DRILLED)
            .testedWaterUsePerMetreDrilled(DEFAULT_TESTED_WATER_USE_PER_METRE_DRILLED)
            .availability(DEFAULT_AVAILABILITY)
            .testedAvailability(DEFAULT_TESTED_AVAILABILITY)
            .costPerMeterDrilled(DEFAULT_COST_PER_METER_DRILLED)
            .testedCostPerMeterDrilled(DEFAULT_TESTED_COST_PER_METER_DRILLED)
            .holeSizeRange(DEFAULT_HOLE_SIZE_RANGE)
            .testedHoleSizeRange(DEFAULT_TESTED_HOLE_SIZE_RANGE)
            .powerSource(DEFAULT_POWER_SOURCE)
            .testedPowerSource(DEFAULT_TESTED_POWER_SOURCE)
            .powerRating(DEFAULT_POWER_RATING)
            .testedPowerRating(DEFAULT_TESTED_POWER_RATING)
            .cost(DEFAULT_COST)
            .testedCost(DEFAULT_TESTED_COST)
            .trammingSpeed(DEFAULT_TRAMMING_SPEED)
            .testedTrammingSpeed(DEFAULT_TESTED_TRAMMING_SPEED)
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
        return stopDrillingTool;
    }

    @Before
    public void initTest() {
        stopDrillingToolSearchRepository.deleteAll();
        stopDrillingTool = createEntity(em);
    }

    @Test
    @Transactional
    public void createStopDrillingTool() throws Exception {
        int databaseSizeBeforeCreate = stopDrillingToolRepository.findAll().size();

        // Create the StopDrillingTool
        restStopDrillingToolMockMvc.perform(post("/api/stop-drilling-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(stopDrillingTool)))
            .andExpect(status().isCreated());

        // Validate the StopDrillingTool in the database
        List<StopDrillingTool> stopDrillingToolList = stopDrillingToolRepository.findAll();
        assertThat(stopDrillingToolList).hasSize(databaseSizeBeforeCreate + 1);
        StopDrillingTool testStopDrillingTool = stopDrillingToolList.get(stopDrillingToolList.size() - 1);
        assertThat(testStopDrillingTool.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testStopDrillingTool.getModel()).isEqualTo(DEFAULT_MODEL);
        assertThat(testStopDrillingTool.getTechnologyType()).isEqualTo(DEFAULT_TECHNOLOGY_TYPE);
        assertThat(testStopDrillingTool.getTrl()).isEqualTo(DEFAULT_TRL);
        assertThat(testStopDrillingTool.getPhoto()).isEqualTo(DEFAULT_PHOTO);
        assertThat(testStopDrillingTool.getPhotoContentType()).isEqualTo(DEFAULT_PHOTO_CONTENT_TYPE);
        assertThat(testStopDrillingTool.getDatasheet()).isEqualTo(DEFAULT_DATASHEET);
        assertThat(testStopDrillingTool.getDatasheetContentType()).isEqualTo(DEFAULT_DATASHEET_CONTENT_TYPE);
        assertThat(testStopDrillingTool.getWeight()).isEqualTo(DEFAULT_WEIGHT);
        assertThat(testStopDrillingTool.getTestedWeight()).isEqualTo(DEFAULT_TESTED_WEIGHT);
        assertThat(testStopDrillingTool.getLength()).isEqualTo(DEFAULT_LENGTH);
        assertThat(testStopDrillingTool.getTestedLength()).isEqualTo(DEFAULT_TESTED_LENGTH);
        assertThat(testStopDrillingTool.getDrillingRate()).isEqualTo(DEFAULT_DRILLING_RATE);
        assertThat(testStopDrillingTool.getTestedDrillingRate()).isEqualTo(DEFAULT_TESTED_DRILLING_RATE);
        assertThat(testStopDrillingTool.getNoiseLevel()).isEqualTo(DEFAULT_NOISE_LEVEL);
        assertThat(testStopDrillingTool.getTestedNoiseLevel()).isEqualTo(DEFAULT_TESTED_NOISE_LEVEL);
        assertThat(testStopDrillingTool.getSettingUpTime()).isEqualTo(DEFAULT_SETTING_UP_TIME);
        assertThat(testStopDrillingTool.getTestedSettingUpTime()).isEqualTo(DEFAULT_TESTED_SETTING_UP_TIME);
        assertThat(testStopDrillingTool.getDismantlingTime()).isEqualTo(DEFAULT_DISMANTLING_TIME);
        assertThat(testStopDrillingTool.getTestedDismantlingTime()).isEqualTo(DEFAULT_TESTED_DISMANTLING_TIME);
        assertThat(testStopDrillingTool.getWaterUsePerMetreDrilled()).isEqualTo(DEFAULT_WATER_USE_PER_METRE_DRILLED);
        assertThat(testStopDrillingTool.getTestedWaterUsePerMetreDrilled()).isEqualTo(DEFAULT_TESTED_WATER_USE_PER_METRE_DRILLED);
        assertThat(testStopDrillingTool.getAvailability()).isEqualTo(DEFAULT_AVAILABILITY);
        assertThat(testStopDrillingTool.getTestedAvailability()).isEqualTo(DEFAULT_TESTED_AVAILABILITY);
        assertThat(testStopDrillingTool.getCostPerMeterDrilled()).isEqualTo(DEFAULT_COST_PER_METER_DRILLED);
        assertThat(testStopDrillingTool.getTestedCostPerMeterDrilled()).isEqualTo(DEFAULT_TESTED_COST_PER_METER_DRILLED);
        assertThat(testStopDrillingTool.getHoleSizeRange()).isEqualTo(DEFAULT_HOLE_SIZE_RANGE);
        assertThat(testStopDrillingTool.getTestedHoleSizeRange()).isEqualTo(DEFAULT_TESTED_HOLE_SIZE_RANGE);
        assertThat(testStopDrillingTool.getPowerSource()).isEqualTo(DEFAULT_POWER_SOURCE);
        assertThat(testStopDrillingTool.getTestedPowerSource()).isEqualTo(DEFAULT_TESTED_POWER_SOURCE);
        assertThat(testStopDrillingTool.getPowerRating()).isEqualTo(DEFAULT_POWER_RATING);
        assertThat(testStopDrillingTool.getTestedPowerRating()).isEqualTo(DEFAULT_TESTED_POWER_RATING);
        assertThat(testStopDrillingTool.getCost()).isEqualTo(DEFAULT_COST);
        assertThat(testStopDrillingTool.getTestedCost()).isEqualTo(DEFAULT_TESTED_COST);
        assertThat(testStopDrillingTool.getTrammingSpeed()).isEqualTo(DEFAULT_TRAMMING_SPEED);
        assertThat(testStopDrillingTool.getTestedTrammingSpeed()).isEqualTo(DEFAULT_TESTED_TRAMMING_SPEED);
        assertThat(testStopDrillingTool.getGradeability()).isEqualTo(DEFAULT_GRADEABILITY);
        assertThat(testStopDrillingTool.getTestedGradeability()).isEqualTo(DEFAULT_TESTED_GRADEABILITY);
        assertThat(testStopDrillingTool.getNumberOfBooms()).isEqualTo(DEFAULT_NUMBER_OF_BOOMS);
        assertThat(testStopDrillingTool.getTestedNumberOfBooms()).isEqualTo(DEFAULT_TESTED_NUMBER_OF_BOOMS);
        assertThat(testStopDrillingTool.getTypeOfBoom()).isEqualTo(DEFAULT_TYPE_OF_BOOM);
        assertThat(testStopDrillingTool.getTestedTypeOfBoom()).isEqualTo(DEFAULT_TESTED_TYPE_OF_BOOM);
        assertThat(testStopDrillingTool.getOuterTurningRadius()).isEqualTo(DEFAULT_OUTER_TURNING_RADIUS);
        assertThat(testStopDrillingTool.getTestedOuterTurningRadius()).isEqualTo(DEFAULT_TESTED_OUTER_TURNING_RADIUS);
        assertThat(testStopDrillingTool.getInnerTurningRadius()).isEqualTo(DEFAULT_INNER_TURNING_RADIUS);
        assertThat(testStopDrillingTool.getTestedInnerTurningRadius()).isEqualTo(DEFAULT_TESTED_INNER_TURNING_RADIUS);
        assertThat(testStopDrillingTool.getObservations1()).isEqualTo(DEFAULT_OBSERVATIONS_1);
        assertThat(testStopDrillingTool.getTestedObservations1()).isEqualTo(DEFAULT_TESTED_OBSERVATIONS_1);
        assertThat(testStopDrillingTool.getObservations2()).isEqualTo(DEFAULT_OBSERVATIONS_2);
        assertThat(testStopDrillingTool.getTestedObservations2()).isEqualTo(DEFAULT_TESTED_OBSERVATIONS_2);
        assertThat(testStopDrillingTool.getObservations3()).isEqualTo(DEFAULT_OBSERVATIONS_3);
        assertThat(testStopDrillingTool.getTestedObservations3()).isEqualTo(DEFAULT_TESTED_OBSERVATIONS_3);
        assertThat(testStopDrillingTool.getObservations4()).isEqualTo(DEFAULT_OBSERVATIONS_4);
        assertThat(testStopDrillingTool.getTestedObservations4()).isEqualTo(DEFAULT_TESTED_OBSERVATIONS_4);
        assertThat(testStopDrillingTool.getObservations5()).isEqualTo(DEFAULT_OBSERVATIONS_5);
        assertThat(testStopDrillingTool.getTestedObservations5()).isEqualTo(DEFAULT_TESTED_OBSERVATIONS_5);
        assertThat(testStopDrillingTool.getObservations6()).isEqualTo(DEFAULT_OBSERVATIONS_6);
        assertThat(testStopDrillingTool.getTestedObservations6()).isEqualTo(DEFAULT_TESTED_OBSERVATIONS_6);
        assertThat(testStopDrillingTool.getOperators_Comments()).isEqualTo(DEFAULT_OPERATORS_COMMENTS);
        assertThat(testStopDrillingTool.getTestedOperators_Comments()).isEqualTo(DEFAULT_TESTED_OPERATORS_COMMENTS);

        // Validate the StopDrillingTool in Elasticsearch
        StopDrillingTool stopDrillingToolEs = stopDrillingToolSearchRepository.findOne(testStopDrillingTool.getId());
        assertThat(stopDrillingToolEs).isEqualToComparingFieldByField(testStopDrillingTool);
    }

    @Test
    @Transactional
    public void createStopDrillingToolWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = stopDrillingToolRepository.findAll().size();

        // Create the StopDrillingTool with an existing ID
        stopDrillingTool.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restStopDrillingToolMockMvc.perform(post("/api/stop-drilling-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(stopDrillingTool)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<StopDrillingTool> stopDrillingToolList = stopDrillingToolRepository.findAll();
        assertThat(stopDrillingToolList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = stopDrillingToolRepository.findAll().size();
        // set the field null
        stopDrillingTool.setName(null);

        // Create the StopDrillingTool, which fails.

        restStopDrillingToolMockMvc.perform(post("/api/stop-drilling-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(stopDrillingTool)))
            .andExpect(status().isBadRequest());

        List<StopDrillingTool> stopDrillingToolList = stopDrillingToolRepository.findAll();
        assertThat(stopDrillingToolList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkModelIsRequired() throws Exception {
        int databaseSizeBeforeTest = stopDrillingToolRepository.findAll().size();
        // set the field null
        stopDrillingTool.setModel(null);

        // Create the StopDrillingTool, which fails.

        restStopDrillingToolMockMvc.perform(post("/api/stop-drilling-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(stopDrillingTool)))
            .andExpect(status().isBadRequest());

        List<StopDrillingTool> stopDrillingToolList = stopDrillingToolRepository.findAll();
        assertThat(stopDrillingToolList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTrlIsRequired() throws Exception {
        int databaseSizeBeforeTest = stopDrillingToolRepository.findAll().size();
        // set the field null
        stopDrillingTool.setTrl(null);

        // Create the StopDrillingTool, which fails.

        restStopDrillingToolMockMvc.perform(post("/api/stop-drilling-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(stopDrillingTool)))
            .andExpect(status().isBadRequest());

        List<StopDrillingTool> stopDrillingToolList = stopDrillingToolRepository.findAll();
        assertThat(stopDrillingToolList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkWeightIsRequired() throws Exception {
        int databaseSizeBeforeTest = stopDrillingToolRepository.findAll().size();
        // set the field null
        stopDrillingTool.setWeight(null);

        // Create the StopDrillingTool, which fails.

        restStopDrillingToolMockMvc.perform(post("/api/stop-drilling-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(stopDrillingTool)))
            .andExpect(status().isBadRequest());

        List<StopDrillingTool> stopDrillingToolList = stopDrillingToolRepository.findAll();
        assertThat(stopDrillingToolList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLengthIsRequired() throws Exception {
        int databaseSizeBeforeTest = stopDrillingToolRepository.findAll().size();
        // set the field null
        stopDrillingTool.setLength(null);

        // Create the StopDrillingTool, which fails.

        restStopDrillingToolMockMvc.perform(post("/api/stop-drilling-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(stopDrillingTool)))
            .andExpect(status().isBadRequest());

        List<StopDrillingTool> stopDrillingToolList = stopDrillingToolRepository.findAll();
        assertThat(stopDrillingToolList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDrillingRateIsRequired() throws Exception {
        int databaseSizeBeforeTest = stopDrillingToolRepository.findAll().size();
        // set the field null
        stopDrillingTool.setDrillingRate(null);

        // Create the StopDrillingTool, which fails.

        restStopDrillingToolMockMvc.perform(post("/api/stop-drilling-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(stopDrillingTool)))
            .andExpect(status().isBadRequest());

        List<StopDrillingTool> stopDrillingToolList = stopDrillingToolRepository.findAll();
        assertThat(stopDrillingToolList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNoiseLevelIsRequired() throws Exception {
        int databaseSizeBeforeTest = stopDrillingToolRepository.findAll().size();
        // set the field null
        stopDrillingTool.setNoiseLevel(null);

        // Create the StopDrillingTool, which fails.

        restStopDrillingToolMockMvc.perform(post("/api/stop-drilling-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(stopDrillingTool)))
            .andExpect(status().isBadRequest());

        List<StopDrillingTool> stopDrillingToolList = stopDrillingToolRepository.findAll();
        assertThat(stopDrillingToolList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSettingUpTimeIsRequired() throws Exception {
        int databaseSizeBeforeTest = stopDrillingToolRepository.findAll().size();
        // set the field null
        stopDrillingTool.setSettingUpTime(null);

        // Create the StopDrillingTool, which fails.

        restStopDrillingToolMockMvc.perform(post("/api/stop-drilling-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(stopDrillingTool)))
            .andExpect(status().isBadRequest());

        List<StopDrillingTool> stopDrillingToolList = stopDrillingToolRepository.findAll();
        assertThat(stopDrillingToolList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDismantlingTimeIsRequired() throws Exception {
        int databaseSizeBeforeTest = stopDrillingToolRepository.findAll().size();
        // set the field null
        stopDrillingTool.setDismantlingTime(null);

        // Create the StopDrillingTool, which fails.

        restStopDrillingToolMockMvc.perform(post("/api/stop-drilling-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(stopDrillingTool)))
            .andExpect(status().isBadRequest());

        List<StopDrillingTool> stopDrillingToolList = stopDrillingToolRepository.findAll();
        assertThat(stopDrillingToolList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkWaterUsePerMetreDrilledIsRequired() throws Exception {
        int databaseSizeBeforeTest = stopDrillingToolRepository.findAll().size();
        // set the field null
        stopDrillingTool.setWaterUsePerMetreDrilled(null);

        // Create the StopDrillingTool, which fails.

        restStopDrillingToolMockMvc.perform(post("/api/stop-drilling-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(stopDrillingTool)))
            .andExpect(status().isBadRequest());

        List<StopDrillingTool> stopDrillingToolList = stopDrillingToolRepository.findAll();
        assertThat(stopDrillingToolList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAvailabilityIsRequired() throws Exception {
        int databaseSizeBeforeTest = stopDrillingToolRepository.findAll().size();
        // set the field null
        stopDrillingTool.setAvailability(null);

        // Create the StopDrillingTool, which fails.

        restStopDrillingToolMockMvc.perform(post("/api/stop-drilling-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(stopDrillingTool)))
            .andExpect(status().isBadRequest());

        List<StopDrillingTool> stopDrillingToolList = stopDrillingToolRepository.findAll();
        assertThat(stopDrillingToolList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCostPerMeterDrilledIsRequired() throws Exception {
        int databaseSizeBeforeTest = stopDrillingToolRepository.findAll().size();
        // set the field null
        stopDrillingTool.setCostPerMeterDrilled(null);

        // Create the StopDrillingTool, which fails.

        restStopDrillingToolMockMvc.perform(post("/api/stop-drilling-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(stopDrillingTool)))
            .andExpect(status().isBadRequest());

        List<StopDrillingTool> stopDrillingToolList = stopDrillingToolRepository.findAll();
        assertThat(stopDrillingToolList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkHoleSizeRangeIsRequired() throws Exception {
        int databaseSizeBeforeTest = stopDrillingToolRepository.findAll().size();
        // set the field null
        stopDrillingTool.setHoleSizeRange(null);

        // Create the StopDrillingTool, which fails.

        restStopDrillingToolMockMvc.perform(post("/api/stop-drilling-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(stopDrillingTool)))
            .andExpect(status().isBadRequest());

        List<StopDrillingTool> stopDrillingToolList = stopDrillingToolRepository.findAll();
        assertThat(stopDrillingToolList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPowerSourceIsRequired() throws Exception {
        int databaseSizeBeforeTest = stopDrillingToolRepository.findAll().size();
        // set the field null
        stopDrillingTool.setPowerSource(null);

        // Create the StopDrillingTool, which fails.

        restStopDrillingToolMockMvc.perform(post("/api/stop-drilling-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(stopDrillingTool)))
            .andExpect(status().isBadRequest());

        List<StopDrillingTool> stopDrillingToolList = stopDrillingToolRepository.findAll();
        assertThat(stopDrillingToolList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPowerRatingIsRequired() throws Exception {
        int databaseSizeBeforeTest = stopDrillingToolRepository.findAll().size();
        // set the field null
        stopDrillingTool.setPowerRating(null);

        // Create the StopDrillingTool, which fails.

        restStopDrillingToolMockMvc.perform(post("/api/stop-drilling-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(stopDrillingTool)))
            .andExpect(status().isBadRequest());

        List<StopDrillingTool> stopDrillingToolList = stopDrillingToolRepository.findAll();
        assertThat(stopDrillingToolList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCostIsRequired() throws Exception {
        int databaseSizeBeforeTest = stopDrillingToolRepository.findAll().size();
        // set the field null
        stopDrillingTool.setCost(null);

        // Create the StopDrillingTool, which fails.

        restStopDrillingToolMockMvc.perform(post("/api/stop-drilling-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(stopDrillingTool)))
            .andExpect(status().isBadRequest());

        List<StopDrillingTool> stopDrillingToolList = stopDrillingToolRepository.findAll();
        assertThat(stopDrillingToolList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTrammingSpeedIsRequired() throws Exception {
        int databaseSizeBeforeTest = stopDrillingToolRepository.findAll().size();
        // set the field null
        stopDrillingTool.setTrammingSpeed(null);

        // Create the StopDrillingTool, which fails.

        restStopDrillingToolMockMvc.perform(post("/api/stop-drilling-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(stopDrillingTool)))
            .andExpect(status().isBadRequest());

        List<StopDrillingTool> stopDrillingToolList = stopDrillingToolRepository.findAll();
        assertThat(stopDrillingToolList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkGradeabilityIsRequired() throws Exception {
        int databaseSizeBeforeTest = stopDrillingToolRepository.findAll().size();
        // set the field null
        stopDrillingTool.setGradeability(null);

        // Create the StopDrillingTool, which fails.

        restStopDrillingToolMockMvc.perform(post("/api/stop-drilling-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(stopDrillingTool)))
            .andExpect(status().isBadRequest());

        List<StopDrillingTool> stopDrillingToolList = stopDrillingToolRepository.findAll();
        assertThat(stopDrillingToolList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNumberOfBoomsIsRequired() throws Exception {
        int databaseSizeBeforeTest = stopDrillingToolRepository.findAll().size();
        // set the field null
        stopDrillingTool.setNumberOfBooms(null);

        // Create the StopDrillingTool, which fails.

        restStopDrillingToolMockMvc.perform(post("/api/stop-drilling-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(stopDrillingTool)))
            .andExpect(status().isBadRequest());

        List<StopDrillingTool> stopDrillingToolList = stopDrillingToolRepository.findAll();
        assertThat(stopDrillingToolList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTypeOfBoomIsRequired() throws Exception {
        int databaseSizeBeforeTest = stopDrillingToolRepository.findAll().size();
        // set the field null
        stopDrillingTool.setTypeOfBoom(null);

        // Create the StopDrillingTool, which fails.

        restStopDrillingToolMockMvc.perform(post("/api/stop-drilling-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(stopDrillingTool)))
            .andExpect(status().isBadRequest());

        List<StopDrillingTool> stopDrillingToolList = stopDrillingToolRepository.findAll();
        assertThat(stopDrillingToolList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkOuterTurningRadiusIsRequired() throws Exception {
        int databaseSizeBeforeTest = stopDrillingToolRepository.findAll().size();
        // set the field null
        stopDrillingTool.setOuterTurningRadius(null);

        // Create the StopDrillingTool, which fails.

        restStopDrillingToolMockMvc.perform(post("/api/stop-drilling-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(stopDrillingTool)))
            .andExpect(status().isBadRequest());

        List<StopDrillingTool> stopDrillingToolList = stopDrillingToolRepository.findAll();
        assertThat(stopDrillingToolList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkInnerTurningRadiusIsRequired() throws Exception {
        int databaseSizeBeforeTest = stopDrillingToolRepository.findAll().size();
        // set the field null
        stopDrillingTool.setInnerTurningRadius(null);

        // Create the StopDrillingTool, which fails.

        restStopDrillingToolMockMvc.perform(post("/api/stop-drilling-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(stopDrillingTool)))
            .andExpect(status().isBadRequest());

        List<StopDrillingTool> stopDrillingToolList = stopDrillingToolRepository.findAll();
        assertThat(stopDrillingToolList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkObservations1IsRequired() throws Exception {
        int databaseSizeBeforeTest = stopDrillingToolRepository.findAll().size();
        // set the field null
        stopDrillingTool.setObservations1(null);

        // Create the StopDrillingTool, which fails.

        restStopDrillingToolMockMvc.perform(post("/api/stop-drilling-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(stopDrillingTool)))
            .andExpect(status().isBadRequest());

        List<StopDrillingTool> stopDrillingToolList = stopDrillingToolRepository.findAll();
        assertThat(stopDrillingToolList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkObservations2IsRequired() throws Exception {
        int databaseSizeBeforeTest = stopDrillingToolRepository.findAll().size();
        // set the field null
        stopDrillingTool.setObservations2(null);

        // Create the StopDrillingTool, which fails.

        restStopDrillingToolMockMvc.perform(post("/api/stop-drilling-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(stopDrillingTool)))
            .andExpect(status().isBadRequest());

        List<StopDrillingTool> stopDrillingToolList = stopDrillingToolRepository.findAll();
        assertThat(stopDrillingToolList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkObservations3IsRequired() throws Exception {
        int databaseSizeBeforeTest = stopDrillingToolRepository.findAll().size();
        // set the field null
        stopDrillingTool.setObservations3(null);

        // Create the StopDrillingTool, which fails.

        restStopDrillingToolMockMvc.perform(post("/api/stop-drilling-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(stopDrillingTool)))
            .andExpect(status().isBadRequest());

        List<StopDrillingTool> stopDrillingToolList = stopDrillingToolRepository.findAll();
        assertThat(stopDrillingToolList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkObservations4IsRequired() throws Exception {
        int databaseSizeBeforeTest = stopDrillingToolRepository.findAll().size();
        // set the field null
        stopDrillingTool.setObservations4(null);

        // Create the StopDrillingTool, which fails.

        restStopDrillingToolMockMvc.perform(post("/api/stop-drilling-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(stopDrillingTool)))
            .andExpect(status().isBadRequest());

        List<StopDrillingTool> stopDrillingToolList = stopDrillingToolRepository.findAll();
        assertThat(stopDrillingToolList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkObservations5IsRequired() throws Exception {
        int databaseSizeBeforeTest = stopDrillingToolRepository.findAll().size();
        // set the field null
        stopDrillingTool.setObservations5(null);

        // Create the StopDrillingTool, which fails.

        restStopDrillingToolMockMvc.perform(post("/api/stop-drilling-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(stopDrillingTool)))
            .andExpect(status().isBadRequest());

        List<StopDrillingTool> stopDrillingToolList = stopDrillingToolRepository.findAll();
        assertThat(stopDrillingToolList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkObservations6IsRequired() throws Exception {
        int databaseSizeBeforeTest = stopDrillingToolRepository.findAll().size();
        // set the field null
        stopDrillingTool.setObservations6(null);

        // Create the StopDrillingTool, which fails.

        restStopDrillingToolMockMvc.perform(post("/api/stop-drilling-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(stopDrillingTool)))
            .andExpect(status().isBadRequest());

        List<StopDrillingTool> stopDrillingToolList = stopDrillingToolRepository.findAll();
        assertThat(stopDrillingToolList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkOperators_CommentsIsRequired() throws Exception {
        int databaseSizeBeforeTest = stopDrillingToolRepository.findAll().size();
        // set the field null
        stopDrillingTool.setOperators_Comments(null);

        // Create the StopDrillingTool, which fails.

        restStopDrillingToolMockMvc.perform(post("/api/stop-drilling-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(stopDrillingTool)))
            .andExpect(status().isBadRequest());

        List<StopDrillingTool> stopDrillingToolList = stopDrillingToolRepository.findAll();
        assertThat(stopDrillingToolList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllStopDrillingTools() throws Exception {
        // Initialize the database
        stopDrillingToolRepository.saveAndFlush(stopDrillingTool);

        // Get all the stopDrillingToolList
        restStopDrillingToolMockMvc.perform(get("/api/stop-drilling-tools?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(stopDrillingTool.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].model").value(hasItem(DEFAULT_MODEL.toString())))
            .andExpect(jsonPath("$.[*].technologyType").value(hasItem(DEFAULT_TECHNOLOGY_TYPE.toString())))
            .andExpect(jsonPath("$.[*].trl").value(hasItem(DEFAULT_TRL)))
            .andExpect(jsonPath("$.[*].photoContentType").value(hasItem(DEFAULT_PHOTO_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].photo").value(hasItem(Base64Utils.encodeToString(DEFAULT_PHOTO))))
            .andExpect(jsonPath("$.[*].datasheetContentType").value(hasItem(DEFAULT_DATASHEET_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].datasheet").value(hasItem(Base64Utils.encodeToString(DEFAULT_DATASHEET))))
            .andExpect(jsonPath("$.[*].weight").value(hasItem(DEFAULT_WEIGHT.doubleValue())))
            .andExpect(jsonPath("$.[*].testedWeight").value(hasItem(DEFAULT_TESTED_WEIGHT.doubleValue())))
            .andExpect(jsonPath("$.[*].length").value(hasItem(DEFAULT_LENGTH.doubleValue())))
            .andExpect(jsonPath("$.[*].testedLength").value(hasItem(DEFAULT_TESTED_LENGTH.doubleValue())))
            .andExpect(jsonPath("$.[*].drillingRate").value(hasItem(DEFAULT_DRILLING_RATE.doubleValue())))
            .andExpect(jsonPath("$.[*].testedDrillingRate").value(hasItem(DEFAULT_TESTED_DRILLING_RATE.doubleValue())))
            .andExpect(jsonPath("$.[*].noiseLevel").value(hasItem(DEFAULT_NOISE_LEVEL.doubleValue())))
            .andExpect(jsonPath("$.[*].testedNoiseLevel").value(hasItem(DEFAULT_TESTED_NOISE_LEVEL.doubleValue())))
            .andExpect(jsonPath("$.[*].settingUpTime").value(hasItem(DEFAULT_SETTING_UP_TIME.doubleValue())))
            .andExpect(jsonPath("$.[*].testedSettingUpTime").value(hasItem(DEFAULT_TESTED_SETTING_UP_TIME.doubleValue())))
            .andExpect(jsonPath("$.[*].dismantlingTime").value(hasItem(DEFAULT_DISMANTLING_TIME.doubleValue())))
            .andExpect(jsonPath("$.[*].testedDismantlingTime").value(hasItem(DEFAULT_TESTED_DISMANTLING_TIME.doubleValue())))
            .andExpect(jsonPath("$.[*].waterUsePerMetreDrilled").value(hasItem(DEFAULT_WATER_USE_PER_METRE_DRILLED.doubleValue())))
            .andExpect(jsonPath("$.[*].testedWaterUsePerMetreDrilled").value(hasItem(DEFAULT_TESTED_WATER_USE_PER_METRE_DRILLED.doubleValue())))
            .andExpect(jsonPath("$.[*].availability").value(hasItem(DEFAULT_AVAILABILITY.doubleValue())))
            .andExpect(jsonPath("$.[*].testedAvailability").value(hasItem(DEFAULT_TESTED_AVAILABILITY.doubleValue())))
            .andExpect(jsonPath("$.[*].costPerMeterDrilled").value(hasItem(DEFAULT_COST_PER_METER_DRILLED.doubleValue())))
            .andExpect(jsonPath("$.[*].testedCostPerMeterDrilled").value(hasItem(DEFAULT_TESTED_COST_PER_METER_DRILLED.doubleValue())))
            .andExpect(jsonPath("$.[*].holeSizeRange").value(hasItem(DEFAULT_HOLE_SIZE_RANGE.toString())))
            .andExpect(jsonPath("$.[*].testedHoleSizeRange").value(hasItem(DEFAULT_TESTED_HOLE_SIZE_RANGE.toString())))
            .andExpect(jsonPath("$.[*].powerSource").value(hasItem(DEFAULT_POWER_SOURCE.toString())))
            .andExpect(jsonPath("$.[*].testedPowerSource").value(hasItem(DEFAULT_TESTED_POWER_SOURCE.toString())))
            .andExpect(jsonPath("$.[*].powerRating").value(hasItem(DEFAULT_POWER_RATING.doubleValue())))
            .andExpect(jsonPath("$.[*].testedPowerRating").value(hasItem(DEFAULT_TESTED_POWER_RATING.doubleValue())))
            .andExpect(jsonPath("$.[*].cost").value(hasItem(DEFAULT_COST.intValue())))
            .andExpect(jsonPath("$.[*].testedCost").value(hasItem(DEFAULT_TESTED_COST.intValue())))
            .andExpect(jsonPath("$.[*].trammingSpeed").value(hasItem(DEFAULT_TRAMMING_SPEED.doubleValue())))
            .andExpect(jsonPath("$.[*].testedTrammingSpeed").value(hasItem(DEFAULT_TESTED_TRAMMING_SPEED.doubleValue())))
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
    public void getStopDrillingTool() throws Exception {
        // Initialize the database
        stopDrillingToolRepository.saveAndFlush(stopDrillingTool);

        // Get the stopDrillingTool
        restStopDrillingToolMockMvc.perform(get("/api/stop-drilling-tools/{id}", stopDrillingTool.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(stopDrillingTool.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.model").value(DEFAULT_MODEL.toString()))
            .andExpect(jsonPath("$.technologyType").value(DEFAULT_TECHNOLOGY_TYPE.toString()))
            .andExpect(jsonPath("$.trl").value(DEFAULT_TRL))
            .andExpect(jsonPath("$.photoContentType").value(DEFAULT_PHOTO_CONTENT_TYPE))
            .andExpect(jsonPath("$.photo").value(Base64Utils.encodeToString(DEFAULT_PHOTO)))
            .andExpect(jsonPath("$.datasheetContentType").value(DEFAULT_DATASHEET_CONTENT_TYPE))
            .andExpect(jsonPath("$.datasheet").value(Base64Utils.encodeToString(DEFAULT_DATASHEET)))
            .andExpect(jsonPath("$.weight").value(DEFAULT_WEIGHT.doubleValue()))
            .andExpect(jsonPath("$.testedWeight").value(DEFAULT_TESTED_WEIGHT.doubleValue()))
            .andExpect(jsonPath("$.length").value(DEFAULT_LENGTH.doubleValue()))
            .andExpect(jsonPath("$.testedLength").value(DEFAULT_TESTED_LENGTH.doubleValue()))
            .andExpect(jsonPath("$.drillingRate").value(DEFAULT_DRILLING_RATE.doubleValue()))
            .andExpect(jsonPath("$.testedDrillingRate").value(DEFAULT_TESTED_DRILLING_RATE.doubleValue()))
            .andExpect(jsonPath("$.noiseLevel").value(DEFAULT_NOISE_LEVEL.doubleValue()))
            .andExpect(jsonPath("$.testedNoiseLevel").value(DEFAULT_TESTED_NOISE_LEVEL.doubleValue()))
            .andExpect(jsonPath("$.settingUpTime").value(DEFAULT_SETTING_UP_TIME.doubleValue()))
            .andExpect(jsonPath("$.testedSettingUpTime").value(DEFAULT_TESTED_SETTING_UP_TIME.doubleValue()))
            .andExpect(jsonPath("$.dismantlingTime").value(DEFAULT_DISMANTLING_TIME.doubleValue()))
            .andExpect(jsonPath("$.testedDismantlingTime").value(DEFAULT_TESTED_DISMANTLING_TIME.doubleValue()))
            .andExpect(jsonPath("$.waterUsePerMetreDrilled").value(DEFAULT_WATER_USE_PER_METRE_DRILLED.doubleValue()))
            .andExpect(jsonPath("$.testedWaterUsePerMetreDrilled").value(DEFAULT_TESTED_WATER_USE_PER_METRE_DRILLED.doubleValue()))
            .andExpect(jsonPath("$.availability").value(DEFAULT_AVAILABILITY.doubleValue()))
            .andExpect(jsonPath("$.testedAvailability").value(DEFAULT_TESTED_AVAILABILITY.doubleValue()))
            .andExpect(jsonPath("$.costPerMeterDrilled").value(DEFAULT_COST_PER_METER_DRILLED.doubleValue()))
            .andExpect(jsonPath("$.testedCostPerMeterDrilled").value(DEFAULT_TESTED_COST_PER_METER_DRILLED.doubleValue()))
            .andExpect(jsonPath("$.holeSizeRange").value(DEFAULT_HOLE_SIZE_RANGE.toString()))
            .andExpect(jsonPath("$.testedHoleSizeRange").value(DEFAULT_TESTED_HOLE_SIZE_RANGE.toString()))
            .andExpect(jsonPath("$.powerSource").value(DEFAULT_POWER_SOURCE.toString()))
            .andExpect(jsonPath("$.testedPowerSource").value(DEFAULT_TESTED_POWER_SOURCE.toString()))
            .andExpect(jsonPath("$.powerRating").value(DEFAULT_POWER_RATING.doubleValue()))
            .andExpect(jsonPath("$.testedPowerRating").value(DEFAULT_TESTED_POWER_RATING.doubleValue()))
            .andExpect(jsonPath("$.cost").value(DEFAULT_COST.intValue()))
            .andExpect(jsonPath("$.testedCost").value(DEFAULT_TESTED_COST.intValue()))
            .andExpect(jsonPath("$.trammingSpeed").value(DEFAULT_TRAMMING_SPEED.doubleValue()))
            .andExpect(jsonPath("$.testedTrammingSpeed").value(DEFAULT_TESTED_TRAMMING_SPEED.doubleValue()))
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
    public void getNonExistingStopDrillingTool() throws Exception {
        // Get the stopDrillingTool
        restStopDrillingToolMockMvc.perform(get("/api/stop-drilling-tools/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateStopDrillingTool() throws Exception {
        // Initialize the database
        stopDrillingToolRepository.saveAndFlush(stopDrillingTool);
        stopDrillingToolSearchRepository.save(stopDrillingTool);
        int databaseSizeBeforeUpdate = stopDrillingToolRepository.findAll().size();

        // Update the stopDrillingTool
        StopDrillingTool updatedStopDrillingTool = stopDrillingToolRepository.findOne(stopDrillingTool.getId());
        updatedStopDrillingTool
            .name(UPDATED_NAME)
            .model(UPDATED_MODEL)
            .technologyType(UPDATED_TECHNOLOGY_TYPE)
            .trl(UPDATED_TRL)
            .photo(UPDATED_PHOTO)
            .photoContentType(UPDATED_PHOTO_CONTENT_TYPE)
            .datasheet(UPDATED_DATASHEET)
            .datasheetContentType(UPDATED_DATASHEET_CONTENT_TYPE)
            .weight(UPDATED_WEIGHT)
            .testedWeight(UPDATED_TESTED_WEIGHT)
            .length(UPDATED_LENGTH)
            .testedLength(UPDATED_TESTED_LENGTH)
            .drillingRate(UPDATED_DRILLING_RATE)
            .testedDrillingRate(UPDATED_TESTED_DRILLING_RATE)
            .noiseLevel(UPDATED_NOISE_LEVEL)
            .testedNoiseLevel(UPDATED_TESTED_NOISE_LEVEL)
            .settingUpTime(UPDATED_SETTING_UP_TIME)
            .testedSettingUpTime(UPDATED_TESTED_SETTING_UP_TIME)
            .dismantlingTime(UPDATED_DISMANTLING_TIME)
            .testedDismantlingTime(UPDATED_TESTED_DISMANTLING_TIME)
            .waterUsePerMetreDrilled(UPDATED_WATER_USE_PER_METRE_DRILLED)
            .testedWaterUsePerMetreDrilled(UPDATED_TESTED_WATER_USE_PER_METRE_DRILLED)
            .availability(UPDATED_AVAILABILITY)
            .testedAvailability(UPDATED_TESTED_AVAILABILITY)
            .costPerMeterDrilled(UPDATED_COST_PER_METER_DRILLED)
            .testedCostPerMeterDrilled(UPDATED_TESTED_COST_PER_METER_DRILLED)
            .holeSizeRange(UPDATED_HOLE_SIZE_RANGE)
            .testedHoleSizeRange(UPDATED_TESTED_HOLE_SIZE_RANGE)
            .powerSource(UPDATED_POWER_SOURCE)
            .testedPowerSource(UPDATED_TESTED_POWER_SOURCE)
            .powerRating(UPDATED_POWER_RATING)
            .testedPowerRating(UPDATED_TESTED_POWER_RATING)
            .cost(UPDATED_COST)
            .testedCost(UPDATED_TESTED_COST)
            .trammingSpeed(UPDATED_TRAMMING_SPEED)
            .testedTrammingSpeed(UPDATED_TESTED_TRAMMING_SPEED)
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

        restStopDrillingToolMockMvc.perform(put("/api/stop-drilling-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedStopDrillingTool)))
            .andExpect(status().isOk());

        // Validate the StopDrillingTool in the database
        List<StopDrillingTool> stopDrillingToolList = stopDrillingToolRepository.findAll();
        assertThat(stopDrillingToolList).hasSize(databaseSizeBeforeUpdate);
        StopDrillingTool testStopDrillingTool = stopDrillingToolList.get(stopDrillingToolList.size() - 1);
        assertThat(testStopDrillingTool.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testStopDrillingTool.getModel()).isEqualTo(UPDATED_MODEL);
        assertThat(testStopDrillingTool.getTechnologyType()).isEqualTo(UPDATED_TECHNOLOGY_TYPE);
        assertThat(testStopDrillingTool.getTrl()).isEqualTo(UPDATED_TRL);
        assertThat(testStopDrillingTool.getPhoto()).isEqualTo(UPDATED_PHOTO);
        assertThat(testStopDrillingTool.getPhotoContentType()).isEqualTo(UPDATED_PHOTO_CONTENT_TYPE);
        assertThat(testStopDrillingTool.getDatasheet()).isEqualTo(UPDATED_DATASHEET);
        assertThat(testStopDrillingTool.getDatasheetContentType()).isEqualTo(UPDATED_DATASHEET_CONTENT_TYPE);
        assertThat(testStopDrillingTool.getWeight()).isEqualTo(UPDATED_WEIGHT);
        assertThat(testStopDrillingTool.getTestedWeight()).isEqualTo(UPDATED_TESTED_WEIGHT);
        assertThat(testStopDrillingTool.getLength()).isEqualTo(UPDATED_LENGTH);
        assertThat(testStopDrillingTool.getTestedLength()).isEqualTo(UPDATED_TESTED_LENGTH);
        assertThat(testStopDrillingTool.getDrillingRate()).isEqualTo(UPDATED_DRILLING_RATE);
        assertThat(testStopDrillingTool.getTestedDrillingRate()).isEqualTo(UPDATED_TESTED_DRILLING_RATE);
        assertThat(testStopDrillingTool.getNoiseLevel()).isEqualTo(UPDATED_NOISE_LEVEL);
        assertThat(testStopDrillingTool.getTestedNoiseLevel()).isEqualTo(UPDATED_TESTED_NOISE_LEVEL);
        assertThat(testStopDrillingTool.getSettingUpTime()).isEqualTo(UPDATED_SETTING_UP_TIME);
        assertThat(testStopDrillingTool.getTestedSettingUpTime()).isEqualTo(UPDATED_TESTED_SETTING_UP_TIME);
        assertThat(testStopDrillingTool.getDismantlingTime()).isEqualTo(UPDATED_DISMANTLING_TIME);
        assertThat(testStopDrillingTool.getTestedDismantlingTime()).isEqualTo(UPDATED_TESTED_DISMANTLING_TIME);
        assertThat(testStopDrillingTool.getWaterUsePerMetreDrilled()).isEqualTo(UPDATED_WATER_USE_PER_METRE_DRILLED);
        assertThat(testStopDrillingTool.getTestedWaterUsePerMetreDrilled()).isEqualTo(UPDATED_TESTED_WATER_USE_PER_METRE_DRILLED);
        assertThat(testStopDrillingTool.getAvailability()).isEqualTo(UPDATED_AVAILABILITY);
        assertThat(testStopDrillingTool.getTestedAvailability()).isEqualTo(UPDATED_TESTED_AVAILABILITY);
        assertThat(testStopDrillingTool.getCostPerMeterDrilled()).isEqualTo(UPDATED_COST_PER_METER_DRILLED);
        assertThat(testStopDrillingTool.getTestedCostPerMeterDrilled()).isEqualTo(UPDATED_TESTED_COST_PER_METER_DRILLED);
        assertThat(testStopDrillingTool.getHoleSizeRange()).isEqualTo(UPDATED_HOLE_SIZE_RANGE);
        assertThat(testStopDrillingTool.getTestedHoleSizeRange()).isEqualTo(UPDATED_TESTED_HOLE_SIZE_RANGE);
        assertThat(testStopDrillingTool.getPowerSource()).isEqualTo(UPDATED_POWER_SOURCE);
        assertThat(testStopDrillingTool.getTestedPowerSource()).isEqualTo(UPDATED_TESTED_POWER_SOURCE);
        assertThat(testStopDrillingTool.getPowerRating()).isEqualTo(UPDATED_POWER_RATING);
        assertThat(testStopDrillingTool.getTestedPowerRating()).isEqualTo(UPDATED_TESTED_POWER_RATING);
        assertThat(testStopDrillingTool.getCost()).isEqualTo(UPDATED_COST);
        assertThat(testStopDrillingTool.getTestedCost()).isEqualTo(UPDATED_TESTED_COST);
        assertThat(testStopDrillingTool.getTrammingSpeed()).isEqualTo(UPDATED_TRAMMING_SPEED);
        assertThat(testStopDrillingTool.getTestedTrammingSpeed()).isEqualTo(UPDATED_TESTED_TRAMMING_SPEED);
        assertThat(testStopDrillingTool.getGradeability()).isEqualTo(UPDATED_GRADEABILITY);
        assertThat(testStopDrillingTool.getTestedGradeability()).isEqualTo(UPDATED_TESTED_GRADEABILITY);
        assertThat(testStopDrillingTool.getNumberOfBooms()).isEqualTo(UPDATED_NUMBER_OF_BOOMS);
        assertThat(testStopDrillingTool.getTestedNumberOfBooms()).isEqualTo(UPDATED_TESTED_NUMBER_OF_BOOMS);
        assertThat(testStopDrillingTool.getTypeOfBoom()).isEqualTo(UPDATED_TYPE_OF_BOOM);
        assertThat(testStopDrillingTool.getTestedTypeOfBoom()).isEqualTo(UPDATED_TESTED_TYPE_OF_BOOM);
        assertThat(testStopDrillingTool.getOuterTurningRadius()).isEqualTo(UPDATED_OUTER_TURNING_RADIUS);
        assertThat(testStopDrillingTool.getTestedOuterTurningRadius()).isEqualTo(UPDATED_TESTED_OUTER_TURNING_RADIUS);
        assertThat(testStopDrillingTool.getInnerTurningRadius()).isEqualTo(UPDATED_INNER_TURNING_RADIUS);
        assertThat(testStopDrillingTool.getTestedInnerTurningRadius()).isEqualTo(UPDATED_TESTED_INNER_TURNING_RADIUS);
        assertThat(testStopDrillingTool.getObservations1()).isEqualTo(UPDATED_OBSERVATIONS_1);
        assertThat(testStopDrillingTool.getTestedObservations1()).isEqualTo(UPDATED_TESTED_OBSERVATIONS_1);
        assertThat(testStopDrillingTool.getObservations2()).isEqualTo(UPDATED_OBSERVATIONS_2);
        assertThat(testStopDrillingTool.getTestedObservations2()).isEqualTo(UPDATED_TESTED_OBSERVATIONS_2);
        assertThat(testStopDrillingTool.getObservations3()).isEqualTo(UPDATED_OBSERVATIONS_3);
        assertThat(testStopDrillingTool.getTestedObservations3()).isEqualTo(UPDATED_TESTED_OBSERVATIONS_3);
        assertThat(testStopDrillingTool.getObservations4()).isEqualTo(UPDATED_OBSERVATIONS_4);
        assertThat(testStopDrillingTool.getTestedObservations4()).isEqualTo(UPDATED_TESTED_OBSERVATIONS_4);
        assertThat(testStopDrillingTool.getObservations5()).isEqualTo(UPDATED_OBSERVATIONS_5);
        assertThat(testStopDrillingTool.getTestedObservations5()).isEqualTo(UPDATED_TESTED_OBSERVATIONS_5);
        assertThat(testStopDrillingTool.getObservations6()).isEqualTo(UPDATED_OBSERVATIONS_6);
        assertThat(testStopDrillingTool.getTestedObservations6()).isEqualTo(UPDATED_TESTED_OBSERVATIONS_6);
        assertThat(testStopDrillingTool.getOperators_Comments()).isEqualTo(UPDATED_OPERATORS_COMMENTS);
        assertThat(testStopDrillingTool.getTestedOperators_Comments()).isEqualTo(UPDATED_TESTED_OPERATORS_COMMENTS);

        // Validate the StopDrillingTool in Elasticsearch
        StopDrillingTool stopDrillingToolEs = stopDrillingToolSearchRepository.findOne(testStopDrillingTool.getId());
        assertThat(stopDrillingToolEs).isEqualToComparingFieldByField(testStopDrillingTool);
    }

    @Test
    @Transactional
    public void updateNonExistingStopDrillingTool() throws Exception {
        int databaseSizeBeforeUpdate = stopDrillingToolRepository.findAll().size();

        // Create the StopDrillingTool

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restStopDrillingToolMockMvc.perform(put("/api/stop-drilling-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(stopDrillingTool)))
            .andExpect(status().isCreated());

        // Validate the StopDrillingTool in the database
        List<StopDrillingTool> stopDrillingToolList = stopDrillingToolRepository.findAll();
        assertThat(stopDrillingToolList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteStopDrillingTool() throws Exception {
        // Initialize the database
        stopDrillingToolRepository.saveAndFlush(stopDrillingTool);
        stopDrillingToolSearchRepository.save(stopDrillingTool);
        int databaseSizeBeforeDelete = stopDrillingToolRepository.findAll().size();

        // Get the stopDrillingTool
        restStopDrillingToolMockMvc.perform(delete("/api/stop-drilling-tools/{id}", stopDrillingTool.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate Elasticsearch is empty
        boolean stopDrillingToolExistsInEs = stopDrillingToolSearchRepository.exists(stopDrillingTool.getId());
        assertThat(stopDrillingToolExistsInEs).isFalse();

        // Validate the database is empty
        List<StopDrillingTool> stopDrillingToolList = stopDrillingToolRepository.findAll();
        assertThat(stopDrillingToolList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void searchStopDrillingTool() throws Exception {
        // Initialize the database
        stopDrillingToolRepository.saveAndFlush(stopDrillingTool);
        stopDrillingToolSearchRepository.save(stopDrillingTool);

        // Search the stopDrillingTool
        restStopDrillingToolMockMvc.perform(get("/api/_search/stop-drilling-tools?query=id:" + stopDrillingTool.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(stopDrillingTool.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].model").value(hasItem(DEFAULT_MODEL.toString())))
            .andExpect(jsonPath("$.[*].technologyType").value(hasItem(DEFAULT_TECHNOLOGY_TYPE.toString())))
            .andExpect(jsonPath("$.[*].trl").value(hasItem(DEFAULT_TRL)))
            .andExpect(jsonPath("$.[*].photoContentType").value(hasItem(DEFAULT_PHOTO_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].photo").value(hasItem(Base64Utils.encodeToString(DEFAULT_PHOTO))))
            .andExpect(jsonPath("$.[*].datasheetContentType").value(hasItem(DEFAULT_DATASHEET_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].datasheet").value(hasItem(Base64Utils.encodeToString(DEFAULT_DATASHEET))))
            .andExpect(jsonPath("$.[*].weight").value(hasItem(DEFAULT_WEIGHT.doubleValue())))
            .andExpect(jsonPath("$.[*].testedWeight").value(hasItem(DEFAULT_TESTED_WEIGHT.doubleValue())))
            .andExpect(jsonPath("$.[*].length").value(hasItem(DEFAULT_LENGTH.doubleValue())))
            .andExpect(jsonPath("$.[*].testedLength").value(hasItem(DEFAULT_TESTED_LENGTH.doubleValue())))
            .andExpect(jsonPath("$.[*].drillingRate").value(hasItem(DEFAULT_DRILLING_RATE.doubleValue())))
            .andExpect(jsonPath("$.[*].testedDrillingRate").value(hasItem(DEFAULT_TESTED_DRILLING_RATE.doubleValue())))
            .andExpect(jsonPath("$.[*].noiseLevel").value(hasItem(DEFAULT_NOISE_LEVEL.doubleValue())))
            .andExpect(jsonPath("$.[*].testedNoiseLevel").value(hasItem(DEFAULT_TESTED_NOISE_LEVEL.doubleValue())))
            .andExpect(jsonPath("$.[*].settingUpTime").value(hasItem(DEFAULT_SETTING_UP_TIME.doubleValue())))
            .andExpect(jsonPath("$.[*].testedSettingUpTime").value(hasItem(DEFAULT_TESTED_SETTING_UP_TIME.doubleValue())))
            .andExpect(jsonPath("$.[*].dismantlingTime").value(hasItem(DEFAULT_DISMANTLING_TIME.doubleValue())))
            .andExpect(jsonPath("$.[*].testedDismantlingTime").value(hasItem(DEFAULT_TESTED_DISMANTLING_TIME.doubleValue())))
            .andExpect(jsonPath("$.[*].waterUsePerMetreDrilled").value(hasItem(DEFAULT_WATER_USE_PER_METRE_DRILLED.doubleValue())))
            .andExpect(jsonPath("$.[*].testedWaterUsePerMetreDrilled").value(hasItem(DEFAULT_TESTED_WATER_USE_PER_METRE_DRILLED.doubleValue())))
            .andExpect(jsonPath("$.[*].availability").value(hasItem(DEFAULT_AVAILABILITY.doubleValue())))
            .andExpect(jsonPath("$.[*].testedAvailability").value(hasItem(DEFAULT_TESTED_AVAILABILITY.doubleValue())))
            .andExpect(jsonPath("$.[*].costPerMeterDrilled").value(hasItem(DEFAULT_COST_PER_METER_DRILLED.doubleValue())))
            .andExpect(jsonPath("$.[*].testedCostPerMeterDrilled").value(hasItem(DEFAULT_TESTED_COST_PER_METER_DRILLED.doubleValue())))
            .andExpect(jsonPath("$.[*].holeSizeRange").value(hasItem(DEFAULT_HOLE_SIZE_RANGE.toString())))
            .andExpect(jsonPath("$.[*].testedHoleSizeRange").value(hasItem(DEFAULT_TESTED_HOLE_SIZE_RANGE.toString())))
            .andExpect(jsonPath("$.[*].powerSource").value(hasItem(DEFAULT_POWER_SOURCE.toString())))
            .andExpect(jsonPath("$.[*].testedPowerSource").value(hasItem(DEFAULT_TESTED_POWER_SOURCE.toString())))
            .andExpect(jsonPath("$.[*].powerRating").value(hasItem(DEFAULT_POWER_RATING.doubleValue())))
            .andExpect(jsonPath("$.[*].testedPowerRating").value(hasItem(DEFAULT_TESTED_POWER_RATING.doubleValue())))
            .andExpect(jsonPath("$.[*].cost").value(hasItem(DEFAULT_COST.intValue())))
            .andExpect(jsonPath("$.[*].testedCost").value(hasItem(DEFAULT_TESTED_COST.intValue())))
            .andExpect(jsonPath("$.[*].trammingSpeed").value(hasItem(DEFAULT_TRAMMING_SPEED.doubleValue())))
            .andExpect(jsonPath("$.[*].testedTrammingSpeed").value(hasItem(DEFAULT_TESTED_TRAMMING_SPEED.doubleValue())))
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
        TestUtil.equalsVerifier(StopDrillingTool.class);
        StopDrillingTool stopDrillingTool1 = new StopDrillingTool();
        stopDrillingTool1.setId(1L);
        StopDrillingTool stopDrillingTool2 = new StopDrillingTool();
        stopDrillingTool2.setId(stopDrillingTool1.getId());
        assertThat(stopDrillingTool1).isEqualTo(stopDrillingTool2);
        stopDrillingTool2.setId(2L);
        assertThat(stopDrillingTool1).isNotEqualTo(stopDrillingTool2);
        stopDrillingTool1.setId(null);
        assertThat(stopDrillingTool1).isNotEqualTo(stopDrillingTool2);
    }
}
