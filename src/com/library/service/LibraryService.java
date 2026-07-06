package com.library.service;
import com.library.dao.BookDAO;
import com.library.dao.IssueDAO;

import java.util.List;

import com.library.bean.Book;
import com.library.bean.Issue;
import com.library.util.ActiveIssueException;
import com.library.util.BookUnavailableException;
import com.library.util.ValidationException;

public class LibraryService {
	private BookDAO bookDAO = new BookDAO();
	private IssueDAO issueDAO = new IssueDAO();
	
	public Book viewBookDetails(String bookID) throws ValidationException{
		if(bookID==null ||bookID.trim().isEmpty()) {
			throw new ValidationException();
		}
		return bookDAO.findBook(bookID);
	}
	public List<Book> viewAllBooks(){
		return bookDAO.viewAllBooks();
	}
	public boolean addNewBook(Book book) throws ValidationException {
		if (book==null || (book.getBookID()==null || book.getBookID().trim().isEmpty())
			|| (book.getAuthor()==null || book.getAuthor().trim().isEmpty())
			|| (book.getTotalCopies()<0) || (book.getAvailableCopies()<0)) {
			throw new ValidationException();
		}
		Book b = bookDAO.findBook(book.getBookID());	
		if(b!=null) {
			throw new  ValidationException();
		}
		return bookDAO.insertBook(book);
		
	}
	public boolean removeBook(String bookID) throws ValidationException ,ActiveIssueException{
		if (bookID==null || bookID.trim().isEmpty()) {
			throw new ValidationException();
		}
		if(issueDAO.hasActiveIssue(bookID)) {
		    throw new ActiveIssueException();
		}
		return bookDAO.deleteBook(bookID);
	}
	public boolean issueBook(String bookID,String studentID,String studentName)
			throws ValidationException,BookUnavailableException {
				if(bookID == null || bookID.trim().isEmpty() ||
				studentID == null || studentID.trim().isEmpty() ||
				studentName == null || studentName.trim().isEmpty()) {
			throw new ValidationException();
	} 
			Book book = bookDAO.findBook(bookID);
            if(book == null) {
                    return false;
            }
            if(book.getAvailableCopies() == 0) {
            	throw new BookUnavailableException();
            	}
            int updatedCopies = book.getAvailableCopies() - 1;

            boolean bookUpdated =bookDAO.updateAvailableCopies(bookID,
                             updatedCopies);
            int issueID = issueDAO.generateIssueID();
            Issue issue = new Issue(issueID,bookID,studentID,studentName,
            		new java.sql.Date(System.currentTimeMillis()),null);
            	boolean issueRecorded =
            	issueDAO.recordIssue(issue);
            	if(bookUpdated && issueRecorded) {
            		return true;
            	}
            	return false;
	}
	public boolean returnBook(int issueID)throws ValidationException {
        if (issueID <= 0) {
            throw new ValidationException();
        }
        Issue issue =issueDAO.findIssue(issueID);

        if (issue == null) {
            return false;
        }
        Book book =bookDAO.findBook(issue.getBookID());
        if (book == null) {
            return false;
        }
        int updatedCopies =book.getAvailableCopies() + 1;
        boolean bookUpdated =bookDAO.updateAvailableCopies(book.getBookID(),updatedCopies);
        boolean issueClosed =issueDAO.closeIssue(issueID);
        if (bookUpdated && issueClosed) {
            return true;
        }
        return false;
    }
	
}
