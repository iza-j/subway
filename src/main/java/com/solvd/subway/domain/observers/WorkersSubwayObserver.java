package com.solvd.subway.domain.observers;

import com.solvd.subway.domain.networkelements.Line;
import com.solvd.subway.domain.networkelements.Subway;
import com.solvd.subway.domain.workers.Worker;

import java.util.ArrayList;
import java.util.List;

public class WorkersSubwayObserver extends SubwayObserver {


	private List<Worker> subscribers;

	public WorkersSubwayObserver(Subway subway) {
		super(subway);
		this.subscribers = new ArrayList<>();
	}

	@Override
	public void onNewLine(Line line) {
		StringBuilder message = new StringBuilder()
			.append("Line ")
			.append(line.getName())
			.append(" will start operating shortly. Check out intranet for possible reassignment information.");
		subscribers.forEach(subscriber -> System.out.println(message));
		System.out.println();
	}

	@Override
	public void onNewWorker(Worker worker) {
		StringBuilder message = new StringBuilder()
			.append("Let's welcome ")
			.append(worker.getName())
			.append(", our new ")
			.append(worker.getJob().getTitle())
			.append("! ");
		subscribers.forEach(subscriber -> System.out.println(message));
		System.out.println();
	}

	public void subscribe(Worker subscriber) {
		this.subscribers.add(subscriber);
	}

	public void unsubscribe(Worker subscriber) {
		this.subscribers.remove(subscriber);
	}
}