package vertx

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

class VertxInitialBufferSimulation extends Simulation {

  val httpConf = http
    .baseURL("http://familiabuscape:8083") // Here is the root for all relative URLs
    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8") // Here are the common headers
    .acceptLanguageHeader("en-US,en;q=0.5")
    .userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.8; rv:16.0) Gecko/20100101 Firefox/16.0")
    
    .header("Header1", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce malesuada ligula vulputate mi volutpat, sed mattis neque dictum. Pellentesque sit amet vehicula libero. Sed pellentesque a ex ac venenatis. Maecenas lacus nibh, bibendum in nisl at, feugiat euismod dolor. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Vivamus elementum lectus nisl, at varius mi ornare ac. Nulla fermentum gravida metus vel elementum. Curabitur vehicula interdum orci, nec aliquet dolor rutrum ac.")

    .header("Header2", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed fringilla est sem, sit amet cursus eros efficitur at. Vestibulum eu lectus ligula. Nullam vel ex et quam suscipit commodo at sed sapien. Phasellus id orci ipsum. Etiam facilisis metus sit amet neque maximus, vel imperdiet velit interdum. In vel bibendum sapien. Quisque ullamcorper odio porta leo egestas pulvinar. Pellentesque sodales molestie cursus. In accumsan vel nisl malesuada eleifend. Cras id consectetur risus. Aenean turpis tellus")

    .header("Header3", "Proin pretium orci tellus, et condimentum nisi facilisis in. Proin pellentesque enim odio, eget suscipit eros luctus a. Aenean quis felis lobortis, hendrerit felis vitae, rhoncus tortor. Phasellus et rhoncus arcu, sit amet condimentum orci. Suspendisse dignissim, augue at vulputate egestas, massa sapien dapibus massa, vitae efficitur magna dolor in est. Sed pharetra sapien ac turpis tempor pharetra. Praesent nec ligula et purus cursus auctor. In sed semper erat. Sed nec justo in mi gravida nullam.")

    .header("Header4", "Aenean turpis tellus, suscipit nec laoreet in, hendrerit at lacus. Quisque ornare turpis vel ligula porta mattis. Nulla lobortis, enim sed euismod vestibulum, augue dolor consectetur metus, id pretium nisi sem commodo ipsum. Curabitur vel hendrerit orci. Sed et faucibus augue. Etiam dignissim, nunc at egestas tempus, quam urna porta orci, a porttitor justo ex at quam. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam fringilla elit a tortor aliquam congue. Nulla sit amet pellentesque odio. Nunc justo tellus, eleifend metus.")

    .header("Header5", "ed euismod vestibulum, augue dolor consectetur metus, id pretium nisi sem commodo ipsum. Curabitur vel hendrerit orci. Sed et faucibus augue. Etiam dignissim, nunc at egestas tempus, quam urna porta orci, a porttitor justo ex at quam. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam fringilla elit a tortor aliquam congue. Nulla sit amet pellent Aenean turpis tellus, suscipit nec laoreet in, hendrerit at lacus. Quisque ornare turpis vel ligula porta mattis. Nulla lobortis, enim sed euis")

    .header("Header6", "rci, a porttitor justo ex at quam. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam fringilla elit a tortor aliquam congue. Nulla sit amet pellent ed euismod vestibulum, augue dolor consectetur metus, id pretium nisi sem commodo ipsum. Curabitur vel hendrerit orci. Sed et faucibus augue. Etiam dignissim, nunc at egestas tempus, quam urna porta orci, a porttitor justo ex at quam. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam fringilla elit a tortor aliquam congue. Nulla sit amet pellent")

    .header("Header7", "lor sit amet, consectetur adipiscing elit. Nullam fringilla elit a tortor aliquam congue. Nulla sit amet pellent ed euismod vestibulum, augue dolor consectetur metus, id pretium nisi sem commodo ipsum. Curabitur vel hendrerit orci. Sed et faucibu ed euismod vestibulum, augue dolor consectetur metus, id pretium nisi sem commodo ipsum. Curabitur vel hendrerit orci. Sed et faucibus augue. Etiam dignissim, nunc at egestas tempus, quam urna porta orci, a porttitor justo ex at quam. Lorem ipsum dos augue. Etiam dignissim, ")

    .header("Header8", "Lorem ipsum dos augue. Etiam digniss lor sit amet, consectetur adipiscing elit. Nullam fringilla elit a tortor aliquam congue. Nulla sit amet pellent ed euismod vestibulum, augue dolor consectetur metus, id pretium nisi sem commodo ipsum. Curabitur vel hendrerit orci. Sed et faucibu ed euismod vestibulum, augue dolor consectetur metus, id pretium nisi sem commodo ipsum. Curabitur vel hendrerit orci. Sed et faucibus augue. Etiam dignissim, nunc at egestas tempus, quam urna porta orci, a porttitor justo ex at quamim")

    .header("Header9", "suscipit nec laoreet in, hendrerit at lacus. Quisque ornare turpis vel ligula porta mattis. Nulla lobortis, enim sed euismod vestibulum, augue dolor consectetur metus, id pretium nisi sem commodo ipsum. Curabitur vel hendrerit orci. Sed et faucibus augue. Etiam dignissim, nunc at egestas tempus, quam urna porta orci, a porttitor justo ex at quam. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam fringilla elit a tortor aliquam congue. Nulla sit amet pellentesque odio. Nunc justo tellus, eleifend metus.")

    .header("Header10", "mod vestibulum, augue dolor consectetur metus, id pretium nisi sem commodo ipsum. Curabitur vel hendrerit orci. Sed et faucibus augue. Etiam dignissim, nunc at egestas tempus, quam urna porta orci, a porttitor justo ex at quam. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nullam fringilla elit a tortor aliquam congue. Nulla sit amet pellentesque odio. Nunc justo tellus, eleifend metus.")

    .header("Header11", "In lectus justo, pellentesque vel sem ac, vestibulum congue eros. Nunc sagittis quam non metus dignissim faucibus. Suspendisse nec efficitur dolor. Aliquam eu purus magna. Integer porta ultricies sem, nec suscipit elit tincidunt vel. Duis vestibulum sed eros u")

    .header("Header12", "t porttitor. Phasellus rhoncus massa ut sollicitudin mollis. In ultricies elit imperdiet diam consequat aliquam non ac nibh. Maecenas rhoncus sollicitudin lorem, sit amet ornare lectus vestibulum vel.")

    .header("Header13", "In vitae lectus felis. Donec fermentum mi arcu, quis scelerisque est blandit ut. Donec fringilla cursus dolor, vitae pulvinar quam porta tempus. Nunc dapibus dolor et interdum fermentum.")

    .header("Header14", "ac vulputate ipsum tellus et nisl. Etiam sit amet vehicula leo. Aliquam laoreet est quis scelerisque condimentum. Aliquam ut vestibulum lacus.")

    .header("Header15", "Cras commodo, eros vel semper blandit, quam arcu interdum eros, eu elementum risus justo at nisi. Phasellus viverra nunc eu ex ornare feugiat.")

  val scn = scenario("My scenario").repeat(1000){
    exec(
      http("Vertx")
        .get("/")
        .check(status.is(200))
    )}

  setUp(scn.inject(rampUsers(1000) over (60 seconds)).protocols(httpConf))

}
