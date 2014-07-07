name := "eraser"

version := "1.0"

scalaVersion := "2.10.4"

mainClass := Some("com.zmack.Main")

resolvers += "akka" at "http://repo.akka.io/snapshots"

libraryDependencies ++= Seq(
    "com.netflix.rxjava" % "rxjava-scala" % "0.19.2",
    "net.databinder.dispatch" %% "dispatch-core" % "0.11.1"
)
