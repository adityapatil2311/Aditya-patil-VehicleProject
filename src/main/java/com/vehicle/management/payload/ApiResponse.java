package com.vehicle.management.payload;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;

public class ApiResponse<T> {
	
	private T data;
	private List<T> dataList;
	private Page<T> dataPage;
	private String message;
	private HttpStatus httpStatus;
	private boolean error;
	public ApiResponse(T data, List<T> dataList, Page<T> dataPage, String message, HttpStatus httpStatus,
			boolean error) {
		super();
		this.data = data;
		this.dataList = dataList;
		this.dataPage = dataPage;
		this.message = message;
		this.httpStatus = httpStatus;
		this.error = error;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public List<T> getDataList() {
		return dataList;
	}
	public void setDataList(List<T> dataList) {
		this.dataList = dataList;
	}
	public Page<T> getDataPage() {
		return dataPage;
	}
	public void setDataPage(Page<T> dataPage) {
		this.dataPage = dataPage;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}
	public boolean isError() {
		return error;
	}
	public void setError(boolean error) {
		this.error = error;
	}
	
	
	
	

}
