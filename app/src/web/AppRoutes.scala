package app.web

import io.vertx.ext.web.Router
import th.logz.LoggerProvider

import app.util.runZ

import app.web.main.home
import app.web.interaction.todos

object appRoutes extends LoggerProvider {

  def setRoutes(router: Router) = {
    logger.debug("Set app routes.")

    router.get("/").handler(rc => runZ.runTask(home.makePage(), rc))
    router.get("/todos").handler(rc => runZ.runTask(todos.makePage(), rc))

    logger.debug("App routes are set.")
  }
}
