package com.calculator.service;

import com.calculator.domain.Condition;
import com.calculator.domain.Result;

public interface CalculatorService {
	
	Result[] alsoDuePrincipalAndInterest(Condition c);	//到期还本息
	
	Result[] monthInterestDueDebt(Condition c);			//月还息到期还本
	
	Result[] equalPrincipalInterest(Condition c);       //等额本息
	
	Result[] equalPrincipal(Condition c);       //等额本金
	
}
