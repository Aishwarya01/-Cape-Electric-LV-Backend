package com.capeelectric.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
@Table(name = "testing_reports_table")

@NamedQueries(value = {
		@NamedQuery(name = "TestingReportRepository.findByUserNameAndSiteId", query = "Select t From TestingReport t Where t.userName=:userName and t.siteId=:siteId"),
        @NamedQuery(name = "TestingReportRepository.findBySiteId", query = "Select t From TestingReport t Where t.siteId=:siteId") })
public class TestingReport implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "TESTING_REPORT_ID")
	private Integer testingReportId;
	
	@Column(name = "SITE_ID")
	private Integer siteId;
	
	@Column(name = "USER_NAME")
	private String userName;
	
	@Column(name = "VIEWER_COMMENT")
	private String viewerComment;
	
	@Column(name = "INSPECTOR_COMMENT")
	private String inspectorComment;

	@Column(name = "COMMENT_APPROVE_OR_REJECT")
	private String commentApproveOrReject;
	
	@Column(name = "CREATED_DATE")
	private LocalDateTime createdDate;
	
	@Column(name = "CREATED_BY")
	private String createdBy;

	@Column(name = "UPDATED_BY")
	private String updatedBy;
	
	@Column(name = "UPDATED_DATE")
	private LocalDateTime updatedDate;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "testingReport", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Testing> testing;

	public Integer getTestingReportId() {
		return testingReportId;
	}

	public void setTestingReportId(Integer testingReportId) {
		this.testingReportId = testingReportId;
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

	public String getViewerComment() {
		return viewerComment;
	}

	public void setViewerComment(String viewerComment) {
		this.viewerComment = viewerComment;
	}

	public String getInspectorComment() {
		return inspectorComment;
	}

	public void setInspectorComment(String inspectorComment) {
		this.inspectorComment = inspectorComment;
	}

	public String getCommentApproveOrReject() {
		return commentApproveOrReject;
	}

	public void setCommentApproveOrReject(String commentApproveOrReject) {
		this.commentApproveOrReject = commentApproveOrReject;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public LocalDateTime getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(LocalDateTime updatedDate) {
		this.updatedDate = updatedDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public List<Testing> getTesting() {
		return testing;
	}

	public void setTesting(List<Testing> testing) {
		this.testing = testing;
	}

}
