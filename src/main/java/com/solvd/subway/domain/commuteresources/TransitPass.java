package com.solvd.subway.domain.commuteresources;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.solvd.subway.domain.networkelements.Zone;
import jakarta.xml.bind.annotation.*;

import java.math.BigDecimal;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"name", "outermostZone", "numberOfDays", "price"})
@JsonRootName(value = "transit_pass")
public class TransitPass {

	@XmlElement
	private String name;
	@XmlElement
	private Zone outermostZone;
	@XmlElement
	private Integer numberOfDays;
	@XmlElement
	private BigDecimal price;

	@JsonGetter
	public String getName() {
		return name;
	}

	@JsonSetter
	public void setName(String name) {
		this.name = name;
	}

	@JsonGetter
	public Zone getOutermostZone() {
		return outermostZone;
	}

	@JsonSetter
	public void setOutermostZone(Zone outermostZone) {
		this.outermostZone = outermostZone;
	}

	@JsonGetter
	public Integer getNumberOfDays() {
		return numberOfDays;
	}

	@JsonSetter
	public void setNumberOfDays(Integer numberOfDays) {
		this.numberOfDays = numberOfDays;
	}

	@JsonGetter
	public BigDecimal getPrice() {
		return price;
	}

	@JsonSetter
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
}