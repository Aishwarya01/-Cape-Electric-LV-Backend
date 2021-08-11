package com.capeelectric.service;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.capeelectric.exception.CompanyDetailsException;
import com.capeelectric.model.Company;
import com.capeelectric.model.User;
import com.capeelectric.repository.CompanyRepository;
import com.capeelectric.repository.UserRepository;
import com.capeelectric.service.impl.CompanyServiceImpl;

@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
public class CompanyDetailsServiceTest {

	@MockBean
	private CompanyRepository companyRepository;

	@InjectMocks
	private CompanyServiceImpl companyServiceimp;

	@MockBean
	private UserRepository userRepository;

	@MockBean
	private CompanyDetailsException companyDetailsException;

	private Company company;

	{
		company = new Company();
		company.setUserName("user@capeindia.net");
		company.setClientName("cape");
		company.setInActive(true);
		company.setCompanyId(1);

	}
	private Company company1;

	{
		company1 = new Company();
		company1.setUserName("lvsystem@capeindia.net");
		company1.setClientName("cape2");
		company1.setInActive(true);
		company1.setCompanyId(1);

	}

	@Test
	public void testUpdateCompany_Success_Flow() throws CompanyDetailsException {
		List<Company> companyList = new ArrayList<>();
		companyList.add(company);

		User user = new User();
		user.setFirstname("firstName");
		user.setLastname("lastName");

		Optional<User> optional_user = Optional.ofNullable(user);
		when(companyRepository.findByUserName(company.getUserName())).thenReturn(companyList);
		when(userRepository.findByUsername(company.getUserName())).thenReturn(optional_user);
		companyServiceimp.updateCompany(company);
	}

	@Test
	public void testUpdateCompany_Invalid_Inputs() throws CompanyDetailsException {
		List<Company> companylist = new ArrayList<>();
		company.setUserName(null);
		when(companyRepository.findByUserName(company.getUserName())).thenReturn(companylist);
		CompanyDetailsException assertThrows = Assertions.assertThrows(CompanyDetailsException.class,
				() -> companyServiceimp.updateCompany(company));
		equals(assertThrows.getMessage());
	}

	@Test
	public void testUpdateCompany_Client_Not_Present2() throws CompanyDetailsException {
		List<Company> companyList = new ArrayList<>();

		when(companyRepository.findByUserName(company.getUserName())).thenReturn(companyList);

		CompanyDetailsException assertThrows = Assertions.assertThrows(CompanyDetailsException.class,
				() -> companyServiceimp.updateCompany(company));
		equals(assertThrows.getMessage());
	}

	@Test
	public void testUpdateCompany_Client_Not_Present3() throws CompanyDetailsException {
		List<Company> companyList = new ArrayList<>();
		Company company2 = null;
		companyList.add(company2);
		when(companyRepository.findByUserName(company.getUserName())).thenReturn(companyList);
		CompanyDetailsException assertThrows = Assertions.assertThrows(CompanyDetailsException.class,
				() -> companyServiceimp.updateCompany(company));
		equals(assertThrows.getMessage());

	}

	@Test
	public void testaddCompany_Success_Flow() throws CompanyDetailsException {
		Optional<Company> companyList = null;
		companyList = Optional.of(company1);

		User user = new User();
		user.setFirstname("firstName");
		user.setLastname("lastName");

		Optional<User> optional_user = Optional.ofNullable(user);
		when(companyRepository.findByUserNameAndClientName(company.getUserName(), company.getClientName()))
				.thenReturn(companyList);
		when(userRepository.findByUsername(company.getUserName())).thenReturn(optional_user);
		companyServiceimp.addCompany(company1);
	}

	@Test
	public void testaddCompany_invalid_input() throws CompanyDetailsException {
		Optional<Company> companyList;
		company.setClientName(null);
		companyList = Optional.of(company);
		when(companyRepository.findByUserNameAndClientName(company.getUserName(), company.getClientName()))
				.thenReturn(companyList);
		CompanyDetailsException assertThrows = Assertions.assertThrows(CompanyDetailsException.class,
				() -> companyServiceimp.addCompany(company));
		equals(assertThrows.getMessage());
	}

	@Test
	public void testaddCompany_Client_is_Present() throws CompanyDetailsException {
		company.setClientName("cape");
		company.setUserName("user@capeindia.net");
		Optional<Company> companyList;
		companyList = Optional.of(company);
		when(companyRepository.findByUserNameAndClientName(company.getUserName(), company.getClientName()))
				.thenReturn(companyList);
		CompanyDetailsException assertThrows = Assertions.assertThrows(CompanyDetailsException.class,
				() -> companyServiceimp.addCompany(company));
		equals(assertThrows.getMessage());

	}

	@Test
	public void testRetriveCompany_Success_Flow() throws CompanyDetailsException {
		List<Company> companyList = new ArrayList<>();
		companyList.add(company);

		when(companyRepository.findByUserName(company.getUserName())).thenReturn(companyList);
		companyServiceimp.retrieveCompany("user@capeindia.net");
	}

	@Test
	public void testRetriveCompany_Unuser() throws CompanyDetailsException {
		List<Company> companylist = new ArrayList<>();
		company.setUserName(null);
		when(companyRepository.findByUserName(company.getUserName())).thenReturn(companylist);
		CompanyDetailsException assertThrows = Assertions.assertThrows(CompanyDetailsException.class,
				() -> companyServiceimp.retrieveCompany(null));
		equals(assertThrows.getMessage());
	}

	@Test
	public void testdeleteCompany_Success_Flow() throws CompanyDetailsException {
		Optional<Company> companyList;
		companyList = Optional.of(company);
		when(companyRepository.findByUserNameAndClientName(company.getUserName(), company.getClientName()))
				.thenReturn(companyList);
	}

	@Test
	public void testdeleteCompany_Invalid_Input_Flow() throws CompanyDetailsException {
		Optional<Company> companyList;
		companyList = Optional.of(company);
		company.setUserName(null);
		when(companyRepository.findByUserNameAndClientName(company.getUserName(), company.getClientName()))
				.thenReturn(companyList);
		CompanyDetailsException assertThrows = Assertions.assertThrows(CompanyDetailsException.class,
				() -> companyServiceimp.deleteCompany(null, null));
		equals(assertThrows.getMessage());
	}

	@Test
	public void testdeleteCompany_Client_Not_Present() throws CompanyDetailsException {
		Optional<Company> companyList;
		companyList = Optional.of(company);
		when(companyRepository.findByUserNameAndClientName(company.getUserName(), company.getClientName()))
				.thenReturn(companyList);
		CompanyDetailsException assertThrows = Assertions.assertThrows(CompanyDetailsException.class,
				() -> companyServiceimp.deleteCompany("cape", ";lvsystem"));
		equals(assertThrows.getMessage());

	}

}
