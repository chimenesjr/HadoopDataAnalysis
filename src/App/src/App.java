package HDA;

import java.io.*;
import java.util.*;
import java.util.Random;
import java.text.*;

import App.src.MapIGTI;
import org.apache.hadoop.conf.*;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapred.*;
import org.apache.hadoop.util.*;


public class App extends Configured implements Tool 
{          
    public static void main (final String[] args) throws Exception {   
      int res = ToolRunner.run(new Configuration(), new App(), args);        
      System.exit(res);           
    }   

    public int run (final String[] args) throws Exception {
      try{ 	             	       	
            JobConf conf = new JobConf(getConf(), App.class);			
            conf.setJobName("Exemplo IGTI - Media");            

            final FileSystem fs = FileSystem.get(conf); 
            Path diretorioEntrada = new Path("Entrada" + UUID.randomUUID().toString()), diretorioSaida = new Path("Saida" + UUID.randomUUID().toString());
            /* Criar um diretorio de entrada no HDFS */
            if (!fs.exists(diretorioEntrada))
                fs.mkdirs(diretorioEntrada);
            /* Adicionar um arquivo para ser processado */
            fs.copyFromLocalFile(new Path("/usr/local/hadoop/Dados/arquivoBigData.txt"), diretorioEntrada);             
            /* Atribuindo os diretorios de Entrada e Saida para o Job */
            FileInputFormat.setInputPaths(conf,  diretorioEntrada); 
            FileOutputFormat.setOutputPath(conf, diretorioSaida);
  
            conf.setOutputKeyClass(Text.class);     
            conf.setOutputValueClass(Text.class);   
            conf.setMapperClass(MapIGTI.class);
            conf.setReducerClass(ReduceIGTI.class);
            JobClient.runJob(conf);   
                                          
        }
        catch ( Exception e ) {
            throw e;
        }
        return 0;
     }
 

    public static class ReduceIGTI extends MapReduceBase implements Reducer<Text, Text, Text, Text> {       
      
       public void reduce (Text key, Iterator<Text> values, OutputCollector<Text, Text> output, Reporter reporter) throws IOException {                                                                                 double media = 0.0; 
            int acumuladorItens = 0, contaVendas = 0;
            Text value = new Text();
        
            while (values.hasNext()) {
                value = values.next();               
                contaVendas++;
                acumuladorItens += Integer.parseInt(value.toString());
            }            
            media = acumuladorItens / new Double(contaVendas);
                          
            value.set(String.valueOf(media)); 
            output.collect(key, value);           
      }            
    
    }
}