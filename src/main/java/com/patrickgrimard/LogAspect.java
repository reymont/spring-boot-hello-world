package com.patrickgrimard;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * Created by wuwf on 17/4/27.
 * 日志切面
 */
@Aspect
@Component
public class LogAspect {
    @Pointcut("execution(public * com.patrickgrimard.*.*(..))")
    public void webLog() {
    }

    @Before("webLog()")
    public void deBefore(JoinPoint joinPoint) throws Throwable {
        try {
            // 接收到请求，记录请求内容
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = attributes.getRequest();
            // 记录下请求内容
            System.out.println("URL : " + request.getRequestURL().toString());
            System.out.println("HTTP_METHOD : " + request.getMethod());
            System.out.println("RemoteIP : " + request.getRemoteAddr());
            System.out.println("RemotePort : " + request.getRemotePort());
            System.out.println("LocalIP : " + request.getLocalAddr());
            System.out.println("LocalPort : " + request.getLocalPort());

            System.out.println("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());

            String s = Arrays.toString(joinPoint.getArgs());
            if (s.length() < 3000) {
                System.out.println("ARGS : " + s);
            }

            //System.out.println(JSON.toJSONString(joinPoint.getSignature(),true));
            System.out.println(JSON.toJSONString(joinPoint.getStaticPart(), true));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void doAfterReturning(Object ret) throws Throwable {
        // 处理完请求，返回内容
        if (ret.toString().length() < 3000) {
            System.out.println("方法的返回值 : " + ret);
        }
    }

    //后置异常通知  
    @AfterThrowing("webLog()")
    public void throwss(JoinPoint jp) {
        System.out.println("方法异常时执行.....");
    }

    //后置最终通知,final增强，不管是抛出异常或者正常退出都会执行  
    @After("webLog()")
    public void after(JoinPoint jp) {
        System.out.println("方法最后执行.....");
    }

    //环绕通知,环绕增强，相当于MethodInterceptor  
    // @Around("webLog()")
    public Object arround(ProceedingJoinPoint pjp) {
        System.out.println("方法环绕start.....");
        try {
            long startTime = System.currentTimeMillis();
            Object o = pjp.proceed();
            long endTime = System.currentTimeMillis();
            System.out.println("方法环绕proceed，结果是 :" + o + " 耗时：" + (endTime - startTime));
            return o;
        } catch (Throwable e) {
            e.printStackTrace();
            return null;
        }
    }
}  