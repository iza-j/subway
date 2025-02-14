package com.solvd.subway.domain.commuteresources;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonSetter;
import jakarta.xml.bind.annotation.*;

import java.math.BigDecimal;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"name", "reductionPercentage"})
@JsonRootName(value = "discount")
public class Discount {

	@XmlElement
	private String name;
	@XmlElement
	private BigDecimal reductionPercentage;

	@JsonGetter
	public String getName() {
		return name;
	}

	@JsonSetter
	public void setName(String name) {
		this.name = name;
	}

	@JsonGetter
	public BigDecimal getReductionPercentage() {
		return reductionPercentage;
	}

	@JsonSetter
	public void setReductionPercentage(BigDecimal reductionPercentage) {
		this.reductionPercentage = reductionPercentage;
	}
}