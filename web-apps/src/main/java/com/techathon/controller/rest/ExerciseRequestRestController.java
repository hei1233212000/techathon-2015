package com.techathon.controller.rest;

import com.techathon.model.entity.ExerciseRequest;
import com.techathon.service.ExerciseRequestService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;

@RestController
@RequestMapping("/api/v1/exercise-requests")
public class ExerciseRequestRestController {
	private ExerciseRequestService exerciseRequestService;

	@Inject
	public ExerciseRequestRestController(ExerciseRequestService exerciseRequestService) {
		this.exerciseRequestService = exerciseRequestService;
	}

	@RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ExerciseRequest>> getAll() {
		return new ResponseEntity<>(exerciseRequestService.findAll(), HttpStatus.OK);
	}

	@RequestMapping(value = "/{id:\\d*}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ExerciseRequest> get(@PathVariable Long id) {
		ExerciseRequest exerciseRequest = exerciseRequestService.find(id);
		if (exerciseRequest == null) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<>(exerciseRequest, HttpStatus.OK);
	}

	@RequestMapping(value = "/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ExerciseRequest> create(@RequestBody ExerciseRequest exerciseRequest) {
		ExerciseRequest createdExerciseRequest = exerciseRequestService.create(exerciseRequest);
		return new ResponseEntity<>(createdExerciseRequest, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id:\\d*}", method = RequestMethod.DELETE)
	public ResponseEntity<?> delete(@PathVariable Long id) {
		exerciseRequestService.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
