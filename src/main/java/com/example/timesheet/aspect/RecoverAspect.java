package com.example.timesheet.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class RecoverAspect {

    @Around("@annotation(recover)")
    public Object handleRecovery(ProceedingJoinPoint jp, Recover recover) {
        try {
            return jp.proceed();
        } catch (Throwable e) {
            for (Class<?> noRecoverFor: recover.noRecoverFor()) {
                System.out.println(noRecoverFor);
                if (noRecoverFor.isAssignableFrom(e.getClass())) {
                    throw new RuntimeException(e);
                }
            }
            log.error("Recovering {}#{} after Exception[{}: \"{}\"]",
                    jp.getSignature().getDeclaringTypeName(),
                    jp.getSignature().getName(),
                    e.getMessage());

            MethodSignature methodSignature = (MethodSignature) jp.getSignature();
            Class<?> returnType = methodSignature.getReturnType();
            return getDefaultValue(returnType);
        }
    }
    private Object getDefaultValue(Class<?> returnType) {
        if (returnType.isPrimitive()) {
            if (returnType == boolean.class) return false;
            if (returnType == char.class) return "\0";
            if (returnType == byte.class || returnType == short.class || returnType == int.class) return 0;
            if (returnType == long.class) return 0L;
            if (returnType == float.class) return 0.0f;
            if (returnType == double.class) return 0.0;
        }
        return null;
    }
}
