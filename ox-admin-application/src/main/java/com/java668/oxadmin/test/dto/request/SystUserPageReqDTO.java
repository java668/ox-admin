package com.java668.oxadmin.test.dto.request;

import lombok.Data;
import lombok.ToString;
import com.java668.common.model.PageParam;

/**
 * 系统用户对象 syst_user
 * 
 * @author jerry.chen
 * @date 2023-06-05 22:45:17
 */
@Data
@ToString
public class SystUserPageReqDTO extends PageParam {
    private static final long serialVersionUID = 1L;
    /**
     * 用户名
     */
    private String username;
    /**
     * 昵称
     */
    private String nickName;
    /**
     * 状态：0启用、1禁用
     */
    private Long enabled;

}
