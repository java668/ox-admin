package com.java668.common.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Jerry.chen
 * @desc jackson JSONUtil
 * @date 2023/03/29 18:05
 **/
@SuppressWarnings("all")
public class JSONUtil {

    private static final ObjectMapper om = new ObjectMapper();


    /**
     * jackson 转json
     * @param obj
     * @return
     */
    public static String toJsonString(Object obj) {
        try {
            return om.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
