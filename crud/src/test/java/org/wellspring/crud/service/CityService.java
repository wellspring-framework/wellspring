package org.wellspring.crud.service;

import org.wellspring.crud.persistence.domain.City;
import org.wellspring.crud.persistence.repository.impl.CityRepository;

public interface CityService extends
		ReadableService<CityRepository, City, Long> {

}
