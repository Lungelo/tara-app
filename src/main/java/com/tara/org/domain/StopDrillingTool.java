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
 * A StopDrillingTool.
 */
@Entity
@Table(name = "stop_drilling_tool")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "stopdrillingtool")
public class StopDrillingTool implements Serializable {

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
    @Column(name = "model", nullable = false)
    private String model;

    @Size(min = 3)
    @Column(name = "technology_type")
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
    @Column(name = "weight", nullable = false)
    private Float weight;

    @Column(name = "tested_weight")
    private Float testedWeight;

    @NotNull
    @Column(name = "length", nullable = false)
    private Float length;

    @Column(name = "tested_length")
    private Float testedLength;

    @NotNull
    @Column(name = "drilling_rate", nullable = false)
    private Float drillingRate;

    @Column(name = "tested_drilling_rate")
    private Float testedDrillingRate;

    @NotNull
    @Column(name = "noise_level", nullable = false)
    private Float noiseLevel;

    @Column(name = "tested_noise_level")
    private Float testedNoiseLevel;

    @NotNull
    @Column(name = "setting_up_time", nullable = false)
    private Float settingUpTime;

    @Column(name = "tested_setting_up_time")
    private Float testedSettingUpTime;

    @NotNull
    @Column(name = "dismantling_time", nullable = false)
    private Float dismantlingTime;

    @Column(name = "tested_dismantling_time")
    private Float testedDismantlingTime;

    @NotNull
    @Column(name = "water_use_per_metre_drilled", nullable = false)
    private Float waterUsePerMetreDrilled;

    @Column(name = "tested_water_use_per_metre_drilled")
    private Float testedWaterUsePerMetreDrilled;

    @NotNull
    @DecimalMin(value = "1")
    @DecimalMax(value = "100")
    @Column(name = "availability", nullable = false)
    private Float availability;

    @DecimalMin(value = "1")
    @DecimalMax(value = "100")
    @Column(name = "tested_availability")
    private Float testedAvailability;

    @NotNull
    @Column(name = "cost_per_meter_drilled", nullable = false)
    private Float costPerMeterDrilled;

    @Column(name = "tested_cost_per_meter_drilled")
    private Float testedCostPerMeterDrilled;

    @NotNull
    @Column(name = "hole_size_range", nullable = false)
    private String holeSizeRange;

    @Column(name = "tested_hole_size_range")
    private String testedHoleSizeRange;

    @NotNull
    @Column(name = "power_source", nullable = false)
    private String powerSource;

    @Column(name = "tested_power_source")
    private String testedPowerSource;

    @NotNull
    @Column(name = "power_rating", nullable = false)
    private Float powerRating;

    @Column(name = "tested_power_rating")
    private Float testedPowerRating;

    @NotNull
    @Column(name = "jhi_cost", precision=10, scale=2, nullable = false)
    private BigDecimal cost;

    @Column(name = "tested_cost", precision=10, scale=2)
    private BigDecimal testedCost;

    @NotNull
    @Column(name = "tramming_speed", nullable = false)
    private Float trammingSpeed;

    @Column(name = "tested_tramming_speed")
    private Float testedTrammingSpeed;

    @NotNull
    @Column(name = "gradeability", nullable = false)
    private Float gradeability;

    @Column(name = "tested_gradeability")
    private Float testedGradeability;

    @NotNull
    @Column(name = "number_of_booms", nullable = false)
    private Float numberOfBooms;

    @Column(name = "tested_number_of_booms")
    private Float testedNumberOfBooms;

    @NotNull
    @Column(name = "type_of_boom", nullable = false)
    private String typeOfBoom;

    @Column(name = "tested_type_of_boom")
    private String testedTypeOfBoom;

    @NotNull
    @Column(name = "outer_turning_radius", nullable = false)
    private Float outerTurningRadius;

    @Column(name = "tested_outer_turning_radius")
    private Float testedOuterTurningRadius;

    @NotNull
    @Column(name = "inner_turning_radius", nullable = false)
    private Float innerTurningRadius;

    @Column(name = "tested_inner_turning_radius")
    private Float testedInnerTurningRadius;

    @NotNull
    @Lob
    @Column(name = "observations_1", nullable = false)
    private String observations1;

    @Lob
    @Column(name = "tested_observations_1")
    private String testedObservations1;

    @NotNull
    @Lob
    @Column(name = "observations_2", nullable = false)
    private String observations2;

    @Lob
    @Column(name = "tested_observations_2")
    private String testedObservations2;

    @NotNull
    @Lob
    @Column(name = "observations_3", nullable = false)
    private String observations3;

    @Lob
    @Column(name = "tested_observations_3")
    private String testedObservations3;

    @NotNull
    @Lob
    @Column(name = "observations_4", nullable = false)
    private String observations4;

    @Lob
    @Column(name = "tested_observations_4")
    private String testedObservations4;

    @NotNull
    @Lob
    @Column(name = "observations_5", nullable = false)
    private String observations5;

    @Lob
    @Column(name = "tested_observations_5")
    private String testedObservations5;

    @NotNull
    @Lob
    @Column(name = "observations_6", nullable = false)
    private String observations6;

    @Lob
    @Column(name = "tested_observations_6")
    private String testedObservations6;

    @NotNull
    @Lob
    @Column(name = "operators_comments", nullable = false)
    private String operators_Comments;

    @Lob
    @Column(name = "tested_operators_comments")
    private String testedOperators_Comments;

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

    public StopDrillingTool name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public StopDrillingTool model(String model) {
        this.model = model;
        return this;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getTechnologyType() {
        return technologyType;
    }

    public StopDrillingTool technologyType(String technologyType) {
        this.technologyType = technologyType;
        return this;
    }

    public void setTechnologyType(String technologyType) {
        this.technologyType = technologyType;
    }

    public Integer getTrl() {
        return trl;
    }

    public StopDrillingTool trl(Integer trl) {
        this.trl = trl;
        return this;
    }

    public void setTrl(Integer trl) {
        this.trl = trl;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public StopDrillingTool photo(byte[] photo) {
        this.photo = photo;
        return this;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public String getPhotoContentType() {
        return photoContentType;
    }

    public StopDrillingTool photoContentType(String photoContentType) {
        this.photoContentType = photoContentType;
        return this;
    }

    public void setPhotoContentType(String photoContentType) {
        this.photoContentType = photoContentType;
    }

    public byte[] getDatasheet() {
        return datasheet;
    }

    public StopDrillingTool datasheet(byte[] datasheet) {
        this.datasheet = datasheet;
        return this;
    }

    public void setDatasheet(byte[] datasheet) {
        this.datasheet = datasheet;
    }

    public String getDatasheetContentType() {
        return datasheetContentType;
    }

    public StopDrillingTool datasheetContentType(String datasheetContentType) {
        this.datasheetContentType = datasheetContentType;
        return this;
    }

    public void setDatasheetContentType(String datasheetContentType) {
        this.datasheetContentType = datasheetContentType;
    }

    public Float getWeight() {
        return weight;
    }

    public StopDrillingTool weight(Float weight) {
        this.weight = weight;
        return this;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public Float getTestedWeight() {
        return testedWeight;
    }

    public StopDrillingTool testedWeight(Float testedWeight) {
        this.testedWeight = testedWeight;
        return this;
    }

    public void setTestedWeight(Float testedWeight) {
        this.testedWeight = testedWeight;
    }

    public Float getLength() {
        return length;
    }

    public StopDrillingTool length(Float length) {
        this.length = length;
        return this;
    }

    public void setLength(Float length) {
        this.length = length;
    }

    public Float getTestedLength() {
        return testedLength;
    }

    public StopDrillingTool testedLength(Float testedLength) {
        this.testedLength = testedLength;
        return this;
    }

    public void setTestedLength(Float testedLength) {
        this.testedLength = testedLength;
    }

    public Float getDrillingRate() {
        return drillingRate;
    }

    public StopDrillingTool drillingRate(Float drillingRate) {
        this.drillingRate = drillingRate;
        return this;
    }

    public void setDrillingRate(Float drillingRate) {
        this.drillingRate = drillingRate;
    }

    public Float getTestedDrillingRate() {
        return testedDrillingRate;
    }

    public StopDrillingTool testedDrillingRate(Float testedDrillingRate) {
        this.testedDrillingRate = testedDrillingRate;
        return this;
    }

    public void setTestedDrillingRate(Float testedDrillingRate) {
        this.testedDrillingRate = testedDrillingRate;
    }

    public Float getNoiseLevel() {
        return noiseLevel;
    }

    public StopDrillingTool noiseLevel(Float noiseLevel) {
        this.noiseLevel = noiseLevel;
        return this;
    }

    public void setNoiseLevel(Float noiseLevel) {
        this.noiseLevel = noiseLevel;
    }

    public Float getTestedNoiseLevel() {
        return testedNoiseLevel;
    }

    public StopDrillingTool testedNoiseLevel(Float testedNoiseLevel) {
        this.testedNoiseLevel = testedNoiseLevel;
        return this;
    }

    public void setTestedNoiseLevel(Float testedNoiseLevel) {
        this.testedNoiseLevel = testedNoiseLevel;
    }

    public Float getSettingUpTime() {
        return settingUpTime;
    }

    public StopDrillingTool settingUpTime(Float settingUpTime) {
        this.settingUpTime = settingUpTime;
        return this;
    }

    public void setSettingUpTime(Float settingUpTime) {
        this.settingUpTime = settingUpTime;
    }

    public Float getTestedSettingUpTime() {
        return testedSettingUpTime;
    }

    public StopDrillingTool testedSettingUpTime(Float testedSettingUpTime) {
        this.testedSettingUpTime = testedSettingUpTime;
        return this;
    }

    public void setTestedSettingUpTime(Float testedSettingUpTime) {
        this.testedSettingUpTime = testedSettingUpTime;
    }

    public Float getDismantlingTime() {
        return dismantlingTime;
    }

    public StopDrillingTool dismantlingTime(Float dismantlingTime) {
        this.dismantlingTime = dismantlingTime;
        return this;
    }

    public void setDismantlingTime(Float dismantlingTime) {
        this.dismantlingTime = dismantlingTime;
    }

    public Float getTestedDismantlingTime() {
        return testedDismantlingTime;
    }

    public StopDrillingTool testedDismantlingTime(Float testedDismantlingTime) {
        this.testedDismantlingTime = testedDismantlingTime;
        return this;
    }

    public void setTestedDismantlingTime(Float testedDismantlingTime) {
        this.testedDismantlingTime = testedDismantlingTime;
    }

    public Float getWaterUsePerMetreDrilled() {
        return waterUsePerMetreDrilled;
    }

    public StopDrillingTool waterUsePerMetreDrilled(Float waterUsePerMetreDrilled) {
        this.waterUsePerMetreDrilled = waterUsePerMetreDrilled;
        return this;
    }

    public void setWaterUsePerMetreDrilled(Float waterUsePerMetreDrilled) {
        this.waterUsePerMetreDrilled = waterUsePerMetreDrilled;
    }

    public Float getTestedWaterUsePerMetreDrilled() {
        return testedWaterUsePerMetreDrilled;
    }

    public StopDrillingTool testedWaterUsePerMetreDrilled(Float testedWaterUsePerMetreDrilled) {
        this.testedWaterUsePerMetreDrilled = testedWaterUsePerMetreDrilled;
        return this;
    }

    public void setTestedWaterUsePerMetreDrilled(Float testedWaterUsePerMetreDrilled) {
        this.testedWaterUsePerMetreDrilled = testedWaterUsePerMetreDrilled;
    }

    public Float getAvailability() {
        return availability;
    }

    public StopDrillingTool availability(Float availability) {
        this.availability = availability;
        return this;
    }

    public void setAvailability(Float availability) {
        this.availability = availability;
    }

    public Float getTestedAvailability() {
        return testedAvailability;
    }

    public StopDrillingTool testedAvailability(Float testedAvailability) {
        this.testedAvailability = testedAvailability;
        return this;
    }

    public void setTestedAvailability(Float testedAvailability) {
        this.testedAvailability = testedAvailability;
    }

    public Float getCostPerMeterDrilled() {
        return costPerMeterDrilled;
    }

    public StopDrillingTool costPerMeterDrilled(Float costPerMeterDrilled) {
        this.costPerMeterDrilled = costPerMeterDrilled;
        return this;
    }

    public void setCostPerMeterDrilled(Float costPerMeterDrilled) {
        this.costPerMeterDrilled = costPerMeterDrilled;
    }

    public Float getTestedCostPerMeterDrilled() {
        return testedCostPerMeterDrilled;
    }

    public StopDrillingTool testedCostPerMeterDrilled(Float testedCostPerMeterDrilled) {
        this.testedCostPerMeterDrilled = testedCostPerMeterDrilled;
        return this;
    }

    public void setTestedCostPerMeterDrilled(Float testedCostPerMeterDrilled) {
        this.testedCostPerMeterDrilled = testedCostPerMeterDrilled;
    }

    public String getHoleSizeRange() {
        return holeSizeRange;
    }

    public StopDrillingTool holeSizeRange(String holeSizeRange) {
        this.holeSizeRange = holeSizeRange;
        return this;
    }

    public void setHoleSizeRange(String holeSizeRange) {
        this.holeSizeRange = holeSizeRange;
    }

    public String getTestedHoleSizeRange() {
        return testedHoleSizeRange;
    }

    public StopDrillingTool testedHoleSizeRange(String testedHoleSizeRange) {
        this.testedHoleSizeRange = testedHoleSizeRange;
        return this;
    }

    public void setTestedHoleSizeRange(String testedHoleSizeRange) {
        this.testedHoleSizeRange = testedHoleSizeRange;
    }

    public String getPowerSource() {
        return powerSource;
    }

    public StopDrillingTool powerSource(String powerSource) {
        this.powerSource = powerSource;
        return this;
    }

    public void setPowerSource(String powerSource) {
        this.powerSource = powerSource;
    }

    public String getTestedPowerSource() {
        return testedPowerSource;
    }

    public StopDrillingTool testedPowerSource(String testedPowerSource) {
        this.testedPowerSource = testedPowerSource;
        return this;
    }

    public void setTestedPowerSource(String testedPowerSource) {
        this.testedPowerSource = testedPowerSource;
    }

    public Float getPowerRating() {
        return powerRating;
    }

    public StopDrillingTool powerRating(Float powerRating) {
        this.powerRating = powerRating;
        return this;
    }

    public void setPowerRating(Float powerRating) {
        this.powerRating = powerRating;
    }

    public Float getTestedPowerRating() {
        return testedPowerRating;
    }

    public StopDrillingTool testedPowerRating(Float testedPowerRating) {
        this.testedPowerRating = testedPowerRating;
        return this;
    }

    public void setTestedPowerRating(Float testedPowerRating) {
        this.testedPowerRating = testedPowerRating;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public StopDrillingTool cost(BigDecimal cost) {
        this.cost = cost;
        return this;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public BigDecimal getTestedCost() {
        return testedCost;
    }

    public StopDrillingTool testedCost(BigDecimal testedCost) {
        this.testedCost = testedCost;
        return this;
    }

    public void setTestedCost(BigDecimal testedCost) {
        this.testedCost = testedCost;
    }

    public Float getTrammingSpeed() {
        return trammingSpeed;
    }

    public StopDrillingTool trammingSpeed(Float trammingSpeed) {
        this.trammingSpeed = trammingSpeed;
        return this;
    }

    public void setTrammingSpeed(Float trammingSpeed) {
        this.trammingSpeed = trammingSpeed;
    }

    public Float getTestedTrammingSpeed() {
        return testedTrammingSpeed;
    }

    public StopDrillingTool testedTrammingSpeed(Float testedTrammingSpeed) {
        this.testedTrammingSpeed = testedTrammingSpeed;
        return this;
    }

    public void setTestedTrammingSpeed(Float testedTrammingSpeed) {
        this.testedTrammingSpeed = testedTrammingSpeed;
    }

    public Float getGradeability() {
        return gradeability;
    }

    public StopDrillingTool gradeability(Float gradeability) {
        this.gradeability = gradeability;
        return this;
    }

    public void setGradeability(Float gradeability) {
        this.gradeability = gradeability;
    }

    public Float getTestedGradeability() {
        return testedGradeability;
    }

    public StopDrillingTool testedGradeability(Float testedGradeability) {
        this.testedGradeability = testedGradeability;
        return this;
    }

    public void setTestedGradeability(Float testedGradeability) {
        this.testedGradeability = testedGradeability;
    }

    public Float getNumberOfBooms() {
        return numberOfBooms;
    }

    public StopDrillingTool numberOfBooms(Float numberOfBooms) {
        this.numberOfBooms = numberOfBooms;
        return this;
    }

    public void setNumberOfBooms(Float numberOfBooms) {
        this.numberOfBooms = numberOfBooms;
    }

    public Float getTestedNumberOfBooms() {
        return testedNumberOfBooms;
    }

    public StopDrillingTool testedNumberOfBooms(Float testedNumberOfBooms) {
        this.testedNumberOfBooms = testedNumberOfBooms;
        return this;
    }

    public void setTestedNumberOfBooms(Float testedNumberOfBooms) {
        this.testedNumberOfBooms = testedNumberOfBooms;
    }

    public String getTypeOfBoom() {
        return typeOfBoom;
    }

    public StopDrillingTool typeOfBoom(String typeOfBoom) {
        this.typeOfBoom = typeOfBoom;
        return this;
    }

    public void setTypeOfBoom(String typeOfBoom) {
        this.typeOfBoom = typeOfBoom;
    }

    public String getTestedTypeOfBoom() {
        return testedTypeOfBoom;
    }

    public StopDrillingTool testedTypeOfBoom(String testedTypeOfBoom) {
        this.testedTypeOfBoom = testedTypeOfBoom;
        return this;
    }

    public void setTestedTypeOfBoom(String testedTypeOfBoom) {
        this.testedTypeOfBoom = testedTypeOfBoom;
    }

    public Float getOuterTurningRadius() {
        return outerTurningRadius;
    }

    public StopDrillingTool outerTurningRadius(Float outerTurningRadius) {
        this.outerTurningRadius = outerTurningRadius;
        return this;
    }

    public void setOuterTurningRadius(Float outerTurningRadius) {
        this.outerTurningRadius = outerTurningRadius;
    }

    public Float getTestedOuterTurningRadius() {
        return testedOuterTurningRadius;
    }

    public StopDrillingTool testedOuterTurningRadius(Float testedOuterTurningRadius) {
        this.testedOuterTurningRadius = testedOuterTurningRadius;
        return this;
    }

    public void setTestedOuterTurningRadius(Float testedOuterTurningRadius) {
        this.testedOuterTurningRadius = testedOuterTurningRadius;
    }

    public Float getInnerTurningRadius() {
        return innerTurningRadius;
    }

    public StopDrillingTool innerTurningRadius(Float innerTurningRadius) {
        this.innerTurningRadius = innerTurningRadius;
        return this;
    }

    public void setInnerTurningRadius(Float innerTurningRadius) {
        this.innerTurningRadius = innerTurningRadius;
    }

    public Float getTestedInnerTurningRadius() {
        return testedInnerTurningRadius;
    }

    public StopDrillingTool testedInnerTurningRadius(Float testedInnerTurningRadius) {
        this.testedInnerTurningRadius = testedInnerTurningRadius;
        return this;
    }

    public void setTestedInnerTurningRadius(Float testedInnerTurningRadius) {
        this.testedInnerTurningRadius = testedInnerTurningRadius;
    }

    public String getObservations1() {
        return observations1;
    }

    public StopDrillingTool observations1(String observations1) {
        this.observations1 = observations1;
        return this;
    }

    public void setObservations1(String observations1) {
        this.observations1 = observations1;
    }

    public String getTestedObservations1() {
        return testedObservations1;
    }

    public StopDrillingTool testedObservations1(String testedObservations1) {
        this.testedObservations1 = testedObservations1;
        return this;
    }

    public void setTestedObservations1(String testedObservations1) {
        this.testedObservations1 = testedObservations1;
    }

    public String getObservations2() {
        return observations2;
    }

    public StopDrillingTool observations2(String observations2) {
        this.observations2 = observations2;
        return this;
    }

    public void setObservations2(String observations2) {
        this.observations2 = observations2;
    }

    public String getTestedObservations2() {
        return testedObservations2;
    }

    public StopDrillingTool testedObservations2(String testedObservations2) {
        this.testedObservations2 = testedObservations2;
        return this;
    }

    public void setTestedObservations2(String testedObservations2) {
        this.testedObservations2 = testedObservations2;
    }

    public String getObservations3() {
        return observations3;
    }

    public StopDrillingTool observations3(String observations3) {
        this.observations3 = observations3;
        return this;
    }

    public void setObservations3(String observations3) {
        this.observations3 = observations3;
    }

    public String getTestedObservations3() {
        return testedObservations3;
    }

    public StopDrillingTool testedObservations3(String testedObservations3) {
        this.testedObservations3 = testedObservations3;
        return this;
    }

    public void setTestedObservations3(String testedObservations3) {
        this.testedObservations3 = testedObservations3;
    }

    public String getObservations4() {
        return observations4;
    }

    public StopDrillingTool observations4(String observations4) {
        this.observations4 = observations4;
        return this;
    }

    public void setObservations4(String observations4) {
        this.observations4 = observations4;
    }

    public String getTestedObservations4() {
        return testedObservations4;
    }

    public StopDrillingTool testedObservations4(String testedObservations4) {
        this.testedObservations4 = testedObservations4;
        return this;
    }

    public void setTestedObservations4(String testedObservations4) {
        this.testedObservations4 = testedObservations4;
    }

    public String getObservations5() {
        return observations5;
    }

    public StopDrillingTool observations5(String observations5) {
        this.observations5 = observations5;
        return this;
    }

    public void setObservations5(String observations5) {
        this.observations5 = observations5;
    }

    public String getTestedObservations5() {
        return testedObservations5;
    }

    public StopDrillingTool testedObservations5(String testedObservations5) {
        this.testedObservations5 = testedObservations5;
        return this;
    }

    public void setTestedObservations5(String testedObservations5) {
        this.testedObservations5 = testedObservations5;
    }

    public String getObservations6() {
        return observations6;
    }

    public StopDrillingTool observations6(String observations6) {
        this.observations6 = observations6;
        return this;
    }

    public void setObservations6(String observations6) {
        this.observations6 = observations6;
    }

    public String getTestedObservations6() {
        return testedObservations6;
    }

    public StopDrillingTool testedObservations6(String testedObservations6) {
        this.testedObservations6 = testedObservations6;
        return this;
    }

    public void setTestedObservations6(String testedObservations6) {
        this.testedObservations6 = testedObservations6;
    }

    public String getOperators_Comments() {
        return operators_Comments;
    }

    public StopDrillingTool operators_Comments(String operators_Comments) {
        this.operators_Comments = operators_Comments;
        return this;
    }

    public void setOperators_Comments(String operators_Comments) {
        this.operators_Comments = operators_Comments;
    }

    public String getTestedOperators_Comments() {
        return testedOperators_Comments;
    }

    public StopDrillingTool testedOperators_Comments(String testedOperators_Comments) {
        this.testedOperators_Comments = testedOperators_Comments;
        return this;
    }

    public void setTestedOperators_Comments(String testedOperators_Comments) {
        this.testedOperators_Comments = testedOperators_Comments;
    }

    public Company getOemName() {
        return oemName;
    }

    public StopDrillingTool oemName(Company company) {
        this.oemName = company;
        return this;
    }

    public void setOemName(Company company) {
        this.oemName = company;
    }

    public User getAddedBy() {
        return addedBy;
    }

    public StopDrillingTool addedBy(User user) {
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
        StopDrillingTool stopDrillingTool = (StopDrillingTool) o;
        if (stopDrillingTool.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), stopDrillingTool.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "StopDrillingTool{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", model='" + getModel() + "'" +
            ", technologyType='" + getTechnologyType() + "'" +
            ", trl='" + getTrl() + "'" +
            ", photo='" + getPhoto() + "'" +
            ", photoContentType='" + photoContentType + "'" +
            ", datasheet='" + getDatasheet() + "'" +
            ", datasheetContentType='" + datasheetContentType + "'" +
            ", weight='" + getWeight() + "'" +
            ", testedWeight='" + getTestedWeight() + "'" +
            ", length='" + getLength() + "'" +
            ", testedLength='" + getTestedLength() + "'" +
            ", drillingRate='" + getDrillingRate() + "'" +
            ", testedDrillingRate='" + getTestedDrillingRate() + "'" +
            ", noiseLevel='" + getNoiseLevel() + "'" +
            ", testedNoiseLevel='" + getTestedNoiseLevel() + "'" +
            ", settingUpTime='" + getSettingUpTime() + "'" +
            ", testedSettingUpTime='" + getTestedSettingUpTime() + "'" +
            ", dismantlingTime='" + getDismantlingTime() + "'" +
            ", testedDismantlingTime='" + getTestedDismantlingTime() + "'" +
            ", waterUsePerMetreDrilled='" + getWaterUsePerMetreDrilled() + "'" +
            ", testedWaterUsePerMetreDrilled='" + getTestedWaterUsePerMetreDrilled() + "'" +
            ", availability='" + getAvailability() + "'" +
            ", testedAvailability='" + getTestedAvailability() + "'" +
            ", costPerMeterDrilled='" + getCostPerMeterDrilled() + "'" +
            ", testedCostPerMeterDrilled='" + getTestedCostPerMeterDrilled() + "'" +
            ", holeSizeRange='" + getHoleSizeRange() + "'" +
            ", testedHoleSizeRange='" + getTestedHoleSizeRange() + "'" +
            ", powerSource='" + getPowerSource() + "'" +
            ", testedPowerSource='" + getTestedPowerSource() + "'" +
            ", powerRating='" + getPowerRating() + "'" +
            ", testedPowerRating='" + getTestedPowerRating() + "'" +
            ", cost='" + getCost() + "'" +
            ", testedCost='" + getTestedCost() + "'" +
            ", trammingSpeed='" + getTrammingSpeed() + "'" +
            ", testedTrammingSpeed='" + getTestedTrammingSpeed() + "'" +
            ", gradeability='" + getGradeability() + "'" +
            ", testedGradeability='" + getTestedGradeability() + "'" +
            ", numberOfBooms='" + getNumberOfBooms() + "'" +
            ", testedNumberOfBooms='" + getTestedNumberOfBooms() + "'" +
            ", typeOfBoom='" + getTypeOfBoom() + "'" +
            ", testedTypeOfBoom='" + getTestedTypeOfBoom() + "'" +
            ", outerTurningRadius='" + getOuterTurningRadius() + "'" +
            ", testedOuterTurningRadius='" + getTestedOuterTurningRadius() + "'" +
            ", innerTurningRadius='" + getInnerTurningRadius() + "'" +
            ", testedInnerTurningRadius='" + getTestedInnerTurningRadius() + "'" +
            ", observations1='" + getObservations1() + "'" +
            ", testedObservations1='" + getTestedObservations1() + "'" +
            ", observations2='" + getObservations2() + "'" +
            ", testedObservations2='" + getTestedObservations2() + "'" +
            ", observations3='" + getObservations3() + "'" +
            ", testedObservations3='" + getTestedObservations3() + "'" +
            ", observations4='" + getObservations4() + "'" +
            ", testedObservations4='" + getTestedObservations4() + "'" +
            ", observations5='" + getObservations5() + "'" +
            ", testedObservations5='" + getTestedObservations5() + "'" +
            ", observations6='" + getObservations6() + "'" +
            ", testedObservations6='" + getTestedObservations6() + "'" +
            ", operators_Comments='" + getOperators_Comments() + "'" +
            ", testedOperators_Comments='" + getTestedOperators_Comments() + "'" +
            "}";
    }
}
