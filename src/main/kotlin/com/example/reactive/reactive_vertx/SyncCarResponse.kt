package com.example.reactive.reactive_vertx

import io.vertx.core.Handler
import io.vertx.ext.web.RoutingContext

class SyncCarResponse : Handler<RoutingContext> {
  override fun handle(rtx: RoutingContext?) {
    val response = rtx?.response()
    response?.end("Vert.X Hello")
  }
}
