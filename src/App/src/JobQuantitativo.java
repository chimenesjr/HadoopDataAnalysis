package HDA;

import HDA.Helpers.CountingIterator;

import java.io.IOException;
import java.io.*;
import java.util.*;
import java.util.Random;
import java.text.*;
import java.util.Arrays;
import java.util.Iterator;

import org.apache.hadoop.conf.*;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapred.*;
import org.apache.hadoop.util.*;

public class JobQuantitativo {

    public static class Map extends MapReduceBase implements Mapper<LongWritable, Text, Text, Text> {

        public void map(LongWritable key, Text value, OutputCollector<Text, Text> output, Reporter reporter) throws IOException {

            System.out.println("***************** JOB QUANTITATIVO MAP STARTED ***************");
            System.out.println("VALUE: " + value.toString());

            // String[] list = value.toString().split("\\\",\\\"");
            String[] list = value.toString().split("\",\"");

            System.out.println("Quant: " + list.length);
            System.out.println("Part 3: " + list[3]);
            System.out.println("Part 4: " + list[4]);

            String county = list[3].trim();
            String price = list[4].trim();
    
            Text txtChave = new Text();
            Text txtValor = new Text();
    
            txtChave.set(county);
            txtValor.set(price);
    
            output.collect(txtChave, txtValor);

            System.out.println("***************** JOB QUANTITATIVO MAP FINISHED ***************");

        }
    }

    public static class Reduce extends MapReduceBase implements Reducer<Text, Text, Text, Text> {

        public void reduce (Text key, Iterator<Text> values, OutputCollector<Text, Text> output, Reporter reporter) throws IOException {                                                                                 double media = 0.0;
            
            System.out.println("***************** JOB QUANTITATIVO REDUCE STARTED ***************");

            Text value = new Text();

            int count = CountingIterator.Count(values);
    
            value.set(Integer.toString(count));
            output.collect(key, value);

            System.out.println("***************** JOB QUANTITATIVO REDUCE FINISHED ***************");

        }

    }
}