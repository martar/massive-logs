/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package massive.logs;

import java.io.IOException;
import java.util.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapred.*;

/**
 *
 * @author hadoop-user
 */
    public class Reduce extends MapReduceBase implements Reducer<Text, IntWritable, Text, IntWritable>{
            public void reduce(Text key, Iterator<IntWritable> values,
                    OutputCollector<Text, IntWritable> output, Reporter reporter)
                    throws IOException{
                            int sum = 0;
                            while (values.hasNext()){
                                    sum += values.next().get();
                            }
                            output.collect(key, new IntWritable(sum));
            }
    }