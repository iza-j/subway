package com.solvd.subway.domain.observers;

import com.solvd.subway.domain.networkelements.Line;
import com.solvd.subway.domain.networkelements.Subway;
import com.solvd.subway.domain.workers.Worker;

public abstract class SubwayObserver {

	// subway is protected so it may be used by children classes
	protected final Subway subway;

	public SubwayObserver(Subway subway) {
		this.subway = subway;
		subway.addObserver(this);
	}

	public abstract void onNewLine(Line line);

	public abstract void onNewWorker(Worker worker);
}