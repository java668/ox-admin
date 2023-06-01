package com.java668.common.db.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.java668.common.db.properties.MybatisPlusAutoFillProperties;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * @author Jerry.chen
 * @desc MybatisPlusAutoConfigure
 * @date 2022/09/07 18:35
 **/
@EnableConfigurationProperties({MybatisPlusAutoFillProperties.class})
@MapperScan(basePackages = {"com.java668.**.mapper"})
public class MybatisPlusAutoConfigure {


    @Autowired
    private MybatisPlusAutoFillProperties autoFillProperties;


    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        // 乐观锁插件
        interceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());
        // 分页插件。如果你不配置，分页插件将不生效 指定数据库方言为 MYSQL
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = "java668.record-platform.mybatis-plus.auto-fill", name = "enabled", havingValue = "true", matchIfMissing = true)
    public MetaObjectHandler metaObjectHandler() {
        return new DateMetaObjectHandler(autoFillProperties);
    }

    /**
     * 批量插入
     * @return
     */
    @Bean
    public EasySqlInjector easySqlInjector() {
        return new EasySqlInjector();
    }

}