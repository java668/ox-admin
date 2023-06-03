package com.java668.oxadmin.modules.generator.dto.request;

import com.java668.common.model.PageParam;
import lombok.Data;

/**
 * 业务表 gen_table
 *
 * @author ruoyi
 */
@Data
public class TablePageReqDTO extends PageParam {

    private String tableName;

    private String tableComment;

    private String beginTime;

    private String endTime;
}
