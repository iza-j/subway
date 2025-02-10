package com.solvd.subway.persistence.impl;

import com.solvd.subway.domain.workers.Job;
import com.solvd.subway.persistence.ConnectionPool;
import com.solvd.subway.persistence.JobRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JobRepositoryImpl implements JobRepository {

	private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();

	public static Job mapRow(ResultSet rs) throws SQLException {
		Job job = new Job();
		job.setTitle(rs.getString("title"));
		return job;
	}

	public static List<Job> mapRows(ResultSet rs) throws SQLException {
		List<Job> jobs = new ArrayList<>();
		while (rs.next()) {
			jobs.add(mapRow(rs));
		}
		return jobs;
	}

	@Override
	public List<Job> getAll() {
		List<Job> jobs;
		Connection connection = CONNECTION_POOL.getConnection();

		try (PreparedStatement preparedStatement = connection.prepareStatement(
			"SELECT * FROM jobs;")) {
			ResultSet rs = preparedStatement.executeQuery();
			jobs = mapRows(rs);

		} catch (SQLException e) {
			throw new RuntimeException("Unable to get all jobs.", e);
		} finally {
			CONNECTION_POOL.releaseConnection(connection);
		}
		return jobs;
	}

	@Override
	public void create(Job job) {
		Connection connection = CONNECTION_POOL.getConnection();

		try (PreparedStatement ps = connection.prepareStatement(
			"INSERT INTO jobs (title) VALUE (?);")) {
			ps.setString(1, job.getTitle());
			ps.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException("Unable to create job.", e);
		} finally {
			CONNECTION_POOL.releaseConnection(connection);
		}
	}
}