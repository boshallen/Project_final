package com.book.publishingHouse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.HashMap;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Mapper.Context;

import com.book.ratingLow.ReceiveTable;
 
public class MyPublishingHouseMapper extends Mapper<LongWritable, Text, Text, IntWritable>{
	 
	HashMap<String,Float> map = new HashMap<String,Float>(); 
	
	
	protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
	 
		IntWritable i = new IntWritable(1);
		
		
		//按照^字符对数据进行分割
		String[] words = value.toString().split("\\^"); 
		if(words.length == 10) {//等于10表明数据长度正常
			try {
				String url = words[0]; 
				String tag = words[1]; // 标签，例如 小说
				String name = words[2]; // 书名
				String author = words[3]; // 作者
				Float rating = Float.valueOf(words[4]); // 评分
				String price = words[6]; // 价格
				String publisher = words[7]; // 出版社
				String publish_date = words[8]; // 出版时间
				int commentNum = Integer.parseInt(words[9]); // 评价次数
				
				// 拿取出版社和评分的数据传到 reduce中
				
				context.write(new Text(publisher), i);
				
				
			}catch (NumberFormatException e) {
				// TODO: handle exception
				System.out.println("have error about NumberFormat");
			}
		}
	}
	/*
	 * @Override protected void cleanup(Context context) throws IOException,
	 * InterruptedException {
	 * 
	 * for(String v : map.keySet()) { ReceiveTable receiveTable = new
	 * ReceiveTable(v,map.get(v)); context.write(receiveTable, null);
	 * System.out.println("debug --------------> "); }
	 * 
	 * }
	 */
}
