package com.ppmoney.edu.calculator;

/**
 * 还款条件
 */
public class PaymentCondition {
    /**
     * 待收余额（计算本金）
     */
    protected double remainingBalance;
    /**
     * 利率
     */
    protected double rate;

    /**
     * 利率方式：0->年化率；1->日化率
     */
    protected int rateType;
    /**
     * 还款期限
     */
    protected int numberPayment;
    /**
     * 期限方式（月、天），每月算30天，一年算360天：0->月;1->天
     */
    protected int numberPaymentType;

    public static PaymentCondition newBuilder() {
        return new PaymentCondition();
    }

    public double getRemainingBalance() {
        return remainingBalance;
    }

    public PaymentCondition setRemainingBalance(double remainingBalance) {
        this.remainingBalance = remainingBalance;
        return this;
    }

    public double getRate() {
        return rate;
    }

    public PaymentCondition setRate(double rate) {
        this.rate = rate;
        return this;
    }

    public int getRateType() {
        return rateType;
    }

    public PaymentCondition setRateType(int rateType) {
        this.rateType = rateType;
        return this;
    }

    public int getNumberPayment() {
        return numberPayment;
    }

    public PaymentCondition setNumberPayment(int numberPayment) {
        this.numberPayment = numberPayment;
        return this;
    }

    public int getNumberPaymentType() {
        return numberPaymentType;
    }

    public PaymentCondition setNumberPaymentType(int numberPaymentType) {
        this.numberPaymentType = numberPaymentType;
        return this;
    }
}
