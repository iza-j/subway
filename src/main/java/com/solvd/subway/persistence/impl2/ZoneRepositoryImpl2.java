package com.solvd.subway.persistence.impl2;

import com.solvd.subway.domain.networkelements.Zone;
import com.solvd.subway.persistence.MybatisSessionHolder;
import com.solvd.subway.persistence.ZoneRepository;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class ZoneRepositoryImpl2 implements ZoneRepository {

	@Override
	public void create(Zone zone) {
		try (SqlSession session = MybatisSessionHolder.getSqlSessionFactory().openSession(true)) {
			ZoneRepository repository = session.getMapper(ZoneRepository.class);
			repository.create(zone);
		}
	}

	@Override
	public Zone getById(String name) {
		try (SqlSession session = MybatisSessionHolder.getSqlSessionFactory().openSession(true)) {
			ZoneRepository repository = session.getMapper(ZoneRepository.class);
			return repository.getById(name);
		}
	}

	@Override
	public List<Zone> getAll() {
		try (SqlSession session = MybatisSessionHolder.getSqlSessionFactory().openSession(true)) {
			ZoneRepository repository = session.getMapper(ZoneRepository.class);
			return repository.getAll();
		}
	}
}