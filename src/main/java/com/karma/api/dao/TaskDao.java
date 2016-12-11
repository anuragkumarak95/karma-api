package com.karma.api.dao;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.karma.api.dao.subs.MiniTask;

@Entity
public class TaskDao {

	@Id @GeneratedValue
	private int t_id;
	
	private String t_title;
	private String t_author;
	
	@ElementCollection(fetch=FetchType.EAGER)
	private Collection<MiniTask> t_miniTaskList = new ArrayList<MiniTask>();

	/*public TaskDao(String t_id, String t_title, String t_author, ArrayList<MiniTask> t_miniTaskList) {
		super();
		this.t_id = t_id;
		this.t_title = t_title;
		this.t_author = t_author;
		this.t_miniTaskList = t_miniTaskList;
	}*/
	
	public TaskDao(){
		
	}

	public int getT_id() {
		return t_id;
	}

	public void setT_id(int t_id) {
		this.t_id = t_id;
	}

	public String getT_title() {
		return t_title;
	}

	public void setT_title(String t_title) {
		this.t_title = t_title;
	}

	public String getT_author() {
		return t_author;
	}

	public void setT_author(String t_author) {
		this.t_author = t_author;
	}

	public Collection<MiniTask> getT_miniTaskList() {
		return t_miniTaskList;
	}

	public void setT_miniTaskList(Collection<MiniTask> t_miniTaskList) {
		this.t_miniTaskList = t_miniTaskList;
	}
	
	
	
}
