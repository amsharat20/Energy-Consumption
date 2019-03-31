package com.consumption.engine.integration;


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

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
import com.consumption.engine.pojos.ConsumptionInfo;
import com.consumption.engine.pojos.ConsumptionReport;
import com.consumption.engine.pojos.PlaceInfo;
import com.consumption.engine.pojos.Villages;
import com.consumption.engine.services.ConsumptionServiceImpl;

@RunWith(SpringRunner.class)
@WebMvcTest(ConsumptionController.class )
public class TestConsumptionEngineGet24 {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	ConsumptionDAO consumptionDAO;

	@MockBean
	ConsumptionServiceImpl consumptionService;

	@MockBean
	PlaceInfo placeInfo;
	
	@MockBean
	ConsumptionInfo consumptionInfo;
	
	@Test
	public void testGetConsumption() throws Exception {
		
		ConsumptionReport consumptionReport = new ConsumptionReport();
		Villages village1 = new Villages("one",25);
		Villages village2 = new Villages("two",25);
		List<Villages> villages = new ArrayList<Villages>();
		villages.add(village1);
		villages.add(village2);
		consumptionReport.setVillages(villages);
			
			Mockito.when(consumptionService.getAll("24h")).thenReturn(consumptionReport);

			RequestBuilder requestGet = MockMvcRequestBuilders.get("/api/v1/consumption_report/{duration}","24h").accept(MediaType.APPLICATION_JSON);
		    mockMvc.perform(requestGet).andExpect(status().isOk());
			
		}
	}
	
	
