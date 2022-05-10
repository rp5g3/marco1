package com.adapit.portal.services.validation;

public abstract class AbstractValidator {

	private String name;

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

}