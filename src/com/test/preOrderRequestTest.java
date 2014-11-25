package com.test;

import java.util.List;

import org.hibernate.Session;

import com.model.PreOrderRequest;
import com.util.HibernateUtils;

import junit.framework.TestCase;

public class preOrderRequestTest extends TestCase {
	public void testIf_is_preOrder(){
		int flat=0;
		Session session = null;

		try {
			session = HibernateUtils.getSession();
			String sql = "";
		    sql="from PreOrderRequest where bookId=? AND isFinish=0 ";
			Object[] params=new Object[]{"9"};
			List results=HibernateUtils.executeQuery(session, sql,params);
		//	preOrderRequest=(PreOrderRequest)results.get(0);
			if(results.size()!=0){
				flat=1;
			}
			System.out.print(flat);
			
		}catch(Exception e) {
			e.printStackTrace();
		
		}finally {
			HibernateUtils.closeSession(session);
		}
	
	}
}
