				package com.book.ratingLow;

import java.io.IOException;
import java.util.Comparator;
import java.util.Map.Entry;
import java.util.*;
 

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Mapper.Context;

public class MyRatingLowMapper extends Mapper<LongWritable, Text, ReceiveTable, NullWritable>{
 
 
	public static TreeMap<Float,String> treeMap = new TreeMap<Float,String>();
  
	public static List list = new ArrayList<Entry<String, Float>>(20); //装评分最高的20本书
	
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
					 
					 treeMap.put(rating, name); if(treeMap.size() > 10) {
					 treeMap.remove(treeMap.lastKey()); }
					 
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
		 
		for(Float v : treeMap.keySet()) {
			ReceiveTable receiveTable = new ReceiveTable(treeMap.get(v),v);
			context.write(receiveTable, null);
			System.out.println("debug --------------> ");
		}
		 
	}
}
