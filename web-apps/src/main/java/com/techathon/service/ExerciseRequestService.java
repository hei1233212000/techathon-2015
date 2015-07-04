package com.techathon.service;

import com.avaje.ebean.EbeanServer;
import com.techathon.model.entity.Exercise;
import com.techathon.model.entity.ExerciseRequest;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.IntStream;

@Service
public class ExerciseRequestService {
	private EbeanServer ebeanServer;

	@Inject
	public ExerciseRequestService(EbeanServer ebeanServer) {
		this.ebeanServer = ebeanServer;
	}

	/**
	 * Null may be returned
	 */
	public ExerciseRequest find(long id) {
		return ebeanServer.find(ExerciseRequest.class).fetch("exercises").where().eq("id", id).findUnique();
	}

	public List<ExerciseRequest> findAll() {
		return ebeanServer.find(ExerciseRequest.class).fetch("exercises").findList();
	}

	/**
	 * It will create the ExerciseRequest and corresponding Exercise based on the frequency
	 */
	public ExerciseRequest create(final ExerciseRequest exerciseRequest) {
		final Date startDate = exerciseRequest.getStart();
		IntStream.rangeClosed(1, exerciseRequest.getFrequency()).forEach(i -> {
			Exercise exercise = new Exercise();
			LocalDateTime localDateTime = LocalDateTime.ofInstant(startDate.toInstant(), ZoneId.systemDefault()).plusDays(i - 1);
			Date date = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
			exercise.setDate(date);
			exerciseRequest.addExercise(exercise);
		});
		ebeanServer.save(exerciseRequest);
		return exerciseRequest;
	}

	public int delete(long id) {
		return ebeanServer.delete(ExerciseRequest.class, id);
	}
}
