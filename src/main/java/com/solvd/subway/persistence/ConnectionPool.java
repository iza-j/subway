package com.solvd.subway.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.IntStream.range;

public class ConnectionPool {

	// ConnectionPool class initializes its one and only instance
	// https://refactoring.guru/design-patterns/singleton
	private static ConnectionPool instance;

	private final List<Connection> connections;

	// constructor for singleton is private
	private ConnectionPool() {

		// check whether you can get the Driver class from the value of DRIVER constant
		// stop program's execution if you can't
		try {
			Class.forName(Config.DRIVER.getValue());
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Unable to find Driver class.", e);
		}

		// convert pool size from String to int
		int connectionPoolSize = Integer.parseInt(Config.POOL_SIZE.getValue());

		// set Connection List's capacity (improves performance by avoiding unnecessary resizing)
		this.connections = new ArrayList<>(connectionPoolSize);

		// create Connections and add them to the List
		range(0, connectionPoolSize).forEach(i -> connections.add(createConnection()));
	}

	private Connection createConnection() {
		Connection connection;
		try {
			connection = DriverManager.getConnection(Config.URL.getValue(), Config.USERNAME.getValue(), Config.PASSWORD.getValue());
		} catch (SQLException e) {
			throw new RuntimeException("Unable to create connection.", e);
		}
		return connection;
	}

	public static synchronized ConnectionPool getInstance() {
		if (instance == null) {
			instance = new ConnectionPool();
		}
		return instance;
	}

	public Connection getConnection() {
		// only 1 thread at a time can access a synchronized block
		synchronized (connections) {
			if (connections.isEmpty()) {
				try {
					while (connections.isEmpty()) {
						connections.wait();
					}
				} catch (InterruptedException e) {
					throw new RuntimeException("Unable to get connection.", e);
				}
			}
			return connections.removeLast();
		}
	}

	public void releaseConnection(Connection connection) {
		synchronized (connections) {
			connections.add(connection);
			connections.notifyAll();
		}
	}
}