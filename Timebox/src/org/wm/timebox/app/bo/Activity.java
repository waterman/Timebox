package org.wm.timebox.app.bo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Type;
import org.joda.time.Duration;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

@Entity
public class Activity {

	@OneToOne
	private Sponsor sponsor;

	@OneToOne
	private Project project;

	@Id
	@GeneratedValue(generator = "globalId")
	private Long id;

	@Column(nullable = false)
	@Type(type = UserType.LOCAL_DATE)
	private LocalDate date;
	@Type(type = UserType.LOCAL_TIME)
	private LocalTime start;
	@Type(type = UserType.LOCAL_TIME)
	private LocalTime end;
	@Type(type = UserType.DURATION)
	private Duration pauseInMinutes;

	private String summary;

	public Long getId() {
		return id;
	}

	public void setId(Long aId) {
		id = aId;
	}

	public Duration getPauseInMinutes() {
		return pauseInMinutes;
	}

	public void setPauseInMinutes(Duration aPauseInMinutes) {
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

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate aDate) {
		date = aDate;
	}

	public LocalTime getStart() {
		return start;
	}

	public void setStart(LocalTime aStart) {
		start = aStart;
	}

	public LocalTime getEnd() {
		return end;
	}

	public void setEnd(LocalTime aEnd) {
		end = aEnd;
	}

}
