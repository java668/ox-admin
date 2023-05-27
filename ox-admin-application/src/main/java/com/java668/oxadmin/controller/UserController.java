package com.java668.oxadmin.controller;

import com.java668.common.model.PageResult;
import com.java668.common.model.R;
import com.java668.oxadmin.dto.request.UserPageReqDTO;
import com.java668.oxadmin.dto.request.UserReqDTO;
import com.java668.oxadmin.dto.request.groups.Insert;
import com.java668.oxadmin.dto.request.groups.Update;
import com.java668.oxadmin.dto.response.UserRespDTO;
import com.java668.oxadmin.service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Jerry.chen
 * @desc 用户管理
 * @date 2023/02/01 16:32
 **/
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/user")
public class UserController {

    private final UserService userService;

    /**
     * 新增用户
     * @param body
     * @return
     */
    @PostMapping
    @ApiOperation("新增用户")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public R<Boolean> add(@RequestBody @Validated(Insert.class) UserReqDTO body) {
        Boolean userId = userService.add(body);
        return R.succeed(userId);
    }

    /**
     * 删除用户
     * @param ids
     * @return
     */
    @DeleteMapping
    @ApiOperation("删除用户")
    @PreAuthorize("hasRole('ADMIN')")
    public R<Boolean> delete(@RequestParam(name = "ids") List<Long> ids) {
        Boolean result = userService.delete(ids);
        return R.succeed(result);
    }

    /**
     * 更新用户
     * @param body
     * @return
     */
    @PatchMapping
    @ApiOperation("更新用户")
    @PreAuthorize("hasRole('ADMIN')")
    public R<Boolean> update(@RequestBody @Validated(Update.class) UserReqDTO body) {
        Boolean result = userService.update(body);
        return R.succeed(result);
    }

    /**
     * 查询用户详情
     * @param id
     * @return
     */
    @GetMapping("{id}")
    @ApiOperation("查询用户详情")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public R<UserRespDTO> get(@PathVariable("id") Long id) {
        UserRespDTO user = userService.get(id);
        return R.succeed(user);
    }

    /**
     * 分页查询用户列表
     * @param params
     * @return
     */
    @GetMapping("/page")
    @ApiOperation("分页查询用户列表")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public R<PageResult<UserRespDTO>> page(UserPageReqDTO params) {
        PageResult<UserRespDTO> pageRespDTO = userService.page(params);
        return R.succeed(pageRespDTO);
    }

    /**
     * 修改用户状态
     * @param status
     * @return
     */
    @PatchMapping("/changeStatus")
    @ApiOperation("修改用户状态")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public R<Boolean> changeStatus(@RequestParam(name = "status") Integer status) {
        return R.succeed(Boolean.FALSE);
    }

    /**
     * 测试接口
     * @param body
     * @return
     */
    @PostMapping("/test")
    @ApiOperation("测试接口")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public R<Boolean> test(@RequestBody @Validated UserReqDTO body) {
        return R.succeed(Boolean.FALSE);
    }

}