package com.solvd.subway.domain.commuteresources;

import jakarta.xml.bind.annotation.*;

import java.math.BigDecimal;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"name", "reductionPercentage"})
public class Discount {

	@XmlElement
	private String name;

	@XmlElement
	private BigDecimal reductionPercentage;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getReductionPercentage() {
		return reductionPercentage;
	}

	public void setReductionPercentage(BigDecimal reductionPercentage) {
		this.reductionPercentage = reductionPercentage;
	}
}