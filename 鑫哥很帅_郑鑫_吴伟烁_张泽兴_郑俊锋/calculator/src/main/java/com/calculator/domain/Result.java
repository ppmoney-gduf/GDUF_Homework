package com.calculator.domain;

public class Result {
	
	private int num;				//期号
	private double total;		//代收
	private double principal;	//本金
	private double interest;	//利息
	private String date;			//到期日期
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public double getPrincipal() {
		return principal;
	}
	public void setPrincipal(double principal) {
		this.principal = principal;
	}
	public double getInterest() {
		return interest;
	}
	public void setInterest(double interst) {
		this.interest = interst;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	@Override
	public String toString() {
		return "Result [num=" + num + ", total=" + total + ", principal="
				+ principal + ", interest=" + interest + ", date=" + date + "]";
	}
	
}
