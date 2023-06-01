package com.java668.oxadmin.modules.system.dto.request;

import com.java668.common.model.PageParam;
import lombok.Data;

/**
 * @author Jerry.chen
 * @desc UserPageReqDTO
 * @date 2023/03/29 18:05
 **/
@Data
public class UserPageReqDTO extends PageParam {

    private String q;

    private String startTime;

    private String endTime;

    private Integer enabled;

}