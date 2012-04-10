/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package massive.logs;

/**
 *
 * @author hadoop-user
 */
import java.io.IOException;
import java.util.*;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapred.*;
public class WordCount{
    
    public static void main(String[]args) throws IOException{
            JobConf conf = new JobConf(WordCount.class);
            conf.setJobName("wordcount");
            conf.setOutputKeyClass(Text.class);
            conf.setOutputValueClass(IntWritable.class);
            conf.setMapperClass(Map.class);
            conf.setReducerClass(Reduce.class);
            conf.setInputFormat(TextInputFormat.class);
            conf.setOutputFormat(TextOutputFormat.class);
            FileInputFormat.setInputPaths(conf, new Path(args[0]));
            FileOutputFormat.setOutputPath(conf, new Path(args[1]));
            try{
                JobClient.runJob(conf);
            }catch(IOException e){
                System.err.println(e.getMessage());
            }
    }
}