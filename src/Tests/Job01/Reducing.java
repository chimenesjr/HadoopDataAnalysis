package Tests.Job01;

import Tests.Helpers.OutputCollector;
import Tests.Helpers.Text;

import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;

public class Reducing {

    public static void main(String[] args) {
        Reducing reducing = new Reducing();

        Text key = new Text("Co. Dublin");

        ArrayList<Text> values = new ArrayList<Text>();
        values.add(new Text("500"));
        values.add(new Text("2200"));
        values.add(new Text("677"));
        values.add(new Text("1342"));

        OutputCollector<Text, Text> output = new OutputCollector<Text, Text>();

        reducing.reduce(key, values.iterator(), output);

        var result = output.list.entrySet().iterator().next();

        System.out.println(result.getKey() + " - " + result.getValue());
    }
    
    public void reduce(Text key, Iterator<Text> values, OutputCollector<Text, Text>output){

        double sum = 0.0;
        int quant = 0;
        Text value = new Text();

        while(values.hasNext())
        {
            sum += Double.parseDouble(values.next().toString());
            quant++;
        }

        var media = sum / quant;
        value.set(Double.toString(media));
        output.collect(key, value);
    }
}
