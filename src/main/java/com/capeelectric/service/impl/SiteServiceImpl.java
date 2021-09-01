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
import com.capeelectric.model.Site;
import com.capeelectric.model.SitePersons;
import com.capeelectric.model.User;
import com.capeelectric.repository.SitePersonsRepository;
import com.capeelectric.repository.SiteRepository;
import com.capeelectric.repository.UserRepository;
import com.capeelectric.service.SiteService;

@Service
public class SiteServiceImpl implements SiteService {

	@Autowired
	private SiteRepository siteRepository;

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private SitePersonsRepository sitePersonsRepository;

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
				site.setCreatedBy(generateFullName(site.getUserName()));
				site.setUpdatedBy(generateFullName(site.getUserName()));
				boolean email = checkSitePersonEmail(site.getSitePersons());
				if (email) {
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
	 * @param Site 
	 * updateSite method to comparing department_ClientName,
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
			if (siteRepo != null && siteRepo.get().getSite().equalsIgnoreCase(site.getSite())
					&& siteRepo.get().getSiteId().equals(site.getSiteId())) {
				site.setSiteCd(site.getSite().substring(0, 3).concat("_0") + (count + 1));
				site.setUpdatedDate(LocalDateTime.now());
				site.setUpdatedBy(generateFullName(site.getUserName()));
				boolean email = checkSitePersonEmail(site.getSitePersons());
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
	 * @param clientName,departmentName
	 * retriveSite method to retrieving site from DB
	 */
	@Override
	public List<Site> retriveSite(String userName) throws CompanyDetailsException {
		if (userName != null) {
			return siteRepository.findByUserName(userName);
		} else {
			throw new CompanyDetailsException("Invalid Inputs");
		}
	}

	private String generateFullName(String userName) {
		Optional<User> user = userRepository.findByUsername(userName);
		if (user.isPresent() && user.get() != null)
			return user.get().getFirstname() + " " + user.get().getLastname();
		return "";
	}

	/*
	 * @param sitePersons
	 * checkSitePersonEmail method to finding duplicate personInchargeMail entry
	 */
	private boolean checkSitePersonEmail(Set<SitePersons> sitePersons) throws CompanyDetailsException {
		boolean emailAvailable = true;
		for (SitePersons sitePersonsItr : sitePersons) {

			Optional<SitePersons> inchargeEmail = sitePersonsRepository
					.findByPersonInchargeEmail(sitePersonsItr.getPersonInchargeEmail());

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
			if(sitePersonsItr !=null && !sitePersonsItr.getInActive()) {
				sitePersonsRepository.deleteById(sitePersonsItr.getPersonId());
				sitePersonSet.add(sitePersonsItr);
			}
		}
		return sitePersonSet;
	}

}
