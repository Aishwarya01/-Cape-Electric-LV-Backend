package com.capeelectric.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 * 
 * @author capeelectricsoftware
 *
 */
@Entity
@Table(name = "TESTING_TABLE")
public class Testing implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "TESTING_ID")
	private Integer testingId;
	
	@Column(name = "LOCATION_NUMBER")
	private Integer locationNumber;
	
	@Column(name = "LOCATION_NAME")
	private String locationName;

	@Column(name = "TEST_ENGINEER_NAME")
	private String testEngineerName;

	@Column(name = "DATE")
	private String date;

	@JsonManagedReference
	@OneToMany(mappedBy = "testing", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<TestDistribution> testDistribution;

	@JsonManagedReference
	@OneToMany(mappedBy = "testing", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<TestingRecords> testingRecords;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "testingReport", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<TestingEquipment> testingEquipment;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "TESTING_REPORT_ID")
	private TestingReport testingReport;

	public Integer getTestingId() {
		return testingId;
	}

	public void setTestingId(Integer testingId) {
		this.testingId = testingId;
	}

 	public Integer getLocationNumber() {
		return locationNumber;
	}

	public void setLocationNumber(Integer locationNumber) {
		this.locationNumber = locationNumber;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public String getTestEngineerName() {
		return testEngineerName;
	}

	public void setTestEngineerName(String testEngineerName) {
		this.testEngineerName = testEngineerName;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public List<TestDistribution> getTestDistribution() {
		return testDistribution;
	}

	public void setTestDistribution(List<TestDistribution> testDistribution) {
		this.testDistribution = testDistribution;
	}

	public List<TestingRecords> getTestingRecords() {
		return testingRecords;
	}

	public void setTestingRecords(List<TestingRecords> testingRecords) {
		this.testingRecords = testingRecords;
	}

	public TestingReport getTestingReport() {
		return testingReport;
	}

	public void setTestingReport(TestingReport testingReport) {
		this.testingReport = testingReport;
	}

	public List<TestingEquipment> getTestingEquipment() {
		return testingEquipment;
	}

	public void setTestingEquipment(List<TestingEquipment> testingEquipment) {
		this.testingEquipment = testingEquipment;
	}
}
