package webTourstest

import io.gatling.core.Predef._
import io.gatling.http.Predef._

class Debug extends Simulation{

  setUp(CommonScenario().inject(
      incrementUsersPerSec((1).toInt)
        // Количество ступеней
        .times(7)
        // Длительность полки
        .eachLevelLasting(25)
        // Длительность разгона
        .separatedByRampsLasting(5)
        // Начало нагрузки с
        .startingFrom(0),

  ))
    .protocols(httpProtocol)
    .assertions(forAll.responseTime.max.lt(3000), global.successfulRequests.percent.gt(90))
    .maxDuration(1800000)


//  constantConcurrentUsers(56).during(3600)
}
