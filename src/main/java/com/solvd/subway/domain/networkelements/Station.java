package com.solvd.subway.domain.networkelements;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonSetter;
import jakarta.xml.bind.annotation.*;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"id", "name"})
@JsonRootName(value = "station")
public class Station {

	@XmlElement
	private Integer id;
	@XmlElement
	private String name;

	@JsonGetter
	public Integer getId() {
		return id;
	}

	@JsonSetter
	public void setId(Integer id) {
		this.id = id;
	}

	@JsonGetter
	public String getName() {
		return name;
	}

	@JsonSetter
	public void setName(String name) {
		this.name = name;
	}
}