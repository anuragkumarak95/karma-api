package com.karma.api.webapi;

import java.util.ArrayList;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.karma.api.dao.TaskDao;
import com.karma.api.dao.subs.MiniTask;
import com.karma.api.service.TaskService;
import com.karma.api.webapi.resources.JobFlags;
import com.karma.api.webapi.resources.JobInterface;
import com.karma.api.webapi.resources.JobPool;
import com.karma.api.webapi.resources.TaskListResponse;
import com.karma.api.webapi.resources.jobs.TaskAddJob;


@RestController
public class WebService {
	
	@Autowired TaskService taskService;
	
	@RequestMapping(value="/hi",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public TaskListResponse hi(){
		ArrayList<MiniTask> arr = new ArrayList<MiniTask>();
		arr.add(new MiniTask());
		arr.add(new MiniTask());
		arr.add(new MiniTask());
		arr.add(new MiniTask());
		
		//System.out.println(JobFlags.valueOf(JobFlags.TASK_ADD));
		
		ArrayList<TaskDao> taskList = new ArrayList<TaskDao>();
		taskList.add(new TaskDao());
		TaskDao task = new TaskDao();
		task.setT_title("hello");
		task.setT_miniTaskList(arr);
		
		//testing jobPool implementation.
		TaskAddJob addjob = new TaskAddJob();
		addjob.setTask(task);
		
		ArrayList<Object> jobList = new ArrayList<Object>();
		jobList.add(addjob);
		JobPool jobs = new JobPool();
		jobs.setJobList(jobList);
		jobs.setJobCount(jobList.size());
		
		this.taskService.jobPool(jobs);
		
		return this.taskService.addTask(task);
	}
	
	@RequestMapping(value="/get/{t_auth}/{t_id}",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public TaskListResponse getTaskService(@PathVariable("t_auth") String t_auth,@PathVariable("t_id") int t_id){
		return this.taskService.getTask(t_id);
	}
	
	@RequestMapping(value="/get/{t_auth}",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public TaskListResponse getTaskByAuthorService(@PathVariable("t_auth") String t_auth){
		return this.taskService.getTaskByAuthor(t_auth);
	}
	
	@RequestMapping(value="/delete/{t_id}",method=RequestMethod.DELETE,produces=MediaType.APPLICATION_JSON_VALUE)
	public TaskListResponse deleteTaskService(@PathVariable("t_id") int t_id){
		return this.taskService.deleteTask(t_id);
	}
	
	@RequestMapping(value="/update",method=RequestMethod.PUT,consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)	
	public TaskListResponse updateTaskService(@RequestBody TaskDao task){
		return this.taskService.updateTask(task);
	}
		
	//Experimentation services for network task pooling interface.(Optional implementation codes.)
	@RequestMapping(value="/pool",method=RequestMethod.PUT,consumes=MediaType.APPLICATION_JSON_VALUE)
	public void JobPoolManager(@RequestBody JobPool pool){
		this.taskService.jobPool(pool);
	}
}
