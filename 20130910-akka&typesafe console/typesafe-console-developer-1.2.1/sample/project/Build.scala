package com.typesafe.atmos.sample

import sbt._
import Keys._

object AtmosSample extends Build {
  val Organization = "demo"
  val Version      = "1.2.1"
  val ScalaVersion = "2.10.1"

  lazy val sample = Project(
    id = "atmos-sample",
    base = file("."),
    settings = defaultSettings ++ Seq(
      libraryDependencies ++= Dependencies.tracedAkka,
      scalacOptions += "-language:postfixOps",
      javaOptions in run ++= Seq(
        "-javaagent:../lib/weaver/aspectjweaver.jar",
        "-Dorg.aspectj.tracing.factory=default",
        "-Djava.library.path=../lib/sigar"
      ),
      mainClass in (Compile, run) := Some("demo.Greeting3"),
      Keys.fork in run := true
    )
  )

  lazy val buildSettings = Defaults.defaultSettings ++ Seq(
    organization := Organization,
    version      := Version,
    scalaVersion := ScalaVersion,
    crossPaths   := false
  )

  lazy val defaultSettings = buildSettings ++ Seq(
    resolvers += "Typesafe Repo" at "http://repo.typesafe.com/typesafe/releases/"
  )
}

object Dependencies {

  object V {
    val Akka    = "2.2.0_2.10"
    val Atmos   = "1.2.1"
    val Logback = "1.0.7"
  }

  val atmosTrace = "com.typesafe.atmos" % ("trace-akka-" + V.Akka) % V.Atmos
  val logback    = "ch.qos.logback"     % "logback-classic"        % V.Logback

  val tracedAkka = Seq(atmosTrace, logback)
}
