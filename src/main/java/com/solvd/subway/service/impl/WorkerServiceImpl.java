package com.solvd.subway.service.impl;

import com.solvd.subway.domain.workers.Worker;
import com.solvd.subway.persistence.WorkerRepository;
import com.solvd.subway.persistence.impl2.WorkerRepositoryImpl2;
import com.solvd.subway.service.WorkerService;

import java.util.List;

public class WorkerServiceImpl implements WorkerService {

	private final WorkerRepository workerRepository;

	public WorkerServiceImpl() {
		this.workerRepository = new WorkerRepositoryImpl2();
	}

	public static void printDetails(Worker w) {
		System.out.println(new StringBuilder()
			.append(w.getId())
			.append(" --- ")
			.append(w.getName())
			.append(" --- ")
			.append(w.getHourlyWage())
			.append(" --- ")
			.append(w.getJob() != null ? w.getJob().getTitle() : null)
			.append(" --- ")
			.append(w.getLine() != null ? w.getLine().getName() : null)
			.append(" --- ")
			.append((w.getStation() != null ? w.getStation().getName() : null)));
	}

	@Override
	public Worker getById(Integer id, List<Worker> workers) {
		return workers.stream()
			.filter(i -> i.getId().equals(id))
			.findFirst()
			.orElseGet(() -> {
				Worker x = new Worker();
				return x;
			});
	}

	@Override
	public List<Worker> getAll() {
		return workerRepository.getAll();
	}

	@Override
	public void create(Worker w) {
		workerRepository.create(w);
		System.out.println("Worker added to the database! " + w.getName() + "'s ID is: " + w.getId());
	}

	@Override
	public void delete(Worker w) {
		workerRepository.delete(w);
		System.out.println("ID: " + w.getId() + ", Name: " + w.getName() + ". Worker deleted from the database!");
	}

	@Override
	public void updateName(Integer id, String name) {
		String oldName = getById(id, workerRepository.getAll()).getName();
		workerRepository.updateName(id, name);
		System.out.println(oldName + "'s data was updated! New name: " + name);
	}
}