
package com.demo.spa.boot.mvc.rest.presentation;

public class BasicMessageBean {

	private String required = null;

	private String notFound = null;

	private String duplicate = null;

	private String nonNumeric = null;

	public BasicMessageBean() {

	}

	public String getRequired() {
		return required;
	}

	public void setRequired(String required) {
		this.required = required;
	}

	public String getNotFound() {
		return notFound;
	}

	public void setNotFound(String notFound) {
		this.notFound = notFound;
	}

	public String getDuplicate() {
		return duplicate;
	}

	public void setDuplicate(String duplicate) {
		this.duplicate = duplicate;
	}

	public String getNonNumeric() {
		return nonNumeric;
	}

	public void setNonNumeric(String nonNumeric) {
		this.nonNumeric = nonNumeric;
	}

}
