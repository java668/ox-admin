package com.java668.oxadmin.modules.system.controller;

import cn.hutool.core.lang.tree.Tree;
import com.java668.common.model.PageParam;
import com.java668.common.model.PageResult;
import com.java668.common.model.R;
import com.java668.common.model.SysUser;
import com.java668.common.utils.AuthUtils;
import com.java668.oxadmin.modules.system.service.AuthService;
import com.java668.oxadmin.modules.system.service.MenuService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Jerry.chen
 * @desc 用户授权
 * @date 2023/02/01 16:32
 **/
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/auth")
public class AuthController {

    private final MenuService menuService;
    private final AuthService authService;

    /**
     * 获取登陆用户信息
     *
     * @return
     */
    @ApiOperation("获取登陆用户信息")
    @GetMapping("/info")
    public R<SysUser> userInfo() {
        return R.success(AuthUtils.getCurrentUser());
    }

    /**
     * 获取路由列表
     *
     * @return
     */
    @ApiOperation("获取路由列表")
    @GetMapping("/routerList")
    public R<List<Tree<Long>>> routerList() {
        return R.success(menuService.routerList());
    }

    /**
     * @return
     */
    @ApiOperation("在线用户列表")
    @GetMapping("/onlineUser")
    public R<PageResult<SysUser>> onlineUser(PageParam pageParam) {
        return R.success(authService.onlineUser(pageParam));
    }

}