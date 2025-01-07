package com.example.timesheet.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Slf4j  //Slf4j - simple logging facade for java
@Aspect
@Component
public class LoginAspect {

    // Before
    // AfterTrowing
    // AfterReturning
    // After = After + AfterTrowing
    // Around ->

    @Pointcut("execution(* com.example.timesheet.service.TimesheetService.*(..))")
    public void timesheetServiceMethodsPointcut() {
        log.info("TimesheetServiceMethodsPointcut");
    }

    // Pointcut - точка входа в аспект
    @Before("execution(* com.example.timesheet.service.TimesheetService.findById(Long))")
    public void beforeTimesheetServiceFindById(JoinPoint jp) {
        logArguments(jp);
    }

    @Before("timesheetServiceMethodsPointcut()")
    public void before(JoinPoint jp) {
        logArguments(jp);
    }

    @After(value = "timesheetServiceMethodsPointcut()")
    public void after(JoinPoint jp) {
        logArguments(jp);
        log.info("After method: {}", jp.getSignature().getName());
    }

    @AfterThrowing(value = "timesheetServiceMethodsPointcut()", throwing = "exception")
    public void afterThrowing(JoinPoint jp, Exception exception) {
        logArguments(jp);
        log.info("After throwing method: {}, Exception : {}", jp.getSignature().getName(),
                exception.getClass().getName());
    }

    @AfterReturning(value = "timesheetServiceMethodsPointcut()")
    public void afterReturning(JoinPoint jp) {
        logArguments(jp);
        log.info("After returning method: {}", jp.getSignature().getName());
    }

    private void logArguments(JoinPoint jp) {
        String methodName = jp.getSignature().getName();
        Object[] args = jp.getArgs();
        StringBuilder sb = new StringBuilder(methodName).append("(");
        for (int i = 0; i < args.length; i++) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(args[i].getClass().getSimpleName()).append(" = ").append(args[i]);
        }
        sb.append(")");
        log.info(sb.toString());
    }

//    @After(value = "timesheetServiceMethodsPointcut()")
//    public void afterTimesheetServiceFindById(JoinPoint jp) {
//        String methodName = jp.getSignature().getName();
//        log.info("After -> TimesheetService#{}", methodName);
//    }

//    @AfterThrowing(value = "timesheetServiceMethodsPointcut()", throwing = "ex")
//    public void afterTimesheetServiceFindById(JoinPoint jp, Exception ex) {
//        String methodName = jp.getSignature().getName();
//        log.info("AfterThrowing -> TimesheetService#{} -> {}", methodName, ex.getClass().getName());
//    }

//    @Around(value = "timesheetServiceMethodsPointcut()")
//    public Object aroundTimesheetServiceFind(ProceedingJoinPoint pjp) throws Throwable {
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
