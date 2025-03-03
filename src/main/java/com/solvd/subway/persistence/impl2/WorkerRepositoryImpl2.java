package com.solvd.subway.persistence.impl2;

import com.solvd.subway.domain.workers.Worker;
import com.solvd.subway.persistence.MybatisSessionHolder;
import com.solvd.subway.persistence.WorkerRepository;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class WorkerRepositoryImpl2 implements WorkerRepository {

	@Override
	public void create(Worker worker) {
		try (SqlSession session = MybatisSessionHolder.getSqlSessionFactory().openSession(true)) {
			WorkerRepository repository = session.getMapper(WorkerRepository.class);
			repository.create(worker);
		}
	}

	@Override
	public List<Worker> getAll() {
		try (SqlSession session = MybatisSessionHolder.getSqlSessionFactory().openSession(true)) {
			WorkerRepository repository = session.getMapper(WorkerRepository.class);
			return repository.getAll();
		}
	}

	@Override
	public void delete(Worker worker) {
		try (SqlSession session = MybatisSessionHolder.getSqlSessionFactory().openSession(true)) {
			WorkerRepository repository = session.getMapper(WorkerRepository.class);
			repository.delete(worker);
		}
	}

	@Override
	public void updateName(Integer id, String name) {
		try (SqlSession session = MybatisSessionHolder.getSqlSessionFactory().openSession(true)) {
			WorkerRepository repository = session.getMapper(WorkerRepository.class);
			repository.updateName(id, name);
		}
	}
}