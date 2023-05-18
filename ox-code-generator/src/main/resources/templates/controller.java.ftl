package ${package.Controller};

import org.springframework.validation.annotation.Validated;
import ${package.Entity}.${entity};
import ${package.Service}.*;

import com.java668.common.model.R;
import com.java668.common.model.PageParam;
import com.java668.common.model.PageResult;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.*;

<#if restControllerStyle>
import org.springframework.web.bind.annotation.RestController;
<#else>
import org.springframework.stereotype.Controller;
</#if>
<#if superControllerClassPackage??>
import ${superControllerClassPackage};
</#if>

/**
 * <p>
    * ${table.comment!} 前端控制器
    * </p>
 *
 * @author ${author}
 * @date ${date}
 */

@Slf4j
@RequiredArgsConstructor
<#if restControllerStyle>
@RestController
<#else>
@Controller
</#if>
@RequestMapping("<#if package.ModuleName??>/${package.ModuleName}</#if>/<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>")
<#if kotlin>
class ${table.controllerName}<#if superControllerClass??> : ${superControllerClass}()</#if>
<#else>
    <#if superControllerClass??>
public class ${table.controllerName} extends ${superControllerClass} {
    <#else>
public class ${table.controllerName} {
    </#if>

    private final ${table.serviceName} ${table.entityPath}Service;

    /**
     * 功能描述:单个获取 (仅作参考)
     * @author: ${author}
     * @date: ${date}
     */
    @GetMapping("/getById/{id}")
    @ApiOperation("查询详情")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public R<${entity}> getById(@PathVariable(name = "id") Long id) {
        ${entity} ${table.entityPath} = ${table.entityPath}Service.getById(id);
        return R.succeed(${table.entityPath});
    }


    /**
     * 条件 分页查询 (仅作参考)
     * @author: ${author}
     * @date: ${date}
     */
    @GetMapping("/page")
    @ApiOperation("分页查询用户列表")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public R<PageResult<${entity}>> page(PageParam params) {
        PageResult<${entity}> iPage = null; //${table.entityPath}Service.page(params);
        return R.succeed(iPage);
    }

    /**
	 * 新增 (仅作参考)
     * @author: ${author}
     * @date: ${date}
	 */
    @PostMapping
    @ApiOperation("新增")
    @PreAuthorize("hasRole('ADMIN')")
    public R<Boolean> add(@RequestBody @Validated ${entity} body) {
        Boolean result = ${table.entityPath}Service.save(body);
        return R.succeed(result);
    }

	/**
	 * 更新 (仅作参考)
     * @author: ${author}
     * @date: ${date}
	 */
    @PatchMapping
    @ApiOperation("更新用户")
    @PreAuthorize("hasRole('ADMIN')")
    public R<Boolean> update(@RequestBody @Validated ${entity} body) {
        Boolean result = ${table.entityPath}Service.updateById(body);
        return R.succeed(result);
    }

	/**
	 * 删除 (仅作参考)
     * @author: ${author}
     * @date: ${date}
	 */
    @DeleteMapping
    @ApiOperation("删除用户")
    @PreAuthorize("hasRole('ADMIN')")
    public R<Boolean> delete(@RequestParam(name = "ids") List<Long> ids) {
          Boolean result = ${table.entityPath}Service.removeByIds(ids);
          return R.succeed(result);
    }
}
</#if>
