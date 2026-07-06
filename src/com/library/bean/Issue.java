package com.library.bean;

import java.sql.Date;
public class Issue {
	private int issueID;
	private String bookID;
	private String studentID;
	private String studentName;
	private Date issueDate;
	private Date returnDate;
	public Issue(int issueID,String bookID,String studentID,
			String studentName,Date issueDate,Date returnDate) {
		this.issueID=issueID;
		this.bookID=bookID;
		this.studentID=studentID;
		this.studentName=studentName;
		this.issueDate=issueDate;
		this.returnDate=returnDate;
	}
	public int getIssueID() {
		return issueID;
	}
	public void setIssueID(int issueID) {
		this.issueID=issueID;
	}
	public String getBookID() {
		return bookID;
	}
	public void setBookID(String bookID) {
		this.bookID=bookID;
	}
	public String getStudentID() {
		return studentID;
	}
	public void setStudentID(String studentID) {
		this.studentID=studentID;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName=studentName;
	}
	public Date getIssueDate() {
		return issueDate;
	}
	public void setIssueDate(Date issueDate) {
		this.issueDate=issueDate;
	}
	public Date getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(Date returnDate) {
		this.returnDate=returnDate;
	}
}
