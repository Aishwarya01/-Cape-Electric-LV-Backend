package com.capeelectric.service.impl;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capeelectric.exception.CompanyDetailsException;
import com.capeelectric.model.Site;
import com.capeelectric.model.SitePersons;
import com.capeelectric.repository.SitePersonsRepository;
import com.capeelectric.service.SitePersonsService;

@Service
public class SitePersonsServiceImpl implements SitePersonsService {

	@Autowired
	private SitePersonsRepository sitePersonsRepository;

	@Override
	public void addSitePerson(Site site) throws CompanyDetailsException {
		sitePersonsRepository.saveAll(site.getSitePersons());

	}

	@Override
	public void updateSitePerson(Site site) throws CompanyDetailsException {
		Set<SitePersons> sitePersons = site.getSitePersons();

		for (SitePersons sitePersonsIter : sitePersons) {
			if (sitePersonsIter.getPersonId() != null) {
				Optional<SitePersons> personInchargerepo = sitePersonsRepository
						.findByPersonInchargeEmail(sitePersonsIter.getPersonInchargeEmail());
				if (personInchargerepo.get().getPersonId().equals(sitePersonsIter.getPersonId())) {
					personInchargerepo.get().setContactNo(sitePersonsIter.getContactNo());
					personInchargerepo.get().setDesignation(sitePersonsIter.getDesignation());
					personInchargerepo.get().setPersonId(sitePersonsIter.getPersonId());
					personInchargerepo.get().setPersonIncharge(sitePersonsIter.getPersonIncharge());
					personInchargerepo.get().setPersonInchargeEmail(sitePersonsIter.getPersonInchargeEmail());
					sitePersonsRepository.save(personInchargerepo.get());
				}
				if (!personInchargerepo.get().getPersonId().equals(sitePersonsIter.getPersonId())) {
					sitePersonsRepository.deleteById(personInchargerepo.get().getPersonId());
				}
			}
			if (sitePersonsIter.getPersonId() == null) {
				sitePersonsRepository.save(sitePersonsIter);
			}

		}

	}

}
