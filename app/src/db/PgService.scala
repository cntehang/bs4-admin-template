package app.db

import zio.Task
import com.tersesystems.blindsight.LoggerFactory

import app.model.Todo

import io.getquill.{PostgresJdbcContext, LowerCase}
import com.zaxxer.hikari.{HikariConfig, HikariDataSource}

final case object PgService extends DbService {

  val logger = LoggerFactory.getLogger

  type PgContext = PostgresJdbcContext[LowerCase]

  // db setup neeeds this
  val pgContext: PgContext = {

    val pgDataSource = new org.postgresql.ds.PGSimpleDataSource()
    pgDataSource.setUser("postgres")

    val config = new HikariConfig()
    config.setDataSource(pgDataSource)

    // the two lines work differently if moved out of this UIO constructor
    logger.info("!!!Important PgContext creation")

    new PgContext(
      LowerCase,
      new HikariDataSource(config)
    )
  }

  import pgContext._

  def getTodos(): Task[List[Todo]] = Task {
    logger.debug("db getTodos")
    pgContext.run(query[Todo])
  }

  def getSelected(ids: List[Long]): Task[List[Todo]] = Task {
    logger.debug(s"db getSelected: ${ids}")
    pgContext.run(query[Todo].filter(p => liftQuery(ids).contains(p.id)))
  }

}
