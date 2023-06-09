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
public enum BusinessType implements ICommonEnum {
    /**
     * 其它
     */
    OTHER(0, "其它"),

    /**
     * 新增
     */
    INSERT(1, "新增"),

    /**
     * 修改
     */
    UPDATE(2, "修改"),

    /**
     * 查询
     */
    QUERY(3, "查询"),

    /**
     * 删除
     */
    DELETE(4, "删除"),

    /**
     * 授权
     */
    GRANT(5, "授权"),

    /**
     * 导出
     */
    EXPORT(6, "导出"),

    /**
     * 导入
     */
    IMPORT(7, "导入"),

    /**
     * 强退
     */
    FORCE(8, "强退"),

    /**
     * 生成代码
     */
    GEN_CODE(9, "生成代码"),

    /**
     * 清空数据
     */
    CLEAN(10, "其它"),
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
        BusinessType businessType = ICommonEnum.getEnum(item, BusinessType.class);
        return Optional.ofNullable(businessType)
                .map(v -> businessType.getCode())
                .orElse(BusinessType.OTHER.getCode());
    }
}