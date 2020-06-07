package app.web.interaction

import zio.Task
import io.vertx.core.http.HttpServerRequest
import io.vertx.ext.web.RoutingContext

import th.logz.LoggerProvider
import app.model.Todo
import app.repository.todoRepository
import app.util.runZ

object todos extends LoggerProvider {

  def get(rc: RoutingContext) = {
    logger.debug("Get todos")
    val todoList: List[Todo] = todoRepository.todoList.toList
    runZ.runTask(Task(todosView.render(todoList)), rc)
  }

  def post(rc: RoutingContext) = {
    logger.debug("Post todos")
    getSelected(rc)
    ()
  }

  private def getSelected(rc: RoutingContext) = {
    import scala.jdk.CollectionConverters._
    val request: HttpServerRequest = rc.request()

    // wait for the end of reques to process form data
    request.setExpectMultipart(true)
    request.endHandler(_ => {
      val params = request.params()
      logger.trace(
        s"params size: ${params.size()}, names: ${params.names().asScala}"
      )

      val formAttributes = request.formAttributes()
      logger.trace(
        s"form attr size: ${formAttributes.size()}, names: ${formAttributes.names().asScala}"
      )

      val ids: List[String] = formAttributes.getAll("ids[]").asScala.toList
      logger.debug(s"Post ids:${ids}")

      val todoList = todoRepository.todoList
        .filter(todo => ids.contains(todo.id.toString()))
        .toList

      runZ.runTask(Task(todosView.render(todoList)), rc)
    })
  }
}
