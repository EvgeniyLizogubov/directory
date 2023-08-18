package com.github.EvgenyLizogubov.directory.web;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.List;

import static com.github.EvgenyLizogubov.directory.web.CompanyTestData.*;
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
    void getByName() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL_SLASH + "by-name")
                .param("name", "Coca-cola"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(COMPANY_MATCHER.contentJson(coca));
    }
    
    @Test
    void getAllByBuilding() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL_SLASH + "building")
                .param("address", "ул. Ленина, д. 666"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(COMPANY_MATCHER.contentJson(ochakovo, pepsi));
    }
    
    @Test
    void getAllByHeading() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL_SLASH + "heading")
                .param("heading", "Напитки"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(COMPANY_MATCHER.contentJson(List.of(coca)));
    }
    
    @Test
    void getAllInArea() throws Exception {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("x", "100");
        params.add("y", "100");
        params.add("radius", "250");
        
        perform(MockMvcRequestBuilders.get(REST_URL_SLASH + "in-area")
                .params(params))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(COMPANY_MATCHER.contentJson(coca, pepsi, ochakovo));
    }
    
    @Test
    void getAllByNameAndHeading() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL_SLASH + "by-name-and-heading")
                .param("companyName", "Ochakovo")
                .param("headingName", "Напитки"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(COMPANY_MATCHER.contentJson(List.of(ochakovo)));
    }
}
