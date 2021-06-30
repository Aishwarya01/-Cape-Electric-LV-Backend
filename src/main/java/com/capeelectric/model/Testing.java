package com.capeelectric.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 * 
 * @author capeelectricsoftware
 *
 */
@Entity
@Table(name = "TESTING_TABLE")

@NamedQueries(value = {
		@NamedQuery(name = "TestInfoRepository.findByUserNameAndSiteId", query = "Select t From Testing t Where t.userName=:userName and t.siteId=:siteId"),
        @NamedQuery(name = "TestInfoRepository.findBySiteId", query = "Select t From Testing t Where t.siteId=:siteId") })
public class Testing implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "TESTING_ID")
	private Integer testingId;
	
	@Column(name = "SITE_ID")
	private Integer siteId;
	
	@Column(name = "USER_NAME")
	private String userName;
	
	@Column(name = "TEST_ENGINEER_NAME")
	private String testEngineerName;
	
	@Column(name = "DATE")
	private String date;
	
	@Column(name = "DETAILS_TEST_INSTRUMENT")
	private String detailsTestInstrument;
	
	@Column(name = "CONTINUITY")
	private String continuity;
	
	@Column(name = "INSULATION_RESISANCE")
	private String insulationResisance;
	
	@Column(name = "IMPEDANCE")
	private String impedance;
	
	@Column(name = "RCD")
	private String rcd;
	
	@Column(name = "EARTH_ELECTRODE_RESISTANCE")
	private String earthElectrodeResistance;
	
	@Column(name = "DESIGNATION")
	private String designation;
	
	@Column(name = "COMPANY_NAME")
	private String companyName;
	
	@Column(name = "CREATED_DATE")
	private LocalDateTime createdDate;
	
	@JsonManagedReference
	@OneToOne(mappedBy = "testing", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private TestDistribution testDistribution;
	
	@JsonManagedReference
	@OneToOne(mappedBy="testing", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private  TestCircuit testCircuit;
	
	@JsonManagedReference
	@OneToOne(mappedBy="testing", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private  TestConductor testConductor;
	
	@JsonManagedReference
	@OneToOne(mappedBy="testing", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private  TestContinuity testContinuity;
	
	@JsonManagedReference
	@OneToOne(mappedBy="testing", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private  TestVoltage testVoltage;
	
	@JsonManagedReference
	@OneToOne(mappedBy="testing", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private  TestLoopImpedance testLoopImpedance;
	
	@JsonManagedReference
	@OneToOne(mappedBy="testing", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private  TestFaultCurrent testFaultCurrent;
	
	@JsonManagedReference
	@OneToOne(mappedBy="testing", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private  TestDisconnectionTime testDisconnectionTime;
	
	@JsonManagedReference
	@OneToOne(mappedBy="testing", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private  TestRcd testRcd;

	public Integer getTestingId() {
		return testingId;
	}

	public void setTestingId(Integer testingId) {
		this.testingId = testingId;
	}

	public Integer getSiteId() {
		return siteId;
	}

	public void setSiteId(Integer siteId) {
		this.siteId = siteId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	public String getDetailsTestInstrument() {
		return detailsTestInstrument;
	}

	public void setDetailsTestInstrument(String detailsTestInstrument) {
		this.detailsTestInstrument = detailsTestInstrument;
	}

	public String getContinuity() {
		return continuity;
	}

	public void setContinuity(String continuity) {
		this.continuity = continuity;
	}

	public String getInsulationResisance() {
		return insulationResisance;
	}

	public void setInsulationResisance(String insulationResisance) {
		this.insulationResisance = insulationResisance;
	}

	public String getImpedance() {
		return impedance;
	}

	public void setImpedance(String impedance) {
		this.impedance = impedance;
	}

	public String getRcd() {
		return rcd;
	}

	public void setRcd(String rcd) {
		this.rcd = rcd;
	}

	public String getEarthElectrodeResistance() {
		return earthElectrodeResistance;
	}

	public void setEarthElectrodeResistance(String earthElectrodeResistance) {
		this.earthElectrodeResistance = earthElectrodeResistance;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public TestDistribution getTestDistribution() {
		return testDistribution;
	}

	public void setTestDistribution(TestDistribution testDistribution) {
		this.testDistribution = testDistribution;
	}

	public TestCircuit getTestCircuit() {
		return testCircuit;
	}

	public void setTestCircuit(TestCircuit testCircuit) {
		this.testCircuit = testCircuit;
	}

	public TestConductor getTestConductor() {
		return testConductor;
	}

	public void setTestConductor(TestConductor testConductor) {
		this.testConductor = testConductor;
	}

	public TestContinuity getTestContinuity() {
		return testContinuity;
	}

	public void setTestContinuity(TestContinuity testContinuity) {
		this.testContinuity = testContinuity;
	}

	public TestVoltage getTestVoltage() {
		return testVoltage;
	}

	public void setTestVoltage(TestVoltage testVoltage) {
		this.testVoltage = testVoltage;
	}

	public TestLoopImpedance getTestLoopImpedance() {
		return testLoopImpedance;
	}

	public void setTestLoopImpedance(TestLoopImpedance testLoopImpedance) {
		this.testLoopImpedance = testLoopImpedance;
	}

	public TestFaultCurrent getTestFaultCurrent() {
		return testFaultCurrent;
	}

	public void setTestFaultCurrent(TestFaultCurrent testFaultCurrent) {
		this.testFaultCurrent = testFaultCurrent;
	}

	public TestDisconnectionTime getTestDisconnectionTime() {
		return testDisconnectionTime;
	}

	public void setTestDisconnectionTime(TestDisconnectionTime testDisconnectionTime) {
		this.testDisconnectionTime = testDisconnectionTime;
	}

	public TestRcd getTestRcd() {
		return testRcd;
	}

	public void setTestRcd(TestRcd testRcd) {
		this.testRcd = testRcd;
	}
	
}
