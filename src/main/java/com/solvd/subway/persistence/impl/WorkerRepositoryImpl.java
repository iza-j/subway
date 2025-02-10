package com.solvd.subway.persistence.impl;

import com.solvd.subway.domain.workers.Worker;
import com.solvd.subway.persistence.ConnectionPool;
import com.solvd.subway.persistence.StationRepository;
import com.solvd.subway.persistence.WorkerRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WorkerRepositoryImpl implements WorkerRepository {

	private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

	public static Worker mapRow(ResultSet rs) throws SQLException {
		Worker worker = new Worker();

		worker.setId(rs.getInt("id"));
		worker.setName(rs.getString("name"));
		worker.setHourlyWage(rs.getBigDecimal("hourly_wage"));
//		worker.setJob(...);
//		worker.setLine(...);

		StationRepository stationRepository = new StationRepositoryImpl();
		worker.setStation(stationRepository.getById(rs.getInt("station_id")));

		return worker;
	}

	public static List<Worker> mapRows(ResultSet rs) throws SQLException {
		List<Worker> workers = new ArrayList<>();
		while (rs.next()) {
			workers.add(mapRow(rs));
		}
		return workers;
	}

	@Override
	public List<Worker> getAll() {
		List<Worker> workers;
		Connection connection = CONNECTION_POOL.getConnection();

		try (PreparedStatement ps = connection.prepareStatement(
			"SELECT * FROM workers;")) {
			ResultSet resultSet = ps.executeQuery();
			workers = mapRows(resultSet);

		} catch (SQLException e) {
			throw new RuntimeException("Unable to get all workers.", e);
		} finally {
			CONNECTION_POOL.releaseConnection(connection);
		}
		return workers;
	}

	@Override
	public void create(Worker worker) {
		Connection connection = CONNECTION_POOL.getConnection();

		try (PreparedStatement ps = connection.prepareStatement(
			"INSERT INTO workers(name, hourly_wage) VALUES (?, ?);", Statement.RETURN_GENERATED_KEYS)) {
			ps.setString(1, worker.getName());
			ps.setBigDecimal(2, worker.getHourlyWage());
			ps.executeUpdate();

			ResultSet resultSet = ps.getGeneratedKeys();
			while (resultSet.next()) {
				worker.setId(resultSet.getInt(1));
			}

		} catch (SQLException e) {
			throw new RuntimeException("Unable to create worker.", e);
		} finally {
			CONNECTION_POOL.releaseConnection(connection);
		}
	}

	@Override
	public void delete(Worker worker) {
		Connection connection = CONNECTION_POOL.getConnection();

		try (PreparedStatement ps = connection.prepareStatement(
			"DELETE workers FROM workers WHERE id = ? AND name = ?;")) {
			ps.setInt(1, worker.getId());
			ps.setString(2, worker.getName());
			ps.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException("Unable to delete worker.", e);
		} finally {
			CONNECTION_POOL.releaseConnection(connection);
		}
	}

	@Override
	public void updateName(Integer id, String name) {
		Connection connection = CONNECTION_POOL.getConnection();

		try (PreparedStatement ps = connection.prepareStatement(
			"UPDATE workers SET name = ? WHERE id = ?;")) {
			ps.setString(1, name);
			ps.setInt(2, id);
			ps.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException("Unable to update name.", e);
		} finally {
			CONNECTION_POOL.releaseConnection(connection);
		}
	}
}