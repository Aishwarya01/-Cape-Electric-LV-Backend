package com.capeelectric.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capeelectric.exception.CompanyDetailsException;
import com.capeelectric.exception.InspectionException;
import com.capeelectric.model.IpaoInspection;
import com.capeelectric.model.PeriodicInspection;
import com.capeelectric.model.PeriodicInspectionComment;
import com.capeelectric.model.Site;
import com.capeelectric.model.SitePersons;
import com.capeelectric.model.Testing;
import com.capeelectric.repository.InspectionRepository;
import com.capeelectric.repository.SiteRepository;
import com.capeelectric.repository.TestInfoRepository;
import com.capeelectric.service.InspectionService;
import com.capeelectric.util.Constants;
import com.capeelectric.util.SiteDetails;
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
	
	@Autowired
	private SiteDetails siteDetails;
	
	private static Integer locationCount=0;
	
	@Autowired
	private TestInfoRepository testInfoRepository;

	/**
	 * @param IpaoInspection object 
	 * addInspectionDetails method to save IpaoInspection object into table
	 * @throws CompanyDetailsException 
	 * 
	*/
	@Override
	public void addInspectionDetails(PeriodicInspection periodicInspection) throws InspectionException, CompanyDetailsException {
		listOfComments = new ArrayList<PeriodicInspectionComment>();
        int i=0;
		if (periodicInspection.getUserName() != null && periodicInspection.getSiteId() != null
				&& periodicInspection.getIpaoInspection() != null) {
			List<IpaoInspection> ipaoInspection = periodicInspection.getIpaoInspection();
			Optional<PeriodicInspection> siteId = inspectionRepository
					.findBySiteId(periodicInspection.getSiteId());
			if (!siteId.isPresent() || !siteId.get().getSiteId().equals(periodicInspection.getSiteId())) {
				if (ipaoInspection != null && ipaoInspection.size() > 0) {
					for (IpaoInspection ipaoInspectionItr : ipaoInspection) {
						ipaoInspectionItr.setLocationCount(++locationCount);
						if (ipaoInspectionItr !=null && ipaoInspectionItr.getConsumerUnit() != null && ipaoInspectionItr.getCircuit() != null
								&& ipaoInspectionItr.getIsolationCurrent() != null
								&& ipaoInspectionItr.getConsumerUnit().size() > 0
								&& ipaoInspectionItr.getCircuit().size() > 0
								&& ipaoInspectionItr.getIsolationCurrent().size() > 0) {
							i++;
							if (i == ipaoInspection.size()) {
								periodicInspectionComment = new PeriodicInspectionComment();
								periodicInspectionComment.setInspectorFlag(Constants.INTIAL_FLAG_VALUE);
								periodicInspectionComment.setViewerFlag(Constants.INTIAL_FLAG_VALUE);
								periodicInspectionComment.setNoOfComment(1);
								periodicInspectionComment.setViewerDate(LocalDateTime.now());
								periodicInspectionComment.setPeriodicInspection(periodicInspection);
								listOfComments.add(periodicInspectionComment);
								periodicInspection.setPeriodicInspectorComment(listOfComments);
								periodicInspection.setCreatedDate(LocalDateTime.now());
								periodicInspection.setUpdatedDate(LocalDateTime.now());
								periodicInspection
										.setCreatedBy(userFullName.findByUserName(periodicInspection.getUserName()));
								periodicInspection
										.setUpdatedBy(userFullName.findByUserName(periodicInspection.getUserName()));

								inspectionRepository.save(periodicInspection);
								siteDetails.updateSite(periodicInspection.getSiteId(),
										periodicInspection.getUserName());
							}

						} else {
							throw new InspectionException("Please fill all the fields before clicking next button");
						}
					}
				} else {
					throw new InspectionException("SiteId already present");
				}

			} else {
				throw new InspectionException("Inspection data contains duplicate Object");
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
	public PeriodicInspection retrieveInspectionDetails(String userName, Integer siteId)
			throws InspectionException {
		if (userName != null && !userName.isEmpty() && siteId != null) {
			PeriodicInspection inspectionRepo = inspectionRepository.findByUserNameAndSiteId(userName, siteId);
			if (inspectionRepo != null) {
				inspectionRepo.setIpaoInspection(findNonRemovedInspectionLocation(inspectionRepo));
				sortingDateTime(inspectionRepo.getPeriodicInspectorComment());
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
	 * @throws CompanyDetailsException 
	 * 
	*/
	@Override
	public void updateInspectionDetails(PeriodicInspection periodicInspection) throws InspectionException, CompanyDetailsException {
		if (periodicInspection != null && periodicInspection.getPeriodicInspectionId() != null
				&& periodicInspection.getPeriodicInspectionId() != 0 && periodicInspection.getSiteId() != null
				&& periodicInspection.getSiteId() != 0) {
			Optional<PeriodicInspection> periodicInspectionRepo = inspectionRepository
					.findById(periodicInspection.getPeriodicInspectionId());
			if (periodicInspectionRepo.isPresent()
					&& periodicInspectionRepo.get().getSiteId().equals(periodicInspection.getSiteId())) {
				addRemoveStatusInTesting(periodicInspection.getIpaoInspection());
				List<IpaoInspection> ipaoInspection = periodicInspection.getIpaoInspection();
				 //locationcount value adding for new location
				for (IpaoInspection ipaoInspectionItr : ipaoInspection) {
					if (ipaoInspectionItr !=null && ipaoInspectionItr.getLocationCount() == null) {
						ipaoInspectionItr.setLocationCount(++locationCount);
					}
				}
				periodicInspection.setUpdatedDate(LocalDateTime.now());
				periodicInspection.setUpdatedBy(userFullName.findByUserName(periodicInspection.getUserName()));
				inspectionRepository.save(periodicInspection);
				siteDetails.updateSite(periodicInspection.getSiteId(), periodicInspection.getUserName());							
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
		PeriodicInspection periodicInspection = verifyCommentsInfo(userName, siteId, periodicInspectionComment, Constants.SEND_COMMENT);
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
				Constants.REPLY_COMMENT);
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
				Constants.APPROVE_REJECT_COMMENT);
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
			if (siteRepo.isPresent() && siteRepo.get().getSiteId().equals(siteId)
					&& siteRepo.get().getAssignedTo() != null && siteRepo.get().getUserName() != null
					&& siteRepo.get().getSite() != null) {
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

							if (process.equalsIgnoreCase(Constants.SEND_COMMENT)) {
								periodicInspectionCommentItr.setSiteName(siteRepo.get().getSite());
								periodicInspectionCommentItr.setInspectorEmail(siteRepo.get().getUserName());
								periodicInspectionCommentItr.setViewerUserEmail(siteRepo.get().getAssignedTo());
								periodicInspectionCommentItr.setViewerDate(LocalDateTime.now());
								periodicInspectionCommentItr
										.setViewerComment(periodicInspectionComment.getViewerComment());
								periodicInspectionCommentItr.setViewerFlag(Constants.INCREASED_FLAG_VALUE);
								periodicInspectionCommentItr.setViewerUserName(userFullName.findByUserName(userName));
								periodicInspectorCommentRepo.add(periodicInspectionCommentItr);
								periodicInspection.setPeriodicInspectorComment(periodicInspectorCommentRepo);
								return periodicInspection;
							}
							if (process.equalsIgnoreCase(Constants.REPLY_COMMENT)) {
								periodicInspectionCommentItr.setInspectorDate(LocalDateTime.now());
								periodicInspectionCommentItr
										.setInspectorUserName(userFullName.findByUserName(userName));
								periodicInspectionCommentItr
										.setInspectorComment(periodicInspectionComment.getInspectorComment());
								periodicInspectionCommentItr.setInspectorFlag(Constants.INCREASED_FLAG_VALUE);
								periodicInspectorCommentRepo.add(periodicInspectionCommentItr);
								periodicInspection.setPeriodicInspectorComment(periodicInspectorCommentRepo);
								return periodicInspection;
							}
							if (process.equalsIgnoreCase(Constants.APPROVE_REJECT_COMMENT)) {
								periodicInspectionCommentItr.setViewerUserName(userFullName.findByUserName(userName));
								periodicInspectionCommentItr
										.setApproveOrReject(periodicInspectionComment.getApproveOrReject());
								periodicInspectorCommentRepo.add(periodicInspectionCommentItr);
								periodicInspection.setPeriodicInspectorComment(periodicInspectorCommentRepo);
								return periodicInspection;
							}
						}
					}
					if (flagInspectionComment) {

						if (process.equalsIgnoreCase(Constants.SEND_COMMENT)) {
							periodicInspectionComment.setNoOfComment(
									checkNoOfComments(periodicInspection.getPeriodicInspectorComment()));
							periodicInspectionComment.setPeriodicInspection(periodicInspection);
							periodicInspectionComment.setSiteName(siteRepo.get().getSite());
							periodicInspectionComment.setInspectorEmail(siteRepo.get().getUserName());
							periodicInspectionComment.setViewerUserEmail(siteRepo.get().getAssignedTo());
							periodicInspectionComment.setViewerDate(LocalDateTime.now());
							periodicInspectionComment.setViewerFlag(Constants.INCREASED_FLAG_VALUE);
							periodicInspectionComment.setInspectorFlag(Constants.INTIAL_FLAG_VALUE);
							periodicInspectionComment.setViewerUserName(userFullName.findByUserName(userName));
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
			throw new InspectionException("Invalid Inputs");
		}
		return null;
	}
	
	private void sortingDateTime(List<PeriodicInspectionComment> listOfComments) {
		if (listOfComments.size() > 1) {
			Collections.sort(listOfComments, (o1, o2) -> o1.getViewerDate().compareTo(o2.getViewerDate()));
		}
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
		if (approveRejectedFlag != null && approveRejectedFlag.equalsIgnoreCase(Constants.APPROVE_REJECT_COMMENT)) {
			return maxNum + 1;
		} else {
			return maxNum;
		}
	}
	
	private Boolean checkInspectorViewer(String userName, String process, Optional<Site> siteRepo,
			Optional<PeriodicInspection> periodicInspectionRepo) throws InspectionException {
		Boolean flag = false;
		if (process.equalsIgnoreCase(Constants.REPLY_COMMENT)) {
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

		} else if (process.equalsIgnoreCase(Constants.SEND_COMMENT) || process.equalsIgnoreCase(Constants.APPROVE_REJECT_COMMENT)) {

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
	
	private void addRemoveStatusInTesting(List<IpaoInspection> listIpaoInspection)
			throws InspectionException {

		for (IpaoInspection ipaoInspectionItr : listIpaoInspection) {
			if (ipaoInspectionItr != null && ipaoInspectionItr.getLocationCount() != null && ipaoInspectionItr.getInspectionFlag().equalsIgnoreCase("R")
					) {
				try {
					Testing testingRepo = testInfoRepository.findByLocationCount(ipaoInspectionItr.getLocationCount());
					if (testingRepo != null
							&& testingRepo.getLocationCount().equals(ipaoInspectionItr.getLocationCount())) {
						testingRepo.setTestingStatus("R");
						testInfoRepository.save(testingRepo);
					}
				} catch (Exception e) {
					throw new InspectionException(
							"Please check removed Inspection Location data not avlible in PeriodicTesting");
				}
			}
		}
	}
	
	private List<IpaoInspection> findNonRemovedInspectionLocation(PeriodicInspection inspectionRepo) {

		ArrayList<IpaoInspection>inspectionReport = new ArrayList<IpaoInspection>();
		List<IpaoInspection> findNonRemoveLocation = inspectionRepo.getIpaoInspection();
		for (IpaoInspection inspectionLocationReport : findNonRemoveLocation) {
			if (!inspectionLocationReport.getInspectionFlag().equalsIgnoreCase("R")) {
				inspectionReport.add(inspectionLocationReport);
			}
		}
		return inspectionReport;
	}
}
