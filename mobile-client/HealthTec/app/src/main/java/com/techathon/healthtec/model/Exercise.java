package com.techathon.healthtec.model;

import com.google.common.base.MoreObjects;

import java.io.Serializable;
import java.util.Date;

public class Exercise implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private Date date;
	private String path;

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this)
				.add("id", id)
				.add("date", date)
				.add("path", path)
				.toString();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
}
