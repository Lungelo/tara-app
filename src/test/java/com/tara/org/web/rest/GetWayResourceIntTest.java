package com.tara.org.web.rest;

import com.tara.org.TaraApp;

import com.tara.org.domain.GetWay;
import com.tara.org.repository.GetWayRepository;
import com.tara.org.repository.search.GetWaySearchRepository;
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

import com.tara.org.domain.enumeration.Choice;
import com.tara.org.domain.enumeration.Choice;
/**
 * Test class for the GetWayResource REST controller.
 *
 * @see GetWayResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TaraApp.class)
public class GetWayResourceIntTest {

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

    private static final String DEFAULT_COMPATIBILITY = "AAAAAAAAAA";
    private static final String UPDATED_COMPATIBILITY = "BBBBBBBBBB";

    private static final String DEFAULT_UPGRADEABILITY = "AAAAAAAAAA";
    private static final String UPDATED_UPGRADEABILITY = "BBBBBBBBBB";

    private static final Choice DEFAULT_DUST_PROOF = Choice.YES;
    private static final Choice UPDATED_DUST_PROOF = Choice.NO;

    private static final Choice DEFAULT_WATER_OR_CONDENSATION_PROOF = Choice.YES;
    private static final Choice UPDATED_WATER_OR_CONDENSATION_PROOF = Choice.NO;

    private static final Float DEFAULT_TEMPERATURE = 1F;
    private static final Float UPDATED_TEMPERATURE = 2F;

    private static final String DEFAULT_EASE_OF_INSTALLATION = "AAAAAAAAAA";
    private static final String UPDATED_EASE_OF_INSTALLATION = "BBBBBBBBBB";

    private static final String DEFAULT_MAINTAINABILITY = "AAAAAAAAAA";
    private static final String UPDATED_MAINTAINABILITY = "BBBBBBBBBB";

    private static final Float DEFAULT_SIZE = 1F;
    private static final Float UPDATED_SIZE = 2F;

    private static final String DEFAULT_RAM = "AAAAAAAAAA";
    private static final String UPDATED_RAM = "BBBBBBBBBB";

    private static final String DEFAULT_STORAGE = "AAAAAAAAAA";
    private static final String UPDATED_STORAGE = "BBBBBBBBBB";

    private static final Float DEFAULT_OPERATING_TEMPERATURE = 1F;
    private static final Float UPDATED_OPERATING_TEMPERATURE = 2F;

    private static final String DEFAULT_POWER = "AAAAAAAAAA";
    private static final String UPDATED_POWER = "BBBBBBBBBB";

    private static final Float DEFAULT_RELATIVE_HUMIDITY = 1F;
    private static final Float UPDATED_RELATIVE_HUMIDITY = 2F;

    private static final String DEFAULT_SYSTEM_ON_CHIP = "AAAAAAAAAA";
    private static final String UPDATED_SYSTEM_ON_CHIP = "BBBBBBBBBB";

    private static final String DEFAULT_CLOUD_PLATFORM_INTEGRATION = "AAAAAAAAAA";
    private static final String UPDATED_CLOUD_PLATFORM_INTEGRATION = "BBBBBBBBBB";

    @Autowired
    private GetWayRepository getWayRepository;

    @Autowired
    private GetWaySearchRepository getWaySearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restGetWayMockMvc;

    private GetWay getWay;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final GetWayResource getWayResource = new GetWayResource(getWayRepository, getWaySearchRepository);
        this.restGetWayMockMvc = MockMvcBuilders.standaloneSetup(getWayResource)
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
    public static GetWay createEntity(EntityManager em) {
        GetWay getWay = new GetWay()
            .name(DEFAULT_NAME)
            .technologyType(DEFAULT_TECHNOLOGY_TYPE)
            .trl(DEFAULT_TRL)
            .photo(DEFAULT_PHOTO)
            .photoContentType(DEFAULT_PHOTO_CONTENT_TYPE)
            .datasheet(DEFAULT_DATASHEET)
            .datasheetContentType(DEFAULT_DATASHEET_CONTENT_TYPE)
            .compatibility(DEFAULT_COMPATIBILITY)
            .upgradeability(DEFAULT_UPGRADEABILITY)
            .dustProof(DEFAULT_DUST_PROOF)
            .waterOrCondensationProof(DEFAULT_WATER_OR_CONDENSATION_PROOF)
            .temperature(DEFAULT_TEMPERATURE)
            .easeOfInstallation(DEFAULT_EASE_OF_INSTALLATION)
            .maintainability(DEFAULT_MAINTAINABILITY)
            .size(DEFAULT_SIZE)
            .ram(DEFAULT_RAM)
            .storage(DEFAULT_STORAGE)
            .operatingTemperature(DEFAULT_OPERATING_TEMPERATURE)
            .power(DEFAULT_POWER)
            .relativeHumidity(DEFAULT_RELATIVE_HUMIDITY)
            .systemOnChip(DEFAULT_SYSTEM_ON_CHIP)
            .cloudPlatformIntegration(DEFAULT_CLOUD_PLATFORM_INTEGRATION);
        return getWay;
    }

    @Before
    public void initTest() {
        getWaySearchRepository.deleteAll();
        getWay = createEntity(em);
    }

    @Test
    @Transactional
    public void createGetWay() throws Exception {
        int databaseSizeBeforeCreate = getWayRepository.findAll().size();

        // Create the GetWay
        restGetWayMockMvc.perform(post("/api/get-ways")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(getWay)))
            .andExpect(status().isCreated());

        // Validate the GetWay in the database
        List<GetWay> getWayList = getWayRepository.findAll();
        assertThat(getWayList).hasSize(databaseSizeBeforeCreate + 1);
        GetWay testGetWay = getWayList.get(getWayList.size() - 1);
        assertThat(testGetWay.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testGetWay.getTechnologyType()).isEqualTo(DEFAULT_TECHNOLOGY_TYPE);
        assertThat(testGetWay.getTrl()).isEqualTo(DEFAULT_TRL);
        assertThat(testGetWay.getPhoto()).isEqualTo(DEFAULT_PHOTO);
        assertThat(testGetWay.getPhotoContentType()).isEqualTo(DEFAULT_PHOTO_CONTENT_TYPE);
        assertThat(testGetWay.getDatasheet()).isEqualTo(DEFAULT_DATASHEET);
        assertThat(testGetWay.getDatasheetContentType()).isEqualTo(DEFAULT_DATASHEET_CONTENT_TYPE);
        assertThat(testGetWay.getCompatibility()).isEqualTo(DEFAULT_COMPATIBILITY);
        assertThat(testGetWay.getUpgradeability()).isEqualTo(DEFAULT_UPGRADEABILITY);
        assertThat(testGetWay.getDustProof()).isEqualTo(DEFAULT_DUST_PROOF);
        assertThat(testGetWay.getWaterOrCondensationProof()).isEqualTo(DEFAULT_WATER_OR_CONDENSATION_PROOF);
        assertThat(testGetWay.getTemperature()).isEqualTo(DEFAULT_TEMPERATURE);
        assertThat(testGetWay.getEaseOfInstallation()).isEqualTo(DEFAULT_EASE_OF_INSTALLATION);
        assertThat(testGetWay.getMaintainability()).isEqualTo(DEFAULT_MAINTAINABILITY);
        assertThat(testGetWay.getSize()).isEqualTo(DEFAULT_SIZE);
        assertThat(testGetWay.getRam()).isEqualTo(DEFAULT_RAM);
        assertThat(testGetWay.getStorage()).isEqualTo(DEFAULT_STORAGE);
        assertThat(testGetWay.getOperatingTemperature()).isEqualTo(DEFAULT_OPERATING_TEMPERATURE);
        assertThat(testGetWay.getPower()).isEqualTo(DEFAULT_POWER);
        assertThat(testGetWay.getRelativeHumidity()).isEqualTo(DEFAULT_RELATIVE_HUMIDITY);
        assertThat(testGetWay.getSystemOnChip()).isEqualTo(DEFAULT_SYSTEM_ON_CHIP);
        assertThat(testGetWay.getCloudPlatformIntegration()).isEqualTo(DEFAULT_CLOUD_PLATFORM_INTEGRATION);

        // Validate the GetWay in Elasticsearch
        GetWay getWayEs = getWaySearchRepository.findOne(testGetWay.getId());
        assertThat(getWayEs).isEqualToComparingFieldByField(testGetWay);
    }

    @Test
    @Transactional
    public void createGetWayWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = getWayRepository.findAll().size();

        // Create the GetWay with an existing ID
        getWay.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restGetWayMockMvc.perform(post("/api/get-ways")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(getWay)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<GetWay> getWayList = getWayRepository.findAll();
        assertThat(getWayList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = getWayRepository.findAll().size();
        // set the field null
        getWay.setName(null);

        // Create the GetWay, which fails.

        restGetWayMockMvc.perform(post("/api/get-ways")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(getWay)))
            .andExpect(status().isBadRequest());

        List<GetWay> getWayList = getWayRepository.findAll();
        assertThat(getWayList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTechnologyTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = getWayRepository.findAll().size();
        // set the field null
        getWay.setTechnologyType(null);

        // Create the GetWay, which fails.

        restGetWayMockMvc.perform(post("/api/get-ways")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(getWay)))
            .andExpect(status().isBadRequest());

        List<GetWay> getWayList = getWayRepository.findAll();
        assertThat(getWayList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTrlIsRequired() throws Exception {
        int databaseSizeBeforeTest = getWayRepository.findAll().size();
        // set the field null
        getWay.setTrl(null);

        // Create the GetWay, which fails.

        restGetWayMockMvc.perform(post("/api/get-ways")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(getWay)))
            .andExpect(status().isBadRequest());

        List<GetWay> getWayList = getWayRepository.findAll();
        assertThat(getWayList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCompatibilityIsRequired() throws Exception {
        int databaseSizeBeforeTest = getWayRepository.findAll().size();
        // set the field null
        getWay.setCompatibility(null);

        // Create the GetWay, which fails.

        restGetWayMockMvc.perform(post("/api/get-ways")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(getWay)))
            .andExpect(status().isBadRequest());

        List<GetWay> getWayList = getWayRepository.findAll();
        assertThat(getWayList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkUpgradeabilityIsRequired() throws Exception {
        int databaseSizeBeforeTest = getWayRepository.findAll().size();
        // set the field null
        getWay.setUpgradeability(null);

        // Create the GetWay, which fails.

        restGetWayMockMvc.perform(post("/api/get-ways")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(getWay)))
            .andExpect(status().isBadRequest());

        List<GetWay> getWayList = getWayRepository.findAll();
        assertThat(getWayList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDustProofIsRequired() throws Exception {
        int databaseSizeBeforeTest = getWayRepository.findAll().size();
        // set the field null
        getWay.setDustProof(null);

        // Create the GetWay, which fails.

        restGetWayMockMvc.perform(post("/api/get-ways")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(getWay)))
            .andExpect(status().isBadRequest());

        List<GetWay> getWayList = getWayRepository.findAll();
        assertThat(getWayList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkWaterOrCondensationProofIsRequired() throws Exception {
        int databaseSizeBeforeTest = getWayRepository.findAll().size();
        // set the field null
        getWay.setWaterOrCondensationProof(null);

        // Create the GetWay, which fails.

        restGetWayMockMvc.perform(post("/api/get-ways")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(getWay)))
            .andExpect(status().isBadRequest());

        List<GetWay> getWayList = getWayRepository.findAll();
        assertThat(getWayList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTemperatureIsRequired() throws Exception {
        int databaseSizeBeforeTest = getWayRepository.findAll().size();
        // set the field null
        getWay.setTemperature(null);

        // Create the GetWay, which fails.

        restGetWayMockMvc.perform(post("/api/get-ways")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(getWay)))
            .andExpect(status().isBadRequest());

        List<GetWay> getWayList = getWayRepository.findAll();
        assertThat(getWayList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEaseOfInstallationIsRequired() throws Exception {
        int databaseSizeBeforeTest = getWayRepository.findAll().size();
        // set the field null
        getWay.setEaseOfInstallation(null);

        // Create the GetWay, which fails.

        restGetWayMockMvc.perform(post("/api/get-ways")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(getWay)))
            .andExpect(status().isBadRequest());

        List<GetWay> getWayList = getWayRepository.findAll();
        assertThat(getWayList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMaintainabilityIsRequired() throws Exception {
        int databaseSizeBeforeTest = getWayRepository.findAll().size();
        // set the field null
        getWay.setMaintainability(null);

        // Create the GetWay, which fails.

        restGetWayMockMvc.perform(post("/api/get-ways")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(getWay)))
            .andExpect(status().isBadRequest());

        List<GetWay> getWayList = getWayRepository.findAll();
        assertThat(getWayList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllGetWays() throws Exception {
        // Initialize the database
        getWayRepository.saveAndFlush(getWay);

        // Get all the getWayList
        restGetWayMockMvc.perform(get("/api/get-ways?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(getWay.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].technologyType").value(hasItem(DEFAULT_TECHNOLOGY_TYPE.toString())))
            .andExpect(jsonPath("$.[*].trl").value(hasItem(DEFAULT_TRL)))
            .andExpect(jsonPath("$.[*].photoContentType").value(hasItem(DEFAULT_PHOTO_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].photo").value(hasItem(Base64Utils.encodeToString(DEFAULT_PHOTO))))
            .andExpect(jsonPath("$.[*].datasheetContentType").value(hasItem(DEFAULT_DATASHEET_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].datasheet").value(hasItem(Base64Utils.encodeToString(DEFAULT_DATASHEET))))
            .andExpect(jsonPath("$.[*].compatibility").value(hasItem(DEFAULT_COMPATIBILITY.toString())))
            .andExpect(jsonPath("$.[*].upgradeability").value(hasItem(DEFAULT_UPGRADEABILITY.toString())))
            .andExpect(jsonPath("$.[*].dustProof").value(hasItem(DEFAULT_DUST_PROOF.toString())))
            .andExpect(jsonPath("$.[*].waterOrCondensationProof").value(hasItem(DEFAULT_WATER_OR_CONDENSATION_PROOF.toString())))
            .andExpect(jsonPath("$.[*].temperature").value(hasItem(DEFAULT_TEMPERATURE.doubleValue())))
            .andExpect(jsonPath("$.[*].easeOfInstallation").value(hasItem(DEFAULT_EASE_OF_INSTALLATION.toString())))
            .andExpect(jsonPath("$.[*].maintainability").value(hasItem(DEFAULT_MAINTAINABILITY.toString())))
            .andExpect(jsonPath("$.[*].size").value(hasItem(DEFAULT_SIZE.doubleValue())))
            .andExpect(jsonPath("$.[*].ram").value(hasItem(DEFAULT_RAM.toString())))
            .andExpect(jsonPath("$.[*].storage").value(hasItem(DEFAULT_STORAGE.toString())))
            .andExpect(jsonPath("$.[*].operatingTemperature").value(hasItem(DEFAULT_OPERATING_TEMPERATURE.doubleValue())))
            .andExpect(jsonPath("$.[*].power").value(hasItem(DEFAULT_POWER.toString())))
            .andExpect(jsonPath("$.[*].relativeHumidity").value(hasItem(DEFAULT_RELATIVE_HUMIDITY.doubleValue())))
            .andExpect(jsonPath("$.[*].systemOnChip").value(hasItem(DEFAULT_SYSTEM_ON_CHIP.toString())))
            .andExpect(jsonPath("$.[*].cloudPlatformIntegration").value(hasItem(DEFAULT_CLOUD_PLATFORM_INTEGRATION.toString())));
    }

    @Test
    @Transactional
    public void getGetWay() throws Exception {
        // Initialize the database
        getWayRepository.saveAndFlush(getWay);

        // Get the getWay
        restGetWayMockMvc.perform(get("/api/get-ways/{id}", getWay.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(getWay.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.technologyType").value(DEFAULT_TECHNOLOGY_TYPE.toString()))
            .andExpect(jsonPath("$.trl").value(DEFAULT_TRL))
            .andExpect(jsonPath("$.photoContentType").value(DEFAULT_PHOTO_CONTENT_TYPE))
            .andExpect(jsonPath("$.photo").value(Base64Utils.encodeToString(DEFAULT_PHOTO)))
            .andExpect(jsonPath("$.datasheetContentType").value(DEFAULT_DATASHEET_CONTENT_TYPE))
            .andExpect(jsonPath("$.datasheet").value(Base64Utils.encodeToString(DEFAULT_DATASHEET)))
            .andExpect(jsonPath("$.compatibility").value(DEFAULT_COMPATIBILITY.toString()))
            .andExpect(jsonPath("$.upgradeability").value(DEFAULT_UPGRADEABILITY.toString()))
            .andExpect(jsonPath("$.dustProof").value(DEFAULT_DUST_PROOF.toString()))
            .andExpect(jsonPath("$.waterOrCondensationProof").value(DEFAULT_WATER_OR_CONDENSATION_PROOF.toString()))
            .andExpect(jsonPath("$.temperature").value(DEFAULT_TEMPERATURE.doubleValue()))
            .andExpect(jsonPath("$.easeOfInstallation").value(DEFAULT_EASE_OF_INSTALLATION.toString()))
            .andExpect(jsonPath("$.maintainability").value(DEFAULT_MAINTAINABILITY.toString()))
            .andExpect(jsonPath("$.size").value(DEFAULT_SIZE.doubleValue()))
            .andExpect(jsonPath("$.ram").value(DEFAULT_RAM.toString()))
            .andExpect(jsonPath("$.storage").value(DEFAULT_STORAGE.toString()))
            .andExpect(jsonPath("$.operatingTemperature").value(DEFAULT_OPERATING_TEMPERATURE.doubleValue()))
            .andExpect(jsonPath("$.power").value(DEFAULT_POWER.toString()))
            .andExpect(jsonPath("$.relativeHumidity").value(DEFAULT_RELATIVE_HUMIDITY.doubleValue()))
            .andExpect(jsonPath("$.systemOnChip").value(DEFAULT_SYSTEM_ON_CHIP.toString()))
            .andExpect(jsonPath("$.cloudPlatformIntegration").value(DEFAULT_CLOUD_PLATFORM_INTEGRATION.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingGetWay() throws Exception {
        // Get the getWay
        restGetWayMockMvc.perform(get("/api/get-ways/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateGetWay() throws Exception {
        // Initialize the database
        getWayRepository.saveAndFlush(getWay);
        getWaySearchRepository.save(getWay);
        int databaseSizeBeforeUpdate = getWayRepository.findAll().size();

        // Update the getWay
        GetWay updatedGetWay = getWayRepository.findOne(getWay.getId());
        updatedGetWay
            .name(UPDATED_NAME)
            .technologyType(UPDATED_TECHNOLOGY_TYPE)
            .trl(UPDATED_TRL)
            .photo(UPDATED_PHOTO)
            .photoContentType(UPDATED_PHOTO_CONTENT_TYPE)
            .datasheet(UPDATED_DATASHEET)
            .datasheetContentType(UPDATED_DATASHEET_CONTENT_TYPE)
            .compatibility(UPDATED_COMPATIBILITY)
            .upgradeability(UPDATED_UPGRADEABILITY)
            .dustProof(UPDATED_DUST_PROOF)
            .waterOrCondensationProof(UPDATED_WATER_OR_CONDENSATION_PROOF)
            .temperature(UPDATED_TEMPERATURE)
            .easeOfInstallation(UPDATED_EASE_OF_INSTALLATION)
            .maintainability(UPDATED_MAINTAINABILITY)
            .size(UPDATED_SIZE)
            .ram(UPDATED_RAM)
            .storage(UPDATED_STORAGE)
            .operatingTemperature(UPDATED_OPERATING_TEMPERATURE)
            .power(UPDATED_POWER)
            .relativeHumidity(UPDATED_RELATIVE_HUMIDITY)
            .systemOnChip(UPDATED_SYSTEM_ON_CHIP)
            .cloudPlatformIntegration(UPDATED_CLOUD_PLATFORM_INTEGRATION);

        restGetWayMockMvc.perform(put("/api/get-ways")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedGetWay)))
            .andExpect(status().isOk());

        // Validate the GetWay in the database
        List<GetWay> getWayList = getWayRepository.findAll();
        assertThat(getWayList).hasSize(databaseSizeBeforeUpdate);
        GetWay testGetWay = getWayList.get(getWayList.size() - 1);
        assertThat(testGetWay.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testGetWay.getTechnologyType()).isEqualTo(UPDATED_TECHNOLOGY_TYPE);
        assertThat(testGetWay.getTrl()).isEqualTo(UPDATED_TRL);
        assertThat(testGetWay.getPhoto()).isEqualTo(UPDATED_PHOTO);
        assertThat(testGetWay.getPhotoContentType()).isEqualTo(UPDATED_PHOTO_CONTENT_TYPE);
        assertThat(testGetWay.getDatasheet()).isEqualTo(UPDATED_DATASHEET);
        assertThat(testGetWay.getDatasheetContentType()).isEqualTo(UPDATED_DATASHEET_CONTENT_TYPE);
        assertThat(testGetWay.getCompatibility()).isEqualTo(UPDATED_COMPATIBILITY);
        assertThat(testGetWay.getUpgradeability()).isEqualTo(UPDATED_UPGRADEABILITY);
        assertThat(testGetWay.getDustProof()).isEqualTo(UPDATED_DUST_PROOF);
        assertThat(testGetWay.getWaterOrCondensationProof()).isEqualTo(UPDATED_WATER_OR_CONDENSATION_PROOF);
        assertThat(testGetWay.getTemperature()).isEqualTo(UPDATED_TEMPERATURE);
        assertThat(testGetWay.getEaseOfInstallation()).isEqualTo(UPDATED_EASE_OF_INSTALLATION);
        assertThat(testGetWay.getMaintainability()).isEqualTo(UPDATED_MAINTAINABILITY);
        assertThat(testGetWay.getSize()).isEqualTo(UPDATED_SIZE);
        assertThat(testGetWay.getRam()).isEqualTo(UPDATED_RAM);
        assertThat(testGetWay.getStorage()).isEqualTo(UPDATED_STORAGE);
        assertThat(testGetWay.getOperatingTemperature()).isEqualTo(UPDATED_OPERATING_TEMPERATURE);
        assertThat(testGetWay.getPower()).isEqualTo(UPDATED_POWER);
        assertThat(testGetWay.getRelativeHumidity()).isEqualTo(UPDATED_RELATIVE_HUMIDITY);
        assertThat(testGetWay.getSystemOnChip()).isEqualTo(UPDATED_SYSTEM_ON_CHIP);
        assertThat(testGetWay.getCloudPlatformIntegration()).isEqualTo(UPDATED_CLOUD_PLATFORM_INTEGRATION);

        // Validate the GetWay in Elasticsearch
        GetWay getWayEs = getWaySearchRepository.findOne(testGetWay.getId());
        assertThat(getWayEs).isEqualToComparingFieldByField(testGetWay);
    }

    @Test
    @Transactional
    public void updateNonExistingGetWay() throws Exception {
        int databaseSizeBeforeUpdate = getWayRepository.findAll().size();

        // Create the GetWay

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restGetWayMockMvc.perform(put("/api/get-ways")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(getWay)))
            .andExpect(status().isCreated());

        // Validate the GetWay in the database
        List<GetWay> getWayList = getWayRepository.findAll();
        assertThat(getWayList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteGetWay() throws Exception {
        // Initialize the database
        getWayRepository.saveAndFlush(getWay);
        getWaySearchRepository.save(getWay);
        int databaseSizeBeforeDelete = getWayRepository.findAll().size();

        // Get the getWay
        restGetWayMockMvc.perform(delete("/api/get-ways/{id}", getWay.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate Elasticsearch is empty
        boolean getWayExistsInEs = getWaySearchRepository.exists(getWay.getId());
        assertThat(getWayExistsInEs).isFalse();

        // Validate the database is empty
        List<GetWay> getWayList = getWayRepository.findAll();
        assertThat(getWayList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void searchGetWay() throws Exception {
        // Initialize the database
        getWayRepository.saveAndFlush(getWay);
        getWaySearchRepository.save(getWay);

        // Search the getWay
        restGetWayMockMvc.perform(get("/api/_search/get-ways?query=id:" + getWay.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(getWay.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].technologyType").value(hasItem(DEFAULT_TECHNOLOGY_TYPE.toString())))
            .andExpect(jsonPath("$.[*].trl").value(hasItem(DEFAULT_TRL)))
            .andExpect(jsonPath("$.[*].photoContentType").value(hasItem(DEFAULT_PHOTO_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].photo").value(hasItem(Base64Utils.encodeToString(DEFAULT_PHOTO))))
            .andExpect(jsonPath("$.[*].datasheetContentType").value(hasItem(DEFAULT_DATASHEET_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].datasheet").value(hasItem(Base64Utils.encodeToString(DEFAULT_DATASHEET))))
            .andExpect(jsonPath("$.[*].compatibility").value(hasItem(DEFAULT_COMPATIBILITY.toString())))
            .andExpect(jsonPath("$.[*].upgradeability").value(hasItem(DEFAULT_UPGRADEABILITY.toString())))
            .andExpect(jsonPath("$.[*].dustProof").value(hasItem(DEFAULT_DUST_PROOF.toString())))
            .andExpect(jsonPath("$.[*].waterOrCondensationProof").value(hasItem(DEFAULT_WATER_OR_CONDENSATION_PROOF.toString())))
            .andExpect(jsonPath("$.[*].temperature").value(hasItem(DEFAULT_TEMPERATURE.doubleValue())))
            .andExpect(jsonPath("$.[*].easeOfInstallation").value(hasItem(DEFAULT_EASE_OF_INSTALLATION.toString())))
            .andExpect(jsonPath("$.[*].maintainability").value(hasItem(DEFAULT_MAINTAINABILITY.toString())))
            .andExpect(jsonPath("$.[*].size").value(hasItem(DEFAULT_SIZE.doubleValue())))
            .andExpect(jsonPath("$.[*].ram").value(hasItem(DEFAULT_RAM.toString())))
            .andExpect(jsonPath("$.[*].storage").value(hasItem(DEFAULT_STORAGE.toString())))
            .andExpect(jsonPath("$.[*].operatingTemperature").value(hasItem(DEFAULT_OPERATING_TEMPERATURE.doubleValue())))
            .andExpect(jsonPath("$.[*].power").value(hasItem(DEFAULT_POWER.toString())))
            .andExpect(jsonPath("$.[*].relativeHumidity").value(hasItem(DEFAULT_RELATIVE_HUMIDITY.doubleValue())))
            .andExpect(jsonPath("$.[*].systemOnChip").value(hasItem(DEFAULT_SYSTEM_ON_CHIP.toString())))
            .andExpect(jsonPath("$.[*].cloudPlatformIntegration").value(hasItem(DEFAULT_CLOUD_PLATFORM_INTEGRATION.toString())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(GetWay.class);
        GetWay getWay1 = new GetWay();
        getWay1.setId(1L);
        GetWay getWay2 = new GetWay();
        getWay2.setId(getWay1.getId());
        assertThat(getWay1).isEqualTo(getWay2);
        getWay2.setId(2L);
        assertThat(getWay1).isNotEqualTo(getWay2);
        getWay1.setId(null);
        assertThat(getWay1).isNotEqualTo(getWay2);
    }
}
