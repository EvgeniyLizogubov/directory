package com.github.evgenylizogubov.directory.web;

import com.github.evgenylizogubov.directory.model.Building;

public class BuildingTestData {
    public final static MatcherFactory.Matcher<Building> BUILDING_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Building.class, "coordinates.building");
    public final static Building building1 = Building.builder().id(1).address("ул. Пушкина, д. Колотушкина")
            .latitude(111).longitude(111).build();
    public final static Building building2 = Building.builder().id(2).address("ул. Ленина, д. 666")
            .latitude(222).longitude(222).build();
    public final static Building building3 = Building.builder().id(3).address("ул. Школьная, д. 13")
            .latitude(333).longitude(333).build();
}
