package com.calculator.service.impl;

import java.math.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.calculator.domain.Condition;
import com.calculator.domain.Result;
import com.calculator.service.CalculatorService;

@Service
public class CalculatorServiceImpl implements CalculatorService {
	// 到期还本息
	public Result[] alsoDuePrincipalAndInterest(Condition c) {
		Result[] tr = new Result[1];
		Result r = new Result();

		// 计算日利率
		double dayRate = getDayRate(c.getRate(), c.getRateIsDay());

		// 计算到期日期
		Calendar endCalendar = getEndCalendar(c.getDate(), c.getLimit(),
				c.getLimitIsDay());

		// 将借出期限转化为天数
		long totalDay = getTotalDay(c.getDate(), endCalendar);

		// 计算利息
		double interest = c.getMoney() * dayRate * totalDay;
		interest = new BigDecimal(String.valueOf(interest)).setScale(2,
				BigDecimal.ROUND_HALF_UP).doubleValue();

		// 设置result
		r.setNum(1);
		r.setDate(new SimpleDateFormat("yyyy-MM-dd").format(endCalendar
				.getTime()));
		r.setInterest(interest);
		r.setPrincipal(c.getMoney());
		r.setTotal(interest + r.getPrincipal());

		tr[0] = r;
		return tr;
	}

	@Override
	// 月还息到期还本
	public Result[] monthInterestDueDebt(Condition c) {
		Result[] tr = new Result[c.getLimit()];

		// 计算日利率
		double dayRate = getDayRate(c.getRate(), c.getRateIsDay());

		// 计算每期结果
		int i = 0;
		Date startDate = c.getDate();
		while (i < c.getLimit()) {
			Result r = new Result();

			// 计算到期日期
			Calendar endCalendar = getEndCalendar(startDate, 1,
					c.getLimitIsDay());

			// 将借出期限转化为天数
			long totalDay = getTotalDay(startDate, endCalendar);

			// 计算利息
			double interest = c.getMoney() * dayRate * 30;
			interest = new BigDecimal(String.valueOf(interest)).setScale(2,
					BigDecimal.ROUND_HALF_UP).doubleValue();

			// 设置result
			r.setNum(i + 1);
			Date endDate = endCalendar.getTime();
			r.setDate(new SimpleDateFormat("yyyy-MM-dd").format(endDate));
			r.setInterest(interest);
			r.setPrincipal(i == c.getLimit() - 1 ? c.getMoney() : 0);
			r.setTotal(i == c.getLimit() - 1 ? interest + r.getPrincipal()
					: interest);

			tr[i] = r;
			startDate = endDate;
			i++;
		}

		return tr;
	}

	@Override
	// 等额本息
	public Result[] equalPrincipalInterest(Condition c) {
		// TODO Auto-generated method stub
		Result[] tr = new Result[c.getLimit()];
		double principal = c.getMoney();// 贷款本金
		// 计算日利率
		double dayRate = getDayRate(c.getRate(), c.getRateIsDay());

		// 计算月利率
		double monthRate = dayRate * 30;

		// 计算每期结果
		int i = 0;
		Date startDate = c.getDate();
		while (i < c.getLimit()) {
			Result r = new Result();

			// 计算剩余金额
			double remainMoney = c.getMoney()
					- (c.getMoney() / c.getLimit() * i);

			// 计算每月月供额
			double total = principal * monthRate
					* Math.pow((1 + monthRate), c.getLimit())
					/ (Math.pow((1 + monthRate), c.getLimit()) - 1);
			total = new BigDecimal(String.valueOf(total)).setScale(2,
					BigDecimal.ROUND_HALF_UP).doubleValue();

			// 计算到期日期
			Calendar endCalendar = getEndCalendar(startDate, 1,
					c.getLimitIsDay());

			// 计算每月应还利息
			double dueInterest = remainMoney * monthRate;
			dueInterest = new BigDecimal(String.valueOf(dueInterest)).setScale(
					2, BigDecimal.ROUND_HALF_UP).doubleValue();

			// 计算每月应还本金
			double duemonthPrinc = total - dueInterest;
			duemonthPrinc = new BigDecimal(String.valueOf(duemonthPrinc))
					.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();

			// 设置result
			r.setNum(i + 1);
			Date endDate = endCalendar.getTime();
			r.setDate(new SimpleDateFormat("yyyy-MM-dd").format(endDate));
			r.setInterest(dueInterest);
			r.setPrincipal(duemonthPrinc);
			r.setTotal(total);

			tr[i] = r;
			startDate = endDate;
			i++;
		}

		return tr;
	}

	@Override
	// 等额本金
	public Result[] equalPrincipal(Condition c) {

		// TODO Auto-generated method stub
		Result[] tr = new Result[c.getLimit()];

		// 计算日利率
		double dayRate = getDayRate(c.getRate(), c.getRateIsDay());

		// 计算每期结果
		int i = 0;
		Date startDate = c.getDate();
		while (i < c.getLimit()) {
			Result r = new Result();

			// 计算剩余待还金额

			double remainMoney = c.getMoney()
					- (c.getMoney() / c.getLimit() * i);
			remainMoney = new BigDecimal(String.valueOf(remainMoney)).setScale(
					2, BigDecimal.ROUND_HALF_UP).doubleValue();

			// 计算到期日期
			Calendar endCalendar = getEndCalendar(startDate, 1,
					c.getLimitIsDay());

			// 计算利息
			double interest = remainMoney * dayRate * 30;
			interest = new BigDecimal(String.valueOf(interest)).setScale(2,
					BigDecimal.ROUND_HALF_UP).doubleValue();

			// 计算每月应还本金
			double monthPrin = c.getMoney() / c.getLimit();
			monthPrin = new BigDecimal(String.valueOf(monthPrin)).setScale(2,
					BigDecimal.ROUND_HALF_UP).doubleValue();

			// 设置result
			r.setNum(i + 1);
			Date endDate = endCalendar.getTime();
			r.setDate(new SimpleDateFormat("yyyy-MM-dd").format(endDate));
			r.setInterest(interest);
			r.setPrincipal(monthPrin);
			r.setTotal(r.getPrincipal() + interest);

			tr[i] = r;
			startDate = endDate;
			i++;
		}

		return tr;
	}

	// 计算日利率
	private double getDayRate(double rate, int rateIsDay) {
		double dayRate;
		if (rateIsDay == 1) {
			dayRate = rate;
		} else {
			dayRate = rate / 365.0;
		}
		return dayRate;
	}

	// 计算到期日期
	private Calendar getEndCalendar(Date d, int limit, int limitIsDay) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(d);
		if (limitIsDay == 1) {
			// 借出期限按天算
			calendar.add(Calendar.DAY_OF_YEAR, limit);
		} else {
			// 借出期限按月算
			calendar.add(Calendar.MONTH, limit);
		}
		return calendar;
	}

	// 将借出期限转化为天数
	private long getTotalDay(Date startDate, Calendar endCalendar) {
		long totalDay;
		long startTime = startDate.getTime();
		long endTime = endCalendar.getTimeInMillis();
		totalDay=(endTime-startTime)/(1000*3600*24);
		return totalDay;
	}
}
