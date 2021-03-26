package HDA;

import HDA.Helpers.CountingIterator;

import java.io.IOException;
import java.io.*;
import java.util.*;
import java.util.Random;
import java.text.*;
import java.util.Arrays;

import org.apache.hadoop.conf.*;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapred.*;
import org.apache.hadoop.util.*;

public class JobQuantitativo {

    public static class Map extends MapReduceBase implements Mapper<LongWritable, Text, Text, Text> {

        public void map(LongWritable key, Text value, OutputCollector<Text, Text> output, Reporter reporter) throws IOException {

            var list = Arrays.stream(value.replace("\"", "").split(",")).map(String::trim).toArray(String[]::new);

            var county = list[3];
            var price = list[4];
    
            Text txtChave = new Text();
            Text txtValor = new Text();
    
            txtChave.set(county);
            txtValor.set(price);
    
            output.collect(txtChave, txtValor);

        }
    }

    public static class Reduce extends MapReduceBase implements Reducer<Text, Text, Text, Text> {

        public void reduce (Text key, Iterator<Text> values, OutputCollector<Text, Text> output, Reporter reporter) throws IOException {                                                                                 double media = 0.0;
            
            Text value = new Text();

            var count = CountingIterator.Count(values);
    
            value.set(Integer.toString(count));
            output.collect(key, value);

        }

    }
}