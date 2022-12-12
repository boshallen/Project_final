package com.book.bookTypeAndRating;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.filecache.DistributedCache;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.db.DBConfiguration;
import org.apache.hadoop.mapreduce.lib.db.DBOutputFormat;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;

import com.book.util.DBUtil;


/**
 *  评价次数在50000以上的图书的(评价次数高的数据才有可信度)
 *    图书类型和评分的关系
 */
public class App {
	
	//配置连接数据库的相关参数
	public static String driverClass = DBUtil.driverClass;
    public static String dbUrl = DBUtil.dbUrl;
    public static String userName = DBUtil.userName;
    public static String passwd = DBUtil.passwd;
    
    public static String tableName = "book_cat_rating";
//    public static String [] fields = {"name","rating","url","author","publisher","publish_date","price","commentNum","tag"};
    public static String [] fields = {"category","rating"}; //  出版社，本出版社的图书的评价次数
   
    public static void main(String[] args) throws Exception{
    	
    	Configuration conf = new Configuration();
    	DBConfiguration.configureDB(conf,driverClass,dbUrl,userName,passwd);
    	//获取job信息
    	Job job = Job.getInstance(conf);
    	
    	job.setJarByClass(App.class);
    	//设置map输出数据类型
        job.setMapOutputValueClass(FloatWritable.class);
        job.setMapOutputKeyClass(Text.class);
        
        //只用mapper即可完成分析，所以设置reduce为0
//        job.setNumReduceTasks(0);
        
        //关联自定义的mapper reducer
        job.setMapperClass(MyBookTypeAndRatingMapper.class);
        job.setReducerClass(MyBookTypeAndRatingReducer.class);
        
        // 添加mysql数据库jar
        //job.addArchiveToClassPath(new Path("/jar/mysql-connector-java-5.1.41.jar"));
        
        //设置数据输入和输出文件的路径
        FileInputFormat.setInputPaths(job,new Path("hdfs://192.168.36.128:9870/tools/bookinfo.txt"));
        DBOutputFormat.setOutput(job,tableName,fields);

      //提交代码
      boolean b = job.waitForCompletion(true);
      System.exit(b ? 0:1);
        
	}
    
}
