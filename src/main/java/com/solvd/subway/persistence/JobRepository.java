package com.solvd.subway.persistence;

import com.solvd.subway.domain.workers.Job;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface JobRepository {

	List<Job> getAll();

	void create(@Param("job") Job job);
}