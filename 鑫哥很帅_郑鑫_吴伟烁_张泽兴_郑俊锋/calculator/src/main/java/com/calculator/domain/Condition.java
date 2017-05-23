package com.calculator.domain;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Condition {
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date date;			//借出日期
	private double money;	//借出金额
	private int limit;			//借出期限
	private double rate;	//利率
	private String method;			//还款方式
	
	private int limitIsDay;		//借出期限是否按天算,0是按月算，1按天算
	private int rateIsDay;		//是否是日利率，0是年利率，1是日利率
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public double getRate() {
		return rate;
	}
	public void setRate(double rate) {
		this.rate = rate;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public int getLimitIsDay() {
		return limitIsDay;
	}
	public void setLimitIsDay(int limitIsDay) {
		this.limitIsDay = limitIsDay;
	}
	public int getRateIsDay() {
		return rateIsDay;
	}
	public void setRateIsDay(int rateIsDay) {
		this.rateIsDay = rateIsDay;
	}
	
}
