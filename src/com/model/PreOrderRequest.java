package com.model;

public class PreOrderRequest {
	private int id;
	private int bookId;
	private User user;
	private String preOrderTime;
	private int isFinish;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getPreOrderTime() {
		return preOrderTime;
	}
	public void setPreOrderTime(String preOrderTime) {
		this.preOrderTime = preOrderTime;
	}
	public int getIsFinish() {
		return isFinish;
	}
	public void setIsFinish(int isFinish) {
		this.isFinish = isFinish;
	}
	
}
