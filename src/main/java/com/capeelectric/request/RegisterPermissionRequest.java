package com.capeelectric.request;

public class RegisterPermissionRequest {

	private String adminUserName;

	private String permission;

	private Integer RegisterId;

	public String getAdminUserName() {
		return adminUserName;
	}

	public void setAdminUserName(String adminUserName) {
		this.adminUserName = adminUserName;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	public Integer getRegisterId() {
		return RegisterId;
	}

	public void setRegisterId(Integer registerId) {
		RegisterId = registerId;
	}

}
