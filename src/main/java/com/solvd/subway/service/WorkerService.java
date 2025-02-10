package com.solvd.subway.service;

import com.solvd.subway.domain.workers.Worker;

import java.util.List;

public interface WorkerService {

	Worker getById(Integer id, List<Worker> workers);

	List<Worker> getAll();

	void create(Worker worker);

	void delete(Worker worker);

	void updateName(Integer id, String name);
}