package app

import th.logz.LoggerProvider

import app.db.{embeddedPg, dbSetup}

object database extends LoggerProvider {
  def start() = {
    val pg = embeddedPg.start()
    logger.info(s"Embedded Pg started at port: ${pg.getPort()}")

    val result = dbSetup.createTableIfNotExists()
    logger.debug(s"createTableIfNotExists result: ${result}")

  }
}
