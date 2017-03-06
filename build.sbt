// project
name := "news-radar"
version := "1.0"

// environment
scalaVersion := "2.11.7"

// dependencies
lazy val scalaTest = "org.scalatest" %% "scalatest" % "3.0.1" % "test"

libraryDependencies += scalaTest

// plugins
lazy val root = (project in file(".")).enablePlugins(PlayScala)