package com.example.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.example.properties.ConfigProperties;
import com.example.properties.MyProperties;
import com.iflyzunhong.vvm3.common.definition.Result;
import com.iflyzunhong.vvm3.redismgrapi.RedisMgr;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @author 彭家玮
 * @version V1.0
 * @className ChangePwdController
 * @description //TODO 内置段修改密码指令
 * @date 2016年11月24日
 */
@RestController
@EnableAutoConfiguration
//@EnableConfigurationProperties(ConfigProperties.class)      暂时未解决，加上这句会报重复的错
public class ChangePwdController {
    private final static Logger logger = Logger.getLogger(ChangePwdController.class);
    @Autowired
    ConfigProperties config;
    @Reference
    RedisMgr redisMgr;
    @Autowired
    MyProperties my;
    @RequestMapping(value = "/changepwdhttp",method = RequestMethod.POST)
    public String changepwd(@RequestHeader("Key") String key
    ) throws IOException {
       logger.info("redismgr = "+redisMgr);
        logger.info("test = "+ config.getHost());
        logger.info("my name = "+my.getName());
        Result<String> stringResult = redisMgr.codis_get("",key);
        return null;
    }
}
