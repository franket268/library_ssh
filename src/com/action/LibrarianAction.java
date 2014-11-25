package com.action;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import com.model.Book;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.service.impl.LibrarianServiceImpl;
import com.service.impl.UserServiceImpl;

public class LibrarianAction extends ActionSupport {
    private Book book;
    private int bookId;
    private int display;  //=1 means display late_notice,=0 means cancel display
    private ArrayList<Book> searchList;
    private ArrayList<HashMap<String, Object>> booklist;
	private String ref;  //search ref 
    private String key;  // search key
    private UserServiceImpl userServiceIml=new UserServiceImpl();
    private LibrarianServiceImpl librarianServiceImpl=new LibrarianServiceImpl();

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}
	
	
	
	
	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public ArrayList<Book> getSearchList() {
		return searchList;
	}

	public void setSearchList(ArrayList<Book> searchList) {
		this.searchList = searchList;
	}
	
	
	
	

	public int getDisplay() {
		return display;
	}

	public void setDisplay(int display) {
		this.display = display;
	}

	public ArrayList<HashMap<String, Object>> getBooklist() {
		return booklist;
	}

	public void setBooklist(ArrayList<HashMap<String, Object>> booklist) {
		this.booklist = booklist;
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



	public String addBook(){
		int flat=0;
		flat=librarianServiceImpl.if_book_exist(book.getISBN());
		if(flat==0)
		  {
		    librarianServiceImpl.add_Book(book.getISBN(),book.getName(),book.getAuthor(),book.getDescription(),book.getLocation());
		  }
		else
		{
			librarianServiceImpl.add_Copy(book.getISBN());
		}
		return "update";
	}
	
	
	public String search() throws ClassNotFoundException, IOException{
		searchList=new ArrayList<Book>();
		booklist=new ArrayList<HashMap<String,Object>>();
		searchList=userServiceIml.searchBook(ref,key);
		book=new Book();
		if (searchList!=null){
			book=searchList.get(0);
			booklist=userServiceIml.browseBookDetail(book);
		}
		ActionContext actionContext=ActionContext.getContext();
		actionContext.put("booklist",booklist);
    	return "update";
    }
	
	public String updateBook(){
		librarianServiceImpl.update_Book(book.getISBN(), book.getName(), book.getAuthor(), book.getDescription(), book.getLocation());
		return "update";
	}
	
	public String deleteBook(){
		librarianServiceImpl.delete_Copy(bookId);
		return "update";
	}
	
	public String late_notice() throws ClassNotFoundException, IOException, SQLException{
		ArrayList<HashMap<String, Object>> notice= new ArrayList<HashMap<String, Object>>();
		notice=librarianServiceImpl.get_Late_notice();
		ActionContext actionContext=ActionContext.getContext();
		actionContext.getSession().put("late_notice",notice);
		return "late_notice";
	}
	
	public String display_late_notice(){
		ActionContext actionContext=ActionContext.getContext();
		ArrayList<HashMap<String, Object>> notice=new ArrayList<HashMap<String, Object>>();
	    notice=(ArrayList<HashMap<String, Object>>)actionContext.getSession().get("late_notice");
	    if(display==1)
	    {
	    	actionContext.getApplication().put("display_notice", notice);
	    }
	    else
	    {
	    	actionContext.getApplication().remove("display_notice");
	    }
	    return "late_notice";
	}
	
	public String generate_usage_report() throws ClassNotFoundException, IOException, SQLException{
		HashMap<String, Object> usage_report=new  HashMap<String, Object>();
		ArrayList<HashMap<String, Object>> loan_record=new ArrayList<HashMap<String, Object>>();
		ArrayList<HashMap<String, Object>> pre_order_record=new ArrayList<HashMap<String, Object>>();

		usage_report=librarianServiceImpl.get_Usage_Report();
		loan_record=librarianServiceImpl.get_Loan_Report();
		pre_order_record=librarianServiceImpl.get_Pre_Order_Report();
		
		ActionContext actionContext=ActionContext.getContext();
		actionContext.put("loan_record", loan_record);
		actionContext.put("pre_order_record", pre_order_record);
		actionContext.put("usage_report", usage_report);
	     
		return "usage_report";
	}
	
}
