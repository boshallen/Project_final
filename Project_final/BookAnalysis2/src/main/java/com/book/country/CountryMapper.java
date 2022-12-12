package com.book.country;

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

import com.book.util.CountryUtil;


/**
 * 西班牙 有的是【西】有的是【西班牙】需要处理一下country_jianxie.properties中记录是西班牙
 *荷兰 有的是【荷兰】有的是【荷】 需要处理一下，country_jianxie.properties中记录是荷兰
 *加拿大 有的是【加拿大】有的是【加】 需要处理一下，country_jianxie.properties中记录是加拿大
 *
 *注:当作者字段前面没有国家时默认归属于中国，所以有些书不是中国但是因为没有国家所以会误统计进中国，所以结果造成中国作者数量会偏大。
 */
public class CountryMapper extends Mapper<LongWritable, Text, Text, IntWritable>{
 
 
	public static TreeMap<Float,String> treeMap = new TreeMap<Float,String>();
  
 
	Map<String,String> map = null;
	
	@Override
		protected void setup(Mapper<LongWritable, Text, Text, IntWritable>.Context context)
				throws IOException, InterruptedException {
			 map = new CountryUtil().getCountryDataFromDB();
		}
	
	protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
	 
		IntWritable one = new IntWritable(1);
		//按照^字符对数据进行分割
		String[] words = value.toString().split("\\^"); 
		if(words.length == 10) {//等于10表明数据长度正常
			try {
				String url = words[0];
				String tag = words[1];
				String name = words[2];
				String author = words[3];  //这是第四个字段，是作者，此字符串前面有国家
				Float rating = Float.valueOf(words[4]);
				String price = words[6];
				String publisher = words[7];
				String publish_date = words[8];
				int commentNum = Integer.parseInt(words[9]);
				
				//提取国家
				int previousIndex = author.indexOf("[");
				int laterIndex = author.indexOf("]");
				
				
				String country = null;
				
				if(previousIndex == -1 || laterIndex == -1) {
					//表明没有国家，记做中国
					country = "中国";
				}else {
					//下面的if用来处理【西】【西班牙】和【荷】【荷兰】等这类问题
					country = author.substring(previousIndex+1, laterIndex);
					if(country.equals("西")) {
						country = "西班牙";
					}
					if(country.equals("荷")) {
						country = "荷兰";
					}
					if(country.equals("加")) {
						country = "加拿大";
					}
				}
				
				if(map.containsKey(country)) {
					context.write(new Text(map.get(country)), one);
				}else {
					System.out.println(country + "------------->");
				}
				
			}catch (NumberFormatException e) {
				// TODO: handle exception
				System.out.println("have error about NumberFormat");
			}
		}
	}
}
