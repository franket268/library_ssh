package com.service.impl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;


import com.dao.BookCopyDao;
import com.dao.BookDao;
import com.dao.GeneralDao;
import com.dao.UserDao;
import com.model.Book;
import com.service.LibrarianService;

public class LibrarianServiceImpl extends UserServiceImpl implements LibrarianService{

	@Override
	public void add_Book(String ISBN,String name,String author,String description,String location) {
		// TODO Auto-generated method stub
	   
	   BookDao bookDao;
	   BookCopyDao bookCopyDao;
	   bookDao = new BookDao();
	   bookDao.add_book(ISBN,name,author,description,location);

	  
		
		
	}

	@Override
	public void delete_Book(Book book) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update_Book(String ISBN,String name,String author,String description,String location) {
		// TODO Auto-generated method stub
		BookDao bookDao;
		bookDao=new BookDao();
		bookDao.update_book(ISBN,name,author,description,location);
		
		
		
		
	}
	
	public int if_book_exist(String ISBN)
	{
		int flat=0;
		BookDao bookDao;
		bookDao = new BookDao();
		flat=bookDao.if_book_exist(ISBN);
		
		return flat;
	}

	public void add_Copy(String ISBN) {
		// TODO Auto-generated method stub
		BookCopyDao bookCopyDao;
		bookCopyDao=new BookCopyDao();
		bookCopyDao.add_copy(ISBN);
	}
	
	public void delete_Copy(int bookId)
	{
		BookCopyDao bookCopyDao;
		bookCopyDao=new BookCopyDao();
		bookCopyDao.delete_copy(bookId);
		
	}
	
	public ArrayList<HashMap<String, Object>> get_Late_notice() throws ClassNotFoundException, IOException, SQLException
	{
		ArrayList<HashMap<String, Object>> list=new ArrayList<HashMap<String, Object>>();
		GeneralDao generalDao;
		generalDao = new GeneralDao();
		list=generalDao.get_late_notice();
		
		
		return list;
		
	}
	
	public ArrayList<HashMap<String, Object>> get_Loan_Report() throws ClassNotFoundException, IOException, SQLException
	{
		ArrayList<HashMap<String, Object>> list=new ArrayList<HashMap<String, Object>>();
		GeneralDao generalDao;
		generalDao = new GeneralDao();
		list=generalDao.get_loan_report();
		UserDao userDao=new UserDao();
		BookCopyDao bookcopyDao=new BookCopyDao();
		
		String userName="";
		int bookId=0;
		int userId=0;
		String ISBN="";
		 for(HashMap<String, Object> hashmap :list)
		 {
			userId=(Integer)hashmap.get("userId");
			userName=userDao.getNameById(userId);
			hashmap.put("userName",userName);
			
			bookId=(Integer)hashmap.get("bookId");
			ISBN=bookcopyDao.get_ISBN_byId(bookId);
			hashmap.put("ISBN",ISBN);
		 }
		return list;
	}

	public ArrayList<HashMap<String, Object>> get_Pre_Order_Report() throws ClassNotFoundException, IOException, SQLException {
		// TODO Auto-generated method stub
		ArrayList<HashMap<String, Object>> list=new ArrayList<HashMap<String, Object>>();
		GeneralDao generalDao;
		
		String userName="";
		int bookId=0;
		int userId=0;
		String ISBN="";

        generalDao = new GeneralDao();
		list=generalDao.get_pre_order_report();
		UserDao userDao=new UserDao();
		BookCopyDao bookcopyDao=new BookCopyDao();
		
		 for(HashMap<String, Object> hashmap :list)
		 {
			userId=(Integer)hashmap.get("userId");
			userName=userDao.getNameById(userId);
			hashmap.put("userName",userName);
			
			bookId=(Integer)hashmap.get("bookId");
			ISBN=bookcopyDao.get_ISBN_byId(bookId);
			hashmap.put("ISBN",ISBN);
		 }
	    
	    return list;
	}
	
	public HashMap<String, Object> get_Usage_Report() throws ClassNotFoundException, IOException, SQLException
	{
		HashMap<String, Object> map=new HashMap<String, Object>();
		GeneralDao generalDao;
	    generalDao = new GeneralDao();
		map=generalDao.get_usage_report();
		return map;
	    
	}

}
