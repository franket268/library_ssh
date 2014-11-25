package com.test;

import java.util.List;

import org.hibernate.Session;

import com.model.User;
import com.util.HibernateUtils;

import junit.framework.TestCase;

public class userTest extends TestCase {
     public void testQuota(){
    	 Session session = null;
 		
 		try {
 			session = HibernateUtils.getSession();
 			String sql="select quota from User where username='user1'";
 			Object[] params=new Object[]{"user1"};
 			List results=HibernateUtils.executeQuery2(session,sql);
 			Object obj=(Object)results.get(0);
 			System.out.println(obj);
 		}catch(Exception e) {
 			e.printStackTrace();
 			session.getTransaction().rollback();
 		}finally {
 			HibernateUtils.closeSession(session);
 		}
     }
     
     public void testSetquota(){
    	 Session session = null;
         try {
        	session = HibernateUtils.getSession();
 			session.beginTransaction();
             User user=(User) session.get(User.class,1);
             user.setQuota(1);
             session.getTransaction().commit();
             
 		}catch(Exception e) {
 			e.printStackTrace();
 			session.getTransaction().rollback();
 		}finally {
 			HibernateUtils.closeSession(session);
 		}
 	
     }
}
