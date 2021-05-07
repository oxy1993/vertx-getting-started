package com.example.reactive.reactive_vertx

import io.vertx.core.AbstractVerticle
import io.vertx.core.Future
import io.vertx.core.http.HttpMethod
import io.vertx.ext.web.Router
import io.vertx.ext.web.handler.CorsHandler;

class MainVerticle : AbstractVerticle() {

  override fun start(startFuture: Future<Void>) {

    val startTime = System.currentTimeMillis()

    val server = vertx.createHttpServer()

    val router = Router.router(vertx)

    val allowedHeaders: MutableSet<String> = HashSet()
    allowedHeaders.add("x-requested-with")
    allowedHeaders.add("Access-Control-Allow-Origin")
    allowedHeaders.add("origin")
    allowedHeaders.add("Content-Type")
    allowedHeaders.add("accept")

    val allowedMethods: MutableSet<HttpMethod> = HashSet()
    allowedMethods.add(HttpMethod.GET)
    allowedMethods.add(HttpMethod.POST)
    allowedMethods.add(HttpMethod.DELETE)
    allowedMethods.add(HttpMethod.PATCH)
    allowedMethods.add(HttpMethod.OPTIONS)
    allowedMethods.add(HttpMethod.PUT)

    router.route().handler(
      CorsHandler.create(".*.")
        .allowedHeaders(allowedHeaders)
        .allowedMethods(allowedMethods)
    )

    router.route("/hello")
      .produces("application/json")
      .handler(SyncCarResponse())
      .failureHandler {
        println("car error synchron response\n")
        it.response().end("car error synchron response\n")
      }

    server.requestHandler(router).listen(8083) { http ->
      if (http.succeeded()) {
        startFuture.complete()
        val startTimeDone = System.currentTimeMillis() - startTime
        println("HTTP server started on port 8083 in $startTimeDone ms on event loop thread ${Thread.currentThread()}")
      } else {
        startFuture.fail(http.cause());
      }
    }
  }
}
