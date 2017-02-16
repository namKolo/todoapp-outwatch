enablePlugins(ScalaJSPlugin)

name := "Hellooutwatch"

version := "0.1.0"

organization := "Your organization"

scalaVersion := "2.12.1"

jsEnv := PhantomJSEnv().value

libraryDependencies ++= Seq(
  "io.github.outwatch" %%% "outwatch" % "0.7.1",
  "org.scalatest" %%% "scalatest" % "3.0.1" % Test
)
