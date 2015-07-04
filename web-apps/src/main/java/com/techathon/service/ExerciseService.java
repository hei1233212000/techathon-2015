package com.techathon.service;

import com.avaje.ebean.EbeanServer;
import com.techathon.model.entity.Exercise;
import com.techathon.model.entity.ExerciseRequest;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class ExerciseService {
	private EbeanServer ebeanServer;

	@Inject
	public ExerciseService(EbeanServer ebeanServer) {
		this.ebeanServer = ebeanServer;
	}

	/**
	 * ordered by Exercise.date ASC
	 */
	public List<Exercise> findByClientId(long clientId) {
		List<ExerciseRequest> exerciseRequests = ebeanServer.find(ExerciseRequest.class)
													.where().eq("clientId", clientId)
													.findList();
		if (exerciseRequests.isEmpty()) return Collections.emptyList();
		final List<Exercise> exercises = new ArrayList<>();
		exerciseRequests.forEach(er -> exercises.addAll(er.getExercises()));
		Collections.sort(exercises, (o1, o2) -> o1.getDate().compareTo(o2.getDate()));
		return exercises;
	}

	public Exercise update(long id, String path) {
		Exercise exercise = ebeanServer.find(Exercise.class, id);
		if (exercise == null) return null;
		exercise.setPath(path);
		ebeanServer.update(exercise);
		return exercise;
	}
}
