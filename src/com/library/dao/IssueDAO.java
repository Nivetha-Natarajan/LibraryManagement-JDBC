package com.library.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;

import com.library.bean.Issue;
import com.library.util.DBUtil;

public class IssueDAO {
	public int generateIssueID() {
		int id=30000;
		Connection con=DBUtil.getDBConnection();
		String query="select ifnull(max(issueID),30000)+1 from Issue_tbl";
		try {
			PreparedStatement ps=con.prepareStatement(query);
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				id=rs.getInt(1);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return id;
	}
	public boolean recordIssue(Issue issue) {
		Connection con=DBUtil.getDBConnection();
		String query="insert into issue_tbl values(?,?,?,?,?,?)";
		try {
			PreparedStatement ps=con.prepareStatement(query);
			ps.setInt(1,issue.getIssueID());
			ps.setString(2,issue.getBookID());
			ps.setString(3, issue.getStudentID());
			ps.setString(4, issue.getStudentName());
			ps.setDate(5,issue.getIssueDate());			
			ps.setDate(6,null);
			return ps.executeUpdate()>0;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	public boolean closeIssue(int issueID) {
		Connection con=DBUtil.getDBConnection();
		String query="UPDATE ISSUE_TBL SET returnDate=? WHERE issueID=?";
		try {
			PreparedStatement ps=con.prepareStatement(query);
			ps.setDate(1,Date.valueOf(LocalDate.now()));
            ps.setInt(2, issueID);
            return ps.executeUpdate() > 0;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	public boolean hasActiveIssue(String bookID) {
	    Connection con = DBUtil.getDBConnection();
	    String query ="SELECT * FROM ISSUE_TBL WHERE bookID=? AND returnDate IS NULL";
	    try {
	        PreparedStatement ps =con.prepareStatement(query);
	        ps.setString(1, bookID);
	        ResultSet rs = ps.executeQuery();
	        return rs.next();
	    } catch(Exception e) {
	        e.printStackTrace();
	    }
	    return false;
	}
	public Issue findIssue(int issueID) {
	    Issue issue = null;
	    Connection con = DBUtil.getDBConnection();
	    String query ="SELECT * FROM ISSUE_TBL WHERE issueID=?";
	    try {
	        PreparedStatement ps = con.prepareStatement(query);
	        ps.setInt(1, issueID);
	        ResultSet rs = ps.executeQuery();
	        if(rs.next()) {
	            issue = new Issue(
	                    rs.getInt("issueID"),
	                    rs.getString("bookID"),
	                    rs.getString("studentID"),
	                    rs.getString("studentName"),
	                    rs.getDate("issueDate"),
	                    rs.getDate("returnDate"));
	        }
	    } catch(Exception e) {
	        e.printStackTrace();
	    }
	    return issue;
	}
	
}
