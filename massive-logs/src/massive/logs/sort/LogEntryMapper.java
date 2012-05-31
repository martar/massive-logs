/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package massive.logs.sort;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.naming.Context;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapred.*;

public class LogEntryMapper extends MapReduceBase 
        implements Mapper<Object, Text, Text, IntWritable> {

  private final static IntWritable one = new IntWritable(1);
  private Text url = new Text();

  private Pattern p = Pattern.compile("(?:GET|POST)\\s([^\\s]+)");

    @Override
  public void map(Object key, Text value, OutputCollector<Text, IntWritable> output, Reporter reporter) throws IOException {
	// split log file to individual lines
        String[] entries = value.toString().split("\r?\n");
	for (int i=0, len=entries.length; i<len; i+=1) {
		Matcher matcher = p.matcher(entries[i]);
		if (matcher.find()) {
			url.set(matcher.group(1));
			output.collect(url, one);
		}
	}
  }

}


