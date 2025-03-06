package com.solvd.subway;

import com.solvd.subway.domain.commuteresources.Discount;
import com.solvd.subway.domain.commuteresources.Passenger;
import com.solvd.subway.domain.commuteresources.TransitPass;
import com.solvd.subway.domain.networkelements.*;
import com.solvd.subway.domain.observers.PassengersSubwayObserver;
import com.solvd.subway.domain.observers.WorkersSubwayObserver;
import com.solvd.subway.domain.workers.Job;
import com.solvd.subway.domain.workers.Worker;
import com.solvd.subway.persistence.Config;
import com.solvd.subway.service.*;
import com.solvd.subway.service.impl.*;
import com.solvd.subway.service.parser.DiscountSAXParser;
import com.solvd.subway.service.parser.JAXBParser;
import com.solvd.subway.service.parser.JacksonParser;
import com.solvd.subway.service.parser.Parser;
import com.solvd.subway.service.parser.factory.ParserFactory;
import com.solvd.subway.service.parser.factory.abstractfactory.ParserFactoryProducer;
import jakarta.xml.bind.JAXBException;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static com.solvd.subway.persistence.Config.*;
import static com.solvd.subway.service.impl.RouteSectionServiceImpl.printDetails;
import static com.solvd.subway.service.impl.WorkerServiceImpl.printDetails;

public class Main {

	public static void main(String[] args) throws JAXBException, IOException {

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

		Worker newWorker = new Worker();
		newWorker.setName("Andrzej DDoSik");
		newWorker.setJob(newJob);
		newWorker.setLine(lineService.getByName("12"));
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
		DiscountSAXParser discountSAXParser = new DiscountSAXParser();
		Discount saxDiscount = discountSAXParser.unmarshalDiscount("src/main/resources/xml/discount.xml");
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
		// CTRL + SHIFT + U = toggle case in intellij

		// zones
		ZoneService zoneService = new ZoneServiceImpl();
		Zone zone1 = zoneService.getById("A");
		zone1.setName("C");
		zoneService.create(zone1);
		List<Zone> zones = zoneService.getAll();

		// stations
		Station station1 = stationService.getById(1);
		stationService.create(station1);
		List<Station> stations = stationService.getAll();

		// route sections
		RouteSection routeSection1 = routeSectionService.getById(1);
		routeSectionService.create(routeSection1);
		routeSectionService.updateTime(1, 345);
		List<RouteSection> routeSections = routeSectionService.getAll();

		// lines
		Line line1 = lineService.getByName("12");
		line1.setName("77");
		lineService.create(line1);
		List<Line> lines = lineService.getAll();

		// jobs
		jobService.create(job);
		List<Job> jobs = jobService.getAll();

		// workers
		List<Worker> workers = workerService.getAll();
		Worker worker1 = workers.get(1);
		worker1.setName("Zygmunt Kłosik");
		worker1.setJob(job);
		workerService.create(worker1);

		///////////////////////////////////////////////////////////////////////////////////////////////////
		// design patterns HW

		// builder
		Passenger passenger2 = Passenger.builder()
			.name("Genowefa Kolosik")
			.discount(discount)
			.transitPass(transitPass)
			.passValidityStartingDay(Date.valueOf("2025-07-10"))
			.build();

		Worker worker2 = Worker.builder()
			.name("Lolo Chaosik")
			.hourlyWage(BigDecimal.valueOf(42.0))
			.job(job)
			.build();

		// abstract factory + factory
		ParserFactory jsonParserFactory = ParserFactoryProducer.getFactory("json");
		ParserFactory xmlParserFactory = ParserFactoryProducer.getFactory("xml");

		Parser jacksonParser1 = jsonParserFactory.createParser("jackson");
		Parser jaxbParser1 = xmlParserFactory.createParser("jaxb");
		Parser saxParser1 = xmlParserFactory.createParser("sax");

		Discount jacksonDiscount1 = jacksonParser1.unmarshalDiscount("src/main/resources/json/discount.json");
		Discount jaxbDiscount1 = jaxbParser1.unmarshalDiscount("src/main/resources/discount.xml");
		Discount saxDiscount1 = saxParser1.unmarshalDiscount("src/main/resources/discount.xml");

		// listener/observer
		PassengersSubwayObserver passengersSubwayObserver = new PassengersSubwayObserver(subway);
		passengersSubwayObserver.subscribe(passenger);
		passengersSubwayObserver.subscribe(passenger2);

		WorkersSubwayObserver workersSubwayObserver = new WorkersSubwayObserver(subway);
		workersSubwayObserver.subscribe(worker);
		workersSubwayObserver.subscribe(worker1);
		workersSubwayObserver.subscribe(worker2);

		System.out.println();
		subway.addWorker(worker2);
		subway.addLine(line);
	}
}