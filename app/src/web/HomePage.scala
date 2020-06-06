package app.web

import th.logz.LoggerProvider
import zio.Task

import app.util.runZ
import io.vertx.ext.web.RoutingContext

object homePage extends LoggerProvider {
  def handle(rc: RoutingContext): Unit = {
    logger.debug("Enter handle().")
    runZ.runTask(renderHome, rc)
  }

  val renderHome: Task[String] = {
    Task {
      logger.debug("Enter renderHome")

      import scalatags.Text.all._

      val doc = html(lang := "en")(
        head(
          meta(
            charset := "UTF-8",
            name := "viewport",
            content := "width=device-width, initial-scale=1.0"
          ),
          link(
            rel := "stylesheet",
            href := "/static/css/sb-admin-2.css"
          )
        ),
        body(
          div(cls := "container")(
            h1("Have a nice day.")
          )
        )
      )

      "<!DOCTYPE html>" + doc.render
    }
  }
}
