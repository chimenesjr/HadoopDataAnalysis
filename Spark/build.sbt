name := "Report Jobs" 

version := "0.0.1" 

scalaVersion := "2.10.4" 

// libraryDependencies ++= Seq("org.apache.spark" %% "spark-core" % "2.1.0" % "provided")

val sparkVersion = "2.2.0"

libraryDependencies ++= Seq(
        "org.apache.spark" %% "spark-core" % sparkVersion,
        "org.apache.spark" %% "spark-sql" % sparkVersion
    )