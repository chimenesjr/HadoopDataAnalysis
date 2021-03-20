package Tests;

import Tests.Helpers.OutputCollector;
import Tests.Helpers.Text;

public class Map_test {
    public static void main(String[] args) {
        System.out.println("Hello World!");

        Mapping mapping = new Mapping();

        mapping.map("x", "u", new OutputCollector<Text, Text>());
    }
}
