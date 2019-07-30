name := "scala-graph-ql-server"

version := "0.1"

scalaVersion := "2.12.8"

//Dependency Versions
val sangria = "1.4.2"
val sangriaSprayJson = "1.0.1"
val akkaHTTP = "10.1.9"

libraryDependencies ++= Seq("org.sangria-graphql" %% "sangria" % sangria,
  "org.sangria-graphql" %% "sangria-spray-json" % sangriaSprayJson,
  "com.typesafe.akka" %% "akka-http"   % akkaHTTP,
  "org.sangria-graphql" %% "sangria-circe" % "1.2.1"
)


