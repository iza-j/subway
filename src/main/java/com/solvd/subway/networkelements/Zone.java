package com.solvd.subway.networkelements;

import java.math.BigDecimal;

public class Zone {

	private String name;
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
