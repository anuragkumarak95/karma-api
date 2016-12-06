package com.karma.api.dao.subs;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class MiniTask {

	@Id @GeneratedValue
	private int mt_id;
	
	private String mt_content;
	private boolean mt_status;
	
	
	
	/*public MiniTask(String mt_id, String mt_content, boolean mt_status) {
		super();
		this.mt_id = mt_id;
		this.mt_content = mt_content;
		this.mt_status = mt_status;
	}*/
	
	public MiniTask(){
		
	}
	
	public int getMt_id() {
		return mt_id;
	}
	public void setMt_id(int mt_id) {
		this.mt_id = mt_id;
	}
	public String getMt_content() {
		return mt_content;
	}
	public void setMt_content(String mt_content) {
		this.mt_content = mt_content;
	}
	public boolean isMt_status() {
		return mt_status;
	}
	public void setMt_status(boolean mt_status) {
		this.mt_status = mt_status;
	}
	
}
