package com.book.category;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Mapper.Context;

public class MyCategoryMapper extends Mapper<LongWritable, Text, Text, IntWritable>{
	
	private Set<String> literatureSet = new HashSet<String>(); //文学集合
	private Set<String> popularSet = new HashSet<String>(); //流行集合
	private Set<String> cultureSet = new HashSet<String>(); //文化集合
	private Set<String> lifeSet = new HashSet<String>(); //生活集合
	private Set<String> managementSet = new HashSet<String>(); //经管集合
	private Set<String> technologySet = new HashSet<String>(); //科技集合
	
	@Override
	protected void setup(Mapper<LongWritable, Text, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {
		/**
		 * 在初始化方法里面定义每种类型 的图书属于那种类别
		 */
		literatureSet.add("小说");
		 literatureSet.add("外国文学");
		 literatureSet.add("文学");
		 literatureSet.add("随笔");
		 literatureSet.add("中国文学");
		 literatureSet.add("经典");
		 literatureSet.add("日本文学");
		 literatureSet.add("散文");
		 literatureSet.add("村上春树");
		 literatureSet.add("诗歌");
		 literatureSet.add("童话");
		 literatureSet.add("王小波");
		 literatureSet.add("杂文");
		 literatureSet.add("古典文学");
		 literatureSet.add("儿童文学");
		 literatureSet.add("名著");
		 literatureSet.add("张爱玲");
		 literatureSet.add("余华");
		 literatureSet.add("当代文学");
		 literatureSet.add("钱钟书");
		 literatureSet.add("外国名著");
		 literatureSet.add("鲁迅");
		 literatureSet.add("诗词");
		 literatureSet.add("茨威格");
		 literatureSet.add("米兰·昆德拉");
		 literatureSet.add("杜拉斯");
		 literatureSet.add("港台");
		 
		 popularSet.add("漫画");
		 popularSet.add("绘本");
		 popularSet.add("推理");
		 popularSet.add("青春");
		 popularSet.add("东野圭吾");
		 popularSet.add("科幻");
		 popularSet.add("言情");
		 popularSet.add("悬疑");
		 popularSet.add("武侠");
		 popularSet.add("韩寒");
		 popularSet.add("日本漫画");
		 popularSet.add("耽美");
		 popularSet.add("亦舒");
		 popularSet.add("推理小说");
		 popularSet.add("三毛");
		 popularSet.add("网络小说");
		 popularSet.add("安妮宝贝");
		 popularSet.add("郭敬明");
		 popularSet.add("穿越");
		 popularSet.add("金庸");
		 popularSet.add("轻小说");
		 popularSet.add("阿加莎·克里斯蒂");
		 popularSet.add("几米");
		 popularSet.add("科幻小说");
		 popularSet.add("魔幻");
		 popularSet.add("青春文学");
		 popularSet.add("张小娴");
		 popularSet.add("幾米");
		 popularSet.add("J.K.罗琳");
		 popularSet.add("高木直子");
		 popularSet.add("古龙");
		 popularSet.add("沧月");
		 popularSet.add("落落");
		 popularSet.add("张悦然");
		 popularSet.add("校园");
		 
		 cultureSet.add("哲学");
		 cultureSet.add("传记");
		 cultureSet.add("文化");
		 cultureSet.add("社会学");
		 cultureSet.add("艺术");
		 cultureSet.add("设计");
		 cultureSet.add("社会");
		 cultureSet.add("政治");
		 cultureSet.add("建筑");
		 cultureSet.add("宗教");
		 cultureSet.add("电影");
		 cultureSet.add("数学");
		 cultureSet.add("政治学");
		 cultureSet.add("回忆录");
		 cultureSet.add("中国历史");
		 cultureSet.add("思想");
		 cultureSet.add("国学");
		 cultureSet.add("音乐");
		 cultureSet.add("人文");
		 cultureSet.add("人物传记");
		 cultureSet.add("绘画");
		 cultureSet.add("戏剧");
		 cultureSet.add("艺术史");
		 cultureSet.add("佛教");
		 cultureSet.add("军事");
		 cultureSet.add("西方哲学");
		 cultureSet.add("二战");
		 cultureSet.add("近代史");
		 cultureSet.add("考古");
		 cultureSet.add("自由主义");
		 cultureSet.add("美术");
 
		 lifeSet.add("爱情");
		 lifeSet.add("旅行");
		 lifeSet.add("生活");
		 lifeSet.add("成长");
		 lifeSet.add("励志");
		 lifeSet.add("心理");
		 lifeSet.add("摄影");
		 lifeSet.add("女性");
		 lifeSet.add("职场");
		 lifeSet.add("美食");
		 lifeSet.add("教育");
		 lifeSet.add("游记");
		 lifeSet.add("灵修");
		 lifeSet.add("健康");
		 lifeSet.add("情感");
		 lifeSet.add("手工");
		 lifeSet.add("两性");
		 lifeSet.add("养生");
		 lifeSet.add("人际关系");
		 lifeSet.add("家居");
		 lifeSet.add("自助游");
		 
		 managementSet.add("经济学");
		 managementSet.add("管理");
		 managementSet.add("经济");
		 managementSet.add("商业");
		 managementSet.add("金融");
		 managementSet.add("投资");
		 managementSet.add("营销");
		 managementSet.add("创业");
		 managementSet.add("理财");
		 managementSet.add("广告");
		 managementSet.add("股票");
		 managementSet.add("企业史");
		 managementSet.add("策划");
		 
		 technologySet.add("科普");
		 technologySet.add("互联网");
		 technologySet.add("编程");
		 technologySet.add("科学");
		 technologySet.add("交互设计");
		 technologySet.add("用户体验");
		 technologySet.add("算法");
		 technologySet.add("web");
		 technologySet.add("科技");
		 technologySet.add("UE");
		 technologySet.add("通信");
		 technologySet.add("交互");
		 technologySet.add("UCD");
		 technologySet.add("神经网络");
		 technologySet.add("程序");
	}
	
	
	protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
	 
		//按照^字符对数据进行分割
		String[] words = value.toString().split("\\^"); 
		
		//过滤掉异常数据
		if(words.length == 10) {
			String type=words[1];
			
			if(literatureSet.contains(type)) {
				context.write(new Text("文学"), new IntWritable(1));
			}else {
				if(popularSet.contains(type)) {
					context.write(new Text("流行"), new IntWritable(1));
				}else {
					if(cultureSet.contains(type)) {
						context.write(new Text("文化"), new IntWritable(1));
					}else {
						if(lifeSet.contains(type)) {
							context.write(new Text("生活"), new IntWritable(1));
						}else {
							if(managementSet.contains(type)) {
								context.write(new Text("经管"), new IntWritable(1));
							}else {
								if(technologySet.contains(type)) {
									context.write(new Text("科技"), new IntWritable(1));
								}else {
									context.write(new Text("其他"), new IntWritable(1));
								}
							}
						}
					}
				}
			}
	 	
		}
		
	}
}
