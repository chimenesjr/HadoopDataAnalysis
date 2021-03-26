package Tests.Job01;

import Tests.Helpers.OutputCollector;
import Tests.Helpers.Text;
import java.util.Arrays;

public class Mapping {

    public static void main(String[] args) {
        Mapping mapping = new Mapping();
        
        String row = "\"01/01/2010\",\"5 Braemor Drive, Churchtown, Co.Dublin\",\"500\",\"Dublin\",\"\uFFFD343,000.00\",\"No\",\"No\",\"Second-Hand Dwelling house /Apartment\",\"\"";

        var output = new OutputCollector<Text, Text>();
        mapping.map("x", row, output);

        var result = output.list.entrySet().iterator().next();

        System.out.println(result.getKey() + " - " + result.getValue());
    }

    public void map(String key, String value, OutputCollector<Text, Text> output) {

        String[] list = value.replace("\"", "").split(",");

        String county = list[3].trim();
        String price = list[4].trim();

        Text txtChave = new Text();
        Text txtValor = new Text();

        txtChave.set(county);
        txtValor.set(price);

        output.collect(txtChave, txtValor);
    }
}
