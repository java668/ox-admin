package com.java668.common.utils;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpRequest;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.google.common.collect.Maps;
import com.java668.common.properties.SystemSettingProperties;
import com.mzt.logapi.util.IpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * 获取地址类
 *
 * @author ruoyi
 */
public class AddressUtils {
    private static final Logger log = LoggerFactory.getLogger(AddressUtils.class);

    /**
     * IP地址查询
     */
    public static final String IP_URL = "http://whois.pconline.com.cn/ipJson.jsp";

    /**
     * 未知地址
     */
    public static final String UNKNOWN = "XX XX";

    public static String getRealAddressByIP(String ip) {
        // 内网不查询
        if (IpUtils.internalIp(ip)) {
            return "内网IP";
        }
        SystemSettingProperties systemSettingProperties = SpringUtils.getBean(SystemSettingProperties.class);
        if (systemSettingProperties.getAddressEnabled()) {
            try {
                // "ip=" + ip + "&json=true"
                Map<String, Object> paramMap = Maps.newHashMap();
                paramMap.put("ip" , ip);
                paramMap.put("json" , true);
                String rspStr = HttpRequest.get(IP_URL).charset("GBK" ).form(paramMap).execute().body();
                if (StrUtil.isEmpty(rspStr)) {
                    log.error("获取地理位置异常 {}" , ip);
                    return UNKNOWN;
                }
                JSONObject obj = JSON.parseObject(rspStr);
                String region = obj.getString("pro" );
                String city = obj.getString("city" );
                return String.format("%s %s" , region, city);
            } catch (Exception e) {
                log.error("获取地理位置异常 {}" , ip);
            }
        }
        return UNKNOWN;
    }

}
