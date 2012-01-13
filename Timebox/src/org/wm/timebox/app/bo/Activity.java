package org.wm.timebox.app.bo;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Activity {

	@OneToOne
	private Sponsor sponsor;

	@OneToOne
	private Project project;

	@Id
	@GeneratedValue(generator = "globalId")
	private Long id;

	@Temporal(TemporalType.TIME)
	private Date start;

	@Temporal(TemporalType.TIME)
	private Date end;

	private Integer pauseInMinutes;

	private String summary;

	public Long getId() {
		return id;
	}

	public void setId(Long aId) {
		id = aId;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date aFrom) {
		start = aFrom;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date aUntil) {
		end = aUntil;
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

	public Sponsor getSponsor() {
		return sponsor;
	}

	public void setSponsor(Sponsor sponsor) {
		this.sponsor = sponsor;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

}
