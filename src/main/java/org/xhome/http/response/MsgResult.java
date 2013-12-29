package org.xhome.http.response;

import java.io.Serializable;

import org.xhome.common.constant.Status;

/**
 * @project xhome-http
 * @author 	jhat
 * @email 	cpf624@126.com
 * @date 	Dec 29, 20138:34:24 PM
 * @describe 
 */
public class MsgResult implements Serializable {
	
	private static final long	serialVersionUID	= -1196254114557273208L;
	protected short				status				= Status.SUCCESS;
	protected String			message;
	
	public MsgResult(short status) {
		this.setStatus(status);
	}
	
	public MsgResult(String message) {
		this.setMessage(message);
	}
	
	public MsgResult(short status, String message) {
		this.setStatus(status);
		this.setMessage(message);
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
	
	public boolean isSuccess() {
		return this.status == Status.SUCCESS;
	}
	
}
