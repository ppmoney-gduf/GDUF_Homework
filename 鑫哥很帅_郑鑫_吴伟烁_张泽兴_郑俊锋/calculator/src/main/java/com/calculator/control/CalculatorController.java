package com.calculator.control;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.calculator.domain.Condition;
import com.calculator.domain.Result;
import com.calculator.service.CalculatorService;

@Controller
public class CalculatorController {
	@Resource
	private CalculatorService cs;
	
	@RequestMapping(value="calculate",method=RequestMethod.POST)
	public @ResponseBody Result[] calculate(Condition c) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		Result[] tr=null;
		//将百分比利率转为小数
		c.setRate(c.getRate()/100);
		
		//通过反射调用对应的计算方法
		Class<? extends CalculatorService> clazz=cs.getClass();
		tr=(Result[]) clazz.getMethod(c.getMethod(), Condition.class).invoke(cs, c);
		
		return tr;
	}
	
}
