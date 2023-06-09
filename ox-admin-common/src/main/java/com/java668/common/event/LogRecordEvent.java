package com.java668.common.event;

import com.java668.common.enums.BusinessTypeEnum;
import org.springframework.context.ApplicationEvent;

/**
 * @author Jerry.chen
 * @desc
 * @date 2023/06/09 16:16
 **/
public class LogRecordEvent extends ApplicationEvent {

    private BusinessTypeEnum businessType;

    public LogRecordEvent(Object source) {
        super(source);
    }

    public BusinessTypeEnum getBusinessType() {
        return businessType;
    }

    public void setBusinessType(BusinessTypeEnum businessType) {
        this.businessType = businessType;
    }
}