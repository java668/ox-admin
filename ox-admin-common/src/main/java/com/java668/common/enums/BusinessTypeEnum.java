package com.java668.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Optional;

/**
 * 业务操作类型
 *
 * @author ruoyi
 */
@Getter
@AllArgsConstructor
public enum BusinessTypeEnum implements ICommonEnum {
    /**
     * 其它
     */
    OTHER(0, "其它"),

    /**
     * 新增
     */
    INSERT(1, "新增"),

    /**
     * 删除
     */
    DELETE(2, "删除"),

    /**
     * 修改
     */
    UPDATE(3, "修改"),

    /**
     * 查询
     */
    QUERY(4, "查询"),

    /**
     * 登陆
     */
    LOGIN(5, "登陆"),

    /**
     * 登出
     */
    LOGOUT(6, "登出"),

    /**
     * 授权
     */
    GRANT(7, "授权"),

    /**
     * 导出
     */
    EXPORT(8, "导出"),

    /**
     * 导入
     */
    IMPORT(9, "导入"),

    /**
     * 强退
     */
    FORCE(10, "强退"),

    /**
     * 生成代码
     */
    GEN_CODE(11, "生成代码"),

    /**
     * 清空数据
     */
    CLEAN(12, "其它"),
    ;

    private Integer code;
    private String remark;

    /**
     * 获取 code
     *
     * @param item
     * @return
     */
    public static int getByEnum(String item) {
        BusinessTypeEnum businessType = ICommonEnum.getEnum(item, BusinessTypeEnum.class);
        return Optional.ofNullable(businessType)
                .map(v -> businessType.getCode())
                .orElse(BusinessTypeEnum.OTHER.getCode());
    }
}