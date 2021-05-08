
package IGTI

import org.apache.spark.{SparkContext, SparkConf}

object MPRApp {
   def main(args: Array[String]) {   
      var conf = new SparkConf().setAppName("IGTI MPRApp")
      var sc = new SparkContext(conf)
      
      val arquivo = sc.textFile("file:///usr/local/HadoopDataAnalysis/spark-igti/arquivoBigData.txt")    
      
      val clientes_vendas = arquivo.map(linha => (linha.substring(58, 61).toInt, linha.substring(76, 84).toDouble)) 
      val grupo = clientes_vendas.reduceByKey((x, y) => (x+y))
      val grupo_ord = grupo.sortByKey()  
      
      grupo_ord.saveAsTextFile("file:///usr/local/HadoopDataAnalysis/spark-igti/resultado");
      
      val input = sc.textFile("file:///usr/local/HadoopDataAnalysis/spark-igti/PPR-ALL.csv")
      val filter = input.take(15)
   }
}
