
package com.demo.spa.boot.mvc.rest.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.demo.spa.boot.mvc.rest.presentation.BasicMessageBean;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/i18n")
@Api(tags={"api-i18n"})
public class I18nController {
	
	@Autowired
    private MessageSource messageSource = null;

	@RequestMapping(value="/bmpb", method=RequestMethod.GET, produces={MediaType.APPLICATION_JSON_VALUE})
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	@ApiOperation(value="Get basic message presentation bean.", notes="Get basic message presentation bean.")
	public BasicMessageBean getBasicMessagePresentationBean(Locale locale) {
		BasicMessageBean basicMessageBean = new BasicMessageBean();
		basicMessageBean.setRequired(messageSource.getMessage("required", new Object[] {"required"}, locale));
		basicMessageBean.setNotFound(messageSource.getMessage("notFound", new Object[] {"notFound"}, locale));
		basicMessageBean.setDuplicate(messageSource.getMessage("duplicate", new Object[] {"duplicate"}, locale));
		basicMessageBean.setNonNumeric(messageSource.getMessage("nonNumeric", new Object[] {"nonNumeric"}, locale));
		return basicMessageBean;
	}

}
