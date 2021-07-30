package com.capeelectric.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capeelectric.exception.UserException;
import com.capeelectric.model.User;
import com.capeelectric.repository.UserRepository;
import com.capeelectric.service.AdminControllService;

@Service
public class AdminControllerServiceImpl implements AdminControllService {

	private static final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

	@Autowired
	private UserRepository userRepository;

	@Override
	public List<User> getAllUser() throws UserException {
		logger.debug("Fetching The User&Viewer Data Starts");
		List<User> list = (List<User>) userRepository.findAll();

		List<User> list1 = list.stream()
				.filter(user -> user.getAuthorisedUser() == null)

//				|| user.getUserpermission().equalsIgnoreCase("Declined"))
				

				.collect(Collectors.toList());
		return list1;
		
//		List<User> list1 = list.stream()
//				.filter(user -> (user.getUsertype().equalsIgnoreCase("user")
//						|| user.getUsertype().equalsIgnoreCase("viewer")
//						|| user.getUsertype().equalsIgnoreCase("admin"))
//						&& user.getUserpermission() == null)
//				.collect(Collectors.toList());
//		return list1;



	}

	@Override
	public void updateAccessUserslist(List<User> user) throws UserException {
		logger.debug("Updating The Access of User&Viewer Details Starts ");
		for (User user1 : user) {
			userRepository.save(user1);
		}
	}
	
}
