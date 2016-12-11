package com.karma.api.webapi.resources.jobs;

import com.karma.api.dao.TaskDao;
import com.karma.api.webapi.resources.JobFlags;
import com.karma.api.webapi.resources.JobInterface;

public class TaskAddJob{

	
	private TaskDao task;

	public TaskAddJob(){}

	public TaskDao getTask() {
		return task;
	}

	public void setTask(TaskDao task) {
		this.task = task;
	}

}
