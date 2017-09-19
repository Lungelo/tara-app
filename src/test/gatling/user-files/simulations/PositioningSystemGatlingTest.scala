import _root_.io.gatling.core.scenario.Simulation
import ch.qos.logback.classic.{Level, LoggerContext}
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import org.slf4j.LoggerFactory

import scala.concurrent.duration._

/**
 * Performance test for the PositioningSystem entity.
 */
class PositioningSystemGatlingTest extends Simulation {

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

    val scn = scenario("Test the PositioningSystem entity")
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
            exec(http("Get all positioningSystems")
            .get("/api/positioning-systems")
            .headers(headers_http_authenticated)
            .check(status.is(200)))
            .pause(10 seconds, 20 seconds)
            .exec(http("Create new positioningSystem")
            .post("/api/positioning-systems")
            .headers(headers_http_authenticated)
            .body(StringBody("""{"id":null, "name":"SAMPLE_TEXT", "technologyType":"SAMPLE_TEXT", "trl":"0", "photo":null, "datasheet":null, "accuracy":null, "coverageArea":"SAMPLE_TEXT", "cost":"0", "requiredInfrastructure":"SAMPLE_TEXT", "marketMaturity":null, "outputData":"SAMPLE_TEXT", "privacy":"SAMPLE_TEXT", "updateRate":null, "systemLatency":"SAMPLE_TEXT", "_interface":"SAMPLE_TEXT", "systemIntegrity":"SAMPLE_TEXT", "robustness":"SAMPLE_TEXT", "availability":null, "continuity":null, "scalability":"SAMPLE_TEXT", "numberOfUsers":"SAMPLE_TEXT", "approval":"SAMPLE_TEXT", "levelOfHybridisation":"SAMPLE_TEXT", "technology":"SAMPLE_TEXT", "measuredQuantity":"SAMPLE_TEXT", "basicMeasuringPrinciple":"SAMPLE_TEXT", "positionAlgorithm":"SAMPLE_TEXT", "signaltype":"SAMPLE_TEXT", "signalWavelength":"SAMPLE_TEXT", "systemArchitecture":"SAMPLE_TEXT", "application":"SAMPLE_TEXT", "coordinateReference":"SAMPLE_TEXT"}""")).asJSON
            .check(status.is(201))
            .check(headerRegex("Location", "(.*)").saveAs("new_positioningSystem_url"))).exitHereIfFailed
            .pause(10)
            .repeat(5) {
                exec(http("Get created positioningSystem")
                .get("${new_positioningSystem_url}")
                .headers(headers_http_authenticated))
                .pause(10)
            }
            .exec(http("Delete created positioningSystem")
            .delete("${new_positioningSystem_url}")
            .headers(headers_http_authenticated))
            .pause(10)
        }

    val users = scenario("Users").exec(scn)

    setUp(
        users.inject(rampUsers(Integer.getInteger("users", 100)) over (Integer.getInteger("ramp", 1) minutes))
    ).protocols(httpConf)
}
