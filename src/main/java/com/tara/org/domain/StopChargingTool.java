package com.tara.org.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

import com.tara.org.domain.enumeration.LubricationType;

/**
 * A StopChargingTool.
 */
@Entity
@Table(name = "stop_charging_tool")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "stopchargingtool")
public class StopChargingTool implements Serializable {

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
    @Column(name = "hose_length", nullable = false)
    private Float hoseLength;

    @Column(name = "tested_hose_length")
    private Float testedHoseLength;

    @NotNull
    @Column(name = "hole_diameter", nullable = false)
    private Float holeDiameter;

    @Column(name = "tested_hole_diameter")
    private Float testedHoleDiameter;

    @NotNull
    @Column(name = "explosive_type", nullable = false)
    private String explosiveType;

    @Column(name = "tested_explosive_type")
    private String testedExplosiveType;

    @Column(name = "tank_capacity")
    private Float TankCapacity;

    @Column(name = "tested_tank_capacity")
    private Float testedTankCapacity;

    @NotNull
    @Column(name = "height", nullable = false)
    private Float height;

    @Column(name = "tested_height")
    private Float testedHeight;

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

    @Column(name = "rpm_output")
    private Float rpmOutput;

    @Column(name = "tested_rpm_output")
    private Float testedRpmOutput;

    @NotNull
    @Column(name = "torque", nullable = false)
    private Float torque;

    @Column(name = "tested_torque")
    private Float testedTorque;

    @Column(name = "tank_range")
    private String tankRange;

    @Column(name = "tested_tank_range")
    private String testedTankRange;

    @NotNull
    @Column(name = "fuel_consumption", nullable = false)
    private Float fuelConsumption;

    @Column(name = "tested_fuel_consumption")
    private Float testedFuelConsumption;

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
    @Column(name = "operating_cost_per_tonne", nullable = false)
    private Float operatingCostPerTonne;

    @Column(name = "tested_operating_cost_per_tonne")
    private Float testedOperatingCostPerTonne;

    @Column(name = "fuel_consumption_per_explosive_kg_charged")
    private Float fuelConsumptionPerExplosiveKgCharged;

    @Column(name = "tested_fuel_consumption_per_explosive_kg_charged")
    private Float testedFuelConsumptionPerExplosiveKgCharged;

    @NotNull
    @Column(name = "control_system", nullable = false)
    private String controlSystem;

    @Column(name = "tested_control_system")
    private String testedControlSystem;

    @Column(name = "cycle_time")
    private Float cycleTime;

    @Column(name = "tested_cycle_time")
    private Float testedCycleTime;

    @NotNull
    @Column(name = "turning_radius_inner", nullable = false)
    private Float turningRadiusInner;

    @Column(name = "tested_turning_radius_inner")
    private Float testedTurningRadiusInner;

    @NotNull
    @Column(name = "turning_radius_outer", nullable = false)
    private Float turningRadiusOuter;

    @Column(name = "tested_turning_radius_outer")
    private Float testedTurningRadiusOuter;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "lubrication_type", nullable = false)
    private LubricationType lubricationType;

    @Enumerated(EnumType.STRING)
    @Column(name = "tested_lubrication_type")
    private LubricationType testedLubricationType;

    @Column(name = "temperature_at_ambient")
    private Float temperatureAtAmbient;

    @Column(name = "tested_temperature_at_ambient")
    private Float testedTemperatureAtAmbient;

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

    public StopChargingTool name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public StopChargingTool model(String model) {
        this.model = model;
        return this;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getTechnologyType() {
        return technologyType;
    }

    public StopChargingTool technologyType(String technologyType) {
        this.technologyType = technologyType;
        return this;
    }

    public void setTechnologyType(String technologyType) {
        this.technologyType = technologyType;
    }

    public Integer getTrl() {
        return trl;
    }

    public StopChargingTool trl(Integer trl) {
        this.trl = trl;
        return this;
    }

    public void setTrl(Integer trl) {
        this.trl = trl;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public StopChargingTool photo(byte[] photo) {
        this.photo = photo;
        return this;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public String getPhotoContentType() {
        return photoContentType;
    }

    public StopChargingTool photoContentType(String photoContentType) {
        this.photoContentType = photoContentType;
        return this;
    }

    public void setPhotoContentType(String photoContentType) {
        this.photoContentType = photoContentType;
    }

    public byte[] getDatasheet() {
        return datasheet;
    }

    public StopChargingTool datasheet(byte[] datasheet) {
        this.datasheet = datasheet;
        return this;
    }

    public void setDatasheet(byte[] datasheet) {
        this.datasheet = datasheet;
    }

    public String getDatasheetContentType() {
        return datasheetContentType;
    }

    public StopChargingTool datasheetContentType(String datasheetContentType) {
        this.datasheetContentType = datasheetContentType;
        return this;
    }

    public void setDatasheetContentType(String datasheetContentType) {
        this.datasheetContentType = datasheetContentType;
    }

    public Float getHoseLength() {
        return hoseLength;
    }

    public StopChargingTool hoseLength(Float hoseLength) {
        this.hoseLength = hoseLength;
        return this;
    }

    public void setHoseLength(Float hoseLength) {
        this.hoseLength = hoseLength;
    }

    public Float getTestedHoseLength() {
        return testedHoseLength;
    }

    public StopChargingTool testedHoseLength(Float testedHoseLength) {
        this.testedHoseLength = testedHoseLength;
        return this;
    }

    public void setTestedHoseLength(Float testedHoseLength) {
        this.testedHoseLength = testedHoseLength;
    }

    public Float getHoleDiameter() {
        return holeDiameter;
    }

    public StopChargingTool holeDiameter(Float holeDiameter) {
        this.holeDiameter = holeDiameter;
        return this;
    }

    public void setHoleDiameter(Float holeDiameter) {
        this.holeDiameter = holeDiameter;
    }

    public Float getTestedHoleDiameter() {
        return testedHoleDiameter;
    }

    public StopChargingTool testedHoleDiameter(Float testedHoleDiameter) {
        this.testedHoleDiameter = testedHoleDiameter;
        return this;
    }

    public void setTestedHoleDiameter(Float testedHoleDiameter) {
        this.testedHoleDiameter = testedHoleDiameter;
    }

    public String getExplosiveType() {
        return explosiveType;
    }

    public StopChargingTool explosiveType(String explosiveType) {
        this.explosiveType = explosiveType;
        return this;
    }

    public void setExplosiveType(String explosiveType) {
        this.explosiveType = explosiveType;
    }

    public String getTestedExplosiveType() {
        return testedExplosiveType;
    }

    public StopChargingTool testedExplosiveType(String testedExplosiveType) {
        this.testedExplosiveType = testedExplosiveType;
        return this;
    }

    public void setTestedExplosiveType(String testedExplosiveType) {
        this.testedExplosiveType = testedExplosiveType;
    }

    public Float getTankCapacity() {
        return TankCapacity;
    }

    public StopChargingTool TankCapacity(Float TankCapacity) {
        this.TankCapacity = TankCapacity;
        return this;
    }

    public void setTankCapacity(Float TankCapacity) {
        this.TankCapacity = TankCapacity;
    }

    public Float getTestedTankCapacity() {
        return testedTankCapacity;
    }

    public StopChargingTool testedTankCapacity(Float testedTankCapacity) {
        this.testedTankCapacity = testedTankCapacity;
        return this;
    }

    public void setTestedTankCapacity(Float testedTankCapacity) {
        this.testedTankCapacity = testedTankCapacity;
    }

    public Float getHeight() {
        return height;
    }

    public StopChargingTool height(Float height) {
        this.height = height;
        return this;
    }

    public void setHeight(Float height) {
        this.height = height;
    }

    public Float getTestedHeight() {
        return testedHeight;
    }

    public StopChargingTool testedHeight(Float testedHeight) {
        this.testedHeight = testedHeight;
        return this;
    }

    public void setTestedHeight(Float testedHeight) {
        this.testedHeight = testedHeight;
    }

    public Float getWeight() {
        return weight;
    }

    public StopChargingTool weight(Float weight) {
        this.weight = weight;
        return this;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public Float getTestedWeight() {
        return testedWeight;
    }

    public StopChargingTool testedWeight(Float testedWeight) {
        this.testedWeight = testedWeight;
        return this;
    }

    public void setTestedWeight(Float testedWeight) {
        this.testedWeight = testedWeight;
    }

    public Float getLength() {
        return length;
    }

    public StopChargingTool length(Float length) {
        this.length = length;
        return this;
    }

    public void setLength(Float length) {
        this.length = length;
    }

    public Float getTestedLength() {
        return testedLength;
    }

    public StopChargingTool testedLength(Float testedLength) {
        this.testedLength = testedLength;
        return this;
    }

    public void setTestedLength(Float testedLength) {
        this.testedLength = testedLength;
    }

    public Float getRpmOutput() {
        return rpmOutput;
    }

    public StopChargingTool rpmOutput(Float rpmOutput) {
        this.rpmOutput = rpmOutput;
        return this;
    }

    public void setRpmOutput(Float rpmOutput) {
        this.rpmOutput = rpmOutput;
    }

    public Float getTestedRpmOutput() {
        return testedRpmOutput;
    }

    public StopChargingTool testedRpmOutput(Float testedRpmOutput) {
        this.testedRpmOutput = testedRpmOutput;
        return this;
    }

    public void setTestedRpmOutput(Float testedRpmOutput) {
        this.testedRpmOutput = testedRpmOutput;
    }

    public Float getTorque() {
        return torque;
    }

    public StopChargingTool torque(Float torque) {
        this.torque = torque;
        return this;
    }

    public void setTorque(Float torque) {
        this.torque = torque;
    }

    public Float getTestedTorque() {
        return testedTorque;
    }

    public StopChargingTool testedTorque(Float testedTorque) {
        this.testedTorque = testedTorque;
        return this;
    }

    public void setTestedTorque(Float testedTorque) {
        this.testedTorque = testedTorque;
    }

    public String getTankRange() {
        return tankRange;
    }

    public StopChargingTool tankRange(String tankRange) {
        this.tankRange = tankRange;
        return this;
    }

    public void setTankRange(String tankRange) {
        this.tankRange = tankRange;
    }

    public String getTestedTankRange() {
        return testedTankRange;
    }

    public StopChargingTool testedTankRange(String testedTankRange) {
        this.testedTankRange = testedTankRange;
        return this;
    }

    public void setTestedTankRange(String testedTankRange) {
        this.testedTankRange = testedTankRange;
    }

    public Float getFuelConsumption() {
        return fuelConsumption;
    }

    public StopChargingTool fuelConsumption(Float fuelConsumption) {
        this.fuelConsumption = fuelConsumption;
        return this;
    }

    public void setFuelConsumption(Float fuelConsumption) {
        this.fuelConsumption = fuelConsumption;
    }

    public Float getTestedFuelConsumption() {
        return testedFuelConsumption;
    }

    public StopChargingTool testedFuelConsumption(Float testedFuelConsumption) {
        this.testedFuelConsumption = testedFuelConsumption;
        return this;
    }

    public void setTestedFuelConsumption(Float testedFuelConsumption) {
        this.testedFuelConsumption = testedFuelConsumption;
    }

    public Float getAvailability() {
        return availability;
    }

    public StopChargingTool availability(Float availability) {
        this.availability = availability;
        return this;
    }

    public void setAvailability(Float availability) {
        this.availability = availability;
    }

    public Float getTestedAvailability() {
        return testedAvailability;
    }

    public StopChargingTool testedAvailability(Float testedAvailability) {
        this.testedAvailability = testedAvailability;
        return this;
    }

    public void setTestedAvailability(Float testedAvailability) {
        this.testedAvailability = testedAvailability;
    }

    public Float getOperatingCostPerTonne() {
        return operatingCostPerTonne;
    }

    public StopChargingTool operatingCostPerTonne(Float operatingCostPerTonne) {
        this.operatingCostPerTonne = operatingCostPerTonne;
        return this;
    }

    public void setOperatingCostPerTonne(Float operatingCostPerTonne) {
        this.operatingCostPerTonne = operatingCostPerTonne;
    }

    public Float getTestedOperatingCostPerTonne() {
        return testedOperatingCostPerTonne;
    }

    public StopChargingTool testedOperatingCostPerTonne(Float testedOperatingCostPerTonne) {
        this.testedOperatingCostPerTonne = testedOperatingCostPerTonne;
        return this;
    }

    public void setTestedOperatingCostPerTonne(Float testedOperatingCostPerTonne) {
        this.testedOperatingCostPerTonne = testedOperatingCostPerTonne;
    }

    public Float getFuelConsumptionPerExplosiveKgCharged() {
        return fuelConsumptionPerExplosiveKgCharged;
    }

    public StopChargingTool fuelConsumptionPerExplosiveKgCharged(Float fuelConsumptionPerExplosiveKgCharged) {
        this.fuelConsumptionPerExplosiveKgCharged = fuelConsumptionPerExplosiveKgCharged;
        return this;
    }

    public void setFuelConsumptionPerExplosiveKgCharged(Float fuelConsumptionPerExplosiveKgCharged) {
        this.fuelConsumptionPerExplosiveKgCharged = fuelConsumptionPerExplosiveKgCharged;
    }

    public Float getTestedFuelConsumptionPerExplosiveKgCharged() {
        return testedFuelConsumptionPerExplosiveKgCharged;
    }

    public StopChargingTool testedFuelConsumptionPerExplosiveKgCharged(Float testedFuelConsumptionPerExplosiveKgCharged) {
        this.testedFuelConsumptionPerExplosiveKgCharged = testedFuelConsumptionPerExplosiveKgCharged;
        return this;
    }

    public void setTestedFuelConsumptionPerExplosiveKgCharged(Float testedFuelConsumptionPerExplosiveKgCharged) {
        this.testedFuelConsumptionPerExplosiveKgCharged = testedFuelConsumptionPerExplosiveKgCharged;
    }

    public String getControlSystem() {
        return controlSystem;
    }

    public StopChargingTool controlSystem(String controlSystem) {
        this.controlSystem = controlSystem;
        return this;
    }

    public void setControlSystem(String controlSystem) {
        this.controlSystem = controlSystem;
    }

    public String getTestedControlSystem() {
        return testedControlSystem;
    }

    public StopChargingTool testedControlSystem(String testedControlSystem) {
        this.testedControlSystem = testedControlSystem;
        return this;
    }

    public void setTestedControlSystem(String testedControlSystem) {
        this.testedControlSystem = testedControlSystem;
    }

    public Float getCycleTime() {
        return cycleTime;
    }

    public StopChargingTool cycleTime(Float cycleTime) {
        this.cycleTime = cycleTime;
        return this;
    }

    public void setCycleTime(Float cycleTime) {
        this.cycleTime = cycleTime;
    }

    public Float getTestedCycleTime() {
        return testedCycleTime;
    }

    public StopChargingTool testedCycleTime(Float testedCycleTime) {
        this.testedCycleTime = testedCycleTime;
        return this;
    }

    public void setTestedCycleTime(Float testedCycleTime) {
        this.testedCycleTime = testedCycleTime;
    }

    public Float getTurningRadiusInner() {
        return turningRadiusInner;
    }

    public StopChargingTool turningRadiusInner(Float turningRadiusInner) {
        this.turningRadiusInner = turningRadiusInner;
        return this;
    }

    public void setTurningRadiusInner(Float turningRadiusInner) {
        this.turningRadiusInner = turningRadiusInner;
    }

    public Float getTestedTurningRadiusInner() {
        return testedTurningRadiusInner;
    }

    public StopChargingTool testedTurningRadiusInner(Float testedTurningRadiusInner) {
        this.testedTurningRadiusInner = testedTurningRadiusInner;
        return this;
    }

    public void setTestedTurningRadiusInner(Float testedTurningRadiusInner) {
        this.testedTurningRadiusInner = testedTurningRadiusInner;
    }

    public Float getTurningRadiusOuter() {
        return turningRadiusOuter;
    }

    public StopChargingTool turningRadiusOuter(Float turningRadiusOuter) {
        this.turningRadiusOuter = turningRadiusOuter;
        return this;
    }

    public void setTurningRadiusOuter(Float turningRadiusOuter) {
        this.turningRadiusOuter = turningRadiusOuter;
    }

    public Float getTestedTurningRadiusOuter() {
        return testedTurningRadiusOuter;
    }

    public StopChargingTool testedTurningRadiusOuter(Float testedTurningRadiusOuter) {
        this.testedTurningRadiusOuter = testedTurningRadiusOuter;
        return this;
    }

    public void setTestedTurningRadiusOuter(Float testedTurningRadiusOuter) {
        this.testedTurningRadiusOuter = testedTurningRadiusOuter;
    }

    public LubricationType getLubricationType() {
        return lubricationType;
    }

    public StopChargingTool lubricationType(LubricationType lubricationType) {
        this.lubricationType = lubricationType;
        return this;
    }

    public void setLubricationType(LubricationType lubricationType) {
        this.lubricationType = lubricationType;
    }

    public LubricationType getTestedLubricationType() {
        return testedLubricationType;
    }

    public StopChargingTool testedLubricationType(LubricationType testedLubricationType) {
        this.testedLubricationType = testedLubricationType;
        return this;
    }

    public void setTestedLubricationType(LubricationType testedLubricationType) {
        this.testedLubricationType = testedLubricationType;
    }

    public Float getTemperatureAtAmbient() {
        return temperatureAtAmbient;
    }

    public StopChargingTool temperatureAtAmbient(Float temperatureAtAmbient) {
        this.temperatureAtAmbient = temperatureAtAmbient;
        return this;
    }

    public void setTemperatureAtAmbient(Float temperatureAtAmbient) {
        this.temperatureAtAmbient = temperatureAtAmbient;
    }

    public Float getTestedTemperatureAtAmbient() {
        return testedTemperatureAtAmbient;
    }

    public StopChargingTool testedTemperatureAtAmbient(Float testedTemperatureAtAmbient) {
        this.testedTemperatureAtAmbient = testedTemperatureAtAmbient;
        return this;
    }

    public void setTestedTemperatureAtAmbient(Float testedTemperatureAtAmbient) {
        this.testedTemperatureAtAmbient = testedTemperatureAtAmbient;
    }

    public String getObservations1() {
        return observations1;
    }

    public StopChargingTool observations1(String observations1) {
        this.observations1 = observations1;
        return this;
    }

    public void setObservations1(String observations1) {
        this.observations1 = observations1;
    }

    public String getTestedObservations1() {
        return testedObservations1;
    }

    public StopChargingTool testedObservations1(String testedObservations1) {
        this.testedObservations1 = testedObservations1;
        return this;
    }

    public void setTestedObservations1(String testedObservations1) {
        this.testedObservations1 = testedObservations1;
    }

    public String getObservations2() {
        return observations2;
    }

    public StopChargingTool observations2(String observations2) {
        this.observations2 = observations2;
        return this;
    }

    public void setObservations2(String observations2) {
        this.observations2 = observations2;
    }

    public String getTestedObservations2() {
        return testedObservations2;
    }

    public StopChargingTool testedObservations2(String testedObservations2) {
        this.testedObservations2 = testedObservations2;
        return this;
    }

    public void setTestedObservations2(String testedObservations2) {
        this.testedObservations2 = testedObservations2;
    }

    public String getObservations3() {
        return observations3;
    }

    public StopChargingTool observations3(String observations3) {
        this.observations3 = observations3;
        return this;
    }

    public void setObservations3(String observations3) {
        this.observations3 = observations3;
    }

    public String getTestedObservations3() {
        return testedObservations3;
    }

    public StopChargingTool testedObservations3(String testedObservations3) {
        this.testedObservations3 = testedObservations3;
        return this;
    }

    public void setTestedObservations3(String testedObservations3) {
        this.testedObservations3 = testedObservations3;
    }

    public String getObservations4() {
        return observations4;
    }

    public StopChargingTool observations4(String observations4) {
        this.observations4 = observations4;
        return this;
    }

    public void setObservations4(String observations4) {
        this.observations4 = observations4;
    }

    public String getTestedObservations4() {
        return testedObservations4;
    }

    public StopChargingTool testedObservations4(String testedObservations4) {
        this.testedObservations4 = testedObservations4;
        return this;
    }

    public void setTestedObservations4(String testedObservations4) {
        this.testedObservations4 = testedObservations4;
    }

    public String getObservations5() {
        return observations5;
    }

    public StopChargingTool observations5(String observations5) {
        this.observations5 = observations5;
        return this;
    }

    public void setObservations5(String observations5) {
        this.observations5 = observations5;
    }

    public String getTestedObservations5() {
        return testedObservations5;
    }

    public StopChargingTool testedObservations5(String testedObservations5) {
        this.testedObservations5 = testedObservations5;
        return this;
    }

    public void setTestedObservations5(String testedObservations5) {
        this.testedObservations5 = testedObservations5;
    }

    public String getObservations6() {
        return observations6;
    }

    public StopChargingTool observations6(String observations6) {
        this.observations6 = observations6;
        return this;
    }

    public void setObservations6(String observations6) {
        this.observations6 = observations6;
    }

    public String getTestedObservations6() {
        return testedObservations6;
    }

    public StopChargingTool testedObservations6(String testedObservations6) {
        this.testedObservations6 = testedObservations6;
        return this;
    }

    public void setTestedObservations6(String testedObservations6) {
        this.testedObservations6 = testedObservations6;
    }

    public String getOperators_Comments() {
        return operators_Comments;
    }

    public StopChargingTool operators_Comments(String operators_Comments) {
        this.operators_Comments = operators_Comments;
        return this;
    }

    public void setOperators_Comments(String operators_Comments) {
        this.operators_Comments = operators_Comments;
    }

    public String getTestedOperators_Comments() {
        return testedOperators_Comments;
    }

    public StopChargingTool testedOperators_Comments(String testedOperators_Comments) {
        this.testedOperators_Comments = testedOperators_Comments;
        return this;
    }

    public void setTestedOperators_Comments(String testedOperators_Comments) {
        this.testedOperators_Comments = testedOperators_Comments;
    }

    public Company getOemName() {
        return oemName;
    }

    public StopChargingTool oemName(Company company) {
        this.oemName = company;
        return this;
    }

    public void setOemName(Company company) {
        this.oemName = company;
    }

    public User getAddedBy() {
        return addedBy;
    }

    public StopChargingTool addedBy(User user) {
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
        StopChargingTool stopChargingTool = (StopChargingTool) o;
        if (stopChargingTool.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), stopChargingTool.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "StopChargingTool{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", model='" + getModel() + "'" +
            ", technologyType='" + getTechnologyType() + "'" +
            ", trl='" + getTrl() + "'" +
            ", photo='" + getPhoto() + "'" +
            ", photoContentType='" + photoContentType + "'" +
            ", datasheet='" + getDatasheet() + "'" +
            ", datasheetContentType='" + datasheetContentType + "'" +
            ", hoseLength='" + getHoseLength() + "'" +
            ", testedHoseLength='" + getTestedHoseLength() + "'" +
            ", holeDiameter='" + getHoleDiameter() + "'" +
            ", testedHoleDiameter='" + getTestedHoleDiameter() + "'" +
            ", explosiveType='" + getExplosiveType() + "'" +
            ", testedExplosiveType='" + getTestedExplosiveType() + "'" +
            ", TankCapacity='" + getTankCapacity() + "'" +
            ", testedTankCapacity='" + getTestedTankCapacity() + "'" +
            ", height='" + getHeight() + "'" +
            ", testedHeight='" + getTestedHeight() + "'" +
            ", weight='" + getWeight() + "'" +
            ", testedWeight='" + getTestedWeight() + "'" +
            ", length='" + getLength() + "'" +
            ", testedLength='" + getTestedLength() + "'" +
            ", rpmOutput='" + getRpmOutput() + "'" +
            ", testedRpmOutput='" + getTestedRpmOutput() + "'" +
            ", torque='" + getTorque() + "'" +
            ", testedTorque='" + getTestedTorque() + "'" +
            ", tankRange='" + getTankRange() + "'" +
            ", testedTankRange='" + getTestedTankRange() + "'" +
            ", fuelConsumption='" + getFuelConsumption() + "'" +
            ", testedFuelConsumption='" + getTestedFuelConsumption() + "'" +
            ", availability='" + getAvailability() + "'" +
            ", testedAvailability='" + getTestedAvailability() + "'" +
            ", operatingCostPerTonne='" + getOperatingCostPerTonne() + "'" +
            ", testedOperatingCostPerTonne='" + getTestedOperatingCostPerTonne() + "'" +
            ", fuelConsumptionPerExplosiveKgCharged='" + getFuelConsumptionPerExplosiveKgCharged() + "'" +
            ", testedFuelConsumptionPerExplosiveKgCharged='" + getTestedFuelConsumptionPerExplosiveKgCharged() + "'" +
            ", controlSystem='" + getControlSystem() + "'" +
            ", testedControlSystem='" + getTestedControlSystem() + "'" +
            ", cycleTime='" + getCycleTime() + "'" +
            ", testedCycleTime='" + getTestedCycleTime() + "'" +
            ", turningRadiusInner='" + getTurningRadiusInner() + "'" +
            ", testedTurningRadiusInner='" + getTestedTurningRadiusInner() + "'" +
            ", turningRadiusOuter='" + getTurningRadiusOuter() + "'" +
            ", testedTurningRadiusOuter='" + getTestedTurningRadiusOuter() + "'" +
            ", lubricationType='" + getLubricationType() + "'" +
            ", testedLubricationType='" + getTestedLubricationType() + "'" +
            ", temperatureAtAmbient='" + getTemperatureAtAmbient() + "'" +
            ", testedTemperatureAtAmbient='" + getTestedTemperatureAtAmbient() + "'" +
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
