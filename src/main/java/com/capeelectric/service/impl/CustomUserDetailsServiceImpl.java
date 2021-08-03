package com.capeelectric.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.capeelectric.model.Admin;
import com.capeelectric.model.CustomUserDetails;
import com.capeelectric.repository.AdminControllRepositary;

@Service
public class CustomUserDetailsServiceImpl implements UserDetailsService {

	private static final Logger logger = LoggerFactory.getLogger(CustomUserDetailsServiceImpl.class);

	@Autowired
	private AdminControllRepositary adminControllRepositary;

	@Override
	public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		logger.debug("Load User By UserName starts");
        Admin admin = adminControllRepositary.findByUsername(username).get();
        CustomUserDetails userDetails = null;
        if (admin != null) {

//            if (user.getAuthorisedUser() != null) {
//                if (!(user.getAuthorisedUser().equalsIgnoreCase("Declined"))) {

                    userDetails = new CustomUserDetails(admin);

//                } else {
//                    logger.debug("Authenticating Declined ");
//                    throw new UsernameNotFoundException(
//                            " Your Account will be Declined : " + username);
//                }
//
//            } else {
//                logger.debug("After Registration Process authenticatting the UserPermission");
//                throw new UsernameNotFoundException(
//                        " Please Wait Your Account will be Authenticatting : " + username);
//            }
        } else {
            logger.debug("Load User By UserName ends");
            throw new UsernameNotFoundException("User not exist with name : " + username);
        }
        logger.debug("Load User By UserName ends");
        return userDetails;
    }
}