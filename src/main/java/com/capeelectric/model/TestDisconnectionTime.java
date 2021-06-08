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

import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * 
 * @author capeelectricsoftware
 *
 */
@Entity
@Table(name = "TESTING_DISCONNECTIONTIME_TABLE")
public class TestDisconnectionTime implements Serializable {

	 
	private static final long serialVersionUID = 1L;


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "TESTING_DISCONNECTIONTIME_ID")
	private Integer disconnectionTimeId;
	
	@Column(name = "R_B")
	private String rbDisconnect;
	
	@Column(name = "Y_B")
	private String ybDisconnect;
	
	@Column(name = "R_N")
 	private String rnDisconnect;
	
	@Column(name = "Y_N")
	private String ynDisconnect;
	
	@Column(name = "B_N")
	private String bnDisconnect;
	
	@Column(name = "R_PE")
	private String rpeDisconnect;
	
	@Column(name = "Y_PE")
	private String ypeDisconnect;
	
	@Column(name = "B_PE")
	private String bpeDisconnect;
	
	@JsonBackReference
	@OneToOne
	@JoinColumn(name = "TESTING_ID")
	private Testing testing;

	public Integer getDisconnectionTimeId() {
		return disconnectionTimeId;
	}

	public void setDisconnectionTimeId(Integer disconnectionTimeId) {
		this.disconnectionTimeId = disconnectionTimeId;
	}

	public String getRbDisconnect() {
		return rbDisconnect;
	}

	public void setRbDisconnect(String rbDisconnect) {
		this.rbDisconnect = rbDisconnect;
	}

	public String getYbDisconnect() {
		return ybDisconnect;
	}

	public void setYbDisconnect(String ybDisconnect) {
		this.ybDisconnect = ybDisconnect;
	}

	public String getRnDisconnect() {
		return rnDisconnect;
	}

	public void setRnDisconnect(String rnDisconnect) {
		this.rnDisconnect = rnDisconnect;
	}

	public String getYnDisconnect() {
		return ynDisconnect;
	}

	public void setYnDisconnect(String ynDisconnect) {
		this.ynDisconnect = ynDisconnect;
	}

	public String getBnDisconnect() {
		return bnDisconnect;
	}

	public void setBnDisconnect(String bnDisconnect) {
		this.bnDisconnect = bnDisconnect;
	}

	public String getRpeDisconnect() {
		return rpeDisconnect;
	}

	public void setRpeDisconnect(String rpeDisconnect) {
		this.rpeDisconnect = rpeDisconnect;
	}

	public String getYpeDisconnect() {
		return ypeDisconnect;
	}

	public void setYpeDisconnect(String ypeDisconnect) {
		this.ypeDisconnect = ypeDisconnect;
	}

	public String getBpeDisconnect() {
		return bpeDisconnect;
	}

	public void setBpeDisconnect(String bpeDisconnect) {
		this.bpeDisconnect = bpeDisconnect;
	}

	public Testing getTesting() {
		return testing;
	}

	public void setTesting(Testing testing) {
		this.testing = testing;
	}

}
