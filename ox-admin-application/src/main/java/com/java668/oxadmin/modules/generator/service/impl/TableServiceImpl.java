package com.java668.oxadmin.modules.generator.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.java668.common.model.PageResult;
import com.java668.oxadmin.modules.generator.dto.request.TableReqDTO;
import com.java668.oxadmin.modules.generator.dto.response.TableColumnRespDTO;
import com.java668.oxadmin.modules.generator.mapper.TableMapper;
import com.java668.oxadmin.modules.generator.entity.Table;
import com.java668.oxadmin.modules.generator.service.ITableService;
import com.java668.oxadmin.modules.system.dto.response.UserRespDTO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 代码生成业务表(Table)表服务实现类
 *
 * @author jerry.chen
 * @since 2023-06-02 10:23:29
 */
@Service("tableService")
public class TableServiceImpl extends ServiceImpl<TableMapper, Table> implements ITableService {

    @Override
    public PageResult<UserRespDTO> page(TableReqDTO tableReqDTO) {
        return null;
    }

    @Override
    public PageResult<UserRespDTO> dbTablePage(TableReqDTO tableReqDTO) {
        return null;
    }

    @Override
    public List<TableColumnRespDTO> columnList(Long tableId) {
        return null;
    }

    @Override
    public boolean importTableSave(String tables) {
        // 查询表信息
        List<Table> tableList = findDbTableListByNames(tables);
        importGenTable(tableList);
        return false;
    }

    private void importGenTable(List<Table> tableList) {

    }

    private List<Table> findDbTableListByNames(String tables) {
        return null;
    }

}

