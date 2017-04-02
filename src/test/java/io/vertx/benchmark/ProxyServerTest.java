package io.vertx.benchmark;

import io.netty.handler.codec.http.HttpRequest;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpClientRequest;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

@RunWith(VertxUnitRunner.class)
public class ProxyServerTest {

    private Vertx vertx;

    int port;

    @Before
    public void setUp(TestContext context) throws IOException {

        port = 8080;
        DeploymentOptions option = new DeploymentOptions()
            .setConfig(new JsonObject().put("http.port", port));
        vertx = Vertx.vertx();

        vertx.deployVerticle(ProxyServer.class.getName(),
                context.asyncAssertSuccess());
        vertx.deployVerticle(EchoServer.class.getName(),
                context.asyncAssertSuccess());
    }

    @After
    public void tearDown(TestContext context) {
        vertx.close(context.asyncAssertSuccess());
    }

    @Test
    public void testMyApplication(TestContext context) {
        final Async async = context.async();

        HttpClientRequest req = vertx.createHttpClient().post(port, "localhost", "/",
                response -> response.handler(body -> {
                    System.out.println("Received response: " + body.toString());
                    context.assertTrue(body.toString().contains("Hello"));
                    async.complete();
                }));

        req.end("Hello");
    }
}
