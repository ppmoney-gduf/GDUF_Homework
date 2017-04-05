package com.ppmoney.edu.calculator;

import java.util.Date;
import java.util.List;

/**
 * 还款计算器
 */
public abstract class PaymentCalculator {
    /**
     * 还款条件
     */
    protected PaymentCondition paymentCondition;

    public PaymentCalculator(PaymentCondition paymentCondition) {
        this.paymentCondition = paymentCondition;
    }

    /**
     * 试算还款计划
     */
    public abstract List<PaymentSchedule> advance(Date fromDate);
}
