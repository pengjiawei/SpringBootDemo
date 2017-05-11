package com.example.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author 彭家玮
 * @version V1.0
 * @className MyProperties
 * @description //TODO 描述这个类的作用
 * @date 2017/5/10
 */
@Component
@ConfigurationProperties(prefix = "my")
//引入自己的自定义配置文件
@PropertySource(value = "classpath:/test/my-test.properties")
public class MyProperties {
    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
