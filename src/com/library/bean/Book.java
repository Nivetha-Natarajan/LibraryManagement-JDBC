package com.library.bean;

public class Book {
	private String bookID;
	private String title;
	private String author;
	private int totalCopies;
	private int availableCopies;
	public Book(String bookID,String title,String author,int totalCopies,int availableCopies) {
		this.bookID=bookID;
		this.title=title;
		this.author=author;
		this.totalCopies=totalCopies;
		this.availableCopies=availableCopies;
	}
	public String getBookID() {
		return bookID;
	}
	public void setBookID(String bookID) {
		this.bookID=bookID;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title=title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author=author;
	}
	public int getTotalCopies() {
		return totalCopies;
	}
	public void setTotalCopies(int totalCopies) {
		this.totalCopies=totalCopies;
	}
	public int getAvailableCopies() {
		return availableCopies;
	}
	public void setAvailableCopies(int availableCopies) {
		this.availableCopies=availableCopies;
	}
}
