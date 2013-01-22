name := "subpixel-illustrator"

organization := "de.vorb"

version := "1.0.0-RELEASE"

scalaVersion := "2.10.0"


libraryDependencies += "org.scala-lang" % "scala-swing" % "2.10.0"


com.github.retronym.SbtOneJar.oneJarSettings

mainClass in oneJar := Some("de.vorb.subpixelillustrator.SubpixelIllustrator")
