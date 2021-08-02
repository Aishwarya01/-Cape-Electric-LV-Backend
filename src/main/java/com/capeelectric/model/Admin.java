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
	private int adminId;
    @Column(name="first_name")
	private String firstname;
	@Column(name="last_name")
	private String lastname;
	@Column(name="email")
	private String email;
	@Column(name="user_name")
	private String username;
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
	
	public Admin(int adminId, String firstname, String lastname, String email, String usertype, String password, String username, boolean adminexist,  LocalDateTime creationdate, LocalDateTime updateddate) {
		super();
		this.adminId = adminId;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.usertype = usertype;
		this.password = password;
		this.username = username;
		this.adminexist = adminexist;
		this.creationdate = creationdate;
		this.updateddate = updateddate;
	}

	
	public int getAdminId() {
		return adminId;
	}

	public void setAdminId(int adminId) {
		this.adminId = adminId;
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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