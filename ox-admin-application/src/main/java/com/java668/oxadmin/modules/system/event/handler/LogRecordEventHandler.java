package com.java668.oxadmin.modules.system.event.handler;

import com.java668.oxadmin.modules.system.event.LogRecordEvent;
import com.mzt.logapi.beans.LogRecord;
import com.mzt.logapi.service.ILogRecordService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author Jerry.chen
 * @desc
 * @date 2023/06/09 16:18
 **/
@Slf4j
@Component
@RequiredArgsConstructor
public class LogRecordEventHandler implements ApplicationListener<LogRecordEvent> {

    private final ILogRecordService logRecordService;

    @Async
    @Override
    public void onApplicationEvent(LogRecordEvent event) {
        LogRecord logRecord = new LogRecord();
        logRecord.setTenant("oxadmin");
        logRecord.setType(event.getType());
        logRecord.setSubType(event.getSubType());
        logRecord.setBizNo("");
        logRecord.setOperator(event.getOperName());
        logRecord.setAction(event.getMessage());
        logRecord.setFail(event.getFail());
        logRecord.setCreateTime(new Date());
        logRecord.setExtra("");
        logRecord.setCodeVariable(event.getMap());
        logRecordService.record(logRecord);
        log.info("==========={}", event);
    }

}