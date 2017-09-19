package com.tara.org.web.rest;

import com.tara.org.TaraApp;

import com.tara.org.domain.DevSupportingTool;
import com.tara.org.repository.DevSupportingToolRepository;
import com.tara.org.repository.search.DevSupportingToolSearchRepository;
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
 * Test class for the DevSupportingToolResource REST controller.
 *
 * @see DevSupportingToolResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TaraApp.class)
public class DevSupportingToolResourceIntTest {

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

    private static final Float DEFAULT_POWER_RATING_PERCUSSION = 1F;
    private static final Float UPDATED_POWER_RATING_PERCUSSION = 2F;

    private static final Float DEFAULT_TESTED_POWER_RATING_PERCUSSION = 1F;
    private static final Float UPDATED_TESTED_POWER_RATING_PERCUSSION = 2F;

    private static final Float DEFAULT_NOISE_LEVEL_AT_OPERATOR_STAND = 1F;
    private static final Float UPDATED_NOISE_LEVEL_AT_OPERATOR_STAND = 2F;

    private static final Float DEFAULT_TESTED_NOISE_LEVEL_AT_OPERATOR_STAND = 1F;
    private static final Float UPDATED_TESTED_NOISE_LEVEL_AT_OPERATOR_STAND = 2F;

    private static final String DEFAULT_HOLE_SIZE_RANGE = "AAAAAAAAAA";
    private static final String UPDATED_HOLE_SIZE_RANGE = "BBBBBBBBBB";

    private static final String DEFAULT_TESTED_HOLE_SIZE_RANGE = "AAAAAAAAAA";
    private static final String UPDATED_TESTED_HOLE_SIZE_RANGE = "BBBBBBBBBB";

    private static final Float DEFAULT_DRILL_WATER_CONSUMPTION = 1F;
    private static final Float UPDATED_DRILL_WATER_CONSUMPTION = 2F;

    private static final Float DEFAULT_TESTED_DRILL_WATER_CONSUMPTION = 1F;
    private static final Float UPDATED_TESTED_DRILL_WATER_CONSUMPTION = 2F;

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

    private static final Float DEFAULT_MINIMUM_OPERATING_HEIGHT = 1F;
    private static final Float UPDATED_MINIMUM_OPERATING_HEIGHT = 2F;

    private static final Float DEFAULT_TESTED_MINIMUM_OPERATING_HEIGHT = 1F;
    private static final Float UPDATED_TESTED_MINIMUM_OPERATING_HEIGHT = 2F;

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

    private static final Float DEFAULT_NNER_TURNING_RADIUS = 1F;
    private static final Float UPDATED_NNER_TURNING_RADIUS = 2F;

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
    private DevSupportingToolRepository devSupportingToolRepository;

    @Autowired
    private DevSupportingToolSearchRepository devSupportingToolSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restDevSupportingToolMockMvc;

    private DevSupportingTool devSupportingTool;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DevSupportingToolResource devSupportingToolResource = new DevSupportingToolResource(devSupportingToolRepository, devSupportingToolSearchRepository);
        this.restDevSupportingToolMockMvc = MockMvcBuilders.standaloneSetup(devSupportingToolResource)
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
    public static DevSupportingTool createEntity(EntityManager em) {
        DevSupportingTool devSupportingTool = new DevSupportingTool()
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
            .powerRatingPercussion(DEFAULT_POWER_RATING_PERCUSSION)
            .testedPowerRatingPercussion(DEFAULT_TESTED_POWER_RATING_PERCUSSION)
            .noiseLevelAtOperatorStand(DEFAULT_NOISE_LEVEL_AT_OPERATOR_STAND)
            .testedNoiseLevelAtOperatorStand(DEFAULT_TESTED_NOISE_LEVEL_AT_OPERATOR_STAND)
            .holeSizeRange(DEFAULT_HOLE_SIZE_RANGE)
            .testedHoleSizeRange(DEFAULT_TESTED_HOLE_SIZE_RANGE)
            .drillWaterConsumption(DEFAULT_DRILL_WATER_CONSUMPTION)
            .testedDrillWaterConsumption(DEFAULT_TESTED_DRILL_WATER_CONSUMPTION)
            .waterConsumptionPerMetreDrilled(DEFAULT_WATER_CONSUMPTION_PER_METRE_DRILLED)
            .testedWaterConsumptionPerMetreDrilled(DEFAULT_TESTED_WATER_CONSUMPTION_PER_METRE_DRILLED)
            .powerSource(DEFAULT_POWER_SOURCE)
            .testedPowerSource(DEFAULT_TESTED_POWER_SOURCE)
            .trammingSpeed(DEFAULT_TRAMMING_SPEED)
            .testedTrammingSpeed(DEFAULT_TESTED_TRAMMING_SPEED)
            .boltLengthRange(DEFAULT_BOLT_LENGTH_RANGE)
            .testedBoltLengthRange(DEFAULT_TESTED_BOLT_LENGTH_RANGE)
            .minimumOperatingHeight(DEFAULT_MINIMUM_OPERATING_HEIGHT)
            .testedMinimumOperatingHeight(DEFAULT_TESTED_MINIMUM_OPERATING_HEIGHT)
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
            .nnerTurningRadius(DEFAULT_NNER_TURNING_RADIUS)
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
        return devSupportingTool;
    }

    @Before
    public void initTest() {
        devSupportingToolSearchRepository.deleteAll();
        devSupportingTool = createEntity(em);
    }

    @Test
    @Transactional
    public void createDevSupportingTool() throws Exception {
        int databaseSizeBeforeCreate = devSupportingToolRepository.findAll().size();

        // Create the DevSupportingTool
        restDevSupportingToolMockMvc.perform(post("/api/dev-supporting-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(devSupportingTool)))
            .andExpect(status().isCreated());

        // Validate the DevSupportingTool in the database
        List<DevSupportingTool> devSupportingToolList = devSupportingToolRepository.findAll();
        assertThat(devSupportingToolList).hasSize(databaseSizeBeforeCreate + 1);
        DevSupportingTool testDevSupportingTool = devSupportingToolList.get(devSupportingToolList.size() - 1);
        assertThat(testDevSupportingTool.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testDevSupportingTool.getModel()).isEqualTo(DEFAULT_MODEL);
        assertThat(testDevSupportingTool.getTechnologyType()).isEqualTo(DEFAULT_TECHNOLOGY_TYPE);
        assertThat(testDevSupportingTool.getTrl()).isEqualTo(DEFAULT_TRL);
        assertThat(testDevSupportingTool.getPhoto()).isEqualTo(DEFAULT_PHOTO);
        assertThat(testDevSupportingTool.getPhotoContentType()).isEqualTo(DEFAULT_PHOTO_CONTENT_TYPE);
        assertThat(testDevSupportingTool.getDatasheet()).isEqualTo(DEFAULT_DATASHEET);
        assertThat(testDevSupportingTool.getDatasheetContentType()).isEqualTo(DEFAULT_DATASHEET_CONTENT_TYPE);
        assertThat(testDevSupportingTool.getTypeOfMachine()).isEqualTo(DEFAULT_TYPE_OF_MACHINE);
        assertThat(testDevSupportingTool.getTestedTypeOfMachine()).isEqualTo(DEFAULT_TESTED_TYPE_OF_MACHINE);
        assertThat(testDevSupportingTool.getWeight()).isEqualTo(DEFAULT_WEIGHT);
        assertThat(testDevSupportingTool.getTestedWeight()).isEqualTo(DEFAULT_TESTED_WEIGHT);
        assertThat(testDevSupportingTool.getLength()).isEqualTo(DEFAULT_LENGTH);
        assertThat(testDevSupportingTool.getTestedLength()).isEqualTo(DEFAULT_TESTED_LENGTH);
        assertThat(testDevSupportingTool.getWidth()).isEqualTo(DEFAULT_WIDTH);
        assertThat(testDevSupportingTool.getTestedWidth()).isEqualTo(DEFAULT_TESTED_WIDTH);
        assertThat(testDevSupportingTool.getPowerRatingPercussion()).isEqualTo(DEFAULT_POWER_RATING_PERCUSSION);
        assertThat(testDevSupportingTool.getTestedPowerRatingPercussion()).isEqualTo(DEFAULT_TESTED_POWER_RATING_PERCUSSION);
        assertThat(testDevSupportingTool.getNoiseLevelAtOperatorStand()).isEqualTo(DEFAULT_NOISE_LEVEL_AT_OPERATOR_STAND);
        assertThat(testDevSupportingTool.getTestedNoiseLevelAtOperatorStand()).isEqualTo(DEFAULT_TESTED_NOISE_LEVEL_AT_OPERATOR_STAND);
        assertThat(testDevSupportingTool.getHoleSizeRange()).isEqualTo(DEFAULT_HOLE_SIZE_RANGE);
        assertThat(testDevSupportingTool.getTestedHoleSizeRange()).isEqualTo(DEFAULT_TESTED_HOLE_SIZE_RANGE);
        assertThat(testDevSupportingTool.getDrillWaterConsumption()).isEqualTo(DEFAULT_DRILL_WATER_CONSUMPTION);
        assertThat(testDevSupportingTool.getTestedDrillWaterConsumption()).isEqualTo(DEFAULT_TESTED_DRILL_WATER_CONSUMPTION);
        assertThat(testDevSupportingTool.getWaterConsumptionPerMetreDrilled()).isEqualTo(DEFAULT_WATER_CONSUMPTION_PER_METRE_DRILLED);
        assertThat(testDevSupportingTool.getTestedWaterConsumptionPerMetreDrilled()).isEqualTo(DEFAULT_TESTED_WATER_CONSUMPTION_PER_METRE_DRILLED);
        assertThat(testDevSupportingTool.getPowerSource()).isEqualTo(DEFAULT_POWER_SOURCE);
        assertThat(testDevSupportingTool.getTestedPowerSource()).isEqualTo(DEFAULT_TESTED_POWER_SOURCE);
        assertThat(testDevSupportingTool.getTrammingSpeed()).isEqualTo(DEFAULT_TRAMMING_SPEED);
        assertThat(testDevSupportingTool.getTestedTrammingSpeed()).isEqualTo(DEFAULT_TESTED_TRAMMING_SPEED);
        assertThat(testDevSupportingTool.getBoltLengthRange()).isEqualTo(DEFAULT_BOLT_LENGTH_RANGE);
        assertThat(testDevSupportingTool.getTestedBoltLengthRange()).isEqualTo(DEFAULT_TESTED_BOLT_LENGTH_RANGE);
        assertThat(testDevSupportingTool.getMinimumOperatingHeight()).isEqualTo(DEFAULT_MINIMUM_OPERATING_HEIGHT);
        assertThat(testDevSupportingTool.getTestedMinimumOperatingHeight()).isEqualTo(DEFAULT_TESTED_MINIMUM_OPERATING_HEIGHT);
        assertThat(testDevSupportingTool.getDrillSpeed()).isEqualTo(DEFAULT_DRILL_SPEED);
        assertThat(testDevSupportingTool.getTestedDrillSpeed()).isEqualTo(DEFAULT_TESTED_DRILL_SPEED);
        assertThat(testDevSupportingTool.getGradeability()).isEqualTo(DEFAULT_GRADEABILITY);
        assertThat(testDevSupportingTool.getTestedGradeability()).isEqualTo(DEFAULT_TESTED_GRADEABILITY);
        assertThat(testDevSupportingTool.getNumberOfBooms()).isEqualTo(DEFAULT_NUMBER_OF_BOOMS);
        assertThat(testDevSupportingTool.getTestedNumberOfBooms()).isEqualTo(DEFAULT_TESTED_NUMBER_OF_BOOMS);
        assertThat(testDevSupportingTool.getTypeOfBoom()).isEqualTo(DEFAULT_TYPE_OF_BOOM);
        assertThat(testDevSupportingTool.getTestedTypeOfBoom()).isEqualTo(DEFAULT_TESTED_TYPE_OF_BOOM);
        assertThat(testDevSupportingTool.getOuterTurningRadius()).isEqualTo(DEFAULT_OUTER_TURNING_RADIUS);
        assertThat(testDevSupportingTool.getTestedOuterTurningRadius()).isEqualTo(DEFAULT_TESTED_OUTER_TURNING_RADIUS);
        assertThat(testDevSupportingTool.getNnerTurningRadius()).isEqualTo(DEFAULT_NNER_TURNING_RADIUS);
        assertThat(testDevSupportingTool.getTestedInnerTurningRadius()).isEqualTo(DEFAULT_TESTED_INNER_TURNING_RADIUS);
        assertThat(testDevSupportingTool.getObservations1()).isEqualTo(DEFAULT_OBSERVATIONS_1);
        assertThat(testDevSupportingTool.getTestedObservations1()).isEqualTo(DEFAULT_TESTED_OBSERVATIONS_1);
        assertThat(testDevSupportingTool.getObservations2()).isEqualTo(DEFAULT_OBSERVATIONS_2);
        assertThat(testDevSupportingTool.getTestedObservations2()).isEqualTo(DEFAULT_TESTED_OBSERVATIONS_2);
        assertThat(testDevSupportingTool.getObservations3()).isEqualTo(DEFAULT_OBSERVATIONS_3);
        assertThat(testDevSupportingTool.getTestedObservations3()).isEqualTo(DEFAULT_TESTED_OBSERVATIONS_3);
        assertThat(testDevSupportingTool.getObservations4()).isEqualTo(DEFAULT_OBSERVATIONS_4);
        assertThat(testDevSupportingTool.getTestedObservations4()).isEqualTo(DEFAULT_TESTED_OBSERVATIONS_4);
        assertThat(testDevSupportingTool.getObservations5()).isEqualTo(DEFAULT_OBSERVATIONS_5);
        assertThat(testDevSupportingTool.getTestedObservations5()).isEqualTo(DEFAULT_TESTED_OBSERVATIONS_5);
        assertThat(testDevSupportingTool.getObservations6()).isEqualTo(DEFAULT_OBSERVATIONS_6);
        assertThat(testDevSupportingTool.getTestedObservations6()).isEqualTo(DEFAULT_TESTED_OBSERVATIONS_6);
        assertThat(testDevSupportingTool.getOperators_Comments()).isEqualTo(DEFAULT_OPERATORS_COMMENTS);
        assertThat(testDevSupportingTool.getTestedOperators_Comments()).isEqualTo(DEFAULT_TESTED_OPERATORS_COMMENTS);

        // Validate the DevSupportingTool in Elasticsearch
        DevSupportingTool devSupportingToolEs = devSupportingToolSearchRepository.findOne(testDevSupportingTool.getId());
        assertThat(devSupportingToolEs).isEqualToComparingFieldByField(testDevSupportingTool);
    }

    @Test
    @Transactional
    public void createDevSupportingToolWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = devSupportingToolRepository.findAll().size();

        // Create the DevSupportingTool with an existing ID
        devSupportingTool.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDevSupportingToolMockMvc.perform(post("/api/dev-supporting-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(devSupportingTool)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<DevSupportingTool> devSupportingToolList = devSupportingToolRepository.findAll();
        assertThat(devSupportingToolList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = devSupportingToolRepository.findAll().size();
        // set the field null
        devSupportingTool.setName(null);

        // Create the DevSupportingTool, which fails.

        restDevSupportingToolMockMvc.perform(post("/api/dev-supporting-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(devSupportingTool)))
            .andExpect(status().isBadRequest());

        List<DevSupportingTool> devSupportingToolList = devSupportingToolRepository.findAll();
        assertThat(devSupportingToolList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkModelIsRequired() throws Exception {
        int databaseSizeBeforeTest = devSupportingToolRepository.findAll().size();
        // set the field null
        devSupportingTool.setModel(null);

        // Create the DevSupportingTool, which fails.

        restDevSupportingToolMockMvc.perform(post("/api/dev-supporting-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(devSupportingTool)))
            .andExpect(status().isBadRequest());

        List<DevSupportingTool> devSupportingToolList = devSupportingToolRepository.findAll();
        assertThat(devSupportingToolList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTechnologyTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = devSupportingToolRepository.findAll().size();
        // set the field null
        devSupportingTool.setTechnologyType(null);

        // Create the DevSupportingTool, which fails.

        restDevSupportingToolMockMvc.perform(post("/api/dev-supporting-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(devSupportingTool)))
            .andExpect(status().isBadRequest());

        List<DevSupportingTool> devSupportingToolList = devSupportingToolRepository.findAll();
        assertThat(devSupportingToolList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTrlIsRequired() throws Exception {
        int databaseSizeBeforeTest = devSupportingToolRepository.findAll().size();
        // set the field null
        devSupportingTool.setTrl(null);

        // Create the DevSupportingTool, which fails.

        restDevSupportingToolMockMvc.perform(post("/api/dev-supporting-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(devSupportingTool)))
            .andExpect(status().isBadRequest());

        List<DevSupportingTool> devSupportingToolList = devSupportingToolRepository.findAll();
        assertThat(devSupportingToolList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTypeOfMachineIsRequired() throws Exception {
        int databaseSizeBeforeTest = devSupportingToolRepository.findAll().size();
        // set the field null
        devSupportingTool.setTypeOfMachine(null);

        // Create the DevSupportingTool, which fails.

        restDevSupportingToolMockMvc.perform(post("/api/dev-supporting-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(devSupportingTool)))
            .andExpect(status().isBadRequest());

        List<DevSupportingTool> devSupportingToolList = devSupportingToolRepository.findAll();
        assertThat(devSupportingToolList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkWeightIsRequired() throws Exception {
        int databaseSizeBeforeTest = devSupportingToolRepository.findAll().size();
        // set the field null
        devSupportingTool.setWeight(null);

        // Create the DevSupportingTool, which fails.

        restDevSupportingToolMockMvc.perform(post("/api/dev-supporting-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(devSupportingTool)))
            .andExpect(status().isBadRequest());

        List<DevSupportingTool> devSupportingToolList = devSupportingToolRepository.findAll();
        assertThat(devSupportingToolList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLengthIsRequired() throws Exception {
        int databaseSizeBeforeTest = devSupportingToolRepository.findAll().size();
        // set the field null
        devSupportingTool.setLength(null);

        // Create the DevSupportingTool, which fails.

        restDevSupportingToolMockMvc.perform(post("/api/dev-supporting-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(devSupportingTool)))
            .andExpect(status().isBadRequest());

        List<DevSupportingTool> devSupportingToolList = devSupportingToolRepository.findAll();
        assertThat(devSupportingToolList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkWidthIsRequired() throws Exception {
        int databaseSizeBeforeTest = devSupportingToolRepository.findAll().size();
        // set the field null
        devSupportingTool.setWidth(null);

        // Create the DevSupportingTool, which fails.

        restDevSupportingToolMockMvc.perform(post("/api/dev-supporting-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(devSupportingTool)))
            .andExpect(status().isBadRequest());

        List<DevSupportingTool> devSupportingToolList = devSupportingToolRepository.findAll();
        assertThat(devSupportingToolList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPowerRatingPercussionIsRequired() throws Exception {
        int databaseSizeBeforeTest = devSupportingToolRepository.findAll().size();
        // set the field null
        devSupportingTool.setPowerRatingPercussion(null);

        // Create the DevSupportingTool, which fails.

        restDevSupportingToolMockMvc.perform(post("/api/dev-supporting-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(devSupportingTool)))
            .andExpect(status().isBadRequest());

        List<DevSupportingTool> devSupportingToolList = devSupportingToolRepository.findAll();
        assertThat(devSupportingToolList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNoiseLevelAtOperatorStandIsRequired() throws Exception {
        int databaseSizeBeforeTest = devSupportingToolRepository.findAll().size();
        // set the field null
        devSupportingTool.setNoiseLevelAtOperatorStand(null);

        // Create the DevSupportingTool, which fails.

        restDevSupportingToolMockMvc.perform(post("/api/dev-supporting-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(devSupportingTool)))
            .andExpect(status().isBadRequest());

        List<DevSupportingTool> devSupportingToolList = devSupportingToolRepository.findAll();
        assertThat(devSupportingToolList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDrillWaterConsumptionIsRequired() throws Exception {
        int databaseSizeBeforeTest = devSupportingToolRepository.findAll().size();
        // set the field null
        devSupportingTool.setDrillWaterConsumption(null);

        // Create the DevSupportingTool, which fails.

        restDevSupportingToolMockMvc.perform(post("/api/dev-supporting-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(devSupportingTool)))
            .andExpect(status().isBadRequest());

        List<DevSupportingTool> devSupportingToolList = devSupportingToolRepository.findAll();
        assertThat(devSupportingToolList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkWaterConsumptionPerMetreDrilledIsRequired() throws Exception {
        int databaseSizeBeforeTest = devSupportingToolRepository.findAll().size();
        // set the field null
        devSupportingTool.setWaterConsumptionPerMetreDrilled(null);

        // Create the DevSupportingTool, which fails.

        restDevSupportingToolMockMvc.perform(post("/api/dev-supporting-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(devSupportingTool)))
            .andExpect(status().isBadRequest());

        List<DevSupportingTool> devSupportingToolList = devSupportingToolRepository.findAll();
        assertThat(devSupportingToolList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPowerSourceIsRequired() throws Exception {
        int databaseSizeBeforeTest = devSupportingToolRepository.findAll().size();
        // set the field null
        devSupportingTool.setPowerSource(null);

        // Create the DevSupportingTool, which fails.

        restDevSupportingToolMockMvc.perform(post("/api/dev-supporting-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(devSupportingTool)))
            .andExpect(status().isBadRequest());

        List<DevSupportingTool> devSupportingToolList = devSupportingToolRepository.findAll();
        assertThat(devSupportingToolList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkBoltLengthRangeIsRequired() throws Exception {
        int databaseSizeBeforeTest = devSupportingToolRepository.findAll().size();
        // set the field null
        devSupportingTool.setBoltLengthRange(null);

        // Create the DevSupportingTool, which fails.

        restDevSupportingToolMockMvc.perform(post("/api/dev-supporting-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(devSupportingTool)))
            .andExpect(status().isBadRequest());

        List<DevSupportingTool> devSupportingToolList = devSupportingToolRepository.findAll();
        assertThat(devSupportingToolList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMinimumOperatingHeightIsRequired() throws Exception {
        int databaseSizeBeforeTest = devSupportingToolRepository.findAll().size();
        // set the field null
        devSupportingTool.setMinimumOperatingHeight(null);

        // Create the DevSupportingTool, which fails.

        restDevSupportingToolMockMvc.perform(post("/api/dev-supporting-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(devSupportingTool)))
            .andExpect(status().isBadRequest());

        List<DevSupportingTool> devSupportingToolList = devSupportingToolRepository.findAll();
        assertThat(devSupportingToolList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDrillSpeedIsRequired() throws Exception {
        int databaseSizeBeforeTest = devSupportingToolRepository.findAll().size();
        // set the field null
        devSupportingTool.setDrillSpeed(null);

        // Create the DevSupportingTool, which fails.

        restDevSupportingToolMockMvc.perform(post("/api/dev-supporting-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(devSupportingTool)))
            .andExpect(status().isBadRequest());

        List<DevSupportingTool> devSupportingToolList = devSupportingToolRepository.findAll();
        assertThat(devSupportingToolList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkGradeabilityIsRequired() throws Exception {
        int databaseSizeBeforeTest = devSupportingToolRepository.findAll().size();
        // set the field null
        devSupportingTool.setGradeability(null);

        // Create the DevSupportingTool, which fails.

        restDevSupportingToolMockMvc.perform(post("/api/dev-supporting-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(devSupportingTool)))
            .andExpect(status().isBadRequest());

        List<DevSupportingTool> devSupportingToolList = devSupportingToolRepository.findAll();
        assertThat(devSupportingToolList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNumberOfBoomsIsRequired() throws Exception {
        int databaseSizeBeforeTest = devSupportingToolRepository.findAll().size();
        // set the field null
        devSupportingTool.setNumberOfBooms(null);

        // Create the DevSupportingTool, which fails.

        restDevSupportingToolMockMvc.perform(post("/api/dev-supporting-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(devSupportingTool)))
            .andExpect(status().isBadRequest());

        List<DevSupportingTool> devSupportingToolList = devSupportingToolRepository.findAll();
        assertThat(devSupportingToolList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTypeOfBoomIsRequired() throws Exception {
        int databaseSizeBeforeTest = devSupportingToolRepository.findAll().size();
        // set the field null
        devSupportingTool.setTypeOfBoom(null);

        // Create the DevSupportingTool, which fails.

        restDevSupportingToolMockMvc.perform(post("/api/dev-supporting-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(devSupportingTool)))
            .andExpect(status().isBadRequest());

        List<DevSupportingTool> devSupportingToolList = devSupportingToolRepository.findAll();
        assertThat(devSupportingToolList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkOuterTurningRadiusIsRequired() throws Exception {
        int databaseSizeBeforeTest = devSupportingToolRepository.findAll().size();
        // set the field null
        devSupportingTool.setOuterTurningRadius(null);

        // Create the DevSupportingTool, which fails.

        restDevSupportingToolMockMvc.perform(post("/api/dev-supporting-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(devSupportingTool)))
            .andExpect(status().isBadRequest());

        List<DevSupportingTool> devSupportingToolList = devSupportingToolRepository.findAll();
        assertThat(devSupportingToolList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNnerTurningRadiusIsRequired() throws Exception {
        int databaseSizeBeforeTest = devSupportingToolRepository.findAll().size();
        // set the field null
        devSupportingTool.setNnerTurningRadius(null);

        // Create the DevSupportingTool, which fails.

        restDevSupportingToolMockMvc.perform(post("/api/dev-supporting-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(devSupportingTool)))
            .andExpect(status().isBadRequest());

        List<DevSupportingTool> devSupportingToolList = devSupportingToolRepository.findAll();
        assertThat(devSupportingToolList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkObservations1IsRequired() throws Exception {
        int databaseSizeBeforeTest = devSupportingToolRepository.findAll().size();
        // set the field null
        devSupportingTool.setObservations1(null);

        // Create the DevSupportingTool, which fails.

        restDevSupportingToolMockMvc.perform(post("/api/dev-supporting-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(devSupportingTool)))
            .andExpect(status().isBadRequest());

        List<DevSupportingTool> devSupportingToolList = devSupportingToolRepository.findAll();
        assertThat(devSupportingToolList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkObservations2IsRequired() throws Exception {
        int databaseSizeBeforeTest = devSupportingToolRepository.findAll().size();
        // set the field null
        devSupportingTool.setObservations2(null);

        // Create the DevSupportingTool, which fails.

        restDevSupportingToolMockMvc.perform(post("/api/dev-supporting-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(devSupportingTool)))
            .andExpect(status().isBadRequest());

        List<DevSupportingTool> devSupportingToolList = devSupportingToolRepository.findAll();
        assertThat(devSupportingToolList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkObservations3IsRequired() throws Exception {
        int databaseSizeBeforeTest = devSupportingToolRepository.findAll().size();
        // set the field null
        devSupportingTool.setObservations3(null);

        // Create the DevSupportingTool, which fails.

        restDevSupportingToolMockMvc.perform(post("/api/dev-supporting-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(devSupportingTool)))
            .andExpect(status().isBadRequest());

        List<DevSupportingTool> devSupportingToolList = devSupportingToolRepository.findAll();
        assertThat(devSupportingToolList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkObservations4IsRequired() throws Exception {
        int databaseSizeBeforeTest = devSupportingToolRepository.findAll().size();
        // set the field null
        devSupportingTool.setObservations4(null);

        // Create the DevSupportingTool, which fails.

        restDevSupportingToolMockMvc.perform(post("/api/dev-supporting-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(devSupportingTool)))
            .andExpect(status().isBadRequest());

        List<DevSupportingTool> devSupportingToolList = devSupportingToolRepository.findAll();
        assertThat(devSupportingToolList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkObservations5IsRequired() throws Exception {
        int databaseSizeBeforeTest = devSupportingToolRepository.findAll().size();
        // set the field null
        devSupportingTool.setObservations5(null);

        // Create the DevSupportingTool, which fails.

        restDevSupportingToolMockMvc.perform(post("/api/dev-supporting-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(devSupportingTool)))
            .andExpect(status().isBadRequest());

        List<DevSupportingTool> devSupportingToolList = devSupportingToolRepository.findAll();
        assertThat(devSupportingToolList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkObservations6IsRequired() throws Exception {
        int databaseSizeBeforeTest = devSupportingToolRepository.findAll().size();
        // set the field null
        devSupportingTool.setObservations6(null);

        // Create the DevSupportingTool, which fails.

        restDevSupportingToolMockMvc.perform(post("/api/dev-supporting-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(devSupportingTool)))
            .andExpect(status().isBadRequest());

        List<DevSupportingTool> devSupportingToolList = devSupportingToolRepository.findAll();
        assertThat(devSupportingToolList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkOperators_CommentsIsRequired() throws Exception {
        int databaseSizeBeforeTest = devSupportingToolRepository.findAll().size();
        // set the field null
        devSupportingTool.setOperators_Comments(null);

        // Create the DevSupportingTool, which fails.

        restDevSupportingToolMockMvc.perform(post("/api/dev-supporting-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(devSupportingTool)))
            .andExpect(status().isBadRequest());

        List<DevSupportingTool> devSupportingToolList = devSupportingToolRepository.findAll();
        assertThat(devSupportingToolList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllDevSupportingTools() throws Exception {
        // Initialize the database
        devSupportingToolRepository.saveAndFlush(devSupportingTool);

        // Get all the devSupportingToolList
        restDevSupportingToolMockMvc.perform(get("/api/dev-supporting-tools?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(devSupportingTool.getId().intValue())))
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
            .andExpect(jsonPath("$.[*].powerRatingPercussion").value(hasItem(DEFAULT_POWER_RATING_PERCUSSION.doubleValue())))
            .andExpect(jsonPath("$.[*].testedPowerRatingPercussion").value(hasItem(DEFAULT_TESTED_POWER_RATING_PERCUSSION.doubleValue())))
            .andExpect(jsonPath("$.[*].noiseLevelAtOperatorStand").value(hasItem(DEFAULT_NOISE_LEVEL_AT_OPERATOR_STAND.doubleValue())))
            .andExpect(jsonPath("$.[*].testedNoiseLevelAtOperatorStand").value(hasItem(DEFAULT_TESTED_NOISE_LEVEL_AT_OPERATOR_STAND.doubleValue())))
            .andExpect(jsonPath("$.[*].holeSizeRange").value(hasItem(DEFAULT_HOLE_SIZE_RANGE.toString())))
            .andExpect(jsonPath("$.[*].testedHoleSizeRange").value(hasItem(DEFAULT_TESTED_HOLE_SIZE_RANGE.toString())))
            .andExpect(jsonPath("$.[*].drillWaterConsumption").value(hasItem(DEFAULT_DRILL_WATER_CONSUMPTION.doubleValue())))
            .andExpect(jsonPath("$.[*].testedDrillWaterConsumption").value(hasItem(DEFAULT_TESTED_DRILL_WATER_CONSUMPTION.doubleValue())))
            .andExpect(jsonPath("$.[*].waterConsumptionPerMetreDrilled").value(hasItem(DEFAULT_WATER_CONSUMPTION_PER_METRE_DRILLED.doubleValue())))
            .andExpect(jsonPath("$.[*].testedWaterConsumptionPerMetreDrilled").value(hasItem(DEFAULT_TESTED_WATER_CONSUMPTION_PER_METRE_DRILLED.doubleValue())))
            .andExpect(jsonPath("$.[*].powerSource").value(hasItem(DEFAULT_POWER_SOURCE.toString())))
            .andExpect(jsonPath("$.[*].testedPowerSource").value(hasItem(DEFAULT_TESTED_POWER_SOURCE.toString())))
            .andExpect(jsonPath("$.[*].trammingSpeed").value(hasItem(DEFAULT_TRAMMING_SPEED.doubleValue())))
            .andExpect(jsonPath("$.[*].testedTrammingSpeed").value(hasItem(DEFAULT_TESTED_TRAMMING_SPEED.doubleValue())))
            .andExpect(jsonPath("$.[*].boltLengthRange").value(hasItem(DEFAULT_BOLT_LENGTH_RANGE.toString())))
            .andExpect(jsonPath("$.[*].testedBoltLengthRange").value(hasItem(DEFAULT_TESTED_BOLT_LENGTH_RANGE.toString())))
            .andExpect(jsonPath("$.[*].minimumOperatingHeight").value(hasItem(DEFAULT_MINIMUM_OPERATING_HEIGHT.doubleValue())))
            .andExpect(jsonPath("$.[*].testedMinimumOperatingHeight").value(hasItem(DEFAULT_TESTED_MINIMUM_OPERATING_HEIGHT.doubleValue())))
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
            .andExpect(jsonPath("$.[*].nnerTurningRadius").value(hasItem(DEFAULT_NNER_TURNING_RADIUS.doubleValue())))
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
    public void getDevSupportingTool() throws Exception {
        // Initialize the database
        devSupportingToolRepository.saveAndFlush(devSupportingTool);

        // Get the devSupportingTool
        restDevSupportingToolMockMvc.perform(get("/api/dev-supporting-tools/{id}", devSupportingTool.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(devSupportingTool.getId().intValue()))
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
            .andExpect(jsonPath("$.powerRatingPercussion").value(DEFAULT_POWER_RATING_PERCUSSION.doubleValue()))
            .andExpect(jsonPath("$.testedPowerRatingPercussion").value(DEFAULT_TESTED_POWER_RATING_PERCUSSION.doubleValue()))
            .andExpect(jsonPath("$.noiseLevelAtOperatorStand").value(DEFAULT_NOISE_LEVEL_AT_OPERATOR_STAND.doubleValue()))
            .andExpect(jsonPath("$.testedNoiseLevelAtOperatorStand").value(DEFAULT_TESTED_NOISE_LEVEL_AT_OPERATOR_STAND.doubleValue()))
            .andExpect(jsonPath("$.holeSizeRange").value(DEFAULT_HOLE_SIZE_RANGE.toString()))
            .andExpect(jsonPath("$.testedHoleSizeRange").value(DEFAULT_TESTED_HOLE_SIZE_RANGE.toString()))
            .andExpect(jsonPath("$.drillWaterConsumption").value(DEFAULT_DRILL_WATER_CONSUMPTION.doubleValue()))
            .andExpect(jsonPath("$.testedDrillWaterConsumption").value(DEFAULT_TESTED_DRILL_WATER_CONSUMPTION.doubleValue()))
            .andExpect(jsonPath("$.waterConsumptionPerMetreDrilled").value(DEFAULT_WATER_CONSUMPTION_PER_METRE_DRILLED.doubleValue()))
            .andExpect(jsonPath("$.testedWaterConsumptionPerMetreDrilled").value(DEFAULT_TESTED_WATER_CONSUMPTION_PER_METRE_DRILLED.doubleValue()))
            .andExpect(jsonPath("$.powerSource").value(DEFAULT_POWER_SOURCE.toString()))
            .andExpect(jsonPath("$.testedPowerSource").value(DEFAULT_TESTED_POWER_SOURCE.toString()))
            .andExpect(jsonPath("$.trammingSpeed").value(DEFAULT_TRAMMING_SPEED.doubleValue()))
            .andExpect(jsonPath("$.testedTrammingSpeed").value(DEFAULT_TESTED_TRAMMING_SPEED.doubleValue()))
            .andExpect(jsonPath("$.boltLengthRange").value(DEFAULT_BOLT_LENGTH_RANGE.toString()))
            .andExpect(jsonPath("$.testedBoltLengthRange").value(DEFAULT_TESTED_BOLT_LENGTH_RANGE.toString()))
            .andExpect(jsonPath("$.minimumOperatingHeight").value(DEFAULT_MINIMUM_OPERATING_HEIGHT.doubleValue()))
            .andExpect(jsonPath("$.testedMinimumOperatingHeight").value(DEFAULT_TESTED_MINIMUM_OPERATING_HEIGHT.doubleValue()))
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
            .andExpect(jsonPath("$.nnerTurningRadius").value(DEFAULT_NNER_TURNING_RADIUS.doubleValue()))
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
    public void getNonExistingDevSupportingTool() throws Exception {
        // Get the devSupportingTool
        restDevSupportingToolMockMvc.perform(get("/api/dev-supporting-tools/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDevSupportingTool() throws Exception {
        // Initialize the database
        devSupportingToolRepository.saveAndFlush(devSupportingTool);
        devSupportingToolSearchRepository.save(devSupportingTool);
        int databaseSizeBeforeUpdate = devSupportingToolRepository.findAll().size();

        // Update the devSupportingTool
        DevSupportingTool updatedDevSupportingTool = devSupportingToolRepository.findOne(devSupportingTool.getId());
        updatedDevSupportingTool
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
            .powerRatingPercussion(UPDATED_POWER_RATING_PERCUSSION)
            .testedPowerRatingPercussion(UPDATED_TESTED_POWER_RATING_PERCUSSION)
            .noiseLevelAtOperatorStand(UPDATED_NOISE_LEVEL_AT_OPERATOR_STAND)
            .testedNoiseLevelAtOperatorStand(UPDATED_TESTED_NOISE_LEVEL_AT_OPERATOR_STAND)
            .holeSizeRange(UPDATED_HOLE_SIZE_RANGE)
            .testedHoleSizeRange(UPDATED_TESTED_HOLE_SIZE_RANGE)
            .drillWaterConsumption(UPDATED_DRILL_WATER_CONSUMPTION)
            .testedDrillWaterConsumption(UPDATED_TESTED_DRILL_WATER_CONSUMPTION)
            .waterConsumptionPerMetreDrilled(UPDATED_WATER_CONSUMPTION_PER_METRE_DRILLED)
            .testedWaterConsumptionPerMetreDrilled(UPDATED_TESTED_WATER_CONSUMPTION_PER_METRE_DRILLED)
            .powerSource(UPDATED_POWER_SOURCE)
            .testedPowerSource(UPDATED_TESTED_POWER_SOURCE)
            .trammingSpeed(UPDATED_TRAMMING_SPEED)
            .testedTrammingSpeed(UPDATED_TESTED_TRAMMING_SPEED)
            .boltLengthRange(UPDATED_BOLT_LENGTH_RANGE)
            .testedBoltLengthRange(UPDATED_TESTED_BOLT_LENGTH_RANGE)
            .minimumOperatingHeight(UPDATED_MINIMUM_OPERATING_HEIGHT)
            .testedMinimumOperatingHeight(UPDATED_TESTED_MINIMUM_OPERATING_HEIGHT)
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
            .nnerTurningRadius(UPDATED_NNER_TURNING_RADIUS)
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

        restDevSupportingToolMockMvc.perform(put("/api/dev-supporting-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedDevSupportingTool)))
            .andExpect(status().isOk());

        // Validate the DevSupportingTool in the database
        List<DevSupportingTool> devSupportingToolList = devSupportingToolRepository.findAll();
        assertThat(devSupportingToolList).hasSize(databaseSizeBeforeUpdate);
        DevSupportingTool testDevSupportingTool = devSupportingToolList.get(devSupportingToolList.size() - 1);
        assertThat(testDevSupportingTool.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testDevSupportingTool.getModel()).isEqualTo(UPDATED_MODEL);
        assertThat(testDevSupportingTool.getTechnologyType()).isEqualTo(UPDATED_TECHNOLOGY_TYPE);
        assertThat(testDevSupportingTool.getTrl()).isEqualTo(UPDATED_TRL);
        assertThat(testDevSupportingTool.getPhoto()).isEqualTo(UPDATED_PHOTO);
        assertThat(testDevSupportingTool.getPhotoContentType()).isEqualTo(UPDATED_PHOTO_CONTENT_TYPE);
        assertThat(testDevSupportingTool.getDatasheet()).isEqualTo(UPDATED_DATASHEET);
        assertThat(testDevSupportingTool.getDatasheetContentType()).isEqualTo(UPDATED_DATASHEET_CONTENT_TYPE);
        assertThat(testDevSupportingTool.getTypeOfMachine()).isEqualTo(UPDATED_TYPE_OF_MACHINE);
        assertThat(testDevSupportingTool.getTestedTypeOfMachine()).isEqualTo(UPDATED_TESTED_TYPE_OF_MACHINE);
        assertThat(testDevSupportingTool.getWeight()).isEqualTo(UPDATED_WEIGHT);
        assertThat(testDevSupportingTool.getTestedWeight()).isEqualTo(UPDATED_TESTED_WEIGHT);
        assertThat(testDevSupportingTool.getLength()).isEqualTo(UPDATED_LENGTH);
        assertThat(testDevSupportingTool.getTestedLength()).isEqualTo(UPDATED_TESTED_LENGTH);
        assertThat(testDevSupportingTool.getWidth()).isEqualTo(UPDATED_WIDTH);
        assertThat(testDevSupportingTool.getTestedWidth()).isEqualTo(UPDATED_TESTED_WIDTH);
        assertThat(testDevSupportingTool.getPowerRatingPercussion()).isEqualTo(UPDATED_POWER_RATING_PERCUSSION);
        assertThat(testDevSupportingTool.getTestedPowerRatingPercussion()).isEqualTo(UPDATED_TESTED_POWER_RATING_PERCUSSION);
        assertThat(testDevSupportingTool.getNoiseLevelAtOperatorStand()).isEqualTo(UPDATED_NOISE_LEVEL_AT_OPERATOR_STAND);
        assertThat(testDevSupportingTool.getTestedNoiseLevelAtOperatorStand()).isEqualTo(UPDATED_TESTED_NOISE_LEVEL_AT_OPERATOR_STAND);
        assertThat(testDevSupportingTool.getHoleSizeRange()).isEqualTo(UPDATED_HOLE_SIZE_RANGE);
        assertThat(testDevSupportingTool.getTestedHoleSizeRange()).isEqualTo(UPDATED_TESTED_HOLE_SIZE_RANGE);
        assertThat(testDevSupportingTool.getDrillWaterConsumption()).isEqualTo(UPDATED_DRILL_WATER_CONSUMPTION);
        assertThat(testDevSupportingTool.getTestedDrillWaterConsumption()).isEqualTo(UPDATED_TESTED_DRILL_WATER_CONSUMPTION);
        assertThat(testDevSupportingTool.getWaterConsumptionPerMetreDrilled()).isEqualTo(UPDATED_WATER_CONSUMPTION_PER_METRE_DRILLED);
        assertThat(testDevSupportingTool.getTestedWaterConsumptionPerMetreDrilled()).isEqualTo(UPDATED_TESTED_WATER_CONSUMPTION_PER_METRE_DRILLED);
        assertThat(testDevSupportingTool.getPowerSource()).isEqualTo(UPDATED_POWER_SOURCE);
        assertThat(testDevSupportingTool.getTestedPowerSource()).isEqualTo(UPDATED_TESTED_POWER_SOURCE);
        assertThat(testDevSupportingTool.getTrammingSpeed()).isEqualTo(UPDATED_TRAMMING_SPEED);
        assertThat(testDevSupportingTool.getTestedTrammingSpeed()).isEqualTo(UPDATED_TESTED_TRAMMING_SPEED);
        assertThat(testDevSupportingTool.getBoltLengthRange()).isEqualTo(UPDATED_BOLT_LENGTH_RANGE);
        assertThat(testDevSupportingTool.getTestedBoltLengthRange()).isEqualTo(UPDATED_TESTED_BOLT_LENGTH_RANGE);
        assertThat(testDevSupportingTool.getMinimumOperatingHeight()).isEqualTo(UPDATED_MINIMUM_OPERATING_HEIGHT);
        assertThat(testDevSupportingTool.getTestedMinimumOperatingHeight()).isEqualTo(UPDATED_TESTED_MINIMUM_OPERATING_HEIGHT);
        assertThat(testDevSupportingTool.getDrillSpeed()).isEqualTo(UPDATED_DRILL_SPEED);
        assertThat(testDevSupportingTool.getTestedDrillSpeed()).isEqualTo(UPDATED_TESTED_DRILL_SPEED);
        assertThat(testDevSupportingTool.getGradeability()).isEqualTo(UPDATED_GRADEABILITY);
        assertThat(testDevSupportingTool.getTestedGradeability()).isEqualTo(UPDATED_TESTED_GRADEABILITY);
        assertThat(testDevSupportingTool.getNumberOfBooms()).isEqualTo(UPDATED_NUMBER_OF_BOOMS);
        assertThat(testDevSupportingTool.getTestedNumberOfBooms()).isEqualTo(UPDATED_TESTED_NUMBER_OF_BOOMS);
        assertThat(testDevSupportingTool.getTypeOfBoom()).isEqualTo(UPDATED_TYPE_OF_BOOM);
        assertThat(testDevSupportingTool.getTestedTypeOfBoom()).isEqualTo(UPDATED_TESTED_TYPE_OF_BOOM);
        assertThat(testDevSupportingTool.getOuterTurningRadius()).isEqualTo(UPDATED_OUTER_TURNING_RADIUS);
        assertThat(testDevSupportingTool.getTestedOuterTurningRadius()).isEqualTo(UPDATED_TESTED_OUTER_TURNING_RADIUS);
        assertThat(testDevSupportingTool.getNnerTurningRadius()).isEqualTo(UPDATED_NNER_TURNING_RADIUS);
        assertThat(testDevSupportingTool.getTestedInnerTurningRadius()).isEqualTo(UPDATED_TESTED_INNER_TURNING_RADIUS);
        assertThat(testDevSupportingTool.getObservations1()).isEqualTo(UPDATED_OBSERVATIONS_1);
        assertThat(testDevSupportingTool.getTestedObservations1()).isEqualTo(UPDATED_TESTED_OBSERVATIONS_1);
        assertThat(testDevSupportingTool.getObservations2()).isEqualTo(UPDATED_OBSERVATIONS_2);
        assertThat(testDevSupportingTool.getTestedObservations2()).isEqualTo(UPDATED_TESTED_OBSERVATIONS_2);
        assertThat(testDevSupportingTool.getObservations3()).isEqualTo(UPDATED_OBSERVATIONS_3);
        assertThat(testDevSupportingTool.getTestedObservations3()).isEqualTo(UPDATED_TESTED_OBSERVATIONS_3);
        assertThat(testDevSupportingTool.getObservations4()).isEqualTo(UPDATED_OBSERVATIONS_4);
        assertThat(testDevSupportingTool.getTestedObservations4()).isEqualTo(UPDATED_TESTED_OBSERVATIONS_4);
        assertThat(testDevSupportingTool.getObservations5()).isEqualTo(UPDATED_OBSERVATIONS_5);
        assertThat(testDevSupportingTool.getTestedObservations5()).isEqualTo(UPDATED_TESTED_OBSERVATIONS_5);
        assertThat(testDevSupportingTool.getObservations6()).isEqualTo(UPDATED_OBSERVATIONS_6);
        assertThat(testDevSupportingTool.getTestedObservations6()).isEqualTo(UPDATED_TESTED_OBSERVATIONS_6);
        assertThat(testDevSupportingTool.getOperators_Comments()).isEqualTo(UPDATED_OPERATORS_COMMENTS);
        assertThat(testDevSupportingTool.getTestedOperators_Comments()).isEqualTo(UPDATED_TESTED_OPERATORS_COMMENTS);

        // Validate the DevSupportingTool in Elasticsearch
        DevSupportingTool devSupportingToolEs = devSupportingToolSearchRepository.findOne(testDevSupportingTool.getId());
        assertThat(devSupportingToolEs).isEqualToComparingFieldByField(testDevSupportingTool);
    }

    @Test
    @Transactional
    public void updateNonExistingDevSupportingTool() throws Exception {
        int databaseSizeBeforeUpdate = devSupportingToolRepository.findAll().size();

        // Create the DevSupportingTool

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restDevSupportingToolMockMvc.perform(put("/api/dev-supporting-tools")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(devSupportingTool)))
            .andExpect(status().isCreated());

        // Validate the DevSupportingTool in the database
        List<DevSupportingTool> devSupportingToolList = devSupportingToolRepository.findAll();
        assertThat(devSupportingToolList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteDevSupportingTool() throws Exception {
        // Initialize the database
        devSupportingToolRepository.saveAndFlush(devSupportingTool);
        devSupportingToolSearchRepository.save(devSupportingTool);
        int databaseSizeBeforeDelete = devSupportingToolRepository.findAll().size();

        // Get the devSupportingTool
        restDevSupportingToolMockMvc.perform(delete("/api/dev-supporting-tools/{id}", devSupportingTool.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate Elasticsearch is empty
        boolean devSupportingToolExistsInEs = devSupportingToolSearchRepository.exists(devSupportingTool.getId());
        assertThat(devSupportingToolExistsInEs).isFalse();

        // Validate the database is empty
        List<DevSupportingTool> devSupportingToolList = devSupportingToolRepository.findAll();
        assertThat(devSupportingToolList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void searchDevSupportingTool() throws Exception {
        // Initialize the database
        devSupportingToolRepository.saveAndFlush(devSupportingTool);
        devSupportingToolSearchRepository.save(devSupportingTool);

        // Search the devSupportingTool
        restDevSupportingToolMockMvc.perform(get("/api/_search/dev-supporting-tools?query=id:" + devSupportingTool.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(devSupportingTool.getId().intValue())))
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
            .andExpect(jsonPath("$.[*].powerRatingPercussion").value(hasItem(DEFAULT_POWER_RATING_PERCUSSION.doubleValue())))
            .andExpect(jsonPath("$.[*].testedPowerRatingPercussion").value(hasItem(DEFAULT_TESTED_POWER_RATING_PERCUSSION.doubleValue())))
            .andExpect(jsonPath("$.[*].noiseLevelAtOperatorStand").value(hasItem(DEFAULT_NOISE_LEVEL_AT_OPERATOR_STAND.doubleValue())))
            .andExpect(jsonPath("$.[*].testedNoiseLevelAtOperatorStand").value(hasItem(DEFAULT_TESTED_NOISE_LEVEL_AT_OPERATOR_STAND.doubleValue())))
            .andExpect(jsonPath("$.[*].holeSizeRange").value(hasItem(DEFAULT_HOLE_SIZE_RANGE.toString())))
            .andExpect(jsonPath("$.[*].testedHoleSizeRange").value(hasItem(DEFAULT_TESTED_HOLE_SIZE_RANGE.toString())))
            .andExpect(jsonPath("$.[*].drillWaterConsumption").value(hasItem(DEFAULT_DRILL_WATER_CONSUMPTION.doubleValue())))
            .andExpect(jsonPath("$.[*].testedDrillWaterConsumption").value(hasItem(DEFAULT_TESTED_DRILL_WATER_CONSUMPTION.doubleValue())))
            .andExpect(jsonPath("$.[*].waterConsumptionPerMetreDrilled").value(hasItem(DEFAULT_WATER_CONSUMPTION_PER_METRE_DRILLED.doubleValue())))
            .andExpect(jsonPath("$.[*].testedWaterConsumptionPerMetreDrilled").value(hasItem(DEFAULT_TESTED_WATER_CONSUMPTION_PER_METRE_DRILLED.doubleValue())))
            .andExpect(jsonPath("$.[*].powerSource").value(hasItem(DEFAULT_POWER_SOURCE.toString())))
            .andExpect(jsonPath("$.[*].testedPowerSource").value(hasItem(DEFAULT_TESTED_POWER_SOURCE.toString())))
            .andExpect(jsonPath("$.[*].trammingSpeed").value(hasItem(DEFAULT_TRAMMING_SPEED.doubleValue())))
            .andExpect(jsonPath("$.[*].testedTrammingSpeed").value(hasItem(DEFAULT_TESTED_TRAMMING_SPEED.doubleValue())))
            .andExpect(jsonPath("$.[*].boltLengthRange").value(hasItem(DEFAULT_BOLT_LENGTH_RANGE.toString())))
            .andExpect(jsonPath("$.[*].testedBoltLengthRange").value(hasItem(DEFAULT_TESTED_BOLT_LENGTH_RANGE.toString())))
            .andExpect(jsonPath("$.[*].minimumOperatingHeight").value(hasItem(DEFAULT_MINIMUM_OPERATING_HEIGHT.doubleValue())))
            .andExpect(jsonPath("$.[*].testedMinimumOperatingHeight").value(hasItem(DEFAULT_TESTED_MINIMUM_OPERATING_HEIGHT.doubleValue())))
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
            .andExpect(jsonPath("$.[*].nnerTurningRadius").value(hasItem(DEFAULT_NNER_TURNING_RADIUS.doubleValue())))
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
        TestUtil.equalsVerifier(DevSupportingTool.class);
        DevSupportingTool devSupportingTool1 = new DevSupportingTool();
        devSupportingTool1.setId(1L);
        DevSupportingTool devSupportingTool2 = new DevSupportingTool();
        devSupportingTool2.setId(devSupportingTool1.getId());
        assertThat(devSupportingTool1).isEqualTo(devSupportingTool2);
        devSupportingTool2.setId(2L);
        assertThat(devSupportingTool1).isNotEqualTo(devSupportingTool2);
        devSupportingTool1.setId(null);
        assertThat(devSupportingTool1).isNotEqualTo(devSupportingTool2);
    }
}
