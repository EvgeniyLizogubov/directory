package com.github.evgenylizogubov.directory.web;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.github.evgenylizogubov.directory.web.HeadingTestData.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class HeadingControllerTest extends AbstractControllerTest {
    private final String REST_URL = "/api/heading";
    private final String REST_URL_SLASH = REST_URL + "/";
    
    @Test
    void getAllRoots() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(HEADING_MATCHER.contentJson(napitki, eda, avtomobili));
    }
    
    @Test
    void getAllChildrenByParentId() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL_SLASH + NAPITKI_ID))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(HEADING_MATCHER.contentJson(gaz, kvasi, zhivyye, mortvyye));
    }
}
