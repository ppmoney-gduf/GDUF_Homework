package com.ppmoney.edu.calculator;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 到期本息
 */
public class CalcInFine extends PaymentCalculator {

    public CalcInFine(PaymentCondition paymentCondition) {
        super(paymentCondition);
    }

    public List<PaymentSchedule> advance(Date fromDate) {
        List<PaymentSchedule> scheduleList = new ArrayList<>();
        PaymentSchedule paymentSchedule = new PaymentSchedule();

        //获取利息
        double interest;

        if (Constant.DAILY == paymentCondition.getNumberPaymentType() && Constant.DAILYRATE == paymentCondition.getRateType()) {
            interest = paymentCondition.getRemainingBalance() * paymentCondition.getRate() * paymentCondition.getNumberPayment();
        } else if (Constant.DAILY == paymentCondition.getNumberPaymentType() && Constant.ANNUALRATE == paymentCondition.getRateType()) {
            interest = paymentCondition.getRemainingBalance() * paymentCondition.getRate() * paymentCondition.getNumberPayment() / 360;
        } else if (Constant.MONTHLY == paymentCondition.getNumberPaymentType() && Constant.DAILYRATE == paymentCondition.getRateType()) {
            interest = paymentCondition.getRemainingBalance() * paymentCondition.getRate() * paymentCondition.getNumberPayment() * 30;
        } else {
            interest = paymentCondition.getRemainingBalance() * paymentCondition.getRate();
        }

        DateTime dateTime = new DateTime(fromDate);
        paymentSchedule.setPayDate(paymentCondition.getNumberPaymentType() == 1 ? dateTime.plusDays(paymentCondition.getNumberPayment()) : dateTime.plusDays(paymentCondition.getNumberPayment() * 30));
        paymentSchedule.setInterest(interest);
        paymentSchedule.setPrincipal(paymentCondition.getRemainingBalance());
        paymentSchedule.setBalance(paymentCondition.getRemainingBalance() + interest);

        scheduleList.add(paymentSchedule);

        return scheduleList;
    }

    public static void main(String[] args) {
        PaymentCalculator paymentCalculator = new CalcInFine(PaymentCondition.newBuilder()
                .setRemainingBalance(10000)
                .setNumberPayment(12)
                .setNumberPaymentType(0)
                .setRate(0.12)
                .setRateType(0));
        List<PaymentSchedule> scheduleList = paymentCalculator.advance(new Date());

        System.out.println(scheduleList);

    }
}
