package com.book.publishingHouse;

import java.io.IOException;
import java.util.Comparator;
import java.util.TreeMap;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;


public class MyPublishingHouseReducer extends Reducer<Text, IntWritable, ReceiveTable, NullWritable> {
 
	
//	@Override
//	protected void setup(Reducer<Text, IntWritable, ReceiveTable, NullWritable>.Context context)
//			throws IOException, InterruptedException {
//		
//		// 再执行reduce之前，首先执行setup方法，把本表清空
//		
//		
//	}
	
	@Override
	protected void reduce(Text key, Iterable<IntWritable> values,
			Reducer<Text, IntWritable, ReceiveTable, NullWritable>.Context context)
			throws IOException, InterruptedException {
		
		int total = 0;
		
		for(IntWritable v : values) {
			total += v.get();
		}
		
		//将数据存入数据库
        ReceiveTable receiveTable = new ReceiveTable(key.toString(),total);
        context.write(receiveTable,null);
	  
	}
	
	
	
}
