package com.solvd.subway.persistence.impl;

import com.solvd.subway.domain.networkelements.Line;
import com.solvd.subway.persistence.ConnectionPool;
import com.solvd.subway.persistence.LineRepository;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LineRepositoryImpl implements LineRepository {

	private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

	private static final String GET_BASE_FARE_QUERY =
		"SELECT base_fare_one_minute*minutes AS base_fare " +
			"FROM lines_ " +
			"LEFT JOIN lines_have_route_sections lhrs ON lines_.name = lhrs.line_name " +
			"LEFT JOIN route_sections rs ON lhrs.route_section_id = rs.id " +
			"LEFT JOIN stations dep ON rs.departure_station_id = dep.id " +
			"LEFT JOIN stations dest ON rs.destination_station_id = dest.id " +
			"LEFT JOIN zones ON rs.zone_name = zones.name " +
			"WHERE lines_.name = ?;" ;

	@Override
	public BigDecimal getBaseFare(String name) {
		BigDecimal baseFare = BigDecimal.valueOf(0);
		Connection connection = CONNECTION_POOL.getConnection();

		try (PreparedStatement preparedStatement = connection.prepareStatement(
			GET_BASE_FARE_QUERY)) {
			preparedStatement.setString(1, name);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				baseFare = baseFare.add(rs.getBigDecimal("base_fare"));
			}

		} catch (SQLException e) {
			throw new RuntimeException("Unable to get base fare.", e);
		} finally {
			CONNECTION_POOL.releaseConnection(connection);
		}
		return baseFare;
	}

	@Override
	public void create(Line line) {
		Connection connection = CONNECTION_POOL.getConnection();

		try (PreparedStatement ps = connection.prepareStatement(
			"INSERT INTO lines_(name) VALUE (?);")) {
			ps.setString(1, line.getName());
			ps.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException("Unable to create line.", e);
		} finally {
			CONNECTION_POOL.releaseConnection(connection);
		}
	}

	@Override
	public void delete(Line line){
		Connection connection = CONNECTION_POOL.getConnection();

		try (PreparedStatement ps = connection.prepareStatement(
			"DELETE lines_ FROM lines_ WHERE name = ?;")) {
			ps.setString(1, line.getName());
			ps.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException("Unable to delete line.", e);
		} finally {
			CONNECTION_POOL.releaseConnection(connection);
		}
	}
}