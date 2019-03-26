package com.consumption.engine.dao;

import com.consumption.engine.pojos.ConsumptionInfo;
import com.consumption.engine.pojos.ConsumptionReport;
import com.consumption.engine.pojos.PlaceInfo;

public interface ConsumptionDAO {
	
	public void addCounter(PlaceInfo place);

	public PlaceInfo findById(int id);

	public void addConsumption(ConsumptionInfo consumptionInfo);

	public ConsumptionReport getAll(String duration);
	
	

}
