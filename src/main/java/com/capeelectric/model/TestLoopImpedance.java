package com.capeelectric.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.capeelectric.exception.PeriodicTestingException;
import com.capeelectric.util.DecimalConversion;
import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * 
 * @author capeelectricsoftware
 *
 */
@Entity
@Table(name = "TESTING_LOOPIMPEDANCE_TABLE")
public class TestLoopImpedance implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "TESTING_LOOPIMPEDANCE_ID")
	private Integer loopImpedanceId;

	@Column(name = "R_B")
	private String rbLoopImpedance;

	@Column(name = "Y_B")
	private String ybLoopImpedance;

	@Column(name = "R_N")
	private String rnLoopImpedance;

	@Column(name = "Y_N")
	private String ynLoopImpedance;

	@Column(name = "B_N")
	private String bnLoopImpedance;

	@Column(name = "R_PE")
	private String rpeLoopImpedance;

	@Column(name = "Y_PE")
	private String ypeLoopImpedance;

	@Column(name = "B_PE")
	private String bpeLoopImpedance;

	@JsonBackReference
	@OneToOne
	@JoinColumn(name = "TESTING_ID")
	private Testing testing;

	public Integer getLoopImpedanceId() {
		return loopImpedanceId;
	}

	public void setLoopImpedanceId(Integer loopImpedanceId) {
		this.loopImpedanceId = loopImpedanceId;
	}

	public String getRbLoopImpedance() {
		return rbLoopImpedance;
	}

	public void setRbLoopImpedance(String rbLoopImpedance) throws PeriodicTestingException {
		this.rbLoopImpedance =  DecimalConversion.convertToDecimal(rbLoopImpedance, "%.3f");
	}

	public String getYbLoopImpedance() {
		return ybLoopImpedance;
	}

	public void setYbLoopImpedance(String ybLoopImpedance) throws PeriodicTestingException {
		this.ybLoopImpedance =  DecimalConversion.convertToDecimal(ybLoopImpedance, "%.3f");
	}

	public String getRnLoopImpedance() {
		return rnLoopImpedance;
	}

	public void setRnLoopImpedance(String rnLoopImpedance) throws PeriodicTestingException {
		this.rnLoopImpedance =  DecimalConversion.convertToDecimal(rnLoopImpedance, "%.3f");
	}

	public String getYnLoopImpedance() {
		return ynLoopImpedance;
	}

	public void setYnLoopImpedance(String ynLoopImpedance) throws PeriodicTestingException {
		this.ynLoopImpedance =  DecimalConversion.convertToDecimal(ynLoopImpedance, "%.3f");
	}

	public String getBnLoopImpedance() {
		return bnLoopImpedance;
	}

	public void setBnLoopImpedance(String bnLoopImpedance) throws PeriodicTestingException {
		this.bnLoopImpedance =  DecimalConversion.convertToDecimal(bnLoopImpedance, "%.3f");
	}

	public String getRpeLoopImpedance() {
		return rpeLoopImpedance;
	}

	public void setRpeLoopImpedance(String rpeLoopImpedance) throws PeriodicTestingException {
		this.rpeLoopImpedance =  DecimalConversion.convertToDecimal(rpeLoopImpedance, "%.3f");
	}

	public String getYpeLoopImpedance() {
		return ypeLoopImpedance;
	}

	public void setYpeLoopImpedance(String ypeLoopImpedance) throws PeriodicTestingException {
		this.ypeLoopImpedance = DecimalConversion.convertToDecimal(ypeLoopImpedance, "%.3f");
	}

	public String getBpeLoopImpedance() {
		return bpeLoopImpedance;
	}

	public void setBpeLoopImpedance(String bpeLoopImpedance) throws PeriodicTestingException {
		this.bpeLoopImpedance = DecimalConversion.convertToDecimal(bpeLoopImpedance, "%.3f");
	}

	public Testing getTesting() {
		return testing;
	}

	public void setTesting(Testing testing) {
		this.testing = testing;
	}
 
}
