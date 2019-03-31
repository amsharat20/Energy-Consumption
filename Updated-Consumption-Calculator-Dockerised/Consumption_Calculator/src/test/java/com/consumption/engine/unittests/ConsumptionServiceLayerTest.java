package com.consumption.engine.unittests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import com.consumption.engine.dao.ConsumptionDAO;
import com.consumption.engine.pojos.ConsumptionInfo;
import com.consumption.engine.pojos.ConsumptionReport;
import com.consumption.engine.pojos.PlaceInfo;
import com.consumption.engine.services.ConsumptionServiceImpl;



@RunWith(MockitoJUnitRunner.class)
public class ConsumptionServiceLayerTest {
	@Spy
	private ConsumptionServiceImpl consumptionServiceImpl;
	@Mock
	private ConsumptionDAO consumptionDAO;
	@Mock
	private PlaceInfo placeInfo;
	@Mock
	private ConsumptionInfo consumptionInfo;
	@Mock
	private ConsumptionReport consumptionReport;


	@Test
	public void verifyfindById() throws Exception {
		Mockito.doReturn(placeInfo).when(consumptionServiceImpl).findById(5);
		placeInfo = consumptionServiceImpl.findById(5);
		Mockito.verify(consumptionServiceImpl).findById(5);
	}

	@Test
	public void verifyCalculateConsumption() throws Exception {
		Mockito.doReturn(consumptionReport).when(consumptionServiceImpl).getAll("24h");
		consumptionReport = consumptionServiceImpl.getAll("24h");
		Mockito.verify(consumptionServiceImpl).getAll("24h");
	}

	@Test
	public void verifyAddCounter() throws Exception {
		Mockito.doReturn(placeInfo).when(consumptionServiceImpl).addCounter(placeInfo);
		consumptionServiceImpl.addCounter(placeInfo);
		Mockito.verify(consumptionServiceImpl).addCounter(placeInfo);
	}

	@Test
	public void verifyAddConsumption() throws Exception {
		Mockito.doReturn(consumptionInfo).when(consumptionServiceImpl).addConsumption(consumptionInfo);
		consumptionInfo = consumptionServiceImpl.addConsumption(consumptionInfo);
		Mockito.verify(consumptionServiceImpl).addConsumption(consumptionInfo);
	}

}
