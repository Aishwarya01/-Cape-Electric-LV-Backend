package com.capeelectric.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * 
 * @author capeelectricsoftware
 *
 */
@Entity
@Table(name = "APPLICATIONTYPES")
public class ApplicationTypes {

	@Id
	private int id;
	
	@Column(name = "APPLICATION")
	private String type;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}