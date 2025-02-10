package com.solvd.subway.service.impl;

import com.solvd.subway.domain.workers.Job;
import com.solvd.subway.persistence.JobRepository;
import com.solvd.subway.persistence.impl.JobRepositoryImpl;
import com.solvd.subway.service.JobService;

import java.util.List;

public class JobServiceImpl implements JobService {

	private final JobRepository jobRepository;

	public JobServiceImpl() {
		this.jobRepository = new JobRepositoryImpl();
	}

	@Override
	public List<Job> getAll() {
		return jobRepository.getAll();
	}

	@Override
	public void create(Job job) {
		jobRepository.create(job);
//		System.out.println("Job title \'" + job.getTitle() + "\' added to the database!");
	}
}