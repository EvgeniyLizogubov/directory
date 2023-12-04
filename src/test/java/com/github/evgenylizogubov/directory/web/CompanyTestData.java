package com.github.evgenylizogubov.directory.web;

import com.github.evgenylizogubov.directory.model.Company;

import java.util.List;
import java.util.Set;

import static com.github.evgenylizogubov.directory.web.BuildingTestData.*;
import static com.github.evgenylizogubov.directory.web.HeadingTestData.*;

public class CompanyTestData {
    public static final MatcherFactory.Matcher<Company> COMPANY_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Company.class, "headings", "building");
    
    public static final int COCA_ID = 1;
    public static final int PEPSI_ID = 2;
    public static final int NESTLE_ID = 3;
    public static final int OCHAKOVO_ID = 4;
    
    public static final Company coca = Company.builder().id(COCA_ID).name("Coca-cola").phoneNumbers(Set.of("123-123-123", "456-456-456"))
            .building(building1).headings(List.of(gaz, napitki)).build();
    public static final Company pepsi = Company.builder().id(PEPSI_ID).name("Pepsi").phoneNumbers(Set.of("321-321-321", "654-654-654"))
            .building(building2).headings(List.of(gaz)).build();
    public static final Company nestle = Company.builder().id(NESTLE_ID).name("Nestle").phoneNumbers(Set.of("789-789-789"))
            .building(building3).headings(List.of(eda)).build();
    public static final Company ochakovo = Company.builder().id(OCHAKOVO_ID).name("Ochakovo").phoneNumbers(Set.of("777-777-777"))
            .building(building2).headings(List.of(kvasi, mortvyye)).build();
}
