package com.service.impl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;





import com.dao.BookCopyDao;
import com.dao.BookDao;
import com.dao.GeneralDao;
import com.dao.UserDao;
import com.model.Book;
import com.model.Book_copy;
import com.model.User;
import com.service.UserService;
import com.util.MD5;

public class UserServiceImpl implements UserService{

	private UserDao userDao;
	
	
	public UserServiceImpl(){
	
	}
	
	
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}



	@Override
	public User login(User user) {
		
		String username=user.getUsername();
		String psswd=user.getPassword();
		
		//encrypt the password in MD5
          String password;
         password=MD5.GetMD5Code(psswd);

        
	    user =userDao.login(username, password);



		
		
		return user;
	}
	

	
	

	@Override
	public void logout(String username) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<Book> searchBook(String ref,String key) {
		// TODO Auto-generated method stub
		ArrayList<Book> booklist= new ArrayList<Book>();
		BookDao bookDao;
		bookDao=new BookDao();
		booklist=bookDao.searchBook(ref, key);
		

		
		
		return booklist;
		
	}
	
	public Book searchBookByISBN(String ISBN){
		Book book=new Book();
		BookDao bookDao;
		bookDao=new BookDao();
		book=bookDao.searchBookByISBN(ISBN);
		return book;
		
	}

	
	public ArrayList<HashMap<String, Object>> browseBookDetail(Book book) throws ClassNotFoundException, IOException
	{
		ArrayList<HashMap<String, Object>> list= new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> map = new HashMap<String, Object>();	
		
		map.put("name", book.getName());
		map.put("ISBN",book.getISBN());
		map.put("author", book.getAuthor());
		map.put("description", book.getDescription());
		map.put("location", book.getLocation());
		
		list.add(map);
		
		
		Set<Book_copy> book_copylist=book.getBook_copy();

	
		for (Book_copy book_copy : book_copylist)
		{
			int flat=0;
			HashMap<String, Object> map2 = new HashMap<String, Object>();	
	
			
		    map2.put("copyISBN", book_copy.getBook().getISBN());
			map2.put("copyId", book_copy.getId());
		   
		    GeneralDao generalDao=new GeneralDao();
			try {
				flat=generalDao.copyStatus(book_copy.getId());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    map2.put("copyStatus",flat);

			list.add(map2);   


		}
		
		
		return list;
		
	}
	

	
	
//    public int get_Quota(int userId)
//    {
//    	int quota=0;
//    	UserDao userDao;
//    	userDao=new UserDao();
//		quota=userDao.get_quota(userId);
//		return quota;
//    	
//    }
//    
//    public User update_User_Quota(User user,int quota)
//    {
//    	    user.setQuota(quota);
//			return user;
//    	
//    }
//
//    public  int get_Pre_Quota(int userId)
//    {
//    	int pre_quota=0;
//    	UserDao userDao;
//    	userDao=new UserDao();
//		pre_quota=userDao.get_pre_quota(userId);
//		return pre_quota;
//    }
    
    public User update_User_Pre_Quota(User user,int quota)
    {
    	    user.setPreorder_quota(quota);
			return user;
    	
    }

}
