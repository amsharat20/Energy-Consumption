package com.consumption.engine.integration;


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
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
import com.consumption.engine.pojos.PlaceInfo;
import com.consumption.engine.services.ConsumptionServiceImpl;

@RunWith(SpringRunner.class)
@WebMvcTest(ConsumptionController.class )
public class TestConsumptionEngineGet {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	ConsumptionDAO consumptionDAO;

	@MockBean
	ConsumptionServiceImpl consumptionService;

	@MockBean
	PlaceInfo placeInfo;
	
	@Test
	public void testGetCounter() throws Exception {
		
		PlaceInfo place1 = new PlaceInfo(1, "Sharat");
		
		Mockito.when(consumptionService.findById(1)).thenReturn(place1);

		RequestBuilder requestGet = MockMvcRequestBuilders.get("/api/v1/counter/{id}",1).accept(MediaType.APPLICATION_JSON);
	    mockMvc.perform(requestGet).andExpect(status().isOk());
		
	}
	
	@Test
	public void testGetCounterNotFound() throws Exception {
		
		PlaceInfo place1 = new PlaceInfo(1, "Sharat");
		
		Mockito.when(consumptionService.findById(4)).thenReturn(place1);

		RequestBuilder requestGet = MockMvcRequestBuilders.get("/api/v1/counter/{id}",1).accept(MediaType.APPLICATION_JSON);
	    mockMvc.perform(requestGet).andExpect(status().isNotFound());
		
	}
	
}
