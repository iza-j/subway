package com.solvd.subway.domain.observers;

import com.solvd.subway.domain.commuteresources.Passenger;
import com.solvd.subway.domain.networkelements.Line;
import com.solvd.subway.domain.networkelements.Subway;
import com.solvd.subway.domain.workers.Worker;

import java.util.ArrayList;
import java.util.List;

public class PassengersSubwayObserver extends SubwayObserver {

	private List<Passenger> subscribers;

	public PassengersSubwayObserver(Subway subway) {
		super(subway);
		this.subscribers = new ArrayList<>();
	}

	@Override
	public void onNewLine(Line line) {
		for (Passenger subscriber : subscribers) {
			StringBuilder message = new StringBuilder()
				.append("Good morning, ")
				.append(subscriber.getName())
				.append(". ")
				.append(subway.getName())
				.append(" is changing! Visit our website for more information about line ")
				.append(line.getName())
				.append(".");
			System.out.println(message);
		}
		System.out.println();
	}

	@Override
	public void onNewWorker(Worker worker) {
	}

	public void subscribe(Passenger subscriber) {
		this.subscribers.add(subscriber);
	}

	public void unsubscribe(Passenger subscriber) {
		this.subscribers.remove(subscriber);
	}
}