package webTourstest

import io.gatling.core.Predef._
import io.gatling.http.Predef._



object Actions {
  val openRoot = http("Root Page")
    .get("/")

}
