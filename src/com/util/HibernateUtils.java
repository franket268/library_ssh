package com.util;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.model.User;

public class HibernateUtils {

	private static SessionFactory factory;
	
	static {
		try {
			Configuration cfg = new Configuration().configure();
			factory = cfg.buildSessionFactory();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static SessionFactory getSessionFactory() {
		return factory;
	}
	
	public static Session getSession() {
		return factory.openSession();
	}
	
	public static void commitSession(Session session) {
		if (session != null) {
			session.getTransaction().commit();
		}
	}
	
	
	public static void closeSession(Session session) {
		if (session != null) {
			if (session.isOpen()) {
				session.close();
			}
		}
	}
	
	public static List executeQuery(Session session,String sql,Object[] params){
		List results = null;
		try {
			
			session.beginTransaction();

			Query query = session.createQuery(sql);
            for(int i=0;i<params.length;i++){
            	query.setParameter(i, params[i]);
            }
            results=query.list();

	
			
		}catch(Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}finally {
			
		}
	
		return results;
	}
	
	public static List executeQuery2(Session session,String sql){
		List results = null;
		try {
			
			session.beginTransaction();

			Query query = session.createQuery(sql);
            results=query.list();

		}catch(Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}finally {
			
		}
	
		return results;
	}
	
	public static void deleteRecord(Session session,String sql,Object[] params){
		try {
			
			session.beginTransaction();

			Query query = session.createQuery(sql);
            for(int i=0;i<params.length;i++){
            	query.setParameter(i, params[i]);
            }
            query.executeUpdate();

		}catch(Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}finally {
			
		}
	}
	
	
}
