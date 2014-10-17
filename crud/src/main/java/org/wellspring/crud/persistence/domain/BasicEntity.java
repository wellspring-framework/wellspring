package org.wellspring.crud.persistence.domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.wellspring.crud.to.GenericTO;

@MappedSuperclass
public abstract class BasicEntity extends GenericTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5133416499250226369L;

	protected Long id;

	@Version
	@Access(AccessType.FIELD)
	@Column
	private long version;

	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "updated_by")
	private String updatedBy;

	@Column(name = "create_date")
	@Temporal(TemporalType.TIMESTAMP)
	@Basic(optional = false)
	private Date createDate;

	@Column(name = "update_date")
	@Temporal(TemporalType.TIMESTAMP)
	@Basic(optional = false)
	private Date updateDate;

	public Date getCreateDate() {
		return createDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public long getVersion() {
		return version;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public void setVersion(long version) {
		this.version = version;
	}

	@PreUpdate
	public void updateTimeStamps() {
		Date now = new Date();
		setUpdateDate(now);
		if (getCreateDate() == null) {
			setCreateDate(now);
		}

	}

	@PrePersist
	public void updateUpdatedBy() {
		if (getUpdatedBy() == null) {
			if (getCreatedBy() != null) {
				setUpdatedBy(getCreatedBy());
			}
		}
		updateTimeStamps();
	}

}
