package cz.ivosahlik.aspects;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.CodeSignature;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Aspect
@Component
public class LogHandler {

    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
    public void restController() {
    }

    @Before("restController()")
    public void beforeAnyRestController(JoinPoint joinPoint) {
        Map<String, Object> params = new HashMap<>();
        for (int i = 0; i < joinPoint.getArgs().length; i++) {
            params.put(((CodeSignature) joinPoint.getSignature()).getParameterNames()[i], joinPoint.getArgs()[i]);
        }
        log.info("BeforeAnyRestController: {}.", params);
    }

    @AfterReturning(pointcut = "restController()", returning = "result")
    public void afterAnyRestController(Object result) {
        log.info("AfterAnyRestController: {}.", result);
    }

    @AfterThrowing(pointcut = "restController()", throwing = "exception")
    public void afterThrowing(Throwable exception) {
        log.error(exception.getMessage());
    }
}

