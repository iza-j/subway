package com.solvd.subway;

import com.solvd.subway.networkelements.Station;

public class Main {

    public static void main(String[] args) {

        // https://launchbylunch.com/posts/2014/Feb/16/sql-naming-conventions/#naming-conventions

        System.out.println("\nTickets please!!");

        // HW #12
        Station ratajskiego = new Station(2101, "plac Cyryla Ratajskiego");
        Station ogrody = new Station(7777777, "Ogrody");
        Station poznanGlowny = new Station(104, "Poznań Główny....... POZNAŃ MAIN STATION!!!");
        Station srodka = new Station(666, "Rondo Śródka");
    }
}