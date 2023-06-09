package com.java668.oxadmin.modules.system.service.impl;

import com.java668.common.utils.AuthUtils;
import com.mzt.logapi.beans.Operator;
import com.mzt.logapi.service.IOperatorGetService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Jerry.chen
 * @desc
 * @date 2023/06/09 13:48
 **/
@Slf4j
@Service
@RequiredArgsConstructor
public class OperatorGetServiceImpl implements IOperatorGetService {

    @Override
    public Operator getUser() {
        return Optional.ofNullable(AuthUtils.getCurrentUser())
                .map(a -> new Operator(a.getUsername()))
                .orElse(new Operator("" ));
    }

}