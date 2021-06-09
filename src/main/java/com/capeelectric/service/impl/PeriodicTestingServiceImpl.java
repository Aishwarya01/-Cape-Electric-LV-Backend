package com.capeelectric.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capeelectric.exception.PeriodicTestingException;
import com.capeelectric.model.Testing;
import com.capeelectric.repository.TestInfoRepository;
import com.capeelectric.service.PeriodicTestingService;

/**
 * This TestInfoServiceImpl service class doing save and retrieve operation based on Testing
 * @author capeelectricsoftware
 *
 */
@Service
public class PeriodicTestingServiceImpl implements PeriodicTestingService {

	@Autowired
	private TestInfoRepository testInfoRepository;

	/**
	 * @param Testing
	 * addTestInfo method to Testing object will be storing corresponding tables
	*/
	@Override
	public void addTestInfo(Testing testing) throws PeriodicTestingException {
		if (testing.getUserName() != null && testing.getSiteId() != null) {

			Optional<Testing> testingRepo = testInfoRepository.findByUserNameAndSiteId(testing.getUserName(),
					testing.getSiteId());
			if (!testingRepo.isPresent() || !testingRepo.get().getSiteId().equals(testing.getSiteId())) {
				testInfoRepository.save(testing);
			} else {
				throw new PeriodicTestingException("SiteId Already Present");
			}
		} else {
			throw new PeriodicTestingException("UserName and SiteId Invalid Input");
		}
	}

	/**
	 * @param userName,siteId
	 * retrieveSummary method to retrieve based on userName and siteId
	 * @return Optional<Testing>
	 */
	@Override
	public Optional<Testing> retrieveTestInfo(String userName, Integer siteId) throws PeriodicTestingException {
		if (userName != null && !userName.isEmpty() && siteId != null && siteId != 0) {
			return testInfoRepository.findByUserNameAndSiteId(userName, siteId);
		} else {
			throw new PeriodicTestingException("UserName and SiteId Invalid Input");
		}
	}

}
