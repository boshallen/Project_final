package com.book.bookTypeAndRating;

import java.io.IOException;
import java.util.Comparator;
import java.util.TreeMap;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;



public class MyBookTypeAndRatingReducer extends Reducer<Text, FloatWritable, ReceiveTable, NullWritable> {
 
	
//	@Override
//	protected void setup(Reducer<Text, IntWritable, ReceiveTable, NullWritable>.Context context)
//			throws IOException, InterruptedException {
//		
//		// 再执行reduce之前，首先执行setup方法，把本表清空
//		
//		
//	}
	
	@Override
	protected void reduce(Text key, Iterable<FloatWritable> values,
			Reducer<Text, FloatWritable, ReceiveTable, NullWritable>.Context context)
			throws IOException, InterruptedException {
		  
		for(FloatWritable v : values) {
			//将数据存入数据库
	        ReceiveTable receiveTable = new ReceiveTable(key.toString(), v.get());
			context.write(receiveTable,null);
		}
		
		
	  
	}
	
	
	
}
