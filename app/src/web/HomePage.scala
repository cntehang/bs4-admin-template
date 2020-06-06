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
      html(
        head(
          meta(charset := "utf-8"),
          link(
            rel := "stylesheet",
            href := "/static/bootstrap-4.5.0-dist/css/bootstrap.min.css"
          )
        ),
        body(
          div(cls := "container")(
            h1("Have a nice day.")
          )
        )
      ).render
    }
  }
}
