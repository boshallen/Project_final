package com.book.comment;

import java.io.IOException;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeMap;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Mapper.Context;

public class MyCommentMapper extends Mapper<LongWritable, Text, Text, Text>{
	 
//	Book book = new Book();
	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, Text>.Context context)
			throws IOException, InterruptedException {
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
				if(commentNum >= 20000) {  //评论次数大于20000次的图书  
					context.write(new Text(name),new Text(""+rating + ","+ commentNum));
				 
				}
			}catch (NumberFormatException e) {
				// TODO: handle exception
				System.out.println("have error about NumberFormat");
			}
		}
	}
	 
}
