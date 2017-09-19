package com.tara.org.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * A Sensor.
 */
@Entity
@Table(name = "sensor")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "sensor")
public class Sensor implements Serializable {

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
    @Column(name = "sensitivity", nullable = false)
    private Float sensitivity;

    @NotNull
    @Column(name = "stability", nullable = false)
    private Float stability;

    @NotNull
    @Column(name = "accuracy", nullable = false)
    private Float accuracy;

    @NotNull
    @Column(name = "hysteresis", nullable = false)
    private Float hysteresis;

    @NotNull
    @Column(name = "drift", nullable = false)
    private Float drift;

    @NotNull
    @Column(name = "jhi_cost", precision=10, scale=2, nullable = false)
    private BigDecimal cost;

    @NotNull
    @Column(name = "jhi_size", nullable = false)
    private Float size;

    @NotNull
    @Column(name = "weight", nullable = false)
    private Float weight;

    @NotNull
    @Column(name = "range_span", nullable = false)
    private String rangeSpan;

    @NotNull
    @Column(name = "resolution", nullable = false)
    private Float resolution;

    @NotNull
    @Column(name = "linearity", nullable = false)
    private Float linearity;

    @NotNull
    @Column(name = "response_time", nullable = false)
    private Float responseTime;

    @NotNull
    @Column(name = "jhi_precision", nullable = false)
    private Float precision;

    @NotNull
    @Column(name = "signal_to_noise_ratio", nullable = false)
    private Float signalToNoiseRatio;

    @NotNull
    @Column(name = "temperature", nullable = false)
    private Float temperature;

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

    public Sensor name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTechnologyType() {
        return technologyType;
    }

    public Sensor technologyType(String technologyType) {
        this.technologyType = technologyType;
        return this;
    }

    public void setTechnologyType(String technologyType) {
        this.technologyType = technologyType;
    }

    public Integer getTrl() {
        return trl;
    }

    public Sensor trl(Integer trl) {
        this.trl = trl;
        return this;
    }

    public void setTrl(Integer trl) {
        this.trl = trl;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public Sensor photo(byte[] photo) {
        this.photo = photo;
        return this;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public String getPhotoContentType() {
        return photoContentType;
    }

    public Sensor photoContentType(String photoContentType) {
        this.photoContentType = photoContentType;
        return this;
    }

    public void setPhotoContentType(String photoContentType) {
        this.photoContentType = photoContentType;
    }

    public byte[] getDatasheet() {
        return datasheet;
    }

    public Sensor datasheet(byte[] datasheet) {
        this.datasheet = datasheet;
        return this;
    }

    public void setDatasheet(byte[] datasheet) {
        this.datasheet = datasheet;
    }

    public String getDatasheetContentType() {
        return datasheetContentType;
    }

    public Sensor datasheetContentType(String datasheetContentType) {
        this.datasheetContentType = datasheetContentType;
        return this;
    }

    public void setDatasheetContentType(String datasheetContentType) {
        this.datasheetContentType = datasheetContentType;
    }

    public Float getSensitivity() {
        return sensitivity;
    }

    public Sensor sensitivity(Float sensitivity) {
        this.sensitivity = sensitivity;
        return this;
    }

    public void setSensitivity(Float sensitivity) {
        this.sensitivity = sensitivity;
    }

    public Float getStability() {
        return stability;
    }

    public Sensor stability(Float stability) {
        this.stability = stability;
        return this;
    }

    public void setStability(Float stability) {
        this.stability = stability;
    }

    public Float getAccuracy() {
        return accuracy;
    }

    public Sensor accuracy(Float accuracy) {
        this.accuracy = accuracy;
        return this;
    }

    public void setAccuracy(Float accuracy) {
        this.accuracy = accuracy;
    }

    public Float getHysteresis() {
        return hysteresis;
    }

    public Sensor hysteresis(Float hysteresis) {
        this.hysteresis = hysteresis;
        return this;
    }

    public void setHysteresis(Float hysteresis) {
        this.hysteresis = hysteresis;
    }

    public Float getDrift() {
        return drift;
    }

    public Sensor drift(Float drift) {
        this.drift = drift;
        return this;
    }

    public void setDrift(Float drift) {
        this.drift = drift;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public Sensor cost(BigDecimal cost) {
        this.cost = cost;
        return this;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public Float getSize() {
        return size;
    }

    public Sensor size(Float size) {
        this.size = size;
        return this;
    }

    public void setSize(Float size) {
        this.size = size;
    }

    public Float getWeight() {
        return weight;
    }

    public Sensor weight(Float weight) {
        this.weight = weight;
        return this;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public String getRangeSpan() {
        return rangeSpan;
    }

    public Sensor rangeSpan(String rangeSpan) {
        this.rangeSpan = rangeSpan;
        return this;
    }

    public void setRangeSpan(String rangeSpan) {
        this.rangeSpan = rangeSpan;
    }

    public Float getResolution() {
        return resolution;
    }

    public Sensor resolution(Float resolution) {
        this.resolution = resolution;
        return this;
    }

    public void setResolution(Float resolution) {
        this.resolution = resolution;
    }

    public Float getLinearity() {
        return linearity;
    }

    public Sensor linearity(Float linearity) {
        this.linearity = linearity;
        return this;
    }

    public void setLinearity(Float linearity) {
        this.linearity = linearity;
    }

    public Float getResponseTime() {
        return responseTime;
    }

    public Sensor responseTime(Float responseTime) {
        this.responseTime = responseTime;
        return this;
    }

    public void setResponseTime(Float responseTime) {
        this.responseTime = responseTime;
    }

    public Float getPrecision() {
        return precision;
    }

    public Sensor precision(Float precision) {
        this.precision = precision;
        return this;
    }

    public void setPrecision(Float precision) {
        this.precision = precision;
    }

    public Float getSignalToNoiseRatio() {
        return signalToNoiseRatio;
    }

    public Sensor signalToNoiseRatio(Float signalToNoiseRatio) {
        this.signalToNoiseRatio = signalToNoiseRatio;
        return this;
    }

    public void setSignalToNoiseRatio(Float signalToNoiseRatio) {
        this.signalToNoiseRatio = signalToNoiseRatio;
    }

    public Float getTemperature() {
        return temperature;
    }

    public Sensor temperature(Float temperature) {
        this.temperature = temperature;
        return this;
    }

    public void setTemperature(Float temperature) {
        this.temperature = temperature;
    }

    public Company getOemName() {
        return oemName;
    }

    public Sensor oemName(Company company) {
        this.oemName = company;
        return this;
    }

    public void setOemName(Company company) {
        this.oemName = company;
    }

    public User getAddedBy() {
        return addedBy;
    }

    public Sensor addedBy(User user) {
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
        Sensor sensor = (Sensor) o;
        if (sensor.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), sensor.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Sensor{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", technologyType='" + getTechnologyType() + "'" +
            ", trl='" + getTrl() + "'" +
            ", photo='" + getPhoto() + "'" +
            ", photoContentType='" + photoContentType + "'" +
            ", datasheet='" + getDatasheet() + "'" +
            ", datasheetContentType='" + datasheetContentType + "'" +
            ", sensitivity='" + getSensitivity() + "'" +
            ", stability='" + getStability() + "'" +
            ", accuracy='" + getAccuracy() + "'" +
            ", hysteresis='" + getHysteresis() + "'" +
            ", drift='" + getDrift() + "'" +
            ", cost='" + getCost() + "'" +
            ", size='" + getSize() + "'" +
            ", weight='" + getWeight() + "'" +
            ", rangeSpan='" + getRangeSpan() + "'" +
            ", resolution='" + getResolution() + "'" +
            ", linearity='" + getLinearity() + "'" +
            ", responseTime='" + getResponseTime() + "'" +
            ", precision='" + getPrecision() + "'" +
            ", signalToNoiseRatio='" + getSignalToNoiseRatio() + "'" +
            ", temperature='" + getTemperature() + "'" +
            "}";
    }
}
