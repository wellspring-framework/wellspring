package org.wellspring.crud.controller.advice;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.wellspring.crud.binding.ErrorResource;
import org.wellspring.crud.binding.FieldErrorResource;
import org.wellspring.crud.exceptions.EntityNotFoundException;
import org.wellspring.crud.exceptions.InvalidRequestException;

@ControllerAdvice
public class CrudExceptionHandler extends ResponseEntityExceptionHandler {
	public static final Logger LOGGER = LoggerFactory.getLogger(CrudExceptionHandler.class);

	@ExceptionHandler({ InvalidRequestException.class })
	protected ResponseEntity<Object> handleInvalidRequest(RuntimeException e, WebRequest request) {
		InvalidRequestException ire = (InvalidRequestException) e;
		List<FieldErrorResource> fieldErrorResources = new ArrayList<>();

		List<FieldError> fieldErrors = ire.getErrors().getFieldErrors();
		for (FieldError fieldError : fieldErrors) {
			FieldErrorResource fieldErrorResource = new FieldErrorResource();
			fieldErrorResource.setResource(fieldError.getObjectName());
			fieldErrorResource.setField(fieldError.getField());
			fieldErrorResource.setCode(fieldError.getCode());
			fieldErrorResource.setMessage(fieldError.getDefaultMessage());
			fieldErrorResources.add(fieldErrorResource);
		}

		ErrorResource error = new ErrorResource("InvalidRequest", ire.getMessage());
		error.setFieldErrors(fieldErrorResources);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		LOGGER.debug("\nInvalidRequest:" + error.toString());

		return handleExceptionInternal(e, error, headers, HttpStatus.UNPROCESSABLE_ENTITY, request);
	}

	@ExceptionHandler({ EntityNotFoundException.class })
	protected ResponseEntity<Object> handleEntityNotFound(RuntimeException e, WebRequest request) {
		EntityNotFoundException exception = (EntityNotFoundException) e;

		ErrorResource error = new ErrorResource("EntityNotFound", exception.getMessage());

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		LOGGER.debug("\nEntityNotFound:" + error.toString());

		return handleExceptionInternal(e, error, headers, HttpStatus.NOT_FOUND, request);
	}

	@ExceptionHandler({ AccessDeniedException.class })
	protected ResponseEntity<Object> handleAccessDenied(RuntimeException e, WebRequest request) {
		AccessDeniedException exception = (AccessDeniedException) e;

		ErrorResource error = new ErrorResource("AccessDenied", exception.getMessage());

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		LOGGER.debug("\nAccessDenied:" + error.toString());

		return handleExceptionInternal(e, error, headers, HttpStatus.FORBIDDEN, request);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleError(Exception e, WebRequest request) {
		AccessDeniedException exception = (AccessDeniedException) e;

		ErrorResource error = new ErrorResource("Exception", exception.getMessage());

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		LOGGER.debug("\nException:" + error.toString());
		return handleExceptionInternal(e, error, headers, HttpStatus.INTERNAL_SERVER_ERROR, request);
	}

}