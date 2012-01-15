package org.wm.timebox.app.bo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Sponsor {
	
	public Sponsor(){
		super();
	}
	
	public Sponsor(String aName){
		setName(aName);
	}

	@Id
	@GeneratedValue(generator="globalId")
	private Long id;

	private String name;

	public Long getId() {
		return id;
	}

	public void setId(Long aId) {
		id = aId;
	}

	public String getName() {
		return name;
	}

	public void setName(String aName) {
		name = aName;
	}
}
