package com.solvd.subway.domain.networkelements;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonSetter;
import jakarta.xml.bind.annotation.*;

import java.math.BigDecimal;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"name", "baseFareOneMinute"})
@JsonRootName(value = "zone")
public class Zone {

	@XmlElement
	private String name;
	@XmlElement
	private BigDecimal baseFareOneMinute;

	@JsonGetter
	public String getName() {
		return name;
	}

	@JsonSetter
	public void setName(String name) {
		this.name = name;
	}

	@JsonGetter
	public BigDecimal getBaseFareOneMinute() {
		return baseFareOneMinute;
	}

	@JsonSetter
	public void setBaseFareOneMinute(BigDecimal baseFareOneMinute) {
		this.baseFareOneMinute = baseFareOneMinute;
	}
}