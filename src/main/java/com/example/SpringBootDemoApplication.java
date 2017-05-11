package com.example;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
//没有加载到applicationcontext里面去，只能这样加载了
@ImportResource("classpath:dubbo-consumer.xml")
@EnableAspectJAutoProxy
@ComponentScan(basePackages = "com.example")
public class SpringBootDemoApplication {

	public static void main(String[] args) {
		SpringApplication springApplication = new SpringApplication(SpringBootDemoApplication.class);
		//禁用很夸张的banner
		//springApplication.setBannerMode(Banner.Mode.OFF);

		springApplication.run(args);
	}
}
