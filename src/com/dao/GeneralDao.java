package com.dao;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class GeneralDao {
	SqlManagerDao manager;
	String sql = "";
	String sql2 = "";
	ResultSet rs;

	public GeneralDao() throws IOException, ClassNotFoundException
	{
		manager = SqlManagerDao.createInstance();
	}
	
	public int copyStatus(int id)throws SQLException
	{
		/**
	       1-available, isAvailable=1; 
	       2-checked out & not pre orderd, isAvailable=0 isReturn=0 preOrder.isFinish=null/1;
	       3-checked out & pre ordered, isAvailable=0 isReturn=0 isFinish=0; 
	       4-not checked out & pre ordered isAvailable=0 isReturn=1 isFinish=0
		 **/
		
		
		int flat=0;
		sql="SELECT copyStatus FROM v_book_copy_status WHERE id=?";
		 Object[] params = new Object[]{id};
         manager.connectDB();
	     rs = manager.executeQuery(sql, params);
	     while (rs.next())
	     {
	    	 flat=rs.getInt("copyStatus");
	     }
	     manager.closeDB();
	     return flat;
	}
	
	
	
	public ArrayList<HashMap<String, Object>> get_myLoan(int userId) throws SQLException{
		
		 ArrayList<HashMap<String, Object>> list=new ArrayList<HashMap<String,Object>>();
		 
		sql="SELECT bookId,ISBN,name,checkOutDate,expireDate FROM v_my_loan WHERE userId=? and isReturn=0";
		Object[] params = new Object[]{userId};
         manager.connectDB();
	     rs = manager.executeQuery(sql, params);
	    while (rs.next())
		{
	    	HashMap<String, Object> map=new HashMap<String, Object>();
	    	map.put("bookId", rs.getInt("bookId"));
	    	map.put("ISBN", rs.getString("ISBN"));
	    	map.put("name", rs.getString("name"));
	    	map.put("checkOutDate", rs.getString("checkOutDate"));
	    	map.put("expireDate", rs.getString("expireDate"));
	    	list.add(map);
		}
	     manager.closeDB();
		return list;
	
	}
	
	public HashMap<String, Object> ger_myPreOrder(int userId) throws SQLException
	{
		HashMap<String, Object> map=new HashMap<String, Object>();
		sql="SELECT bookId,ISBN,name,preOrderTime,availableTime FROM v_my_pre_order WHERE userId=?";
		Object[] params = new Object[]{userId};
         manager.connectDB();
	     rs = manager.executeQuery(sql, params);
	     while (rs.next())
			{
	    	    map.put("bookId", rs.getInt("bookId"));
		    	map.put("ISBN", rs.getString("ISBN"));
		    	map.put("name", rs.getString("name"));
		    	map.put("preOrderTime", rs.getString("preOrderTime"));
		    	map.put("availableTime", rs.getString("availableTime"));  
			}
		 manager.closeDB();
		return map;

	}

	public ArrayList<HashMap<String, Object>> get_late_notice() throws SQLException {
		// TODO Auto-generated method stub
		ArrayList<HashMap<String, Object>> list=new ArrayList<HashMap<String, Object>>();
		sql="SELECT username,bookId,ISBN,name FROM v_late_return_notice ";
		
         manager.connectDB();
	     rs = manager.executeQuery2(sql);
	     while (rs.next())
			{
	    	    HashMap<String, Object> map=new HashMap<String, Object>();
	    	    map.put("username", rs.getString("username"));
		    	map.put("bookId", rs.getString("bookId"));
		    	map.put("ISBN", rs.getString("ISBN"));
		    	map.put("bookname", rs.getString("name"));
		    	list.add(map);
			}
		 manager.closeDB();
		return list;

	}
	
	public ArrayList<HashMap<String, Object>> get_loan_report() throws SQLException
	{
		ArrayList<HashMap<String, Object>> list=new ArrayList<HashMap<String, Object>>();
		 manager.connectDB();
		sql="SELECT * FROM v_most_recent_loan_status ";
	     rs = manager.executeQuery2(sql);
	     while (rs.next())
			{
	    	    HashMap<String, Object> map=new HashMap<String, Object>();
	    	    map.put("id", rs.getInt("id"));
		    	map.put("bookId", rs.getInt("bookId"));
		    	map.put("userId", rs.getInt("userId"));
		    	map.put("checkOutDate", rs.getString("checkOutDate"));
		    	map.put("isReturn", rs.getInt("isReturn"));
		    	map.put("returnDate", rs.getString("returnDate"));
		    	map.put("lastUpdateTime", rs.getString("lastUpdateTime"));
		    	map.put("expireDate", rs.getString("expireDate"));
		    	
		    	list.add(map);
			}
		 manager.closeDB();
		return list;
	}

	public ArrayList<HashMap<String, Object>> get_pre_order_report() throws SQLException {
		// TODO Auto-generated method stub
		ArrayList<HashMap<String, Object>> list=new ArrayList<HashMap<String, Object>>();
		sql="SELECT * FROM preorder_request ";
		 manager.connectDB();
	     rs = manager.executeQuery2(sql);
	     while (rs.next())
			{
	    	    HashMap<String, Object> map=new HashMap<String, Object>();
	    	    
		    	map.put("bookId", rs.getInt("bookId"));
		    	map.put("userId", rs.getInt("userId"));
		    	map.put("preOrderTime", rs.getString("preOrderTime"));
		    	map.put("isFinish", rs.getInt("isFinish"));
                list.add(map);
			}
		 manager.closeDB();
		return list;
	
	}

	public HashMap<String, Object> get_usage_report() throws SQLException {
		// TODO Auto-generated method stub
		HashMap<String, Object> map=new HashMap<String,Object>();
		 sql="select * from (select count(1) loan_record_count from loan_record) loan_record_count, (select count(1) not_return_count from loan_record where isReturn=0) not_return_count,(select count(1) pre_order_count from preorder_request) pre_order_count,(select count(1) not_finish_count from preorder_request where isFinish=0) not_finish_count,(select now() time from dual) time";
		 manager.connectDB();
	     rs = manager.executeQuery2(sql);
	     while (rs.next())
			{
	    	    map.put("loan_record_count", rs.getInt("loan_record_count"));
		    	map.put("not_return_count", rs.getInt("not_return_count"));
		    	map.put("pre_order_count", rs.getInt("pre_order_count"));
		    	map.put("not_finish_count", rs.getInt("not_finish_count"));
		    	map.put("time", rs.getString("time"));
			}
	     manager.closeDB();
		return map;
	}
}
