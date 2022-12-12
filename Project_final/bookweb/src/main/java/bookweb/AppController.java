package bookweb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {
		
	@Autowired
	private BookService bookService;
	
	//不同类别图书占比
	@RequestMapping(value = "/getCategory")
	public List<Map<String,Object>> getCategorys(){
		List<Map<String,Object>> list = bookService.getCategorys();
		
		return list;
	}
	
	// 评分分析，评分最高的20本书
	@RequestMapping(value = "/getTopRating")
	public Map getTopRating(){
		
		List<Map<String,Object>> list = bookService.getTopRating();
		List key = new ArrayList();
		List val = new ArrayList();
		Map returnMap = new HashMap<String, List>();
		for(Map<String,Object> m:list) {
			key.add(m.get("name"));
			val.add(m.get("rating"));	
		}
		returnMap.put("key", key);
		returnMap.put("val", val);
		return returnMap;
	}
	
	// 评分分析，评分最低的20本书
	@RequestMapping(value = "/getLowRating")
	public Map getLowRating(){
		List<Map<String,Object>> list = bookService.getRatingLow();
		List key = new ArrayList();
		List val = new ArrayList();
		Map returnMap = new HashMap<String, List>();
		for(Map<String,Object> m:list) {
			key.add(m.get("name"));
			val.add(m.get("rating"));	
		}
		returnMap.put("key", key);
		returnMap.put("val", val);
		return returnMap;
	}
	
	// 评分和评价次数的关系
	@RequestMapping(value = "/getComment")
	public List<List> getComment(){
		List<Map<String,Object>> list = bookService.getComment();
		
		List<List> returnList = new ArrayList<List>();
		for(Map<String,Object> m:list) {
			List i = new ArrayList<Float>();
			i.add(m.get("commentNum"));
			i.add(m.get("rating"));
			returnList.add(i);
		}
		
		return returnList;
	}
	
	
	
	// 世界范围内图书分布情况
	@RequestMapping(value = "/getCountry")
	public List<Map<String,Object>> getCountry(){
		 
		return bookService.getCountry();
	}
	
	// 各出版社和出版的图书评价次数的关系
	@RequestMapping(value = "/getPublishingHouse")
	public Map getPublishingHouse(){
		List<Map<String,Object>> list = bookService.getBookPublishRating();	
		
		List<String> keyList = new ArrayList<>();
		for(Map<String,Object> m:list) {
			keyList.add(m.get("name").toString());
		}
		
		Map<String, List> returnMap = new HashMap<>();
		returnMap.put("val", list);
		returnMap.put("key", keyList);
		return returnMap;
	}
	// 图书类型和评分的关系
	@RequestMapping(value = "/getBookTypeAndRating")
	public Map getBookTypeAndRating(){
		List<Map<String,Object>> list = bookService.getBookCatRating();		
		
		List<String> keyList = new ArrayList<String>();
		List<String> valueList = new ArrayList<String>();
		
		for(Map<String,Object> m:list) {
			keyList.add(m.get("name").toString());
			valueList.add(m.get("value").toString());
		}
		Map<String, List> returnMap = new HashMap<>();
		returnMap.put("val", valueList);
		returnMap.put("key", keyList);	
		
		return returnMap;
	}
}
