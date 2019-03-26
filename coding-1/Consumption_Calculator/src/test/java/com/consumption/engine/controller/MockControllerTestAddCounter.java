package com.consumption.engine.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.consumption.engine.controller.ConsumptionController;
import com.consumption.engine.pojos.PlaceInfo;
import com.consumption.engine.services.ConsumptionService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class MockControllerTestAddCounter {

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void contextLoads() throws JSONException {	

		PlaceInfo product = new PlaceInfo();
		product.setCounterid(46);
		product.setCountername("kop");


		String result = this.restTemplate.postForObject("/api/v1/add_counter",product, String.class);
		System.out.println(result);
		
		if(result.contains("successfully ")) {
			assertTrue(true);
		} else {
			if (result.contains("Id already exists")){
			assert(true);
			}
			else assert(false);
		}


	}

}
