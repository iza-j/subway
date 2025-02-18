package com.solvd.subway;

import com.solvd.subway.domain.commuteresources.Discount;
import com.solvd.subway.domain.commuteresources.Passenger;
import com.solvd.subway.domain.commuteresources.TransitPass;
import com.solvd.subway.domain.networkelements.*;
import com.solvd.subway.domain.workers.Job;
import com.solvd.subway.domain.workers.Worker;
import com.solvd.subway.persistence.Config;
import com.solvd.subway.service.*;
import com.solvd.subway.service.impl.*;
import com.solvd.subway.service.parser.JAXBParser;
import com.solvd.subway.service.parser.JacksonParser;
import jakarta.xml.bind.JAXBException;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static com.solvd.subway.persistence.Config.*;
import static com.solvd.subway.service.Helper.viewDetails;
import static com.solvd.subway.service.impl.RouteSectionServiceImpl.printDetails;
import static com.solvd.subway.service.impl.WorkerServiceImpl.printDetails;
import static com.solvd.subway.service.parser.DiscountSAXParser.parseDiscount;

public class Main {

	public static void main(String[] args) throws JAXBException, IOException, InvocationTargetException, IllegalAccessException {

		// https://launchbylunch.com/posts/2014/Feb/16/sql-naming-conventions/#naming-conventions

		System.out.println("\nTickets please!!\n");

		///////////////////////////////////////////////////////////////////////////////////////////////////
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

		String oldName = workerService.getById(4, workerService.getAll()).getName();
		workerService.updateName(4, oldName.concat("-Donosik"));
		workerService.getAll().forEach(w -> printDetails(w));
		System.out.println();

		JobService jobService = new JobServiceImpl();
		Job newJob = new Job();
		newJob.setTitle("controller");
//		jobService.create(newJob);
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

		Worker newWorker = new Worker();
		newWorker.setName("Andrzej DDoSik");
		newWorker.setJob(newJob);
		newWorker.setLine(newLine);
		workerService.create(newWorker);
		printDetails(newWorker);
		workerService.delete(newWorker);
		System.out.println();

		StationService stationService = new StationServiceImpl();
		Station newStation = new Station();
		newStation.setName("Rynek Łazarski");
		stationService.create(newStation);
		stationService.getAll().forEach(s -> System.out.println(s.getId() + ": " + s.getName()));
		System.out.println(stationService.getById(4).getName());
		System.out.println();

		RouteSectionService routeSectionService = new RouteSectionServiceImpl();
		RouteSection newRouteSection = new RouteSection();
		newRouteSection.setDepartureStation(stationService.getById(14));
		newRouteSection.setDestinationStation(stationService.getById(15));
		newRouteSection.setMinutes(2);
		Zone newZone = new Zone();
		newZone.setName("A");
		newRouteSection.setZone(newZone);

		printDetails(newRouteSection);
		routeSectionService.create(newRouteSection);
		routeSectionService.updateTime(newRouteSection.getId(), 1);
		routeSectionService.getAll().stream().filter(Objects::isNull).forEach(r -> printDetails(r));
		System.out.println();

		lineService.viewSections("2");
		System.out.println();
		lineService.viewSections("12");
		System.out.println();
		lineService.viewSections("18");
		System.out.println();

		///////////////////////////////////////////////////////////////////////////////////////////////////
		// mysql HW #4

		// SAX parser
		Discount saxDiscount = parseDiscount();
		System.out.println(saxDiscount.getName() + " --- " + saxDiscount.getReductionPercentage());
		System.out.println();

		///////////////////////////////////////////////////////////////////////////////////////////////////
		// mysql HW #5

		// java to xml	=	marshalling
		// xml to java	=	unmarshalling

		JAXBParser jaxbParser = new JAXBParser();

		// Zone
		Zone zone = new Zone();
		zone.setName("A");
		zone.setBaseFareOneMinute(BigDecimal.valueOf(0.17));
		jaxbParser.marshal(zone);

		// Discount
		Discount discount = new Discount();
		discount.setName("Teacher");
		discount.setReductionPercentage(BigDecimal.valueOf(33));
		jaxbParser.marshal(discount);

		// TransitPass
		TransitPass transitPass = new TransitPass();
		transitPass.setName("Yearly A");
		transitPass.setNumberOfDays(366);
		transitPass.setPrice(BigDecimal.valueOf(702));
		transitPass.setOutermostZone(zone);
		jaxbParser.marshal(transitPass);

		// Passenger
		Passenger passenger = new Passenger();
		passenger.setId(1);
		passenger.setName("Michał Dętka");
		passenger.setDiscount(discount);
		passenger.setCredit(BigDecimal.valueOf(27.92));
		passenger.setTransitPass(transitPass);
		passenger.setPassValidityStartingDay(Date.valueOf("2024-10-01"));
		jaxbParser.marshal(passenger);

		// Job
		Job job = new Job();
		job.setTitle("programmer");
		jaxbParser.marshal(job);

		// Station
		Station station = new Station();
		station.setId(45);
		station.setName("Rondo Starołęka");
		Station station2 = new Station();
		station2.setId(46);
		station2.setName("Rondo Śródka");
		jaxbParser.marshal(station2);

		// RouteSection "id", "departureStation", "destinationStation", "minutes", "zone"})
		RouteSection routeSection = new RouteSection();
		routeSection.setId(1);
		routeSection.setDepartureStation(newStation);
		routeSection.setDestinationStation(station);
		routeSection.setMinutes(2);
		routeSection.setZone(zone);
		RouteSection routeSection2 = new RouteSection();
		routeSection2.setId(2);
		routeSection2.setDepartureStation(station);
		routeSection2.setDestinationStation(station2);
		routeSection2.setMinutes(3);
		routeSection2.setZone(zone);
		jaxbParser.marshal(routeSection2);

		// Line
		Line line =	new Line();
		line.setName("13");
		line.setRouteSections(new ArrayList<>(Arrays.asList(routeSection, routeSection2)));
		jaxbParser.marshal(line);

		// Worker
		Worker worker = new Worker();
		worker.setId(17);
		worker.setName("Irena Rooibosik");
		worker.setHourlyWage(BigDecimal.valueOf(99.54));
		worker.setJob(job);
		worker.setLine(line);
		worker.setStation(station);
		jaxbParser.marshal(worker);

		// Subway
		Subway subway = new Subway();
		subway.setName("Metro Poznańskie");
		subway.setPassengers(new ArrayList<>(Arrays.asList(passenger)));
		subway.setLines(new ArrayList<>(Arrays.asList(line, newLine)));
		subway.setWorkers(new ArrayList<>(Arrays.asList(worker, newWorker)));
		jaxbParser.marshal(subway);

		// unmarshalling
		printDetails(jaxbParser.unmarshalWorker());
		System.out.println(jaxbParser.unmarshalPassenger().getName());
		System.out.println();

		///////////////////////////////////////////////////////////////////////////////////////////////////
		// jackson HW

		// for jackson import issues: File > Invalidate Caches > Invalidate and Restart

		JacksonParser jacksonParser = new JacksonParser();

		// marshal with jackson
		List<Object> objectList = new ArrayList<>(Arrays.asList(discount, passenger, transitPass, line, routeSection, station, subway, zone, job, worker));
		for (Object object : objectList) {
			try {
				jacksonParser.marshal(object);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		// unmarshal with jackson
		Subway jacksonSubway = jacksonParser.unmarshalSubway("src/main/resources/json/subway.json");
		Subway jacksonSubway2 = jacksonParser.unmarshalSubway("src/main/resources/json/my_subway.json");
		jacksonSubway.getWorkers().forEach(i -> printDetails(i));
		jacksonSubway2.getWorkers().forEach(i -> printDetails(i));
		System.out.println();

		///////////////////////////////////////////////////////////////////////////////////////////////////
		// mybatis HW

		// always set automapping to false
		// <![CDATA[  use cdata to escape '<', '>', and '&' in xmls  ]]>

		ZoneService zoneService = new ZoneServiceImpl();
		for (Zone i : zoneService.getAll()) {
			viewDetails(i);
		}
		System.out.println();

		for (Station i : stationService.getAll()) {
			viewDetails(i);
		}
		System.out.println();

		for (RouteSection i : routeSectionService.getAll()) {
			System.out.println(new StringBuilder()
				.append(i.getId())
				.append(" --- ")
				.append(i.getDepartureStation().getName())
				.append(" --- ")
				.append(i.getDestinationStation().getName())
				.append(" --- ")
				.append(i.getMinutes())
				.append(" --- ")
				.append(i.getZone())
			);
		}
		System.out.println();

		for (Line i : lineService.getAll()) {
			System.out.println(i.getName());
		}
		System.out.println();
	}
}