package com.capeelectric.model.licence;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

public class LpsLicence extends LicenceTable{

	@Column(name = "CLIENT_NAME")
	private String clientName;
	
	@Column(name = "PROJECT_NAME")
	private String projectName;
	
	@JsonBackReference
	@ManyToOne
    @JoinColumn(name = "REGISTER_ID")
	private LpsRegister lpsRegister;

}
