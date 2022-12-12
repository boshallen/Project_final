package com.book.category;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;


public class MyCategoryReducer extends Reducer<Text, IntWritable, ReceiveTable, NullWritable> {
	
	
	@Override
	protected void reduce(Text key, Iterable<IntWritable> values,
			Reducer<Text, IntWritable, ReceiveTable, NullWritable>.Context context)
			throws IOException, InterruptedException {
		//统计每种类别的总数
		 //定义初始量为0
        int sum = 0;
        for (IntWritable value : values) {
            sum += value.get();
        }
        
      //将数据存入数据库
        ReceiveTable receiveTable = new ReceiveTable(key.toString(),sum);
        context.write(receiveTable,null);
	}
	
}
