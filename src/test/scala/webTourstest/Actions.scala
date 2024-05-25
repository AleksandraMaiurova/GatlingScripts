package webTourstest

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.util.Random


object Actions {
  val openRootwelcome = http("Welcome")
    .get("/cgi-bin/welcome.pl?signOff=true")
  exec(getCookieValue(CookieKey("MSO")))

  val openRootnav = http("Navigation")
    .get("/cgi-bin/nav.pl?in=home")
    .check(regex("""userSession" value="([^"]+)"/>""").exists)
    .check(regex("""userSession" value="([^"]+)"/>""").saveAs("userSession"))


  val login = http("login")
    .post("/cgi-bin/login.pl")
    .formParam("userSession", "#{userSession}")
    .formParam("username", "#{username}")
    .formParam("password", "#{password}")
    .formParam("login.x:", "69")
    .formParam("login.y", "5")
    .formParam("JSFormSubmit", "off")
    .check(
      substring("You've reached this page incorrectly (probably a bad user session value).").notExists
    )

  val openRootmenu = http("mainMenu")
    .get("/cgi-bin/nav.pl?page=menu&in=home")
  exec(getCookieValue(CookieKey("MSO")))


  val flights = http("flights")
    .get("/cgi-bin/reservations.pl?page=welcome")
    .check(regex("""value="([^"]+)">""").findRandom.saveAs("city1"))
    .check(regex("""value="([^"]+)">""").findRandom.saveAs("city2"))



  val reservOne = http("reservOne")
    .post("/cgi-bin/reservations.pl")
    .formParam("advanceDiscount", "0")
    .formParam("depart", "#{city1}")
    .formParam("departDate", "05/26/2025")
    .formParam("arrive", "#{city2}")
    .formParam("returnDate", "05/27/2025")
    .formParam("numPassengers", "1")
    .formParam("seatPref", "None")
    .formParam("seatType", "Coach")
    .formParam("findFlights.x", "43")
    .formParam("findFlights.y", "14")
    .check(regex("""\d+;\d+;\d+/\d+/\d+""").findRandom.saveAs("flightnum"))

  val bookOne = http("bookOne")
    .post("/cgi-bin/reservations.pl")
    .formParam("outboundFlight", "#{flightnum}")
    .formParam("advanceDiscount", "0")
    .formParam("numPassengers", "1")
    .formParam("seatPref", "None")
    .formParam("seatType", "Coach")
    .formParam("findFlights.x", "63")
    .formParam("findFlights.y", "6")

  val bookTripOne = http("bookTrip")
    .post("/cgi-bin/reservations.pl")
    .formParam("firstName", "#{firstName}")
    .formParam("lastName", "#{lastName}")
    .formParam("address1", "")
    .formParam("address2", "")
    .formParam("pass1", "#{firstName} #{lastName}")
    .formParam("creditCard", "")
    .formParam("expDate", "")
    .formParam("oldCCOption", "")
    .formParam("numPassengers", "1")
    .formParam("seatPref", "None")
    .formParam("seatType", "Coach")
    .formParam("outboundFlight", "#{flightnum}")
    .formParam("advanceDiscount", "0")
    .formParam("returnFlight", "")
    .formParam("JSFormSubmit", "off")


  val reservRound = http("reservRound")
    .post("/cgi-bin/reservations.pl")
    .formParam("advanceDiscount", "0")
    .formParam("depart", "#{city1}")
    .formParam("departDate", "05/26/2025")
    .formParam("arrive", "#{city2}")
    .formParam("roundtrip", "on")
    .formParam("returnDate", "05/27/2025")
    .formParam("numPassengers", "1")
    .formParam("seatPref", "None")
    .formParam("seatType", "Coach")
    .formParam("findFlights.x", "43")
    .formParam("findFlights.y", "14")
    .check(regex("""\d+;\d+;\d+/\d+/\d+""").find(Random.between(1, 4)).saveAs("flightnum1"))
    .check(regex("""\d+;\d+;\d+/\d+/\d+""").find(Random.between(5, 8)).saveAs("flightnum2"))

  val bookRound = http("bookRound")
    .post("/cgi-bin/reservations.pl")
    .formParam("outboundFlight", "#{flightnum1}")
    .formParam("returnFlight", "#{flightnum2}")
    .formParam("advanceDiscount", "0")
    .formParam("numPassengers", "1")
    .formParam("seatPref", "None")
    .formParam("seatType", "Coach")
    .formParam("findFlights.x", "63")
    .formParam("findFlights.y", "6")

  val bookTripRound  = http("bookTripRound")
    .post("/cgi-bin/reservations.pl")
    .formParam("firstName", "#{firstName}")
    .formParam("lastName", "#{lastName}")
    .formParam("address1", "")
    .formParam("address2", "")
    .formParam("pass1", "#{firstName} #{lastName}")
    .formParam("creditCard", "")
    .formParam("expDate", "")
    .formParam("oldCCOption", "")
    .formParam("numPassengers", "1")
    .formParam("seatPref", "None")
    .formParam("seatType", "Coach")
    .formParam("outboundFlight", "#{flightnum1}")
    .formParam("returnFlight", "#{flightnum2}")
    .formParam("advanceDiscount", "0")
    .formParam("JSFormSubmit", "off")

  val signOff = http("signOff")
    .get("/cgi-bin/welcome.pl?signOff=1")





}
