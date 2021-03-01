package com.capeelectric.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author capeelectricsoftware
 *
 */
@Entity
@Table(name="user")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="user_id")
	private int id;
	@Column(name="password")
	private String password;
	@Column(name="active")
	private boolean active;
	@Column(name="email")
	private String email;
	@Column(name="first_name")
	private String firstName;
	@Column(name="last_name")
	private String lastName;
	@Column(name="user_name")
	private String userName;
	@Column(name="user_type")
	private String userType;
	@Column(name = "role")
	private String role;
	@Column(name = "creation_date")
	private Date creationDate;
	@Column(name = "updated_date")
	private Date updatedDate;
	@Column(name="user_exist")
	private boolean userExist;
	public User() {
    }

    public User(User users) {
        this.active = users.isActive();
        this.email = users.getEmail();
        this.firstName = users.getFirstName();
        this.lastName =users.getLastName();
        this.id = users.getId();
        this.password = users.getPassword();
        this.userType = users.getUserType();
        this.userName = users.getEmail();
        this.role = users.getRole();
        this.creationDate = users.getCreationDate();
        this.updatedDate = users.getUpdatedDate();
        this.userExist = users.isUserExist();
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserName() {
		return email;
	}

	public void setUserName(String email) {
		this.userName = email;
	}
	
	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public boolean isUserExist() {
		return userExist;
	}

	public void setUserExist(boolean userExist) {
		this.userExist = userExist;
	}
	
	
}
