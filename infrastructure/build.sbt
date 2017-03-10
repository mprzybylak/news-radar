lazy val slick = "com.typesafe.slick" %% "slick" % "3.1.0"
lazy val slf4jnop = "org.slf4j" % "slf4j-nop" % "1.6.4"
lazy val sclikhikaricp = "com.typesafe.slick" %% "slick-hikaricp" % "3.2.0"
lazy val h2driver = "com.h2database" % "h2" % "1.4.177"

lazy val infrastructure = (project in file("."))
  .settings(
    name := "news-radar-infrastructure",
    version := "1.0",
    scalaVersion := "2.11.7",
    libraryDependencies ++= Seq(slick, slf4jnop, sclikhikaricp, h2driver)
  )