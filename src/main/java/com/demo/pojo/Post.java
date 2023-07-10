package com.demo.pojo;

public class Post {

	private String name;

	private String job;


	public Post(String name, String job) {
		super();
		this.name = name;
		this.job = job;
	}
	public void setName(String name){
		this.name = name;
	}
	public String getName(){
		return this.name;
	}
	public void setJob(String job){
		this.job = job;
	}
	public String getJob(){
		return this.job;
	}
}



