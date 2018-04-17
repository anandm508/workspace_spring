package com.demo.shoppingcart.aspect;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class AspectDemo {
	
	@Pointcut("execution(* com.demo.shoppingcart.service.impl.ProductServiceImpl.filter(..))")
	private void pointCut1() {
		
	}
	
	@Before(value = "pointCut1()")
	public void beforeAdvice() {
		System.out.println("**********************before advice********************************");
	}
	
	@After(value = "pointCut1()")
	public void afterAdvice(JoinPoint joinPoint) {
		System.out.println("************************after advice******************************");
	}
	
	@Around(value = "pointCut1()")
	public Object aroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
		System.out.println("logAround() is running!");
		System.out.println("hijacked method : " + joinPoint.getSignature().getName());
		System.out.println("hijacked arguments : " + Arrays.toString(joinPoint.getArgs()));

		System.out.println("Around before is running!");
		Object result = joinPoint.proceed(); //continue on the intercepted method
		System.out.println("Around after is running!");

		System.out.println("******");
		return result;
	}
	
	@AfterReturning(value = "pointCut1()", returning="result")
	public void afterReturningAdvice(Object result) {
		System.out.println("************************after returning advice******************************");
		System.out.println(result);
	}
	
	@AfterThrowing(value = "pointCut1()", throwing="result")
	public void afterThrowingAdvice(Throwable result) {
		System.out.println("************************after throwing advice******************************");
		System.out.println(result);
	}

}
