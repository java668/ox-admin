package com.java668.oxadmin.modules.generator.controller;


import com.alibaba.fastjson.JSONObject;
import com.java668.common.model.PageResult;
import com.java668.common.model.R;
import com.java668.oxadmin.modules.generator.dto.request.TablePageReqDTO;
import com.java668.oxadmin.modules.generator.dto.request.TableReqDTO;
import com.java668.oxadmin.modules.generator.dto.response.TableRespDTO;
import com.java668.oxadmin.modules.generator.service.ITableService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
    @GetMapping("/page")
    public R<PageResult<TableRespDTO>> page(TablePageReqDTO params) {
        return R.success(tableService.page(params));
    }

    /**
     * 修改代码生成业务
     */
    @GetMapping(value = "/{tableId}")
    public R<JSONObject> getInfo(@PathVariable Long tableId) {
        return R.success(tableService.getInfo(tableId));
    }

    /**
     * 查询数据库列表
     */
    @GetMapping("/dbTablePage")
    public R<PageResult<TableRespDTO>> dbTablePage(TablePageReqDTO params) {
        return R.success(tableService.dbTablePage(params));
    }

    /**
     * 导入表结构（保存）
     */
    @PostMapping("/importTable")
    public R<Boolean> importTable(@RequestParam("tables") List<String> tables) {
        return R.success(tableService.importTable(tables));
    }

    /**
     * 修改保存代码生成业务
     */
    @PutMapping
    public R<Boolean> update(@Validated @RequestBody TableReqDTO genTable) {
        return R.success(tableService.update(genTable));
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
        return R.success(tableService.previewCode(tableId));
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
        return R.success(tableService.generatorCode(tableName));
    }

    /**
     * 同步数据库
     */
    @GetMapping("/syncDb/{tableName}")
    public R<Boolean> syncDb(@PathVariable("tableName") String tableName) {
        tableService.syncDb(tableName);
        return R.success(Boolean.TRUE);
    }

    /**
     * 批量生成代码
     */
    @GetMapping("/batchGenCode")
    public void batchGenCode(HttpServletResponse response, String tables) throws IOException {
        byte[] data = tableService.downloadCode(tables);
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

