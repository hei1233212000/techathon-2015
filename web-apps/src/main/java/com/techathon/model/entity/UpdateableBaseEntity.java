package com.techathon.model.entity;

import com.google.common.base.MoreObjects;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
public abstract class UpdateableBaseEntity extends SimpleBaseEntity {
	@Column(name = "MODIFIED_TS", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	protected Date modifiedTs;

	/**
	 * Please override it and delegate this method if you need a new @PrePersist in the children class
	 */
	@PrePersist
	public void prePersist() {
		super.prePersist();
		modifiedTs = createdTs;
	}

	/**
	 * Please override it and delegate this method if you need a new @PreUpdate in the children class
	 */
	@PreUpdate
	public void preUpdate() {
		modifiedTs = new Date();
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(UpdateableBaseEntity.class)
				.add("super", super.toString())
				.add("modifiedTs", modifiedTs)
				.toString();
	}

	public Date getModifiedTs() {
		return modifiedTs;
	}

	public void setModifiedTs(Date modifiedTs) {
		this.modifiedTs = modifiedTs;
	}

	public static abstract class UpdateableBaseEntityBuilder<E extends UpdateableBaseEntity, B extends UpdateableBaseEntityBuilder<E, B>>
			extends SimpleBaseEntityBuilder<E, B> {
		@Override
		public E build() {
			return super.build();
		}
	}
}
