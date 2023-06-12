package com.java668.oxadmin.modules.system.service.impl;

import com.java668.oxadmin.modules.system.event.LogRecordEvent;
import com.java668.oxadmin.modules.system.service.ILogRecordEventService;
import com.mzt.logapi.beans.CodeVariableType;
import com.mzt.logapi.util.IpUtils;
import com.mzt.logapi.util.ServletUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

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
    public void pushEvent(String type, String subType, String message, Long costTime, String operName, Boolean isFail) {
        LogRecordEvent event = new LogRecordEvent(subType);
        event.setType(type);
        event.setSubType(subType);
        event.setCostTime(costTime);
        event.setOperName(operName);
        event.setMap(getVariableMap(costTime));
        event.setMessage(message);
        event.setFail(isFail);
        applicationContext.publishEvent(event);
    }

    private Map<CodeVariableType, Object> getVariableMap(Long costTime){
        Map<CodeVariableType, Object> map = new HashMap<>(6);
        map.put(CodeVariableType.COST_TIME, costTime);
        HttpServletRequest request = ServletUtils.getRequest();
        if (request != null) {
            map.put(CodeVariableType.USER_AGENT, request.getHeader("User-Agent"));
            map.put(CodeVariableType.REQUEST_IP, IpUtils.getIpAddr());
            map.put(CodeVariableType.REQUEST_URL, request.getRequestURI());
            map.put(CodeVariableType.REQUEST_METHOD, request.getMethod());
            map.put(CodeVariableType.CONTENT_TYPE, request.getContentType());
        }
        return map;
    }
}