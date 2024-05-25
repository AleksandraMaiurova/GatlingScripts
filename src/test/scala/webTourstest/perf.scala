import io.gatling.core.Predef._
import io.gatling.http.Predef._


package object webTourstest{

  val httpProtocol = http
    .baseUrl("http://webtours.load-test.ru:1080/webtours/")
    .acceptEncodingHeader("gzip, deflate")
    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
    .userAgentHeader(" Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/124.0.0.0 Safari/537.36")
    .contentTypeHeader("application/x-www-form-urlencoded")
    .connectionHeader("keep-alive")

}


