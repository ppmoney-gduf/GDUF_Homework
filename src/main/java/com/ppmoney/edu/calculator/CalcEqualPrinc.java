package com.ppmoney.edu.calculator;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 等额本金计算
 */
public class CalcEqualPrinc extends PaymentCalculator {
    private static final Logger logger = LoggerFactory.getLogger(CalcEqualPrinc.class);

    public CalcEqualPrinc(PaymentCondition paymentCondition) {
        super(paymentCondition);
    }

    @Override
    public List<PaymentSchedule> advance(Date fromDate) {
        //月本金 = 每月本金 + (总本金 - 每月本金 * (还款期数 - 1)) * 月利率
        List<PaymentSchedule> paymentScheduleList = new ArrayList<>();
        DateTime nextTime;
        DateTime firstCurrent = new DateTime(fromDate);
        int year = firstCurrent.getYear();
        int month = firstCurrent.getMonthOfYear();
        double totalMoney = paymentCondition.getRemainingBalance();
        int totalMonth = paymentCondition.getNumberPayment();
        double monthPri = paymentCondition.getRemainingBalance() / totalMonth;
        double monRate = paymentCondition.getRate();

        BigDecimal b = new BigDecimal(monRate);
        monRate = b.setScale(6, BigDecimal.ROUND_HALF_UP).doubleValue();
        for (int i = 1; i <= totalMonth; i++) {
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
            double monthRes = monthPri + (totalMoney - monthPri * (i - 1)) * monRate;
            BigDecimal b1 = new BigDecimal(monthRes);
            monthRes = b1.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            logger.info("第{}月，还款为：{}", i, monthRes);
            PaymentSchedule paymentSchedule = new PaymentSchedule();
            paymentSchedule.setBalance(monthRes);
            paymentSchedule.setPayDate(nextTime);
            paymentSchedule.setInterest(monthRes - monthPri);
            paymentSchedule.setPrincipal(monthPri);

            paymentScheduleList.add(paymentSchedule);
        }
        return paymentScheduleList;
    }

    public static void main(String[] args) {
        PaymentCalculator paymentCalculator = new CalcEqualPrinc(PaymentCondition.newBuilder()
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
