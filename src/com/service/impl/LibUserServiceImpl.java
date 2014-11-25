package com.service.impl;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;


import com.dao.BookCopyDao;
import com.dao.GeneralDao;
import com.dao.LoanRecordDao;
import com.dao.PreOrderDao;
import com.dao.UserDao;
import com.service.LibUserService;

public class LibUserServiceImpl extends UserServiceImpl implements LibUserService  {

	@Override
	public ArrayList<HashMap<String, Object>> add_to_checkoutList(ArrayList<HashMap<String, Object>> list,int bookId,String bookName,String bookISBN) {
		// TODO Auto-generated method stub
		
		   
		   int status=1;
		   for(HashMap<String, Object> hashmap :list )
		   {  
			    if(hashmap.get("bookId").equals(bookId))
			    	 status=0;
		   }
	       if(status==1)
	       {
		    HashMap<String, Object> map = new HashMap<String, Object>();	
	        map.put("bookId", bookId);  
	        map.put("bookName", bookName);  
	        map.put("bookISBN", bookISBN);
	        list.add(map);
	       }
	      
	        
		return list;
	}



	@Override
	public ArrayList<HashMap<String, Object>> delete_checkout(
			ArrayList<HashMap<String, Object>> check_out_list, int checkoutId) {
		// TODO Auto-generated method stub
		
	    ArrayList<HashMap<String, Object>> list = (ArrayList<HashMap<String, Object>>)new ArrayList<HashMap<String,Object>>();
		for(HashMap<String, Object> hashmap :check_out_list)
        {
        	int bookId=(Integer)hashmap.get("bookId");
        	if(bookId!=checkoutId)
        	{
        		list.add(hashmap);
        	}
           
        }
		
		return list;
	}



	@Override
	public void submit_checkout( ArrayList<HashMap<String, Object>> check_out_list,int userId,int userQuota ) {
		// TODO Auto-generated method stub
		
		int rest_quota=userQuota-check_out_list.size();
		UserDao userDao=new UserDao();
		userDao.decrease_quota(userId,rest_quota);
		
		
		
	    for(HashMap<String, Object> hashmap :check_out_list)
	    {
	    	int bookId=(Integer)hashmap.get("bookId");
      
            
            
            //compute the expire date
            Date date =new Date();
            SimpleDateFormat s=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String checkOutDate=s.format(date);
            Calendar cal = Calendar.getInstance(); 
            cal.setTime(date); 
            cal.add(Calendar.DATE, 30); 
            Date newDate = cal.getTime();
            String expireDate=s.format(newDate);

            BookCopyDao bookCopyDao;
            LoanRecordDao loanRecordDao;
            //update book_copy table and loan_record table
			bookCopyDao=new BookCopyDao();
			bookCopyDao.borrowBook(bookId);
			loanRecordDao=new LoanRecordDao();
			loanRecordDao.borrowBook(bookId, userId, checkOutDate, expireDate);
           
            
           
	    }
		
		
	}



	@Override
	public HashMap<String, Object> get_book_order_info(int bookId,
			String bookName, String bookISBN) {
		// TODO Auto-generated method stub
	
		String  expireDate="";
		LoanRecordDao loanRecordDao=new LoanRecordDao();
		expireDate=loanRecordDao.getExpireDate(bookId);
		
		HashMap<String, Object> map=new HashMap<String, Object>();
		map.put("bookId", bookId);
		map.put("bookName", bookName);
		map.put("bookISBN", bookISBN);
		map.put("expireDate", expireDate);
		
		return map;
	}
	
	public void submit_pre_order(int bookId,int userId)
	{
		Date date =new Date();
        SimpleDateFormat s=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String preOrderTime=s.format(date);
        UserDao userDao=new UserDao();
		userDao.decrease_preorder_quota(userId);
		PreOrderDao preOrderDao=new PreOrderDao();
		preOrderDao.add_pre_order(bookId, userId, preOrderTime);
        
	}
	

	public ArrayList<HashMap<String, Object>> get_my_loanRecord(int userId) 
	{
		ArrayList<HashMap<String, Object>> list=new ArrayList<HashMap<String,Object>>();
		GeneralDao generalDao = null;
		try {
			generalDao = new GeneralDao();
			list=generalDao.get_myLoan(userId);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
           catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
		
	}
	public HashMap<String, Object> get_preOrderRecord(int userId) throws ClassNotFoundException, IOException, SQLException
	{
		HashMap<String, Object> map=new HashMap<String, Object>();
		GeneralDao generalDao;
		generalDao = new GeneralDao();
		map=generalDao.ger_myPreOrder(userId);
		
		return map;
	}

    public void returnBook(int bookId,int userId)
    {
    	int quota=0;
    	Date date =new Date();
        SimpleDateFormat s=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String returnDate=s.format(date);
        int flat=0;//indicate if the book has been pre-ordered;
    	
    	LoanRecordDao loanRecordDao=new LoanRecordDao();
		loanRecordDao.returnBook(bookId,returnDate);
		UserDao userDao=new UserDao();
		
		quota=userDao.get_quota(userId);
		userDao.increase_quota(userId,quota);
		
		PreOrderDao preOrderDao=new PreOrderDao();
		flat=preOrderDao.if_pre_ordered(bookId);
		
		BookCopyDao bookCopyDao=new BookCopyDao();
		if(flat==0)
		{
			bookCopyDao.setAvailable(bookId);
		}
    }

    public void cancel_preOrder(int bookId,int userId)
    {
    	PreOrderDao preOrderDao=new PreOrderDao();
		preOrderDao.cancel_pre_order(bookId);
		
		UserDao userDao=new UserDao();
		userDao.increase_preorder_quota(userId);
    	
    }






}
