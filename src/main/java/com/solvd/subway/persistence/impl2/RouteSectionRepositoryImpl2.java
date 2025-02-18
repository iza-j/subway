package com.solvd.subway.persistence.impl2;

import com.solvd.subway.domain.networkelements.RouteSection;
import com.solvd.subway.persistence.MybatisSessionHolder;
import com.solvd.subway.persistence.RouteSectionRepository;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class RouteSectionRepositoryImpl2 implements RouteSectionRepository {

	@Override
	public List<RouteSection> getAll() {
		try (SqlSession session = MybatisSessionHolder.getSqlSessionFactory().openSession(true)) {
			RouteSectionRepository repository = session.getMapper(RouteSectionRepository.class);
			return repository.getAll();
		}
	}

	@Override
	public void create(RouteSection routeSection) {
		try (SqlSession session = MybatisSessionHolder.getSqlSessionFactory().openSession(true)) {
			RouteSectionRepository repository = session.getMapper(RouteSectionRepository.class);
			repository.create(routeSection);
		}
	}

	@Override
	public void updateTime(Integer routeSectionId, Integer minutes) {
		try (SqlSession session = MybatisSessionHolder.getSqlSessionFactory().openSession(true)) {
			RouteSectionRepository repository = session.getMapper(RouteSectionRepository.class);
			repository.updateTime(routeSectionId, minutes);
		}
	}

	@Override
	public RouteSection getById(Integer id) {
		try (SqlSession session = MybatisSessionHolder.getSqlSessionFactory().openSession(true)) {
			RouteSectionRepository repository = session.getMapper(RouteSectionRepository.class);
			return repository.getById(id);
		}
	}
}