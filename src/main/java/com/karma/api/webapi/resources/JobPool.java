package com.karma.api.webapi.resources;

import java.util.ArrayList;

public class JobPool {
	
	private int jobCount;
	private ArrayList<Object> jobList;
	
	public JobPool(){}
	
	public int getJobCount() {
		return jobCount;
	}
	public void setJobCount(int jobCount) {
		this.jobCount = jobCount;
	}
	public ArrayList<Object> getJobList() {
		return jobList;
	}
	public void setJobList(ArrayList<Object> jobList) {
		this.jobList = jobList;
	}
	
	

}
