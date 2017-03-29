import sbt._
import sbt.Keys._
import sbtrelease.ReleasePlugin
import com.typesafe.sbt.pgp.PgpKeys

/** Adds common settings automatically to all subprojects */
object GlobalPlugin extends AutoPlugin {

  val org = "com.judopay.avro4s"

  val AvroVersion = "1.8.1"
  val Log4jVersion = "1.2.17"
  val ScalatestVersion = "3.0.0"
  val ScalaVersion = "2.12.1"
  val Slf4jVersion = "1.7.12"

  override def requires = ReleasePlugin
  override def trigger = allRequirements
  override def projectSettings = publishingSettings ++ Seq(
    organization := org,
    scalaVersion := ScalaVersion,
    crossScalaVersions := Seq("2.11.8", "2.12.1"),
    resolvers += Resolver.mavenLocal,
    parallelExecution in Test := false,
    scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8", "-Ywarn-unused-import",
      "-Xfatal-warnings", "-feature", "-language:existentials"
    ),
    javacOptions := Seq("-source", "1.7", "-target", "1.7"),
    libraryDependencies ++= Seq(
      "org.scala-lang"    % "scala-reflect"     % scalaVersion.value,
      "org.apache.avro"   % "avro"              % AvroVersion,
      "org.slf4j"         % "slf4j-api"         % Slf4jVersion,
      "log4j"             % "log4j"             % Log4jVersion          % "test",
      "org.slf4j"         % "log4j-over-slf4j"  % Slf4jVersion          % "test",
      "org.scalatest"     %% "scalatest"        % ScalatestVersion      % "test"
    )
  )

  val publishingSettings = Seq(
    publishMavenStyle := true,
    publishArtifact in Test := false,
    ReleasePlugin.autoImport.releasePublishArtifactsAction := PgpKeys.publishSigned.value,
    ReleasePlugin.autoImport.releaseCrossBuild := true,
    publishTo := Some("Artifactory Realm" at "http://192.168.43.183:8081/artifactory/libs-snapshot-local/"),
    credentials += Credentials("Artifactory Realm", "192.168.43.183", "admin", "password")

  )
}