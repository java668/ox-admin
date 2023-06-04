package com.java668.oxadmin.system.controller;

import com.java668.common.model.PageResult;
import com.java668.common.model.R;
import com.java668.oxadmin.system.dto.request.SystUserPageReqDTO;
import com.java668.oxadmin.system.dto.request.SystUserReqDTO;
import com.java668.oxadmin.system.dto.response.SystUserRespDTO;
import com.java668.oxadmin.system.service.ISystUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 系统用户Controller
 *
 * @author jerry.chen
 * @date 2023-06-04 17:20:10
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/system/user")
public class SystUserController {

    private final ISystUserService systUserService;

    /**
     * 新增系统用户
     *
     * @param body
     * @return
     */
    @PreAuthorize("hasAuthority('system:user:add')")
    @PostMapping
    public R<Boolean> add(@RequestBody SystUserReqDTO body) {
        return R.success(systUserService.add(body));
    }

    /**
     * 删除系统用户
     *
     * @param ids
     * @return
     */
    @PreAuthorize("hasAuthority('system:user:remove')")
    @DeleteMapping("/{ids}")
    public R<Boolean> remove(@PathVariable List<Long> ids) {
        return R.success(systUserService.remove(ids));
    }

    /**
     * 修改系统用户
     *
     * @param body
     * @return
     */
    @PreAuthorize("hasAuthority('system:user:edit')")
    @PutMapping
    public R<Boolean> update(@RequestBody SystUserReqDTO body) {
        return R.success(systUserService.update(body));
    }

    /**
     * 获取系统用户详细信息
     *
     * @param id
     * @return
     */
    @PreAuthorize("hasAuthority('system:user:query')")
    @GetMapping(value = "/{id}")
    public R<SystUserRespDTO> detail(@PathVariable("id") Long id) {
        return R.success(systUserService.detail(id));
    }

    /**
     * 查询系统用户列表
     *
     * @param req
     * @return
     */
    @PreAuthorize("hasAuthority('system:user:page')")
    @GetMapping("/page")
    public R<PageResult<SystUserRespDTO>> page(SystUserPageReqDTO req) {
        return R.success(systUserService.page(req));
    }
}
