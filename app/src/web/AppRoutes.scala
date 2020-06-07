package app.web

import io.vertx.ext.web.Router
import th.logz.LoggerProvider

import app.util.runZ

import app.web.main.home
import app.web.interaction.todos

import app.web.addons._
import app.web.interface._

object appRoutes extends LoggerProvider {

  def setRoutes(router: Router) = {
    logger.debug("Set app routes.")

    router.get("/").handler(rc => runZ.runTask(home.makePage(), rc))
    router.get("/todos").handler(rc => runZ.runTask(todos.makePage(), rc))

    router
      .get("/addons/blank")
      .handler(rc => runZ.runTask(blankPage.makePage(), rc))

    router
      .get("/addons/forgotPassword")
      .handler(rc => runZ.runTask(forgotPassword.makePage(), rc))

    router
      .get("/addons/login")
      .handler(rc => runZ.runTask(login.makePage(), rc))

    router
      .get("/addons/notFound")
      .handler(rc => runZ.runTask(notFound.makePage(), rc))

    router
      .get("/addons/register")
      .handler(rc => runZ.runTask(register.makePage(), rc))

    router
      .get("/addons/charts")
      .handler(rc => runZ.runTask(charts.makePage(), rc))

    router
      .get("/addons/tables")
      .handler(rc => runZ.runTask(tables.makePage(), rc))

    router
      .get("/interface/animations")
      .handler(rc => runZ.runTask(animations.makePage(), rc))

    router
      .get("/interface/borders")
      .handler(rc => runZ.runTask(borders.makePage(), rc))

    router
      .get("/interface/buttons")
      .handler(rc => runZ.runTask(buttons.makePage(), rc))

    router
      .get("/interface/cards")
      .handler(rc => runZ.runTask(cards.makePage(), rc))

    router
      .get("/interface/colors")
      .handler(rc => runZ.runTask(colors.makePage(), rc))

    router
      .get("/interface/other")
      .handler(rc => runZ.runTask(other.makePage(), rc))

    logger.debug("App routes are set.")
  }
}
