package com.capeelectric.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capeelectric.exception.DecimalConversionException;
import com.capeelectric.exception.SupplyCharacteristicsException;
import com.capeelectric.model.Site;
import com.capeelectric.model.SitePersons;
import com.capeelectric.model.SupplyCharacteristicComment;
import com.capeelectric.model.SupplyCharacteristics;
import com.capeelectric.model.SupplyParameters;
import com.capeelectric.repository.SiteRepository;
import com.capeelectric.repository.SupplyCharacteristicsRepository;
import com.capeelectric.service.SupplyCharacteristicsService;
import com.capeelectric.util.DecimalConversion;
import com.capeelectric.util.UserFullName;

/**
 **
 * This SupplyCharacteristicsServiceImpl service class doing save and retrieve operation related to SupplyCharacteristics
 * @author capeelectricsoftware
 *
 */
@Service
public class SupplyCharacteristicsServiceImpl implements SupplyCharacteristicsService {
	
	private static final Logger logger = LoggerFactory.getLogger(SupplyCharacteristicsServiceImpl.class);

	@Autowired
	private SupplyCharacteristicsRepository supplyCharacteristicsRepository;
	
	@Autowired
	private SiteRepository siteRepository;

	@Autowired
	private UserFullName userFullName;
	
	private SupplyCharacteristicComment supplyCharacteristicComment;

	private List<SupplyCharacteristicComment> listOfComments = new ArrayList<SupplyCharacteristicComment>();
	
	private String viewerName;
	
	/**
	 * @param SupplyCharacteristics
	 * addCharacteristics method to first formating the main and alternative_supply (NominalFrequency,NominalVoltage,LoopImpedance and NominalCurrent)
	 * then save SupplyCharacteristics model and its child model also will be saved
	 * @throws DecimalConversionException 
	*/	
	@Override
	public void addCharacteristics(SupplyCharacteristics supplyCharacteristics)
			throws SupplyCharacteristicsException, DecimalConversionException {
		if (supplyCharacteristics != null && supplyCharacteristics.getUserName() != null
				&& !supplyCharacteristics.getUserName().isEmpty() && supplyCharacteristics.getSiteId() != null
				&& supplyCharacteristics.getSiteId() != 0) {
			Optional<SupplyCharacteristics> siteId = supplyCharacteristicsRepository
					.findBySiteId(supplyCharacteristics.getSiteId());
			if (!siteId.isPresent() || !siteId.get().getSiteId().equals(supplyCharacteristics.getSiteId())) {
				if (supplyCharacteristics.getLiveConductorAC() != null
						&& supplyCharacteristics.getMainNominalCurrent() != null
						&& supplyCharacteristics.getMainNominalFrequency() != null
						&& supplyCharacteristics.getMainNominalVoltage() != null
						&& supplyCharacteristics.getMainLoopImpedance() != null) {
					logger.info("decimal formating corrections started for Main supply");
					supplyCharacteristics.setMainNominalCurrent(DecimalConversion.convertToDecimal(
							supplyCharacteristics.getMainNominalCurrent(), "Supply_MainNominalCurrent"));
					supplyCharacteristics.setMainNominalFrequency(DecimalConversion.convertToDecimal(
							supplyCharacteristics.getMainNominalFrequency(), "Supply_MainNominalFrequency"));
					supplyCharacteristics.setMainNominalVoltage(DecimalConversion.convertToDecimal(
							supplyCharacteristics.getMainNominalVoltage(), "Supply_MainNominalVoltage"));
					supplyCharacteristics.setMainLoopImpedance(DecimalConversion.convertToDecimal(
							supplyCharacteristics.getMainLoopImpedance(), "Supply_MainLoopImpedance"));
					logger.info("decimal formating corrections ended for Main supply");
				}
				if (supplyCharacteristics.getSupplyParameters() != null) {
					List<SupplyParameters> supplyParameters = supplyCharacteristics.getSupplyParameters();
					for (SupplyParameters supplyParametersItr : supplyParameters) {
						if (supplyParametersItr.getaLLiveConductorAC() != null
								&& !supplyParametersItr.getaLLiveConductorAC().isEmpty()
								&& supplyParametersItr.getNominalFrequency() != null
								&& supplyParametersItr.getNominalVoltage() != null
								&& supplyParametersItr.getFaultCurrent() != null
								&& supplyParametersItr.getLoopImpedance() != null) {
							logger.info("decimal formating corrections started for alternative supply");
							supplyParametersItr.setNominalFrequency(DecimalConversion.convertToDecimal(
									supplyParametersItr.getNominalFrequency(), "Supply_NominalFrequency"));
							supplyParametersItr.setNominalVoltage(DecimalConversion.convertToDecimal(
									supplyParametersItr.getNominalVoltage(), "Supply_NominalVoltage"));
							supplyParametersItr.setFaultCurrent(DecimalConversion
									.convertToDecimal(supplyParametersItr.getFaultCurrent(), "Supply_FaultCurrent"));
							supplyParametersItr.setLoopImpedance(DecimalConversion
									.convertToDecimal(supplyParametersItr.getLoopImpedance(), "Supply_LoopImpedance"));
							logger.info("decimal formating corrections ended for alternative supply");
						}
					}
				}
				supplyCharacteristicComment = new SupplyCharacteristicComment();
				supplyCharacteristicComment.setInspectorFlag("0");
				supplyCharacteristicComment.setViewerFlag("0");
				supplyCharacteristicComment.setNoOfComment(1);
				supplyCharacteristicComment.setSupplyCharacteristics(supplyCharacteristics);
				listOfComments.add(supplyCharacteristicComment);
				supplyCharacteristics.setSupplyCharacteristicComment(listOfComments);
				supplyCharacteristics.setCreatedDate(LocalDateTime.now());
				supplyCharacteristics.setUpdatedDate(LocalDateTime.now());
				supplyCharacteristics.setCreatedBy(userFullName.getFullName(supplyCharacteristics.getUserName()));
				supplyCharacteristics.setUpdatedBy(userFullName.getFullName(supplyCharacteristics.getUserName()));

				supplyCharacteristicsRepository.save(supplyCharacteristics);
			} else {
				throw new SupplyCharacteristicsException("Site-Id Already Available");
			}
		}

		else {
			throw new SupplyCharacteristicsException("Invalid Inputs");
		}
	}
	
	/**
	 * @param userName,siteId
	 * retrieveCharacteristics method to retrieve list of supplyCharacteristic objects based on userName and siteId
	 * @return List<SupplyCharacteristics>
	 * 	
	*/
	@Override 
	public List<SupplyCharacteristics> retrieveCharacteristics(String userName, Integer siteId)
			throws SupplyCharacteristicsException {

		if (userName != null && !userName.isEmpty() && siteId != null) {
			List<SupplyCharacteristics> supplyCharacteristicsRepo = supplyCharacteristicsRepository
					.findByUserNameAndSiteId(userName, siteId);
			if (supplyCharacteristicsRepo != null) {
				for (SupplyCharacteristics supplyCharacteristics : supplyCharacteristicsRepo) {
					sortingDateTime(supplyCharacteristics.getSupplyCharacteristicComment());
				}
				return supplyCharacteristicsRepo;
			} else {
				throw new SupplyCharacteristicsException("Given UserName & Site doesn't exist Inspection");
			}
		} else {
			throw new SupplyCharacteristicsException("Invalid Inputs");
		}
	}

	
	
	/**
	 * @reportId,siteId must required
	 * @param SupplyCharacteristics Object
	 * updateCharacteristics method to finding the given SupplyCharacteristicsId is available or not in DB,
	 * if available only allowed for updating 
	 * 
	*/
	@Override
	public void updateCharacteristics(SupplyCharacteristics supplyCharacteristics) throws SupplyCharacteristicsException {
		if (supplyCharacteristics != null && supplyCharacteristics.getSupplyCharacteristicsId() != null && supplyCharacteristics.getSupplyCharacteristicsId() != 0
				&& supplyCharacteristics.getSiteId() != null && supplyCharacteristics.getSiteId() != 0) {
			Optional<SupplyCharacteristics> supplyCharacteristicsRepo = supplyCharacteristicsRepository
					.findById(supplyCharacteristics.getSupplyCharacteristicsId());
			if (supplyCharacteristicsRepo.isPresent()
					&& supplyCharacteristicsRepo.get().getSiteId().equals(supplyCharacteristics.getSiteId())) {
				supplyCharacteristics.setUpdatedDate(LocalDateTime.now());
				supplyCharacteristics.setUpdatedBy(userFullName.getFullName(supplyCharacteristics.getUserName()));
				supplyCharacteristicsRepository.save(supplyCharacteristics);
			} else {
				throw new SupplyCharacteristicsException("Given SiteId and ReportId is Invalid");
			}

		} else {
			throw new SupplyCharacteristicsException("Invalid inputs");
		}
		
	}
	
	@Override
	public void sendComments(String userName, Integer siteId,
			SupplyCharacteristicComment supplyCharacteristicComment) throws SupplyCharacteristicsException {

		SupplyCharacteristics supplyCharacteristics = verifyCommentsInfo(userName, siteId,
				supplyCharacteristicComment, "APPROVE");
		if (supplyCharacteristics != null) {
			supplyCharacteristicsRepository.save(supplyCharacteristics);
		} else {
			throw new SupplyCharacteristicsException(
					"SupplyCharacteristics-Information doesn't exist for given Site-Id");
		}
	}

	@Override
	public String replyComments(String inspectorUserName, Integer siteId,
			SupplyCharacteristicComment supplyCharacteristicComment) throws SupplyCharacteristicsException {

		SupplyCharacteristics supplyCharacteristics = verifyCommentsInfo(inspectorUserName, siteId,
				supplyCharacteristicComment, "APPROVE");
		if (supplyCharacteristics != null) {
			supplyCharacteristicsRepository.save(supplyCharacteristics);
			return viewerName;
		} else {
			throw new SupplyCharacteristicsException(
					"SupplyCharacteristics-Information doesn't exist for given Site-Id");
		}
	}
	
	@Override
	public void approveComments(String userName, Integer siteId,
			SupplyCharacteristicComment supplyCharacteristicComment) throws SupplyCharacteristicsException {

		SupplyCharacteristics supplyCharacteristics = verifyCommentsInfo(userName, siteId, supplyCharacteristicComment,
				"APPROVE");
		if (supplyCharacteristics != null) {
			supplyCharacteristicsRepository.save(supplyCharacteristics);
		} else {
			throw new SupplyCharacteristicsException(
					"SupplyCharacteristics-Information doesn't exist for given Site-Id");
		}

	}
	
	private SupplyCharacteristics verifyCommentsInfo(String userName, Integer siteId,
			SupplyCharacteristicComment supplyCharacteristicComment, String process)
			throws SupplyCharacteristicsException {

		Boolean flagInspectionComment = true;
		if (userName != null && siteId != null && supplyCharacteristicComment != null) {
			Optional<Site> siteRepo = siteRepository.findById(siteId);
			if (siteRepo.isPresent() && siteRepo.get().getSiteId().equals(siteId)) {
				Optional<SupplyCharacteristics> supplyCharacteristicsRepo = supplyCharacteristicsRepository
						.findBySiteId(siteId);

				if (supplyCharacteristicsRepo.isPresent() && supplyCharacteristicsRepo.get() != null
						&& checkInspectorViewer(userName, process, siteRepo, supplyCharacteristicsRepo)) {
					SupplyCharacteristics supplyCharacteristics = supplyCharacteristicsRepo.get();
					supplyCharacteristics.setUpdatedDate(LocalDateTime.now());
					supplyCharacteristics.setUpdatedBy(userName);
					List<SupplyCharacteristicComment> supplyCharacteristicCommentRepo = supplyCharacteristics
							.getSupplyCharacteristicComment();

					for (SupplyCharacteristicComment supplyCharacteristicCommentItr : supplyCharacteristicCommentRepo) {
						if (supplyCharacteristicCommentItr.getCommentsId()
								.equals(supplyCharacteristicComment.getCommentsId())) {
							flagInspectionComment = false;

							supplyCharacteristicCommentItr.setSupplyCharacteristics(supplyCharacteristics);

							if (process.equalsIgnoreCase("SEND")) {
								supplyCharacteristicCommentItr.setViewerDate(LocalDateTime.now());
								supplyCharacteristicCommentItr
										.setViewerComment(supplyCharacteristicComment.getViewerComment());
								supplyCharacteristicCommentItr.setViewerFlag("1");
								supplyCharacteristicCommentRepo.add(supplyCharacteristicCommentItr);
								supplyCharacteristics.setSupplyCharacteristicComment(supplyCharacteristicCommentRepo);
								return supplyCharacteristics;
							}
							if (process.equalsIgnoreCase("REPLY")) {
								supplyCharacteristicCommentItr.setInspectorDate(LocalDateTime.now());
								supplyCharacteristicCommentItr
										.setInspectorComment(supplyCharacteristicComment.getInspectorComment());
								supplyCharacteristicCommentItr.setInspectorFlag("1");
								supplyCharacteristicCommentRepo.add(supplyCharacteristicCommentItr);
								supplyCharacteristics.setSupplyCharacteristicComment(supplyCharacteristicCommentRepo);
								return supplyCharacteristics;
							}
							if (process.equalsIgnoreCase("APPROVE")) {
								supplyCharacteristicCommentItr.setViewerDate(LocalDateTime.now());
								supplyCharacteristicCommentItr
										.setApproveOrReject(supplyCharacteristicComment.getApproveOrReject());
								supplyCharacteristicCommentRepo.add(supplyCharacteristicCommentItr);
								supplyCharacteristics.setSupplyCharacteristicComment(supplyCharacteristicCommentRepo);
								return supplyCharacteristics;
							}
						}
					}
					if (flagInspectionComment) {

						if (process.equalsIgnoreCase("SEND")) {
							supplyCharacteristicComment.setNoOfComment(
									checkNoOfComments(supplyCharacteristics.getSupplyCharacteristicComment()));
							supplyCharacteristicComment.setSupplyCharacteristics(supplyCharacteristics);
							supplyCharacteristicComment.setViewerDate(LocalDateTime.now());
							supplyCharacteristicComment.setViewerFlag("1");
							supplyCharacteristicComment.setInspectorFlag("0");
							supplyCharacteristicCommentRepo.add(supplyCharacteristicComment);
							supplyCharacteristics.setSupplyCharacteristicComment(supplyCharacteristicCommentRepo);
							return supplyCharacteristics;
						}
					}
				} else {
					throw new SupplyCharacteristicsException("Given username not have access for comments");
				}

			} else {
				throw new SupplyCharacteristicsException("Siteinformation doesn't exist, try with different Site-Id");
			}

		} else {
			throw new SupplyCharacteristicsException("Invalid inputs");
		}
		return null;
	}
	
	private void sortingDateTime(List<SupplyCharacteristicComment> listOfComments) {
		Collections.sort(listOfComments, (o1, o2) -> o1.getViewerDate().compareTo(o2.getViewerDate()));
	}
	
	private Integer checkNoOfComments(List<SupplyCharacteristicComment> listOfComments) {
		Integer maxNum = 0;
		String approveRejectedFlag = "";
		for (SupplyCharacteristicComment supplyCharacteristicCommentItr : listOfComments) {
			if (supplyCharacteristicCommentItr != null && maxNum <= supplyCharacteristicCommentItr.getNoOfComment()) {
				maxNum = supplyCharacteristicCommentItr.getNoOfComment();
				approveRejectedFlag = supplyCharacteristicCommentItr.getApproveOrReject();
			}
		}
		if (approveRejectedFlag != null && approveRejectedFlag.equalsIgnoreCase("APPROVED")) {
			return maxNum + 1;
		} else {
			return maxNum + 1;
		}
	}

	private Boolean checkInspectorViewer(String userName, String process, Optional<Site> siteRepo,
			Optional<SupplyCharacteristics> supplyCharacteristicsRepo) throws SupplyCharacteristicsException {
		Boolean flag = false;
		if (process.equalsIgnoreCase("REPLY")) {
			if (siteRepo.get().getUserName().equalsIgnoreCase(userName)
					&& supplyCharacteristicsRepo.get().getUserName() != null
					&& siteRepo.get().getUserName().equalsIgnoreCase(supplyCharacteristicsRepo.get().getUserName())) {
				Set<SitePersons> sitePersons = siteRepo.get().getSitePersons();
				for (SitePersons sitePersonsItr : sitePersons) {
					if (sitePersonsItr != null && sitePersonsItr.getPersonInchargeEmail() != null) {
						viewerName = sitePersonsItr.getPersonInchargeEmail();
						return flag = true;
					}
				}
			} else {
				throw new SupplyCharacteristicsException("Given userName not allowing for " + process + " comment");
			}

		} else if (process.equalsIgnoreCase("SEND") || process.equalsIgnoreCase("APPROVE")) {

			Set<SitePersons> sitePersons = siteRepo.get().getSitePersons();
			for (SitePersons sitePersonsItr : sitePersons) {
				if (sitePersonsItr != null && sitePersonsItr.getPersonInchargeEmail() != null
						&& sitePersonsItr.getPersonInchargeEmail().equalsIgnoreCase(userName)) {
					return flag = true;
				} else {
					throw new SupplyCharacteristicsException("Given userName not allowing for " + process + " comment");
				}
			}
		}
		return flag;
	}
}
