package ReportJobs

import org.apache.spark.{SparkContext, SparkConf}

trait TraitJob {
    def Start(conf: SparkConf, sc: SparkContext): Unit
}