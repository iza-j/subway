package com.solvd.subway.service;

import com.solvd.subway.domain.workers.Job;

import java.util.List;

public interface JobService {

	List<Job> getAll();

	void create(Job job);
}