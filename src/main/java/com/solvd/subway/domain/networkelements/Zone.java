package com.solvd.subway.domain.networkelements;

import jakarta.xml.bind.annotation.*;

import java.math.BigDecimal;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"name", "baseFareOneMinute"})
public class Zone {

	@XmlElement
	private String name;

	@XmlElement
	private BigDecimal baseFareOneMinute;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getBaseFareOneMinute() {
		return baseFareOneMinute;
	}

	public void setBaseFareOneMinute(BigDecimal baseFareOneMinute) {
		this.baseFareOneMinute = baseFareOneMinute;
	}
}