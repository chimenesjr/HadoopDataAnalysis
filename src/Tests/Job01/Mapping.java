package Tests.Job01;

import Tests.Helpers.OutputCollector;
import Tests.Helpers.Text;

public class Mapping {

    public static void main(String[] args) {
        Mapping mapping = new Mapping();
        
        // String row = "\"01/01/2010\",\"5 Braemor Drive, Churchtown, Co.Dublin\",\"500\",\"Dublin\",\"\uFFFD343,000.00\",\"No\",\"No\",\"Second-Hand Dwelling house /Apartment\",\"\"";
        String row = "\"04/01/2010\",\"No. 13, Charlotte Quay\",\"\",\"Limerick\",\"�164,167.00\",\"No\",\"Yes\",\"New Dwelling house /Apartment\",\"greater than or equal to 38 sq metres and less than 125 sq metres\"";
        String r = "";

        var output = new OutputCollector<Text, Text>();
        mapping.map("x", new Text(row), output);

        var result = output.list.entrySet().iterator().next();

        System.out.println(result.getKey() + " - " + result.getValue());
    }

    public void map(String key, Text value, OutputCollector<Text, Text> output) {

        String[] list = value.toString().split("\\\",\\\"");

        String county = list[3].trim();
        String price = list[4].trim().replace("�", "");

        Text txtChave = new Text();
        Text txtValor = new Text();

        txtChave.set(county);
        txtValor.set(price);

        output.collect(txtChave, txtValor);
    }
}
