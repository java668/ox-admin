package com.java668.common.db.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.java668.common.db.properties.MybatisPlusAutoFillProperties;
import com.java668.common.utils.AuthUtil;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.util.StringUtils;

import java.util.Date;

/**
 * @author Jerry.chen
 * @desc DateMetaObjectHandler
 * @date 2023/03/29 18:05
 **/
public class DateMetaObjectHandler implements MetaObjectHandler {
    private MybatisPlusAutoFillProperties autoFillProperties;

    public DateMetaObjectHandler(MybatisPlusAutoFillProperties autoFillProperties) {
        this.autoFillProperties = autoFillProperties;
    }

    /**
     * 是否开启了插入填充
     */
    @Override
    public boolean openInsertFill() {
        return autoFillProperties.getEnableInsertFill();
    }

    /**
     * 是否开启了更新填充
     */
    @Override
    public boolean openUpdateFill() {
        return autoFillProperties.getEnableUpdateFill();
    }

    /**
     * 插入填充，字段为空自动填充
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        String createBy = (String) getFieldValByName(autoFillProperties.getCreateByField(), metaObject);
        String updateBy = (String) getFieldValByName(autoFillProperties.getUpdateByField(), metaObject);

        if (StringUtils.isEmpty(createBy) || StringUtils.isEmpty(updateBy)) {
            String username = AuthUtil.getUsername();
            // 获取当前登录用户
            if (StringUtils.isEmpty(createBy)) {
                setFieldValByName(autoFillProperties.getCreateByField(), username, metaObject);
            }

            if (StringUtils.isEmpty(updateBy)) {
                setFieldValByName(autoFillProperties.getUpdateByField(), username, metaObject);
            }
        }


        Object createTime = getFieldValByName(autoFillProperties.getCreateTimeField(), metaObject);
        Object updateTime = getFieldValByName(autoFillProperties.getUpdateTimeField(), metaObject);
        if (createTime == null || updateTime == null) {
            Date date = new Date();
            if (createTime == null) {
                setFieldValByName(autoFillProperties.getCreateTimeField(), date, metaObject);
            }
            if (updateTime == null) {
                setFieldValByName(autoFillProperties.getUpdateTimeField(), date, metaObject);
            }
        }
    }

    /**
     * 更新填充
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        setFieldValByName(autoFillProperties.getUpdateTimeField(), new Date(), metaObject);

        String updateBy = (String) getFieldValByName(autoFillProperties.getUpdateByField(), metaObject);
        if (StringUtils.isEmpty(updateBy)) {
            String username = AuthUtil.getUsername();
            setFieldValByName(autoFillProperties.getUpdateByField(), username, metaObject);
        }
    }
}