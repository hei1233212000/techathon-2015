package com.techathon.model.entity;

import com.google.common.base.MoreObjects;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
public abstract class SimpleBaseEntity implements Serializable {
	@Version
	protected Long version;

	@Column(name = "CREATED_TS", nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	protected Date createdTs;

	/**
	 * Please override it and delegate this method if you need a new @PrePersist in the children class
	 */
	@PrePersist
	public void prePersist() {
		createdTs = new Date();
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(SimpleBaseEntity.class)
				.add("version", version)
				.add("createdTs", createdTs)
				.toString();
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public Date getCreatedTs() {
		return createdTs;
	}

	public void setCreatedTs(Date createdTs) {
		this.createdTs = createdTs;
	}

	public static abstract class SimpleBaseEntityBuilder<E extends SimpleBaseEntity, B extends SimpleBaseEntityBuilder<E, B>> {
		protected Long version;

		/**
		 * @return the new entity instance
		 */
		protected abstract E newEntity();

		/**
		 * just return <code>this</code>
		 */
		protected abstract B getThis();

		public B version(Long version) {
			this.version = version;
			return getThis();
		}

		public E build() {
			E entity = newEntity();
			entity.version = version;
			return entity;
		}
	}
}
