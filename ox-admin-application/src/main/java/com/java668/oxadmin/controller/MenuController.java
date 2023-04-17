package com.java668.oxadmin.controller;

import cn.hutool.core.lang.tree.Tree;
import com.java668.common.model.R;
import com.java668.oxadmin.dto.request.MenuReqDTO;
import com.java668.oxadmin.dto.request.groups.Insert;
import com.java668.oxadmin.dto.request.groups.Update;
import com.java668.oxadmin.dto.response.MenuRespDTO;
import com.java668.oxadmin.service.MenuService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Size;
import java.util.List;

/**
 * @author Jerry.chen
 * @desc 菜单管理
 * @date 2023/02/01 16:32
 **/
@Slf4j
@RequiredArgsConstructor
@Validated
@RestController
@RequestMapping("/v1/menu")
public class MenuController {

    private final MenuService menuService;

    @PostMapping
    @ApiOperation("新增菜单")
    @PreAuthorize("hasRole('ADMIN')")
    public R<Boolean> add(@RequestBody @Validated(Insert.class) MenuReqDTO body) {
        Boolean result = menuService.add(body);
        return R.succeed(result);
    }

    @DeleteMapping
    @ApiOperation("删除菜单")
    @PreAuthorize("hasRole('ADMIN')")
    public R<Boolean> delete(@Size(min = 1, max = 5, message = "批量删除个数须在{min}-{max}范围")
                             @RequestParam(name = "ids", required = true) List<Long> ids) {
        Boolean result = menuService.delete(ids);
        return R.succeed(result);
    }

    @PatchMapping
    @ApiOperation("修改菜单")
    @PreAuthorize("hasRole('ADMIN')")
    public R<Boolean> update(@RequestBody @Validated(Update.class) MenuReqDTO body) {
        Boolean result = menuService.update(body);
        return R.succeed(result);
    }

    @GetMapping("{id}")
    @ApiOperation("查询菜单详情")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public R<MenuRespDTO> get(@PathVariable(name = "id") Long id) {
        MenuRespDTO result = menuService.get(id);
        return R.succeed(result);
    }

    @GetMapping(value = "/treeList")
    @ApiOperation("查询树型列表")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public R<List<Tree<Long>>> treeList() {
        List<Tree<Long>> treeList = menuService.treeList();
        return R.succeed(treeList);
    }

    @GetMapping(value = "/lazy/{pid}")
    @ApiOperation("懒加载菜单数据")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public R<List<MenuRespDTO>> lazy(@PathVariable(value = "pid") Long pid) {
        List<MenuRespDTO> menuList = menuService.lazy(pid);
        return R.succeed(menuList);
    }

    @GetMapping(value = "/superior")
    @ApiOperation("根据ID获取同级与上级数据")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public R<List<Tree<Long>>> superior(@Size(min = 1, max = 5, message = "ids元素个数须在{min}-{max}范围")
                                        @RequestParam(name = "ids", required = true) List<Long> ids) {
        List<Tree<Long>> treeList = menuService.superior(ids);
        return R.succeed(treeList);
    }

}
