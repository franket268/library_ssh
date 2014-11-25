package com.action;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import com.model.Book_copy;
import com.model.User;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.service.impl.LibUserServiceImpl;
import com.service.impl.UserServiceImpl;

public class BookCopyAction extends ActionSupport {
    private Book_copy book_copy;
    private User user;
    private String bookName;
	private LibUserServiceImpl libUserServiceImpl=new LibUserServiceImpl();

	
	
    public Book_copy getBook_copy() {
		return book_copy;
	}

    
	public void setBook_copy(Book_copy book_copy) {
		this.book_copy = book_copy;
	}
	
	
	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public String getBookName() {
		return bookName;
	}


	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	
	

	public String addCheckoutList(){
		 ActionContext actionContext=ActionContext.getContext();
		 ArrayList<HashMap<String, Object>> check_out_list=(ArrayList<HashMap<String, Object>>)actionContext.getSession().get("check_out_list");
		 ArrayList<HashMap<String, Object>>list=new ArrayList<HashMap<String, Object>>();
		 if(check_out_list!=null)
		 {
			    list=check_out_list;
		 }
	        
	     list=libUserServiceImpl.add_to_checkoutList(list,book_copy.getId(),bookName,book_copy.getBook().getISBN());
	     actionContext.getSession().put("check_out_list", list);
	     return "checkoutlist";
    }
	
	public String delete_checkout(){
		 ActionContext actionContext=ActionContext.getContext();
		
		 ArrayList<HashMap<String, Object>> list = (ArrayList<HashMap<String, Object>>)new ArrayList<HashMap<String,Object>>();
		 ArrayList<HashMap<String, Object>> check_out_list = (ArrayList<HashMap<String, Object>>)actionContext.getSession().get("check_out_list");
		 list=libUserServiceImpl.delete_checkout(check_out_list, book_copy.getId());
		 actionContext.getSession().put("check_out_list", list);
		return "delete_checkout";
	}
	
	public String submit_checkout(){
		 ActionContext actionContext=ActionContext.getContext();
		 ArrayList<HashMap<String, Object>> check_out_list=(ArrayList<HashMap<String, Object>>)actionContext.getSession().get("check_out_list");
		 libUserServiceImpl.submit_checkout(check_out_list,user.getId(),user.getQuota());
		 int newQuota=0;
		 newQuota=libUserServiceImpl.get_Quota(user.getId());
         
		 //update user info
		 user=(User) actionContext.getSession().get("user");
  		 user=libUserServiceImpl.update_User_Quota(user, newQuota);
 		 actionContext.getSession().put("user", user);
		 actionContext.getSession().remove("check_out_list");
		 return "submit_checkout";
	}
	
	public String returnbook(){
         libUserServiceImpl.returnBook(book_copy.getId(),user.getId());
         ActionContext actionContext=ActionContext.getContext();
		 int newQuota=0;
         newQuota=libUserServiceImpl.get_Quota(user.getId());
         user=(User) actionContext.getSession().get("user");
         user=libUserServiceImpl.update_User_Quota(user, newQuota);
         actionContext.getSession().put("user", user);
		return "returnbook";
	}
	
	
	public String preOrder(){
		HashMap<String, Object> pre_orderBook=new HashMap<String, Object>();
		pre_orderBook=libUserServiceImpl.get_book_order_info(book_copy.getId(), bookName, book_copy.getBook().getISBN());
		ActionContext actionContext=ActionContext.getContext();
		actionContext.getSession().put("pre_orderBook", pre_orderBook);
		return "preOrder";
	}
	
	public String submit_preOrder(){
		libUserServiceImpl.submit_pre_order(book_copy.getId(), user.getId());
		 ActionContext actionContext=ActionContext.getContext();
		 int new_pre_Quota=0;
	     new_pre_Quota=libUserServiceImpl.get_Pre_Quota(user.getId());
	     user=(User) actionContext.getSession().get("user");
	     user=libUserServiceImpl.update_User_Pre_Quota(user, new_pre_Quota);
	     actionContext.getSession().put("user", user);
	     actionContext.getSession().remove("pre_orderBook");
		return "submit_preOrder";
	}
	
    
	public String delete_preOrder(){
		ActionContext actionContext=ActionContext.getContext();
		actionContext.getSession().remove("pre_orderBook");
		return "delete_preOrder";
	}
	
	public String cancel_preOrder(){
		libUserServiceImpl.cancel_preOrder(book_copy.getId(), user.getId());
		ActionContext actionContext=ActionContext.getContext();
		int new_pre_Quota=0;
		new_pre_Quota=libUserServiceImpl.get_Pre_Quota(user.getId());
		 //update user info
		user=(User) actionContext.getSession().get("user");
	    user=libUserServiceImpl.update_User_Pre_Quota(user, new_pre_Quota);
	    actionContext.getSession().put("user", user);
		return "cancel_preOrder";
	}
	
	
	public String test(){

		return "test";
	}
}
