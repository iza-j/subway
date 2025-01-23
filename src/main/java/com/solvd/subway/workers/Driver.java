package com.solvd.subway.workers;

import com.solvd.subway.networkelements.Line;

public class Driver extends Worker {

	private Line line;

	public Line getLine() {
		return line;
	}

	public void setLine(Line line) {
		this.line = line;
	}
}
