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

      val headFrag = head(
        meta(
          charset := "UTF-8",
          name := "viewport",
          content := "width=device-width, initial-scale=1.0, shrink-to-fit=no"
        ),
        link(
          rel := "stylesheet",
          href := "/public/css/sb-admin-2.css"
        ),
        link(
          href := "/public/lib/fontawesome-free/css/all.min.css",
          rel := "stylesheet",
          `type` := "text/css"
        ),
        link(
          href := "https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i",
          rel := "stylesheet"
        )
      )

      val scripts = Seq[Frag](
        script(src := "/public/lib/jquery-3.5.1/jquery.min.js"),
        script(src := "/public/lib/bootstrap-4.5.0/js/bootstrap.bundle.min.js"),
        script(src := "/public/lib/jquery-easing-1.4.2/jquery.easing.min.js"),
        script(src := "/public/js/sb-admin-2.min.js"),
        // individual page scripts
        script(src := "/public/lib/chart.js-2.9.3/Chart.js"),
        script(src := "/public/js/demo/chart-area-demo.js"),
        script(src := "/public/js/demo/chart-pie-demo.js"),
        script(src := "/public/js/todo/todo-demo.js")
      )

      val bodyFrag = body(
        div(cls := "container")(
          h1("Have a nice day."),
          scripts
        )
      )

      val htmlFrag = html(lang := "en")(
        headFrag,
        bodyFrag
      )

      "<!DOCTYPE html>" + htmlFrag.render
    }
  }
}
