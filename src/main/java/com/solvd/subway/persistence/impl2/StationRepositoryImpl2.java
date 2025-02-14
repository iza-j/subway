package com.solvd.subway.persistence.impl2;

import com.solvd.subway.domain.networkelements.Station;
import com.solvd.subway.persistence.MybatisSessionHolder;
import com.solvd.subway.persistence.StationRepository;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class StationRepositoryImpl2 implements StationRepository {

	@Override
	public List<Station> getAll() {
		try (SqlSession session = MybatisSessionHolder.getSqlSessionFactory().openSession(true)) {
			StationRepository repository = session.getMapper(StationRepository.class);
			return repository.getAll();
		}
	}

	@Override
	public void create(Station station) {
		try (SqlSession session = MybatisSessionHolder.getSqlSessionFactory().openSession(true)) {
			StationRepository repository = session.getMapper(StationRepository.class);
			repository.create(station);
		}
	}

	@Override
	public Station getById(Integer id) {
		try (SqlSession session = MybatisSessionHolder.getSqlSessionFactory().openSession(true)) {
			StationRepository repository = session.getMapper(StationRepository.class);
			return repository.getById(id);
		}
	}
}