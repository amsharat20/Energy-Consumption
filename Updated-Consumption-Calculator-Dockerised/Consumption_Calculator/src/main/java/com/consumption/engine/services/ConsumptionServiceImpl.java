package com.consumption.engine.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.consumption.engine.dao.ConsumptionDAO;
import com.consumption.engine.pojos.ConsumptionInfo;
import com.consumption.engine.pojos.ConsumptionReport;
import com.consumption.engine.pojos.PlaceInfo;

@Service
public class ConsumptionServiceImpl implements ConsumptionService {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ConsumptionDAO consumptionDAO;
	
	public void setConsumptionDAO(ConsumptionDAO consumptionDAO) {
		this.consumptionDAO = consumptionDAO;
	}

	@Transactional
	public PlaceInfo addCounter(PlaceInfo place) {
		logger.info("Calling DAO Layer to make an entry for counter");
		this.consumptionDAO.addCounter(place);
		return place;
				
	}

	@Override
	@Transactional
	public PlaceInfo findById(int id) {
		logger.info("Calling DAO layer to fetch details");
		return consumptionDAO.findById(id);
	}

	@Override
	@Transactional
	public ConsumptionInfo addConsumption(ConsumptionInfo consumptionInfo) {
		logger.info("Calling DAO Layer to make an entry for amount");
		this.consumptionDAO.addConsumption(consumptionInfo);
		return consumptionInfo;
		
	}

	@Override
	@Transactional
	public ConsumptionReport getAll(String duration) {
		logger.info("Calling DAO Layer for all reports");
		return consumptionDAO.getAll(duration);
	}
	
}
