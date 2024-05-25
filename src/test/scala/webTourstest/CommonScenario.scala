package webTourstest

import io.gatling.core.Predef._
import io.gatling.http.Predef._

object CommonScenario {
  def apply() = new CommonScenario().scn

}

class CommonScenario {

  val openRootGroup = group("Root page") {
    exec(Actions.openRootwelcome)
      .exec(Actions.openRootnav)

  }

  val reservOneGroup = group("reservOneGroup") {
    exec(Actions.reservOne)
      .exec(Actions.bookOne)
      .exec(Actions.bookTripOne)


  }

  val reservRoundGroup = group("reservRoundGroup") {
    exec(Actions.reservRound)
      .exec(Actions.bookRound)
      .exec(Actions.bookTripRound)


  }


  val scn = scenario("WebTours")
    .feed(Feeders.Feederlog)
    .feed(Feeders.Feedername)
    .exec(openRootGroup)
    .exec(Actions.login)
    .exec(Actions.openRootmenu)
    .exec(Actions.flights)
    .randomSwitch(
      50.0 -> exec(reservOneGroup),
      50.0 -> exec(reservRoundGroup)
    )
    .exec(Actions.signOff)

}
