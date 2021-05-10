
package ReportJobs

import org.apache.spark.{SparkContext, SparkConf}

object ReportJobs {
    def main(args: Array[String]) {   
        
        var conf = new SparkConf().setAppName("Report Jobs")
        var sc = new SparkContext(conf)

        var jobmedia = new JobMedia(conf, sc)
        jobmedia.Start()
    }
}
