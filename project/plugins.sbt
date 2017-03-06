logLevel := Level.Warn

// play framework
lazy val playResolver = "Typesafe repository" at "https://repo.typesafe.com/typesafe/maven-releases/"
lazy val playPlugin = "com.typesafe.play" % "sbt-plugin" % "2.5.12"

resolvers += playResolver
addSbtPlugin(playPlugin)

// coursier
lazy val coursier = "io.get-coursier" % "sbt-coursier" % "1.0.0-M15"
addSbtPlugin(coursier)