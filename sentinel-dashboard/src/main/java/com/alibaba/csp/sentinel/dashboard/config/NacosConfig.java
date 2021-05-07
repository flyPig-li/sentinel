package com.alibaba.csp.sentinel.dashboard.config;

import com.alibaba.cloud.nacos.NacosConfigManager;
import com.alibaba.csp.sentinel.dashboard.auth.AuthAction;
import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.FlowRuleEntity;
import com.alibaba.csp.sentinel.datasource.Converter;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.fastjson.JSON;
import com.alibaba.nacos.api.config.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @ClassName NacosConfig
 * @Description
 * @Author lifeilong
 * @Date 2021/5/6 10:11
 */
@Configuration
public class NacosConfig {

    @Autowired
    private NacosConfigManager nacosConfigManager;

    @Bean
    ConfigService configService(){
        return nacosConfigManager.getConfigService();
    }

    @Bean
    public Converter<List<FlowRule>, String> flowRuleEncoder() {
        return JSON::toJSONString;
    }

    @Bean
    public Converter<String, List<FlowRule>> flowRuleDecoder() {
        return s -> JSON.parseArray(s, FlowRule.class);
    }
}
