package com.java668.common.event.handler;

import lombok.RequiredArgsConstructor;
import com.java668.common.event.LogRecordEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @author Jerry.chen
 * @desc
 * @date 2023/06/09 16:18
 **/
@Slf4j
@Component
@RequiredArgsConstructor
public class LogRecordEventHandler implements ApplicationListener<LogRecordEvent> {

    @Async
    @Override
    public void onApplicationEvent(LogRecordEvent event) {
        log.info("==========={}", event.getBusinessType());
    }

}