package org.xhome.http.response;

import java.io.Serializable;

import org.xhome.common.constant.Status;

/**
 * @project xhome-http
 * @author jhat
 * @email cpf624@126.com
 * @date Aug 13, 201310:57:05 PM
 * @description 
 */
public class Result implements Serializable {
	
	private static final long	serialVersionUID	= -1196254114557273208L;
	private short				status				= Status.SUCCESS;
	private String				message;
	private Object				data;
	
	public Result(short status) {
		this.setStatus(status);
	}
	
	public Result(short status, String message) {
		this.setStatus(status);
		this.setMessage(message);
	}
	
	public Result(short status, String message, Object data) {
		this.setStatus(status);
		this.setMessage(message);
		this.setData(data);
	}
	
	public Result(String message) {
		this.setMessage(message);
	}
	
	public Result(String message, Object data) {
		this.setMessage(message);
		this.setData(data);
	}
	
	public void setStatus(short status) {
		this.status = status;
	}
	
	public short getStatus() {
		return status;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setData(Object data) {
		this.data = data;
	}
	
	public Object getData() {
		return data;
	}
	
	public boolean isSuccess() {
		return this.status == Status.SUCCESS;
	}
	
}
