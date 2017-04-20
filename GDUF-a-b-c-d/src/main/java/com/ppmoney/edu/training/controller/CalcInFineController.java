package com.ppmoney.edu.training.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.ppmoney.edu.calculator.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 演示如果通过容器将项目跑起来
 */
@RestController
@RequestMapping("/calc")
public class CalcInFineController {
    private static final Logger logger = LoggerFactory.getLogger(CalcInFineController.class);
    private static ObjectMapper objectMapper = new ObjectMapper();

    @RequestMapping(value = "/InFine", method = RequestMethod.POST)
    public String getResult(@RequestBody Map<String, Object> mapParams) {
        objectMapper.registerModule(new JodaModule());
        try {
            String paramsJson = objectMapper.writeValueAsString(mapParams);
            try {
                PaymentCondition paymentCondition = objectMapper.readValue(paramsJson, PaymentCondition.class);

                CalcInFineOne calcInFineOne = new CalcInFineOne(paymentCondition);
                List<PaymentSchedule> scheduleList = calcInFineOne.advance(new Date());

                return objectMapper.writeValueAsString(scheduleList);
            } catch (IOException e) {
                logger.error("json转化成对象出现IO异常：", e);
            }
        } catch (JsonProcessingException e) {
            logger.error("对象转换json出现异常：", e);
        }

        return "";
    }

}
