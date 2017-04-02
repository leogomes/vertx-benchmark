package io.vertx.benchmark;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.http.HttpServerOptions;

/**
 * Echo server
 */
public class EchoServer extends AbstractVerticle {

    @Override
    public void start(Future<Void> startFuture) throws Exception {
        HttpServerOptions options = new HttpServerOptions();
        options.setPort(9000);
        vertx
                .createHttpServer(options)
                .requestHandler(req -> {
                    req.headers().forEach(h -> {
                        if (h.getKey().startsWith("Header")) {
                            req.response().putHeader(h.getKey(), h.getValue());
                        }
                    });
                    req.bodyHandler(b -> {
                        req.response().end(b);
                    });
                })
                .listen( result -> {
                    if (result.succeeded()) {
                        startFuture.complete();
                    } else {
                        startFuture.fail(result.cause());
                    }
                });
    }
}
