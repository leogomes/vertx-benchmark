package io.vertx.benchmark;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.http.*;

/**
 * Basic Proxy Server
 */
public class ProxyServer extends AbstractVerticle {

    @Override
    public void start(Future<Void> fut) {

        HttpServerOptions options = new HttpServerOptions()
                .setInitialBufferSizeHttpDecoder(config().getInteger("initial.buffer", 128));

        HttpClientOptions clientOptions = new HttpClientOptions()
                .setInitialBufferSizeHttpDecoder(config().getInteger("client.initial.buffer", 128))
                .setMaxPoolSize(config().getInteger("client.max.pool.size", 1000));

        Integer clientPort = config().getInteger("client.http.port", 9000);
        String clientHost = config().getString("client.http.host", "localhost");
        String clientPostUrl = config().getString("client.http.post.url", "/");

        HttpClient client = vertx.createHttpClient(clientOptions);

        vertx
                .createHttpServer(options)
                .requestHandler(req -> {
                    HttpClientRequest clientRequest = client.request(req.method(), clientPort, clientHost,
                            clientPostUrl,
                            clientResponse -> {
                                HttpServerResponse resp = req.response();
                                clientResponse.headers().forEach(h -> resp.putHeader(h.getKey(), h.getValue()));
                                clientResponse.bodyHandler(b -> {
                                    resp.end(b);
                                });
                            }
                    );

                    // Adds all headers of the original request to the client request
                    req.headers().forEach( h ->  clientRequest.putHeader(h.getKey(), h.getValue()));
                    // Forward body of the original request
                    req.bodyHandler(b -> {
                        clientRequest.end(b);
                    });
                })
                .listen(
                        // Retrieve the port from the configuration,
                        // default to 8080.
                        config().getInteger("http.port", 8080),
                        result -> {
                            if (result.succeeded()) {
                                fut.complete();
                            } else {
                                fut.fail(result.cause());
                            }
                        }
                );
    }
}
