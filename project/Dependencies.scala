import Versions._
import sbt._

object Dependencies {
  lazy val core = List(
    "de.svenkubiak" % "jBCrypt" % BcryptVersion
  )
  lazy val testLibs = List(
    "dev.zio" %% "zio-test"     % ZioVersion,
    "dev.zio" %% "zio-test-sbt" % ZioVersion
  ).map(_ % Test)
}
