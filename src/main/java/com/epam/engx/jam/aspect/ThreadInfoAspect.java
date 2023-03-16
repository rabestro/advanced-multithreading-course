package com.epam.engx.jam.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import static java.lang.System.Logger.Level.INFO;

@Aspect
@Component
public final class ThreadInfoAspect {
    private static final System.Logger LOG = System.getLogger(ThreadInfoAspect.class.getSimpleName());

    @Pointcut("@annotation(com.epam.engx.jam.aspect.ThreadInfo)")
    public void threadInfo() {
    }

    @Before("threadInfo()")
    public void beforeCommand(JoinPoint joinPoint) {
        LOG.log(INFO, Thread.currentThread().getName());
    }
}
