package app

import th.logz.LoggerProvider

object main extends LoggerProvider {

  def main(args: Array[String]): Unit = {
    logger.debug("Application started.")

    Thread.currentThread().setUncaughtExceptionHandler(new AppExceptionHandler)

    val config = appConfig.load()
    logger.debug("Configuration loaded.")

    database.start()
    httpServer.start(config)
  }
}

private class AppExceptionHandler
    extends Thread.UncaughtExceptionHandler
    with LoggerProvider {

  logger.debug("Set application exception handler.")

  override def uncaughtException(main: Thread, throwable: Throwable) = {
    logger.error(
      "Exception throwed in main thread. Application aborted.",
      throwable
    )

    throw throwable
  }
}
