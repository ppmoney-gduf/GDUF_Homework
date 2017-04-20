package com.ppmoney.edu.calculator;

import org.junit.Test;

import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


/**
 *
 */
public class CalcInFineOneTest {
    @Test
    public void advance() throws Exception {
        CalcInFineOne paymentCalculator = new CalcInFineOne(PaymentCondition.newBuilder()
                .setRemainingBalance(10000)
                .setNumberPayment(12)
                .setNumberPaymentType(0)
                .setRate(12)
                .setRateType(0));
        List<PaymentSchedule> scheduleList = paymentCalculator.advance(new Date());

        assertThat(scheduleList).isNotNull();
        assertThat(scheduleList.get(0).getPrincipal()).isEqualTo(10000);
        assertThat(scheduleList.get(0).getBalance()).isEqualTo(11200);
    }

}