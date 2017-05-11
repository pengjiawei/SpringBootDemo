package com.example.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.parsing.Location;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author 彭家玮
 * @version V1.0
 * @className ConfigProperties
 * @description //TODO 描述这个类的作用
 * @date 2017/5/10
 */
@Component
@ConfigurationProperties(prefix = "flume")
public class ConfigProperties {
      String host;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }
}
