package com.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import com.model.Book;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.service.impl.UserServiceImpl;

public class BookAction extends ActionSupport {
    private Book book;
    private String ISBN;
	private String ref;  //search ref 
    private String key;  // search key
    private ArrayList<Book> booklist;
    private ArrayList<HashMap<String, Object>> book_detail_list;
	private UserServiceImpl userServiceIml=new UserServiceImpl();
    
	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}


    
    public ArrayList<Book> getBooklist() {
		return booklist;
	}

	public void setBooklist(ArrayList<Book> booklist) {
		this.booklist = booklist;
	}




    
    
    public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public String getRef() {
		return ref;
	}

	public void setRef(String ref) {
		this.ref = ref;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}


    


	public String search(){
    	booklist=userServiceIml.searchBook(ref,key);
    	return "display";
    }
    
    public String getDetail() throws ClassNotFoundException, IOException{
    	book=userServiceIml.searchBookByISBN(ISBN);
    	book_detail_list=userServiceIml.browseBookDetail(book);
    	ActionContext actionContext=ActionContext.getContext();
		actionContext.getSession().put("book_detail_list", book_detail_list);
    	return "detail";
    }
    
    public String test(){
    	return "test";
    }
	
	
	
}
