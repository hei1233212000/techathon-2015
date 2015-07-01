package com.techathon.model.entity;

import com.google.common.base.MoreObjects;

import javax.persistence.*;

@Entity
public class Sample extends UpdateableBaseEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column
	private String name;

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this)
				.add("id", id)
				.add("super", super.toString())
				.add("name", name)
				.toString();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static class Builder extends UpdateableBaseEntityBuilder<Sample, Builder> {
		private Long id;
		private String name;

		@Override
		protected Sample newEntity() {
			return new Sample();
		}

		@Override
		protected Builder getThis() {
			return this;
		}

		public Builder id(Long id) {
			this.id = id;
			return getThis();
		}

		public Builder name(String name) {
			this.name = name;
			return this;
		}

		@Override
		public Sample build() {
			Sample sample = super.build();
			sample.id = id;
			sample.name = name;
			return sample;
		}
	}
}
