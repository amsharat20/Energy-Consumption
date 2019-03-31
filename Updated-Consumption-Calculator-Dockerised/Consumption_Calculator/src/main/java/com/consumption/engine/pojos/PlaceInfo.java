package com.consumption.engine.pojos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;



@Entity
@Table(name="placeinfo")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class PlaceInfo {

	
	@Id
	@Column(name="id")
	@Min(1)
	@Max(1000)
	@NotNull(message = "Counter Id canot be empty")
	private int counterid;
	
	@Column(name="name")
	@NotEmpty(message = "Counter Name canot be empty")
	private String countername;
	
	
	public PlaceInfo() {
		
	}
	
     public PlaceInfo(int counterid , String countername) {
    	 super();
		this.counterid =counterid;
		this.countername = countername;
	}
     
	public int getCounterid() {
		return counterid;
	}

	public void setCounterid(int counterid) {
		this.counterid = counterid;
	}

	public String getCountername() {
		return countername;
	}

	public void setCountername(String countername) {
		this.countername = countername;
	}
	
}
