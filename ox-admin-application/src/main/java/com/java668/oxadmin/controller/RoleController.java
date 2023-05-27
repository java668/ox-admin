package com.java668.oxadmin.controller;

import com.java668.common.model.PageResult;
import com.java668.common.model.R;
import com.java668.oxadmin.dto.request.RolePageReqDTO;
import com.java668.oxadmin.dto.request.RoleReqDTO;
import com.java668.oxadmin.dto.response.RoleRespDTO;
import com.java668.oxadmin.service.RoleService;
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
     * @param body
     * @return
     */
    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @ApiOperation("新增角色")
    public R<Boolean> add(@RequestBody @Validated RoleReqDTO body) {
        Boolean result = roleService.add(body);
        return R.succeed(result);
    }

    /**
     * 删除角色
     * @param ids
     * @return
     */
    @DeleteMapping
    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation("删除角色")
    public R<Boolean> delete(@RequestParam(name = "ids") List<Long> ids) {
        Boolean result = roleService.delete(ids);
        return R.succeed(result);
    }

    /**
     * 修改角色
     * @param body
     * @return
     */
    @PutMapping
    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation("修改角色")
    public R<Boolean> update(@RequestBody @Validated RoleReqDTO body) {
        Boolean result = roleService.update(body);
        return R.succeed(result);
    }

    /**
     * 查询角色详情
     * @param id
     * @return
     */
    @GetMapping("{id}")
    @ApiOperation("查询角色详情")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public R<RoleRespDTO> get(@PathVariable("id") Long id) {
        RoleRespDTO user = roleService.get(id);
        return R.succeed(user);
    }

    /**
     * 查询角色列表
     * @return
     */
    @GetMapping("/list")
    @ApiOperation("查询角色列表")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public R<List<RoleRespDTO>> list() {
        List<RoleRespDTO> list = roleService.findList();
        return R.succeed(list);
    }

    /**
     * 分页查询角色列表
     * @param params
     * @return
     */
    @GetMapping("/page")
    @ApiOperation("分页查询角色列表")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public R<PageResult<RoleRespDTO>> page(RolePageReqDTO params) {
        PageResult<RoleRespDTO> pageRespDTO = roleService.page(params);
        return R.succeed(pageRespDTO);
    }

    /**
     * 保存角色菜单
     * @param roleId
     * @param menuIds
     * @return
     */
    @PostMapping("/{roleId}/saveMenu")
    @ApiOperation("保存角色菜单")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public R<Integer> saveMenu(@PathVariable(name = "roleId") Long roleId,
                               @RequestBody List<Long> menuIds) {
        return R.succeed(roleService.saveMenu(roleId, menuIds));
    }

}