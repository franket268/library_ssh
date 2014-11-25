package com.test;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import com.model.Book;
import com.model.Book_copy;
import com.model.User;
import com.util.HibernateUtils;

import junit.framework.TestCase;

public class bookTest extends TestCase {

	public void testSearch(){
		Session session = null;
		User user=null;
		ArrayList<Book> list=null;
		try {
			session = HibernateUtils.getSession();
			String sql = "";
			String ref="name";
			String key="a";
			if(ref.equals("name"))
				   sql="from Book  where name  LIKE '%"+key+"%'  ";
			else if(ref.equals("author"))
			    	sql=" from Book  where author  LIKE '%"+key+"%'  ";
			else if(ref.equals("ISBN"))
					 sql=" from Book  where ISBN="+key;
			
			List results=HibernateUtils.executeQuery2(session, sql);
		    list=(ArrayList<Book>)results;
            Book book=(Book) list.get(0);
            System.out.print(book.getName());
            book=list.get(1);
            System.out.print(book.getName());
		}catch(Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}finally {
			HibernateUtils.closeSession(session);
		}
	}
	
	public void testSearch2(){
		Session session = null;
		try {
			session = HibernateUtils.getSession();
			String sql = "";
		     sql="from Book_copy where id=1  ";

			List results=HibernateUtils.executeQuery2(session, sql);
            Book_copy book_copy=(Book_copy)results.get(0);
            Book book=book_copy.getBook();
            System.out.print(book.getISBN());
		}catch(Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}finally {
			HibernateUtils.closeSession(session);
		}
	}
	
	
	public void testView(){
		Session session = null;
		try {
			session = HibernateUtils.getSession();
			String sql = "";
			sql="from v_book_copy_status where id=?";
			 Object[] params = new Object[]{"1"};
			List results=HibernateUtils.executeQuery(session, sql,params);
             Object[] obj=(Object[])results.get(0);
             System.out.println(obj[1]);
             
		
			session.getTransaction().rollback();
		}finally {
			HibernateUtils.closeSession(session);
		}
	}
	
	
	public void testAddbook(){
			
			 Session session = null;
		     try {
		    	session = HibernateUtils.getSession();
				session.beginTransaction();
		         Book book =new Book();
		         String iSBN="0006";
		         book.setISBN(iSBN);

		         session.save(book);

		         
		         session.getTransaction().commit();
		         
				}catch(Exception e) {
					e.printStackTrace();
					session.getTransaction().rollback();
				}finally {
					HibernateUtils.closeSession(session);
				}
			
			
		}
	
	
	
}
