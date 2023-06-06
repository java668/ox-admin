package com.java668.oxadmin.test2.controller;

import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.java668.oxadmin.test2.dto.request.SystRolePageReqDTO;
import com.java668.oxadmin.test2.dto.request.SystRoleReqDTO;
import com.java668.oxadmin.test2.dto.response.SystRoleRespDTO;
import com.java668.oxadmin.test2.service.ISystRoleService;
import lombok.RequiredArgsConstructor;
import com.java668.common.model.R;
import com.java668.common.model.PageResult;

/**
 * 角色Controller
 * 
 * @author jerry.chen
 * @date 2023-06-06 16:00:03
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/test2/role")
public class SystRoleController {

    private final ISystRoleService systRoleService;

    /**
     * 新增角色
     *
     * @param body
     * @return
     */
    @PreAuthorize("hasAuthority('test2:role:add')")
    @PostMapping
    public R<Integer> add(@RequestBody SystRoleReqDTO body) {
        return R.success(systRoleService.add(body));
    }

    /**
     * 删除角色
     *
     * @param ids
     * @return
     */
    @PreAuthorize("hasAuthority('test2:role:remove')")
    @DeleteMapping("/{ids}")
    public R<Integer> remove(@PathVariable List<Long> ids) {
        return R.success(systRoleService.remove(ids));
    }

    /**
     * 修改角色
     *
     * @param body
     * @return
     */
    @PreAuthorize("hasAuthority('test2:role:edit')")
    @PutMapping
    public R<Integer> update(@RequestBody SystRoleReqDTO body) {
        return R.success(systRoleService.update(body));
    }

    /**
     * 获取角色详细信息
     *
     * @param id
     * @return
     */
    @PreAuthorize("hasAuthority('test2:role:query')")
    @GetMapping(value = "/{id}")
    public R<SystRoleRespDTO> detail(@PathVariable("id") Long id) {
        return R.success(systRoleService.detail(id));
    }

    /**
     * 查询角色列表
     *
     * @param req
     * @return
     */
    @PreAuthorize("hasAuthority('test2:role:list')")
    @GetMapping("/list")
    public R<PageResult<SystRoleRespDTO>> page(SystRolePageReqDTO req) {
        return R.success(systRoleService.page(req));
    }

}
