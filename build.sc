// for bloop work properly in IDE
import $ivy.`com.lihaoyi::mill-contrib-bloop:$MILL_VERSION`

import mill.Agg
import mill.scalalib.{DepSyntax, ScalaModule}

import $file.`build-scripts`.Definitions
import Definitions.commonModule

trait Common extends ScalaModule {
  def scalaVersion = "2.13.2"

  def scalacOptions = commonModule.scalacOptions

  // for blindsight logging
  def repositories = super.repositories ++ Seq(commonModule.loggingRepository)

  def ivyDeps = Agg(ivy"dev.zio::zio:1.0.0-RC20")

}

object app extends Common {

  def moduleDeps = Seq(logz)

  def ivyDeps = super.ivyDeps() ++ Agg(
    ivy"io.vertx:vertx-web:3.9.1",
    ivy"com.lihaoyi::scalatags:0.8.6",
    ivy"com.github.pureconfig::pureconfig:0.12.3",
    ivy"com.typesafe.scala-logging::scala-logging:3.9.2",
    ivy"com.tersesystems.blindsight::blindsight-logstash:1.0.1",
    ivy"ch.qos.logback:logback-classic:1.2.3",
    ivy"io.getquill::quill-jdbc:3.5.1",
    ivy"org.postgresql:postgresql:42.2.13",
    ivy"com.opentable.components:otj-pg-embedded:0.13.3"
  )

  def stop() = T.command {
    os.proc("mill", "clean", "app.runBackground").call()
  }
}

object logz extends Common {

  def ivyDeps = super.ivyDeps() ++ Agg(
    ivy"org.slf4j:slf4j-api:1.7.30",
    ivy"com.tersesystems.blindsight::blindsight-logstash:1.0.1"
  )
}
