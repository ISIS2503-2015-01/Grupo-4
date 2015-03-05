name := """aura"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.1"

libraryDependencies ++= Seq(
  javaJdbc,
  cache,
  javaWs,
  javaJpa,
  "org.hibernate" % "hibernate-entitymanager" % "4.3.6.Final",
  "org.mindrot" % "jbcrypt" % "0.3m"
)

libraryDependencies += "postgresql" % "postgresql" % "9.1-901-1.jdbc4"

