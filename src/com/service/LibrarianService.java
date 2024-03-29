package com.service;

import com.model.Book;




public interface LibrarianService extends UserService {

	/**
	 * 
	 * @param book
	 * 
	 * @return book (inserted book with ID generated by database, if return is
	 *         null then fail)
	 */

	public void add_Book(String ISBN,String name,String author,String description,String location);

	/**
	 * 
	 * @param book
	 *            (must have id)
	 */

	public void delete_Book(Book book);

	/**
	 * 
	 * @param book
	 *            (must have id, need verify before update)
	 */

	public void update_Book(String ISBN,String name,String author,String description,String location);

}
