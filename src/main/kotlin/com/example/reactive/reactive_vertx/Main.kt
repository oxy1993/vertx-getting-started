package com.example.reactive.reactive_vertx

import io.vertx.core.DeploymentOptions
import io.vertx.core.Vertx

fun main(args: Array<String>) {
  val vertx = Vertx.vertx()
  vertx.deployVerticle({ MainVerticle() }, DeploymentOptions().setInstances(1))
}
