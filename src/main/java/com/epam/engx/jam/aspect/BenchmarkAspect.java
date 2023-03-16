package com.epam.engx.jam.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.function.Supplier;

import static java.lang.System.Logger.Level.INFO;
import static java.lang.System.Logger.Level.WARNING;
import static java.util.stream.Collectors.joining;

@Aspect
@Component
public final class BenchmarkAspect {
    private static final System.Logger LOG = System.getLogger(BenchmarkAspect.class.getSimpleName());

    private ZonedDateTime executionStart;

    @Pointcut("within(com.epam.engx.jam.command.*) && @annotation(org.springframework.shell.standard.ShellMethod)")
    public void shellMethod() {
    }

    @Before("shellMethod()")
    public void beforeCommand(JoinPoint joinPoint) {
        LOG.log(INFO, commandWithArgs(joinPoint));
        executionStart = ZonedDateTime.now();
    }

    @AfterReturning("shellMethod()")
    public void afterSuccessful(JoinPoint joinPoint) {
        var executionTime = ChronoUnit.MILLIS.between(executionStart, ZonedDateTime.now());
        var command = command(joinPoint);
        LOG.log(INFO, "{0} successful processed.", command);
        LOG.log(INFO, "{0} exec time: {1, Number}ms", command, executionTime);
    }

    @AfterThrowing("shellMethod()")
    public void afterThrowing(JoinPoint joinPoint) {
        LOG.log(WARNING, "{0} fails.", command(joinPoint));
    }

    private String command(JoinPoint joinPoint) {
        var signature = (MethodSignature) joinPoint.getSignature();
        return signature.getMethod().getName();
    }

    private Supplier<String> commandWithArgs(JoinPoint joinPoint) {
        var parameters = Arrays.stream(joinPoint.getArgs())
            .map(Object::toString)
            .collect(joining(", ", "(", ")"));
        return () -> command(joinPoint) + " " + parameters;
    }
}
