package com.springmqdemo.springmvc.exception;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class DemoExceptionHandler {
	
	private static final Logger LOG = LoggerFactory.getLogger(DemoExceptionHandler.class);

	@ExceptionHandler(DemoException.class)
	public ModelAndView handleEmployeeNotFoundException(HttpServletRequest request, Exception ex){
		LOG.error("Requested URL="+request.getRequestURL());
		LOG.error("Exception Raised="+ex);
		
		ModelAndView modelAndView = new ModelAndView();
	    modelAndView.addObject("exception", ex);
	    modelAndView.addObject("url", request.getRequestURL());
	    
	    modelAndView.setViewName("error");
	    return modelAndView;
	}
	
	@ExceptionHandler(Exception.class)
	public ModelAndView handleAnyException(HttpServletRequest request, Exception ex){
		LOG.error("Requested URL="+request.getRequestURL());
		LOG.error("Exception Raised="+ex);
		
		ModelAndView modelAndView = new ModelAndView();
	    modelAndView.addObject("exception", ex);
	    modelAndView.addObject("url", request.getRequestURL());
	    
	    modelAndView.setViewName("error");
	    return modelAndView;
	}
}
