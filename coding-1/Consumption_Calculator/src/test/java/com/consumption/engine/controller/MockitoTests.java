package com.consumption.engine.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.consumption.engine.pojos.PlaceInfo;
import com.consumption.engine.services.ConsumptionServiceImpl;

@RunWith(SpringRunner.class)
@WebMvcTest(ConsumptionController.class)
public class MockitoTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ConsumptionServiceImpl consumptionService;

	@Test
	@Ignore
	public void addCounterTest() throws Exception {

		PlaceInfo product = new PlaceInfo();
		product.setCounterid(55);
		product.setCountername("test");
		RequestBuilder request = MockMvcRequestBuilders
				.post("/api/v1/add_counter")
				.param("counterid", "4")
				.param("countername", "ganesha")
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = (MvcResult) mockMvc.perform(request)
				.andExpect(status().is2xxSuccessful());

	}

	@Test
	@Ignore
	public void getCounter() throws Exception {

		RequestBuilder request = MockMvcRequestBuilders
				.get("/api/v1/counter/45")
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = (MvcResult) mockMvc.perform(request)
				.andExpect(status().isOk());
	}

}






