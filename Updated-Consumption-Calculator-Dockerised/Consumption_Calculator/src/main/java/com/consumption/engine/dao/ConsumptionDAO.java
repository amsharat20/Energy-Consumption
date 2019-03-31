package com.consumption.engine.dao;

import com.consumption.engine.pojos.ConsumptionInfo;
import com.consumption.engine.pojos.ConsumptionReport;
import com.consumption.engine.pojos.PlaceInfo;

public interface ConsumptionDAO {
	
	public PlaceInfo addCounter(PlaceInfo place);

	public PlaceInfo findById(int id);

	public ConsumptionInfo addConsumption(ConsumptionInfo consumptionInfo);

	public ConsumptionReport getAll(String duration);

}
