package com.solvd.subway.persistence;

import com.solvd.subway.domain.networkelements.Line;

import java.math.BigDecimal;

public interface LineRepository {

	BigDecimal getBaseFare(String name);

	void create(Line line);

	void delete(Line line);
}