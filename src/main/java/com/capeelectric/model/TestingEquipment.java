/**
 * 
 */
package com.capeelectric.model;

import java.io.Serializable;
import java.util.Date;

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
 * @author capeelectricsoftware
 *
 */

@Entity
@Table(name = "TESTING_EQUIPMENT_TABLE")
public class TestingEquipment implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "EQUIPMENT_ID")
	private Integer equipmentId;
	
	@Column(name = "EQUIPMENT_NAME")
	private String equipmentName;

	@Column(name = "EQUIPMENT_MAKE")
	private String equipmentMake;

	@Column(name = "EQUIPMENT_MODEL")
	private String equipmentModel;

	@Column(name = "EQUIPMENT_SERIAL_NO")
	private String equipmentSerialNo;
	
	@Column(name = "EQUIPMENT_CALIBRATION_DUE_DATE")
	private Date equipmentCalibrationDueDate;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "TESTING_REPORT_ID")
	private TestingReport testingReport;

	public Integer getEquipmentId() {
		return equipmentId;
	}

	public void setEquipmentId(Integer equipmentId) {
		this.equipmentId = equipmentId;
	}

	public String getEquipmentName() {
		return equipmentName;
	}

	public void setEquipmentName(String equipmentName) {
		this.equipmentName = equipmentName;
	}

	public String getEquipmentMake() {
		return equipmentMake;
	}

	public void setEquipmentMake(String equipmentMake) {
		this.equipmentMake = equipmentMake;
	}

	public String getEquipmentModel() {
		return equipmentModel;
	}

	public void setEquipmentModel(String equipmentModel) {
		this.equipmentModel = equipmentModel;
	}

	public String getEquipmentSerialNo() {
		return equipmentSerialNo;
	}

	public void setEquipmentSerialNo(String equipmentSerialNo) {
		this.equipmentSerialNo = equipmentSerialNo;
	}

	public Date getEquipmentCalibrationDueDate() {
		return equipmentCalibrationDueDate;
	}

	public void setEquipmentCalibrationDueDate(Date equipmentCalibrationDueDate) {
		this.equipmentCalibrationDueDate = equipmentCalibrationDueDate;
	}

	public TestingReport getTestingReport() {
		return testingReport;
	}

	public void setTestingReport(TestingReport testingReport) {
		this.testingReport = testingReport;
	}
	
	
	

}
