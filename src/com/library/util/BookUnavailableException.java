package com.library.util;

public class BookUnavailableException extends Exception {
	public String toString() {
		return "No copies available";
	}
}
