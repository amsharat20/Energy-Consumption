package com.consumption.engine.pojos;

import java.util.List;

public class ConsumptionReport {
	
	private List<Villages> villages;

    public void setVillages(List<Villages> villages){
        this.villages = villages;
    }
    public List<Villages> getVillages(){
        return this.villages;
    }
	
}
