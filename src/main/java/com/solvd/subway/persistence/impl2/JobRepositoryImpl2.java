package com.solvd.subway.persistence.impl2;

import com.solvd.subway.domain.workers.Job;
import com.solvd.subway.persistence.JobRepository;
import com.solvd.subway.persistence.MybatisSessionHolder;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class JobRepositoryImpl2 implements JobRepository {

	@Override
	public List<Job> getAll() {
		try (SqlSession session = MybatisSessionHolder.getSqlSessionFactory().openSession(true)) {
			JobRepository repository = session.getMapper(JobRepository.class);
			return repository.getAll();
		}
	}

	@Override
	public void create(Job job) {
		try (SqlSession session = MybatisSessionHolder.getSqlSessionFactory().openSession(true)) {
			JobRepository repository = session.getMapper(JobRepository.class);
			repository.create(job);
		}
	}
}