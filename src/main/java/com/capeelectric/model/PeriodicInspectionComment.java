package com.capeelectric.model;

import java.io.Serializable;
import java.time.LocalDateTime;

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
 * 
 * @author capeelectricsoftware
 *
 */
@Entity
@Table(name = "periodic_comments_table")
public class PeriodicInspectionComment implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7288035826161063750L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "COMMENTS_ID")
	private Integer commentsId;

	@Column(name = "VIEWER_COMMENT")
	private String viewerComment;
	
	@Column(name = "INSPECTOR_COMMENT")
	private String inspectorComment;

	@Column(name = "VIEWER_UPDATEDATE")
	private LocalDateTime viewerDate;

	@Column(name = "INSPECTOR_UPDATEDATE")
	private LocalDateTime inspectorDate;

	@Column(name = "COMMENT_APPROVE_OR_REJECT")
	private String approveOrReject;
	
	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "PERIODIC_INSPECTION_ID")
	private PeriodicInspection periodicInspection;

	public Integer getCommentsId() {
		return commentsId;
	}

	public void setCommentsId(Integer commentsId) {
		this.commentsId = commentsId;
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

	public LocalDateTime getViewerDate() {
		return viewerDate;
	}

	public void setViewerDate(LocalDateTime viewerDate) {
		this.viewerDate = viewerDate;
	}

	public LocalDateTime getInspectorDate() {
		return inspectorDate;
	}

	public void setInspectorDate(LocalDateTime inspectorDate) {
		this.inspectorDate = inspectorDate;
	}

	public String getApproveOrReject() {
		return approveOrReject;
	}

	public void setApproveOrReject(String approveOrReject) {
		this.approveOrReject = approveOrReject;
	}

	public PeriodicInspection getPeriodicInspection() {
		return periodicInspection;
	}

	public void setPeriodicInspection(PeriodicInspection periodicInspection) {
		this.periodicInspection = periodicInspection;
	}

}
