package com.calculator.service;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.calculator.domain.Condition;
import com.calculator.domain.Result;
import com.calculator.service.CalculatorService;
import com.calculator.service.impl.CalculatorServiceImpl;

public class CalculatorServiceTest {
	Condition condition;
	CalculatorService cs;
	
	@Before
	public void setUp() throws Exception {
		condition=new Condition();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		condition.setDate(sdf.parse("2017-5-21"));
		condition.setLimit(12);
		condition.setLimitIsDay(0);
		condition.setMoney(1000);
		condition.setRate(1);
		condition.setRateIsDay(2);
		
		cs=new CalculatorServiceImpl();
	}

	@Test
	public void testAlsoDuePrincipalAndInterest() {
		Result[] results = cs.alsoDuePrincipalAndInterest(condition);
		System.out.println(results[0]);
	}

	@Test
	public void testMonthInterestDueDebt() {
		Result[] results = cs.monthInterestDueDebt(condition);
		for(int i=0;i<results.length;i++){
			System.out.println(results[i]);
		}
	}

	@Test
	public void testEqualPrincipalInterest() {
		Result[] results = cs.equalPrincipalInterest(condition);
		for(int i=0;i<results.length;i++){
			System.out.println(results[i]);
		}
	}

	@Test
	public void testEqualPrincipal() {
		Result[] results = cs.equalPrincipal(condition);
		for(int i=0;i<results.length;i++){
			System.out.println(results[i]);
		}
	}

}
