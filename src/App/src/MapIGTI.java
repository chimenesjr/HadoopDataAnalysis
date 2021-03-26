package HDA;

import java.io.IOException;

public static class MapIGTI extends MapReduceBase implements Mapper<LongWritable, Text, Text, Text> {

    public void map(LongWritable key, Text value, OutputCollector<Text, Text> output, Reporter reporter)  throws IOException {
        Text txtChave = new Text();
        Text txtValor = new Text();

        String codigoCliente = value.toString().substring(58, 61);
        String qtdeItens = value.toString().substring(76, 84);
        txtChave.set(codigoCliente);
        txtValor.set(qtdeItens);

        output.collect(txtChave, txtValor);

    }
}
