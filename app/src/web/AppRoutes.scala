package app.web

import io.vertx.ext.web.Router
import th.logz.LoggerProvider

import app.util.runZ
import app.web.main.home

object appRoutes extends LoggerProvider {

  def setRoutes(router: Router) = {
    logger.debug("Set app routes.")

    router.get("/").handler(rc => runZ.runTask(home.makePage(), rc))

    logger.debug("App routes are set.")
  }
}
