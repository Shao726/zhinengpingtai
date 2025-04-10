package com.dite.znpt.aspect;

import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @date 2022/1/4 7:39 上午
 * @description:
 */
@Slf4j
@Aspect
@Component
public class HttpLogAspect {

    /**
     * 换行符
     */
    private static final String LINE_SEPARATOR = System.lineSeparator();

    /**
     * 自定义切点
     */
    @Pointcut("execution(* com.dite.*.controller.*.*(..)) ")
    public void log() {
    }

    /**
     * 在切点之前织入
     *
     * @param joinPoint
     */
    @Before("log()")
    public void doBefore(JoinPoint joinPoint) throws Exception {

        // 开始打印请求日志
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 打印请求相关参数
        log.info("\n===============  Start  ===================");
        // 打印请求 url
        log.info("请求路径   : {}", request.getRequestURL().toString());
        // 打印 Http method
        log.info("请求方法   : {}", request.getMethod());
        // 打印调用 controller 的全路径以及执行方法
        log.info("调用方法   : {}.{}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
        //获取传入目标方法的参数
        log.info("请求参数   : {}", JSONUtil.toJsonStr(joinPoint.getArgs()));

    }

    /**
     * 环绕
     *
     * @param proceedingJoinPoint
     * @return
     * @throws Throwable
     */
    @Around("log()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = proceedingJoinPoint.proceed();
        // 打印出参
        log.info("响应结果  : {}", JSONUtil.toJsonStr(result));
        // 执行耗时
        log.info("请求耗时  : {} ms", System.currentTimeMillis() - startTime);

        log.info("\n=========================================== End ===========================================" + LINE_SEPARATOR);
        return result;
    }

}
