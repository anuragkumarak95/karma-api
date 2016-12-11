package com.karma.api.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.tools.ant.Task;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.karma.api.dao.TaskDao;
import com.karma.api.dao.subs.MiniTask;
import com.karma.api.webapi.resources.JobInterface;
import com.karma.api.webapi.resources.JobPool;
import com.karma.api.webapi.resources.TaskListResponse;
import com.karma.api.webapi.resources.jobs.TaskAddJob;

@Service
@Transactional
public class TaskService {

	@Autowired
	private SessionFactory sessionFactory;
	
	private String TAG = "[TaskService] : ",
			addOK = "ADD OK",
			statusOK = "STATUS OK",
			deleteOK = "DELETE OK",
			addFailed = "ADD FAILED",
			statusFailed = "STATUS FAILED",
			deleteFailed = "DELETE FAILED",
			updateOK = "UPDATE OK",
			updateFailed = "UPDATE FAILED";
	
	//test function for hibernate session flow.
	public void go(){
		MiniTask tk = new MiniTask();
		tk.setMt_status(false);
		tk.setMt_content("hello");
		System.err.println("yess");
		this.sessionFactory.getCurrentSession().save(new TaskDao());
	}
	
	// utility func: Add task to Db.
	public TaskListResponse addTask(TaskDao task){
		if(task.getT_title()==null){
			return new TaskListResponse().addStatus(addFailed).addError("Task Deails NOT properly available.");
		}
		
		this.sessionFactory.getCurrentSession().save(task);
		ArrayList<TaskDao> taskList = new ArrayList<TaskDao>();
		taskList.add(task);
		return new TaskListResponse().addStatus(addOK).addTaskList(taskList).addTask_count(taskList.size());
	}
	
	// utility func: delete task by task_ID
	public TaskListResponse deleteTask(int t_id){
		TaskDao task = (TaskDao)this.sessionFactory.getCurrentSession().get(TaskDao.class, t_id);
		
		if(task != null){
			this.sessionFactory.getCurrentSession().delete(task);
			return new TaskListResponse().addStatus(deleteOK);
		}
		else{
			return new TaskListResponse().addStatus(deleteFailed).addError("No task Found.");
		}
		
	}
	
	//utility func: Update task providing the task.
	public TaskListResponse updateTask(TaskDao task){
		
		TaskDao task_temp = (TaskDao)this.sessionFactory.getCurrentSession().get(TaskDao.class, task.getT_id());
		if(task_temp!= null){
			task_temp.setT_title(task.getT_title());
			task_temp.setT_author(task.getT_author());
			task_temp.setT_miniTaskList(task.getT_miniTaskList());
			this.sessionFactory.getCurrentSession().update(task_temp);
			return new TaskListResponse().addStatus(updateOK);
		}else{
			return new TaskListResponse().addStatus(updateFailed).addError("No such Task Found.");
		}
				
	}
	
	//utility func: read a task form server.
	public TaskListResponse getTask(int t_id){
		TaskDao task = (TaskDao) this.sessionFactory.getCurrentSession().get(TaskDao.class, t_id);
		
		if(task != null){
			ArrayList<TaskDao> taskList = new ArrayList<TaskDao>();
			taskList.add(task);
			return new TaskListResponse().addStatus(statusOK).addTaskList(taskList);
		}
		else{
			return new TaskListResponse().addStatus(statusFailed).addError("No task Found.");
		}
	}
	
	//utility func: read task list filtered by author.
	public TaskListResponse getTaskByAuthor(String t_author){
		List<TaskDao> taskList = this.sessionFactory.getCurrentSession().createCriteria(TaskDao.class)
				.add(Restrictions.eq("t_author", t_author)).list();
		
		if(taskList.isEmpty()){
			return new TaskListResponse().addStatus(statusFailed).addError("No task Found.");
		}else{
			return new TaskListResponse().addStatus(statusOK).addTask_count(taskList.size()).addTaskList(taskList);
		}
	}
	
	public void jobPool(JobPool pool){
		System.err.println("yupp");
		for (Object job : pool.getJobList()){
			if(job.getClass() == LinkedHashMap.class){
				
				addTask(new ObjectMapper().convertValue(job, TaskAddJob.class).getTask());
				System.err.println("task Added");
			}
		}
	}
	
}
