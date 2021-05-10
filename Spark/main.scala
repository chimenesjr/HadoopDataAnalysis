
package ReportJobs

import org.apache.spark.{SparkContext, SparkConf}
import TraitJob
import JobMedia

object ReportJobs {
    def main(args: Array[String]) {   
        
        var conf = new SparkConf().setAppName("Report Jobs")
        var sc = new SparkContext(conf)

        var jobmedia = new JobMedia
        jobmedia.Start(conf, sc)
    }
}