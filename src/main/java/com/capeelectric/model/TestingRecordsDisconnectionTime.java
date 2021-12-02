package com.capeelectric.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * 
 * @author capeelectricsoftware
 *
 */
@Entity
@Table(name = "testing_records_disconnectiontime")
public class TestingRecordsDisconnectionTime implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "DISCONNECTION_TIME_ID")
	private Integer disconnectionTimeId;

	@Column(name = "DISCONNECTION_TIME")
	private String disconnectionTime;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "TESTING_RECORD_ID")
	private TestingRecords testingRecords;

	public Integer getDisconnectionTimeId() {
		return disconnectionTimeId;
	}

	public void setDisconnectionTimeId(Integer disconnectionTimeId) {
		this.disconnectionTimeId = disconnectionTimeId;
	}

	public String getDisconnectionTime() {
		return disconnectionTime;
	}

	public void setDisconnectionTime(String disconnectionTime) {
		this.disconnectionTime = disconnectionTime;
	}

	public TestingRecords getTestingRecords() {
		return testingRecords;
	}

	public void setTestingRecords(TestingRecords testingRecords) {
		this.testingRecords = testingRecords;
	}

}
