package com.github.evgenylizogubov.directory.web;

import com.github.evgenylizogubov.directory.model.Heading;

public class HeadingTestData {
    public static final MatcherFactory.Matcher<Heading> HEADING_MATCHER = MatcherFactory.usingEqualsComparator(Heading.class);
    
    public static final int NAPITKI_ID = 1;
    public static final int GAZ_ID = 2;
    public static final int KVASI_ID = 3;
    public static final int ZHIVYYE_ID = 4;
    public static final int MORTVYYE_ID = 5;
    public static final int EDA_ID = 6;
    public static final int AVTOMOBILI_ID = 9;
    
    public static final Heading napitki = new Heading(NAPITKI_ID, "Напитки");
    public static final Heading gaz = new Heading(GAZ_ID, "Газированные");
    public static final Heading kvasi = new Heading(KVASI_ID, "Квасы");
    public static final Heading zhivyye = new Heading(ZHIVYYE_ID, "Живые");
    public static final Heading mortvyye = new Heading(MORTVYYE_ID, "Неживые");
    public static final Heading eda = new Heading(EDA_ID, "Еда");
    public static final Heading avtomobili = new Heading(AVTOMOBILI_ID, "Автомобили");
    
}
