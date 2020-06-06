package app.web

import io.vertx.ext.web.Router
import th.logz.LoggerProvider

object appRoutes extends LoggerProvider {

  def setRoutes(router: Router) = {
    logger.debug("Set app routes.")

    router.get("/").handler(homePage.handle(_))

    logger.debug("App routes are set.")
  }
}
