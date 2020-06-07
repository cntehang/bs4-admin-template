package app.db

import zio.{Has, Task, RIO, ZIO, ULayer, ZLayer}

import app.model.Todo

import th.logz.ZLoggerProvider

trait DbService {
  def getTodos(): Task[List[Todo]]
  def getSelected(ids: List[Long]): Task[List[Todo]]
}

object DbService extends ZLoggerProvider {

  // accessor methods, the method signature is different:
  // 1. add the current Has[Service] as R
  // 2. remove the effect type of the return type A
  def getTodos(): RIO[Has[DbService], List[Todo]] =
    zLog.debug("in getTodos") *>
      ZIO.accessM(_.get.getTodos())

  def getSelected(ids: List[Long]): RIO[Has[DbService], List[Todo]] =
    zLog.debug("in getSelected") *>
      ZIO.accessM(_.get.getSelected(ids))

  val pgService: ULayer[Has[DbService]] =
    ZLayer.succeed(PgService)
}
