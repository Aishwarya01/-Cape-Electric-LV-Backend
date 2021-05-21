package com.capeelectric.service.impl;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.List;
import java.util.StringTokenizer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capeelectric.exception.SupplyCharacteristicsException;
import com.capeelectric.model.SupplyCharacteristics;
import com.capeelectric.model.SupplyParameters;
import com.capeelectric.repository.SupplyCharacteristicsRepository;
import com.capeelectric.service.SupplyCharacteristicsService;

@Service
public class SupplyCharacteristicsServiceImpl implements SupplyCharacteristicsService {

	@Autowired
	private SupplyCharacteristicsRepository supplyCharacteristicsRepository;

	DecimalFormat decimalFormat;

	@Override
	public void addCharacteristics(SupplyCharacteristics supplyCharacteristics) throws SupplyCharacteristicsException {
		if (supplyCharacteristics != null) {
			
			if (supplyCharacteristics.getMainNominalCurrent() != null) {
				decimalFormat = new DecimalFormat("0.00");
				supplyCharacteristics.setMainNominalCurrent(
						doubleValue(supplyCharacteristics.getMainNominalCurrent(), decimalFormat));
			}
			if (supplyCharacteristics.getMainNominalFrequency() != null) {
				decimalFormat = new DecimalFormat("0.00");
				supplyCharacteristics.setMainNominalFrequency(
						doubleValue(supplyCharacteristics.getMainNominalFrequency(), decimalFormat));
			}
			if (supplyCharacteristics.getMainNominalVoltage() != null) {
				decimalFormat = new DecimalFormat("0.00");
				supplyCharacteristics.setMainNominalVoltage(
						doubleValue(supplyCharacteristics.getMainNominalVoltage(), decimalFormat));
			}
			if (supplyCharacteristics.getMainLoopImpedance() != null) {
				decimalFormat = new DecimalFormat("0.000");
				supplyCharacteristics
						.setMainLoopImpedance(doubleValue(supplyCharacteristics.getMainLoopImpedance(), decimalFormat));
			}
			if (supplyCharacteristics.getSupplyParameters() != null) {
				List<SupplyParameters> supplyParameters = supplyCharacteristics.getSupplyParameters();
				for (SupplyParameters supplyParametersItr : supplyParameters) {
					if (supplyParametersItr.getNominalFrequency() != null) {
						decimalFormat = new DecimalFormat("0.00");
						supplyParametersItr.setNominalFrequency(
								doubleValue(supplyParametersItr.getNominalFrequency(), decimalFormat));
					}
					if (supplyParametersItr.getNominalVoltage() != null) {
						decimalFormat = new DecimalFormat("0.00");
						supplyParametersItr
								.setNominalVoltage(doubleValue(supplyParametersItr.getNominalVoltage(), decimalFormat));
					}
					if (supplyParametersItr.getFaultCurrent() != null) {
						decimalFormat = new DecimalFormat("0.00");
						supplyParametersItr
								.setFaultCurrent(doubleValue(supplyParametersItr.getFaultCurrent(), decimalFormat));
					}
					if (supplyParametersItr.getLoopImpedance() != null) {
						decimalFormat = new DecimalFormat("0.000");
						supplyParametersItr
								.setLoopImpedance(doubleValue(supplyParametersItr.getLoopImpedance(), decimalFormat));
					}

				}
			}
			supplyCharacteristics.setCreatedDate(LocalDateTime.now());
			supplyCharacteristicsRepository.save(supplyCharacteristics);
		}

		else {
			throw new SupplyCharacteristicsException("Invalid inputs");
		}
	}

	@Override
	public List<SupplyCharacteristics> retrieveCharacteristics(String userName, Integer siteId)
			throws SupplyCharacteristicsException {

		if (userName != null && !userName.isEmpty() && siteId != null) {
			return supplyCharacteristicsRepository.findByUserNameAndSiteId(userName, siteId);
		} else {
			throw new SupplyCharacteristicsException("Invalid inputs");
		}
	}

	private String doubleValue(String string, DecimalFormat decimalFormat) {
		String nominalValues = "";

		StringTokenizer stringTokenizer = new StringTokenizer(string, ",");

		while (stringTokenizer.hasMoreElements()) {
			String token = stringTokenizer.nextToken();
			String decimalValue = decimalFormat.format(Double.parseDouble(token));
			nominalValues = nominalValues.concat(decimalValue).concat(",");
		}
		return nominalValues.substring(0, nominalValues.length() - 1);
	}

}
