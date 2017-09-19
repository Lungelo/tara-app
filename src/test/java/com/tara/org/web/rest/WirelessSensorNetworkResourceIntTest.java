package com.tara.org.web.rest;

import com.tara.org.TaraApp;

import com.tara.org.domain.WirelessSensorNetwork;
import com.tara.org.repository.WirelessSensorNetworkRepository;
import com.tara.org.repository.search.WirelessSensorNetworkSearchRepository;
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
/**
 * Test class for the WirelessSensorNetworkResource REST controller.
 *
 * @see WirelessSensorNetworkResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TaraApp.class)
public class WirelessSensorNetworkResourceIntTest {

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

    private static final Float DEFAULT_POWER = 1F;
    private static final Float UPDATED_POWER = 2F;

    private static final String DEFAULT_OPERATING_SYSTEM = "AAAAAAAAAA";
    private static final String UPDATED_OPERATING_SYSTEM = "BBBBBBBBBB";

    private static final Float DEFAULT_PROTOCOL = 1F;
    private static final Float UPDATED_PROTOCOL = 2F;

    private static final BigDecimal DEFAULT_COST = new BigDecimal(1);
    private static final BigDecimal UPDATED_COST = new BigDecimal(2);

    private static final Choice DEFAULT_SECURITY = Choice.YES;
    private static final Choice UPDATED_SECURITY = Choice.NO;

    private static final Choice DEFAULT_SELF_ORGANISATION_OF_NODES = Choice.YES;
    private static final Choice UPDATED_SELF_ORGANISATION_OF_NODES = Choice.NO;

    private static final Choice DEFAULT_MOBILITY = Choice.YES;
    private static final Choice UPDATED_MOBILITY = Choice.NO;

    @Autowired
    private WirelessSensorNetworkRepository wirelessSensorNetworkRepository;

    @Autowired
    private WirelessSensorNetworkSearchRepository wirelessSensorNetworkSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restWirelessSensorNetworkMockMvc;

    private WirelessSensorNetwork wirelessSensorNetwork;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final WirelessSensorNetworkResource wirelessSensorNetworkResource = new WirelessSensorNetworkResource(wirelessSensorNetworkRepository, wirelessSensorNetworkSearchRepository);
        this.restWirelessSensorNetworkMockMvc = MockMvcBuilders.standaloneSetup(wirelessSensorNetworkResource)
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
    public static WirelessSensorNetwork createEntity(EntityManager em) {
        WirelessSensorNetwork wirelessSensorNetwork = new WirelessSensorNetwork()
            .name(DEFAULT_NAME)
            .technologyType(DEFAULT_TECHNOLOGY_TYPE)
            .trl(DEFAULT_TRL)
            .photo(DEFAULT_PHOTO)
            .photoContentType(DEFAULT_PHOTO_CONTENT_TYPE)
            .datasheet(DEFAULT_DATASHEET)
            .datasheetContentType(DEFAULT_DATASHEET_CONTENT_TYPE)
            .power(DEFAULT_POWER)
            .operatingSystem(DEFAULT_OPERATING_SYSTEM)
            .protocol(DEFAULT_PROTOCOL)
            .cost(DEFAULT_COST)
            .security(DEFAULT_SECURITY)
            .selfOrganisationOfNodes(DEFAULT_SELF_ORGANISATION_OF_NODES)
            .mobility(DEFAULT_MOBILITY);
        return wirelessSensorNetwork;
    }

    @Before
    public void initTest() {
        wirelessSensorNetworkSearchRepository.deleteAll();
        wirelessSensorNetwork = createEntity(em);
    }

    @Test
    @Transactional
    public void createWirelessSensorNetwork() throws Exception {
        int databaseSizeBeforeCreate = wirelessSensorNetworkRepository.findAll().size();

        // Create the WirelessSensorNetwork
        restWirelessSensorNetworkMockMvc.perform(post("/api/wireless-sensor-networks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(wirelessSensorNetwork)))
            .andExpect(status().isCreated());

        // Validate the WirelessSensorNetwork in the database
        List<WirelessSensorNetwork> wirelessSensorNetworkList = wirelessSensorNetworkRepository.findAll();
        assertThat(wirelessSensorNetworkList).hasSize(databaseSizeBeforeCreate + 1);
        WirelessSensorNetwork testWirelessSensorNetwork = wirelessSensorNetworkList.get(wirelessSensorNetworkList.size() - 1);
        assertThat(testWirelessSensorNetwork.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testWirelessSensorNetwork.getTechnologyType()).isEqualTo(DEFAULT_TECHNOLOGY_TYPE);
        assertThat(testWirelessSensorNetwork.getTrl()).isEqualTo(DEFAULT_TRL);
        assertThat(testWirelessSensorNetwork.getPhoto()).isEqualTo(DEFAULT_PHOTO);
        assertThat(testWirelessSensorNetwork.getPhotoContentType()).isEqualTo(DEFAULT_PHOTO_CONTENT_TYPE);
        assertThat(testWirelessSensorNetwork.getDatasheet()).isEqualTo(DEFAULT_DATASHEET);
        assertThat(testWirelessSensorNetwork.getDatasheetContentType()).isEqualTo(DEFAULT_DATASHEET_CONTENT_TYPE);
        assertThat(testWirelessSensorNetwork.getPower()).isEqualTo(DEFAULT_POWER);
        assertThat(testWirelessSensorNetwork.getOperatingSystem()).isEqualTo(DEFAULT_OPERATING_SYSTEM);
        assertThat(testWirelessSensorNetwork.getProtocol()).isEqualTo(DEFAULT_PROTOCOL);
        assertThat(testWirelessSensorNetwork.getCost()).isEqualTo(DEFAULT_COST);
        assertThat(testWirelessSensorNetwork.getSecurity()).isEqualTo(DEFAULT_SECURITY);
        assertThat(testWirelessSensorNetwork.getSelfOrganisationOfNodes()).isEqualTo(DEFAULT_SELF_ORGANISATION_OF_NODES);
        assertThat(testWirelessSensorNetwork.getMobility()).isEqualTo(DEFAULT_MOBILITY);

        // Validate the WirelessSensorNetwork in Elasticsearch
        WirelessSensorNetwork wirelessSensorNetworkEs = wirelessSensorNetworkSearchRepository.findOne(testWirelessSensorNetwork.getId());
        assertThat(wirelessSensorNetworkEs).isEqualToComparingFieldByField(testWirelessSensorNetwork);
    }

    @Test
    @Transactional
    public void createWirelessSensorNetworkWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = wirelessSensorNetworkRepository.findAll().size();

        // Create the WirelessSensorNetwork with an existing ID
        wirelessSensorNetwork.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restWirelessSensorNetworkMockMvc.perform(post("/api/wireless-sensor-networks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(wirelessSensorNetwork)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<WirelessSensorNetwork> wirelessSensorNetworkList = wirelessSensorNetworkRepository.findAll();
        assertThat(wirelessSensorNetworkList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = wirelessSensorNetworkRepository.findAll().size();
        // set the field null
        wirelessSensorNetwork.setName(null);

        // Create the WirelessSensorNetwork, which fails.

        restWirelessSensorNetworkMockMvc.perform(post("/api/wireless-sensor-networks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(wirelessSensorNetwork)))
            .andExpect(status().isBadRequest());

        List<WirelessSensorNetwork> wirelessSensorNetworkList = wirelessSensorNetworkRepository.findAll();
        assertThat(wirelessSensorNetworkList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTechnologyTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = wirelessSensorNetworkRepository.findAll().size();
        // set the field null
        wirelessSensorNetwork.setTechnologyType(null);

        // Create the WirelessSensorNetwork, which fails.

        restWirelessSensorNetworkMockMvc.perform(post("/api/wireless-sensor-networks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(wirelessSensorNetwork)))
            .andExpect(status().isBadRequest());

        List<WirelessSensorNetwork> wirelessSensorNetworkList = wirelessSensorNetworkRepository.findAll();
        assertThat(wirelessSensorNetworkList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTrlIsRequired() throws Exception {
        int databaseSizeBeforeTest = wirelessSensorNetworkRepository.findAll().size();
        // set the field null
        wirelessSensorNetwork.setTrl(null);

        // Create the WirelessSensorNetwork, which fails.

        restWirelessSensorNetworkMockMvc.perform(post("/api/wireless-sensor-networks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(wirelessSensorNetwork)))
            .andExpect(status().isBadRequest());

        List<WirelessSensorNetwork> wirelessSensorNetworkList = wirelessSensorNetworkRepository.findAll();
        assertThat(wirelessSensorNetworkList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPowerIsRequired() throws Exception {
        int databaseSizeBeforeTest = wirelessSensorNetworkRepository.findAll().size();
        // set the field null
        wirelessSensorNetwork.setPower(null);

        // Create the WirelessSensorNetwork, which fails.

        restWirelessSensorNetworkMockMvc.perform(post("/api/wireless-sensor-networks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(wirelessSensorNetwork)))
            .andExpect(status().isBadRequest());

        List<WirelessSensorNetwork> wirelessSensorNetworkList = wirelessSensorNetworkRepository.findAll();
        assertThat(wirelessSensorNetworkList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkOperatingSystemIsRequired() throws Exception {
        int databaseSizeBeforeTest = wirelessSensorNetworkRepository.findAll().size();
        // set the field null
        wirelessSensorNetwork.setOperatingSystem(null);

        // Create the WirelessSensorNetwork, which fails.

        restWirelessSensorNetworkMockMvc.perform(post("/api/wireless-sensor-networks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(wirelessSensorNetwork)))
            .andExpect(status().isBadRequest());

        List<WirelessSensorNetwork> wirelessSensorNetworkList = wirelessSensorNetworkRepository.findAll();
        assertThat(wirelessSensorNetworkList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkProtocolIsRequired() throws Exception {
        int databaseSizeBeforeTest = wirelessSensorNetworkRepository.findAll().size();
        // set the field null
        wirelessSensorNetwork.setProtocol(null);

        // Create the WirelessSensorNetwork, which fails.

        restWirelessSensorNetworkMockMvc.perform(post("/api/wireless-sensor-networks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(wirelessSensorNetwork)))
            .andExpect(status().isBadRequest());

        List<WirelessSensorNetwork> wirelessSensorNetworkList = wirelessSensorNetworkRepository.findAll();
        assertThat(wirelessSensorNetworkList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCostIsRequired() throws Exception {
        int databaseSizeBeforeTest = wirelessSensorNetworkRepository.findAll().size();
        // set the field null
        wirelessSensorNetwork.setCost(null);

        // Create the WirelessSensorNetwork, which fails.

        restWirelessSensorNetworkMockMvc.perform(post("/api/wireless-sensor-networks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(wirelessSensorNetwork)))
            .andExpect(status().isBadRequest());

        List<WirelessSensorNetwork> wirelessSensorNetworkList = wirelessSensorNetworkRepository.findAll();
        assertThat(wirelessSensorNetworkList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSecurityIsRequired() throws Exception {
        int databaseSizeBeforeTest = wirelessSensorNetworkRepository.findAll().size();
        // set the field null
        wirelessSensorNetwork.setSecurity(null);

        // Create the WirelessSensorNetwork, which fails.

        restWirelessSensorNetworkMockMvc.perform(post("/api/wireless-sensor-networks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(wirelessSensorNetwork)))
            .andExpect(status().isBadRequest());

        List<WirelessSensorNetwork> wirelessSensorNetworkList = wirelessSensorNetworkRepository.findAll();
        assertThat(wirelessSensorNetworkList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSelfOrganisationOfNodesIsRequired() throws Exception {
        int databaseSizeBeforeTest = wirelessSensorNetworkRepository.findAll().size();
        // set the field null
        wirelessSensorNetwork.setSelfOrganisationOfNodes(null);

        // Create the WirelessSensorNetwork, which fails.

        restWirelessSensorNetworkMockMvc.perform(post("/api/wireless-sensor-networks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(wirelessSensorNetwork)))
            .andExpect(status().isBadRequest());

        List<WirelessSensorNetwork> wirelessSensorNetworkList = wirelessSensorNetworkRepository.findAll();
        assertThat(wirelessSensorNetworkList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkMobilityIsRequired() throws Exception {
        int databaseSizeBeforeTest = wirelessSensorNetworkRepository.findAll().size();
        // set the field null
        wirelessSensorNetwork.setMobility(null);

        // Create the WirelessSensorNetwork, which fails.

        restWirelessSensorNetworkMockMvc.perform(post("/api/wireless-sensor-networks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(wirelessSensorNetwork)))
            .andExpect(status().isBadRequest());

        List<WirelessSensorNetwork> wirelessSensorNetworkList = wirelessSensorNetworkRepository.findAll();
        assertThat(wirelessSensorNetworkList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllWirelessSensorNetworks() throws Exception {
        // Initialize the database
        wirelessSensorNetworkRepository.saveAndFlush(wirelessSensorNetwork);

        // Get all the wirelessSensorNetworkList
        restWirelessSensorNetworkMockMvc.perform(get("/api/wireless-sensor-networks?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(wirelessSensorNetwork.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].technologyType").value(hasItem(DEFAULT_TECHNOLOGY_TYPE.toString())))
            .andExpect(jsonPath("$.[*].trl").value(hasItem(DEFAULT_TRL)))
            .andExpect(jsonPath("$.[*].photoContentType").value(hasItem(DEFAULT_PHOTO_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].photo").value(hasItem(Base64Utils.encodeToString(DEFAULT_PHOTO))))
            .andExpect(jsonPath("$.[*].datasheetContentType").value(hasItem(DEFAULT_DATASHEET_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].datasheet").value(hasItem(Base64Utils.encodeToString(DEFAULT_DATASHEET))))
            .andExpect(jsonPath("$.[*].power").value(hasItem(DEFAULT_POWER.doubleValue())))
            .andExpect(jsonPath("$.[*].operatingSystem").value(hasItem(DEFAULT_OPERATING_SYSTEM.toString())))
            .andExpect(jsonPath("$.[*].protocol").value(hasItem(DEFAULT_PROTOCOL.doubleValue())))
            .andExpect(jsonPath("$.[*].cost").value(hasItem(DEFAULT_COST.intValue())))
            .andExpect(jsonPath("$.[*].security").value(hasItem(DEFAULT_SECURITY.toString())))
            .andExpect(jsonPath("$.[*].selfOrganisationOfNodes").value(hasItem(DEFAULT_SELF_ORGANISATION_OF_NODES.toString())))
            .andExpect(jsonPath("$.[*].mobility").value(hasItem(DEFAULT_MOBILITY.toString())));
    }

    @Test
    @Transactional
    public void getWirelessSensorNetwork() throws Exception {
        // Initialize the database
        wirelessSensorNetworkRepository.saveAndFlush(wirelessSensorNetwork);

        // Get the wirelessSensorNetwork
        restWirelessSensorNetworkMockMvc.perform(get("/api/wireless-sensor-networks/{id}", wirelessSensorNetwork.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(wirelessSensorNetwork.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.technologyType").value(DEFAULT_TECHNOLOGY_TYPE.toString()))
            .andExpect(jsonPath("$.trl").value(DEFAULT_TRL))
            .andExpect(jsonPath("$.photoContentType").value(DEFAULT_PHOTO_CONTENT_TYPE))
            .andExpect(jsonPath("$.photo").value(Base64Utils.encodeToString(DEFAULT_PHOTO)))
            .andExpect(jsonPath("$.datasheetContentType").value(DEFAULT_DATASHEET_CONTENT_TYPE))
            .andExpect(jsonPath("$.datasheet").value(Base64Utils.encodeToString(DEFAULT_DATASHEET)))
            .andExpect(jsonPath("$.power").value(DEFAULT_POWER.doubleValue()))
            .andExpect(jsonPath("$.operatingSystem").value(DEFAULT_OPERATING_SYSTEM.toString()))
            .andExpect(jsonPath("$.protocol").value(DEFAULT_PROTOCOL.doubleValue()))
            .andExpect(jsonPath("$.cost").value(DEFAULT_COST.intValue()))
            .andExpect(jsonPath("$.security").value(DEFAULT_SECURITY.toString()))
            .andExpect(jsonPath("$.selfOrganisationOfNodes").value(DEFAULT_SELF_ORGANISATION_OF_NODES.toString()))
            .andExpect(jsonPath("$.mobility").value(DEFAULT_MOBILITY.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingWirelessSensorNetwork() throws Exception {
        // Get the wirelessSensorNetwork
        restWirelessSensorNetworkMockMvc.perform(get("/api/wireless-sensor-networks/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateWirelessSensorNetwork() throws Exception {
        // Initialize the database
        wirelessSensorNetworkRepository.saveAndFlush(wirelessSensorNetwork);
        wirelessSensorNetworkSearchRepository.save(wirelessSensorNetwork);
        int databaseSizeBeforeUpdate = wirelessSensorNetworkRepository.findAll().size();

        // Update the wirelessSensorNetwork
        WirelessSensorNetwork updatedWirelessSensorNetwork = wirelessSensorNetworkRepository.findOne(wirelessSensorNetwork.getId());
        updatedWirelessSensorNetwork
            .name(UPDATED_NAME)
            .technologyType(UPDATED_TECHNOLOGY_TYPE)
            .trl(UPDATED_TRL)
            .photo(UPDATED_PHOTO)
            .photoContentType(UPDATED_PHOTO_CONTENT_TYPE)
            .datasheet(UPDATED_DATASHEET)
            .datasheetContentType(UPDATED_DATASHEET_CONTENT_TYPE)
            .power(UPDATED_POWER)
            .operatingSystem(UPDATED_OPERATING_SYSTEM)
            .protocol(UPDATED_PROTOCOL)
            .cost(UPDATED_COST)
            .security(UPDATED_SECURITY)
            .selfOrganisationOfNodes(UPDATED_SELF_ORGANISATION_OF_NODES)
            .mobility(UPDATED_MOBILITY);

        restWirelessSensorNetworkMockMvc.perform(put("/api/wireless-sensor-networks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedWirelessSensorNetwork)))
            .andExpect(status().isOk());

        // Validate the WirelessSensorNetwork in the database
        List<WirelessSensorNetwork> wirelessSensorNetworkList = wirelessSensorNetworkRepository.findAll();
        assertThat(wirelessSensorNetworkList).hasSize(databaseSizeBeforeUpdate);
        WirelessSensorNetwork testWirelessSensorNetwork = wirelessSensorNetworkList.get(wirelessSensorNetworkList.size() - 1);
        assertThat(testWirelessSensorNetwork.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testWirelessSensorNetwork.getTechnologyType()).isEqualTo(UPDATED_TECHNOLOGY_TYPE);
        assertThat(testWirelessSensorNetwork.getTrl()).isEqualTo(UPDATED_TRL);
        assertThat(testWirelessSensorNetwork.getPhoto()).isEqualTo(UPDATED_PHOTO);
        assertThat(testWirelessSensorNetwork.getPhotoContentType()).isEqualTo(UPDATED_PHOTO_CONTENT_TYPE);
        assertThat(testWirelessSensorNetwork.getDatasheet()).isEqualTo(UPDATED_DATASHEET);
        assertThat(testWirelessSensorNetwork.getDatasheetContentType()).isEqualTo(UPDATED_DATASHEET_CONTENT_TYPE);
        assertThat(testWirelessSensorNetwork.getPower()).isEqualTo(UPDATED_POWER);
        assertThat(testWirelessSensorNetwork.getOperatingSystem()).isEqualTo(UPDATED_OPERATING_SYSTEM);
        assertThat(testWirelessSensorNetwork.getProtocol()).isEqualTo(UPDATED_PROTOCOL);
        assertThat(testWirelessSensorNetwork.getCost()).isEqualTo(UPDATED_COST);
        assertThat(testWirelessSensorNetwork.getSecurity()).isEqualTo(UPDATED_SECURITY);
        assertThat(testWirelessSensorNetwork.getSelfOrganisationOfNodes()).isEqualTo(UPDATED_SELF_ORGANISATION_OF_NODES);
        assertThat(testWirelessSensorNetwork.getMobility()).isEqualTo(UPDATED_MOBILITY);

        // Validate the WirelessSensorNetwork in Elasticsearch
        WirelessSensorNetwork wirelessSensorNetworkEs = wirelessSensorNetworkSearchRepository.findOne(testWirelessSensorNetwork.getId());
        assertThat(wirelessSensorNetworkEs).isEqualToComparingFieldByField(testWirelessSensorNetwork);
    }

    @Test
    @Transactional
    public void updateNonExistingWirelessSensorNetwork() throws Exception {
        int databaseSizeBeforeUpdate = wirelessSensorNetworkRepository.findAll().size();

        // Create the WirelessSensorNetwork

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restWirelessSensorNetworkMockMvc.perform(put("/api/wireless-sensor-networks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(wirelessSensorNetwork)))
            .andExpect(status().isCreated());

        // Validate the WirelessSensorNetwork in the database
        List<WirelessSensorNetwork> wirelessSensorNetworkList = wirelessSensorNetworkRepository.findAll();
        assertThat(wirelessSensorNetworkList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteWirelessSensorNetwork() throws Exception {
        // Initialize the database
        wirelessSensorNetworkRepository.saveAndFlush(wirelessSensorNetwork);
        wirelessSensorNetworkSearchRepository.save(wirelessSensorNetwork);
        int databaseSizeBeforeDelete = wirelessSensorNetworkRepository.findAll().size();

        // Get the wirelessSensorNetwork
        restWirelessSensorNetworkMockMvc.perform(delete("/api/wireless-sensor-networks/{id}", wirelessSensorNetwork.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate Elasticsearch is empty
        boolean wirelessSensorNetworkExistsInEs = wirelessSensorNetworkSearchRepository.exists(wirelessSensorNetwork.getId());
        assertThat(wirelessSensorNetworkExistsInEs).isFalse();

        // Validate the database is empty
        List<WirelessSensorNetwork> wirelessSensorNetworkList = wirelessSensorNetworkRepository.findAll();
        assertThat(wirelessSensorNetworkList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void searchWirelessSensorNetwork() throws Exception {
        // Initialize the database
        wirelessSensorNetworkRepository.saveAndFlush(wirelessSensorNetwork);
        wirelessSensorNetworkSearchRepository.save(wirelessSensorNetwork);

        // Search the wirelessSensorNetwork
        restWirelessSensorNetworkMockMvc.perform(get("/api/_search/wireless-sensor-networks?query=id:" + wirelessSensorNetwork.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(wirelessSensorNetwork.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].technologyType").value(hasItem(DEFAULT_TECHNOLOGY_TYPE.toString())))
            .andExpect(jsonPath("$.[*].trl").value(hasItem(DEFAULT_TRL)))
            .andExpect(jsonPath("$.[*].photoContentType").value(hasItem(DEFAULT_PHOTO_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].photo").value(hasItem(Base64Utils.encodeToString(DEFAULT_PHOTO))))
            .andExpect(jsonPath("$.[*].datasheetContentType").value(hasItem(DEFAULT_DATASHEET_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].datasheet").value(hasItem(Base64Utils.encodeToString(DEFAULT_DATASHEET))))
            .andExpect(jsonPath("$.[*].power").value(hasItem(DEFAULT_POWER.doubleValue())))
            .andExpect(jsonPath("$.[*].operatingSystem").value(hasItem(DEFAULT_OPERATING_SYSTEM.toString())))
            .andExpect(jsonPath("$.[*].protocol").value(hasItem(DEFAULT_PROTOCOL.doubleValue())))
            .andExpect(jsonPath("$.[*].cost").value(hasItem(DEFAULT_COST.intValue())))
            .andExpect(jsonPath("$.[*].security").value(hasItem(DEFAULT_SECURITY.toString())))
            .andExpect(jsonPath("$.[*].selfOrganisationOfNodes").value(hasItem(DEFAULT_SELF_ORGANISATION_OF_NODES.toString())))
            .andExpect(jsonPath("$.[*].mobility").value(hasItem(DEFAULT_MOBILITY.toString())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(WirelessSensorNetwork.class);
        WirelessSensorNetwork wirelessSensorNetwork1 = new WirelessSensorNetwork();
        wirelessSensorNetwork1.setId(1L);
        WirelessSensorNetwork wirelessSensorNetwork2 = new WirelessSensorNetwork();
        wirelessSensorNetwork2.setId(wirelessSensorNetwork1.getId());
        assertThat(wirelessSensorNetwork1).isEqualTo(wirelessSensorNetwork2);
        wirelessSensorNetwork2.setId(2L);
        assertThat(wirelessSensorNetwork1).isNotEqualTo(wirelessSensorNetwork2);
        wirelessSensorNetwork1.setId(null);
        assertThat(wirelessSensorNetwork1).isNotEqualTo(wirelessSensorNetwork2);
    }
}
