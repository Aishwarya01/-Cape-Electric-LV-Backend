package com.capeelectric.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "SITE_PERSON_TABLE")
@JsonIdentityInfo(
		   generator = ObjectIdGenerators.PropertyGenerator.class,
		   property = "personId")
@NamedQueries(value = {
		@NamedQuery(name = "SitePersonsRepository.RepositoryfindByPersonInchargeEmail", query = "select p.personInchargeEmail from SitePersons p where p.personInchargeEmail=:personInchargeEmail") })
public class SitePersons implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "PERSON_ID")
	private Integer personId;

	@Column(name = "PERSON_INCHARGE")
	private String personIncharge;

	@Column(name = "E_MAIL")
	private String personInchargeEmail;

	@Column(name = "DESIGNATION")
	private String designation;
	
	@Column(name = "IN_ACTIVE")
	private Boolean inActive;

	@Column(name = "CONTACT_NO")
	private String contactNo;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "SITE_ID")
	private Site Site;

	public Integer getPersonId() {
		return personId;
	}

	public void setPersonId(Integer personId) {
		this.personId = personId;
	}

	public String getPersonIncharge() {
		return personIncharge;
	}

	public void setPersonIncharge(String personIncharge) {
		this.personIncharge = personIncharge;
	}

	public String getPersonInchargeEmail() {
		return personInchargeEmail;
	}

	public void setPersonInchargeEmail(String personInchargeEmail) {
		this.personInchargeEmail = personInchargeEmail;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public Site getSite() {
		return Site;
	}

	public void setSite(Site site) {
		Site = site;
	}

	public Boolean getInActive() {
		return inActive;
	}

	public void setInActive(Boolean inActive) {
		this.inActive = inActive;
	}
}
