package com.ppmoney.edu.calculator;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.joda.time.DateTime;

/**
 * 还款计划
 */
public class PaymentSchedule {
    /**
     * 还款日期
     */
    @JsonFormat(shape=JsonFormat.Shape.STRING)
    private DateTime payDate;
    /**
     * 利息
     */
    private double interest;
    /**
     * 奖励
     */
    private double bonusInterest;
    /**
     * 本金
     */
    private double principal;
    /**
     * 待收余额
     */
    private double balance;


    public DateTime getPayDate() {
        return payDate;
    }

    public void setPayDate(DateTime payDate) {
        this.payDate = payDate;
    }

    public double getInterest() {
        return interest;
    }

    public void setInterest(double interest) {
        this.interest = interest;
    }

    public double getBonusInterest() {
        return bonusInterest;
    }

    public void setBonusInterest(double bonusInterest) {
        this.bonusInterest = bonusInterest;
    }

    public double getPrincipal() {
        return principal;
    }

    public void setPrincipal(double principal) {
        this.principal = principal;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "PaymentSchedule{" +
                "payDate=" + payDate +
                ", interest=" + interest +
                ", bonusInterest=" + bonusInterest +
                ", principal=" + principal +
                ", balance=" + balance +
                '}';
    }
}
