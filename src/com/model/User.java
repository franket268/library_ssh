package com.model;



public class User{
    private int id;  
    private String username="";   
    private String password=""; 

    private int role =0;  
    private int quota =0;  
    private int keep_days=0;
    private int preorder_quota=0;
    public User(){
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}



	public int getKeep_days() {
		return keep_days;
	}

	public void setKeep_days(int keep_days) {
		this.keep_days = keep_days;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	public int getQuota() {
		return quota;
	}

	public void setQuota(int quota) {
		this.quota = quota;
	}

	public int getPreorder_quota() {
		return preorder_quota;
	}

	public void setPreorder_quota(int preorder_quota) {
		this.preorder_quota = preorder_quota;
	}

}
