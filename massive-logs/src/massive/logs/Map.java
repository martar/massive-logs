/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package massive.logs;

import java.io.IOException;
import java.util.*;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapred.*;
/**
 *
 * @author hadoop-user
 */
public class Map extends MapReduceBase 
        implements Mapper<LongWritable, Text, Text, IntWritable>{
            private final static IntWritable one =  new IntWritable(1);
            private Text word = new Text();
            public void map(LongWritable key, Text value, OutputCollector<Text, IntWritable> output,
                    Reporter reporter) throws IOException{
                            String line = value.toString();
                            StringTokenizer tokenizer = new StringTokenizer(line);
                            while(tokenizer.hasMoreTokens()){
                                    word.set(tokenizer.nextToken());
                                    output.collect(word, one);
                            }
            }
    }
