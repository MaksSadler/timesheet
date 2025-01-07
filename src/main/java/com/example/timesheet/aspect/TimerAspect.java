package com.example.timesheet.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import java.lang.reflect.Method;

@Slf4j
@Aspect
@Component
public class TimerAspect {

//    @Pointcut("@annotation(com.example.timesheet.aspect.Timer)")
//    public void timerPointcut() {}

    @Pointcut("@annotation(com.example.timesheet.aspect.Timer)")
    public void timesheetServiceMethodsPointcut() {}

    @Pointcut("@within(com.example.timesheet.aspect.Timer)")
    public void timesheetServiceTypePointcut() {}

    @Around(value = "timesheetServiceMethodsPointcut() || timesheetServiceTypePointcut()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
        Object target = pjp.getTarget();
        Method method = methodSignature.getMethod();

        Timer timer = null;
        if (method.isAnnotationPresent(Timer.class)) {
            timer = method.getAnnotation(Timer.class);
        } else if (target.getClass().isAnnotationPresent(Timer.class)) {
            timer = target.getClass().getAnnotation(Timer.class);
        }

        if (timer == null || !timer.enabled()) {
            return pjp.proceed();
        }

        org.slf4j.event.Level level = timer.level();
        long startTime = System.currentTimeMillis();

        try {
            return pjp.proceed();
        } finally {
            long timeTaken = System.currentTimeMillis() - startTime;
            String logMessage = String.format("Around method: %s, Time taken: %s",
                    pjp.getSignature().getName(), timeTaken);
            logAtLevel(level, logMessage);
        }
    }

    private void logAtLevel(org.slf4j.event.Level level, String message) {
        switch (level) {
            case TRACE:
                log.trace(message);
                break;
            case DEBUG:
                log.debug(message);
                break;
            case INFO:
                log.info(message);
                break;
            case WARN:
                log.warn(message);
                break;
            case ERROR:
                log.error(message);
                break;
        }
    }

//    @Around(value = "timerMethodsPointcut() || timerTypePointcut()")
//    public Object aroundTimesheetServiceFind(ProceedingJoinPoint pjp) throws Throwable {
//        // Здесь мы проверяем если наш метод не включен, то через проверку вызываем оригинальный метод.
//       MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
//       Object target = pjp.getTarget();
//       Method method = methodSignature.getMethod();
//
//       boolean enabled = true;
//
//       if (method.isAnnotationPresent(Timer.class)) {
//           enabled = method.getAnnotation(Timer.class).enabled();
//       } else if (target.getClass().isAnnotationPresent(Timer.class)) {
//            enabled = target.getClass().getAnnotation(Timer.class).enabled();
//       }
//
//       if (!enabled) {
//           return pjp.proceed();
//       }
//
//        // Чтобы был вызван оригинальный код, необходимо вызвать метод proceed();
//        long startTime = System.currentTimeMillis();
//        try {
//            return pjp.proceed();
//        } finally {
//            long duration = System.currentTimeMillis() - startTime;
//            log.info("TimesheetService#{} duration = {}ms", pjp.getSignature().getName(), duration);
//        }
//    }
}
