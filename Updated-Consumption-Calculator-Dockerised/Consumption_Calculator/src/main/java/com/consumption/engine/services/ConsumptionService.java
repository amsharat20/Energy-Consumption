package com.consumption.engine.services;

import org.springframework.stereotype.Service;

import com.consumption.engine.pojos.ConsumptionInfo;
import com.consumption.engine.pojos.ConsumptionReport;
import com.consumption.engine.pojos.PlaceInfo;

@Service
public interface ConsumptionService {
	
	public PlaceInfo addCounter(PlaceInfo place);

	public PlaceInfo findById(int id);

	public ConsumptionInfo addConsumption(ConsumptionInfo consumptionInfo);

	public ConsumptionReport getAll(String duration);
	
	

}
