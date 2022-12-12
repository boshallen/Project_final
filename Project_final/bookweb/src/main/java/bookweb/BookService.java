package bookweb;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {
	
	@Autowired
	private BookDao bookDao;
	
	public List<Map<String,Object>> getCategorys(){
		return bookDao.getCategorys();
	}
	
	public List<Map<String,Object>> getTopRating(){
		return bookDao.getTopRating();
	}
	
	public List<Map<String,Object>> getRatingLow(){
		return bookDao.getRatingLow();
	}
	
	public List<Map<String,Object>> getComment(){
		return bookDao.getComment();
	}
	
	
	public List<Map<String,Object>> getCountry() {
		List<Map<String,Object>> list = bookDao.getCountry();
 
		return list;
	}
	 	
	public List<Map<String,Object>> getBookPublishRating(){
		List<Map<String,Object>> list = bookDao.getBookPublishRating();
		return list;
	}
	
	public List<Map<String,Object>> getBookCatRating(){
		List<Map<String,Object>> list = bookDao.getBookCatRating();
		return list;
	}
}
