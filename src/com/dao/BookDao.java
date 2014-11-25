package com.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import com.model.Book;
import com.model.Book_copy;
import com.model.PreOrderRequest;
import com.model.User;
import com.util.HibernateUtils;

public class BookDao  {

	public ArrayList<Book> searchBook(String ref, String key) {
		// TODO Auto-generated method stub
		Session session = null;
		User user=null;
		ArrayList<Book> list=null;
		try {
			session = HibernateUtils.getSession();
			String sql = "";
			if(ref.equals("name"))
				   sql="from Book b where b.name  LIKE '%"+key+"%'  ";
			else if(ref.equals("author"))
			    	sql=" from Book b where b.author  LIKE '%"+key+"%'  ";
			else if(ref.equals("ISBN"))
					 sql=" from Book b where b.ISBN="+key;
			
			List results=HibernateUtils.executeQuery2(session, sql);
            list=(ArrayList<Book>)results;
		}catch(Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}finally {
			HibernateUtils.closeSession(session);
		}
		return list;
	
	}

	public Book searchBookByISBN(String iSBN) {
		// TODO Auto-generated method stub
		Session session = null;
        Book book=null;
		try {
			session = HibernateUtils.getSession();
			String sql = "";
		    sql="from Book  where ISBN=? ";
			Object[] params=new Object[]{iSBN};
			List results=HibernateUtils.executeQuery(session, sql,params);
            book=(Book)results.get(0);
		}catch(Exception e) {
			e.printStackTrace();
			
		}finally {
			HibernateUtils.closeSession(session);
		}
	
		return book;
	}

	public void add_book(String iSBN, String name, String author,
			String description, String location) {
		// TODO Auto-generated method stub
		
		 Session session = null;
	     try {
	    	session = HibernateUtils.getSession();
			session.beginTransaction();
	         Book book =new Book();
	         book.setISBN(iSBN);
	         book.setName(name);
	         book.setAuthor(author);
	         book.setDescription(description);
	         book.setLocation(location);
	         session.save(book);
	         
	         Book_copy book_copy=new Book_copy();
	         book_copy.setBook(book);
	         book_copy.setIsAvailable(1);
	         session.save(book_copy);
	         
	         session.getTransaction().commit();
	         
			}catch(Exception e) {
				e.printStackTrace();
				session.getTransaction().rollback();
			}finally {
				HibernateUtils.closeSession(session);
			}
		
		
	}

	public void update_book(String iSBN, String name, String author,
			String description, String location) {
		// TODO Auto-generated method stub
		
		Session session = null;
	     try {
	    	session = HibernateUtils.getSession();
			session.beginTransaction();
	         Book book =(Book) session.get(Book.class, iSBN);
	         book.setName(name);
	         book.setAuthor(author);
	         book.setDescription(description);
	         book.setLocation(location);
	         
	         
	         session.getTransaction().commit();
	         
			}catch(Exception e) {
				e.printStackTrace();
				session.getTransaction().rollback();
			}finally {
				HibernateUtils.closeSession(session);
			}
		
	}

	public int if_book_exist(String iSBN) {
		// TODO Auto-generated method stub
		int flat=0;
		Session session = null;
     
		try {
			session = HibernateUtils.getSession();
			String sql = "";
		    sql="from Book  where ISBN=? ";
			Object[] params=new Object[]{iSBN};
			List results=HibernateUtils.executeQuery(session, sql,params);
			if (results.size()!=0){
                  flat=1;
			}
		}catch(Exception e) {
			e.printStackTrace();
			
		}finally {
			HibernateUtils.closeSession(session);
		}
		
		return flat;
	}


}
