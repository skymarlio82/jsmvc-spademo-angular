
package com.demo.spa.boot.mvc.data.entity;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Locale;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.junit.Test;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

public class ValidatorTests {

	private Validator createValidator() {
		LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
		localValidatorFactoryBean.afterPropertiesSet();
		return localValidatorFactoryBean;
	}

	@Test
	public void shouldNotValidateWhenFirstNameEmpty() {
		LocaleContextHolder.setLocale(Locale.ENGLISH);
		Event event = new Event();
		event.setId(1);
		event.setTitle("UI Issue");
		event.setDescription(null);
		event.setOwner("Tom");
		event.setStatus("Opening");
		event.setFormEditable(false);
		Validator validator = createValidator();
		Set<ConstraintViolation<Event>> constraintViolations = validator.validate(event);
		ConstraintViolation<Event> violation = constraintViolations.iterator().next();
		assertThat(violation.getPropertyPath().toString()).isEqualTo("description");
		assertThat(violation.getMessage()).isEqualTo("must not be null");
	}

}
