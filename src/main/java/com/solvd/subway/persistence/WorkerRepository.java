package com.solvd.subway.persistence;

import com.solvd.subway.domain.workers.Worker;

import java.util.List;

public interface WorkerRepository {

	List<Worker> getAll();

	void create(Worker worker);

	void delete(Worker worker);

	void updateName(Integer id, String name);
}