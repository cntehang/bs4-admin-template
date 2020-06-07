package app

import th.logz.LoggerProvider

import io.vertx.core.{AsyncResult, Handler, Vertx}
import io.vertx.core.http.HttpServer
import io.vertx.ext.web.Router

object httpServer extends LoggerProvider {

  def start(config: appConfig.AppConfig): Unit = {

    val vertx = Vertx.vertx()
    logger.debug("Vertx instance created.")

    val router = Router.router(vertx)
    mainRouter.setRoutes(router, config)

    val server = vertx.createHttpServer()
    listen(server, router, config)
  }

  private def listen(
      server: HttpServer,
      router: Router,
      config: appConfig.AppConfig
  ) = {
    val httpPort = config.httpPort
    val listenHandler = new ListenHandler(httpPort)

    server
      .requestHandler(router)
      .listen(httpPort, listenHandler): Unit // : Unit suppress discard result warning
  }
}

private class ListenHandler(port: Int)
    extends Handler[AsyncResult[HttpServer]]
    with LoggerProvider {
  def handle(ready: AsyncResult[HttpServer]): Unit = {
    if (ready.failed()) {
      val cause = ready.cause()
      logger.error("Failed to start http server.", cause)
      throw new RuntimeException(cause)
    } else {
      logger.info(s"Http Server is listening on port: ${port}.")
    }
  }
}
