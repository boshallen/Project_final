package com.book.ratingTop;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.HashMap;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Mapper.Context;

import com.book.ratingLow.ReceiveTable;
 
public class MyRatingTopMapper extends Mapper<LongWritable, Text, ReceiveTable, NullWritable>{
	 
	HashMap<String,Float> map = new HashMap<String,Float>(); 
	
	
	protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
	 
		//按照^字符对数据进行分割
		String[] words = value.toString().split("\\^"); 
		if(words.length == 10) {//等于10表明数据长度正常
			try {
				String url = words[0];
				String tag = words[1];
				String name = words[2];
				String author = words[3];
				Float rating = Float.valueOf(words[4]);
				String price = words[6];
				String publisher = words[7];
				String publish_date = words[8];
				int commentNum = Integer.parseInt(words[9]);
				if(commentNum >= 1000) { //只要评论数大于1000次的数据
					
					float min = 100;
					String minKey = "";
					 map.put(name, rating);
					 if(map.size() > 20) {
						for(String v:map.keySet()) {
							 if(map.get(v) < min) {
								 min = map.get(v);
								 minKey = v ;
							 }
						}
						map.remove(minKey);
					 }
				}
			}catch (NumberFormatException e) {
				// TODO: handle exception
				System.out.println("have error about NumberFormat");
			}
		}
	}
	@Override
	protected void cleanup(Context context)
			throws IOException, InterruptedException {
		 
		for(String v : map.keySet()) {
			ReceiveTable receiveTable = new ReceiveTable(v,map.get(v));
			context.write(receiveTable, null);
			System.out.println("debug --------------> ");
		}
		 
	}
}
