package com.tara.org.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DevSupportingTool.
 */
@Entity
@Table(name = "dev_supporing_tool")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "devsupportingtool")
public class DevSupportingTool implements Serializable {

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
    @Column(name = "type_of_machine", nullable = false)
    private String typeOfMachine;

    @Column(name = "tested_type_of_machine")
    private String testedTypeOfMachine;

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
    @Column(name = "width", nullable = false)
    private Float width;

    @Column(name = "tested_width")
    private Float testedWidth;

    @NotNull
    @Column(name = "power_rating_percussion", nullable = false)
    private Float powerRatingPercussion;

    @Column(name = "tested_power_rating_percussion")
    private Float testedPowerRatingPercussion;

    @NotNull
    @Column(name = "noise_level_at_operator_stand", nullable = false)
    private Float noiseLevelAtOperatorStand;

    @Column(name = "tested_noise_level_at_operator_stand")
    private Float testedNoiseLevelAtOperatorStand;

    @Column(name = "hole_size_range")
    private String holeSizeRange;

    @Column(name = "tested_hole_size_range")
    private String testedHoleSizeRange;

    @NotNull
    @Column(name = "drill_water_consumption", nullable = false)
    private Float drillWaterConsumption;

    @Column(name = "tested_drill_water_consumption")
    private Float testedDrillWaterConsumption;

    @NotNull
    @Column(name = "water_consumption_per_metre_drilled", nullable = false)
    private Float waterConsumptionPerMetreDrilled;

    @Column(name = "tested_water_consumption_per_metre_drilled")
    private Float testedWaterConsumptionPerMetreDrilled;

    @NotNull
    @Column(name = "power_source", nullable = false)
    private String powerSource;

    @Column(name = "tested_power_source")
    private String testedPowerSource;

    @Column(name = "tramming_speed")
    private Float trammingSpeed;

    @Column(name = "tested_tramming_speed")
    private Float testedTrammingSpeed;

    @NotNull
    @Column(name = "bolt_length_range", nullable = false)
    private String boltLengthRange;

    @Column(name = "tested_bolt_length_range")
    private String testedBoltLengthRange;

    @NotNull
    @Column(name = "minimum_operating_height", nullable = false)
    private Float minimumOperatingHeight;

    @Column(name = "tested_minimum_operating_height")
    private Float testedMinimumOperatingHeight;

    @NotNull
    @Column(name = "drill_speed", nullable = false)
    private Float drillSpeed;

    @Column(name = "tested_drill_speed")
    private Float testedDrillSpeed;

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
    @Column(name = "nner_turning_radius", nullable = false)
    private Float nnerTurningRadius;

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

    public DevSupportingTool name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public DevSupportingTool model(String model) {
        this.model = model;
        return this;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getTechnologyType() {
        return technologyType;
    }

    public DevSupportingTool technologyType(String technologyType) {
        this.technologyType = technologyType;
        return this;
    }

    public void setTechnologyType(String technologyType) {
        this.technologyType = technologyType;
    }

    public Integer getTrl() {
        return trl;
    }

    public DevSupportingTool trl(Integer trl) {
        this.trl = trl;
        return this;
    }

    public void setTrl(Integer trl) {
        this.trl = trl;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public DevSupportingTool photo(byte[] photo) {
        this.photo = photo;
        return this;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public String getPhotoContentType() {
        return photoContentType;
    }

    public DevSupportingTool photoContentType(String photoContentType) {
        this.photoContentType = photoContentType;
        return this;
    }

    public void setPhotoContentType(String photoContentType) {
        this.photoContentType = photoContentType;
    }

    public byte[] getDatasheet() {
        return datasheet;
    }

    public DevSupportingTool datasheet(byte[] datasheet) {
        this.datasheet = datasheet;
        return this;
    }

    public void setDatasheet(byte[] datasheet) {
        this.datasheet = datasheet;
    }

    public String getDatasheetContentType() {
        return datasheetContentType;
    }

    public DevSupportingTool datasheetContentType(String datasheetContentType) {
        this.datasheetContentType = datasheetContentType;
        return this;
    }

    public void setDatasheetContentType(String datasheetContentType) {
        this.datasheetContentType = datasheetContentType;
    }

    public String getTypeOfMachine() {
        return typeOfMachine;
    }

    public DevSupportingTool typeOfMachine(String typeOfMachine) {
        this.typeOfMachine = typeOfMachine;
        return this;
    }

    public void setTypeOfMachine(String typeOfMachine) {
        this.typeOfMachine = typeOfMachine;
    }

    public String getTestedTypeOfMachine() {
        return testedTypeOfMachine;
    }

    public DevSupportingTool testedTypeOfMachine(String testedTypeOfMachine) {
        this.testedTypeOfMachine = testedTypeOfMachine;
        return this;
    }

    public void setTestedTypeOfMachine(String testedTypeOfMachine) {
        this.testedTypeOfMachine = testedTypeOfMachine;
    }

    public Float getWeight() {
        return weight;
    }

    public DevSupportingTool weight(Float weight) {
        this.weight = weight;
        return this;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public Float getTestedWeight() {
        return testedWeight;
    }

    public DevSupportingTool testedWeight(Float testedWeight) {
        this.testedWeight = testedWeight;
        return this;
    }

    public void setTestedWeight(Float testedWeight) {
        this.testedWeight = testedWeight;
    }

    public Float getLength() {
        return length;
    }

    public DevSupportingTool length(Float length) {
        this.length = length;
        return this;
    }

    public void setLength(Float length) {
        this.length = length;
    }

    public Float getTestedLength() {
        return testedLength;
    }

    public DevSupportingTool testedLength(Float testedLength) {
        this.testedLength = testedLength;
        return this;
    }

    public void setTestedLength(Float testedLength) {
        this.testedLength = testedLength;
    }

    public Float getWidth() {
        return width;
    }

    public DevSupportingTool width(Float width) {
        this.width = width;
        return this;
    }

    public void setWidth(Float width) {
        this.width = width;
    }

    public Float getTestedWidth() {
        return testedWidth;
    }

    public DevSupportingTool testedWidth(Float testedWidth) {
        this.testedWidth = testedWidth;
        return this;
    }

    public void setTestedWidth(Float testedWidth) {
        this.testedWidth = testedWidth;
    }

    public Float getPowerRatingPercussion() {
        return powerRatingPercussion;
    }

    public DevSupportingTool powerRatingPercussion(Float powerRatingPercussion) {
        this.powerRatingPercussion = powerRatingPercussion;
        return this;
    }

    public void setPowerRatingPercussion(Float powerRatingPercussion) {
        this.powerRatingPercussion = powerRatingPercussion;
    }

    public Float getTestedPowerRatingPercussion() {
        return testedPowerRatingPercussion;
    }

    public DevSupportingTool testedPowerRatingPercussion(Float testedPowerRatingPercussion) {
        this.testedPowerRatingPercussion = testedPowerRatingPercussion;
        return this;
    }

    public void setTestedPowerRatingPercussion(Float testedPowerRatingPercussion) {
        this.testedPowerRatingPercussion = testedPowerRatingPercussion;
    }

    public Float getNoiseLevelAtOperatorStand() {
        return noiseLevelAtOperatorStand;
    }

    public DevSupportingTool noiseLevelAtOperatorStand(Float noiseLevelAtOperatorStand) {
        this.noiseLevelAtOperatorStand = noiseLevelAtOperatorStand;
        return this;
    }

    public void setNoiseLevelAtOperatorStand(Float noiseLevelAtOperatorStand) {
        this.noiseLevelAtOperatorStand = noiseLevelAtOperatorStand;
    }

    public Float getTestedNoiseLevelAtOperatorStand() {
        return testedNoiseLevelAtOperatorStand;
    }

    public DevSupportingTool testedNoiseLevelAtOperatorStand(Float testedNoiseLevelAtOperatorStand) {
        this.testedNoiseLevelAtOperatorStand = testedNoiseLevelAtOperatorStand;
        return this;
    }

    public void setTestedNoiseLevelAtOperatorStand(Float testedNoiseLevelAtOperatorStand) {
        this.testedNoiseLevelAtOperatorStand = testedNoiseLevelAtOperatorStand;
    }

    public String getHoleSizeRange() {
        return holeSizeRange;
    }

    public DevSupportingTool holeSizeRange(String holeSizeRange) {
        this.holeSizeRange = holeSizeRange;
        return this;
    }

    public void setHoleSizeRange(String holeSizeRange) {
        this.holeSizeRange = holeSizeRange;
    }

    public String getTestedHoleSizeRange() {
        return testedHoleSizeRange;
    }

    public DevSupportingTool testedHoleSizeRange(String testedHoleSizeRange) {
        this.testedHoleSizeRange = testedHoleSizeRange;
        return this;
    }

    public void setTestedHoleSizeRange(String testedHoleSizeRange) {
        this.testedHoleSizeRange = testedHoleSizeRange;
    }

    public Float getDrillWaterConsumption() {
        return drillWaterConsumption;
    }

    public DevSupportingTool drillWaterConsumption(Float drillWaterConsumption) {
        this.drillWaterConsumption = drillWaterConsumption;
        return this;
    }

    public void setDrillWaterConsumption(Float drillWaterConsumption) {
        this.drillWaterConsumption = drillWaterConsumption;
    }

    public Float getTestedDrillWaterConsumption() {
        return testedDrillWaterConsumption;
    }

    public DevSupportingTool testedDrillWaterConsumption(Float testedDrillWaterConsumption) {
        this.testedDrillWaterConsumption = testedDrillWaterConsumption;
        return this;
    }

    public void setTestedDrillWaterConsumption(Float testedDrillWaterConsumption) {
        this.testedDrillWaterConsumption = testedDrillWaterConsumption;
    }

    public Float getWaterConsumptionPerMetreDrilled() {
        return waterConsumptionPerMetreDrilled;
    }

    public DevSupportingTool waterConsumptionPerMetreDrilled(Float waterConsumptionPerMetreDrilled) {
        this.waterConsumptionPerMetreDrilled = waterConsumptionPerMetreDrilled;
        return this;
    }

    public void setWaterConsumptionPerMetreDrilled(Float waterConsumptionPerMetreDrilled) {
        this.waterConsumptionPerMetreDrilled = waterConsumptionPerMetreDrilled;
    }

    public Float getTestedWaterConsumptionPerMetreDrilled() {
        return testedWaterConsumptionPerMetreDrilled;
    }

    public DevSupportingTool testedWaterConsumptionPerMetreDrilled(Float testedWaterConsumptionPerMetreDrilled) {
        this.testedWaterConsumptionPerMetreDrilled = testedWaterConsumptionPerMetreDrilled;
        return this;
    }

    public void setTestedWaterConsumptionPerMetreDrilled(Float testedWaterConsumptionPerMetreDrilled) {
        this.testedWaterConsumptionPerMetreDrilled = testedWaterConsumptionPerMetreDrilled;
    }

    public String getPowerSource() {
        return powerSource;
    }

    public DevSupportingTool powerSource(String powerSource) {
        this.powerSource = powerSource;
        return this;
    }

    public void setPowerSource(String powerSource) {
        this.powerSource = powerSource;
    }

    public String getTestedPowerSource() {
        return testedPowerSource;
    }

    public DevSupportingTool testedPowerSource(String testedPowerSource) {
        this.testedPowerSource = testedPowerSource;
        return this;
    }

    public void setTestedPowerSource(String testedPowerSource) {
        this.testedPowerSource = testedPowerSource;
    }

    public Float getTrammingSpeed() {
        return trammingSpeed;
    }

    public DevSupportingTool trammingSpeed(Float trammingSpeed) {
        this.trammingSpeed = trammingSpeed;
        return this;
    }

    public void setTrammingSpeed(Float trammingSpeed) {
        this.trammingSpeed = trammingSpeed;
    }

    public Float getTestedTrammingSpeed() {
        return testedTrammingSpeed;
    }

    public DevSupportingTool testedTrammingSpeed(Float testedTrammingSpeed) {
        this.testedTrammingSpeed = testedTrammingSpeed;
        return this;
    }

    public void setTestedTrammingSpeed(Float testedTrammingSpeed) {
        this.testedTrammingSpeed = testedTrammingSpeed;
    }

    public String getBoltLengthRange() {
        return boltLengthRange;
    }

    public DevSupportingTool boltLengthRange(String boltLengthRange) {
        this.boltLengthRange = boltLengthRange;
        return this;
    }

    public void setBoltLengthRange(String boltLengthRange) {
        this.boltLengthRange = boltLengthRange;
    }

    public String getTestedBoltLengthRange() {
        return testedBoltLengthRange;
    }

    public DevSupportingTool testedBoltLengthRange(String testedBoltLengthRange) {
        this.testedBoltLengthRange = testedBoltLengthRange;
        return this;
    }

    public void setTestedBoltLengthRange(String testedBoltLengthRange) {
        this.testedBoltLengthRange = testedBoltLengthRange;
    }

    public Float getMinimumOperatingHeight() {
        return minimumOperatingHeight;
    }

    public DevSupportingTool minimumOperatingHeight(Float minimumOperatingHeight) {
        this.minimumOperatingHeight = minimumOperatingHeight;
        return this;
    }

    public void setMinimumOperatingHeight(Float minimumOperatingHeight) {
        this.minimumOperatingHeight = minimumOperatingHeight;
    }

    public Float getTestedMinimumOperatingHeight() {
        return testedMinimumOperatingHeight;
    }

    public DevSupportingTool testedMinimumOperatingHeight(Float testedMinimumOperatingHeight) {
        this.testedMinimumOperatingHeight = testedMinimumOperatingHeight;
        return this;
    }

    public void setTestedMinimumOperatingHeight(Float testedMinimumOperatingHeight) {
        this.testedMinimumOperatingHeight = testedMinimumOperatingHeight;
    }

    public Float getDrillSpeed() {
        return drillSpeed;
    }

    public DevSupportingTool drillSpeed(Float drillSpeed) {
        this.drillSpeed = drillSpeed;
        return this;
    }

    public void setDrillSpeed(Float drillSpeed) {
        this.drillSpeed = drillSpeed;
    }

    public Float getTestedDrillSpeed() {
        return testedDrillSpeed;
    }

    public DevSupportingTool testedDrillSpeed(Float testedDrillSpeed) {
        this.testedDrillSpeed = testedDrillSpeed;
        return this;
    }

    public void setTestedDrillSpeed(Float testedDrillSpeed) {
        this.testedDrillSpeed = testedDrillSpeed;
    }

    public Float getGradeability() {
        return gradeability;
    }

    public DevSupportingTool gradeability(Float gradeability) {
        this.gradeability = gradeability;
        return this;
    }

    public void setGradeability(Float gradeability) {
        this.gradeability = gradeability;
    }

    public Float getTestedGradeability() {
        return testedGradeability;
    }

    public DevSupportingTool testedGradeability(Float testedGradeability) {
        this.testedGradeability = testedGradeability;
        return this;
    }

    public void setTestedGradeability(Float testedGradeability) {
        this.testedGradeability = testedGradeability;
    }

    public Float getNumberOfBooms() {
        return numberOfBooms;
    }

    public DevSupportingTool numberOfBooms(Float numberOfBooms) {
        this.numberOfBooms = numberOfBooms;
        return this;
    }

    public void setNumberOfBooms(Float numberOfBooms) {
        this.numberOfBooms = numberOfBooms;
    }

    public Float getTestedNumberOfBooms() {
        return testedNumberOfBooms;
    }

    public DevSupportingTool testedNumberOfBooms(Float testedNumberOfBooms) {
        this.testedNumberOfBooms = testedNumberOfBooms;
        return this;
    }

    public void setTestedNumberOfBooms(Float testedNumberOfBooms) {
        this.testedNumberOfBooms = testedNumberOfBooms;
    }

    public String getTypeOfBoom() {
        return typeOfBoom;
    }

    public DevSupportingTool typeOfBoom(String typeOfBoom) {
        this.typeOfBoom = typeOfBoom;
        return this;
    }

    public void setTypeOfBoom(String typeOfBoom) {
        this.typeOfBoom = typeOfBoom;
    }

    public String getTestedTypeOfBoom() {
        return testedTypeOfBoom;
    }

    public DevSupportingTool testedTypeOfBoom(String testedTypeOfBoom) {
        this.testedTypeOfBoom = testedTypeOfBoom;
        return this;
    }

    public void setTestedTypeOfBoom(String testedTypeOfBoom) {
        this.testedTypeOfBoom = testedTypeOfBoom;
    }

    public Float getOuterTurningRadius() {
        return outerTurningRadius;
    }

    public DevSupportingTool outerTurningRadius(Float outerTurningRadius) {
        this.outerTurningRadius = outerTurningRadius;
        return this;
    }

    public void setOuterTurningRadius(Float outerTurningRadius) {
        this.outerTurningRadius = outerTurningRadius;
    }

    public Float getTestedOuterTurningRadius() {
        return testedOuterTurningRadius;
    }

    public DevSupportingTool testedOuterTurningRadius(Float testedOuterTurningRadius) {
        this.testedOuterTurningRadius = testedOuterTurningRadius;
        return this;
    }

    public void setTestedOuterTurningRadius(Float testedOuterTurningRadius) {
        this.testedOuterTurningRadius = testedOuterTurningRadius;
    }

    public Float getNnerTurningRadius() {
        return nnerTurningRadius;
    }

    public DevSupportingTool nnerTurningRadius(Float nnerTurningRadius) {
        this.nnerTurningRadius = nnerTurningRadius;
        return this;
    }

    public void setNnerTurningRadius(Float nnerTurningRadius) {
        this.nnerTurningRadius = nnerTurningRadius;
    }

    public Float getTestedInnerTurningRadius() {
        return testedInnerTurningRadius;
    }

    public DevSupportingTool testedInnerTurningRadius(Float testedInnerTurningRadius) {
        this.testedInnerTurningRadius = testedInnerTurningRadius;
        return this;
    }

    public void setTestedInnerTurningRadius(Float testedInnerTurningRadius) {
        this.testedInnerTurningRadius = testedInnerTurningRadius;
    }

    public String getObservations1() {
        return observations1;
    }

    public DevSupportingTool observations1(String observations1) {
        this.observations1 = observations1;
        return this;
    }

    public void setObservations1(String observations1) {
        this.observations1 = observations1;
    }

    public String getTestedObservations1() {
        return testedObservations1;
    }

    public DevSupportingTool testedObservations1(String testedObservations1) {
        this.testedObservations1 = testedObservations1;
        return this;
    }

    public void setTestedObservations1(String testedObservations1) {
        this.testedObservations1 = testedObservations1;
    }

    public String getObservations2() {
        return observations2;
    }

    public DevSupportingTool observations2(String observations2) {
        this.observations2 = observations2;
        return this;
    }

    public void setObservations2(String observations2) {
        this.observations2 = observations2;
    }

    public String getTestedObservations2() {
        return testedObservations2;
    }

    public DevSupportingTool testedObservations2(String testedObservations2) {
        this.testedObservations2 = testedObservations2;
        return this;
    }

    public void setTestedObservations2(String testedObservations2) {
        this.testedObservations2 = testedObservations2;
    }

    public String getObservations3() {
        return observations3;
    }

    public DevSupportingTool observations3(String observations3) {
        this.observations3 = observations3;
        return this;
    }

    public void setObservations3(String observations3) {
        this.observations3 = observations3;
    }

    public String getTestedObservations3() {
        return testedObservations3;
    }

    public DevSupportingTool testedObservations3(String testedObservations3) {
        this.testedObservations3 = testedObservations3;
        return this;
    }

    public void setTestedObservations3(String testedObservations3) {
        this.testedObservations3 = testedObservations3;
    }

    public String getObservations4() {
        return observations4;
    }

    public DevSupportingTool observations4(String observations4) {
        this.observations4 = observations4;
        return this;
    }

    public void setObservations4(String observations4) {
        this.observations4 = observations4;
    }

    public String getTestedObservations4() {
        return testedObservations4;
    }

    public DevSupportingTool testedObservations4(String testedObservations4) {
        this.testedObservations4 = testedObservations4;
        return this;
    }

    public void setTestedObservations4(String testedObservations4) {
        this.testedObservations4 = testedObservations4;
    }

    public String getObservations5() {
        return observations5;
    }

    public DevSupportingTool observations5(String observations5) {
        this.observations5 = observations5;
        return this;
    }

    public void setObservations5(String observations5) {
        this.observations5 = observations5;
    }

    public String getTestedObservations5() {
        return testedObservations5;
    }

    public DevSupportingTool testedObservations5(String testedObservations5) {
        this.testedObservations5 = testedObservations5;
        return this;
    }

    public void setTestedObservations5(String testedObservations5) {
        this.testedObservations5 = testedObservations5;
    }

    public String getObservations6() {
        return observations6;
    }

    public DevSupportingTool observations6(String observations6) {
        this.observations6 = observations6;
        return this;
    }

    public void setObservations6(String observations6) {
        this.observations6 = observations6;
    }

    public String getTestedObservations6() {
        return testedObservations6;
    }

    public DevSupportingTool testedObservations6(String testedObservations6) {
        this.testedObservations6 = testedObservations6;
        return this;
    }

    public void setTestedObservations6(String testedObservations6) {
        this.testedObservations6 = testedObservations6;
    }

    public String getOperators_Comments() {
        return operators_Comments;
    }

    public DevSupportingTool operators_Comments(String operators_Comments) {
        this.operators_Comments = operators_Comments;
        return this;
    }

    public void setOperators_Comments(String operators_Comments) {
        this.operators_Comments = operators_Comments;
    }

    public String getTestedOperators_Comments() {
        return testedOperators_Comments;
    }

    public DevSupportingTool testedOperators_Comments(String testedOperators_Comments) {
        this.testedOperators_Comments = testedOperators_Comments;
        return this;
    }

    public void setTestedOperators_Comments(String testedOperators_Comments) {
        this.testedOperators_Comments = testedOperators_Comments;
    }

    public Company getOemName() {
        return oemName;
    }

    public DevSupportingTool oemName(Company company) {
        this.oemName = company;
        return this;
    }

    public void setOemName(Company company) {
        this.oemName = company;
    }

    public User getAddedBy() {
        return addedBy;
    }

    public DevSupportingTool addedBy(User user) {
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
        DevSupportingTool devSupportingTool = (DevSupportingTool) o;
        if (devSupportingTool.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), devSupportingTool.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DevSupportingTool{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", model='" + getModel() + "'" +
            ", technologyType='" + getTechnologyType() + "'" +
            ", trl='" + getTrl() + "'" +
            ", photo='" + getPhoto() + "'" +
            ", photoContentType='" + photoContentType + "'" +
            ", datasheet='" + getDatasheet() + "'" +
            ", datasheetContentType='" + datasheetContentType + "'" +
            ", typeOfMachine='" + getTypeOfMachine() + "'" +
            ", testedTypeOfMachine='" + getTestedTypeOfMachine() + "'" +
            ", weight='" + getWeight() + "'" +
            ", testedWeight='" + getTestedWeight() + "'" +
            ", length='" + getLength() + "'" +
            ", testedLength='" + getTestedLength() + "'" +
            ", width='" + getWidth() + "'" +
            ", testedWidth='" + getTestedWidth() + "'" +
            ", powerRatingPercussion='" + getPowerRatingPercussion() + "'" +
            ", testedPowerRatingPercussion='" + getTestedPowerRatingPercussion() + "'" +
            ", noiseLevelAtOperatorStand='" + getNoiseLevelAtOperatorStand() + "'" +
            ", testedNoiseLevelAtOperatorStand='" + getTestedNoiseLevelAtOperatorStand() + "'" +
            ", holeSizeRange='" + getHoleSizeRange() + "'" +
            ", testedHoleSizeRange='" + getTestedHoleSizeRange() + "'" +
            ", drillWaterConsumption='" + getDrillWaterConsumption() + "'" +
            ", testedDrillWaterConsumption='" + getTestedDrillWaterConsumption() + "'" +
            ", waterConsumptionPerMetreDrilled='" + getWaterConsumptionPerMetreDrilled() + "'" +
            ", testedWaterConsumptionPerMetreDrilled='" + getTestedWaterConsumptionPerMetreDrilled() + "'" +
            ", powerSource='" + getPowerSource() + "'" +
            ", testedPowerSource='" + getTestedPowerSource() + "'" +
            ", trammingSpeed='" + getTrammingSpeed() + "'" +
            ", testedTrammingSpeed='" + getTestedTrammingSpeed() + "'" +
            ", boltLengthRange='" + getBoltLengthRange() + "'" +
            ", testedBoltLengthRange='" + getTestedBoltLengthRange() + "'" +
            ", minimumOperatingHeight='" + getMinimumOperatingHeight() + "'" +
            ", testedMinimumOperatingHeight='" + getTestedMinimumOperatingHeight() + "'" +
            ", drillSpeed='" + getDrillSpeed() + "'" +
            ", testedDrillSpeed='" + getTestedDrillSpeed() + "'" +
            ", gradeability='" + getGradeability() + "'" +
            ", testedGradeability='" + getTestedGradeability() + "'" +
            ", numberOfBooms='" + getNumberOfBooms() + "'" +
            ", testedNumberOfBooms='" + getTestedNumberOfBooms() + "'" +
            ", typeOfBoom='" + getTypeOfBoom() + "'" +
            ", testedTypeOfBoom='" + getTestedTypeOfBoom() + "'" +
            ", outerTurningRadius='" + getOuterTurningRadius() + "'" +
            ", testedOuterTurningRadius='" + getTestedOuterTurningRadius() + "'" +
            ", nnerTurningRadius='" + getNnerTurningRadius() + "'" +
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
