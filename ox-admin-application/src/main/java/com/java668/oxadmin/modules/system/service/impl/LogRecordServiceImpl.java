package com.java668.oxadmin.modules.system.service.impl;

import cn.hutool.core.map.MapUtil;
import cn.hutool.http.useragent.UserAgent;
import cn.hutool.http.useragent.UserAgentUtil;
import com.java668.common.enums.BusinessType;
import com.java668.common.utils.AddressUtils;
import com.java668.oxadmin.modules.system.entity.OperLog;
import com.java668.oxadmin.modules.system.service.IOperLogService;
import com.mzt.logapi.beans.CodeVariableType;
import com.mzt.logapi.beans.LogRecord;
import com.mzt.logapi.service.ILogRecordService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author Jerry
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class LogRecordServiceImpl implements ILogRecordService {

    private final IOperLogService systOperLogService;

    @Override
    public void record(LogRecord logRecord) {
        log.info("========================= {}", logRecord);
        Map<CodeVariableType, Object> codeVariable = logRecord.getCodeVariable();
        String className = MapUtil.get(codeVariable, CodeVariableType.ClassName, String.class, "");
        String methodName = MapUtil.get(codeVariable, CodeVariableType.MethodName, String.class, "");
        String userAgent = MapUtil.get(codeVariable, CodeVariableType.USER_AGENT, String.class, "");
        String requestIp = MapUtil.get(codeVariable, CodeVariableType.REQUEST_IP, String.class,"");
        String requestMethod = MapUtil.get(codeVariable, CodeVariableType.REQUEST_METHOD, String.class, "");
        String requestUrl = MapUtil.get(codeVariable, CodeVariableType.REQUEST_URL, String.class, "");
        String requestParam = MapUtil.get(codeVariable, CodeVariableType.REQUEST_PARAM, String.class,"");
        String contentType = MapUtil.get(codeVariable, CodeVariableType.CONTENT_TYPE, String.class, "");
        Long costTime = MapUtil.get(codeVariable, CodeVariableType.COST_TIME, Long.class, 0L);
        String realAddressByIP = AddressUtils.getRealAddressByIP(requestIp);
        UserAgent ua = UserAgentUtil.parse(userAgent);

        OperLog entity = new OperLog();
        entity.setTitle(logRecord.getType());
        entity.setBusinessType(1L);
        entity.setMethod(className + "." + methodName);
        entity.setRequestMethod(requestMethod);
        entity.setOperatorType((long) BusinessType.getByEnum(logRecord.getSubType()));
        entity.setOperName(logRecord.getOperator());
        entity.setOperUrl(requestUrl);
        entity.setOperIp(requestIp);
        entity.setBrowser(ua.getBrowser().toString());
        entity.setOs(ua.getOs().toString());
        entity.setOperLocation(realAddressByIP);
        entity.setOperParam(requestParam);
        entity.setJsonResult(logRecord.getAction());
        entity.setStatus(logRecord.isFail() ? 0L : 1L);
        entity.setCostTime(costTime);
        systOperLogService.save(entity);
    }

    @Override
    public List<LogRecord> queryLog(String bizNo, String type) {
        return null;
    }

    @Override
    public List<LogRecord> queryLogByBizNo(String bizNo, String type, String subType) {
        return null;
    }
}
