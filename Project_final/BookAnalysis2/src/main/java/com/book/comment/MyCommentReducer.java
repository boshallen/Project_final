package com.book.comment;

import java.io.IOException;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;


public class MyCommentReducer extends Reducer<Text, Text, ReceiveTable, NullWritable> {
 
	TreeMap<String,String> treeMap = new TreeMap<String, String>();
	
	 @Override
	protected void reduce(Text name, Iterable<Text> value, Context content)
			throws IOException, InterruptedException {
		 
		 for (Text v : value) {
			treeMap.put(name.toString(), v.toString());
		 }
		 
	}
	 
	@Override
	protected void cleanup(Reducer<Text, Text, ReceiveTable, NullWritable>.Context context)
			throws IOException, InterruptedException {
		
		for(Entry<String, String> v :treeMap.entrySet()) {
			String[] vs = v.getValue().split(",");
			ReceiveTable receiveTable = new ReceiveTable(v.getKey(),Float.valueOf(vs[0]),Integer.valueOf(vs[1]));
			context.write(receiveTable, null);
		}
	 
	}
	 
	 
}
