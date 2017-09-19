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
 * A WirelessSensorNetwork.
 */
@Entity
@Table(name = "wireless_sensor_network")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "wirelesssensornetwork")
public class WirelessSensorNetwork implements Serializable {

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
    @Column(name = "power", nullable = false)
    private Float power;

    @NotNull
    @Column(name = "operating_system", nullable = false)
    private String operatingSystem;

    @NotNull
    @Column(name = "protocol", nullable = false)
    private Float protocol;

    @NotNull
    @Column(name = "jhi_cost", precision=10, scale=2, nullable = false)
    private BigDecimal cost;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "security", nullable = false)
    private Choice security;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "self_organisation_of_nodes", nullable = false)
    private Choice selfOrganisationOfNodes;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "mobility", nullable = false)
    private Choice mobility;

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

    public WirelessSensorNetwork name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTechnologyType() {
        return technologyType;
    }

    public WirelessSensorNetwork technologyType(String technologyType) {
        this.technologyType = technologyType;
        return this;
    }

    public void setTechnologyType(String technologyType) {
        this.technologyType = technologyType;
    }

    public Integer getTrl() {
        return trl;
    }

    public WirelessSensorNetwork trl(Integer trl) {
        this.trl = trl;
        return this;
    }

    public void setTrl(Integer trl) {
        this.trl = trl;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public WirelessSensorNetwork photo(byte[] photo) {
        this.photo = photo;
        return this;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public String getPhotoContentType() {
        return photoContentType;
    }

    public WirelessSensorNetwork photoContentType(String photoContentType) {
        this.photoContentType = photoContentType;
        return this;
    }

    public void setPhotoContentType(String photoContentType) {
        this.photoContentType = photoContentType;
    }

    public byte[] getDatasheet() {
        return datasheet;
    }

    public WirelessSensorNetwork datasheet(byte[] datasheet) {
        this.datasheet = datasheet;
        return this;
    }

    public void setDatasheet(byte[] datasheet) {
        this.datasheet = datasheet;
    }

    public String getDatasheetContentType() {
        return datasheetContentType;
    }

    public WirelessSensorNetwork datasheetContentType(String datasheetContentType) {
        this.datasheetContentType = datasheetContentType;
        return this;
    }

    public void setDatasheetContentType(String datasheetContentType) {
        this.datasheetContentType = datasheetContentType;
    }

    public Float getPower() {
        return power;
    }

    public WirelessSensorNetwork power(Float power) {
        this.power = power;
        return this;
    }

    public void setPower(Float power) {
        this.power = power;
    }

    public String getOperatingSystem() {
        return operatingSystem;
    }

    public WirelessSensorNetwork operatingSystem(String operatingSystem) {
        this.operatingSystem = operatingSystem;
        return this;
    }

    public void setOperatingSystem(String operatingSystem) {
        this.operatingSystem = operatingSystem;
    }

    public Float getProtocol() {
        return protocol;
    }

    public WirelessSensorNetwork protocol(Float protocol) {
        this.protocol = protocol;
        return this;
    }

    public void setProtocol(Float protocol) {
        this.protocol = protocol;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public WirelessSensorNetwork cost(BigDecimal cost) {
        this.cost = cost;
        return this;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public Choice getSecurity() {
        return security;
    }

    public WirelessSensorNetwork security(Choice security) {
        this.security = security;
        return this;
    }

    public void setSecurity(Choice security) {
        this.security = security;
    }

    public Choice getSelfOrganisationOfNodes() {
        return selfOrganisationOfNodes;
    }

    public WirelessSensorNetwork selfOrganisationOfNodes(Choice selfOrganisationOfNodes) {
        this.selfOrganisationOfNodes = selfOrganisationOfNodes;
        return this;
    }

    public void setSelfOrganisationOfNodes(Choice selfOrganisationOfNodes) {
        this.selfOrganisationOfNodes = selfOrganisationOfNodes;
    }

    public Choice getMobility() {
        return mobility;
    }

    public WirelessSensorNetwork mobility(Choice mobility) {
        this.mobility = mobility;
        return this;
    }

    public void setMobility(Choice mobility) {
        this.mobility = mobility;
    }

    public Company getOemName() {
        return oemName;
    }

    public WirelessSensorNetwork oemName(Company company) {
        this.oemName = company;
        return this;
    }

    public void setOemName(Company company) {
        this.oemName = company;
    }

    public User getAddedBy() {
        return addedBy;
    }

    public WirelessSensorNetwork addedBy(User user) {
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
        WirelessSensorNetwork wirelessSensorNetwork = (WirelessSensorNetwork) o;
        if (wirelessSensorNetwork.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), wirelessSensorNetwork.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "WirelessSensorNetwork{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", technologyType='" + getTechnologyType() + "'" +
            ", trl='" + getTrl() + "'" +
            ", photo='" + getPhoto() + "'" +
            ", photoContentType='" + photoContentType + "'" +
            ", datasheet='" + getDatasheet() + "'" +
            ", datasheetContentType='" + datasheetContentType + "'" +
            ", power='" + getPower() + "'" +
            ", operatingSystem='" + getOperatingSystem() + "'" +
            ", protocol='" + getProtocol() + "'" +
            ", cost='" + getCost() + "'" +
            ", security='" + getSecurity() + "'" +
            ", selfOrganisationOfNodes='" + getSelfOrganisationOfNodes() + "'" +
            ", mobility='" + getMobility() + "'" +
            "}";
    }
}
