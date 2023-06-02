package com.java668.oxadmin.modules.generator.controller;


import com.java668.common.model.PageResult;
import com.java668.common.model.R;
import com.java668.oxadmin.modules.generator.dto.request.TableReqDTO;
import com.java668.oxadmin.modules.generator.dto.response.TableColumnRespDTO;
import com.java668.oxadmin.modules.generator.dto.response.TableRespDTO;
import com.java668.oxadmin.modules.generator.service.ITableService;
import com.java668.oxadmin.modules.system.dto.response.UserRespDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 代码生成业务表(Table)表控制层
 *
 * @author jerry.chen
 * @since 2023-06-02 10:23:28
 */
@RestController
@RequestMapping("/v1/table")
@RequiredArgsConstructor
public class TableController {
    /**
     * 服务对象
     */
    private final ITableService tableService;

    /**
     * 查询代码生成列表
     */
    @GetMapping("/list")
    public R<PageResult<UserRespDTO>> page(TableReqDTO tableReqDTO) {
        return R.success(tableService.page(tableReqDTO));
    }

    /**
     * 修改代码生成业务
     */
//    @GetMapping(value = "/{tableId}")
//    public AjaxResult getInfo(@PathVariable Long tableId) {
//        GenTable table = genTableService.selectGenTableById(tableId);
//        List<GenTable> tables = genTableService.selectGenTableAll();
//        List<GenTableColumn> list = genTableColumnService.selectGenTableColumnListByTableId(tableId);
//        Map<String, Object> map = new HashMap<String, Object>();
//        map.put("info", table);
//        map.put("rows", list);
//        map.put("tables", tables);
//        return success(map);
//    }

    /**
     * 查询数据库列表
     */
    @GetMapping("/db/list")
    public R<PageResult<UserRespDTO>> dbTablePage(TableReqDTO tableReqDTO) {
        return R.success(tableService.dbTablePage(tableReqDTO));
    }

    /**
     * 查询数据表字段列表
     */
    @GetMapping(value = "/column/{tableId}")
    public R<List<TableColumnRespDTO>> columnList(@PathVariable("tableId") Long tableId) {
        return R.success(tableService.columnList(tableId));
    }

    /**
     * 导入表结构（保存）
     */
    @PostMapping("/importTable")
    public R<Boolean> importTableSave(String tables) {
        return R.success(tableService.importTableSave(tables));
    }

    /**
     * 修改保存代码生成业务
     */
    @PutMapping
    public R<Boolean> editSave(@Validated @RequestBody TableReqDTO genTable) {
        return R.success(tableService.editSave(genTable));
    }

    /**
     * 删除代码生成
     */
    @DeleteMapping("/{tableIds}")
    public R<Boolean> remove(@PathVariable List<Long> tableIds) {
        return R.success(tableService.removeBatchByIds(tableIds));
    }

    /**
     * 预览代码
     */
    @GetMapping("/preview/{tableId}")
    public R<Map<String, String>> preview(@PathVariable("tableId") Long tableId) throws IOException {
        Map<String, String> dataMap = tableService.previewCode(tableId);
        return R.success(dataMap);
    }

    /**
     * 生成代码（下载方式）
     */
    @GetMapping("/download/{tableName}")
    public void download(HttpServletResponse response, @PathVariable("tableName") String tableName) throws IOException {
        byte[] data = tableService.downloadCode(tableName);
        genCode(response, data);
    }

    /**
     * 生成代码（自定义路径）
     */
    @GetMapping("/genCode/{tableName}")
    public R<Boolean> genCode(@PathVariable("tableName") String tableName) {
        tableService.generatorCode(tableName);
        return R.success();
    }

    /**
     * 同步数据库
     */
    @GetMapping("/synchDb/{tableName}")
    public R<Boolean> synchDb(@PathVariable("tableName") String tableName) {
        tableService.synchDb(tableName);
        return R.success();
    }

    /**
     * 批量生成代码
     */
    @GetMapping("/batchGenCode")
    public void batchGenCode(HttpServletResponse response, String tables) throws IOException {
        byte[] data = tableService.downloadCode(tableNames);
        genCode(response, data);
    }

    /**
     * 生成zip文件
     */
    private void genCode(HttpServletResponse response, byte[] data) throws IOException {
        response.reset();
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Expose-Headers", "Content-Disposition");
        response.setHeader("Content-Disposition", "attachment; filename=\"ruoyi.zip\"");
        response.addHeader("Content-Length", "" + data.length);
        response.setContentType("application/octet-stream; charset=UTF-8");
        IOUtils.write(data, response.getOutputStream());
    }
}

