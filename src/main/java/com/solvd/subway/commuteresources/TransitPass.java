package com.solvd.subway.commuteresources;

import com.solvd.subway.networkelements.Zone;

import java.math.BigDecimal;

public class TransitPass {

    private String name;
    private Zone outermostZone;
    private long numberOfDays;
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

    public long getNumberOfDays() {
        return numberOfDays;
    }

    public void setNumberOfDays(long numberOfDays) {
        this.numberOfDays = numberOfDays;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}