package com.model;

public class LoanRecord {
     private int Id;
     private int bookId;
     private User user;
     private String checkOutDate;
     private int isReturn;
     private String returnDate;
     private String lastUpdateTime;
     private String expireDate;
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
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
	public String getCheckOutDate() {
		return checkOutDate;
	}
	public void setCheckOutDate(String checkOutDate) {
		this.checkOutDate = checkOutDate;
	}
	public int getIsReturn() {
		return isReturn;
	}
	public void setIsReturn(int isReturn) {
		this.isReturn = isReturn;
	}
	public String getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
	}
	public String getLastUpdateTime() {
		return lastUpdateTime;
	}
	public void setLastUpdateTime(String lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
	public String getExpireDate() {
		return expireDate;
	}
	public void setExpireDate(String expireDate) {
		this.expireDate = expireDate;
	}
     
}
