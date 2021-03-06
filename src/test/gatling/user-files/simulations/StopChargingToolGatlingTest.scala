import _root_.io.gatling.core.scenario.Simulation
import ch.qos.logback.classic.{Level, LoggerContext}
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import org.slf4j.LoggerFactory

import scala.concurrent.duration._

/**
 * Performance test for the StopChargingTool entity.
 */
class StopChargingToolGatlingTest extends Simulation {

    val context: LoggerContext = LoggerFactory.getILoggerFactory.asInstanceOf[LoggerContext]
    // Log all HTTP requests
    //context.getLogger("io.gatling.http").setLevel(Level.valueOf("TRACE"))
    // Log failed HTTP requests
    //context.getLogger("io.gatling.http").setLevel(Level.valueOf("DEBUG"))

    val baseURL = Option(System.getProperty("baseURL")) getOrElse """http://127.0.0.1:8080"""

    val httpConf = http
        .baseURL(baseURL)
        .inferHtmlResources()
        .acceptHeader("*/*")
        .acceptEncodingHeader("gzip, deflate")
        .acceptLanguageHeader("fr,fr-fr;q=0.8,en-us;q=0.5,en;q=0.3")
        .connectionHeader("keep-alive")
        .userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.10; rv:33.0) Gecko/20100101 Firefox/33.0")

    val headers_http = Map(
        "Accept" -> """application/json"""
    )

    val headers_http_authentication = Map(
        "Content-Type" -> """application/json""",
        "Accept" -> """application/json"""
    )

    val headers_http_authenticated = Map(
        "Accept" -> """application/json""",
        "Authorization" -> "${access_token}"
    )

    val scn = scenario("Test the StopChargingTool entity")
        .exec(http("First unauthenticated request")
        .get("/api/account")
        .headers(headers_http)
        .check(status.is(401))).exitHereIfFailed
        .pause(10)
        .exec(http("Authentication")
        .post("/api/authenticate")
        .headers(headers_http_authentication)
        .body(StringBody("""{"username":"admin", "password":"admin"}""")).asJSON
        .check(header.get("Authorization").saveAs("access_token"))).exitHereIfFailed
        .pause(1)
        .exec(http("Authenticated request")
        .get("/api/account")
        .headers(headers_http_authenticated)
        .check(status.is(200)))
        .pause(10)
        .repeat(2) {
            exec(http("Get all stopChargingTools")
            .get("/api/stop-charging-tools")
            .headers(headers_http_authenticated)
            .check(status.is(200)))
            .pause(10 seconds, 20 seconds)
            .exec(http("Create new stopChargingTool")
            .post("/api/stop-charging-tools")
            .headers(headers_http_authenticated)
            .body(StringBody("""{"id":null, "name":"SAMPLE_TEXT", "model":"SAMPLE_TEXT", "technologyType":"SAMPLE_TEXT", "trl":"0", "photo":null, "datasheet":null, "hoseLength":null, "testedHoseLength":null, "holeDiameter":null, "testedHoleDiameter":null, "explosiveType":"SAMPLE_TEXT", "testedExplosiveType":"SAMPLE_TEXT", "TankCapacity":null, "testedTankCapacity":null, "height":null, "testedHeight":null, "weight":null, "testedWeight":null, "length":null, "testedLength":null, "rpmOutput":null, "testedRpmOutput":null, "torque":null, "testedTorque":null, "tankRange":"SAMPLE_TEXT", "testedTankRange":"SAMPLE_TEXT", "fuelConsumption":null, "testedFuelConsumption":null, "availability":null, "testedAvailability":null, "operatingCostPerTonne":null, "testedOperatingCostPerTonne":null, "fuelConsumptionPerExplosiveKgCharged":null, "testedFuelConsumptionPerExplosiveKgCharged":null, "controlSystem":"SAMPLE_TEXT", "testedControlSystem":"SAMPLE_TEXT", "cycleTime":null, "testedCycleTime":null, "turningRadiusInner":null, "testedTurningRadiusInner":null, "turningRadiusOuter":null, "testedTurningRadiusOuter":null, "lubricationType":null, "testedLubricationType":null, "temperatureAtAmbient":null, "testedTemperatureAtAmbient":null, "observations1":null, "testedObservations1":null, "observations2":null, "testedObservations2":null, "observations3":null, "testedObservations3":null, "observations4":null, "testedObservations4":null, "observations5":null, "testedObservations5":null, "observations6":null, "testedObservations6":null, "operators_Comments":null, "testedOperators_Comments":null}""")).asJSON
            .check(status.is(201))
            .check(headerRegex("Location", "(.*)").saveAs("new_stopChargingTool_url"))).exitHereIfFailed
            .pause(10)
            .repeat(5) {
                exec(http("Get created stopChargingTool")
                .get("${new_stopChargingTool_url}")
                .headers(headers_http_authenticated))
                .pause(10)
            }
            .exec(http("Delete created stopChargingTool")
            .delete("${new_stopChargingTool_url}")
            .headers(headers_http_authenticated))
            .pause(10)
        }

    val users = scenario("Users").exec(scn)

    setUp(
        users.inject(rampUsers(Integer.getInteger("users", 100)) over (Integer.getInteger("ramp", 1) minutes))
    ).protocols(httpConf)
}
