lazy val slick = "com.typesafe.slick" %% "slick" % "3.1.0"
lazy val slf4jnop = "org.slf4j" % "slf4j-nop" % "1.6.4"
lazy val sclikhikaricp = "com.typesafe.slick" %% "slick-hikaricp" % "3.2.0"
lazy val h2driver = "com.h2database" % "h2" % "1.4.177"
lazy val scalaTest = "org.scalatest" %% "scalatest" % "3.0.1" % "test"
lazy val javaxInject = "javax.inject" % "javax.inject" % "1"

lazy val angularJs = "org.webjars" % "angularjs" % "1.6.2"
lazy val angularMaterial =  "org.webjars" % "angular-material" % "1.1.3"

lazy val commonSettings  = Seq(
  scalaVersion := "2.11.7"
)

lazy val backend = (project in file("backend"))
  .settings(
    commonSettings,
    name := "news-radar-backend",
    version := "1.0",
    libraryDependencies ++= Seq(scalaTest, javaxInject)
  )

lazy val infrastructure = (project in file("infrastructure"))
  .settings(
    commonSettings,
    name := "news-radar-infrastructure",
    version := "1.0",
    libraryDependencies ++= Seq(slick, slf4jnop, sclikhikaricp)
  )
  .aggregate(backend)
  .dependsOn(backend)

lazy val daoh2 = (project in file("dao-h2"))
  .settings(
    commonSettings,
    name := "news-radard-dao-h2",
    version := "1.0",
    libraryDependencies ++= Seq(slick, slf4jnop, sclikhikaricp, h2driver)
  )
  .aggregate(infrastructure)
  .dependsOn(infrastructure)

lazy val gui = (project in file("gui"))
  .settings(
    commonSettings,
    name := "news-radar-gui",
    version := "1.0",
    routesGenerator := InjectedRoutesGenerator,
    libraryDependencies ++= Seq(angularJs, angularMaterial)
  )
  .enablePlugins(PlayScala)
  .dependsOn(backend)
  .aggregate(backend)

lazy val root = (project in file("."))
  .settings(
    commonSettings,
    name := "news-radar",
    version := "1.0"
  )
  .enablePlugins(PlayScala)
  .dependsOn(gui, backend, infrastructure, daoh2)
  .aggregate(gui, backend, infrastructure, daoh2)