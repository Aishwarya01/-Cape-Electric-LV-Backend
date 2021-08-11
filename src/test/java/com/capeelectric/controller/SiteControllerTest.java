package com.capeelectric.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.capeelectric.exception.CompanyDetailsException;
import com.capeelectric.model.Site;
import com.capeelectric.service.impl.SiteServiceImpl;

@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
public class SiteControllerTest {

	@MockBean
	private SiteServiceImpl siteServiceImpl;

	@InjectMocks
	private SiteController siteController;

	@MockBean
	private CompanyDetailsException companyDetailsException;

	private Site site;

	{
		site = new Site();
		site.setUserName("hasan");
		site.setClientName("nissan");
		site.setDepartmentName("Accounts");
		site.setSiteId(1);
		site.setSite("user");

	}

	@Test
	public void tesupdateSite() throws CompanyDetailsException {
		ResponseEntity<String> expectedResponseEntity = new ResponseEntity<String>("Site Updated", HttpStatus.OK);
		ResponseEntity<String> actualResponseEntity = siteController.updateSite(site);
		assertEquals(actualResponseEntity, expectedResponseEntity);
	}

	@Test
	public void testaddSite() throws CompanyDetailsException {
		ResponseEntity<String> expectedResponseEntity = new ResponseEntity<String>("successfully added Site", HttpStatus.OK);
		ResponseEntity<String> actualResponseEntity = siteController.addSite(site);;
		assertEquals(actualResponseEntity, expectedResponseEntity);
	}
	
	@Test
	public void testdeleteSite() throws CompanyDetailsException {
		ResponseEntity<String> expectedResponseEntity = new ResponseEntity<String>(HttpStatus.OK);
		ResponseEntity<String> actualResponseEntity = siteController.deleteSite(1);
		assertEquals(actualResponseEntity, expectedResponseEntity);
	}
    
	 @Test
	    public void testretriveSite() throws CompanyDetailsException {
	    	 List<Site> list = new ArrayList<>();
	    	 list.add(site);
	    	 ResponseEntity<List<Site>> expectedResponseEntity =new  ResponseEntity<List<Site>>(list, HttpStatus.OK);
		     ResponseEntity<List<Site>> actualResponseEntity = siteController.retriveSite("HCL","Electrical");
		     assertEquals(actualResponseEntity.getStatusCode(), expectedResponseEntity.getStatusCode());

	    }
}
