package com.capeelectric.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
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
import com.capeelectric.model.SitePersons;
import com.capeelectric.model.User;
import com.capeelectric.repository.CompanyRepository;
import com.capeelectric.repository.DepartmentRepository;
import com.capeelectric.repository.SitePersonsRepository;
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

	@MockBean
	private SitePersonsRepository sitePersonsRepository;

	private SitePersons sitePersons1 = new SitePersons();

	private SitePersons sitePersons2 = new SitePersons();

	private SitePersons sitePersons3 = new SitePersons();

	private Set<SitePersons> sitePersonsSet;

	private Site site;

	private Department department;

	private Company company;
	{
		company = new Company();
		company.setUserName("hasan");
		company.setClientName("nissan");

		department = new Department();
		department.setClientName("nissan");
		department.setDepartmentName("Accounts");

		site = new Site();
		site.setUserName("hasan");
		site.setClientName("nissan");
		site.setDepartmentName("Accounts");
		site.setSiteId(1);
		site.setSite("user");
		site.setClientName("nissan");

		sitePersons1.setPersonId(1);
		sitePersons1.setPersonInchargeEmail("LVsystem@gmail.com");
		sitePersons1.setInActive(true);
		sitePersons2.setPersonId(2);
		sitePersons2.setPersonInchargeEmail("Cape@gmail.com");
		sitePersons2.setInActive(true);
		sitePersonsSet = new HashSet<SitePersons>();

	}

	@Test
	public void testupdateSite_Success_Flow() throws CompanyDetailsException {
		test();
		sitePersonsSet.add(sitePersons1);
		site.setSitePersons(sitePersonsSet);

		siteServiceImpl.updateSite(site);

	}

	@Test
	public void testupdateSiteRemovedInactivePerson() throws CompanyDetailsException {
		test();

		sitePersons1.setInActive(false);
		sitePersonsSet.add(sitePersons1);
		site.setSitePersons(sitePersonsSet);
		sitePersonsSet.add(sitePersons2);
		sitePersonsSet.add(sitePersons2);
		site.setSitePersons(sitePersonsSet);
		when(siteRepository.save(site)).thenReturn(site);

		sitePersons3.setPersonInchargeEmail("Cape1@gmail.com");
		sitePersons3.setInActive(true);
		sitePersonsSet.add(sitePersons3);
		site.setSitePersons(sitePersonsSet);
		siteServiceImpl.updateSite(site);
	}

	@Test
	public void testupdateSite_SiteNotPresentException() throws CompanyDetailsException {
		test();
		Site site2 = new Site();
		site2.setUserName("hasan");
		site2.setClientName("nissan");
		site2.setDepartmentName("Accounts");
		site2.setSite("user");
		site2.setClientName("nissan");

		site2.setSitePersons(sitePersonsSet);
		site2.setSiteId(2);
		CompanyDetailsException assertThrows = Assertions.assertThrows(CompanyDetailsException.class,
				() -> siteServiceImpl.updateSite(site2));
		assertEquals(assertThrows.getMessage(), "user site not present");
	}

	@Test
	public void testupdateSite_PersonInchargEmailalreadypresentException() throws CompanyDetailsException {
		test();
		sitePersons3.setPersonInchargeEmail("Cape@gmail.com");
		sitePersons3.setInActive(true);
		sitePersonsSet.add(sitePersons2);
		sitePersonsSet.add(sitePersons3);
		site.setSitePersons(sitePersonsSet);
		CompanyDetailsException assertThrows = Assertions.assertThrows(CompanyDetailsException.class,
				() -> siteServiceImpl.updateSite(site));
		assertEquals(assertThrows.getMessage(), "PersonInchargEmail already present");

	}

	@Test
	public void testupdateSite_InvalidInputsException() throws CompanyDetailsException {
		site.setDepartmentName(null);
		CompanyDetailsException assertThrows = Assertions.assertThrows(CompanyDetailsException.class,
				() -> siteServiceImpl.updateSite(site));
		assertEquals(assertThrows.getMessage(), "invalid inputs");

	}

	@Test
	public void testupdateSite_ClientNameNotPresentException() throws CompanyDetailsException {

		when(departmentRepository.findByClientNameAndDepartmentName(site.getClientName(), site.getDepartmentName()))
				.thenReturn(department);
		department.setClientName("Test");
		CompanyDetailsException assertThrows = Assertions.assertThrows(CompanyDetailsException.class,
				() -> siteServiceImpl.updateSite(site));
		assertEquals(assertThrows.getMessage(), "clientName  nissan  not present Accounts department");

	}

	@Test
	public void testupdateSite_DepartmentNotPresentException() throws CompanyDetailsException {

		when(departmentRepository.findByClientNameAndDepartmentName(site.getClientName(), site.getDepartmentName()))
				.thenReturn(department);
		department.setDepartmentName("mech");
		CompanyDetailsException assertThrows = Assertions.assertThrows(CompanyDetailsException.class,
				() -> siteServiceImpl.updateSite(site));
		assertEquals(assertThrows.getMessage(), "Accounts  department not present for nissan company");

	}

	@Test
	public void testaddSite_Success_Flow() throws CompanyDetailsException {
		Optional<Company> companyList = null;
		companyList = Optional.of(company);

		User user = new User();
		user.setFirstname("firstName");
		user.setLastname("lastName");

		when(companyRepository.findByUserNameAndClientName(department.getUserName(), department.getClientName()))
				.thenReturn(companyList);

		when(departmentRepository.findByClientNameAndDepartmentName(site.getClientName(), site.getDepartmentName()))
				.thenReturn(department);

		when(userRepository.findByUsername(department.getUserName())).thenReturn(Optional.of(user));
		when(companyRepository.findByClientName(site.getClientName())).thenReturn(Optional.of(company));

		sitePersonsSet.add(sitePersons1);
		site.setSitePersons(sitePersonsSet);
		siteServiceImpl.addSite(site);
	}

	@Test
	public void testaddSite_Exception() throws CompanyDetailsException {

		when(companyRepository.findByClientName(site.getClientName())).thenReturn(Optional.of(company));
		when(departmentRepository.findByClientNameAndDepartmentName(site.getClientName(), site.getDepartmentName()))
				.thenReturn(department);
		when(siteRepository.findByClientNameAndDepartmentNameAndSite(site.getClientName(), site.getDepartmentName(),
				site.getSite())).thenReturn(site);
		when(sitePersonsRepository.findByPersonInchargeEmail(sitePersons2.getPersonInchargeEmail()))
				.thenReturn(Optional.of(sitePersons2));

		Site site2 = new Site();
		site2.setClientName("nissan");
		site2.setDepartmentName("Accounts");
		site2.setSite("user1");
		sitePersons3.setPersonInchargeEmail("Cape@gmail.com");
		sitePersons3.setInActive(true);
		sitePersonsSet.add(sitePersons3);
		site2.setSitePersons(sitePersonsSet);
		CompanyDetailsException assertThrows5 = Assertions.assertThrows(CompanyDetailsException.class,
				() -> siteServiceImpl.addSite(site2));
		assertEquals(assertThrows5.getMessage(), "PersonInchargEmail already present");

		CompanyDetailsException assertThrows1 = Assertions.assertThrows(CompanyDetailsException.class,
				() -> siteServiceImpl.addSite(site));
		assertEquals(assertThrows1.getMessage(), "user: site already present");

		department.setDepartmentName("mech");
		CompanyDetailsException assertThrows2 = Assertions.assertThrows(CompanyDetailsException.class,
				() -> siteServiceImpl.addSite(site));
		assertEquals(assertThrows2.getMessage(), "Accounts : department not present  nissan company");

		site.setClientName("HCL Tech");
		CompanyDetailsException assertThrows3 = Assertions.assertThrows(CompanyDetailsException.class,
				() -> siteServiceImpl.addSite(site));
		assertEquals(assertThrows3.getMessage(), "clientName  HCL Tech  not present Accounts department");

		site.setClientName(null);
		CompanyDetailsException assertThrows4 = Assertions.assertThrows(CompanyDetailsException.class,
				() -> siteServiceImpl.addSite(site));
		assertEquals(assertThrows4.getMessage(), "invalid inputs");

	}

	@Test
	public void testdeleteSite_Success_Flow() throws CompanyDetailsException {

		when(siteRepository.findById(site.getSiteId())).thenReturn(Optional.of(site));
		siteServiceImpl.deleteSite(1);

	}

	@Test
	public void testdeleteSite_Exception() throws CompanyDetailsException {

		when(siteRepository.findById(site.getSiteId())).thenReturn(Optional.of(site));
		CompanyDetailsException assertThrows1 = Assertions.assertThrows(CompanyDetailsException.class,
				() -> siteServiceImpl.deleteSite(2));
		assertEquals(assertThrows1.getMessage(), "2 : this siteId not present");

		site.setSiteId(null);
		CompanyDetailsException assertThrows2 = Assertions.assertThrows(CompanyDetailsException.class,
				() -> siteServiceImpl.deleteSite(site.getSiteId()));
		assertEquals(assertThrows2.getMessage(), "invalid input");

	}

	@Test
	public void testretriveSite_Success_Flow() throws CompanyDetailsException {
		List<Site> siteList = new ArrayList<>();
		siteList.add(site);
		when(siteRepository.findByClientNameAndDepartmentName(site.getClientName(), site.getDepartmentName()))
				.thenReturn(siteList);

		siteServiceImpl.retriveSite("nissan", "Accounts");

		CompanyDetailsException assertThrows = Assertions.assertThrows(CompanyDetailsException.class,
				() -> siteServiceImpl.retriveSite("nissan", null));
		assertEquals(assertThrows.getMessage(), "invalid inputs");

	}

	public void test() {
		List<Site> deptlist = new ArrayList<>();
		deptlist.add(site);

		when(departmentRepository.findByClientNameAndDepartmentName(site.getClientName(), site.getDepartmentName()))
				.thenReturn(department);

		when(siteRepository.findByClientNameAndDepartmentName(site.getClientName(), site.getDepartmentName()))
				.thenReturn(deptlist);
		when(siteRepository.findByClientNameAndDepartmentNameAndSite(site.getClientName(), site.getDepartmentName(),
				site.getSite())).thenReturn(site);
		when(sitePersonsRepository.findByPersonInchargeEmail(sitePersons1.getPersonInchargeEmail()))
				.thenReturn(Optional.of(sitePersons1));
		when(sitePersonsRepository.findByPersonInchargeEmail(sitePersons2.getPersonInchargeEmail()))
				.thenReturn(Optional.of(sitePersons2));
	}

}
