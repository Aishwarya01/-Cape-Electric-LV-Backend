package com.capeelectric.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.capeelectric.exception.ChangePasswordException;
import com.capeelectric.exception.CompanyDetailsException;
import com.capeelectric.exception.ForgotPasswordException;
import com.capeelectric.exception.UpdatePasswordException;
import com.capeelectric.exception.UserException;
import com.capeelectric.model.Admin;
import com.capeelectric.model.Company;
import com.capeelectric.model.Department;
import com.capeelectric.model.User;
import com.capeelectric.repository.AdminControllRepositary;
import com.capeelectric.repository.UserRepository;
import com.capeelectric.service.impl.AdminControllerServiceImpl;
import com.capeelectric.service.impl.UserDetailsServiceImpl;

@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
public class AdminControllServiceTest {

	@MockBean
	private AdminControllRepositary adminControllRepositary;

	@InjectMocks
	private AdminControllerServiceImpl adminControllerServiceImpl;

	@MockBean
	private BCryptPasswordEncoder passwordEncoder;
	private Admin admin;

	private Optional<Admin> optionaladmin;

	{
		admin = new Admin();
		admin.setUsername("lvsystem@capeindia.net");
		admin.setPassword("cape");
		admin.setEmail("lvsystem@capeindia.net");
		admin.setUsertype("Admin");
		admin.setAdminexist(true);
		admin.setAdminId(1);
        optionaladmin = Optional.of(admin);
	}

	@Test
	public void testSaveAdmin() throws UserException {

		when(adminControllRepositary.findByUsername("lvsystem@capeindia.net")).thenReturn(optionaladmin);
		UserException assertThrows = Assertions.assertThrows(UserException.class,
				() -> adminControllerServiceImpl.saveAdmin(admin));
		assertEquals("Admin already available", assertThrows.getMessage());
		admin.setAdminexist(false);
		Admin saveAdmin = adminControllerServiceImpl.saveAdmin(admin);
		assertNull(saveAdmin);
    }


	@Test
	public void testupdateAdminDetail() throws UserException {
		  Admin updateUserProfile = adminControllerServiceImpl.updateAdminDetails(admin);
		assertNull(updateUserProfile);
	}
	
	
	@Test
	public void testdeleteById()throws UserException{
		Optional<Admin> adminList;
		adminList = Optional.of(admin);

		when(adminControllRepositary.findById(admin.getAdminId())).thenReturn(adminList);
		adminControllerServiceImpl.deleteAdmin(1);
	}
    @Test
	public void testRetrieveAdminInformation() throws UserException {
		when(adminControllRepositary.findByUsername("lvsystem@capeindia.net")).thenReturn(optionaladmin);
		Admin retrieveAdminInformation = adminControllerServiceImpl.retrieveAdminInformation(admin.getEmail());
		assertEquals("lvsystem@capeindia.net", retrieveAdminInformation.getEmail());

		admin.setAdminexist(false);
		UserException assertThrows = Assertions.assertThrows(UserException.class,
				() -> adminControllerServiceImpl.retrieveAdminInformation(admin.getEmail()));
		assertEquals("Admin not available", assertThrows.getMessage());

	}
    @Test
	public void testfindByAdmin() throws ForgotPasswordException {
		Optional<Admin> adminlist = null;
		adminlist = Optional.ofNullable(admin);

		when(adminControllRepositary.findByUsername("lvsystem@capeindia.net")).thenReturn(adminlist);
		Admin findByUserName = adminControllerServiceImpl.findByAdmin("lvsystem@capeindia.net");
		assertEquals("lvsystem@capeindia.net", findByUserName.getUsername());
		
		ForgotPasswordException assertThrows = Assertions.assertThrows(ForgotPasswordException.class,
				() -> adminControllerServiceImpl.findByAdmin(null));
		assertEquals("Email is required", assertThrows.getMessage());

		when(adminControllRepositary.findByUsername("lvsystem@capeindia.net")).thenReturn(null);
		ForgotPasswordException assertThrows2 = Assertions.assertThrows(ForgotPasswordException.class,
				() -> adminControllerServiceImpl.findByAdmin("lvsystem@capeindia.net"));
		assertEquals("Email is not available with us", assertThrows2.getMessage());

	} 
    @Test
	public void testChangePassword() throws ChangePasswordException {
		BCryptPasswordEncoder encode = new BCryptPasswordEncoder();

		String encodePass = encode.encode("cape");
		optionaladmin.get().setPassword(encodePass);

		when(passwordEncoder.matches("cape", encodePass)).thenReturn(true);

		when(adminControllRepositary.findByUsername("lvsystem@capeindia.net")).thenReturn(optionaladmin);
		Admin changePassword = adminControllerServiceImpl.changePassword("lvsystem@capeindia.net", "cape", "cape123");
		assertNull(changePassword);

		ChangePasswordException assertThrows1 = Assertions.assertThrows(ChangePasswordException.class,
				() -> adminControllerServiceImpl.changePassword("lvsystem@capeindia.net", "cape1", "cape123"));
		assertEquals("Old password is not matching with encoded password", assertThrows1.getMessage());

		optionaladmin.get().setPassword(encodePass);
		ChangePasswordException assertThrows2 = Assertions.assertThrows(ChangePasswordException.class,
				() -> adminControllerServiceImpl.changePassword("lvsystem@capeindia.net", "cape", "cape"));
		assertEquals("Old password cannot be entered as new password", assertThrows2.getMessage());

		admin.setAdminexist(false);
		Admin changePassword2 = adminControllerServiceImpl.changePassword("lvsystem@capeindia.net", "cape", "cape123");
		assertNull(changePassword2);
	}

	@Test
	public void testUpdatedPassword() throws UpdatePasswordException {

		when(adminControllRepositary.findByUsername("lvsystem@capeindia.net")).thenReturn(optionaladmin);

		Admin updatePassword= adminControllerServiceImpl.updatePassword("lvsystem@capeindia.net", "cape123");
		assertNull(updatePassword);

		UsernameNotFoundException assertThrows = Assertions.assertThrows(UsernameNotFoundException.class,
				() -> adminControllerServiceImpl.updatePassword(null, "cape123"));
		assertEquals("adminname not valid", assertThrows.getMessage());

		admin.setAdminexist(false);
		  UpdatePasswordException assertThrows2 = Assertions.assertThrows(UpdatePasswordException.class,
				() -> adminControllerServiceImpl.updatePassword("lvsystem@capeindia.net", "cape123"));
		assertEquals("Admin Not available", assertThrows2.getMessage());

	}

}
