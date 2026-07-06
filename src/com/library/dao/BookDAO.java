package com.library.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.library.bean.Book;
import com.library.util.DBUtil;
public class BookDAO {
	public Book findBook(String bookID) {
		Book book=null;
		Connection con=DBUtil.getDBConnection();
		String query="SELECT * FROM BOOK_TBL WHERE bookID=?";
		try {
			PreparedStatement  ps=con.prepareStatement(query);
			ps.setString(1,bookID);
			ResultSet rs=ps.executeQuery();
			if (rs.next()) {
				book=new Book(
				rs.getString("bookID"),
				rs.getString("title"),
				rs.getString("author"),
				rs.getInt("totalCopies"),
				rs.getInt("availableCopies"));
			}
			rs.close();
			ps.close();
			con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return book;
	}
	public List<Book> viewAllBooks(){
		List<Book> books=new ArrayList<>();
		Connection con=DBUtil.getDBConnection();
		String query="SELECT * FROM BOOK_TBL";
		try {
			PreparedStatement  ps=con.prepareStatement(query);
			ResultSet rs=ps.executeQuery();
			while (rs.next()) {
				Book book=new Book(
				rs.getString("bookID"),
				rs.getString("title"),
				rs.getString("author"),
				rs.getInt("totaalCopies"),
				rs.getInt("availableCopies"));
			books.add(book);
			}
			rs.close();
			ps.close();
			con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return books;	
	}
	public boolean insertBook(Book book) {
		Connection con=DBUtil.getDBConnection();
		String query="insert into book_tbl values(?,?,?,?,?)";
		try {
			PreparedStatement  ps=con.prepareStatement(query);
			ps.setString(1,book.getBookID());
			ps.setString(2,book.getTitle());
			ps.setString(3,book.getAuthor());
			ps.setInt(4,book.getTotalCopies());
			ps.setInt(5,book.getAvailableCopies());
			return ps.executeUpdate()>0;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	public boolean updateAvailableCopies(String bookID,int newCount) {
		Connection con=DBUtil.getDBConnection();
		String query="update book_tbl set availableCopies=? where bookID=?";
		try {
			PreparedStatement ps=con.prepareStatement(query);
			ps.setInt(1, newCount);
			ps.setString(2, bookID);
			return ps.executeUpdate()>0;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	public boolean deleteBook(String bookID) {
		Connection con=DBUtil.getDBConnection();
		String query="delete from book_tbl where bookID=?";
		try {
			PreparedStatement ps=con.prepareStatement(query);
			ps.setString(1, bookID);
			return ps.executeUpdate()>0;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
}
