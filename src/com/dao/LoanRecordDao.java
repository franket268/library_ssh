package com.dao;

import java.util.Iterator;
import java.util.List;


import org.hibernate.Session;

import com.model.Book;
import com.model.LoanRecord;
import com.model.User;
import com.util.HibernateUtils;

public class LoanRecordDao {

	public void borrowBook(int bookId, int userId, String checkOutDate,
			String expireDate) {
		// TODO Auto-generated method stub
	   	 Session session = null;
	     try {
	    	session = HibernateUtils.getSession();
			session.beginTransaction();
	         LoanRecord loanRecord=new LoanRecord();
	         User user=(User) session.get(User.class,userId);
	         loanRecord.setBookId(bookId);
	         loanRecord.setUser(user);
	         loanRecord.setCheckOutDate(checkOutDate);
	         loanRecord.setExpireDate(expireDate);
	         session.save(loanRecord);
	         session.getTransaction().commit();
	         
			}catch(Exception e) {
				e.printStackTrace();
				session.getTransaction().rollback();
			}finally {
				HibernateUtils.closeSession(session);
			}
	}

	public String getExpireDate(int bookId) {
		// TODO Auto-generated method stub
		
		Session session = null;
        Book book=null;
        String expireDate="";
		try {
			session = HibernateUtils.getSession();
			String sql = "";
		    sql="from LoanRecord  where bookId=? AND isReturn=0 ";
			Object[] params=new Object[]{bookId};
			List results=HibernateUtils.executeQuery(session, sql,params);
			LoanRecord loanRecord=(LoanRecord)results.get(0);
			expireDate=loanRecord.getExpireDate();
		}catch(Exception e) {
			e.printStackTrace();
			
		}finally {
			HibernateUtils.closeSession(session);
		}
	

		return expireDate;
	}

	public void returnBook(int bookId, String returnDate) {
		// TODO Auto-generated method stub
		 Session session = null;
	     try {
	    	
	    	session = HibernateUtils.getSession();
			String sql="from LoanRecord where bookId=?";
 			Object[] params=new Object[]{bookId};
 			List results=HibernateUtils.executeQuery(session,sql,params);
 			
 			LoanRecord loanRecord=new LoanRecord();
 			for(Iterator iter=results.iterator();iter.hasNext();){
 				loanRecord=(LoanRecord) iter.next();
 			}
 			loanRecord.setIsReturn(1);
 			loanRecord.setReturnDate(returnDate);
 			loanRecord.setLastUpdateTime(returnDate);
	        session.getTransaction().commit();
	         
			}catch(Exception e) {
				e.printStackTrace();
				session.getTransaction().rollback();
			}finally {
				HibernateUtils.closeSession(session);
			}
	}


}
