package com.ppmoney.edu.calculator;

import org.joda.time.DateTime;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 月还息到期还本
 */
public class CalcInMonthlyPrincipalFine extends PaymentCalculator {

    public CalcInMonthlyPrincipalFine(PaymentCondition paymentCondition) {
        super(paymentCondition);
    }

    /**
     * 目前只支持按月，每月30日，年天数360计算
     *
     * @param fromDate 借款日期
     * @return 还款计划
     */
    public List<PaymentSchedule> advance(Date fromDate) {
        List<PaymentSchedule> paymentScheduleList = new ArrayList<>();
        double interest;
        DateTime nextTime;
        DateTime firstCurrent = new DateTime(fromDate);
        int days = 30;
        int year = firstCurrent.getYear();
        int month = firstCurrent.getMonthOfYear();
        for (int i = 1; i <= paymentCondition.getNumberPayment(); i++) {
            PaymentSchedule paymentSchedule = new PaymentSchedule();
            //获取利息 本金*(年化率％/360)*（每月实际天数）
            interest = paymentCondition.getRemainingBalance() * (paymentCondition.getRate() / 360) * days;
            interest = new BigDecimal(String.valueOf(interest)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            month += 1;
            //跨年处理
            if (month > 12) {
                year = year + 1;
                month = 1;
            }

            if (i == paymentCondition.getNumberPayment()) {
                //最后一个月，需要加上本金
                paymentSchedule.setBalance(interest + paymentCondition.getRemainingBalance());
                paymentSchedule.setPrincipal(paymentCondition.getRemainingBalance());
            } else {
                paymentSchedule.setBalance(interest);
            }
            //跨年跨月处理
            DateTime nextYear = new DateTime(year, month, firstCurrent.getDayOfMonth(), firstCurrent.getHourOfDay(), firstCurrent.getHourOfDay(), firstCurrent.getMinuteOfHour(), firstCurrent.getSecondOfMinute());
            nextTime = new DateTime(nextYear.getYear(), nextYear.getMonthOfYear(), nextYear.getDayOfMonth(),
                    nextYear.getHourOfDay(), nextYear.getMinuteOfHour(), nextYear.getSecondOfMinute());

            paymentSchedule.setInterest(interest);
            paymentSchedule.setPayDate(nextTime);
            paymentScheduleList.add(paymentSchedule);
        }
        return paymentScheduleList;
    }

    public static void main(String[] args) {
        PaymentCalculator paymentCalculator = new CalcInMonthlyPrincipalFine(PaymentCondition.newBuilder()
                .setRemainingBalance(10000)
                .setNumberPayment(12)
                .setNumberPaymentType(0)
                .setRate(0.12)
                .setRateType(0));
        List<PaymentSchedule> scheduleList = paymentCalculator.advance(new Date());
        System.out.println(scheduleList.size());
        System.out.println(scheduleList);
    }
}
