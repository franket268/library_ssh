package com.action;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import com.model.User;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.service.impl.LibUserServiceImpl;
import com.service.impl.UserServiceImpl;


public class UserAction extends ActionSupport {
	private User user;
	private String requestFrom;
	private UserServiceImpl userServiceImpl;
	private LibUserServiceImpl libUserServiceImpl=new LibUserServiceImpl();
	
	
	
	public void setUserServiceImpl(UserServiceImpl userServiceImpl) {
		this.userServiceImpl = userServiceImpl;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	

	
	public String getRequestFrom() {
		return requestFrom;
	}

	public void setRequestFrom(String requestFrom) {
		this.requestFrom = requestFrom;
	}

	public String login(){
		user=userServiceImpl.login(user);
        if(user.getUsername().equals(""))
        	return "loginFail";
        else{
        	ActionContext actionContext=ActionContext.getContext();
    		actionContext.getSession().put("user", user);
    		if(requestFrom.equals("preOrder")){
        	   return "loginFromPreOrder";
    		}
    		else if(requestFrom.equals("checkout")){
    			return "loginFromCheckout";
    		}
    		else {
    			return LOGIN;
    		}
        }
	}
	
	public String logout(){
		ActionContext actionContext=ActionContext.getContext();
		actionContext.getSession().remove("user");
		actionContext.getSession().remove("check_out_list");
		actionContext.getSession().remove("pre_orderBook");
		actionContext.getSession().remove("book_detail_list");
		return "login";
	}
	
	//see the books that user has borrowed or pre-order
	public String getBookRecord() throws ClassNotFoundException, IOException, SQLException{
		 ActionContext actionContext=ActionContext.getContext();
		 user=(User)actionContext.getSession().get("user");
		 ArrayList<HashMap<String, Object>> borrowlist=new ArrayList<HashMap<String,Object>>();
		 borrowlist=libUserServiceImpl.get_my_loanRecord(user.getId());
		 
		 HashMap<String, Object> preOrderRecord=new HashMap<String, Object>();
		 preOrderRecord=libUserServiceImpl.get_preOrderRecord(user.getId());
		 
		
		 actionContext.put("borrowlist",borrowlist);
		 actionContext.put("preOrderRecord",preOrderRecord);
		 return "getBookRecord";
	}
	


}
