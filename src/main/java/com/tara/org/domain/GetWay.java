package com.tara.org.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

import com.tara.org.domain.enumeration.Choice;

/**
 * A GetWay.
 */
@Entity
@Table(name = "get_way")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "getway")
public class GetWay implements Serializable {

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
    @Column(name = "jhi_compatibility", nullable = false)
    private String compatibility;

    @NotNull
    @Column(name = "upgradeability", nullable = false)
    private String upgradeability;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "dust_proof", nullable = false)
    private Choice dustProof;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "water_or_condensation_proof", nullable = false)
    private Choice waterOrCondensationProof;

    @NotNull
    @Column(name = "temperature", nullable = false)
    private Float temperature;

    @NotNull
    @Column(name = "ease_of_installation", nullable = false)
    private String easeOfInstallation;

    @NotNull
    @Column(name = "maintainability", nullable = false)
    private String maintainability;

    @Column(name = "jhi_size")
    private Float size;

    @Column(name = "ram")
    private String ram;

    @Column(name = "jhi_storage")
    private String storage;

    @Column(name = "operating_temperature")
    private Float operatingTemperature;

    @Column(name = "power")
    private String power;

    @Column(name = "relative_humidity")
    private Float relativeHumidity;

    @Column(name = "system_on_chip")
    private String systemOnChip;

    @Column(name = "cloud_platform_integration")
    private String cloudPlatformIntegration;

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

    public GetWay name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTechnologyType() {
        return technologyType;
    }

    public GetWay technologyType(String technologyType) {
        this.technologyType = technologyType;
        return this;
    }

    public void setTechnologyType(String technologyType) {
        this.technologyType = technologyType;
    }

    public Integer getTrl() {
        return trl;
    }

    public GetWay trl(Integer trl) {
        this.trl = trl;
        return this;
    }

    public void setTrl(Integer trl) {
        this.trl = trl;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public GetWay photo(byte[] photo) {
        this.photo = photo;
        return this;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public String getPhotoContentType() {
        return photoContentType;
    }

    public GetWay photoContentType(String photoContentType) {
        this.photoContentType = photoContentType;
        return this;
    }

    public void setPhotoContentType(String photoContentType) {
        this.photoContentType = photoContentType;
    }

    public byte[] getDatasheet() {
        return datasheet;
    }

    public GetWay datasheet(byte[] datasheet) {
        this.datasheet = datasheet;
        return this;
    }

    public void setDatasheet(byte[] datasheet) {
        this.datasheet = datasheet;
    }

    public String getDatasheetContentType() {
        return datasheetContentType;
    }

    public GetWay datasheetContentType(String datasheetContentType) {
        this.datasheetContentType = datasheetContentType;
        return this;
    }

    public void setDatasheetContentType(String datasheetContentType) {
        this.datasheetContentType = datasheetContentType;
    }

    public String getCompatibility() {
        return compatibility;
    }

    public GetWay compatibility(String compatibility) {
        this.compatibility = compatibility;
        return this;
    }

    public void setCompatibility(String compatibility) {
        this.compatibility = compatibility;
    }

    public String getUpgradeability() {
        return upgradeability;
    }

    public GetWay upgradeability(String upgradeability) {
        this.upgradeability = upgradeability;
        return this;
    }

    public void setUpgradeability(String upgradeability) {
        this.upgradeability = upgradeability;
    }

    public Choice getDustProof() {
        return dustProof;
    }

    public GetWay dustProof(Choice dustProof) {
        this.dustProof = dustProof;
        return this;
    }

    public void setDustProof(Choice dustProof) {
        this.dustProof = dustProof;
    }

    public Choice getWaterOrCondensationProof() {
        return waterOrCondensationProof;
    }

    public GetWay waterOrCondensationProof(Choice waterOrCondensationProof) {
        this.waterOrCondensationProof = waterOrCondensationProof;
        return this;
    }

    public void setWaterOrCondensationProof(Choice waterOrCondensationProof) {
        this.waterOrCondensationProof = waterOrCondensationProof;
    }

    public Float getTemperature() {
        return temperature;
    }

    public GetWay temperature(Float temperature) {
        this.temperature = temperature;
        return this;
    }

    public void setTemperature(Float temperature) {
        this.temperature = temperature;
    }

    public String getEaseOfInstallation() {
        return easeOfInstallation;
    }

    public GetWay easeOfInstallation(String easeOfInstallation) {
        this.easeOfInstallation = easeOfInstallation;
        return this;
    }

    public void setEaseOfInstallation(String easeOfInstallation) {
        this.easeOfInstallation = easeOfInstallation;
    }

    public String getMaintainability() {
        return maintainability;
    }

    public GetWay maintainability(String maintainability) {
        this.maintainability = maintainability;
        return this;
    }

    public void setMaintainability(String maintainability) {
        this.maintainability = maintainability;
    }

    public Float getSize() {
        return size;
    }

    public GetWay size(Float size) {
        this.size = size;
        return this;
    }

    public void setSize(Float size) {
        this.size = size;
    }

    public String getRam() {
        return ram;
    }

    public GetWay ram(String ram) {
        this.ram = ram;
        return this;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public String getStorage() {
        return storage;
    }

    public GetWay storage(String storage) {
        this.storage = storage;
        return this;
    }

    public void setStorage(String storage) {
        this.storage = storage;
    }

    public Float getOperatingTemperature() {
        return operatingTemperature;
    }

    public GetWay operatingTemperature(Float operatingTemperature) {
        this.operatingTemperature = operatingTemperature;
        return this;
    }

    public void setOperatingTemperature(Float operatingTemperature) {
        this.operatingTemperature = operatingTemperature;
    }

    public String getPower() {
        return power;
    }

    public GetWay power(String power) {
        this.power = power;
        return this;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public Float getRelativeHumidity() {
        return relativeHumidity;
    }

    public GetWay relativeHumidity(Float relativeHumidity) {
        this.relativeHumidity = relativeHumidity;
        return this;
    }

    public void setRelativeHumidity(Float relativeHumidity) {
        this.relativeHumidity = relativeHumidity;
    }

    public String getSystemOnChip() {
        return systemOnChip;
    }

    public GetWay systemOnChip(String systemOnChip) {
        this.systemOnChip = systemOnChip;
        return this;
    }

    public void setSystemOnChip(String systemOnChip) {
        this.systemOnChip = systemOnChip;
    }

    public String getCloudPlatformIntegration() {
        return cloudPlatformIntegration;
    }

    public GetWay cloudPlatformIntegration(String cloudPlatformIntegration) {
        this.cloudPlatformIntegration = cloudPlatformIntegration;
        return this;
    }

    public void setCloudPlatformIntegration(String cloudPlatformIntegration) {
        this.cloudPlatformIntegration = cloudPlatformIntegration;
    }

    public Company getOemName() {
        return oemName;
    }

    public GetWay oemName(Company company) {
        this.oemName = company;
        return this;
    }

    public void setOemName(Company company) {
        this.oemName = company;
    }

    public User getAddedBy() {
        return addedBy;
    }

    public GetWay addedBy(User user) {
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
        GetWay getWay = (GetWay) o;
        if (getWay.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), getWay.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "GetWay{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", technologyType='" + getTechnologyType() + "'" +
            ", trl='" + getTrl() + "'" +
            ", photo='" + getPhoto() + "'" +
            ", photoContentType='" + photoContentType + "'" +
            ", datasheet='" + getDatasheet() + "'" +
            ", datasheetContentType='" + datasheetContentType + "'" +
            ", compatibility='" + getCompatibility() + "'" +
            ", upgradeability='" + getUpgradeability() + "'" +
            ", dustProof='" + getDustProof() + "'" +
            ", waterOrCondensationProof='" + getWaterOrCondensationProof() + "'" +
            ", temperature='" + getTemperature() + "'" +
            ", easeOfInstallation='" + getEaseOfInstallation() + "'" +
            ", maintainability='" + getMaintainability() + "'" +
            ", size='" + getSize() + "'" +
            ", ram='" + getRam() + "'" +
            ", storage='" + getStorage() + "'" +
            ", operatingTemperature='" + getOperatingTemperature() + "'" +
            ", power='" + getPower() + "'" +
            ", relativeHumidity='" + getRelativeHumidity() + "'" +
            ", systemOnChip='" + getSystemOnChip() + "'" +
            ", cloudPlatformIntegration='" + getCloudPlatformIntegration() + "'" +
            "}";
    }
}
