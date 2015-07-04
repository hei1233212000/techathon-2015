package com.techathon.controller.rest;

import com.techathon.model.entity.Exercise;
import com.techathon.model.pojo.BoExercise;
import com.techathon.service.ExerciseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;

@RestController
@RequestMapping("/api/v1/exercises")
public class ExerciseRestController {
	private ExerciseService exerciseService;

	@Inject
	public ExerciseRestController(ExerciseService exerciseService) {
		this.exerciseService = exerciseService;
	}

	@RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Exercise>> getAll(@RequestParam("client-id") Long clientId) {
		return new ResponseEntity<>(exerciseService.findByClientId(clientId), HttpStatus.OK);
	}

	@RequestMapping(value = "/{id:\\d*}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Exercise> update(@PathVariable Long id, @RequestBody BoExercise exercise) {
		Exercise updatedExercise = exerciseService.update(id, exercise.getPath());
		return new ResponseEntity<>(updatedExercise, HttpStatus.OK);
	}
}
