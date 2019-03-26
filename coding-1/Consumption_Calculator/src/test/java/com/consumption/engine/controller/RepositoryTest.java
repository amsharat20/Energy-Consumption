package com.consumption.engine.controller;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Optional;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.consumption.engine.dao.ConsumptionDAO;
import com.consumption.engine.dao.ConsumptionDAOImpl;
import com.consumption.engine.pojos.PlaceInfo;
import com.consumption.engine.services.ConsumptionService;



@DataJpaTest 
@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.AUTO_CONFIGURED)
public class RepositoryTest {
	
	@Autowired
	private ConsumptionDAO repository;
	
	
	@Test
	@Ignore
	public void testAdd() {
		PlaceInfo place = new PlaceInfo();
		place.setCounterid(13);
		place.setCountername("Insert");
		repository.addCounter(place);
	}

}
