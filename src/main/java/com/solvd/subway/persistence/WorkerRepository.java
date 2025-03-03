package com.solvd.subway.persistence;

import com.solvd.subway.domain.workers.Worker;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WorkerRepository {

	List<Worker> getAll();

	void create(@Param("worker") Worker worker);

	void delete(@Param("worker") Worker worker);

	void updateName(@Param("id") Integer id, @Param("name") String name);
}