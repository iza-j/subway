package com.solvd.subway.persistence;

import com.solvd.subway.domain.workers.Job;

import java.util.List;

public interface JobRepository {

	List<Job> getAll();

	void create(Job job);
}