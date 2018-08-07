package com.how2java.springboot.aop;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.how2java.springboot.pojo.Food;
import com.how2java.springboot.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Aspect
@Component
@Slf4j
public class RedisAspect {
    /**
     * @Auther: ncjdjyh
     * @Date: 2018/8/5 16:08
     * @Description:Redis切面
     */
    @Autowired
    private RedisUtil redisUtil;

    @Pointcut("@annotation(com.how2java.springboot.annotation.RedisCache)")
    public void redisPointCut() {}

    @Around("redisPointCut()")
    public Object around(ProceedingJoinPoint joinPoint) {
        //前置：从Redis里获取缓存
        //先获取目标方法参数
        long startTime = System.currentTimeMillis();
        String applId = null;
        Object[] args = joinPoint.getArgs();
        if (args != null && args.length > 0) {
            applId = String.valueOf(args[0]);
        }

        //获取目标方法所在类
        String target = joinPoint.getTarget().toString();
        String className = target.split("@")[0];

        //获取目标方法的方法名称
        String methodName = joinPoint.getSignature().getName();

        //redis中key格式：    applId:方法名称
        String redisKey = applId + ":" + className + "." + methodName;
        //获取所有
        Object obj = redisUtil.get(redisKey);
        if (obj != null) {
            List<Food> foods = (List<Food>) obj;
            log.info("**********从Redis中查到了数据**********");
            log.info("Redis的KEY值:" + redisKey);
            log.info("REDIS的VALUE值:" + foods.size());
            return foods;
        }
        long endTime = System.currentTimeMillis();
        log.info("Redis缓存AOP处理所用时间:" + (endTime - startTime));
        log.info("**********没有从Redis查到数据**********");
        try {
             obj = (ArrayList<Food>) joinPoint.proceed();
            System.out.println(obj);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        log.info("**********开始从MySQL查询数据**********");
        //后置：将数据库查到的数据保存到Redis
        boolean code = redisUtil.set(redisKey, obj);
        if (code) {
            log.info("**********数据成功保存到Redis缓存!!!**********");
            log.info("Redis的KEY值:" + redisKey);
            log.info("REDIS的VALUE值:" + obj.toString());
        }
        return obj;
    }
}
