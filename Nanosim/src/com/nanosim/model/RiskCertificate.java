package com.nanosim.model;

import java.io.Serializable;

import java.util.Date;

public class RiskCertificate implements Serializable {

	private static final long serialVersionUID = 2172607453695003204L;
	
	private int certificateId;
	private int certificateTypeId;
	private int riskReduction;
	private String title;
	private int cost;
	private int groupId;
	private String groupName;
	private Date submitted;
	private int timeLeft;

	public RiskCertificate() {
	}

	public int getCertificateId() {
		return this.certificateId;
	}

	public void setCertificateId(int certificateId) {
		this.certificateId = certificateId;
	}

	public int getCertificateTypeId() {
		return this.certificateTypeId;
	}

	public void setCertificateTypeId(int certificateTypeId) {
		this.certificateTypeId = certificateTypeId;
	}

	public int getCost() {
		return this.cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public int getGroupId() {
		return this.groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public Date getSubmitted() {
		return this.submitted;
	}

	public void setSubmitted(Date submitted) {
		this.submitted = submitted;
	}

	public int getTimeLeft() {
		return this.timeLeft;
	}

	public void setTimeLeft(int timeLeft) {
		this.timeLeft = timeLeft;
	}

	public void setRiskReduction(int riskReduction) {
		this.riskReduction = riskReduction;
	}

	public int getRiskReduction() {
		return riskReduction;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getGroupName() {
		return groupName;
	}

}