package com.test;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.model.User;
import com.util.HibernateUtils;



import junit.framework.TestCase;

public class hqlTest extends TestCase {
	public void  testQuery1(){
		Session session = null;
		try {
			session = HibernateUtils.getSession();
			session.beginTransaction();
			
			
			Object[] params=new Object[]{"user1"};
			String sql="from User s  where s.username=?";
			Query query = session.createQuery(sql);
            for(int i=0;i<params.length;i++){
            	query.setParameter(i, params[i]);
            }
            List results=query.list();

			for (Iterator iter=results.iterator(); iter.hasNext();) {
				User user = (User)iter.next();
				System.out.println(user.getUsername());
			}
			session.getTransaction().commit();
		}catch(Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}finally {
			HibernateUtils.closeSession(session);
		}
	}
}
