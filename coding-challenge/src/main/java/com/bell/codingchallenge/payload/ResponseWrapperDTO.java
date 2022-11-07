package com.bell.codingchallenge.payload;

import java.util.Date;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class ResponseWrapperDTO {
	private HttpStatus status;
	private String message;
	private String error;
	private Object data;
	private long timeStamp;

	public ResponseWrapperDTO(String message, Object data) {
		super();
		this.message = message;
		this.data = data;
		this.timeStamp = new Date().getTime();
	}

	public ResponseWrapperDTO(String message, Object data, HttpStatus status) {
		super();
		this.status = status;
		this.message = message;
		this.data = data;
		this.timeStamp = new Date().getTime();
	}

	public ResponseWrapperDTO(HttpStatus status, String error) {
		super();
		this.status = status;
		this.error = error;
		this.timeStamp = new Date().getTime();
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public long getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}

}
