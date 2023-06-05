package com.java668.oxadmin.test.controller;

import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.java668.oxadmin.test.dto.request.SystUserPageReqDTO;
import com.java668.oxadmin.test.dto.request.SystUserReqDTO;
import com.java668.oxadmin.test.dto.response.SystUserRespDTO;
import com.java668.oxadmin.test.service.ISystUserService;
import lombok.RequiredArgsConstructor;
import com.java668.common.model.R;
import com.java668.common.model.PageResult;

/**
 * 系统用户Controller
 * 
 * @author jerry.chen
 * @date 2023-06-05 22:45:17
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/test/user")
public class SystUserController {

    private final ISystUserService systUserService;

    /**
     * 新增系统用户
     *
     * @param body
     * @return
     */
    @PreAuthorize("hasAuthority('test:user:add')")
    @PostMapping
    public R<Integer> add(@RequestBody SystUserReqDTO body) {
        return R.success(systUserService.add(body));
    }

    /**
     * 删除系统用户
     *
     * @param ids
     * @return
     */
    @PreAuthorize("hasAuthority('test:user:remove')")
    @DeleteMapping("/{ids}")
    public R<Integer> remove(@PathVariable List<Long> ids) {
        return R.success(systUserService.remove(ids));
    }

    /**
     * 修改系统用户
     *
     * @param body
     * @return
     */
    @PreAuthorize("hasAuthority('test:user:edit')")
    @PutMapping
    public R<Integer> update(@RequestBody SystUserReqDTO body) {
        return R.success(systUserService.update(body));
    }

    /**
     * 获取系统用户详细信息
     *
     * @param id
     * @return
     */
    @PreAuthorize("hasAuthority('test:user:query')")
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
    @PreAuthorize("hasAuthority('test:user:list')")
    @GetMapping("/list")
    public R<PageResult<SystUserRespDTO>> page(SystUserPageReqDTO req) {
        return R.success(systUserService.page(req));
    }

}
