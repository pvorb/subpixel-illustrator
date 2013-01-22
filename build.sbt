import AssemblyKeys._

name := "subpixel-illustrator"

organization := "de.vorb"

version := "1.0.1-RELEASE"

scalaVersion := "2.10.0"


libraryDependencies += "org.scala-lang" % "scala-swing" % "2.10.0"


assemblySettings

mainClass in assembly := Some("de.vorb.subpixelillustrator.SubpixelIllustrator")
