package com.consumption.engine.pojos;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModelProperty;


@Entity
@Table(name="consumptioninfo")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"},
allowGetters = true)
public class ConsumptionInfo {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int keyrand;

	@Column(name="id")
	private int id;

	@Column(name="consumption")
	private double consumption;

	@ApiModelProperty(hidden = true)
	@Column(name="tmsmtp",nullable = true, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	private Date createDateTime;

	
	
	public ConsumptionInfo() {}
	
	public ConsumptionInfo(int id, double consumption) {
		super();
		this.id=id;
		this.consumption=consumption;
	}

	@ApiModelProperty(hidden = true)
	public int getKeyrand() {
		return keyrand;
	}

	public void setKeyrand(int keyrand) {
		this.keyrand = keyrand;
	}

	public double getConsumption() {
		return consumption;
	}

	public void setConsumption(double consumption) {
		this.consumption = consumption;
	}

	public int getId() {
		return id;
	}

	public Date getCreateDateTime() {
		return createDateTime;
	}

	public void setCreateDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
	}

	public void setId(int id) {
		this.id = id;
	}

}
