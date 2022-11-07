package com.capeelectric.model.licence;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 * 
 * @author capeelectricsoftware
 *
 */
@Entity
public class LpsRegister extends RegisterTable{
	
	@JsonManagedReference
	@OneToMany(mappedBy = "lpsRegister", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
 	private List<LpsLicense> LpsLicense;

	public List<LpsLicense> getLpsLicense() {
		return LpsLicense;
	}

	public void setLpsLicense(List<LpsLicense> lpsLicense) {
		LpsLicense = lpsLicense;
	}
}