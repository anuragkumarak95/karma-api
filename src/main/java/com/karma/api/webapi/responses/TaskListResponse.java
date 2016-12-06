package com.karma.api.webapi.responses;

import java.util.ArrayList;

import com.karma.api.dao.TaskDao;

public class TaskListResponse {
	
	private String status;
	private String error;
	private int task_count;
	private ArrayList<TaskDao> taskList;
	
	public TaskListResponse(){
		
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public int getTask_count() {
		return task_count;
	}

	public void setTask_count(int task_count) {
		this.task_count = task_count;
	}

	public ArrayList<TaskDao> getTaskList() {
		return taskList;
	}


	public void setTaskList(ArrayList<TaskDao> taskList) {
		this.taskList = taskList;
	}
	

	//Chained method implementations.
	public TaskListResponse addStatus(String status) {
		this.status = status;
		return this;
	}
	
	public TaskListResponse addError(String error) {
		this.error = error;
		return this;
	}
	
	public TaskListResponse addTaskList(ArrayList<TaskDao> taskList) {
		this.taskList = taskList;
		return this;
	}
	
	public TaskListResponse addTask_count(int task_count) {
		this.task_count = task_count;
		return this;
	}
	

}
