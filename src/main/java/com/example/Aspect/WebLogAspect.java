package com.example.Aspect;

import org.apache.log4j.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Enumeration;


/**
* @className: WebLogAspect
* @description: 使用切面的方式为每一次请求打印日志,包含访问的完整路径，调用方法，传入参数，返回值，耗时等信息。
* @author 张景川
* @version V1.0 
* @date 2016年10月26日
*/
@Aspect
@Component
public class WebLogAspect {
	
    
    /**
    * 定义系统时间对象 
    */
    ThreadLocal<Long> systemTime = new ThreadLocal<Long>();
    
    /**
    * 创建log4j对象
    */
    private Logger logger = Logger.getLogger(getClass());


    /**
     * 定义切入点,controller包下的所有方法。
     *
     */
    @Pointcut("execution(public * com.example.controller.*.*(..))")
    public void webLog() {
    }

    
    /**
     * 切入点前执行函数
     *
     * @param joinPoint
     * @throws Throwable
     */
    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
    	
        // 接收到请求，记录请求内容
        systemTime.set(System.currentTimeMillis());
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        
        // 记录下请求内容
        logger.info("-->" + request.getMethod() + " : " + request.getRequestURL().toString());
        
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String name = headerNames.nextElement();
            logger.debug(name + ":" + request.getHeader(name));
        }

        // 记录下参数
        logger.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));
    }

    
    /**
     * 切入点后执行函数,记录下本次操作请求的耗时时间及返回结果
     *
     * @param result
     * @throws Throwable
     */
    @AfterReturning(returning = "result", pointcut = "webLog()")
    public void doAfterReturning(Object result) throws Throwable {
    	
        // 处理完请求，返回内容
        logger.info("<--RESPONSE(" + (System.currentTimeMillis() - systemTime.get()) + ") : " + result);
        systemTime.remove();
    }
}