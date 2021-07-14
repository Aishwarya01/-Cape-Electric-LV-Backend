package com.capeelectric.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capeelectric.exception.InspectionException;
import com.capeelectric.model.PeriodicInspection;
import com.capeelectric.repository.InspectionRepository;
import com.capeelectric.service.InspectionService;
/**
 * This InspectionServiceImpl class to add and retrieve the IpaoInspection object
 * @author capeelectricsoftware
 *
 */
@Service
public class InspectionServiceImpl implements InspectionService {

	@Autowired
	private InspectionRepository inspectionRepository;

	/**
	 * @param IpaoInspection object 
	 * addInspectionDetails method to save IpaoInspection object into table
	 * 
	*/
	@Override
	public void addInspectionDetails(PeriodicInspection periodicInspection) throws InspectionException {
		if (periodicInspection.getUserName() != null && periodicInspection.getSiteId() != null) {
			Optional<PeriodicInspection> siteId = inspectionRepository.findBySiteId(periodicInspection.getSiteId());
			if (!siteId.isPresent() || !siteId.get().getSiteId().equals(periodicInspection.getSiteId())) {
				periodicInspection.setCreatedDate(LocalDateTime.now());
				inspectionRepository.save(periodicInspection);
			} else {
				throw new InspectionException("siteId present");
			}

		} else {
			throw new InspectionException("invalid input");
		}

	}

	/**
	 * @param userName,siteId
	 * retrieveInspectionDetails method to retrieve data based on userName,siteId
	 * @return List<IpaoInspection> object 
	*/
	@Override
	public List<PeriodicInspection> retrieveInspectionDetails(String userName, Integer siteId)
			throws InspectionException {

		if (userName != null && !userName.isEmpty() && siteId != null) {
			return inspectionRepository.findByUserNameAndSiteId(userName, siteId);
		} else {
			throw new InspectionException("Invalid inputs");
		}
	}

}
