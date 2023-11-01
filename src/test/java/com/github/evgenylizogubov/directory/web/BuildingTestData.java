package com.github.evgenylizogubov.directory.web;

import com.github.evgenylizogubov.directory.model.Building;
import com.github.evgenylizogubov.directory.model.Coordinates;

public class BuildingTestData {
    public final static MatcherFactory.Matcher<Building> BUILDING_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Building.class, "coordinates.building");
    public final static Coordinates coor1 = new Coordinates(111, 111);
    public final static Coordinates coor2 = new Coordinates(222, 222);
    public final static Coordinates coor3 = new Coordinates(333, 333);
    public final static Building building1 = new Building(1, "ул. Пушкина, д. Колотушкина", coor1);
    public final static Building building2 = new Building(2, "ул. Ленина, д. 666", coor2);
    public final static Building building3 = new Building(3, "ул. Школьная, д. 13", coor3);
}
