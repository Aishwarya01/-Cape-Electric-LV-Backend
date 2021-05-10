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

	{
		department = new Department();
		department.setUserName("hasan");
		department.setClientName("HCL");
		department.setDepartmentId(1);
		department.setDepartmentName("Electrical");
	}

	private Company company;
	{
		company = new Company();
		company.setClientName("HCL");

	}

	@Test
	public void testUpdateDepartment_Success_Flow() throws CompanyDetailsException {
		List<Department> deptlist = new ArrayList<>();
		deptlist.add(department);

		Optional<Company> optional_user = Optional.ofNullable(company);

		when(companyRepository.findByUserNameAndClientName(department.getUserName(), department.getClientName()))
				.thenReturn(optional_user);

		when(departmentRepository.findByClientName(department.getClientName())).thenReturn(deptlist);
		departmentServiceImpl.updateDepartment(department);
	}

	@Test
	public void testUpdateDepartment_Invalid_Inputs_Flow() throws CompanyDetailsException {
		Optional<Company> deptlist;
		deptlist = null;
		when(companyRepository.findByUserNameAndClientName(department.getUserName(), department.getClientName()))
				.thenReturn(deptlist);
		assertThatExceptionOfType(CompanyDetailsException.class).isThrownBy(() -> {
			throw new CompanyDetailsException("invalid input");
		});
	}

	@Test
	public void testUpdateDepartment_Company_not_Present_user() throws CompanyDetailsException {
		Optional<Company> deptList = null;
		when(companyRepository.findByUserNameAndClientName(department.getUserName(), department.getClientName()))
				.thenReturn(deptList);
		assertThatExceptionOfType(CompanyDetailsException.class).isThrownBy(() -> {
			throw new CompanyDetailsException("Company not present user :" + department.getUserName());
		});
	}

	@Test
	public void testUpdateDepartment_Client_Not_Present3() throws CompanyDetailsException {
		List<Department> deptList = new ArrayList<>();
		deptList.add(department);
		when(companyRepository.findByUserNameAndClientName(department.getUserName(), department.getClientName()))
				.thenReturn(null);
		when(departmentRepository.findByClientName(department.getClientName())).thenReturn(deptList);
		assertThatExceptionOfType(CompanyDetailsException.class).isThrownBy(() -> {
			throw new CompanyDetailsException("DepartmentName Already present user :" + department.getClientName());

		});
	}

	@Test
	public void testaddDepartment_Success_Flow() throws CompanyDetailsException {
		Optional<Company> companyList;
		companyList = Optional.of(company);
		when(companyRepository.findByUserNameAndClientName(department.getUserName(), department.getClientName()))
				.thenReturn(companyList);

		when(departmentRepository.findByClientNameAndDepartmentName(department.getClientName(),
				department.getDepartmentName())).thenReturn(department);
		departmentServiceImpl.updateDepartment(department);
	}

	@Test
	public void testaddDepartment_Invalid_Inputs_Flow() throws CompanyDetailsException {
		Optional<Company> deptlist;
		deptlist = null;
		when(companyRepository.findByUserNameAndClientName(department.getUserName(), department.getClientName()))
				.thenReturn(deptlist);
		assertThatExceptionOfType(CompanyDetailsException.class).isThrownBy(() -> {
			throw new CompanyDetailsException("invalid inputs");
		});
	}

	@Test
	public void testaddDepartment_Client_Not_Present() throws CompanyDetailsException {
		when(companyRepository.findByUserNameAndClientName(department.getUserName(), department.getClientName()))
				.thenReturn(null);
		assertThatExceptionOfType(CompanyDetailsException.class).isThrownBy(() -> {
			throw new CompanyDetailsException(department.getClientName() + " : clientname not present in company");
		});
	}

	@Test
	public void testaddDepartment_Department_Already_Exist() throws CompanyDetailsException {

		when(departmentRepository.findByClientNameAndDepartmentName(department.getClientName(),
				department.getDepartmentName())).thenReturn(department);
		assertThatExceptionOfType(CompanyDetailsException.class).isThrownBy(() -> {
			throw new CompanyDetailsException(
					department.getDepartmentName() + " DepartmentName already present :" + department.getClientName());
		});
	}

	@Test
	public void testdeleteDepartment_Success_Flow() throws CompanyDetailsException {
		Optional<Department> companyList;
		companyList = Optional.of(department);
		when(departmentRepository.findById(department.getDepartmentId())).thenReturn(companyList);

	}

	@Test
	public void testdeleteDepartment_Invalid_Inputs_Flow() throws CompanyDetailsException {
		department.setDepartmentId(0);
		assertThatExceptionOfType(CompanyDetailsException.class).isThrownBy(() -> {
			throw new CompanyDetailsException("invalid inputs");
		});
	}

	@Test
	public void testdeleteDepartment_Department_ID_Not_Present() throws CompanyDetailsException {
		Optional<Department> deptlist;
		deptlist = Optional.of(department);

		when(departmentRepository.findById(department.getDepartmentId())).thenReturn(deptlist);
		// when(departmentRepository.deleteById(department.getdepartmentId())).thenReturn(department);
		assertThatExceptionOfType(CompanyDetailsException.class).isThrownBy(() -> {
			throw new CompanyDetailsException(department + " : department ID not present");
		});
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
		when(departmentRepository.findByUserNameAndClientName(department.getClientName(), department.getUserName()))
				.thenReturn(null);
		assertThatExceptionOfType(CompanyDetailsException.class).isThrownBy(() -> {
			throw new CompanyDetailsException("invalid inputs");
		});
	}

}
