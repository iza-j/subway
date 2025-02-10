package com.solvd.subway;

import com.solvd.subway.domain.networkelements.Line;
import com.solvd.subway.domain.networkelements.RouteSection;
import com.solvd.subway.domain.networkelements.Station;
import com.solvd.subway.domain.networkelements.Zone;
import com.solvd.subway.domain.workers.Job;
import com.solvd.subway.domain.workers.Worker;
import com.solvd.subway.persistence.Config;
import com.solvd.subway.service.*;
import com.solvd.subway.service.impl.*;

import static com.solvd.subway.persistence.Config.*;
import static com.solvd.subway.service.impl.RouteSectionServiceImpl.printDetails;
import static com.solvd.subway.service.impl.WorkerServiceImpl.printDetails;

public class Main {

	public static void main(String[] args) {

		// https://launchbylunch.com/posts/2014/Feb/16/sql-naming-conventions/#naming-conventions

		System.out.println("\nTickets please!!\n");

		// mysql HW #3

		// domain:		 contains the business models
		// persistence:	 interacts with db, retrieves data for the service layer
		// service:		 manages business logic, does operations on the retrieved data
		// service interface -> service implementation -> persistence interface -> persistence implementation

		System.out.println(DRIVER.getValue());
		System.out.println(URL.getValue());
		System.out.println(USERNAME.getValue());
		System.out.println(PASSWORD.getValue());
		System.out.println(POOL_SIZE.getValue());

		System.out.println("\n" + Config.class.getClassLoader().getResource("config.properties") + "\n");

		WorkerService workerService = new WorkerServiceImpl();
		Worker newWorker = new Worker();
		newWorker.setName("Andrzej DDoSik");
		workerService.create(newWorker);
		printDetails(newWorker);
		workerService.delete(newWorker);
		System.out.println();

		String oldName = workerService.getById(4, workerService.getAll()).getName();
		workerService.updateName(4, oldName.concat("-Donosik"));
		workerService.getAll().forEach(w -> printDetails(w));
		System.out.println();

		JobService jobService = new JobServiceImpl();
		Job newJob = new Job();
		newJob.setTitle("controller");
		jobService.create(newJob);
		jobService.getAll().forEach(j -> System.out.println(j.getTitle()));
		System.out.println();

		LineService lineService = new LineServiceImpl();
		System.out.println(lineService.getBaseFare("2"));
		System.out.println(lineService.getBaseFare("12"));
		System.out.println(lineService.getBaseFare("18"));
		System.out.println();

		Line newLine = new Line();
		newLine.setName("42");
		lineService.create(newLine);
		lineService.delete(newLine);
		System.out.println();

		StationService stationService = new StationServiceImpl();
		Station newStation = new Station();
		newStation.setName("Rynek Łazarski");
		stationService.create(newStation);
		stationService.getAll().forEach(s -> System.out.println(s.getId() + ": " + s.getName()));
		System.out.println(stationService.geById(4).getName());
		System.out.println();

		RouteSectionService routeSectionService = new RouteSectionServiceImpl();
		RouteSection newRouteSection = new RouteSection();
		newRouteSection.setDepartureStation(stationService.geById(14));
		newRouteSection.setDestinationStation(stationService.geById(15));
		newRouteSection.setMinutes(2);
		Zone newZone = new Zone();
		newZone.setName("A");
		newRouteSection.setZone(newZone);

		printDetails(newRouteSection);
		routeSectionService.create(newRouteSection);
		routeSectionService.updateTime(newRouteSection.getId(), 1);
		routeSectionService.getAll().forEach(r -> printDetails(r));
	}
}