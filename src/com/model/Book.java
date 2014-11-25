package com.model;

import java.util.HashSet;
import java.util.Set;



public class Book {
    private String author;
    private String name;
    private String description;
    private String ISBN;
    private String location;
    private Set<Book_copy> book_copy=new HashSet<Book_copy>();

    public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public Book(){
    }
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getISBN() {
		return ISBN;
	}
	public void setISBN(String ISBN) {
		this.ISBN = ISBN;
	}


	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Set<Book_copy> getBook_copy() {
		return book_copy;
	}
	public void setBook_copy(Set<Book_copy> book_copy) {
		this.book_copy = book_copy;
	}


}

