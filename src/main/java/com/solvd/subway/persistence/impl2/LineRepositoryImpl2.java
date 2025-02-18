package com.solvd.subway.persistence.impl2;

import com.solvd.subway.domain.networkelements.Line;
import com.solvd.subway.domain.networkelements.RouteSection;
import com.solvd.subway.persistence.LineRepository;
import com.solvd.subway.persistence.MybatisSessionHolder;
import org.apache.ibatis.session.SqlSession;

import java.math.BigDecimal;
import java.util.List;

public class LineRepositoryImpl2 implements LineRepository {

	@Override
	public void create(Line line) {
		try (SqlSession session = MybatisSessionHolder.getSqlSessionFactory().openSession(true)) {
			LineRepository repository = session.getMapper(LineRepository.class);
			repository.create(line);
		}
	}

	@Override
	public void delete(Line line) {
		try (SqlSession session = MybatisSessionHolder.getSqlSessionFactory().openSession(true)) {
			LineRepository repository = session.getMapper(LineRepository.class);
			repository.delete(line);
		}
	}

	@Override
	public BigDecimal getBaseFare(String name) {
		try (SqlSession session = MybatisSessionHolder.getSqlSessionFactory().openSession(true)) {
			LineRepository repository = session.getMapper(LineRepository.class);
			return repository.getBaseFare(name);
		}
	}

	@Override
	public List<RouteSection> getSections(String lineName) {
		try (SqlSession session = MybatisSessionHolder.getSqlSessionFactory().openSession(true)) {
			LineRepository repository = session.getMapper(LineRepository.class);
			return repository.getSections(lineName);
		}
	}

	@Override
	public Line getByName(String name) {
		try (SqlSession session = MybatisSessionHolder.getSqlSessionFactory().openSession(true)) {
			LineRepository repository = session.getMapper(LineRepository.class);
			return repository.getByName(name);
		}
	}

	@Override
	public List<Line> getAll() {
		try (SqlSession session = MybatisSessionHolder.getSqlSessionFactory().openSession(true)) {
			LineRepository repository = session.getMapper(LineRepository.class);
			return repository.getAll();
		}
	}
}