package com.java668.oxadmin.dto.request;

import com.java668.common.model.PageParam;
import lombok.Data;

/**
 * @author Jerry.chen
 * @desc RolePageReqDTO
 * @date 2023/03/29 18:05
 **/
@Data
public class RolePageReqDTO extends PageParam {

    private String q;

    private String startTime;

    private String endTime;

}