/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package massive.logs.sort;

import java.io.IOException;
import massive.logs.Map;
import massive.logs.Reduce;
import massive.logs.WordCount;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.*;
import org.apache.hadoop.mapred.jobcontrol.Job;

/**
 *
 * @author hadoop-user
 */
public class LogEntryAnalizer {
    
    public static void main(String[] args) throws Exception {

	if (args.length != 2) {
		System.err.println("Usage: loganalyzer <in> <out>");
		System.exit(2);
	}

        JobConf conf = new JobConf(LogEntryAnalizer.class);
        conf.setJobName("loganalizer");
        conf.setOutputKeyClass(Text.class);
        conf.setOutputValueClass(IntWritable.class);
        conf.setMapperClass(LogEntryMapper.class);
        conf.setReducerClass(LogEntryReducer.class);
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
