package com.consumption.engine.integration;


import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.consumption.engine.controller.ConsumptionController;
import com.consumption.engine.dao.ConsumptionDAO;
import com.consumption.engine.pojos.ConsumptionInfo;
import com.consumption.engine.pojos.PlaceInfo;
import com.consumption.engine.services.ConsumptionService;
import com.fasterxml.jackson.databind.ObjectMapper;


@RunWith(SpringRunner.class)
@WebMvcTest(ConsumptionController.class )
public class TestConsumptionEnginePost {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	ConsumptionDAO consumptionDAO;

	@MockBean
	ConsumptionService consumptionService;

	@MockBean
	PlaceInfo placeInfo;


	@Test
	public void testPostPlaceInfo() throws Exception {
		PlaceInfo place = new PlaceInfo(1, "Sharat");
		when(consumptionDAO.findById(1)).thenReturn(place);
		RequestBuilder request = MockMvcRequestBuilders.post("/api/v1/add_counter")
				.content(asJsonString(place))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		mockMvc.perform(request).andExpect(status().is2xxSuccessful());
	}

	@Test
	public void testPostConsumption() throws Exception {
		ConsumptionInfo consumptionInfo = new ConsumptionInfo(1, 110.5);
		RequestBuilder request = MockMvcRequestBuilders.post("/api/v1/counter_callback")
				.content(asJsonString(consumptionInfo))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		mockMvc.perform(request).andExpect(status().is2xxSuccessful());
	}


	public static String asJsonString(final Object obj) {
		try {
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonContent = mapper.writeValueAsString(obj);
			System.out.println(jsonContent);
			return jsonContent;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
