package com.fengbin.crowd.mvc.config;
/*
@作者 冯彬
@时间 2020-06-06-20:08
*/

import com.fengbin.crowd.exception.AccessForbiddenException;
import com.fengbin.crowd.exception.LoginFailedException;
import com.fengbin.crowd.util.CrowdUtil;
import com.fengbin.crowd.util.ResultEntity;
import com.google.gson.Gson;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ControllerAdvice
public class CrowdExceptionResolver {


    @ExceptionHandler(value = AccessForbiddenException.class)
    public ModelAndView resolverLoginFaildException(HttpServletRequest request, HttpServletResponse response, AccessForbiddenException exception) throws IOException {
        String viewName = "admin-login";
        return getModelAndView(request, response, exception,viewName);
    }

    @ExceptionHandler(value = LoginFailedException.class)
    public ModelAndView resolverLoginFaildException(HttpServletRequest request, HttpServletResponse response, LoginFailedException exception) throws IOException {
        String viewName = "admin-login";
        return getModelAndView(request, response, exception,viewName);
    }


    @ExceptionHandler(value = NullPointerException.class)
    public ModelAndView resolverNullPointerException(HttpServletRequest request, HttpServletResponse response, NullPointerException exception) throws IOException {
        String viewName = "system-error";
        return getModelAndView(request, response, exception,viewName);
    }

    private ModelAndView getModelAndView(HttpServletRequest request, HttpServletResponse response, Exception exception,String viewName) throws IOException {
        //1.判断请求类型
        boolean result = CrowdUtil.judgeRequestType(request);
        if (result){//如果是ajax请求
            ResultEntity<Object> faild = ResultEntity.faild(exception.getMessage());
            Gson gson = new Gson();
            String json = gson.toJson(faild);
            response.getWriter().write(json);
            return null;
        }
        //不是Ajax请求
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("exception",exception);
        modelAndView.setViewName(viewName);
        return modelAndView;
    }


}
