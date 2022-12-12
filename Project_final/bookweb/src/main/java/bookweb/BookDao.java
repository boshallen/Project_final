package bookweb;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface BookDao {
	
	//不同类别图书占比
	@Select("select type name,num value from book_category")
	public List<Map<String,Object>> getCategorys();
	
	// 评分分析，评分最高的20本书
	@Select("select name,rating from book_ratingTop")
	public List<Map<String,Object>> getTopRating();
	
	// 评分分析，评分最低的20本书
	@Select("select name,rating from book_ratingLow")
	public List<Map<String,Object>> getRatingLow();
	
	// 评分和评价次数的关系
	@Select("select rating,commentNum from book_comment")
	public List<Map<String,Object>> getComment();
	
	// 世界范围内图书分布情况
	@Select("select country name,num value from book_country")
	public List<Map<String,Object>> getCountry();
	
	// 各出版社和出版的图书评价次数的关系
	@Select("select publisher name, totalRatingNum value from book_publish_rating where totalRatingNum > 800")
	public List<Map<String,Object>> getBookPublishRating();
	
	// 图书类型和评分的关系
	@Select("select category name, AVG(rating) value from book_cat_rating GROUP BY category")
	public List<Map<String,Object>> getBookCatRating();
	
}
