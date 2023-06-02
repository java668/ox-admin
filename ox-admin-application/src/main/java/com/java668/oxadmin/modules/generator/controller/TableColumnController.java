package com.java668.oxadmin.modules.generator.controller;


import com.java668.oxadmin.modules.generator.service.ITableColumnService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 代码生成业务表字段(TableColumn)表控制层
 *
 * @author jerry.chen
 * @since 2023-06-02 10:23:29
 */
@RestController
@RequestMapping("/v1/tableColumn")
@RequiredArgsConstructor
public class TableColumnController {
    /**
     * 服务对象
     */
    private final ITableColumnService tableColumnService;


}

