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

import com.capeelectric.config.JwtTokenUtil;
import com.capeelectric.exception.CompanyDetailsException;
import com.capeelectric.model.Company;
import com.capeelectric.service.impl.CompanyServiceImpl;

@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
public class CompanyControllerTest {

	@MockBean
	private CompanyServiceImpl companyserviceImp;

	@MockBean
	private JwtTokenUtil jwtTokenUtil;
	
	@InjectMocks
	private CompanyController companyController;

	private Company company;

	{
		company = new Company();
		company.setUserName("lvsystem@capeindia.net");
		company.setClientName("cape");
		company.setInActive(true);
		company.setCreatedBy("user");
		company.setUpdatedBy("user");
		
	}

	@Test
	public void testUpdateCompany() throws CompanyDetailsException {
		ResponseEntity<String> expectedResponseEntity = new ResponseEntity<String>(HttpStatus.OK);
		// when(companyserviceImpl.updateCompany(company));
		ResponseEntity<String> actualResponseEntity = companyController.updateCompany(company);
		assertEquals(actualResponseEntity, expectedResponseEntity);
	}

	@Test
	public void testaddCompany() throws CompanyDetailsException {
		ResponseEntity<String> expectedResponseEntity = new ResponseEntity<String>(HttpStatus.CREATED);
		ResponseEntity<String> actualResponseEntity = companyController.addCompany(company);
		assertEquals(actualResponseEntity, expectedResponseEntity);

	}

	@Test
	public void testdeleteCompany() throws CompanyDetailsException {
		ResponseEntity<String> expectedResponseEntity = new ResponseEntity<String>(HttpStatus.OK);
		ResponseEntity<String> actualResponseEntity = companyController.deleteCompany("lvsystem@capeindia.net", "cape");
		assertEquals(actualResponseEntity, expectedResponseEntity);

	}

    @Test
    public void testretriveCompany() throws CompanyDetailsException {
    	 List<Company> list = new ArrayList<>();
    	 list.add(company);
    	 
	     ResponseEntity<List<Company>> expectedResponseEntity =new ResponseEntity<List<Company>>(list, HttpStatus.OK);
	     ResponseEntity<List<Company>> actualResponseEntity = companyController.retriveCompany("lvsystem@capeindia.net");
	     assertEquals(actualResponseEntity.getStatusCode(), expectedResponseEntity.getStatusCode());

    }
    }
