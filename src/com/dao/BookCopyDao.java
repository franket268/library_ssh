package com.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import com.model.Book;
import com.model.Book_copy;
import com.model.User;
import com.util.HibernateUtils;

public class BookCopyDao {


	public void borrowBook(int bookId) {
		// TODO Auto-generated method stub
   	 Session session = null;
     try {
    	session = HibernateUtils.getSession();
		session.beginTransaction();
         Book_copy book_copy=(Book_copy) session.get(Book_copy.class,bookId);
         book_copy.setIsAvailable(1);
         session.getTransaction().commit();
         
		}catch(Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}finally {
			HibernateUtils.closeSession(session);
		}
	
		
	}

	public void setAvailable(int bookId) {
		// TODO Auto-generated method stub
		 Session session = null;
	     try {
	    	session = HibernateUtils.getSession();
			session.beginTransaction();
	         Book_copy book_copy=(Book_copy) session.get(Book_copy.class,bookId);
	         book_copy.setIsAvailable(1);
	         session.getTransaction().commit();
	         
			}catch(Exception e) {
				e.printStackTrace();
				session.getTransaction().rollback();
			}finally {
				HibernateUtils.closeSession(session);
			}
		
	}

	public void add_copy(String iSBN) {
		// TODO Auto-generated method stub
		 Session session = null;
	     try {
	    	session = HibernateUtils.getSession();
			session.beginTransaction();
	       
		    Book book=(Book) session.get(Book.class, iSBN);
	         
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

	public void delete_copy(int bookId) {
		// TODO Auto-generated method stub

		Session session = null;
		try {
			session = HibernateUtils.getSession();
			String sql = "";
		    sql="delete from Book_copy where id=?";
			Object[] params=new Object[]{bookId};
			HibernateUtils.deleteRecord(session, sql,params);
			
		}catch(Exception e) {
			e.printStackTrace();
			
		}finally {
			HibernateUtils.closeSession(session);
			
		}
	}

	public String get_ISBN_byId(int bookId) {
		// TODO Auto-generated method stub
		Session session = null;
		String ISBN="";
	     try {
	    	session = HibernateUtils.getSession();
			
	    	Book_copy book_copy=(Book_copy) session.get(Book_copy.class,bookId);
	    	ISBN=book_copy.getBook().getISBN();
	         
	         
			}catch(Exception e) {
				e.printStackTrace();
				session.getTransaction().rollback();
			}finally {
				HibernateUtils.closeSession(session);
			}
		return ISBN;

	}



	

}
