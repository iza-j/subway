package com.solvd.subway.domain.commuteresources;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonSetter;
import jakarta.xml.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Date;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"id", "name", "discount", "credit", "transitPass", "passValidityStartingDay"})
@JsonRootName(value = "passenger")
public class Passenger {

	@XmlElement
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
	@JsonFormat(pattern = "YYYY-MM-DD'T'HH:MM:SSZ") // ISO 8601
	private Date passValidityStartingDay;

	public static Builder builder() {
		return new Passenger.Builder(new Passenger());
	}

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

	@JsonGetter
	public Discount getDiscount() {
		return discount;
	}

	@JsonSetter
	public void setDiscount(Discount discount) {
		this.discount = discount;
	}

	@JsonGetter
	public BigDecimal getCredit() {
		return credit;
	}

	@JsonSetter
	public void setCredit(BigDecimal credit) {
		this.credit = credit;
	}

	@JsonGetter
	public TransitPass getTransitPass() {
		return transitPass;
	}

	@JsonSetter
	public void setTransitPass(TransitPass transitPass) {
		this.transitPass = transitPass;
	}

	@JsonGetter
	public Date getPassValidityStartingDay() {
		return passValidityStartingDay;
	}

	@JsonSetter
	public void setPassValidityStartingDay(Date passValidityStartingDay) {
		this.passValidityStartingDay = passValidityStartingDay;
	}

	public static class Builder {

		private final Passenger passenger;

		public Builder(Passenger passenger) {
			this.passenger = passenger;
		}

		public Builder name(String name) {
			this.passenger.name = name;
			return this;
		}

		public Builder discount(Discount discount) {
			this.passenger.discount = discount;
			return this;
		}

		public Builder credit(BigDecimal credit) {
			this.passenger.credit = credit;
			return this;
		}

		public Builder transitPass(TransitPass transitPass) {
			this.passenger.transitPass = transitPass;
			return this;
		}

		public Builder passValidityStartingDay(Date passValidityStartingDay) {
			this.passenger.passValidityStartingDay = passValidityStartingDay;
			return this;
		}

		public Passenger build() {
			return passenger;
		}
	}
}