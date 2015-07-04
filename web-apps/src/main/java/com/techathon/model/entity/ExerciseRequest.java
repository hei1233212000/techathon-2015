package com.techathon.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.common.base.MoreObjects;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "EXERCISE_REQUEST")
public class ExerciseRequest extends UpdateableBaseEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "CLIENT_ID")
	private Long clientId;

	@Column(name = "REQUESTER_ID")
	private Long requesterId;

	@Column
	@JsonFormat(pattern = "dd-MMM-yyyy")
	private Date start;

	@Column
	@JsonFormat(pattern = "dd-MMM-yyyy")
	private Date end;

	@Column
	private Integer frequency;

	@Column(name = "SPORT_TYPE")
	private String sportType;

	@Column
	private String intensity;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "exerciseRequest")
	private List<Exercise> exercises;

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this)
				.add("id", id)
				.add("super", super.toString())
				.add("clientId", clientId)
				.add("requesterId", requesterId)
				.add("start", start)
				.add("end", end)
				.add("frequency", frequency)
				.add("sportType", sportType)
				.add("intensity", intensity)
				.toString();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}

	public Long getRequesterId() {
		return requesterId;
	}

	public void setRequesterId(Long requesterId) {
		this.requesterId = requesterId;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public Integer getFrequency() {
		return frequency;
	}

	public void setFrequency(Integer frequency) {
		this.frequency = frequency;
	}

	public String getSportType() {
		return sportType;
	}

	public void setSportType(String sportType) {
		this.sportType = sportType;
	}

	public String getIntensity() {
		return intensity;
	}

	public void setIntensity(String intensity) {
		this.intensity = intensity;
	}

	public List<Exercise> getExercises() {
		return exercises;
	}

	public void setExercises(List<Exercise> exercises) {
		this.exercises = exercises;
	}

	public void addExercise(Exercise exercise) {
		getExercises().add(exercise);
		exercise.setExerciseRequest(this);
	}
}
