package com.capeelectric.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capeelectric.exception.ObservationException;
import com.capeelectric.model.ObservationComponent;

import com.capeelectric.repository.ObservationRepository;
import com.capeelectric.service.ObservationService;
import com.capeelectric.util.UserFullName;

/**
 * 
 * @author capeelectricsoftware
 *
 */
@Service
public class ObservationServiceImpl implements ObservationService {

	@Autowired
	private ObservationRepository observationRepository;

	@Autowired
	private UserFullName userFullName;

	@Override
	public void addObservation(ObservationComponent observationComponent) throws ObservationException {
		if (observationComponent != null && observationComponent.getUserName() != null) {
			Optional<ObservationComponent> observationRepo = observationRepository
					.findByUserNameAndSiteIdAndObservationComponent(observationComponent.getUserName(),
							observationComponent.getSiteId(), observationComponent.getObservationComponent());
			if (!observationRepo.isPresent()) {
				observationComponent.setCreatedDate(LocalDateTime.now());
				observationComponent.setCreatedBy(userFullName.findByUserName(observationComponent.getUserName()));
				observationRepository.save(observationComponent);
			}

			else {

				throw new ObservationException("UserName&SiteId&ObservationComponent already exists");
			}

		} else {
			throw new ObservationException("Invalid Inputs");
		}
	}

	@Override
	public void updateObservation(ObservationComponent observationComponent) throws ObservationException {
		if (observationComponent != null && observationComponent.getObservationId() != null
				&& observationComponent.getObservationId() != 0 && observationComponent.getSiteId() != null
				&& observationComponent.getSiteId() != 0) {
			Optional<ObservationComponent> observationRepo = observationRepository
					.findByUserNameAndSiteIdAndObservationComponent(observationComponent.getUserName(),
							observationComponent.getSiteId(), observationComponent.getObservationComponent());
			if (observationRepo.isPresent()
					&& observationRepo.get().getSiteId().equals(observationComponent.getSiteId())) {
				observationComponent.setUpdatedDate(LocalDateTime.now());
				observationComponent.setUpdatedBy(userFullName.findByUserName(observationComponent.getUserName()));
				observationRepository.save(observationComponent);

			} else {
				throw new ObservationException("Given SiteId and ObservationId is Invalid");
			}

		} else {
			throw new ObservationException("Invalid inputs");
		}

	}

	@Override
	public ObservationComponent retrieveObservation(String userName, Integer siteId, String observationComponent)
			throws ObservationException {
		if (userName != null && !userName.isEmpty() && siteId != null && siteId != 0) {
			Optional<ObservationComponent> observationRepo = observationRepository
					.findByUserNameAndSiteIdAndObservationComponent(userName, siteId, observationComponent);
			if (observationRepo.isPresent() && observationRepo.get() != null) {
				return observationRepo.get();
			} else {
				throw new ObservationException("Given UserName & SiteId doesn't exist Observation");
			}
		} else {
			throw new ObservationException("Invalid Inputs");

		}
	}

	@Override
	public List<ObservationComponent> retrieveObservationsInSummary(String userName, Integer siteId)
			throws ObservationException {
		if (userName != null && !userName.isEmpty() && siteId != null && siteId != 0) {
			List<ObservationComponent> observationRepo = observationRepository.findByUserNameAndSiteId(userName,
					siteId);
			if (observationRepo != null) {
				return observationRepo;
			} else {
				throw new ObservationException("Given UserName & SiteId doesn't exist In Observation");
			}
		} else {
			throw new ObservationException("Invalid Inputs");

		}
	}

}
