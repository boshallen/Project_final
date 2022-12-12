package com.book.category;

import java.util.Map;
import java.util.HashMap;;

public class Test {
	public static void main(String[] args) {
		Map map = new HashMap<String,String>();
		map.put("wey", "123");
		Test s = new Test();
		s.test1(map);
		System.out.println(map);
	}
	
	private void test1(Map m) {
		// TODO Auto-generated method stub
		m.put("shuai", "234");
	}
	
}