package com.solvd.subway.persistence.impl;

import com.solvd.subway.domain.networkelements.Line;
import com.solvd.subway.domain.networkelements.RouteSection;
import com.solvd.subway.persistence.ConnectionPool;
import com.solvd.subway.persistence.LineRepository;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.solvd.subway.persistence.impl.RouteSectionRepositoryImpl.mapRows;

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
			"WHERE lines_.name = ?;";

	private static final String VIEW_SECTIONS_QUERY =
		"SELECT line_name, departure_station.name AS departure, destination_station.name AS destination, minutes, zone_name, base_fare_one_minute " +
			"FROM route_sections " +
			"LEFT JOIN stations AS departure_station ON route_sections.departure_station_id = departure_station.id " +
			"LEFT JOIN stations AS destination_station ON route_sections.destination_station_id = destination_station.id " +
			"RIGHT JOIN lines_have_route_sections ON lines_have_route_sections.route_section_id = route_sections.id " +
			"LEFT JOIN zones ON route_sections.zone_name = zones.name " +
			"WHERE line_name = ? " +
			"ORDER BY section_no ASC ;";

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
	public void delete(Line line) {
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

	@Override
	public List<RouteSection> getSections(String lineName) {
		Connection connection = CONNECTION_POOL.getConnection();
		List<RouteSection> routeSections = new ArrayList<>();

		try (PreparedStatement ps = connection.prepareStatement(
			VIEW_SECTIONS_QUERY)) {
			ps.setString(1, lineName);
			ps.executeQuery();
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				routeSections = mapRows(rs);
			}

		} catch (SQLException e) {
			throw new RuntimeException("Unable to view line sections.", e);
		} finally {
			CONNECTION_POOL.releaseConnection(connection);
		}
		return routeSections;
	}

	@Override
	public Line getByName(String name) {
		Connection connection = CONNECTION_POOL.getConnection();
		Line line = new Line();

		try (PreparedStatement ps = connection.prepareStatement(
			"SELECT * FROM lines_ WHERE name = ?;")) {
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				line.setName(name);
			}

		} catch (SQLException e) {
			throw new RuntimeException("Unable to delete line.", e);
		} finally {
			CONNECTION_POOL.releaseConnection(connection);
		}
		return line;
	}

	@Override
	public List<Line> getAll() {
		return new ArrayList<Line>();
	}
}