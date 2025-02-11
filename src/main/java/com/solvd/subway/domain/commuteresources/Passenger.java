package com.solvd.subway.domain.commuteresources;

import jakarta.xml.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Date;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"id", "name", "discount", "credit", "transitPass", "passValidityStartingDay"})
public class Passenger {

	@XmlAttribute
	private Integer id;

	@XmlElement
	private String name;

	@XmlElement
	private Discount discount;

	@XmlElement
	private BigDecimal credit;

	@XmlElement
	private TransitPass transitPass;

	@XmlElement
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