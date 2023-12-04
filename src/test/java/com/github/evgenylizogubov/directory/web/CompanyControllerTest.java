package com.github.evgenylizogubov.directory.web;

import com.github.evgenylizogubov.directory.model.Building;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.List;

import static com.github.evgenylizogubov.directory.web.BuildingTestData.*;
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
        perform(MockMvcRequestBuilders.get(REST_URL_SLASH + "in-area")
                .params(getMultiValueMap(building1, building2, true)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(COMPANY_MATCHER.contentJson(coca, ochakovo, pepsi));
    }
    
    @Test
    void getAllInRectangleArea() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL_SLASH + "in-area")
                .params(getMultiValueMap(building3, building2, false)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(COMPANY_MATCHER.contentJson(nestle, ochakovo, pepsi));
    }
    
    private MultiValueMap<String, String> getMultiValueMap(Building startPoint, Building endPoint, boolean isCircleArea) {
        int radius = (int) Math.ceil(Math.sqrt(Math.pow(endPoint.getLatitude() - startPoint.getLatitude(), 2) +
                Math.pow(endPoint.getLongitude() - startPoint.getLongitude(), 2)));
        
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("latitude", startPoint.getLatitude().toString());
        params.add("longitude", startPoint.getLongitude().toString());
        params.add("radius", String.valueOf(radius));
        params.add("isCircleArea", String.valueOf(isCircleArea));
        return params;
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
}
