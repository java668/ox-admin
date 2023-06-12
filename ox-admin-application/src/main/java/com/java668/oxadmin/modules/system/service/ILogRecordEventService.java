package com.java668.oxadmin.modules.system.service;

/**
 * @author Jerry.chen
 * @desc
 * @date 2023/06/09 16:23
 **/
public interface ILogRecordEventService {

    void pushEvent(String type, String subType, String message, Long costTime, String operName, Boolean isFail);

}