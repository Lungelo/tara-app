package com.tara.org.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

import com.tara.org.domain.enumeration.MarketMaturity;

/**
 * A PositioningSystem.
 */
@Entity
@Table(name = "positioning_system")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "positioningsystem")
public class PositioningSystem implements Serializable {

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
    @Column(name = "accuracy", nullable = false)
    private Float accuracy;

    @NotNull
    @Column(name = "coverage_area", nullable = false)
    private String coverageArea;

    @NotNull
    @Column(name = "jhi_cost", precision=10, scale=2, nullable = false)
    private BigDecimal cost;

    @Column(name = "required_infrastructure")
    private String requiredInfrastructure;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "market_maturity", nullable = false)
    private MarketMaturity marketMaturity;

    @NotNull
    @Column(name = "output_data", nullable = false)
    private String outputData;

    @NotNull
    @Column(name = "privacy", nullable = false)
    private String privacy;

    @Column(name = "update_rate")
    private Float updateRate;

    @Column(name = "system_latency")
    private String systemLatency;

    @Column(name = "interface")
    private String _interface;

    @Column(name = "system_integrity")
    private String systemIntegrity;

    @Column(name = "robustness")
    private String robustness;

    @NotNull
    @DecimalMin(value = "1")
    @DecimalMax(value = "100")
    @Column(name = "availability", nullable = false)
    private Float availability;

    @Column(name = "continuity")
    private Float continuity;

    @Column(name = "scalability")
    private String scalability;

    @Column(name = "number_of_users")
    private String numberOfUsers;

    @Column(name = "approval")
    private String approval;

    @Column(name = "level_of_hybridisation")
    private String levelOfHybridisation;

    @Column(name = "technology")
    private String technology;

    @Column(name = "measured_quantity")
    private String measuredQuantity;

    @Column(name = "basic_measuring_principle")
    private String basicMeasuringPrinciple;

    @Column(name = "position_algorithm")
    private String positionAlgorithm;

    @Column(name = "signaltype")
    private String signaltype;

    @Column(name = "signal_wavelength")
    private String signalWavelength;

    @Column(name = "system_architecture")
    private String systemArchitecture;

    @Column(name = "application")
    private String application;

    @Column(name = "coordinate_reference")
    private String coordinateReference;

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

    public PositioningSystem name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTechnologyType() {
        return technologyType;
    }

    public PositioningSystem technologyType(String technologyType) {
        this.technologyType = technologyType;
        return this;
    }

    public void setTechnologyType(String technologyType) {
        this.technologyType = technologyType;
    }

    public Integer getTrl() {
        return trl;
    }

    public PositioningSystem trl(Integer trl) {
        this.trl = trl;
        return this;
    }

    public void setTrl(Integer trl) {
        this.trl = trl;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public PositioningSystem photo(byte[] photo) {
        this.photo = photo;
        return this;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public String getPhotoContentType() {
        return photoContentType;
    }

    public PositioningSystem photoContentType(String photoContentType) {
        this.photoContentType = photoContentType;
        return this;
    }

    public void setPhotoContentType(String photoContentType) {
        this.photoContentType = photoContentType;
    }

    public byte[] getDatasheet() {
        return datasheet;
    }

    public PositioningSystem datasheet(byte[] datasheet) {
        this.datasheet = datasheet;
        return this;
    }

    public void setDatasheet(byte[] datasheet) {
        this.datasheet = datasheet;
    }

    public String getDatasheetContentType() {
        return datasheetContentType;
    }

    public PositioningSystem datasheetContentType(String datasheetContentType) {
        this.datasheetContentType = datasheetContentType;
        return this;
    }

    public void setDatasheetContentType(String datasheetContentType) {
        this.datasheetContentType = datasheetContentType;
    }

    public Float getAccuracy() {
        return accuracy;
    }

    public PositioningSystem accuracy(Float accuracy) {
        this.accuracy = accuracy;
        return this;
    }

    public void setAccuracy(Float accuracy) {
        this.accuracy = accuracy;
    }

    public String getCoverageArea() {
        return coverageArea;
    }

    public PositioningSystem coverageArea(String coverageArea) {
        this.coverageArea = coverageArea;
        return this;
    }

    public void setCoverageArea(String coverageArea) {
        this.coverageArea = coverageArea;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public PositioningSystem cost(BigDecimal cost) {
        this.cost = cost;
        return this;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public String getRequiredInfrastructure() {
        return requiredInfrastructure;
    }

    public PositioningSystem requiredInfrastructure(String requiredInfrastructure) {
        this.requiredInfrastructure = requiredInfrastructure;
        return this;
    }

    public void setRequiredInfrastructure(String requiredInfrastructure) {
        this.requiredInfrastructure = requiredInfrastructure;
    }

    public MarketMaturity getMarketMaturity() {
        return marketMaturity;
    }

    public PositioningSystem marketMaturity(MarketMaturity marketMaturity) {
        this.marketMaturity = marketMaturity;
        return this;
    }

    public void setMarketMaturity(MarketMaturity marketMaturity) {
        this.marketMaturity = marketMaturity;
    }

    public String getOutputData() {
        return outputData;
    }

    public PositioningSystem outputData(String outputData) {
        this.outputData = outputData;
        return this;
    }

    public void setOutputData(String outputData) {
        this.outputData = outputData;
    }

    public String getPrivacy() {
        return privacy;
    }

    public PositioningSystem privacy(String privacy) {
        this.privacy = privacy;
        return this;
    }

    public void setPrivacy(String privacy) {
        this.privacy = privacy;
    }

    public Float getUpdateRate() {
        return updateRate;
    }

    public PositioningSystem updateRate(Float updateRate) {
        this.updateRate = updateRate;
        return this;
    }

    public void setUpdateRate(Float updateRate) {
        this.updateRate = updateRate;
    }

    public String getSystemLatency() {
        return systemLatency;
    }

    public PositioningSystem systemLatency(String systemLatency) {
        this.systemLatency = systemLatency;
        return this;
    }

    public void setSystemLatency(String systemLatency) {
        this.systemLatency = systemLatency;
    }

    public String get_interface() {
        return _interface;
    }

    public PositioningSystem _interface(String _interface) {
        this._interface = _interface;
        return this;
    }

    public void set_interface(String _interface) {
        this._interface = _interface;
    }

    public String getSystemIntegrity() {
        return systemIntegrity;
    }

    public PositioningSystem systemIntegrity(String systemIntegrity) {
        this.systemIntegrity = systemIntegrity;
        return this;
    }

    public void setSystemIntegrity(String systemIntegrity) {
        this.systemIntegrity = systemIntegrity;
    }

    public String getRobustness() {
        return robustness;
    }

    public PositioningSystem robustness(String robustness) {
        this.robustness = robustness;
        return this;
    }

    public void setRobustness(String robustness) {
        this.robustness = robustness;
    }

    public Float getAvailability() {
        return availability;
    }

    public PositioningSystem availability(Float availability) {
        this.availability = availability;
        return this;
    }

    public void setAvailability(Float availability) {
        this.availability = availability;
    }

    public Float getContinuity() {
        return continuity;
    }

    public PositioningSystem continuity(Float continuity) {
        this.continuity = continuity;
        return this;
    }

    public void setContinuity(Float continuity) {
        this.continuity = continuity;
    }

    public String getScalability() {
        return scalability;
    }

    public PositioningSystem scalability(String scalability) {
        this.scalability = scalability;
        return this;
    }

    public void setScalability(String scalability) {
        this.scalability = scalability;
    }

    public String getNumberOfUsers() {
        return numberOfUsers;
    }

    public PositioningSystem numberOfUsers(String numberOfUsers) {
        this.numberOfUsers = numberOfUsers;
        return this;
    }

    public void setNumberOfUsers(String numberOfUsers) {
        this.numberOfUsers = numberOfUsers;
    }

    public String getApproval() {
        return approval;
    }

    public PositioningSystem approval(String approval) {
        this.approval = approval;
        return this;
    }

    public void setApproval(String approval) {
        this.approval = approval;
    }

    public String getLevelOfHybridisation() {
        return levelOfHybridisation;
    }

    public PositioningSystem levelOfHybridisation(String levelOfHybridisation) {
        this.levelOfHybridisation = levelOfHybridisation;
        return this;
    }

    public void setLevelOfHybridisation(String levelOfHybridisation) {
        this.levelOfHybridisation = levelOfHybridisation;
    }

    public String getTechnology() {
        return technology;
    }

    public PositioningSystem technology(String technology) {
        this.technology = technology;
        return this;
    }

    public void setTechnology(String technology) {
        this.technology = technology;
    }

    public String getMeasuredQuantity() {
        return measuredQuantity;
    }

    public PositioningSystem measuredQuantity(String measuredQuantity) {
        this.measuredQuantity = measuredQuantity;
        return this;
    }

    public void setMeasuredQuantity(String measuredQuantity) {
        this.measuredQuantity = measuredQuantity;
    }

    public String getBasicMeasuringPrinciple() {
        return basicMeasuringPrinciple;
    }

    public PositioningSystem basicMeasuringPrinciple(String basicMeasuringPrinciple) {
        this.basicMeasuringPrinciple = basicMeasuringPrinciple;
        return this;
    }

    public void setBasicMeasuringPrinciple(String basicMeasuringPrinciple) {
        this.basicMeasuringPrinciple = basicMeasuringPrinciple;
    }

    public String getPositionAlgorithm() {
        return positionAlgorithm;
    }

    public PositioningSystem positionAlgorithm(String positionAlgorithm) {
        this.positionAlgorithm = positionAlgorithm;
        return this;
    }

    public void setPositionAlgorithm(String positionAlgorithm) {
        this.positionAlgorithm = positionAlgorithm;
    }

    public String getSignaltype() {
        return signaltype;
    }

    public PositioningSystem signaltype(String signaltype) {
        this.signaltype = signaltype;
        return this;
    }

    public void setSignaltype(String signaltype) {
        this.signaltype = signaltype;
    }

    public String getSignalWavelength() {
        return signalWavelength;
    }

    public PositioningSystem signalWavelength(String signalWavelength) {
        this.signalWavelength = signalWavelength;
        return this;
    }

    public void setSignalWavelength(String signalWavelength) {
        this.signalWavelength = signalWavelength;
    }

    public String getSystemArchitecture() {
        return systemArchitecture;
    }

    public PositioningSystem systemArchitecture(String systemArchitecture) {
        this.systemArchitecture = systemArchitecture;
        return this;
    }

    public void setSystemArchitecture(String systemArchitecture) {
        this.systemArchitecture = systemArchitecture;
    }

    public String getApplication() {
        return application;
    }

    public PositioningSystem application(String application) {
        this.application = application;
        return this;
    }

    public void setApplication(String application) {
        this.application = application;
    }

    public String getCoordinateReference() {
        return coordinateReference;
    }

    public PositioningSystem coordinateReference(String coordinateReference) {
        this.coordinateReference = coordinateReference;
        return this;
    }

    public void setCoordinateReference(String coordinateReference) {
        this.coordinateReference = coordinateReference;
    }

    public Company getOemName() {
        return oemName;
    }

    public PositioningSystem oemName(Company company) {
        this.oemName = company;
        return this;
    }

    public void setOemName(Company company) {
        this.oemName = company;
    }

    public User getAddedBy() {
        return addedBy;
    }

    public PositioningSystem addedBy(User user) {
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
        PositioningSystem positioningSystem = (PositioningSystem) o;
        if (positioningSystem.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), positioningSystem.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PositioningSystem{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", technologyType='" + getTechnologyType() + "'" +
            ", trl='" + getTrl() + "'" +
            ", photo='" + getPhoto() + "'" +
            ", photoContentType='" + photoContentType + "'" +
            ", datasheet='" + getDatasheet() + "'" +
            ", datasheetContentType='" + datasheetContentType + "'" +
            ", accuracy='" + getAccuracy() + "'" +
            ", coverageArea='" + getCoverageArea() + "'" +
            ", cost='" + getCost() + "'" +
            ", requiredInfrastructure='" + getRequiredInfrastructure() + "'" +
            ", marketMaturity='" + getMarketMaturity() + "'" +
            ", outputData='" + getOutputData() + "'" +
            ", privacy='" + getPrivacy() + "'" +
            ", updateRate='" + getUpdateRate() + "'" +
            ", systemLatency='" + getSystemLatency() + "'" +
            ", _interface='" + get_interface() + "'" +
            ", systemIntegrity='" + getSystemIntegrity() + "'" +
            ", robustness='" + getRobustness() + "'" +
            ", availability='" + getAvailability() + "'" +
            ", continuity='" + getContinuity() + "'" +
            ", scalability='" + getScalability() + "'" +
            ", numberOfUsers='" + getNumberOfUsers() + "'" +
            ", approval='" + getApproval() + "'" +
            ", levelOfHybridisation='" + getLevelOfHybridisation() + "'" +
            ", technology='" + getTechnology() + "'" +
            ", measuredQuantity='" + getMeasuredQuantity() + "'" +
            ", basicMeasuringPrinciple='" + getBasicMeasuringPrinciple() + "'" +
            ", positionAlgorithm='" + getPositionAlgorithm() + "'" +
            ", signaltype='" + getSignaltype() + "'" +
            ", signalWavelength='" + getSignalWavelength() + "'" +
            ", systemArchitecture='" + getSystemArchitecture() + "'" +
            ", application='" + getApplication() + "'" +
            ", coordinateReference='" + getCoordinateReference() + "'" +
            "}";
    }
}
