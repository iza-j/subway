package com.solvd.subway.persistence.impl;

import com.solvd.subway.domain.networkelements.Station;
import com.solvd.subway.persistence.ConnectionPool;
import com.solvd.subway.persistence.StationRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StationRepositoryImpl implements StationRepository {

	private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

	public static Station mapRow(ResultSet rs) throws SQLException {
		Station station = new Station();
		station.setId(rs.getInt("id"));
		station.setName(rs.getString("name")); // job_title
		return station;
	}

	public static List<Station> mapRows(ResultSet rs) throws SQLException {
		List<Station> stations = new ArrayList<>();
		while (rs.next()) {
			stations.add(mapRow(rs));
		}
		return stations;
	}

	@Override
	public List<Station> getAll() {
		List<Station> stations;
		Connection connection = CONNECTION_POOL.getConnection();

		try (PreparedStatement ps = connection.prepareStatement(
			"SELECT * FROM stations;")) {
			ResultSet resultSet = ps.executeQuery();
			stations = mapRows(resultSet);

		} catch (SQLException e) {
			throw new RuntimeException("Unable to get all stations.", e);
		} finally {
			CONNECTION_POOL.releaseConnection(connection);
		}
		return stations;
	}

	@Override
	public void create(Station station) {
		Connection connection = CONNECTION_POOL.getConnection();

		try (PreparedStatement ps = connection.prepareStatement(
			"INSERT INTO stations(name) VALUE (?);", Statement.RETURN_GENERATED_KEYS)) {
			ps.setString(1, station.getName());
			ps.executeUpdate();

			ResultSet resultSet = ps.getGeneratedKeys();
			while (resultSet.next()) {
				station.setId(resultSet.getInt(1));
			}

		} catch (SQLException e) {
			throw new RuntimeException("Unable to create station.", e);
		} finally {
			CONNECTION_POOL.releaseConnection(connection);
		}
	}

	@Override
	public Station getById(Integer id) {
		Station station = null;
		Connection connection = CONNECTION_POOL.getConnection();

		try (PreparedStatement ps = connection.prepareStatement(
			"SELECT * FROM stations WHERE id = ?;")) {
			ps.setInt(1, id);
			ResultSet resultSet = ps.executeQuery();
			while (resultSet.next()) {
				station = mapRow(resultSet);
			}

		} catch (SQLException e) {
			throw new RuntimeException("Unable to get station.", e);
		} finally {
			CONNECTION_POOL.releaseConnection(connection);
		}
		return station;
	}
}