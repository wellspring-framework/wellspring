package org.wellspring.crud.service.impl;

import org.springframework.stereotype.Service;
import org.wellspring.crud.persistence.domain.City;
import org.wellspring.crud.persistence.repository.impl.CityRepository;
import org.wellspring.crud.service.CityService;

@Service
public class CityServiceImpl extends
		RedeableServiceImpl<CityRepository, City, Long>implements
		CityService {

	/*
	 * @Autowired public CityServiceImpl(CityRepository repository) {
	 * super(repository); }
	 */
}
