package org.wellspring.crud.persistence.listener;

import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.domain.AbstractAuditable;

public class EntityListener {
	public static final Logger LOGGER = LoggerFactory.getLogger(EntityListener.class);

	@PrePersist
	public void prePersist(final Object entity) {
		LOGGER.debug("prePersist: " + entity.toString());
		prepareAuditable(entity);
	}

	@PostPersist
	public void postPersist(final Object entity) {
		LOGGER.debug("postPersist: " + entity.toString());
	}

	@PreUpdate
	public void preUpdate(final Object entity) {
		updateAuditable(entity);
		LOGGER.debug("preUpdate: " + entity.toString());
	}

	@PostUpdate
	public void postUpdate(final Object entity) {
		LOGGER.debug("postUpdate: " + entity.toString());
	}

	@PostLoad
	public void postLoad(final Object entity) {
		LOGGER.debug("postLoad: " + entity.toString());
	}

	@PreRemove
	public void preRemove(final Object entity) {
		LOGGER.debug("preRemove: " + entity.toString());
	}

	@PostRemove
	public void postRemove(final Object entity) {
		LOGGER.debug("postRemove: " + entity.toString());
	}

	private void prepareAuditable(final Object obj) {
		if (obj instanceof AbstractAuditable) {
			AbstractAuditable<?, ?> entity = (AbstractAuditable<?, ?>) obj;

			if (entity.getCreatedDate() == null) {
				entity.setCreatedDate(new DateTime());
				entity.setLastModifiedDate(new DateTime());

			}

			if (entity.getLastModifiedDate() == null) {

				entity.setLastModifiedDate(new DateTime());

			}
		}
	}

	private void updateAuditable(final Object obj) {
		if (obj instanceof AbstractAuditable) {
			AbstractAuditable<?, ?> entity = (AbstractAuditable<?, ?>) obj;
			entity.setLastModifiedDate(new DateTime());
		}
	}

}