package app.web.interaction

import io.vertx.core.http.HttpServerRequest
import io.vertx.ext.web.RoutingContext

import th.logz.{LoggerProvider, ZLoggerProvider}
import app.util.runZ
import app.db.DbService

object todos extends LoggerProvider with ZLoggerProvider {

  def get(rc: RoutingContext) = {
    logger.debug("Get todos")
    val todoListZ = getAll()
    val task = todoListZ.provideLayer(DbService.pgService)
    runZ.runBlocking(task, rc)
  }

  def post(rc: RoutingContext) = {
    logger.debug("Post todos")
    getSelected(rc)
    ()
  }

  private def getAll() = {
    for {
      todos <- DbService.getTodos()
    } yield (todosView.render(todos))
  }

  private def getSelected(rc: RoutingContext) = {
    import scala.jdk.CollectionConverters._
    val request: HttpServerRequest = rc.request()

    // wait for the end of reques to process form data
    request.setExpectMultipart(true)
    request.endHandler(_ => {
      val formAttributes = request.formAttributes()
      logger.trace(
        s"form attr size: ${formAttributes.size()}, names: ${formAttributes.names().asScala}"
      )

      val idsStr: List[String] = formAttributes.getAll("ids[]").asScala.toList
      logger.debug(s"Post ids:${idsStr}")

      val ids = idsStr.map(_.toLong)
      val todoList = DbService.getSelected(ids).map(todosView.render(_))
      val task = todoList.provideLayer(DbService.pgService)
      runZ.runBlocking(task, rc)
    })
  }
}
