package com.solvd.subway.commuteresources;

import java.math.BigDecimal;
import java.util.Date;

public class Passenger {

    private Integer id;
    private String name;
    private Discount discount;
    private BigDecimal credit;
    private TransitPass transitPass;
    private Date passValidityStartingDay;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Discount getDiscount() {
        return discount;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }

    public BigDecimal getCredit() {
        return credit;
    }

    public void setCredit(BigDecimal credit) {
        this.credit = credit;
    }

    public TransitPass getTransitPass() {
        return transitPass;
    }

    public void setTransitPass(TransitPass transitPass) {
        this.transitPass = transitPass;
    }

    public Date getPassValidityStartingDay() {
        return passValidityStartingDay;
    }

    public void setPassValidityStartingDay(Date passValidityStartingDay) {
        this.passValidityStartingDay = passValidityStartingDay;
    }
}
