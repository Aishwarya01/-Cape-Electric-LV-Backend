package com.capeelectric.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="admin_table")
public class Admin {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="admin_id")
	private int id;
    @Column(name="first_name")
	private String firstname;
	@Column(name="last_name")
	private String lastname;
	@Column(name="email")
	private String email;
	@Column(name="admin_name")
	private String adminname;
	@Column(name="user_type")
	private String usertype;
	@Column(name="password")
	private String password;
	@Column(name="admin_exist")
	private boolean adminexist;
	@Column(name = "creation_date")
	private LocalDateTime creationdate;
	@Column(name = "updated_date")
	private LocalDateTime updateddate;
	
	public Admin() {
		super();
	}
	
	public Admin(int id, String firstname, String lastname, String email, String usertype, String password, String adminname, boolean adminexist,  LocalDateTime creationdate, LocalDateTime updateddate) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.usertype = usertype;
		this.password = password;
		this.adminname = adminname;
		this.adminexist = adminexist;
		this.creationdate = creationdate;
		this.updateddate = updateddate;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUsertype() {
		return usertype;
	}
	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getAdminname() {
		return adminname;
	}

	public void setAdminname(String adminname) {
		this.adminname = adminname;
	}

	public boolean isAdminexist() {
		return adminexist;
	}

	public void setAdminexist(boolean adminexist) {
		this.adminexist = adminexist;
	}

	public LocalDateTime getCreationdate() {
		return creationdate;
	}

	public void setCreationdate(LocalDateTime creationdate) {
		this.creationdate = creationdate;
	}

	public LocalDateTime getUpdateddate() {
		return updateddate;
	}

	public void setUpdateddate(LocalDateTime updateddate) {
		this.updateddate = updateddate;
	}
	
	
}