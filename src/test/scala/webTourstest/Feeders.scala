package webTourstest

import io.gatling.core.Predef._
import io.gatling.http.Predef._

object Feeders {
  val Feederlog = csv("login.csv").circular
  val Feedername = csv("name.csv").random

}
