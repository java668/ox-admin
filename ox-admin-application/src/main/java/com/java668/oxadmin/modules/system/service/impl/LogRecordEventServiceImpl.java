package com.java668.oxadmin.modules.system.service.impl;

import com.java668.common.enums.BusinessTypeEnum;
import com.java668.common.event.LogRecordEvent;
import com.java668.oxadmin.modules.system.service.ILogRecordEventService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

/**
 * @author Jerry.chen
 * @desc
 * @date 2023/06/09 16:23
 **/
@Slf4j
@Service
@RequiredArgsConstructor
public class LogRecordEventServiceImpl implements ILogRecordEventService {

    private final ApplicationContext applicationContext;

    @Override
    public void pushEvent(String businessType) {
        LogRecordEvent event = new LogRecordEvent("LogRecordEvent");
        event.setBusinessType(BusinessTypeEnum.LOGIN);
        applicationContext.publishEvent(event);
    }
}