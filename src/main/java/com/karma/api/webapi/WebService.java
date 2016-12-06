package com.karma.api.webapi;

import java.util.ArrayList;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.karma.api.dao.TaskDao;
import com.karma.api.dao.subs.MiniTask;
import com.karma.api.service.TaskService;
import com.karma.api.webapi.responses.TaskListResponse;


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
		
		this.taskService.go();
		
		ArrayList<TaskDao> taskList = new ArrayList<TaskDao>();
		taskList.add(new TaskDao());
		return new TaskListResponse().addStatus("200").addTaskList(taskList).addTask_count(taskList.size());
	}
}
