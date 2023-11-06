package com.github.evgenylizogubov.directory.web;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.List;

import static com.github.evgenylizogubov.directory.web.BuildingTestData.building1;
import static com.github.evgenylizogubov.directory.web.BuildingTestData.building2;
import static com.github.evgenylizogubov.directory.web.CompanyTestData.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CompanyControllerTest extends AbstractControllerTest {
    private final String REST_URL_SLASH = "/api/company/";
    
    @Test
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL_SLASH + 1))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(COMPANY_MATCHER.contentJson(coca));
    }
    
    @Test
    void getByNameLike() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL_SLASH + "by-name")
                .param("name", coca.getName()))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(COMPANY_MATCHER.contentJson(List.of(coca)));
    }
    
    @Test
    void getAllByBuildingId() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL_SLASH + "by-building-id")
                .param("buildingId", building2.getId().toString()))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(COMPANY_MATCHER.contentJson(ochakovo, pepsi));
    }
    
    @Test
    void getAllByHeading() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL_SLASH + "by-heading")
                .param("heading", napitki.getName()))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(COMPANY_MATCHER.contentJson(List.of(coca)));
    }
    
    @Test
    void getAllInCircleArea() throws Exception {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("latitude", building1.getLatitude().toString());
        params.add("longitude", building1.getLongitude().toString());
        params.add("radius", "170");
        
        perform(MockMvcRequestBuilders.get(REST_URL_SLASH + "in-circle-area")
                .params(params))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(COMPANY_MATCHER.contentJson(coca, ochakovo, pepsi));
    }
    
    @Test
    void getAllInRectangleArea() throws Exception {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("point1Latitude", String.valueOf(building2.getLatitude() - 1));
        params.add("point1Longitude", String.valueOf(building2.getLongitude() - 1));
        params.add("point2Latitude", String.valueOf(building2.getLatitude() + 1));
        params.add("point2Longitude", String.valueOf(building2.getLongitude() + 1));
        
        perform(MockMvcRequestBuilders.get(REST_URL_SLASH + "in-rectangle-area")
                .params(params))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(COMPANY_MATCHER.contentJson(ochakovo, pepsi));
    }
    
    @Test
    void getAllByNameAndHeading() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL_SLASH + "by-name-and-heading")
                .param("companyName", ochakovo.getName())
                .param("headingName", napitki.getName()))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(COMPANY_MATCHER.contentJson(List.of(ochakovo)));
    }
    
    @Test
    void getAllByNameAndHeadingNotFount() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL_SLASH + "by-name-and-heading")
                .param("companyName", ochakovo.getName())
                .param("headingName", "NotFound"))
                .andExpect(status().isNotFound());
    }
}
