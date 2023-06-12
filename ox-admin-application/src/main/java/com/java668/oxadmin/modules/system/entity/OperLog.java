package com.java668.oxadmin.modules.system.entity;

import lombok.Data;
import lombok.ToString;
import com.baomidou.mybatisplus.annotation.TableName;
import com.java668.common.db.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;

/**
 * 操作日志对象 syst_oper_log
 * 
 * @author jerry.chen
 * @date 2023-06-09 11:17:20
 */
@Data
@ToString
@TableName("syst_oper_log")
public class OperLog extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 日志主键
     */
    private Long id;
    /**
     * 模块标题
     */
    private String title;
    /**
     * 业务类型
     */
    private String businessType;
    /**
     * 方法名称
     */
    private String method;
    /**
     * 请求方式
     */
    private String requestMethod;
    /**
     * 操作类别（0其它 1后台用户 2手机端用户）
     */
    private Long operatorType;
    /**
     * 操作人员
     */
    private String operName;
    /**
     * 请求URL
     */
    private String operUrl;
    /**
     * 主机地址
     */
    private String operIp;
    /**
     * 浏览器类型
     */
    private String browser;
    /**
     * 操作系统
     */
    private String os;
    /**
     * 操作地点
     */
    private String operLocation;
    /**
     * 请求参数
     */
    private String operParam;
    /**
     * 返回参数
     */
    private String jsonResult;
    /**
     * 操作状态（0正常 1异常）
     */
    private Long status;
    /**
     * 消耗时间
     */
    private Long costTime;

}
