package webTourstest

import io.gatling.core.Predef._
import io.gatling.http.Predef._

class Debug extends Simulation{

  setUp(CommonScenario().inject(
    constantConcurrentUsers(56).during(3600)
      ))
    .protocols(httpProtocol)
    .assertions(forAll.responseTime.max.lt(3000), global.successfulRequests.percent.gt(90))
    .maxDuration(1800000)

//  incrementConcurrentUsers(5)
//    .times(30)
//    .eachLevelLasting(10)
//    .separatedByRampsLasting(10)
//    .startingFrom(0)
}
