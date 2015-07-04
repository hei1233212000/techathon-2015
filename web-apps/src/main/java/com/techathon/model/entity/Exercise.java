package com.techathon.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.common.base.MoreObjects;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "EXERCISE")
@JsonIgnoreProperties("exerciseRequest")
public class Exercise extends UpdateableBaseEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;

	@Column
	private String path;

	@ManyToOne
	private ExerciseRequest exerciseRequest;

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this)
				.add("id", id)
				.add("super", super.toString())
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

	public ExerciseRequest getExerciseRequest() {
		return exerciseRequest;
	}

	public void setExerciseRequest(ExerciseRequest exerciseRequest) {
		this.exerciseRequest = exerciseRequest;
	}
}
