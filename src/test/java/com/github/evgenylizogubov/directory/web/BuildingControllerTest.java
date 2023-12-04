package com.github.evgenylizogubov.directory.web;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static com.github.evgenylizogubov.directory.web.BuildingTestData.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class BuildingControllerTest extends AbstractControllerTest {
    private final String REST_URL = "/api/building";
    private final String REST_URL_SLASH = REST_URL + "/";
    
    @Test
    void getAll() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(BUILDING_MATCHER.contentJson(building2, building1, building3));
    }
    
    @Test
    void getAllByAddress() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL_SLASH + "by-address")
                .param("address", building1.getAddress()))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(BUILDING_MATCHER.contentJson(List.of(building1)));
    }
}
