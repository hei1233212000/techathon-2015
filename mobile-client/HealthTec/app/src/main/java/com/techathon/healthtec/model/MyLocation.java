package com.techathon.healthtec.model;

import com.google.common.base.MoreObjects;

import java.io.Serializable;

public class MyLocation implements Serializable {
	private static final long serialVersionUID = 1L;

	private double latitude;
	private double longitude;

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this)
				.add("latitude", latitude)
				.add("longitude", longitude)
				.toString();
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
}
