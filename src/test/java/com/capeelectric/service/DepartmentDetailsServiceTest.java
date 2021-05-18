package com.capeelectric.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
import com.capeelectric.model.Department;
import com.capeelectric.model.User;
import com.capeelectric.repository.CompanyRepository;
import com.capeelectric.repository.DepartmentRepository;
import com.capeelectric.repository.UserRepository;
import com.capeelectric.service.impl.DepartmentServiceImpl;

@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
public class DepartmentDetailsServiceTest {

	@MockBean
	private DepartmentRepository departmentRepository;

	@InjectMocks
	private DepartmentServiceImpl departmentServiceImpl;

	@MockBean
	private UserRepository userRepository;

	@MockBean
	private CompanyRepository companyRepository;

	@MockBean
	private CompanyDetailsException companyDetailsException;

	private Department department;

	List<Department> deptlist;

	{
		department = new Department();
		department.setUserName("hasan");
		department.setClientName("HCL");
		department.setDepartmentName("Electrical");
		department.setDepartmentId(1);

	}

	private Company company;
	{
		company = new Company();
		company.setClientName("HCL");

	}

	@Test
	public void testUpdateDepartment_Success_Flow() throws CompanyDetailsException {
		deptlist = new ArrayList<>();
		deptlist.add(department);

		when(companyRepository.findByUserNameAndClientName(department.getUserName(), department.getClientName()))
				.thenReturn(Optional.of(company));
		when(departmentRepository.findByClientName(department.getClientName())).thenReturn(deptlist);
		departmentServiceImpl.updateDepartment(department);

		Department department2 = new Department();
		department2.setUserName("hasan");
		department2.setClientName("HCL");
		department2.setDepartmentId(1);
		department2.setDepartmentName("mech");
		departmentServiceImpl.updateDepartment(department2);

	}

	@Test
	public void testUpdateDepartment_Invalid_Inputs_Flow() throws CompanyDetailsException {
		Optional<Company> deptlist;
		department.setClientName(null);
		deptlist = Optional.empty();

		when(companyRepository.findByUserNameAndClientName(department.getUserName(), department.getClientName()))
				.thenReturn(deptlist);
		CompanyDetailsException assertThrows = Assertions.assertThrows(CompanyDetailsException.class,
				() -> departmentServiceImpl.updateDepartment(department));
		equals(assertThrows.getMessage());
	}

	@Test
	public void testUpdateDepartment_Company_not_Present_user() throws CompanyDetailsException {
		Optional<Company> companylist;
		companylist = Optional.of(company);

		when(companyRepository.findByUserNameAndClientName(company.getUserName(), company.getClientName()))
				.thenReturn(companylist);
		CompanyDetailsException assertThrows = Assertions.assertThrows(CompanyDetailsException.class,
				() -> departmentServiceImpl.updateDepartment(department));
		equals(assertThrows.getMessage());
	}

	@Test
	public void testUpdateDepartment_DepartmentName_Already_present_user() throws CompanyDetailsException {
		List<Department> deptList = new ArrayList<>();
		deptList.add(department);

		when(departmentRepository.findByClientName(department.getClientName())).thenReturn(deptList);
		when(companyRepository.findByUserNameAndClientName(department.getUserName(), department.getClientName()))
				.thenReturn(Optional.of(company));

		Department department2 = new Department();
		department2.setUserName("hasan");
		department2.setClientName("HCL");
		department2.setDepartmentName("Electrical");
		department2.setDepartmentId(3);
		CompanyDetailsException assertThrows = Assertions.assertThrows(CompanyDetailsException.class,
				() -> departmentServiceImpl.updateDepartment(department2));

	}

	@Test
	public void testaddDepartment_Success_Flow() throws CompanyDetailsException {
		Optional<Company> companylist;
		companylist = Optional.of(company);

		User user = new User();
		user.setFirstname("firstName");
		user.setLastname("lastName");
		Optional<User> optional_user = Optional.empty();

		when(userRepository.findByUsername("hasan")).thenReturn(Optional.of(user));

		when(companyRepository.findByUserNameAndClientName(department.getUserName(), department.getClientName()))
				.thenReturn(companylist);
		when(departmentRepository.findByClientNameAndDepartmentName(department.getClientName(),
				department.getDepartmentName())).thenReturn(null);
		when(userRepository.findByUsername(user.getFirstname())).thenReturn(optional_user);

		departmentServiceImpl.addDepartment(department);
	}

	@Test
	public void testaddDepartment_Invalid_Inputs_Flow() throws CompanyDetailsException {
		department.setDepartmentName(null);
		CompanyDetailsException assertThrows = Assertions.assertThrows(CompanyDetailsException.class,
				() -> departmentServiceImpl.addDepartment(department));
		equals(assertThrows.getMessage());
	}

	@Test
	public void testaddDepartment_Client_Not_Present() throws CompanyDetailsException {
		Optional<Company> deptlist;
		deptlist = Optional.of(company);

		when(companyRepository.findByUserNameAndClientName(company.getUserName(), company.getClientName()))
				.thenReturn(deptlist);
		CompanyDetailsException assertThrows = Assertions.assertThrows(CompanyDetailsException.class,
				() -> departmentServiceImpl.addDepartment(department));
		equals(assertThrows.getMessage());
	}

	@Test
	public void testaddDepartment_Department_Already_Exist() throws CompanyDetailsException {
		Optional<Company> companylist;
		companylist = Optional.of(company);

		User user = new User();
		user.setFirstname("firstName");
		user.setLastname("lastName");
		Optional<User> optional_user = Optional.empty();

		when(companyRepository.findByUserNameAndClientName(department.getUserName(), department.getClientName()))
				.thenReturn(companylist);
		when(departmentRepository.findByClientNameAndDepartmentName(department.getClientName(),
				department.getDepartmentName())).thenReturn(department);
		when(userRepository.findByUsername(company.getUserName())).thenReturn(optional_user);
		CompanyDetailsException assertThrows = Assertions.assertThrows(CompanyDetailsException.class,
				() -> departmentServiceImpl.addDepartment(department));
	}

	@Test
	public void testaddDepartment_Department_Already_Exist1() throws CompanyDetailsException {
       //For Writing this TestCase To Cover The code 
		Optional<Company> companyList;
		companyList = Optional.of(company);

		when(companyRepository.findByUserNameAndClientName(department.getUserName(), department.getClientName()))
				.thenReturn(companyList);
		when(departmentRepository.findByClientNameAndDepartmentName(department.getClientName(),
				department.getDepartmentName())).thenReturn(department);
		Assertions.assertThrows(CompanyDetailsException.class, () -> departmentServiceImpl.addDepartment(department));

	}

	@Test
	public void testdeleteDepartment_Success_Flow() throws CompanyDetailsException {
		Optional<Department> companyList;
		companyList = Optional.of(department);

		when(departmentRepository.findById(department.getDepartmentId())).thenReturn(companyList);
		departmentServiceImpl.deleteDepartment(1);
	}

	@Test
	public void testdeleteDepartment_Invalid_Inputs_Flow() throws CompanyDetailsException {
		Optional<Department> deptlist;
		deptlist = Optional.of(department);

		when(departmentRepository.findById(department.getDepartmentId())).thenReturn(deptlist);
		CompanyDetailsException assertThrows = Assertions.assertThrows(CompanyDetailsException.class,
				() -> departmentServiceImpl.deleteDepartment(null));
		equals(assertThrows.getMessage());

	}

	@Test
	public void testdeleteDepartment_Department_ID_Not_Present() throws CompanyDetailsException {
		Optional<Department> deptlist;
		deptlist = Optional.of(department);

		when(departmentRepository.findById(department.getDepartmentId())).thenReturn(deptlist);
		CompanyDetailsException assertThrows = Assertions.assertThrows(CompanyDetailsException.class,
				() -> departmentServiceImpl.deleteDepartment(2));
		assertEquals("2 : department ID not present", assertThrows.getMessage());
	}

	@Test
	public void testretriveDepartment_Success_Flow() throws CompanyDetailsException {
		List<Department> deptList = new ArrayList<>();
		deptList.add(department);

		when(departmentRepository.findByUserNameAndClientName(department.getClientName(), department.getUserName()))
				.thenReturn(deptList);
		departmentServiceImpl.retriveDepartment("hasan", "HCL");
	}

	@Test
	public void testretriveDepartment_Invalid_Input() throws CompanyDetailsException {
		List<Department> deptList = new ArrayList<>();
		department.setClientName(null);

		when(departmentRepository.findByUserNameAndClientName(department.getClientName(), department.getUserName()))
				.thenReturn(deptList);
		CompanyDetailsException assertThrows = Assertions.assertThrows(CompanyDetailsException.class,
				() -> departmentServiceImpl.retriveDepartment(null, null));
		equals(assertThrows.getMessage());
	}

}
