package com.consumption.engine.pojos;

public class Villages {

	private String name;
    private double consumption;
    
    public Villages() {
    	
    }
    
 public Villages(String name, double consumption ) {
	     super();
    	this.name =name;
    	this.consumption =consumption;
    }
    
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getconsumption() {
		return consumption;
	}

	public void setconsumption(double consumption) {
		this.consumption = consumption;
	}

	@Override
	public String toString() {
		return "Villages [name=" + name + ", consumption=" + consumption + "]";
	}

	
	
}
