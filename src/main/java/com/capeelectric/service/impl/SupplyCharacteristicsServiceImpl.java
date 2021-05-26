package com.capeelectric.service.impl;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
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

	@Override
	public void addCharacteristics(SupplyCharacteristics supplyCharacteristics) throws SupplyCharacteristicsException {
		if (supplyCharacteristics != null) {
			Optional<SupplyCharacteristics> siteId = supplyCharacteristicsRepository
					.findBySiteId(supplyCharacteristics.getSiteId());
			if ( !siteId.isPresent() || !siteId.get().getSiteId().equals(supplyCharacteristics.getSiteId())) {
				if (supplyCharacteristics.getMainNominalCurrent() != null
						&& supplyCharacteristics.getMainNominalFrequency() != null
						&& supplyCharacteristics.getMainNominalVoltage() != null
						&& supplyCharacteristics.getMainLoopImpedance() != null) {
					supplyCharacteristics.setMainNominalCurrent(
							doubleValue(supplyCharacteristics.getMainNominalCurrent(), new DecimalFormat("0.00")));
					supplyCharacteristics.setMainNominalFrequency(
							doubleValue(supplyCharacteristics.getMainNominalFrequency(), new DecimalFormat("0.00")));
					supplyCharacteristics.setMainNominalVoltage(
							doubleValue(supplyCharacteristics.getMainNominalVoltage(), new DecimalFormat("0.00")));
					supplyCharacteristics.setMainLoopImpedance(
							doubleValue(supplyCharacteristics.getMainLoopImpedance(), new DecimalFormat("0.000")));
				}
				if (supplyCharacteristics.getSupplyParameters() != null) {
					List<SupplyParameters> supplyParameters = supplyCharacteristics.getSupplyParameters();
					for (SupplyParameters supplyParametersItr : supplyParameters) {
						if (supplyParametersItr.getNominalFrequency() != null
								&& supplyParametersItr.getNominalVoltage() != null
								&& supplyParametersItr.getFaultCurrent() != null
								&& supplyParametersItr.getLoopImpedance() != null) {
							supplyParametersItr.setNominalFrequency(
									doubleValue(supplyParametersItr.getNominalFrequency(), new DecimalFormat("0.00")));
							supplyParametersItr.setNominalVoltage(
									doubleValue(supplyParametersItr.getNominalVoltage(), new DecimalFormat("0.00")));
							supplyParametersItr.setFaultCurrent(
									doubleValue(supplyParametersItr.getFaultCurrent(), new DecimalFormat("0.00")));
							supplyParametersItr.setLoopImpedance(
									doubleValue(supplyParametersItr.getLoopImpedance(), new DecimalFormat("0.000")));
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
