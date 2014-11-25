package com.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;


import com.model.User;
import com.util.HibernateUtils;

public class UserDao extends HibernateDaoSupport  {
	
	

	public User login(String username, String password) {
		// TODO Auto-generated method stub
//		Session session = null;
		User user=null;
		try {
			String sql="from User";
			List result = this.getHibernateTemplate().find(sql);
			
			user=(User) result.get(0);
			System.out.println("000000000000"+user.getUsername());
		}catch(Exception e) {
			e.printStackTrace();
			
		}finally {
			
		}
		
		
		return user;
	}

	public int get_quota(int userId) {
		// TODO Auto-generated method stub
		Session session = null;
		int quota=0;
		try {
			session = HibernateUtils.getSession();
			String sql="select quota from User where id=?";
			Object[] params=new Object[]{userId};
			List results=HibernateUtils.executeQuery(session, sql, params);
			quota=(Integer)results.get(0);
		}catch(Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}finally {
			HibernateUtils.closeSession(session);
		}
		
		
		return quota;
	}

	public int get_pre_quota(int userId) {
		// TODO Auto-generated method stub
		Session session = null;
		int preorder_quota=0;
		try {
			session = HibernateUtils.getSession();
			String sql="select preorder_quota from User where id=?";
			Object[] params=new Object[]{userId};
			List results=HibernateUtils.executeQuery(session, sql, params);
			preorder_quota=(Integer)results.get(0);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			HibernateUtils.closeSession(session);
		}
		return preorder_quota;
	}

	public void decrease_quota(int userId, int rest_quota) {
		// TODO Auto-generated method stub
   	 Session session = null;
     try {
    	session = HibernateUtils.getSession();
		session.beginTransaction();
         User user=(User) session.get(User.class,userId);
         user.setQuota(rest_quota);
         session.getTransaction().commit();
         
		}catch(Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}finally {
			HibernateUtils.closeSession(session);
		}
		
		
	}

	public void decrease_preorder_quota(int userId) {
		// TODO Auto-generated method stub
		 Session session = null;
	     try {
	    	session = HibernateUtils.getSession();
			session.beginTransaction();
	         User user=(User) session.get(User.class,userId);
	         user.setQuota(0);
	         session.getTransaction().commit();
	         
			}catch(Exception e) {
				e.printStackTrace();
				session.getTransaction().rollback();
			}finally {
				HibernateUtils.closeSession(session);
			}
	}
		
		


	public void increase_quota(int userId, int quota) {
		// TODO Auto-generated method stub
		 Session session = null;
		 int rest_quota=quota+1;
	     try {
	    	session = HibernateUtils.getSession();
			session.beginTransaction();
	         User user=(User) session.get(User.class,userId);
	         user.setQuota(rest_quota);
	         session.getTransaction().commit();
	         
			}catch(Exception e) {
				e.printStackTrace();
				session.getTransaction().rollback();
			}finally {
				HibernateUtils.closeSession(session);
			}
	}

	public void increase_preorder_quota(int userId) {
		// TODO Auto-generated method stub
		Session session = null;
	     try {
	    	session = HibernateUtils.getSession();
			session.beginTransaction();
	         User user=(User) session.get(User.class,userId);
	         user.setQuota(1);
	         session.getTransaction().commit();
	         
			}catch(Exception e) {
				e.printStackTrace();
				session.getTransaction().rollback();
			}finally {
				HibernateUtils.closeSession(session);
			}
	}

	public String getNameById(int userId) {
		// TODO Auto-generated method stub
		Session session = null;
		String username="";
	     try {
	    	session = HibernateUtils.getSession();
			
	         User user=(User) session.get(User.class,userId);
	         username=user.getUsername();
	         
	         
			}catch(Exception e) {
				e.printStackTrace();
				session.getTransaction().rollback();
			}finally {
				HibernateUtils.closeSession(session);
			}
		return username;
	}


}
