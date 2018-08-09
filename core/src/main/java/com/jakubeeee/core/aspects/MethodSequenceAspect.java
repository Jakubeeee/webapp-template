package com.jakubeeee.core.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.stereotype.Component;

/**
 * If activated in common.properties, it creates logs when any method is entered or exited.
 * Moreover it logs every parameter passed to that method.
 */

@Slf4j
@Aspect
@Component
@ConditionalOnExpression("${enable.method.sequence.monitor:true}")
public class MethodSequenceAspect {

    @Around("execution( * com.jakubeeee..*.*(..))")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        LOG.info("Starting method : \"" + joinPoint.getSignature().getName() +
                "\" in class: \"" + joinPoint.getSignature().getDeclaringTypeName() + "\"");
        try {
            for (var param : joinPoint.getArgs()) LOG.info("Parameter value in above method: " + param.toString());
        } catch (NullPointerException ignored) {
        }
        Object result = joinPoint.proceed();
        LOG.info("Exiting method :\"" + joinPoint.getSignature().getName() +
                "\" in class: \"" + joinPoint.getSignature().getDeclaringTypeName() + "\"");
        return result;
    }
}