package com.solvd.subway.service;

import com.solvd.subway.domain.workers.Job;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface JobService {

	List<Job> getAll();

	void create(@Param("job") Job job);
}