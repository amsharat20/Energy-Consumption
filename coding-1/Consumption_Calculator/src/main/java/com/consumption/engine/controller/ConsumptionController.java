package com.consumption.engine.controller;

import javax.validation.Valid;

import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.consumption.engine.errorhandling.CustomException;
import com.consumption.engine.errorhandling.ErrorResponse;
import com.consumption.engine.errorhandling.RecordNotFoundException;
import com.consumption.engine.pojos.ConsumptionInfo;
import com.consumption.engine.pojos.ConsumptionReport;
import com.consumption.engine.pojos.PlaceInfo;
import com.consumption.engine.services.ConsumptionService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@RequestMapping("/api/v1")
@Api(value="Energy Consumption Controller APIs")

@ControllerAdvice
@Controller
public class ConsumptionController extends ResponseEntityExceptionHandler {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ConsumptionService consumptionService;

	@PostMapping("/add_counter")
	@ApiOperation("Add counter with the id associated with the counter")

	@ExceptionHandler(Exception.class )
	public ResponseEntity<Object> addCounter(@Valid @RequestBody PlaceInfo placeInfo)  {
		logger.info("Request Body is received. Starting to make entry");
		try {
			consumptionService.addCounter(placeInfo);
		} catch (DataIntegrityViolationException e) {
			ErrorResponse error = new ErrorResponse("Id already exists ! Please check Counter Id");
			return new ResponseEntity<Object>(error, HttpStatus.CONFLICT);
		} catch (Exception e) {
			ErrorResponse error = new ErrorResponse("Entry Updation Failed. Server may be down. Try after sometime");
			return new ResponseEntity<Object>(error, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Object>("Entry done successfully ", HttpStatus.CREATED);
	}


	@GetMapping(value = "/counter/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation("Get counter with the id associated with the counter ")


	public ResponseEntity<Object> getCounterById(@PathVariable("id") int id) throws RecordNotFoundException {
		logger.info("Fetching Place with id " + id);
		PlaceInfo placeInfo = consumptionService.findById(id);
		if (placeInfo == null) {
			throw new RecordNotFoundException("Counter Id is not Found. Please Check the counter id again ");
		}
		return new ResponseEntity<Object>(placeInfo, HttpStatus.OK);
	}

	@PostMapping("/counter_callback")
	@ApiOperation("Add amount associated with a partuclar counter")


	public ResponseEntity<?> addConsumption( @Valid @RequestBody ConsumptionInfo consumptionInfo ) {
		logger.info("Received request body to add amount to counter");

		try {
			consumptionService.addConsumption(consumptionInfo);
		} catch (ConstraintViolationException e) {
			ErrorResponse error = new ErrorResponse("Check if counter ID exists ");
			return new ResponseEntity<Object>(error, HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			ErrorResponse error = new ErrorResponse("Entry Updation Failed. Server may be down. Try after sometime ");
			return new ResponseEntity<Object>(error, HttpStatus.INTERNAL_SERVER_ERROR);
		}				
		return new ResponseEntity<Object>("Added amount to counter", HttpStatus.CREATED);
	}

	@GetMapping(value = "/consumption_report/{duration}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation("Calculate energy consumption for 24hours")

	public ResponseEntity<Object> getAllReport(@PathVariable("duration") String duration) throws RecordNotFoundException {

		boolean match = duration.matches(".*\\d.*");
		ConsumptionReport consumptionReport = null;
		if(match) {
			logger.info("Received time to calculate energy consumption ");
			
			consumptionReport=consumptionService.getAll(duration);
			
			if (consumptionReport == null) {
				throw new RecordNotFoundException("Counter Id is not Found. Please Check the counter id again ");
			}
		} else {
			throw new CustomException("Check the passed string. Format is 24h ");
		}
		return new ResponseEntity<Object>(consumptionReport,HttpStatus.OK);
	}
}
