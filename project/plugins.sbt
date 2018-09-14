resolvers += Classpaths.sbtPluginReleases
resolvers += "Era7 maven releases" at "https://s3-eu-west-1.amazonaws.com/releases.era7.com"
addSbtPlugin("com.typesafe.sbt" % "sbt-pgp" % "0.8.3")

addSbtPlugin("com.github.gseitz" % "sbt-release" % "1.0.0")

addSbtPlugin("ohnosequences" % "sbt-s3-resolver" % "0.16.0")