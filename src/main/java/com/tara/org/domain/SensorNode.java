package com.tara.org.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

import com.tara.org.domain.enumeration.Choice;

/**
 * A SensorNode.
 */
@Entity
@Table(name = "sensor_node")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "sensornode")
public class SensorNode implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 3)
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Size(min = 3)
    @Column(name = "technology_type", nullable = false)
    private String technologyType;

    @NotNull
    @Min(value = 1)
    @Max(value = 9)
    @Column(name = "trl", nullable = false)
    private Integer trl;

    @Lob
    @Column(name = "photo")
    private byte[] photo;

    @Column(name = "photo_content_type")
    private String photoContentType;

    @Lob
    @Column(name = "datasheet")
    private byte[] datasheet;

    @Column(name = "datasheet_content_type")
    private String datasheetContentType;

    @NotNull
    @Column(name = "jhi_size", nullable = false)
    private Float size;

    @NotNull
    @Column(name = "jhi_cost", precision=10, scale=2, nullable = false)
    private BigDecimal cost;

    @NotNull
    @Column(name = "bandwidth", nullable = false)
    private Float bandwidth;

    @NotNull
    @Column(name = "data_rate", nullable = false)
    private Float dataRate;

    @NotNull
    @Column(name = "flash_memory", nullable = false)
    private Float flashMemory;

    @NotNull
    @Column(name = "ram", nullable = false)
    private String ram;

    @NotNull
    @Column(name = "energy_usage", nullable = false)
    private Float energyUsage;

    @NotNull
    @Column(name = "sleep_energy", nullable = false)
    private Float sleepEnergy;

    @NotNull
    @Column(name = "duty_cycle", nullable = false)
    private String dutyCycle;

    @NotNull
    @Column(name = "frequency", nullable = false)
    private Float frequency;

    @NotNull
    @Column(name = "jhi_range", nullable = false)
    private String range;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "mobility", nullable = false)
    private Choice mobility;

    @NotNull
    @Column(name = "resilience", nullable = false)
    private Float resilience;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "self_testing", nullable = false)
    private Choice selfTesting;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "self_calibration", nullable = false)
    private Choice selfCalibration;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "self_repair", nullable = false)
    private Choice selfRepair;

    @Column(name = "transmission_power")
    private Float transmissionPower;

    @ManyToOne
    private Company oemName;

    @ManyToOne
    private User addedBy;

    // jhipster-needle-entity-add-field - Jhipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public SensorNode name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTechnologyType() {
        return technologyType;
    }

    public SensorNode technologyType(String technologyType) {
        this.technologyType = technologyType;
        return this;
    }

    public void setTechnologyType(String technologyType) {
        this.technologyType = technologyType;
    }

    public Integer getTrl() {
        return trl;
    }

    public SensorNode trl(Integer trl) {
        this.trl = trl;
        return this;
    }

    public void setTrl(Integer trl) {
        this.trl = trl;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public SensorNode photo(byte[] photo) {
        this.photo = photo;
        return this;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public String getPhotoContentType() {
        return photoContentType;
    }

    public SensorNode photoContentType(String photoContentType) {
        this.photoContentType = photoContentType;
        return this;
    }

    public void setPhotoContentType(String photoContentType) {
        this.photoContentType = photoContentType;
    }

    public byte[] getDatasheet() {
        return datasheet;
    }

    public SensorNode datasheet(byte[] datasheet) {
        this.datasheet = datasheet;
        return this;
    }

    public void setDatasheet(byte[] datasheet) {
        this.datasheet = datasheet;
    }

    public String getDatasheetContentType() {
        return datasheetContentType;
    }

    public SensorNode datasheetContentType(String datasheetContentType) {
        this.datasheetContentType = datasheetContentType;
        return this;
    }

    public void setDatasheetContentType(String datasheetContentType) {
        this.datasheetContentType = datasheetContentType;
    }

    public Float getSize() {
        return size;
    }

    public SensorNode size(Float size) {
        this.size = size;
        return this;
    }

    public void setSize(Float size) {
        this.size = size;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public SensorNode cost(BigDecimal cost) {
        this.cost = cost;
        return this;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public Float getBandwidth() {
        return bandwidth;
    }

    public SensorNode bandwidth(Float bandwidth) {
        this.bandwidth = bandwidth;
        return this;
    }

    public void setBandwidth(Float bandwidth) {
        this.bandwidth = bandwidth;
    }

    public Float getDataRate() {
        return dataRate;
    }

    public SensorNode dataRate(Float dataRate) {
        this.dataRate = dataRate;
        return this;
    }

    public void setDataRate(Float dataRate) {
        this.dataRate = dataRate;
    }

    public Float getFlashMemory() {
        return flashMemory;
    }

    public SensorNode flashMemory(Float flashMemory) {
        this.flashMemory = flashMemory;
        return this;
    }

    public void setFlashMemory(Float flashMemory) {
        this.flashMemory = flashMemory;
    }

    public String getRam() {
        return ram;
    }

    public SensorNode ram(String ram) {
        this.ram = ram;
        return this;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public Float getEnergyUsage() {
        return energyUsage;
    }

    public SensorNode energyUsage(Float energyUsage) {
        this.energyUsage = energyUsage;
        return this;
    }

    public void setEnergyUsage(Float energyUsage) {
        this.energyUsage = energyUsage;
    }

    public Float getSleepEnergy() {
        return sleepEnergy;
    }

    public SensorNode sleepEnergy(Float sleepEnergy) {
        this.sleepEnergy = sleepEnergy;
        return this;
    }

    public void setSleepEnergy(Float sleepEnergy) {
        this.sleepEnergy = sleepEnergy;
    }

    public String getDutyCycle() {
        return dutyCycle;
    }

    public SensorNode dutyCycle(String dutyCycle) {
        this.dutyCycle = dutyCycle;
        return this;
    }

    public void setDutyCycle(String dutyCycle) {
        this.dutyCycle = dutyCycle;
    }

    public Float getFrequency() {
        return frequency;
    }

    public SensorNode frequency(Float frequency) {
        this.frequency = frequency;
        return this;
    }

    public void setFrequency(Float frequency) {
        this.frequency = frequency;
    }

    public String getRange() {
        return range;
    }

    public SensorNode range(String range) {
        this.range = range;
        return this;
    }

    public void setRange(String range) {
        this.range = range;
    }

    public Choice getMobility() {
        return mobility;
    }

    public SensorNode mobility(Choice mobility) {
        this.mobility = mobility;
        return this;
    }

    public void setMobility(Choice mobility) {
        this.mobility = mobility;
    }

    public Float getResilience() {
        return resilience;
    }

    public SensorNode resilience(Float resilience) {
        this.resilience = resilience;
        return this;
    }

    public void setResilience(Float resilience) {
        this.resilience = resilience;
    }

    public Choice getSelfTesting() {
        return selfTesting;
    }

    public SensorNode selfTesting(Choice selfTesting) {
        this.selfTesting = selfTesting;
        return this;
    }

    public void setSelfTesting(Choice selfTesting) {
        this.selfTesting = selfTesting;
    }

    public Choice getSelfCalibration() {
        return selfCalibration;
    }

    public SensorNode selfCalibration(Choice selfCalibration) {
        this.selfCalibration = selfCalibration;
        return this;
    }

    public void setSelfCalibration(Choice selfCalibration) {
        this.selfCalibration = selfCalibration;
    }

    public Choice getSelfRepair() {
        return selfRepair;
    }

    public SensorNode selfRepair(Choice selfRepair) {
        this.selfRepair = selfRepair;
        return this;
    }

    public void setSelfRepair(Choice selfRepair) {
        this.selfRepair = selfRepair;
    }

    public Float getTransmissionPower() {
        return transmissionPower;
    }

    public SensorNode transmissionPower(Float transmissionPower) {
        this.transmissionPower = transmissionPower;
        return this;
    }

    public void setTransmissionPower(Float transmissionPower) {
        this.transmissionPower = transmissionPower;
    }

    public Company getOemName() {
        return oemName;
    }

    public SensorNode oemName(Company company) {
        this.oemName = company;
        return this;
    }

    public void setOemName(Company company) {
        this.oemName = company;
    }

    public User getAddedBy() {
        return addedBy;
    }

    public SensorNode addedBy(User user) {
        this.addedBy = user;
        return this;
    }

    public void setAddedBy(User user) {
        this.addedBy = user;
    }
    // jhipster-needle-entity-add-getters-setters - Jhipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SensorNode sensorNode = (SensorNode) o;
        if (sensorNode.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), sensorNode.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SensorNode{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", technologyType='" + getTechnologyType() + "'" +
            ", trl='" + getTrl() + "'" +
            ", photo='" + getPhoto() + "'" +
            ", photoContentType='" + photoContentType + "'" +
            ", datasheet='" + getDatasheet() + "'" +
            ", datasheetContentType='" + datasheetContentType + "'" +
            ", size='" + getSize() + "'" +
            ", cost='" + getCost() + "'" +
            ", bandwidth='" + getBandwidth() + "'" +
            ", dataRate='" + getDataRate() + "'" +
            ", flashMemory='" + getFlashMemory() + "'" +
            ", ram='" + getRam() + "'" +
            ", energyUsage='" + getEnergyUsage() + "'" +
            ", sleepEnergy='" + getSleepEnergy() + "'" +
            ", dutyCycle='" + getDutyCycle() + "'" +
            ", frequency='" + getFrequency() + "'" +
            ", range='" + getRange() + "'" +
            ", mobility='" + getMobility() + "'" +
            ", resilience='" + getResilience() + "'" +
            ", selfTesting='" + getSelfTesting() + "'" +
            ", selfCalibration='" + getSelfCalibration() + "'" +
            ", selfRepair='" + getSelfRepair() + "'" +
            ", transmissionPower='" + getTransmissionPower() + "'" +
            "}";
    }
}
