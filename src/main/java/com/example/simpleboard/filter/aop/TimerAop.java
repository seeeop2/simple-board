package com.example.simpleboard.filter.aop;

import com.example.simpleboard.filter.model.UserRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.util.Arrays;
import java.util.List;

@Aspect
@Component
public class TimerAop {

    @Pointcut(value = "within(com.example.simpleboard.filter.controller.UserApiController)")
    public void timerPointCut(){}

    //메소드가 실행되기 전
    @Before(value = "timerPointCut()")
    public void before(JoinPoint joinPoint){
        System.out.println("before");
    }

    //메소드가 끝난 지점
    @After(value = "timerPointCut()")
    public void after(JoinPoint joinPoint){
        System.out.println("after");
    }

    //성공했을 때(결과 값을 받을 때)
    @AfterReturning(value = "timerPointCut()", returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result){
        System.out.println("after returning");
    }

    //예외가 발생했을 때
    @AfterThrowing(value = "timerPointCut()", throwing = "tx")
    public void afterThrowing(JoinPoint joinPoint, Throwable tx){
        System.out.println("after throwing");
    }

    //메소드 실행 앞 뒤
    @Around(value = "timerPointCut()")
    public void around(ProceedingJoinPoint joinPoint) throws Throwable {

        System.out.println("메소드 실행 이전");

        Arrays.stream(joinPoint.getArgs()).forEach(it -> {
            if (it instanceof UserRequest){
                UserRequest tempUser = (UserRequest) it;
                String phoneNumber = tempUser.getPhoneNumber().replace("-", "");

                tempUser.setPhoneNumber(phoneNumber);
            }
        }
        );

        // 암/복호화 / 로깅
        List<UserRequest> newObjs = Arrays.asList(
                new UserRequest()
        );

        StopWatch stopWatch = new StopWatch();

        stopWatch.start();
        joinPoint.proceed(newObjs.toArray());
        stopWatch.stop();

        System.out.println("총 소요된 시간 : " + stopWatch.getTotalTimeMillis());

        System.out.println("메소드 실행 이후");

    }

}
