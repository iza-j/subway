package com.solvd.subway.commuteresources;

import java.math.BigDecimal;

public class Discount {

    private String name;
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