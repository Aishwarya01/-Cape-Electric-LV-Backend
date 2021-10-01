package com.capeelectric.service.impl;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.capeelectric.exception.CompanyDetailsException;
import com.capeelectric.model.Register;
import com.capeelectric.model.Site;
import com.capeelectric.model.SitePersons;
import com.capeelectric.repository.RegistrationRepository;
import com.capeelectric.repository.SitePersonsRepository;
import com.capeelectric.repository.SiteRepository;
import com.capeelectric.service.SiteService;
import com.capeelectric.util.UserFullName;

@Service
public class SiteServiceImpl implements SiteService {

	@Autowired
	private SiteRepository siteRepository;

	@Autowired
	private SitePersonsRepository sitePersonsRepository;

	@Autowired
	private UserFullName userName;
	
	@Autowired
	private RegistrationRepository registrationRepository;

	/*
	 * @param Site addSite method to c comparing department client_name, comparing
	 * department_name,checking site_name
	 */
	@Override
	public void addSite(Site site) throws CompanyDetailsException {
		int count = 0;

		if (site.getUserName() != null && site.getSite() != null) {
			Optional<Site> siteRepo = siteRepository.findByUserNameAndSite(site.getUserName(), site.getSite());

			if (!siteRepo.isPresent() || !siteRepo.get().getSite().equalsIgnoreCase(site.getSite())) {
				site.setSiteCd(site.getSite().substring(0, 3).concat("_0") + (count + 1));
				site.setCreatedDate(LocalDateTime.now());
				site.setUpdatedDate(LocalDateTime.now());
				site.setCreatedBy(userName.findByUserName(site.getUserName()));
				site.setUpdatedBy(userName.findByUserName(site.getUserName()));
				boolean email = checkSitePersonEmail(site.getSite(), site.getSitePersons());
				if (email) {
					reduceLicence(site.getUserName());
					siteRepository.save(site);
				} else {
					throw new CompanyDetailsException("PersonInchargEmail already present");
				}
			} else {
				throw new CompanyDetailsException(site.getSite() + ": site already present");
			}

		} else {
			throw new CompanyDetailsException("Invalid Inputs");
		}
	}

	/*
	 * @param Site updateSite method to comparing department_ClientName,
	 * department_name comparing, then comparing site
	 */
	@Override
	public void updateSite(Site site) throws CompanyDetailsException {
		int count = 0;

		if (site.getUserName() != null && site.getSite() != null) {
			Optional<Site> siteRepo = siteRepository.findByUserNameAndSite(site.getUserName(), site.getSite());
			Set<SitePersons> sitePersons = deleteSitePersonDetails(site.getSitePersons());
			if (!sitePersons.isEmpty()) {
				site.getSitePersons().removeAll(sitePersons);
			}
			if (siteRepo.isPresent() && siteRepo.get().getSite().equalsIgnoreCase(site.getSite())
					&& siteRepo.get().getSiteId().equals(site.getSiteId())) {
				site.setSiteCd(site.getSite().substring(0, 3).concat("_0") + (count + 1));
				site.setUpdatedDate(LocalDateTime.now());
				site.setUpdatedBy(userName.findByUserName(site.getUserName()));
				boolean email = checkSitePersonEmail(site.getSite(), site.getSitePersons());
				if (email) {
					siteRepository.save(site);
				} else {
					throw new CompanyDetailsException("PersonInchargEmail already present");
				}
			} else {
				throw new CompanyDetailsException(site.getSite() + " site not present");
			}
		} else {
			throw new CompanyDetailsException("Invalid Inputs");
		}
	}

	/*
	 * @param siteId deleteSite method to comparing siteId in site_table and @param
	 * siteId is true then site_object will be delete
	 */
	@Override
	public void deleteSite(Integer siteId) throws CompanyDetailsException, EmptyResultDataAccessException {
		if (siteId != null && siteId != 0) {
			Optional<Site> site = siteRepository.findById(siteId);

			if (site.isPresent() && site != null && site.get().getSiteId().equals(siteId)) {

				siteRepository.deleteById(siteId);
			} else {
				throw new CompanyDetailsException(siteId + " : this siteId not present");
			}

		} else {
			throw new CompanyDetailsException("Invalid Inputs");
		}

	}

	/*
	 * @param clientName,departmentName retriveSite method to retrieving site from
	 * DB
	 */
	@Override
	public List<Site> retriveSite(String userName) throws CompanyDetailsException {
		if (userName != null) {
			return siteRepository.findByUserName(userName);
		} else {
			throw new CompanyDetailsException("Invalid Inputs");
		}
	}

	/*
	 * @param sitePersons checkSitePersonEmail method to finding duplicate
	 * personInchargeMail entry
	 */
	private boolean checkSitePersonEmail(String siteName, Set<SitePersons> sitePersons) throws CompanyDetailsException {
		boolean emailAvailable = true;
		for (SitePersons sitePersonsItr : sitePersons) {
			sitePersonsItr.setSiteName(siteName);
			Optional<SitePersons> inchargeEmail = sitePersonsRepository.findBySiteNameAndPersonInchargeEmail(siteName,
					sitePersonsItr.getPersonInchargeEmail());

			if (inchargeEmail.isPresent() && inchargeEmail != null) {
				if (inchargeEmail.get().getPersonInchargeEmail()
						.equalsIgnoreCase(sitePersonsItr.getPersonInchargeEmail())
						&& inchargeEmail.get().getPersonId().equals(sitePersonsItr.getPersonId())) {
				} else {
					emailAvailable = false;
				}
			}
		}
		return emailAvailable;
	}

	/**
	 * 
	 * @param sitePersons
	 */
	private Set<SitePersons> deleteSitePersonDetails(Set<SitePersons> sitePersons) {
		Set<SitePersons> sitePersonSet = new HashSet<SitePersons>();
		for (SitePersons sitePersonsItr : sitePersons) {
			if (sitePersonsItr != null && !sitePersonsItr.getInActive()) {
				sitePersonsRepository.deleteById(sitePersonsItr.getPersonId());
				sitePersonSet.add(sitePersonsItr);
			}
		}
		return sitePersonSet;
	}

	/*
	 * @param companyName, departmentName, siteName
	 * retrieveSiteByName method to retrive based on companyName, departmentName, siteName
	 * DB
	 */
	@Override
	public Site retrieveSiteByName(String companyName, String departmentName, String siteName)
			throws CompanyDetailsException {
		if(null != companyName && null != departmentName && null != siteName) {
			return siteRepository.findByCompanyNameAndDepartmentNameAndSite(companyName, departmentName, siteName);
		} else {
			throw new CompanyDetailsException("Company Name "
					+ companyName +", " +"Department Name "+ departmentName + ", "
					+ " Site Name " + siteName + " is not available");
		}
	}

	/*
	 * @param inspectorUserName
	 * reduceLicence method to one license decreased if inspector license except zero
	 * DB
	 */
	private void reduceLicence(String inspectorUserName) throws CompanyDetailsException {
		if (inspectorUserName != null) {
			Optional<Register> inspectorRepo = registrationRepository.findByUsername(inspectorUserName);
			if (inspectorRepo.isPresent()) {
				Register inspector = inspectorRepo.get();
				if (!inspector.getNoOfLicence().equals("0")) {
					inspector.setNoOfLicence(String.valueOf(Integer.parseInt(inspector.getNoOfLicence()) - 1));
					inspector.setUpdatedBy(userName.findByUserName(inspectorUserName));
					inspector.setUpdatedDate(LocalDateTime.now());
					registrationRepository.save(inspector);
				} else {
					throw new CompanyDetailsException(inspectorUserName + " Given Inspector doesn't have Licence");
				}
			} else {
				throw new CompanyDetailsException(inspectorUserName + " Given Inspector doesn't exist");
			}

		} else {
			throw new CompanyDetailsException("Invalid Inspector Name");
		}

	}
}
