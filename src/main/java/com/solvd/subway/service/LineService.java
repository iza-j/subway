package com.solvd.subway.service;

import com.solvd.subway.domain.networkelements.Line;

import java.math.BigDecimal;

public interface LineService {

	BigDecimal getBaseFare(String name);

	void create(Line line);

	void delete(Line line);
}