package org.wellspring.crud.controller.impl;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

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
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.wellspring.crud.controller.RestCrudController;
import org.wellspring.crud.exceptions.EntityNotFoundException;
import org.wellspring.crud.exceptions.InvalidRequestException;
import org.wellspring.crud.persistence.repository.CrudRepository;
import org.wellspring.crud.service.CrudService;
import org.wellspring.crud.util.CrudConstants;

import com.wordnik.swagger.annotations.ApiOperation;

public class RestCrudControllerImpl<S extends CrudService<R, T, ID>, R extends CrudRepository<T, ID>, T extends Persistable<ID>, ID extends Serializable>
		implements RestCrudController<S, R, T, ID> {

	private final String PAGE = "page";

	private final String SIZE = "size";

	@Autowired
	private S service;

	public S getService() {
		return service;
	}

	private Class<T> entityClass;

	@SuppressWarnings("unchecked")
	public RestCrudControllerImpl() {
		this.entityClass = (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[2];
	}

	@Override
	@ApiOperation(value = "Count entities")
	@RequestMapping(value = CrudConstants.OPERATION_COUNT, method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public HttpEntity<Resource<Long>> count() {
		Resource<Long> resource = new Resource<Long>(Long.valueOf(service.count()));

		resource.add(buildLink(CrudConstants.OPERATION_COUNT, "count"));
		return new ResponseEntity<Resource<Long>>(resource, HttpStatus.OK);
	}

	@Override
	@ApiOperation(value = "Delete specific entity")
	@RequestMapping(value = CrudConstants.OPERATION_DELETE_BY_ID + CrudConstants.ID, method = RequestMethod.DELETE, produces = { MediaType.APPLICATION_JSON_VALUE })
	public HttpEntity<Resource<T>> delete(@PathVariable("id") ID id) {
		T entity = service.findOne(id);
		Resource<T> resource = new Resource<T>(entity);
		service.delete(id);
		resource.add(buildLink(CrudConstants.OPERATION_SAVE, "post"));
		return new ResponseEntity<Resource<T>>(resource, HttpStatus.OK);
	}

	@SuppressWarnings("unchecked")
	@Override
	@RequestMapping(value = CrudConstants.OPERATION_DELETE_BY_ENTITIES, method = RequestMethod.DELETE, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public HttpEntity<Resources<T>> delete(Iterable<T> entities) {

		List<ID> ids = new ArrayList<ID>();

		for (T entity : entities) {
			if (entity instanceof Persistable) {
				Persistable<ID> persistable = (Persistable<ID>) ids;
				ids.add(persistable.getId());
			}
		}

		Iterable<T> savedEntities = service.findAll(ids);
		Resources<T> resource = new Resources<T>(savedEntities);
		service.delete(entities);
		return new ResponseEntity<Resources<T>>(resource, HttpStatus.OK);
	}

	@Override
	@RequestMapping(value = CrudConstants.OPERATION_DELETE_BY_ENTITY, method = RequestMethod.DELETE, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public HttpEntity<Resource<T>> delete(@RequestBody T entity) {
		T savedEntity = service.findOne(((Persistable<ID>) entity).getId());
		Resource<T> resource = new Resource<T>(savedEntity);
		service.delete(savedEntity);

		resource.add(buildLink(CrudConstants.OPERATION_SAVE, "post"));
		return new ResponseEntity<Resource<T>>(resource, HttpStatus.OK);
	}

	@Override
	@ApiOperation(value = "Delete all entities")
	@RequestMapping(value = CrudConstants.OPERATION_DELETE_ALL, method = RequestMethod.DELETE, produces = { MediaType.APPLICATION_JSON_VALUE })
	public HttpEntity<Resources<T>> deleteAll() {
		Iterable<T> savedEntities = service.findAll();
		Resources<T> resource = new Resources<T>(savedEntities);
		service.deleteAll();
		resource.add(buildLink(CrudConstants.OPERATION_DELETE_ALL, "deleteAll"));

		return new ResponseEntity<Resources<T>>(resource, HttpStatus.OK);
	}

	@Override
	@ApiOperation(value = "Verify if the specific entity exists")
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
	@ApiOperation(value = "Find all entities")
	@RequestMapping(value = CrudConstants.OPERATION_FIND_ALL_SORTED, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public HttpEntity<Resources<T>> findAll(Sort sort) {
		Resources<T> resource = new Resources<T>(service.findAll(sort));
		return new ResponseEntity<Resources<T>>(resource, HttpStatus.OK);
	}

	@Override
	@ApiOperation(value = "Find specific entity")
	@RequestMapping(value = CrudConstants.OPERATION_FIND_ONE + CrudConstants.ID, method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public HttpEntity<Resource<T>> findOne(@PathVariable("id") ID id) {
		T entity = service.findOne(id);
		if (entity == null) {
			throw new EntityNotFoundException(String.valueOf(id));
		}
		Resource<T> resource = new Resource<T>(entity);

		resource.add(buildLink(CrudConstants.OPERATION_FIND_ONE + CrudConstants.ID, "get", id));
		resource.add(buildLink(CrudConstants.OPERATION_SAVE, "post"));
		resource.add(buildLink(CrudConstants.OPERATION_UPDATE + CrudConstants.ID, "put", id));
		resource.add(buildLink(CrudConstants.OPERATION_DELETE_BY_ID + CrudConstants.ID, "delete", id));
		resource.add(buildLink(CrudConstants.OPERATION_VALIDATE, "validate"));

		return new ResponseEntity<Resource<T>>(resource, HttpStatus.OK);
	}

	@Override
	@RequestMapping(value = CrudConstants.OPERATION_SAVE_ENTITIES, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public HttpEntity<Resources<T>> save(Iterable<T> entities) {
		Resources<T> resource = new Resources<T>(service.save(entities));

		return new ResponseEntity<Resources<T>>(resource, HttpStatus.CREATED);
	}

	@Override
	@RequestMapping(value = CrudConstants.OPERATION_UPDATE_ENTITIES, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public HttpEntity<Resources<T>> update(Iterable<T> entities) {
		Resources<T> resource = new Resources<T>(service.save(entities));
		return new ResponseEntity<Resources<T>>(resource, HttpStatus.OK);
	}

	@Override
	@ApiOperation(value = "Save the entity ")
	@RequestMapping(value = CrudConstants.OPERATION_SAVE, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public HttpEntity<Resource<T>> save(@RequestBody @Valid T entity, BindingResult bindingResult, Model model) {
		validate(entity, bindingResult);
		T savedEntity = service.save(entity);
		Resource<T> resource = new Resource<T>(savedEntity);

		resource.add(buildLink(CrudConstants.OPERATION_FIND_ONE + CrudConstants.ID, "get", savedEntity.getId()));
		resource.add(buildLink(CrudConstants.OPERATION_UPDATE + CrudConstants.ID, "put", savedEntity.getId()));
		resource.add(buildLink(CrudConstants.OPERATION_DELETE_BY_ID + CrudConstants.ID, "delete", savedEntity.getId()));
		resource.add(buildLink(CrudConstants.OPERATION_VALIDATE, "validate"));
		return new ResponseEntity<Resource<T>>(resource, HttpStatus.CREATED);
	}

	@Override
	@ApiOperation(value = "Update the entity")
	@RequestMapping(value = CrudConstants.OPERATION_UPDATE + CrudConstants.ID, method = RequestMethod.PUT, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public HttpEntity<Resource<T>> update(@RequestBody @Valid T entity, @PathVariable("id") ID id, BindingResult bindingResult, Model model) {
		validate(entity, bindingResult);
		Resource<T> resource = new Resource<T>(service.save(entity));

		resource.add(buildLink(CrudConstants.OPERATION_FIND_ONE + CrudConstants.ID, "get", id));
		resource.add(buildLink(CrudConstants.OPERATION_UPDATE + CrudConstants.ID, "put", id));
		resource.add(buildLink(CrudConstants.OPERATION_DELETE_BY_ID + CrudConstants.ID, "delete", entity.getId()));
		resource.add(buildLink(CrudConstants.OPERATION_VALIDATE, "validate"));
		return new ResponseEntity<Resource<T>>(resource, HttpStatus.OK);
	}

	@Override
	@ApiOperation(value = "Validate the entity")
	@RequestMapping(value = CrudConstants.OPERATION_VALIDATE, method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public HttpEntity<Resource<Boolean>> validate(@RequestBody @Valid T entity, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			throw new InvalidRequestException("Invalid " + entityClass.getSimpleName(), bindingResult);
		}
		Resource<Boolean> resource = new Resource<Boolean>(true);

		if (entity.getId() != null) {
			resource.add(buildLink(CrudConstants.OPERATION_FIND_ONE + CrudConstants.ID, "get", entity.getId()));
			resource.add(buildLink(CrudConstants.OPERATION_UPDATE + CrudConstants.ID, "put", entity.getId()));
			resource.add(buildLink(CrudConstants.OPERATION_DELETE_BY_ID + CrudConstants.ID, "delete", entity.getId()));
			resource.add(buildLink(CrudConstants.OPERATION_VALIDATE, "validate"));
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
