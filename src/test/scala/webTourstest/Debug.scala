package webTourstest

import io.gatling.core.Predef._
import io.gatling.http.Predef._

class Debug extends Simulation{

  setUp(CommonScenario().inject(
    rampConcurrentUsers(1).to(30).during(1200)  ))
    .protocols(httpProtocol)
    .assertions(global.successfulRequests.percent.gt(90))
    .maxDuration(1800000)


}
