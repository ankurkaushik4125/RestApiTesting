package com.qa.data;

public class Users {
// This  class has user post payload which will be send in json
	
	String name;
	String job;
	String id;
	String createdAt;
	
	public Users()
	{
	
	}
	
	public Users(String name,String job)
	{
		
		this.name=name;
		this.job=job;
	}


	// create the getter and setter methods
	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getJob() {
		return job;
	}


	public void setJob(String job) {
		this.job = job;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
	
}
