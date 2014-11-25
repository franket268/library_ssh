package com.dao;

import java.util.List;

import org.hibernate.Session;

import com.model.Book;
import com.model.LoanRecord;
import com.model.PreOrderRequest;
import com.model.User;
import com.util.HibernateUtils;

public class PreOrderDao  {

	public void add_pre_order(int bookId, int userId, String preOrderTime) {
		// TODO Auto-generated method stub
		
        Session session = null;
	     try {
	    	session = HibernateUtils.getSession();
			session.beginTransaction();
	         PreOrderRequest preOrderRequest =new PreOrderRequest();
	         User user=(User) session.get(User.class,userId);
	         preOrderRequest.setBookId(bookId);
	         preOrderRequest.setUser(user);
	         preOrderRequest.setPreOrderTime(preOrderTime);
	         preOrderRequest.setIsFinish(0);
	         session.save(preOrderRequest);
	         session.getTransaction().commit();
	         
			}catch(Exception e) {
				e.printStackTrace();
				session.getTransaction().rollback();
			}finally {
				HibernateUtils.closeSession(session);
			}
	}
		


	public int if_pre_ordered(int bookId) {
		// TODO Auto-generated method stub
		int flat=0;
		Session session = null;
		try {
			session = HibernateUtils.getSession();
			String sql = "";
		    sql="from PreOrderRequest where bookId=? AND isFinish=0 ";
			Object[] params=new Object[]{bookId};
			List results=HibernateUtils.executeQuery(session, sql,params);
			if(results.size()!=0){
				flat=1;
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		
		}finally {
			HibernateUtils.closeSession(session);
		}
	
		
		
		return flat;
	}

	public void cancel_pre_order(int bookId) {
		// TODO Auto-generated method stub
		Session session = null;
	     try {
	    	session = HibernateUtils.getSession();
			session.beginTransaction();
	         String sql = "";
			 sql="from PreOrderRequest where bookId=? AND isFinish=0 ";
		     Object[] params=new Object[]{bookId};
			 List results=HibernateUtils.executeQuery(session, sql,params);
			 PreOrderRequest preOrderRequest=(PreOrderRequest) results.get(0);
			 preOrderRequest.setIsFinish(1);
			 
	         session.getTransaction().commit();
	         
			}catch(Exception e) {
				e.printStackTrace();
				session.getTransaction().rollback();
			}finally {
				HibernateUtils.closeSession(session);
			}
		
	}



}
