package app.web

import th.logz.LoggerProvider
import zio.Task

import app.util.runZ
import io.vertx.ext.web.RoutingContext

import app.web.main.home

object homePage extends LoggerProvider {
  def handle(rc: RoutingContext): Unit = {
    logger.debug("Enter handle().")
    runZ.runTask(renderHome, rc)
  }

  val renderHome: Task[String] = Task(home.makePage)
}
