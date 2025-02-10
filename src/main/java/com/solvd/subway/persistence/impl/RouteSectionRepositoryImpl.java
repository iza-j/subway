package com.solvd.subway.persistence.impl;

import com.solvd.subway.domain.networkelements.RouteSection;
import com.solvd.subway.domain.networkelements.Station;
import com.solvd.subway.persistence.ConnectionPool;
import com.solvd.subway.persistence.RouteSectionRepository;
import com.solvd.subway.service.StationService;
import com.solvd.subway.service.impl.StationServiceImpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RouteSectionRepositoryImpl implements RouteSectionRepository {

	private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

	public static RouteSection mapRow(ResultSet rs) throws SQLException {
		RouteSection routeSection = new RouteSection();

		routeSection.setId(rs.getInt("id"));
		routeSection.setMinutes(rs.getInt("minutes"));

		StationService stationService = new StationServiceImpl();
		Station departureStation = stationService.geById(rs.getInt("departure_station_id"));
		Station destinationStation = stationService.geById(rs.getInt("destination_station_id"));
		routeSection.setDepartureStation(departureStation);
		routeSection.setDestinationStation(destinationStation);

		return routeSection;
	}

	public static List<RouteSection> mapRows(ResultSet rs) throws SQLException {
		List<RouteSection> routeSections = new ArrayList<>();
		while (rs.next()) {
			routeSections.add(mapRow(rs));
		}
		return routeSections;
	}

	@Override
	public List<RouteSection> getAll() {
		List<RouteSection> routeSections = null;
		Connection connection = CONNECTION_POOL.getConnection();

		try (PreparedStatement ps = connection.prepareStatement(
			"SELECT * FROM route_sections;")) {
			ResultSet resultSet = ps.executeQuery();
			routeSections = mapRows(resultSet);

		} catch (SQLException e) {
			throw new RuntimeException("Unable to get all route sections.", e);
		} finally {
			CONNECTION_POOL.releaseConnection(connection);
		}
		return routeSections;
	}

	@Override
	public void create(RouteSection routeSection) {
		Connection connection = CONNECTION_POOL.getConnection();

		try (PreparedStatement ps = connection.prepareStatement(
			"INSERT INTO route_sections (departure_station_id, destination_station_id, minutes, zone_name) VALUES (?, ?, ?, ?);", Statement.RETURN_GENERATED_KEYS)) {
			ps.setInt(1, routeSection.getDepartureStation().getId());
			ps.setInt(2, routeSection.getDestinationStation().getId());
			ps.setInt(3, routeSection.getMinutes());
			ps.setString(4, routeSection.getZone().getName());
			ps.executeUpdate();

			ResultSet resultSet = ps.getGeneratedKeys();
			while (resultSet.next()) {
				routeSection.setId(resultSet.getInt(1));
			}

		} catch (SQLException e) {
			throw new RuntimeException("Unable to create route section.", e);
		} finally {
			CONNECTION_POOL.releaseConnection(connection);
		}
	}

	@Override
	public void updateTime(Integer routeSectionId, Integer minutes) {
		Connection connection = CONNECTION_POOL.getConnection();

		try (PreparedStatement ps = connection.prepareStatement(
			"UPDATE route_sections SET minutes = ? WHERE id = ?;")) {
			ps.setInt(1, minutes);
			ps.setInt(2, routeSectionId);
			ps.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException("Unable to update time.", e);
		} finally {
			CONNECTION_POOL.releaseConnection(connection);
		}
	}
}