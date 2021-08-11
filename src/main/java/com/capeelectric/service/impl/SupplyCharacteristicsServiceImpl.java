package com.capeelectric.service.impl;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.StringTokenizer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capeelectric.exception.SupplyCharacteristicsException;
import com.capeelectric.model.SupplyCharacteristics;
import com.capeelectric.model.SupplyParameters;
import com.capeelectric.repository.SupplyCharacteristicsRepository;
import com.capeelectric.service.SupplyCharacteristicsService;

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

	/**
	 * @param SupplyCharacteristics
	 * addCharacteristics method to first formating the main and alternative_supply (NominalFrequency,NominalVoltage,LoopImpedance and NominalCurrent)
	 * then save SupplyCharacteristics model and its child model also will be saved
	*/	
	@Override
	public void addCharacteristics(SupplyCharacteristics supplyCharacteristics) throws SupplyCharacteristicsException {
		if (supplyCharacteristics != null) {
			Optional<SupplyCharacteristics> siteId = supplyCharacteristicsRepository
					.findBySiteId(supplyCharacteristics.getSiteId());
			if ( !siteId.isPresent() || !siteId.get().getSiteId().equals(supplyCharacteristics.getSiteId())) {
				if (supplyCharacteristics.getLiveConductorAC() !=null && supplyCharacteristics.getMainNominalCurrent() != null
						&& supplyCharacteristics.getMainNominalFrequency() != null
						&& supplyCharacteristics.getMainNominalVoltage() != null
						&& supplyCharacteristics.getMainLoopImpedance() != null) {
					logger.info("decimal formating corrections started for Main supply");
					supplyCharacteristics.setMainNominalCurrent(
							doubleValue(supplyCharacteristics.getMainNominalCurrent(), new DecimalFormat("0.00")));
					supplyCharacteristics.setMainNominalFrequency(
							doubleValue(supplyCharacteristics.getMainNominalFrequency(), new DecimalFormat("0.00")));
					supplyCharacteristics.setMainNominalVoltage(
							doubleValue(supplyCharacteristics.getMainNominalVoltage(), new DecimalFormat("0.00")));
					supplyCharacteristics.setMainLoopImpedance(
							doubleValue(supplyCharacteristics.getMainLoopImpedance(), new DecimalFormat("0.000")));
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
									doubleValue(supplyParametersItr.getNominalFrequency(), new DecimalFormat("0.00")));
							supplyParametersItr.setNominalVoltage(
									doubleValue(supplyParametersItr.getNominalVoltage(), new DecimalFormat("0.00")));
							supplyParametersItr.setFaultCurrent(
									doubleValue(supplyParametersItr.getFaultCurrent(), new DecimalFormat("0.00")));
							supplyParametersItr.setLoopImpedance(
									doubleValue(supplyParametersItr.getLoopImpedance(), new DecimalFormat("0.000")));
							logger.info("decimal formating corrections ended for alternative supply");
						}
					}
				}
				supplyCharacteristics.setCreatedDate(LocalDateTime.now());
				supplyCharacteristicsRepository.save(supplyCharacteristics);
			} else {
				throw new SupplyCharacteristicsException("siteId already present");
			}
		}

		else {
			throw new SupplyCharacteristicsException("Invalid inputs");
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
			throw new SupplyCharacteristicsException("Invalid inputs");
		}
	}

	/** 
	 * @parm
	 * doubleValue method to  Formating the decimal values for main and alternative supply 
	 * @return String value
	 * 
	*/
	private String doubleValue(String string, DecimalFormat decimalFormat) {
		String nominalValues = "";
		String decimalValue = "NA";
		StringTokenizer stringTokenizer = new StringTokenizer(string, ",");

		while (stringTokenizer.hasMoreElements()) {
			String token = stringTokenizer.nextToken();
			if (token.equalsIgnoreCase("NA")) {
				nominalValues = nominalValues.concat("NA").concat(",");
			} else {
				decimalValue = decimalFormat.format(Double.parseDouble(token));
				nominalValues = nominalValues.concat(decimalValue).concat(",");
			}

		}
		return nominalValues.substring(0, nominalValues.length() - 1);
	}

}
