lazy val root = (project in file("."))
  .settings(
    name := "news-radar",
    version := "1.0",
    scalaVersion := "2.11.7"
  )
  .enablePlugins(PlayScala)
  .aggregate(backend)
  .dependsOn(backend)

lazy val backend = project in file("backend")

lazy val infrastructure = project in file("infrastructure")