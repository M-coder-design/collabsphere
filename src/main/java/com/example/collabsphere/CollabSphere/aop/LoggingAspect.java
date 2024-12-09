package com.example.collabsphere.CollabSphere.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Around("execution(* com.example.collabsphere.CollabSphere.service..*(..))")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().getName();
        logger.info("Method {} started", methodName);

        long startTime = System.currentTimeMillis();

        Object result;

        try {
            result = joinPoint.proceed();
        } catch (Exception ex) {
            logger.error("Exception in method {}: {}", methodName, ex.getMessage());
            throw ex;
        }

        long endTime = System.currentTimeMillis();
        logger.info("Method {} completed in {} ms", methodName, (endTime - startTime));
        logger.info("Method {} returned: {}", methodName, result);

        return result;
    }
}
