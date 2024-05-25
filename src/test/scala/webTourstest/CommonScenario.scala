package webTourstest

import io.gatling.core.Predef._
import io.gatling.http.Predef._

object CommonScenario{
  def apply() = new CommonScenario().scn

}

class CommonScenario {

  val scn =  scenario("WebTours")
    .exec(Actions.openRoot)

}
