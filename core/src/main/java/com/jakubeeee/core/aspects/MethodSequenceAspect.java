package com.jakubeeee.core.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.stereotype.Component;

import static java.util.Objects.requireNonNullElse;

/**
 * If activated in common.properties, it creates logs when any method is entered or exited.
 * Moreover, it logs every parameter passed to that method.
 */

@Slf4j
@Aspect
@Component
@ConditionalOnExpression("${enable.method.sequence.monitor:true}")
public class MethodSequenceAspect {

    @Around("execution( * com.jakubeeee..*.*(..))")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        LOG.debug("Starting method : \"" + joinPoint.getSignature().getName() +
                "\" in class: \"" + joinPoint.getSignature().getDeclaringTypeName() + "\"");
        for (var param : joinPoint.getArgs())
            LOG.debug("Parameter value in above method: " + requireNonNullElse(param.toString(), param));
        Object result = joinPoint.proceed();
        LOG.debug("Exiting method :\"" + joinPoint.getSignature().getName() +
                "\" in class: \"" + joinPoint.getSignature().getDeclaringTypeName() + "\"" +
                " with return value: \"" + result + "\"");
        return result;
    }
}