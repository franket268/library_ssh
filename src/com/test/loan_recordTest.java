package com.test;

import java.util.Iterator;
import java.util.List;


import org.hibernate.Session;

import com.model.LoanRecord;
import com.model.User;
import com.util.HibernateUtils;

import junit.framework.TestCase;

public class loan_recordTest extends TestCase {

	public void testInsert(){
		 Session session = null;
         try {
        	session = HibernateUtils.getSession();
 			session.beginTransaction();
             User user=(User) session.get(User.class,1);
             LoanRecord loanRecord=new LoanRecord();
             loanRecord.setBookId(99);
             loanRecord.setUser(user);
             session.save(loanRecord);
             session.getTransaction().commit();
             
 		}catch(Exception e) {
 			e.printStackTrace();
 			session.getTransaction().rollback();
 		}finally {
 			HibernateUtils.closeSession(session);
 		}
	}
	
	public void testGetloan(){
		 Session session = null;
	     try {
	    	
	    	session = HibernateUtils.getSession();
			String sql="from LoanRecord where bookId=?";
 			Object[] params=new Object[]{9};
 			List results=HibernateUtils.executeQuery(session,sql,params);
 			
 			LoanRecord loanRecord=new LoanRecord();
 			for(Iterator iter=results.iterator();iter.hasNext();){
 				loanRecord=(LoanRecord)iter.next();
 			}
	         System.out.print(loanRecord.getReturnDate());
	         loanRecord.setIsReturn(1);
	         session.getTransaction().commit();
	         

			}catch(Exception e) {
				e.printStackTrace();
				session.getTransaction().rollback();
			}finally {
				HibernateUtils.closeSession(session);
			}
	}


	
}
