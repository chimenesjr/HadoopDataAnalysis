package HDA;

import java.io.*;
import java.util.*;

import org.apache.hadoop.conf.*;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapred.*;
import org.apache.hadoop.util.*;


public class App extends Configured implements Tool {
    public static void main(final String[] args) throws Exception {
        int res = ToolRunner.run(new Configuration(), new App(), args);
        System.exit(res);
    }

    public int run(final String[] args) throws Exception {
        try {
            JobConf conf = new JobConf(getConf(), App.class);

            configFolder(conf);

            conf.setOutputKeyClass(Text.class);
            conf.setOutputValueClass(Text.class);
            conf.setMapperClass(HDA.JobQuantitativo.Map.class);
            conf.setReducerClass(HDA.JobQuantitativo.Reduce.class);
            JobClient.runJob(conf);

        } catch (Exception e) {
            throw e;
        }
        return 0;
    }

    public void configFolder(JobConf conf) {

        final FileSystem fs = FileSystem.get(conf);
        Path diretorioEntrada = new Path("DataIn" + UUID.randomUUID().toString()), diretorioSaida = new Path("DataOut" + UUID.randomUUID().toString());

        if (!fs.exists(diretorioEntrada))
            fs.mkdirs(diretorioEntrada);

        fs.copyFromLocalFile(new Path("/usr/local/hadoop/App/data/PPR-ALL.csv"), diretorioEntrada);

        FileInputFormat.setInputPaths(conf, diretorioEntrada);
        FileOutputFormat.setOutputPath(conf, diretorioSaida);

    }
}