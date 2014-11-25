package com.service;

import java.util.ArrayList;
import java.util.HashMap;

import com.model.Book;
import com.model.User;


public interface UserService {

	public User login(User user);

	public void logout(String username);

	public ArrayList<Book> searchBook(String ref,String key);


}
