package com.solvd.subway.domain.commuteresources;

import com.solvd.subway.domain.networkelements.Zone;
import jakarta.xml.bind.annotation.*;

import java.math.BigDecimal;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"name", "outermostZone", "numberOfDays", "price"})
public class TransitPass {

	@XmlElement
	private String name;

	@XmlElement
	private Zone outermostZone;

	@XmlElement
	private Integer numberOfDays;

	@XmlElement
	private BigDecimal price;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Zone getOutermostZone() {
		return outermostZone;
	}

	public void setOutermostZone(Zone outermostZone) {
		this.outermostZone = outermostZone;
	}

	public Integer getNumberOfDays() {
		return numberOfDays;
	}

	public void setNumberOfDays(Integer numberOfDays) {
		this.numberOfDays = numberOfDays;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
}