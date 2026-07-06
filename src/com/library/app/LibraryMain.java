package com.library.app;
import java.util.Scanner;
import com.library.service.LibraryService;

public class LibraryMain {
	private static LibraryService libraryService;
	 public static void main(String[] args) {
	 libraryService = new LibraryService();
	 Scanner sc = new Scanner(System.in);
	 System.out.println("--- Library Console ---");
	 try {
	 boolean r = libraryService.issueBook("BK101","ST5001","Meera Nair");
	 System.out.println(r ? "ISSUED" : "FAILED");
	 } catch(Exception e){ System.out.println(e);} 
	 try {
	 boolean r = libraryService.returnBook(30001);
	 System.out.println(r ? "RETURNED" : "FAILED");
	 } catch(Exception e){ System.out.println(e);} 
	 sc.close();
	 }
	}
