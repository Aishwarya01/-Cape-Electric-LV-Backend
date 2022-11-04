package com.capeelectric.model.licence;

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

import com.capeelectric.model.Register;
import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 * 
 * @author capeelectricsoftware
 *
 */

public class LpsRegister extends Register{
	
	@JsonManagedReference
	@OneToMany(mappedBy = "lpsRegister", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
 	private List<LpsLicence> lpsLicence;

	public List<LpsLicence> getLpsLicence() {
		return lpsLicence;
	}

	public void setLpsLicence(List<LpsLicence> lpsLicence) {
		this.lpsLicence = lpsLicence;
	}

}