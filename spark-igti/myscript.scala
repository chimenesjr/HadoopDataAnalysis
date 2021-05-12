
package IGTI

import org.apache.spark.{SparkContext, SparkConf}
import org.apache.spark.sql.SparkSession

object MPRApp {
   def main(args: Array[String]) {   
      var conf = new SparkConf().setAppName("IGTI MPRApp")
      var sc = new SparkContext(conf)
      
      val arquivo = sc.textFile("file:///usr/local/HadoopDataAnalysis/spark-igti/arquivoBigData.txt")
      
      val clientes_vendas = arquivo.map(linha => (linha.substring(58, 61).toInt, linha.substring(76, 84).toDouble)) 
      val grupo = clientes_vendas.reduceByKey((x, y) => (x+y))
      val grupo_ord = grupo.sortByKey()  
      
      grupo_ord.saveAsTextFile("file:///usr/local/HadoopDataAnalysis/spark-igti/resultado");
      
      val spark = SparkSession
         .builder()
         .appName("Spark SQL basic example")
         .config("spark.some.config.option", "some-value")
         .getOrCreate()
      }

      val input = spark.read.csv("file:///usr/local/PPR-ALL.csv")
      input.printSchema
   }
}
