package com.java668.oxadmin.modules.generator.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.java668.oxadmin.modules.generator.mapper.TableColumnMapper;
import com.java668.oxadmin.modules.generator.entity.TableColumn;
import com.java668.oxadmin.modules.generator.service.ITableColumnService;
import org.springframework.stereotype.Service;

/**
 * 代码生成业务表字段(TableColumn)表服务实现类
 *
 * @author jerry.chen
 * @since 2023-06-02 10:23:30
 */
@Service("tableColumnService")
public class TableColumnServiceImpl extends ServiceImpl<TableColumnMapper, TableColumn> implements ITableColumnService {

}

