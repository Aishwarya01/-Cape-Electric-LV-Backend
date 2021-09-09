package com.capeelectric.service.impl;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
	
	/**
	 * @param SupplyCharacteristics
	 * addCharacteristics method to first formating the main and alternative_supply (NominalFrequency,NominalVoltage,LoopImpedance and NominalCurrent)
	 * then save SupplyCharacteristics model and its child model also will be saved
	 * @throws DecimalConversionException 
	*/	
	@Override
	public void addCharacteristics(SupplyCharacteristics supplyCharacteristics) throws SupplyCharacteristicsException, DecimalConversionException {
		if (supplyCharacteristics != null && supplyCharacteristics.getUserName() != null
				&& !supplyCharacteristics.getUserName().isEmpty() && supplyCharacteristics.getSiteId() != null
				&& supplyCharacteristics.getSiteId() != 0) {
			Optional<SupplyCharacteristics> siteId = supplyCharacteristicsRepository
					.findBySiteId(supplyCharacteristics.getSiteId());
			if ( !siteId.isPresent() || !siteId.get().getSiteId().equals(supplyCharacteristics.getSiteId())) {
				if (supplyCharacteristics.getLiveConductorAC() !=null && supplyCharacteristics.getMainNominalCurrent() != null
						&& supplyCharacteristics.getMainNominalFrequency() != null
						&& supplyCharacteristics.getMainNominalVoltage() != null
						&& supplyCharacteristics.getMainLoopImpedance() != null) {
					logger.info("decimal formating corrections started for Main supply");
					supplyCharacteristics.setMainNominalCurrent(DecimalConversion.convertToDecimal(supplyCharacteristics.getMainNominalCurrent(), new DecimalFormat("0.00")));
					supplyCharacteristics.setMainNominalFrequency(
							DecimalConversion.convertToDecimal(supplyCharacteristics.getMainNominalFrequency(), new DecimalFormat("0.00")));
					supplyCharacteristics.setMainNominalVoltage(
							DecimalConversion.convertToDecimal(supplyCharacteristics.getMainNominalVoltage(), new DecimalFormat("0.00")));
					supplyCharacteristics.setMainLoopImpedance(
							DecimalConversion.convertToDecimal(supplyCharacteristics.getMainLoopImpedance(), new DecimalFormat("0.000")));
					logger.info("decimal formating corrections ended for Main supply");
				}
				if (supplyCharacteristics.getSupplyParameters() != null) {
					List<SupplyParameters> supplyParameters = supplyCharacteristics.getSupplyParameters();
					for (SupplyParameters supplyParametersItr : supplyParameters) {
						if (supplyParametersItr.getaLLiveConductorAC() !=null && !supplyParametersItr.getaLLiveConductorAC().isEmpty() && supplyParametersItr.getNominalFrequency() != null
								&& supplyParametersItr.getNominalVoltage() != null
								&& supplyParametersItr.getFaultCurrent() != null
								&& supplyParametersItr.getLoopImpedance() != null) {
							logger.info("decimal formating corrections started for alternative supply");
							supplyParametersItr.setNominalFrequency(
									DecimalConversion.convertToDecimal(supplyParametersItr.getNominalFrequency(), new DecimalFormat("0.00")));
							supplyParametersItr.setNominalVoltage(
									DecimalConversion.convertToDecimal(supplyParametersItr.getNominalVoltage(), new DecimalFormat("0.00")));
							supplyParametersItr.setFaultCurrent(
									DecimalConversion.convertToDecimal(supplyParametersItr.getFaultCurrent(), new DecimalFormat("0.00")));
							supplyParametersItr.setLoopImpedance(
									DecimalConversion.convertToDecimal(supplyParametersItr.getLoopImpedance(), new DecimalFormat("0.000")));
							logger.info("decimal formating corrections ended for alternative supply");
						}
					}
				}
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
			return supplyCharacteristicsRepository.findByUserNameAndSiteId(userName, siteId);
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
	public SupplyCharacteristics sendComments(String userName, Integer siteId, String comments)
			throws SupplyCharacteristicsException {
		if (userName != null && siteId != null && comments != null) {
			Optional<SupplyCharacteristics> supplyCharacteristicsRepo = supplyCharacteristicsRepository.findBySiteId(siteId);
			if (supplyCharacteristicsRepo.isPresent() && supplyCharacteristicsRepo.get().getUserName().equalsIgnoreCase(userName)) {
				SupplyCharacteristics supplyCharacteristics = supplyCharacteristicsRepo.get();
				supplyCharacteristics.setUpdatedDate(LocalDateTime.now());
				supplyCharacteristics.setUpdatedBy(userName);
				supplyCharacteristicComment = new SupplyCharacteristicComment();
				supplyCharacteristicComment.setViewerDate(LocalDateTime.now());
				supplyCharacteristicComment.setViewerComment(comments);
				supplyCharacteristicComment.setSupplyCharacteristics(supplyCharacteristics);
				listOfComments.add(supplyCharacteristicComment);
				supplyCharacteristics.setSupplyCharacteristicComment(listOfComments);
				return supplyCharacteristicsRepository.save(supplyCharacteristics);
			} else {
				throw new SupplyCharacteristicsException("Given SiteId is Invalid");
			}
		} else {
			throw new SupplyCharacteristicsException("Invalid inputs");
		}
	}

	@Override
	public String replyComments(String inspectorUserName, Integer siteId, SupplyCharacteristicComment supplyCharacteristicComment)
			throws SupplyCharacteristicsException {
		if (inspectorUserName != null && siteId != null && supplyCharacteristicComment.getCommentsId() != null) {
			Optional<Site> siteRepo = siteRepository.findById(siteId);
			if (siteRepo.isPresent() && siteRepo.get().getSiteId().equals(siteId)) {
				Set<SitePersons> sitePersons = siteRepo.get().getSitePersons();
				for (SitePersons sitePersons2 : sitePersons) {
					Optional<SupplyCharacteristics> supplyCharacteristicsRepo = supplyCharacteristicsRepository.findBySiteId(siteId);
					if (supplyCharacteristicsRepo.isPresent() && supplyCharacteristicsRepo.get().getUserName()
							.equalsIgnoreCase(sitePersons2.getPersonInchargeEmail())) {
						SupplyCharacteristics supplyCharacteristics = supplyCharacteristicsRepo.get();
						supplyCharacteristics.setUpdatedDate(LocalDateTime.now());
						supplyCharacteristics.setUpdatedBy(inspectorUserName);
						List<SupplyCharacteristicComment> supplyCharacteristicCommentRepo = supplyCharacteristics
								.getSupplyCharacteristicComment();

						for (SupplyCharacteristicComment supplyCharacteristicCommentItr : supplyCharacteristicCommentRepo) {
							if (supplyCharacteristicCommentItr.getCommentsId()
									.equals(supplyCharacteristicComment.getCommentsId())) {
								supplyCharacteristicCommentItr.setInspectorDate(LocalDateTime.now());
								supplyCharacteristicCommentItr.setSupplyCharacteristics(supplyCharacteristics);
								supplyCharacteristicCommentItr
										.setInspectorComment(supplyCharacteristicComment.getInspectorComment());
								supplyCharacteristicCommentRepo.add(supplyCharacteristicCommentItr);
								supplyCharacteristics.setSupplyCharacteristicComment(supplyCharacteristicCommentRepo);
								supplyCharacteristicsRepository.save(supplyCharacteristics);
								return supplyCharacteristics.getUserName();
							}
						}

					}
				}

			} else {
				throw new SupplyCharacteristicsException("Invalid Site-Id");
			}

		} else {
			throw new SupplyCharacteristicsException("Invalid inputs");
		}
		return null;
	}
}
