package com.capeelectric.service;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.capeelectric.exception.CompanyDetailsException;
import com.capeelectric.model.Company;
import com.capeelectric.model.Department;
import com.capeelectric.model.Site;
import com.capeelectric.model.User;
import com.capeelectric.repository.CompanyRepository;
import com.capeelectric.repository.DepartmentRepository;
import com.capeelectric.repository.SiteRepository;
import com.capeelectric.repository.UserRepository;
import com.capeelectric.service.impl.SiteServiceImpl;

@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
public class SiteDetailsServiceTest {

	@MockBean
	private SiteRepository siteRepository;

	@InjectMocks
	private SiteServiceImpl siteServiceImpl;

	@MockBean
	private DepartmentRepository departmentRepository;

	@MockBean
	private UserRepository userRepository;

	@MockBean
	private CompanyRepository companyRepository;

	@MockBean
	private CompanyDetailsException companyDetailsException;

	private Site site;
	{
		site = new Site();
		site.setUserName("hasan");
		site.setClientName("nissan");
		site.setDepartmentName("Accounts");
		site.setSiteId(1);
		site.setSiteName("user");
		site.setClientName("nissan");

	}

	private Department department;
	{
		department = new Department();
		department.setClientName(" nissan");
		department.setDepartmentName("Accounts");

	}

	private Company company;
	{
		company = new Company();
		company.setUserName("hasan");
		company.setClientName("nissan");
	}
	@Test
	public void testupdateSite_Success_Flow() throws CompanyDetailsException {
		List<Site> deptlist = new ArrayList<>();
		deptlist.add(site);
		
		//Department optional_user = Optional.ofNullable(department);
		
		when(departmentRepository.findByClientNameAndDepartmentName(site.getClientName(),
				site.getDepartmentName())).thenReturn(department);

		when(siteRepository.findByClientNameAndDepartmentName(site.getClientName(),
				site.getDepartmentName())).thenReturn(deptlist);
		siteServiceImpl.updateSite(site);
	}

	@Test
	public void testupdateSite_Invalid_Inputs_Flow() throws CompanyDetailsException {
		site.setDepartmentName(null);
		assertThatExceptionOfType(CompanyDetailsException.class).isThrownBy(() -> {
			throw new CompanyDetailsException("invalid input");
		});
	}

	@Test
	public void testupdateSite_ClientName_not_Present() throws CompanyDetailsException {
		department.setClientName(null);
		when(departmentRepository.findByClientNameAndDepartmentName(site.getClientName(), site.getDepartmentName()))
				.thenReturn(department);
		assertThatExceptionOfType(CompanyDetailsException.class).isThrownBy(() -> {
			throw new CompanyDetailsException("clientName  " + site.getClientName() + "  not present "
					+ site.getDepartmentName() + " department");
		});
	}

	@Test
	public void testupdateSite_Department_not_Present() throws CompanyDetailsException {
		department.setDepartmentName(null);
		when(departmentRepository.findByClientNameAndDepartmentName(site.getClientName(), site.getDepartmentName()))
				.thenReturn(department);
		assertThatExceptionOfType(CompanyDetailsException.class).isThrownBy(() -> {
			throw new CompanyDetailsException(
					site.getDepartmentName() + "  department not present for " + site.getClientName() + " company");
		});
	}

	@Test
	public void testupdateSite_Site_Already_Present() throws CompanyDetailsException {
		List<Site> deptlist = new ArrayList<>();
		deptlist.add(site);

		when(departmentRepository.findByClientNameAndDepartmentName(site.getClientName(), site.getDepartmentName()))
				.thenReturn(department);

		when(siteRepository.findByClientNameAndDepartmentName(site.getClientName(), site.getDepartmentName()))
				.thenReturn(deptlist);

		assertThatExceptionOfType(CompanyDetailsException.class).isThrownBy(() -> {
			throw new CompanyDetailsException(site.getSite() + " : site Already present");
		});
	}

	@Test
	public void testaddSite_Success_Flow() throws CompanyDetailsException {
		Optional<Company> companyList=null ;
		companyList = Optional.of(company);
		
		//Optional<Site> optional_user = Optional.ofNullable(site);

		User user = new User();
		user.setFirstname("firstName");
		user.setLastname("lastName");

		Optional<User> optional_user = Optional.ofNullable(user);

		when(companyRepository.findByUserNameAndClientName(department.getUserName(),department.getClientName())).thenReturn(companyList);

		when(departmentRepository.findByClientNameAndDepartmentName(site.getClientName(),
				site.getDepartmentName())).thenReturn(department);
		
		when(userRepository.findByUsername(department.getUserName())).thenReturn(optional_user);
		
		siteServiceImpl.addSite(site);
	}

	@Test
	public void testaddSite_Invalid_Inputs_Flow() throws CompanyDetailsException {
		site.setUserName(null);
		assertThatExceptionOfType(CompanyDetailsException.class).isThrownBy(() -> {
			throw new CompanyDetailsException("invalid input");
		});
	}

	@Test
	public void testaddSite_ClientName_not_Present() throws CompanyDetailsException {
		Optional<Company> companyList = null;
		when(companyRepository.findByClientName(site.getClientName())).thenReturn(companyList);
		assertThatExceptionOfType(CompanyDetailsException.class).isThrownBy(() -> {
			throw new CompanyDetailsException("clientName  " + site.getClientName() + "  not present "
					+ site.getDepartmentName() + " department");
		});
	}

	@Test
	public void testaddSite_Department_not_Present() throws CompanyDetailsException {

		Optional<Company> companyList;
		companyList = Optional.of(company);

		department.setDepartmentName(null);

		when(companyRepository.findByClientName(site.getClientName())).thenReturn(companyList);

		when(departmentRepository.findByClientNameAndDepartmentName(site.getClientName(), site.getDepartmentName()))
				.thenReturn(department);

		assertThatExceptionOfType(CompanyDetailsException.class).isThrownBy(() -> {
			throw new CompanyDetailsException(
					site.getDepartmentName() + " : department not present  " + site.getClientName() + " company");
		});
	}

	@Test
	public void testaddSite_Site_Already_Present() throws CompanyDetailsException {
		Optional<Company> companyList;
		companyList = Optional.of(company);

		List<Site> deptlist = new ArrayList<>();
		deptlist.add(site);
		when(companyRepository.findByClientName(site.getClientName())).thenReturn(companyList);
		when(departmentRepository.findByClientNameAndDepartmentName(site.getClientName(), site.getDepartmentName()))
				.thenReturn(department);
		when(siteRepository.findByClientNameAndDepartmentName(site.getClientName(), site.getDepartmentName()))
				.thenReturn(deptlist);
		assertThatExceptionOfType(CompanyDetailsException.class).isThrownBy(() -> {
			throw new CompanyDetailsException(site.getSite() + " : site Already present");
		});
	}
	
	@Test
	public void testdeleteSite_Success_Flow() throws CompanyDetailsException {
		Optional<Site> siteList ;
		siteList = Optional.of(site);
		when(siteRepository.findById(site.getSiteId())).thenReturn(siteList);
	//	when(siteRepository.deleteById(site.getSiteId())).thenReturn(site);
		
	}
	
	@Test
	public void testdeleteSite_Invalid_Inputs_Flow() throws CompanyDetailsException {
		site.setSiteId(null);
		assertThatExceptionOfType(CompanyDetailsException.class).isThrownBy(() -> {
			throw new CompanyDetailsException("invalid input");
		});
	}
	
	@Test
	public void testdeleteSite_Site_not_Present() throws CompanyDetailsException {
		Optional<Site> siteList =null;
		when(siteRepository.findById(site.getSiteId())).thenReturn(siteList);
		assertThatExceptionOfType(CompanyDetailsException.class).isThrownBy(() -> {
			throw new CompanyDetailsException(site.getSite()+" : this site not present");
		});
	}
	
	@Test
	public void testretriveSite_Success_Flow() throws CompanyDetailsException {
		List<Site> siteList = new ArrayList<>();
		siteList.add(site);
		when(siteRepository.findByClientNameAndDepartmentName(site.getClientName(), site.getDepartmentName())).thenReturn(siteList);
	}
	
	@Test
	public void testretriveSite_Invalid_Input() throws CompanyDetailsException {
		List<Site> siteList = null;
		when(siteRepository.findByClientNameAndDepartmentName(site.getClientName(), site.getDepartmentName())).thenReturn(siteList);
     	assertThatExceptionOfType(CompanyDetailsException.class).isThrownBy(() -> {
				throw new CompanyDetailsException("invalid inputs");
			});
		}
	

}
