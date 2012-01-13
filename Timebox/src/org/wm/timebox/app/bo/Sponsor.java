package org.wm.timebox.app.bo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Sponsor {

	@Id
	@GeneratedValue(generator="globalId")
	private Long id;

	private String name;
}
