package com.java668.oxadmin.modules.system.controller;

import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.java668.oxadmin.modules.system.dto.request.OperLogPageReqDTO;
import com.java668.oxadmin.modules.system.dto.request.OperLogReqDTO;
import com.java668.oxadmin.modules.system.dto.response.OperLogRespDTO;
import com.java668.oxadmin.modules.system.service.IOperLogService;
import lombok.RequiredArgsConstructor;
import com.java668.common.model.R;
import com.java668.common.model.PageResult;

/**
 * 操作日志Controller
 *
 * @author jerry.chen
 * @date 2023-06-09 11:17:20
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/system/log")
public class OperLogController {

    private final IOperLogService systOperLogService;

    /**
     * 新增操作日志
     *
     * @param body
     * @return
     */
    @PreAuthorize("hasAuthority('system:log:add')")
    @PostMapping
    public R<Integer> add(@RequestBody OperLogReqDTO body) {
        return R.succeed(systOperLogService.add(body));
    }

    /**
     * 删除操作日志
     *
     * @param ids
     * @return
     */
    @PreAuthorize("hasAuthority('system:log:remove')")
    @DeleteMapping("/{ids}")
    public R<Integer> remove(@PathVariable List<Long> ids) {
        return R.succeed(systOperLogService.remove(ids));
    }

    /**
     * 修改操作日志
     *
     * @param body
     * @return
     */
    @PreAuthorize("hasAuthority('system:log:edit')")
    @PutMapping
    public R<Integer> update(@RequestBody OperLogReqDTO body) {
        return R.succeed(systOperLogService.update(body));
    }

    /**
     * 获取操作日志详细信息
     *
     * @param id
     * @return
     */
    @PreAuthorize("hasAuthority('system:log:query')")
    @GetMapping(value = "/{id}")
    public R<OperLogRespDTO> detail(@PathVariable("id") Long id) {
        return R.succeed(systOperLogService.detail(id));
    }

    /**
     * 查询操作日志列表
     *
     * @param req
     * @return
     */
    @PreAuthorize("hasAuthority('system:log:list')")
    @GetMapping("/list")
    public R<PageResult<OperLogRespDTO>> page(OperLogPageReqDTO req) {
        return R.succeed(systOperLogService.page(req));
    }

}
