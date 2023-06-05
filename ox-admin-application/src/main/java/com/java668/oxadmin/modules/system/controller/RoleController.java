package com.java668.oxadmin.modules.system.controller;

import com.java668.common.model.PageResult;
import com.java668.common.model.R;
import com.java668.oxadmin.modules.system.dto.request.RolePageReqDTO;
import com.java668.oxadmin.modules.system.dto.request.RoleReqDTO;
import com.java668.oxadmin.modules.system.dto.response.RoleRespDTO;
import com.java668.oxadmin.modules.system.service.RoleService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Jerry.chen
 * @desc 角色管理
 * @date 2023/02/01 16:32
 **/
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/role")
public class RoleController {

    private final RoleService roleService;

    /**
     * 新增角色
     *
     * @param body
     * @return
     */
    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @ApiOperation("新增角色")
    public R<Integer> add(@RequestBody @Validated RoleReqDTO body) {
        return R.success(roleService.add(body));
    }

    /**
     * 删除角色
     *
     * @param ids
     * @return
     */
    @DeleteMapping
    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation("删除角色")
    public R<Integer> delete(@RequestParam(name = "ids") List<Long> ids) {
        return R.success(roleService.delete(ids));
    }

    /**
     * 修改角色
     *
     * @param body
     * @return
     */
    @PutMapping
    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation("修改角色")
    public R<Integer> update(@RequestBody @Validated RoleReqDTO body) {
        return R.success(roleService.update(body));
    }

    /**
     * 查询角色详情
     *
     * @param id
     * @return
     */
    @GetMapping("{id}")
    @ApiOperation("查询角色详情")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public R<RoleRespDTO> get(@PathVariable("id") Long id) {
        return R.success(roleService.get(id));
    }

    /**
     * 查询角色列表
     *
     * @return
     */
    @GetMapping("/list")
    @ApiOperation("查询角色列表")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public R<List<RoleRespDTO>> list() {
        return R.success(roleService.findList());
    }

    /**
     * 分页查询角色列表
     *
     * @param params
     * @return
     */
    @GetMapping("/page")
    @ApiOperation("分页查询角色列表")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public R<PageResult<RoleRespDTO>> page(RolePageReqDTO params) {
        return R.success(roleService.page(params));
    }

    /**
     * 保存角色菜单
     *
     * @param roleId
     * @param menuIds
     * @return
     */
    @PostMapping("/{roleId}/saveMenu")
    @ApiOperation("保存角色菜单")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public R<Integer> saveMenu(@PathVariable(name = "roleId") Long roleId,
                               @RequestBody List<Long> menuIds) {
        return R.success(roleService.saveMenu(roleId, menuIds));
    }

}