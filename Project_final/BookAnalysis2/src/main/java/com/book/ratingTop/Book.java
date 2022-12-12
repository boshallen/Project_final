package com.book.ratingTop;


/**
 * 每条图书评价的实体类
 *
 *
 */
public class Book {
	private int id = -1;
	private String num = "";
	private float rating;
	private String author = "";
	private String publisher = "";
	private String publish_date = "";
	private float price =  0F;
	private int commentNum = 0;
	private String tag = "";
	
	
	
	@Override
	public String toString() {
		return "Book [id=" + id + ", num=" + num + ", rating=" + rating + ", author=" + author + ", publisher="
				+ publisher + ", publish_date=" + publish_date + ", price=" + price + ", commentNum=" + commentNum
				+ ", tag=" + tag + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public float getRating() {
		return rating;
	}
	public void setRating(float rating) {
		this.rating = rating;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public String getPublish_date() {
		return publish_date;
	}
	public void setPublish_date(String publish_date) {
		this.publish_date = publish_date;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public int getCommentNum() {
		return commentNum;
	}
	public void setCommentNum(int commentNum) {
		this.commentNum = commentNum;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	
	
	
}
