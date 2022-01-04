val scala212         = "2.12.15"
val scala213         = "2.13.7"
val scala3           = "3.1.0"
val allScalaVersions = List(scala212, scala213, scala3)
val crypto4sVersion  = "0.1.0"
val ZioVersion       = "1.0.13"
val BcryptVersion    = "0.4.3"

lazy val crypto4s = (project in file("."))
  .settings(
    name         := "crypto4s",
    version      := crypto4sVersion,
    scalaVersion := scala212,
    libraryDependencies ++= Seq(
      "de.svenkubiak" % "jBCrypt"      % BcryptVersion,
      "dev.zio"      %% "zio-test-sbt" % ZioVersion % Test
    ),
    crossScalaVersions       := allScalaVersions,
    Test / parallelExecution := false,
    testFrameworks += new TestFramework("zio.test.sbt.ZTestFramework")
  )
