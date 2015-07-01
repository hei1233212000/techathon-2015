package com.techathon.service;

import com.avaje.ebean.EbeanServer;
import com.techathon.model.entity.Sample;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service
public class SampleService {
	private EbeanServer ebeanServer;

	@Inject
	public SampleService(EbeanServer ebeanServer) {
		this.ebeanServer = ebeanServer;
	}

	/**
	 * Null may be returned
	 */
	public Sample find(long id) {
		return ebeanServer.find(Sample.class, id);
	}

	public List<Sample> findAll() {
		return ebeanServer.find(Sample.class).findList();
	}

	public Sample create(Sample sample) {
		ebeanServer.save(sample);
		return sample;
	}

	public int delete(long id) {
		return ebeanServer.delete(Sample.class, id);
	}
}
