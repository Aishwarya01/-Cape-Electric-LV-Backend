package com.capeelectric.model;

/**
 *
 * @author capeelectricsoftware
 *
 */

public class FinalReport {

	private String userName;

	private Integer siteId;

	private ReportDetails reportDetails;

	private SupplyCharacteristics supplyCharacteristics;

	private IpaoInspection ipaoInspection;

	private Testing testing;

	private Summary summary;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getSiteId() {
		return siteId;
	}

	public void setSiteId(Integer siteId) {
		this.siteId = siteId;
	}

	public ReportDetails getReportDetails() {
		return reportDetails;
	}

	public void setReportDetails(ReportDetails reportDetails) {
		this.reportDetails = reportDetails;
	}

	public SupplyCharacteristics getSupplyCharacteristics() {
		return supplyCharacteristics;
	}

	public void setSupplyCharacteristics(SupplyCharacteristics supplyCharacteristics) {
		this.supplyCharacteristics = supplyCharacteristics;
	}

	public IpaoInspection getIpaoInspection() {
		return ipaoInspection;
	}

	public void setIpaoInspection(IpaoInspection ipaoInspection) {
		this.ipaoInspection = ipaoInspection;
	}

	public Testing getTesting() {
		return testing;
	}

	public void setTesting(Testing testing) {
		this.testing = testing;
	}

	public Summary getSummary() {
		return summary;
	}

	public void setSummary(Summary summary) {
		this.summary = summary;
	}

}
