package com.book.country;

import java.io.IOException;
import java.util.Comparator;
import java.util.TreeMap;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import com.book.category.ReceiveTable;


public class CountryReducer extends Reducer<Text, IntWritable, ReceiveTable, NullWritable> {
 
	
	@Override
	protected void reduce(Text key, Iterable<IntWritable> values,
			Reducer<Text, IntWritable, ReceiveTable, NullWritable>.Context context)
			throws IOException, InterruptedException {
		int sum = 0;
		for (IntWritable value : values) {
			sum += value.get();
		}
		
		 //将数据存入数据库
        ReceiveTable receiveTable = new ReceiveTable(key.toString(),sum);
        context.write(receiveTable,null);
	}
	
}
