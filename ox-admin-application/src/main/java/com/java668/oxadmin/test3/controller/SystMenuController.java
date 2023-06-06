package com.java668.oxadmin.test3.controller;

import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.java668.oxadmin.test3.dto.request.SystMenuPageReqDTO;
import com.java668.oxadmin.test3.dto.request.SystMenuReqDTO;
import com.java668.oxadmin.test3.dto.response.SystMenuRespDTO;
import com.java668.oxadmin.test3.service.ISystMenuService;
import lombok.RequiredArgsConstructor;
import com.java668.common.model.R;

/**
 * 系统菜单Controller
 * 
 * @author jerry.chen
 * @date 2023-06-06 23:32:17
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/test3/menu")
public class SystMenuController {

    private final ISystMenuService systMenuService;

    /**
     * 新增系统菜单
     *
     * @param body
     * @return
     */
    @PreAuthorize("hasAuthority('test3:menu:add')")
    @PostMapping
    public R<Integer> add(@RequestBody SystMenuReqDTO body) {
        return R.success(systMenuService.add(body));
    }

    /**
     * 删除系统菜单
     *
     * @param ids
     * @return
     */
    @PreAuthorize("hasAuthority('test3:menu:remove')")
    @DeleteMapping("/{ids}")
    public R<Integer> remove(@PathVariable List<Long> ids) {
        return R.success(systMenuService.remove(ids));
    }

    /**
     * 修改系统菜单
     *
     * @param body
     * @return
     */
    @PreAuthorize("hasAuthority('test3:menu:edit')")
    @PutMapping
    public R<Integer> update(@RequestBody SystMenuReqDTO body) {
        return R.success(systMenuService.update(body));
    }

    /**
     * 获取系统菜单详细信息
     *
     * @param id
     * @return
     */
    @PreAuthorize("hasAuthority('test3:menu:query')")
    @GetMapping(value = "/{id}")
    public R<SystMenuRespDTO> detail(@PathVariable("id") Long id) {
        return R.success(systMenuService.detail(id));
    }

    /**
     * 查询系统菜单列表
     *
     * @param req
     * @return
     */
    @PreAuthorize("hasAuthority('test3:menu:list')")
    @GetMapping("/list")
    public R<List<SystMenuRespDTO>> list(SystMenuPageReqDTO req) {
        List<SystMenuRespDTO> list = systMenuService.list(req);
        return R.success(list);
    }

}
