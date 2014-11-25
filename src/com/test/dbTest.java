package com.test;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.dao.SqlManagerDao;

import junit.framework.TestCase;

public class dbTest extends TestCase {
    public void testDB() throws SQLException, ClassNotFoundException, IOException{
    	
    	SqlManagerDao manager = null;
    	String sql = "";
    	ResultSet rs;
    	manager = SqlManagerDao.createInstance();
    	
    	int flat=0;
		sql="SELECT copyStatus FROM v_book_copy_status WHERE id=?";
		 Object[] params = new Object[]{"1"};
         manager.connectDB();
	     rs = manager.executeQuery(sql, params);
	     while (rs.next())
	     {
	    	 flat=rs.getInt("copyStatus");
	     }
	     System.out.print(flat);
	     manager.closeDB();

    }
}
