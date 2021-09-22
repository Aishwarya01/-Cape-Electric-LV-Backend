package com.capeelectric.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
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
	
	private List<PeriodicInspectionComment> listOfComments;
	
	private String viewerName;

	/**
	 * @param IpaoInspection object 
	 * addInspectionDetails method to save IpaoInspection object into table
	 * 
	*/
	@Override
	public void addInspectionDetails(PeriodicInspection periodicInspection) throws InspectionException {
		listOfComments = new ArrayList<PeriodicInspectionComment>();
		
		if (periodicInspection.getUserName() != null && periodicInspection.getSiteId() != null) {
			Optional<PeriodicInspection> siteId = inspectionRepository.findBySiteId(periodicInspection.getSiteId());
			if (!siteId.isPresent() || !siteId.get().getSiteId().equals(periodicInspection.getSiteId())) {
				periodicInspectionComment = new PeriodicInspectionComment();
				periodicInspectionComment.setInspectorFlag("0");
				periodicInspectionComment.setViewerFlag("0");
				periodicInspectionComment.setNoOfComment(1);
				periodicInspectionComment.setPeriodicInspection(periodicInspection);
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
			List<PeriodicInspection> inspectionRepo = inspectionRepository.findByUserNameAndSiteId(userName, siteId);
			if (inspectionRepo != null) {
				for (PeriodicInspection periodicInspection : inspectionRepo) {
					sortingDateTime(periodicInspection.getPeriodicInspectorComment());
				}
				return inspectionRepo;
			} else {
				throw new InspectionException("Given UserName & Site doesn't exist Inspection");
			}

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
		if (periodicInspection != null && periodicInspection.getPeriodicInspectionId() != null
				&& periodicInspection.getPeriodicInspectionId() != 0 && periodicInspection.getSiteId() != null
				&& periodicInspection.getSiteId() != 0) {
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
	public void sendComments(String userName, Integer siteId, PeriodicInspectionComment periodicInspectionComment)
			throws InspectionException {
		PeriodicInspection periodicInspection = verifyCommentsInfo(userName, siteId, periodicInspectionComment, "SEND");
		if (periodicInspection != null) {
			inspectionRepository.save(periodicInspection);
		} else {
			throw new InspectionException("Periodic-Inspection information doesn't exist for given Site-Id");
		}
	}

	@Override
	public String replyComments(String inspectorUserName, Integer siteId,
			PeriodicInspectionComment periodicInspectionComment) throws InspectionException {
		PeriodicInspection periodicInspection = verifyCommentsInfo(inspectorUserName, siteId, periodicInspectionComment,
				"REPLY");
		if (periodicInspection != null) {
			inspectionRepository.save(periodicInspection);
			return viewerName;
		} else {
			throw new InspectionException("Periodic-Inspection information doesn't exist for given Site-Id");
		}
	}

	@Override
	public void approveComments(String userName, Integer siteId, PeriodicInspectionComment periodicInspectionComment)
			throws InspectionException {
		PeriodicInspection periodicInspection = verifyCommentsInfo(userName, siteId, periodicInspectionComment,
				"APPROVE");
		if (periodicInspection != null) {
			inspectionRepository.save(periodicInspection);
		} else {
			throw new InspectionException("Periodic-Inspection information doesn't exist for given Site-Id");
		}
	}

	private PeriodicInspection verifyCommentsInfo(String userName, Integer siteId,
			PeriodicInspectionComment periodicInspectionComment, String process) throws InspectionException {

		Boolean flagInspectionComment = true;
		if (userName != null && siteId != null && periodicInspectionComment != null) {
			Optional<Site> siteRepo = siteRepository.findById(siteId);
			if (siteRepo.isPresent() && siteRepo.get().getSiteId().equals(siteId)) {
				Optional<PeriodicInspection> periodicInspectionRepo = inspectionRepository.findBySiteId(siteId);
				if (periodicInspectionRepo.isPresent() && periodicInspectionRepo.get() != null
						&& periodicInspectionRepo.get().getUserName() != null
						&& checkInspectorViewer(userName, process, siteRepo, periodicInspectionRepo)) {
					PeriodicInspection periodicInspection = periodicInspectionRepo.get();
					periodicInspection.setUpdatedDate(LocalDateTime.now());
					periodicInspection.setUpdatedBy(userName);
					List<PeriodicInspectionComment> periodicInspectorCommentRepo = periodicInspection
							.getPeriodicInspectorComment();

					for (PeriodicInspectionComment periodicInspectionCommentItr : periodicInspectorCommentRepo) {
						if (periodicInspectionCommentItr.getCommentsId()
								.equals(periodicInspectionComment.getCommentsId())) {
							flagInspectionComment = false;

							periodicInspectionCommentItr.setPeriodicInspection(periodicInspection);

							if (process.equalsIgnoreCase("SEND")) {
								periodicInspectionCommentItr.setViewerDate(LocalDateTime.now());
								periodicInspectionCommentItr
										.setViewerComment(periodicInspectionComment.getViewerComment());
								periodicInspectionCommentItr.setViewerFlag("1");
								periodicInspectorCommentRepo.add(periodicInspectionCommentItr);
								periodicInspection.setPeriodicInspectorComment(periodicInspectorCommentRepo);
								return periodicInspection;
							}
							if (process.equalsIgnoreCase("REPLY")) {
								periodicInspectionCommentItr.setInspectorDate(LocalDateTime.now());
								periodicInspectionCommentItr
										.setInspectorComment(periodicInspectionComment.getInspectorComment());
								periodicInspectionCommentItr.setInspectorFlag("1");
								periodicInspectorCommentRepo.add(periodicInspectionCommentItr);
								periodicInspection.setPeriodicInspectorComment(periodicInspectorCommentRepo);
								return periodicInspection;
							}
							if (process.equalsIgnoreCase("APPROVE")) {
								periodicInspectionCommentItr.setViewerDate(LocalDateTime.now());
								periodicInspectionCommentItr
										.setApproveOrReject(periodicInspectionComment.getApproveOrReject());
								periodicInspectorCommentRepo.add(periodicInspectionCommentItr);
								periodicInspection.setPeriodicInspectorComment(periodicInspectorCommentRepo);
								return periodicInspection;
							}
						}
					}
					if (flagInspectionComment) {

						if (process.equalsIgnoreCase("SEND")) {
							periodicInspectionComment.setNoOfComment(
									checkNoOfComments(periodicInspection.getPeriodicInspectorComment()));
							periodicInspectionComment.setPeriodicInspection(periodicInspection);
							periodicInspectionComment.setViewerDate(LocalDateTime.now());
							periodicInspectionComment.setViewerFlag("1");
							periodicInspectionComment.setInspectorFlag("0");
							periodicInspectorCommentRepo.add(periodicInspectionComment);
							periodicInspection.setPeriodicInspectorComment(periodicInspectorCommentRepo);
							return periodicInspection;
						} else {
							throw new InspectionException("Sending viewer comments faild");
						}
					}
				} else {
					throw new InspectionException("Given username not have access for comments");
				}

			} else {
				throw new InspectionException("Siteinformation doesn't exist, try with different Site-Id");
			}

		} else {
			throw new InspectionException("Invalid inputs");
		}
		return null;
	}
	
	private void sortingDateTime(List<PeriodicInspectionComment> listOfComments) {
		Collections.sort(listOfComments, (o1, o2) -> o1.getViewerDate().compareTo(o2.getViewerDate()));
	}
	
	private Integer checkNoOfComments(List<PeriodicInspectionComment> listOfComments) {
		Integer maxNum = 0;
		String approveRejectedFlag = "";
		for (PeriodicInspectionComment periodicInspectionComment : listOfComments) {
			if (periodicInspectionComment != null && maxNum <= periodicInspectionComment.getNoOfComment()) {
				maxNum = periodicInspectionComment.getNoOfComment();
				approveRejectedFlag = periodicInspectionComment.getApproveOrReject();
			}
		}
		if (approveRejectedFlag != null && approveRejectedFlag.equalsIgnoreCase("APPROVED")) {
			return maxNum + 1;
		} else {
			return maxNum;
		}
	}
	
	private Boolean checkInspectorViewer(String userName, String process, Optional<Site> siteRepo,
			Optional<PeriodicInspection> periodicInspectionRepo) throws InspectionException {
		Boolean flag = false;
		if (process.equalsIgnoreCase("REPLY")) {
			if (siteRepo.get().getUserName().equalsIgnoreCase(userName)
					&& periodicInspectionRepo.get().getUserName() != null
					&& siteRepo.get().getUserName().equalsIgnoreCase(periodicInspectionRepo.get().getUserName())) {
				Set<SitePersons> sitePersons = siteRepo.get().getSitePersons();
				for (SitePersons sitePersonsItr : sitePersons) {
					if (sitePersonsItr != null && sitePersonsItr.getPersonInchargeEmail() != null) {
						viewerName = sitePersonsItr.getPersonInchargeEmail();
						return flag = true;
					}
				}
			} else {
				throw new InspectionException("Given userName not allowing for " + process + " comment");
			}

		} else if (process.equalsIgnoreCase("SEND") || process.equalsIgnoreCase("APPROVE")) {

			Set<SitePersons> sitePersons = siteRepo.get().getSitePersons();
			for (SitePersons sitePersonsItr : sitePersons) {
				if (sitePersonsItr != null && sitePersonsItr.getPersonInchargeEmail() != null
						&& sitePersonsItr.getPersonInchargeEmail().equalsIgnoreCase(userName)) {
					return flag = true;
				} else {
					throw new InspectionException("Given userName not allowing for " + process + " comment");
				}
			}
		}
		return flag;
	}
}
