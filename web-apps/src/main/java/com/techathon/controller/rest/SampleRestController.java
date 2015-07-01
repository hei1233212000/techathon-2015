package com.techathon.controller.rest;

import com.techathon.model.entity.Sample;
import com.techathon.service.SampleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;

@RestController
@RequestMapping("/api/v1/samples")
public class SampleRestController {
	private SampleService sampleService;

	@Inject
	public SampleRestController(SampleService sampleService) {
		this.sampleService = sampleService;
	}

	@RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Sample>> getAll() {
		return new ResponseEntity<>(sampleService.findAll(), HttpStatus.OK);
	}

	@RequestMapping(value = "/{id:\\d*}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Sample> get(@PathVariable Long id) {
		Sample sample = sampleService.find(id);
		if (sample == null) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<>(sample, HttpStatus.OK);
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ResponseEntity<Sample> create(@RequestBody Sample sample) {
		Sample createdSample = sampleService.create(sample);
		return new ResponseEntity<>(createdSample, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id:\\d*}", method = RequestMethod.DELETE)
	public ResponseEntity<?> delete(@PathVariable Long id) {
		sampleService.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
