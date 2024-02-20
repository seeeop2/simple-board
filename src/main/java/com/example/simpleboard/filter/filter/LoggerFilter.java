package com.example.simpleboard.filter.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;

@Slf4j
//@Component
public class LoggerFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        //진입 전
        log.info(">>>>>진입");

        ContentCachingRequestWrapper req = new ContentCachingRequestWrapper((HttpServletRequest) request);
        ContentCachingResponseWrapper res = new ContentCachingResponseWrapper((HttpServletResponse) response);

/*
        chain.doFilter(request,response); //UserRequest(name=null, phoneNumber=null, email=temp@gmail.com, age=100)
        chain.doFilter(req,res);    //컨트롤러에서 전달 받는 request 와 response는 한번 변환된 req 와 res 이다.
                                    //UserRequest(name=null, phoneNumber=null, email=temp@gmail.com, age=100)
*/

        chain.doFilter(req,res);

        String reqJson = new String(req.getContentAsByteArray());
        log.info("req : {}",reqJson);

        String resJson = new String(res.getContentAsByteArray());
        log.info("res : {}",resJson);


        log.info("<<<<<리턴");
        //진입 후

        res.copyBodyToResponse();   //이게 없으면 api 받을 때 response 확인 안된다.
    }
}
