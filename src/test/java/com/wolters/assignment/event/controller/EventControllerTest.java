package com.wolters.assignment.event.controller;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class EventControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setUp() {

    }

    @Test
    public void getEventByIdShouldReturnEventBelongToRequestedId() throws Exception {
        mockMvc.perform(get("/events/" + 1001L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value( 1001))
                .andExpect(jsonPath("$.description").value( "Rent Advance"))
                .andExpect(jsonPath("$.eventType").value( "Transactions"))
                .andExpect(jsonPath("$.customerName").value( "Hemant"));
    }

    @Test
    public void getEventByIdShouldReturn404ResponseIfNoRecordFound() throws Exception {
        mockMvc.perform(get("/events/" + 100100L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void getEventByIdShouldReturnListOfAllEvents() throws Exception {
        mockMvc.perform(get("/events")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", Matchers.hasSize(8)));
    }

    @Test
    public void getEventShouldReturnListOfEventsBelongToCustomerAndEventType() throws Exception {
        mockMvc.perform(get("/events/customers/" + "Hemant" + "/types/" + "Transactions")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", Matchers.hasSize(2)));
    }

    @Test
    public void getEventShouldReturnShouldReturnEmptyListIfCustomerNotFound() throws Exception {
        mockMvc.perform(get("/events/customers/" + "CustomerNotExist" + "/types/" + "Transactions")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", Matchers.hasSize(0)));
    }

    @Test
    public void getEventShouldReturnShouldReturnEmptyListIfCustomerFoundButEventNotPresentForThatCustomer() throws Exception {
        mockMvc.perform(get("/events/customers/" + "Hemant" + "/types/" + "Custom")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", Matchers.hasSize(0)));
    }

}
