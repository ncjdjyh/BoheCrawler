package com.how2java.springboot.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;


@Aspect
@Component
@Slf4j
public class LoggerAspect {
    /**
     * @Auther: ncjdjyh
     * @Date: 2018/8/5 16:37
     * @Description:打印日志切面
     */

    //FoodService中的所有方法
    //@Around(value = "execution(* com.how2java.springboot.service.FoodService.*(..))")
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("start log:" + joinPoint.getSignature().getName());
        Object object = joinPoint.proceed();
        log.info("end log:" + joinPoint.getSignature().getName());
        return object;
    }
}
