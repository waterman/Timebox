package org.wm.timebox.app.bo;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class TimelineItem {

	@OneToOne
	private Customer customer;
	
	@OneToOne
	private Project project;
	
	@Id
	@GeneratedValue(generator="increment")
	@GenericGenerator(name="increment", strategy="increment")
	private Long id;
	
	@Temporal(TemporalType.TIME)
	private Date from;
	
	@Temporal(TemporalType.TIME)
	private Date until;
	
	private Integer pauseInMinutes;
	
	private String summary;

	public Long getId() {
		return id;
	}

	public void setId(Long aId) {
		id = aId;
	}

	public Date getFrom() {
		return from;
	}

	public void setFrom(Date aFrom) {
		from = aFrom;
	}

	public Date getUntil() {
		return until;
	}

	public void setUntil(Date aUntil) {
		until = aUntil;
	}

	public Integer getPauseInMinutes() {
		return pauseInMinutes;
	}

	public void setPauseInMinutes(Integer aPauseInMinutes) {
		pauseInMinutes = aPauseInMinutes;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String aSummary) {
		summary = aSummary;
	}
	
}
