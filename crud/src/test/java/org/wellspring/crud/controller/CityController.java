package org.wellspring.crud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.wellspring.crud.controller.impl.rest.RestReadableControllerImpl;
import org.wellspring.crud.persistence.domain.City;
import org.wellspring.crud.persistence.repository.impl.CityRepository;
import org.wellspring.crud.service.CityService;

@Controller
@RequestMapping("/city")
public class CityController extends
		/*
		 * AbstractRestReadableController<City, Long, ReadableRepository<City,
		 * Long>,
		 * ReadableService<City, Long, ReadableRepository<City, Long>>>
		 */
		RestReadableControllerImpl<CityService, CityRepository, City, Long> {

}
