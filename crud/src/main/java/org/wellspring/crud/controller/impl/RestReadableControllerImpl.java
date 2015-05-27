package org.wellspring.crud.controller.impl;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Persistable;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.wellspring.crud.controller.RestReadableController;
import org.wellspring.crud.exceptions.EntityNotFoundException;
import org.wellspring.crud.exceptions.InvalidRequestException;
import org.wellspring.crud.persistence.repository.ReadableRepository;
import org.wellspring.crud.service.ReadableService;
import org.wellspring.crud.util.CrudConstants;

public class RestReadableControllerImpl<S extends ReadableService<R, T, ID>, R extends ReadableRepository<T, ID>, T extends Persistable<ID>, ID extends Serializable>
		implements RestReadableController<S, R, T, ID> {

	private final String PAGE = "page";

	private final String SIZE = "size";

	@Autowired
	private S service;

	public S getService() {
		return service;
	}

	private Class<T> entityClass;

	@SuppressWarnings("unchecked")
	public RestReadableControllerImpl() {
		this.entityClass = (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[2];
	}

	@Override
	@RequestMapping(value = CrudConstants.OPERATION_COUNT, method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public HttpEntity<Resource<Long>> count() {
		Resource<Long> resource = new Resource<Long>(Long.valueOf(service.count()));

		resource.add(buildLink(CrudConstants.OPERATION_COUNT, "count"));
		return new ResponseEntity<Resource<Long>>(resource, HttpStatus.OK);
	}

	@Override
	@RequestMapping(value = CrudConstants.OPERATION_EXISTS + CrudConstants.ID, method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public HttpEntity<Resource<Boolean>> exists(@PathVariable("id") ID id) {
		Resource<Boolean> resource = new Resource<Boolean>(service.exists(id));
		resource.add(buildLink(CrudConstants.OPERATION_EXISTS + CrudConstants.ID, "exists", id));
		return new ResponseEntity<Resource<Boolean>>(resource, HttpStatus.OK);
	}

	@Override
	@RequestMapping(value = CrudConstants.OPERATION_FIND_ALL_BY_IDS, method = RequestMethod.GET, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public HttpEntity<Resources<T>> findAll(Iterable<ID> ids) {
		Resources<T> resource = new Resources<T>(service.findAll(ids));
		return new ResponseEntity<Resources<T>>(resource, HttpStatus.OK);
	}

	/**
	 * Returns all sorted instances of the type
	 * 
	 * @param sort
	 * @return all sorted entities
	 */
	@Override
	@RequestMapping(value = CrudConstants.OPERATION_FIND_ALL_SORTED, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public HttpEntity<Resources<T>> findAll(Sort sort) {
		Resources<T> resource = new Resources<T>(service.findAll(sort));
		return new ResponseEntity<Resources<T>>(resource, HttpStatus.OK);
	}

	@Override
	@RequestMapping(value = CrudConstants.OPERATION_FIND_ONE + CrudConstants.ID, method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public HttpEntity<Resource<T>> findOne(@PathVariable("id") ID id) {
		T entity = service.findOne(id);
		if (entity == null) {
			throw new EntityNotFoundException(String.valueOf(id));
		}
		Resource<T> resource = new Resource<T>(entity);

		resource.add(buildLink(CrudConstants.OPERATION_FIND_ONE + CrudConstants.ID, "get", id));

		return new ResponseEntity<Resource<T>>(resource, HttpStatus.OK);
	}

	@Override
	@RequestMapping(value = CrudConstants.OPERATION_VALIDATE, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public HttpEntity<Resource<Boolean>> validate(@RequestBody @Valid T entity, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			throw new InvalidRequestException("Invalid " + entityClass.getSimpleName(), bindingResult);
		}
		Resource<Boolean> resource = new Resource<Boolean>(true);

		if (entity.getId() != null) {
			resource.add(buildLink(CrudConstants.OPERATION_FIND_ONE + CrudConstants.ID, "get", entity.getId()));
		}
		return new ResponseEntity<Resource<Boolean>>(resource, HttpStatus.OK);
	}

	private Link buildLink(String path, String rel) {
		return new Link(linkTo(getClass()).toUriComponentsBuilder().path(path).buildAndExpand().toUriString()).withRel(rel);
	}

	private Link buildLink(String path, String rel, ID id) {
		return new Link(linkTo(getClass()).toUriComponentsBuilder().path(path).buildAndExpand(id).toUriString()).withRel(rel);
	}

	private Link buildPageLink(int page, int size, String rel) {
		String path = ServletUriComponentsBuilder.fromCurrentRequestUri()
				.queryParam(PAGE, page)
				.queryParam(SIZE, size)
				.build()
				.toUriString();
		Link link = new Link(path, rel);
		return link;
	}

	/**
	 * @param pageable
	 * @return all paginated entities
	 */
	@Override
	@RequestMapping(value = CrudConstants.OPERATION_QUERY, method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public HttpEntity<Resource<Page<T>>> query(Pageable pageable) {
		Resource<Page<T>> resource = new Resource<Page<T>>(service.findAll(pageable));
		Page<T> page = resource.getContent();
		resource.add(buildPageLink(0, page.getSize(), Link.REL_FIRST));
		resource.add(buildPageLink(page.getNumber(), page.getSize(), Link.REL_SELF));

		if (page.hasNext()) {
			resource.add(buildPageLink(page.getNumber() + 1, page.getSize(), Link.REL_NEXT));
		}
		if (page.hasPrevious()) {
			resource.add(buildPageLink(page.getNumber() - 1, page.getSize(), Link.REL_PREVIOUS));
		}
		if (page.getTotalPages() > 0) {
			resource.add(buildPageLink(page.getTotalPages() - 1, page.getSize(), Link.REL_LAST));
		}
		return new ResponseEntity<Resource<Page<T>>>(resource, HttpStatus.OK);
	}
}
