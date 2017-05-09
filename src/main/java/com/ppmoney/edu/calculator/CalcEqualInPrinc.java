package com.ppmoney.edu.calculator;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 等额本息计算
 */
public class CalcEqualInPrinc extends PaymentCalculator {
    private static final Logger logger = LoggerFactory.getLogger(CalcEqualInPrinc.class);

    public CalcEqualInPrinc(PaymentCondition paymentCondition) {
        super(paymentCondition);
    }

    @Override
    public List<PaymentSchedule> advance(Date fromDate) {
        //本金×[月利率×（1＋月利率）^还款期数]÷[（1＋月利率）^还款期数－1]
        List<PaymentSchedule> paymentScheduleList = new ArrayList<>();
        DateTime nextTime;
        DateTime firstCurrent = new DateTime(fromDate);
        int year = firstCurrent.getYear();
        int month = firstCurrent.getMonthOfYear();
        double totalMoney = paymentCondition.getRemainingBalance();
        double monRate = paymentCondition.getRate();
        int months = paymentCondition.getNumberPayment();
        double monInterest = totalMoney * monRate * Math.pow((1 + monRate), months) / (Math.pow((1 + monRate), months) - 1);
        BigDecimal b = new BigDecimal(monInterest);
        monInterest = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        logger.info("月供本息和：{}", monInterest);
        for (int i = 1; i <= months; i++) {
            month += 1;
            //跨年处理
            if (month > 12 || (i % 13) == 0) {
                year = year + 1;
                month = 1;
            }
            //跨年跨月处理
            DateTime nextYear = new DateTime(year, month, firstCurrent.getDayOfMonth(), firstCurrent.getHourOfDay(), firstCurrent.getHourOfDay(), firstCurrent.getMinuteOfHour(), firstCurrent.getSecondOfMinute());
            nextTime = new DateTime(nextYear.getYear(), nextYear.getMonthOfYear(), nextYear.getDayOfMonth(),
                    nextYear.getHourOfDay(), nextYear.getMinuteOfHour(), nextYear.getSecondOfMinute());
            PaymentSchedule paymentSchedule = new PaymentSchedule();
            paymentSchedule.setBalance(monInterest);
            paymentSchedule.setPayDate(nextTime);
            paymentSchedule.setPrincipal(totalMoney);

            paymentScheduleList.add(paymentSchedule);
        }
        return paymentScheduleList;
    }

    public static void main(String[] args) {
        PaymentCalculator paymentCalculator = new CalcEqualInPrinc(PaymentCondition.newBuilder()
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
