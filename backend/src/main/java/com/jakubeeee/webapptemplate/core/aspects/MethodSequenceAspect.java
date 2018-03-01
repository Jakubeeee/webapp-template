package com.jakubeeee.webapptemplate.core.aspects;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.stereotype.Component;

/**
 * If activated in app-config.properties, it creates logs when any method is entered or exited.
 * Moreover it logs every parameter passed to that method.
 */

@Slf4j
@Aspect
@Component
@ConditionalOnExpression("${enableMethodSequenceMonitor:true}")
public class MethodSequenceAspect {

    @Around("execution( * com.jakubeeee.webapptemplate..*.*(..))")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        LOG.info("Starting method : \"" + joinPoint.getSignature().getName() +
                "\" in class: \"" + joinPoint.getSignature().getDeclaringTypeName() + "\"");
        try {
            for (val param : joinPoint.getArgs()) LOG.info("Parameter value in above method: " + param.toString());
        } catch (NullPointerException ignored) {
        }
        Object result = joinPoint.proceed();
        LOG.info("Exiting method :\"" + joinPoint.getSignature().getName() +
                "\" in class: \"" + joinPoint.getSignature().getDeclaringTypeName() + "\"");
        return result;
    }
}