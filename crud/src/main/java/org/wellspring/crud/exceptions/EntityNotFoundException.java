package org.wellspring.crud.exceptions;


// @ResponseStatus(HttpStatus.NOT_FOUND)
public class EntityNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 6934744629594572993L;

	public EntityNotFoundException(String id) {
		super("could not find entity '" + id + "'.");
	}
}