package org.wm.timebox.app.bo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Project {

	public Project(){
		super();
	}
	
	public Project(String aName){
		setName(aName);
	}
	
	@Id
	@GeneratedValue(generator = "globalId")
	private Long id;

	private String name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
