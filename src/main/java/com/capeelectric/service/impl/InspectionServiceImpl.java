package com.capeelectric.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capeelectric.exception.InspectionException;
import com.capeelectric.model.PeriodicInspection;
import com.capeelectric.model.PeriodicInspectionComment;
import com.capeelectric.model.Site;
import com.capeelectric.model.SitePersons;
import com.capeelectric.repository.InspectionRepository;
import com.capeelectric.repository.SiteRepository;
import com.capeelectric.service.InspectionService;
import com.capeelectric.util.UserFullName;
/**
 * This InspectionServiceImpl class to add and retrieve the IpaoInspection object
 * @author capeelectricsoftware
 *
 */
@Service
public class InspectionServiceImpl implements InspectionService {

	@Autowired
	private InspectionRepository inspectionRepository;

	@Autowired
	private UserFullName userFullName;
	
	@Autowired
	private SiteRepository siteRepository;
	
	private PeriodicInspectionComment periodicInspectionComment;
	
	private List<PeriodicInspectionComment> listOfComments = new ArrayList<PeriodicInspectionComment>();

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
				periodicInspectionComment = new PeriodicInspectionComment();
				periodicInspectionComment.setInspectorFlag("0");
				periodicInspectionComment.setViewerFlag("0");
				listOfComments.add(periodicInspectionComment);
				periodicInspection.setPeriodicInspectorComment(listOfComments);
				periodicInspection.setCreatedDate(LocalDateTime.now());
				periodicInspection.setUpdatedDate(LocalDateTime.now());
				periodicInspection.setCreatedBy(userFullName.getFullName(periodicInspection.getUserName()));
				periodicInspection.setUpdatedBy(userFullName.getFullName(periodicInspection.getUserName()));
				inspectionRepository.save(periodicInspection);
			} else {
				throw new InspectionException("SiteId already present");
			}

		} else {
			throw new InspectionException("Invalid input");

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
			throw new InspectionException("Invalid Inputs");
		}
	}
	
	/**
	 * @reportId,siteId must required
	 * @param PeriodicInspection Object
	 * updateInspectionDetails method to finding the given PeriodicInspectionId is available or not in DB,
	 * if available only allowed for updating 
	 * 
	*/
	@Override
	public void updateInspectionDetails(PeriodicInspection periodicInspection) throws InspectionException {
		if (periodicInspection != null && periodicInspection.getPeriodicInspectionId() != null && periodicInspection.getPeriodicInspectionId() != 0
				&& periodicInspection.getSiteId() != null && periodicInspection.getSiteId() != 0) {
			Optional<PeriodicInspection> periodicInspectionRepo = inspectionRepository
					.findById(periodicInspection.getPeriodicInspectionId());
			if (periodicInspectionRepo.isPresent()
					&& periodicInspectionRepo.get().getSiteId().equals(periodicInspection.getSiteId())) {
				periodicInspection.setUpdatedDate(LocalDateTime.now());
				periodicInspection.setUpdatedBy(userFullName.getFullName(periodicInspection.getUserName()));
				inspectionRepository.save(periodicInspection);
			} else {
				throw new InspectionException("Given SiteId and ReportId is Invalid");
			}

		} else {
			throw new InspectionException("Invalid inputs");
		}
		
	}

	@Override
	public PeriodicInspection sendComments(String userName, Integer siteId, String comments) throws InspectionException {
		if (userName != null && siteId != null && comments != null) {
			Optional<PeriodicInspection> periodicInspectionRepo = inspectionRepository.findBySiteId(siteId);
			if (periodicInspectionRepo.isPresent() && periodicInspectionRepo.get().getUserName().equalsIgnoreCase(userName)) {
				PeriodicInspection periodicInspection = periodicInspectionRepo.get();
				periodicInspection.setUpdatedDate(LocalDateTime.now());
				periodicInspection.setUpdatedBy(userName);
				periodicInspectionComment = new PeriodicInspectionComment();
				periodicInspectionComment.setViewerDate(LocalDateTime.now());
				periodicInspectionComment.setViewerComment(comments);
				periodicInspectionComment.setPeriodicInspection(periodicInspection);
				periodicInspectionComment.setViewerFlag("1");
				listOfComments.add(periodicInspectionComment);
				periodicInspection.setPeriodicInspectorComment(listOfComments);
				return inspectionRepository.save(periodicInspection);
			} else {
				throw new InspectionException("Given SiteId is Invalid");
			}
		} else {
			throw new InspectionException("Invalid inputs");
		}
	}

	@Override
	public String replyComments(String inspectorUserName, Integer siteId, PeriodicInspectionComment periodicInspectionComment) throws InspectionException {
		
		if (inspectorUserName != null && siteId != null && periodicInspectionComment.getCommentsId() != null) {
			Optional<Site> siteRepo = siteRepository.findById(siteId);
			if (siteRepo.isPresent() && siteRepo.get().getSiteId().equals(siteId)) {
				Set<SitePersons> sitePersons = siteRepo.get().getSitePersons();
				for (SitePersons sitePersons2 : sitePersons) {
					Optional<PeriodicInspection> periodicInspectionRepo = inspectionRepository
							.findBySiteId(siteId);
					if (periodicInspectionRepo.isPresent() && periodicInspectionRepo.get().getUserName()
							.equalsIgnoreCase(sitePersons2.getPersonInchargeEmail())) {
						PeriodicInspection periodicInspection = periodicInspectionRepo.get();
						periodicInspection.setUpdatedDate(LocalDateTime.now());
						periodicInspection.setUpdatedBy(inspectorUserName); 
						List<PeriodicInspectionComment> periodicInspectorCommentRepo = periodicInspection.getPeriodicInspectorComment();
						
						for (PeriodicInspectionComment periodicInspectionCommentItr : periodicInspectorCommentRepo) {
							if (periodicInspectionCommentItr.getCommentsId().equals(periodicInspectionComment.getCommentsId())) {
								periodicInspectionCommentItr.setInspectorDate(LocalDateTime.now());
								periodicInspectionCommentItr.setPeriodicInspection(periodicInspection);
								periodicInspectionCommentItr.setInspectorComment(periodicInspectionComment.getInspectorComment());
								periodicInspectionCommentItr.setInspectorFlag("1");
								periodicInspectorCommentRepo.add(periodicInspectionCommentItr);
								periodicInspection.setPeriodicInspectorComment(periodicInspectorCommentRepo);
								inspectionRepository.save(periodicInspection);
								return periodicInspection.getUserName();
							}
						}
						
					} 
				}

			} else {
				throw new InspectionException("Invalid Site-Id");
			}

		} else {
			throw new InspectionException("Invalid inputs");
		}
		return null;
	}

}
