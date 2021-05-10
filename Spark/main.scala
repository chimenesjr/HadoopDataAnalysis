
package ReportJobs

import org.apache.spark.{SparkContext, SparkConf}

object ReportJobs {
    def main(args: Array[String]) {   
        
        var conf = new SparkConf().setAppName("Report Jobs")
        var sc = new SparkContext(conf)
        var fileRdd = sc.textFile("file:///usr/local/PPR-ALL.csv")

        var jobmedia = new JobMedia(conf, sc)
        jobmedia.Start(conf, sc, fileRdd)
    }
}
