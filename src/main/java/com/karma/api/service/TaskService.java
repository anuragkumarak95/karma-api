package com.karma.api.service;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.karma.api.dao.TaskDao;
import com.karma.api.dao.subs.MiniTask;

@Service
@Transactional
public class TaskService {

	@Autowired
	private SessionFactory sessionFactory;
	
	//test function for hibernate session flow.
	public void go(){
		MiniTask tk = new MiniTask();
		tk.setMt_status(false);
		tk.setMt_content("hello");
		System.err.println("yess");
		this.sessionFactory.getCurrentSession().save(new TaskDao());
	}
}
