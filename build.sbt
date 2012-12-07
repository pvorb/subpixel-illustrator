import AssemblyKeys._

name := "subpixel-illustrator"

organization := "de.vorb"

version := "0.0.0-SNAPSHOT"

scalaVersion := "2.9.2"


libraryDependencies += "org.scala-lang" % "scala-swing" % "2.9.2"


// deploy runnable jar file
assemblySettings

jarName in assembly := "subpixel-illustrator.jar"
