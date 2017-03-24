package vertx

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

class VertxInitialBufferSimulation extends Simulation {

  val httpConf = http
    .baseURL("http://anothermachine:8083") // Here is the root for all relative URLs
    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8") // Here are the common headers
    .acceptLanguageHeader("en-US,en;q=0.5")
    .userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.8; rv:16.0) Gecko/20100101 Firefox/16.0")
    
    .header("Toto1", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce malesuada ligula vulputate mi volutpat, sed mattis neque dictum. Pellentesque sit amet vehicula libero. Sed pellentesque a ex ac venenatis. Maecenas lacus nibh, bibendum in nisl at, feugiat euismod dolor. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Vivamus elementum lectus nisl, at varius mi ornare ac. Nulla fermentum gravida metus vel elementum. Curabitur vehicula interdum orci, nec aliquet dolor rutrum ac. Proin pretium orci tellus, et condimentum nisi facilisis in. Proin pellentesque enim odio, eget suscipit eros luctus a. Aenean quis felis lobortis, hendrerit felis vitae, rhoncus tortor. Phasellus et rhoncus arcu, sit amet condimentum orci. Suspendisse dignissim, augue at vulputate egestas, massa sapien dapibus massa, vitae efficitur magna dolor in est. Sed pharetra sapien ac turpis tempor pharetra. Praesent nec ligula et purus cursus auctor. In sed semper erat. Sed nec justo in mi gravida nullam.")

    .header("Toto2", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed fringilla est sem, sit amet cursus eros efficitur at. Vestibulum eu lectus ligula. Nullam vel ex et quam suscipit commodo at sed sapien. Phasellus id orci ipsum. Etiam facilisis metus sit amet neque maximus, vel imperdiet velit interdum. In vel bibendum sapien. Quisque ullamcorper odio porta leo egestas pulvinar. Pellentesque sodales molestie cursus. In accumsan vel nisl malesuada eleifend. Cras id consectetur risus. Aenean turpis tellus, suscipit nec laoreet in, hendrerit at lacus. Quisque ornare turpis vel ligula porta mattis. Nulla lobortis, enim sed euismod vestibulum, augue dolor consectetur metus, id pretium nisi sem commodo ipsum. Curabitur vel hendrerit orci. Sed et faucibus augue. Etiam dignissim, nunc at egestas tempus, quam urna porta orci, a porttitor justo ex at quam. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam fringilla elit a tortor aliquam congue. Nulla sit amet pellentesque odio. Nunc justo tellus, eleifend metus.")

  val scn = scenario("My scenario").repeat(10000) {
    exec(
      http("Vertx")
        .get("/")
        .check(status.is(200))
    )
  }

  setUp(scn.inject(atOnceUsers(200)).protocols(httpConf))
}