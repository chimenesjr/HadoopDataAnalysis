
package ReportJobs

import org.apache.spark.{SparkContext, SparkConf}
import org.apache.spark.rdd.RDD

object ReportJobs {
    def main(args: Array[String]) {   
        
        var conf = new SparkConf().setAppName("Report Jobs")
        var sc = new SparkContext(conf)
        var fileRdd = sc.textFile("file:///usr/local/PPR-ALL.csv")

        var jobmedia = new JobMedia()
        jobmedia.Start(conf, sc, fileRdd)
    }
}
