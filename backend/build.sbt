lazy val scalaTest = "org.scalatest" %% "scalatest" % "3.0.1" % "test"

lazy val backend = (project in file("."))
  .settings(
    name := "news-radar-backend",
    version := "1.0",
    scalaVersion := "2.11.7",
    libraryDependencies += scalaTest
  )