package com.github.evgenylizogubov.directory.web;

import com.github.evgenylizogubov.directory.model.Company;
import com.github.evgenylizogubov.directory.model.Heading;

import java.util.List;
import java.util.Set;

import static com.github.evgenylizogubov.directory.web.BuildingTestData.*;

public class CompanyTestData {
    public static final MatcherFactory.Matcher<Company> COMPANY_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Company.class, "headings.parent");
    
    public static final int COCA_ID = 1;
    public static final int PEPSI_ID = 2;
    public static final int NESTLE_ID = 3;
    public static final int OCHAKOVO_ID = 4;
    
    public static final Heading napitki = new Heading(1, "Напитки", null);
    public static final Heading gaz = new Heading(2, "Газированные", napitki);
    public static final Heading kvasi = new Heading(3, "Квасы", gaz);
    public static final Heading dead = new Heading(5, "Неживые", kvasi);
    public static final Heading food = new Heading(6, "Еда", null);
    
    public static final Company coca = new Company(COCA_ID, "Coca-cola", Set.of("123-123-123", "456-456-456"), building1, List.of(gaz, napitki));
    public static final Company pepsi = new Company(PEPSI_ID, "Pepsi", Set.of("321-321-321", "654-654-654"), building2, List.of(gaz));
    public static final Company nestle = new Company(NESTLE_ID, "Nestle", Set.of("789-789-789"), building3, List.of(food));
    public static final Company ochakovo = new Company(OCHAKOVO_ID, "Ochakovo", Set.of("777-777-777"), building2, List.of(kvasi, dead));
}
