package IGTI

import org.apache.spark.{SparkContext, SparkConf}

object MPRApp {
   def main(args: Array[String]) {   
        var conf = new SparkConf().setAppName("IGTI MPRApp")
        var sc = new SparkContext(conf)
        
        val arquivo = sc.textFile("hdfs://localhost:54310/entrada/arquivoBigData.txt")    
        
        val clientes_vendas = arquivo.map(linha => (linha.substring(58, 61).toInt, linha.substring(76, 84).toDouble)) 
        val grupo = clientes_vendas.reduceByKey((x, y) => (x+y))
        val grupo_ord = grupo.sortByKey()  
       
        grupo_ord.saveAsTextFile("hdfs://localhost:54310/entrada/resultado");    
   }
}
