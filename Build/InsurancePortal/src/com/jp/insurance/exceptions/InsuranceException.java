package com.jp.insurance.exceptions;

public class InsuranceException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4626989426197010768L;

	public InsuranceException(String arg0, Throwable arg1) {
		super(arg0, arg1);

	}

	public InsuranceException(String arg0) {
		super(arg0);

	}

}
