
package com.demo.spa.boot.mvc.rest.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.demo.spa.boot.mvc.rest.model.ErrRtnResult;

@ControllerAdvice
public class GlobalExceptionHandler {

	private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(Exception.class)
	@ResponseBody
	public ResponseEntity<ErrRtnResult> handleAllException(Exception ex) {
		ErrRtnResult result = new ErrRtnResult();
		log.error(ex.getMessage());
		ex.printStackTrace();
		result.getModelState().put("ERROR", ex.getMessage());
		return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
	}

}
