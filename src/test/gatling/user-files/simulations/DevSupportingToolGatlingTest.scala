import _root_.io.gatling.core.scenario.Simulation
import ch.qos.logback.classic.{Level, LoggerContext}
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import org.slf4j.LoggerFactory

import scala.concurrent.duration._

/**
 * Performance test for the DevSupportingTool entity.
 */
class DevSupportingToolGatlingTest extends Simulation {

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

    val scn = scenario("Test the DevSupportingTool entity")
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
            exec(http("Get all devSupportingTools")
            .get("/api/dev-supporting-tools")
            .headers(headers_http_authenticated)
            .check(status.is(200)))
            .pause(10 seconds, 20 seconds)
            .exec(http("Create new devSupportingTool")
            .post("/api/dev-supporting-tools")
            .headers(headers_http_authenticated)
            .body(StringBody("""{"id":null, "name":"SAMPLE_TEXT", "model":"SAMPLE_TEXT", "technologyType":"SAMPLE_TEXT", "trl":"0", "photo":null, "datasheet":null, "typeOfMachine":"SAMPLE_TEXT", "testedTypeOfMachine":"SAMPLE_TEXT", "weight":null, "testedWeight":null, "length":null, "testedLength":null, "width":null, "testedWidth":null, "powerRatingPercussion":null, "testedPowerRatingPercussion":null, "noiseLevelAtOperatorStand":null, "testedNoiseLevelAtOperatorStand":null, "holeSizeRange":"SAMPLE_TEXT", "testedHoleSizeRange":"SAMPLE_TEXT", "drillWaterConsumption":null, "testedDrillWaterConsumption":null, "waterConsumptionPerMetreDrilled":null, "testedWaterConsumptionPerMetreDrilled":null, "powerSource":"SAMPLE_TEXT", "testedPowerSource":"SAMPLE_TEXT", "trammingSpeed":null, "testedTrammingSpeed":null, "boltLengthRange":"SAMPLE_TEXT", "testedBoltLengthRange":"SAMPLE_TEXT", "minimumOperatingHeight":null, "testedMinimumOperatingHeight":null, "drillSpeed":null, "testedDrillSpeed":null, "gradeability":null, "testedGradeability":null, "numberOfBooms":null, "testedNumberOfBooms":null, "typeOfBoom":"SAMPLE_TEXT", "testedTypeOfBoom":"SAMPLE_TEXT", "outerTurningRadius":null, "testedOuterTurningRadius":null, "nnerTurningRadius":null, "testedInnerTurningRadius":null, "observations1":null, "testedObservations1":null, "observations2":null, "testedObservations2":null, "observations3":null, "testedObservations3":null, "observations4":null, "testedObservations4":null, "observations5":null, "testedObservations5":null, "observations6":null, "testedObservations6":null, "operators_Comments":null, "testedOperators_Comments":null}""")).asJSON
            .check(status.is(201))
            .check(headerRegex("Location", "(.*)").saveAs("new_devSupportingTool_url"))).exitHereIfFailed
            .pause(10)
            .repeat(5) {
                exec(http("Get created devSupportingTool")
                .get("${new_devSupportingTool_url}")
                .headers(headers_http_authenticated))
                .pause(10)
            }
            .exec(http("Delete created devSupportingTool")
            .delete("${new_devSupportingTool_url}")
            .headers(headers_http_authenticated))
            .pause(10)
        }

    val users = scenario("Users").exec(scn)

    setUp(
        users.inject(rampUsers(Integer.getInteger("users", 100)) over (Integer.getInteger("ramp", 1) minutes))
    ).protocols(httpConf)
}
