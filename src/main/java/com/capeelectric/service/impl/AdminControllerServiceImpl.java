package com.capeelectric.service.impl;

import java.time.LocalDateTime;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.capeelectric.exception.UserException;
import com.capeelectric.model.Admin;
import com.capeelectric.repository.AdminControllRepositary;
import com.capeelectric.service.AdminControllService;

@Service
public class AdminControllerServiceImpl implements AdminControllService {

	private static final Logger logger = LoggerFactory.getLogger(AdminControllerServiceImpl.class);

	@Autowired
	private AdminControllRepositary adminControllRepositary;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public Admin saveAdmin(Admin admin) throws UserException {
		logger.debug("Save Admin Starts");
		if (admin.getUsername() != null) {
			Optional<Admin> createdAdmin = adminControllRepositary.findByUsername(admin.getUsername	());
			if (createdAdmin.isPresent() && createdAdmin.get() != null && createdAdmin.get().isAdminexist()) {
				logger.debug("Save Admin Ends");
				throw new UserException("Admin already available");
			} else {
				String password = admin.getPassword();
				admin.setPassword(passwordEncoder.encode(password));
				admin.setAdminexist(true);
				admin.setCreationdate(LocalDateTime.now());
				admin.setUpdateddate(LocalDateTime.now());
				logger.debug("Save Admin Ends");
				return adminControllRepositary.save(admin);
			}
		} else {
			throw new UserException("invalid input");
		}
	}

	public Admin retrieveManagementInformation(String email) throws UserException {
		logger.debug("Retrieve Admin Starts");
		Optional<Admin> retrievedAdmin = adminControllRepositary.findByUsername(email);
		if (retrievedAdmin.isPresent() && retrievedAdmin.get() != null && retrievedAdmin.get().isAdminexist()) {
			return retrievedAdmin.get();
		} else {
			throw new UserException("Admin not available");
		}
	}

	@Override
	public Admin updateManagementDetails(Admin admin) throws UserException  {
		logger.debug("Update Management Profile Starts");
		admin.setUpdateddate(LocalDateTime.now());
		logger.debug("Update Management Profile Ends");
		return adminControllRepositary.save(admin);
	}

	@Override
	public void deleteById(Integer adminId) throws UserException {
		if (adminId != null && adminId != 0) {

			Optional<Admin> adminRepo = adminControllRepositary.findById(adminId);

			if (adminRepo.isPresent() && adminRepo != null) {

				adminControllRepositary.deleteById(adminId);
			} else {
				throw new UserException(adminId + " : this adminid not present");
			}

		} else {
			throw new UserException("invalid input");
		}

	}

//	@Override
//    public List<User> getAllUser() throws UserException {
//        logger.debug("Fetching The User&Viewer Data Starts");
//        List<User> list = (List<User>) userRepository.findAll();
//
//        List<User> list1 = list.stream()
//                .filter(user -> user.getAuthorisedUser() == null)
//
//               || user.getUserpermission().equalsIgnoreCase("Declined"))
//
//                .collect(Collectors.toList());
//        return list1;

//        List<User> list1 = list.stream()
//                .filter(user -> (user.getUsertype().equalsIgnoreCase("user")
//                        || user.getUsertype().equalsIgnoreCase("viewer")
//                        || user.getUsertype().equalsIgnoreCase("admin"))
//                        && user.getUserpermission() == null)
//                .collect(Collectors.toList());
//        return list1;
//
//    }

//    @Override
//    public void updateAccessUserslist(List<User> user) throws UserException {
//        logger.debug("Updating The Access of User&Viewer Details Starts ");
//        for (User user1 : user) {
//            userRepository.save(user1);
//        }
//   }

}
