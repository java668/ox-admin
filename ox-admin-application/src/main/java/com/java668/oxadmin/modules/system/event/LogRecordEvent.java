package com.java668.oxadmin.modules.system.event;

import com.mzt.logapi.beans.CodeVariableType;
import lombok.ToString;
import org.springframework.context.ApplicationEvent;

import java.util.Map;

/**
 * @author Jerry.chen
 * @desc
 * @date 2023/06/09 16:16
 **/
@ToString
public class LogRecordEvent extends ApplicationEvent {

    private String type;

    private String subType;

    private Long costTime;

    private String message;

    private String operName;

    private Boolean isFail;

    Map<CodeVariableType, Object> map;

    public LogRecordEvent(Object source) {
        super(source);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSubType() {
        return subType;
    }

    public void setSubType(String subType) {
        this.subType = subType;
    }

    public Long getCostTime() {
        return costTime;
    }

    public void setCostTime(Long costTime) {
        this.costTime = costTime;
    }

    public String getOperName() {
        return operName;
    }

    public void setOperName(String operName) {
        this.operName = operName;
    }

    public Map<CodeVariableType, Object> getMap() {
        return map;
    }

    public void setMap(Map<CodeVariableType, Object> map) {
        this.map = map;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getFail() {
        return isFail;
    }

    public void setFail(Boolean fail) {
        isFail = fail;
    }
}