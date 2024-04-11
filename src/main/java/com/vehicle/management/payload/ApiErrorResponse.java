package com.vehicle.management.payload;

import org.springframework.http.HttpStatusCode;

public class ApiErrorResponse {
	
	private String message;
	private boolean success;
	private HttpStatusCode statuscode;
	
	public ApiErrorResponse(String message, boolean success, HttpStatusCode statuscode) {
		super();
		this.message = message;
		this.success = success;
		this.statuscode = statuscode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public HttpStatusCode getStatuscode() {
		return statuscode;
	}
	public void setStatuscode(HttpStatusCode statuscode) {
		this.statuscode = statuscode;
	}
	

}
