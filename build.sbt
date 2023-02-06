import Dependencies._

ThisBuild / scalaVersion     := "2.13.10"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "com.example"
ThisBuild / organizationName := "example"

lazy val root = (project in file("."))
  .settings(
    name := "caliban-zio-http-logging",
    libraryDependencies += munit % Test,
    libraryDependencies += "com.github.ghostdogpr" %% "caliban" % "2.0.2",
    libraryDependencies += "com.github.ghostdogpr" %% "caliban-zio-http"   % "2.0.2", // routes for zio-http
    libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.4.5",
    libraryDependencies += "ch.qos.logback" % "logback-classic"% "1.4.5",
    libraryDependencies += "dev.zio" %% "zio-logging" % "2.1.8",
    libraryDependencies += "dev.zio" %% "zio-logging-slf4j" % "2.1.8",
    libraryDependencies += "dev.zio" %% "zio-http" % "0.0.4"
  )

// See https://www.scala-sbt.org/1.x/docs/Using-Sonatype.html for instructions on how to publish to Sonatype.
