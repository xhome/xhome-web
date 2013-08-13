package org.xhome.http.response;

import org.xhome.common.constant.Status;

/**
 * @project xhome-http
 * @author jhat
 * @email cpf624@126.com
 * @date Aug 13, 201310:57:05 PM
 * @description 
 */
public class ErrorResult extends Result {
	
	private static final long	serialVersionUID	= -3694537079190994308L;
	
	public ErrorResult(String message) {
		super(message);
		super.setStatus(Status.ERROR);
	}
	
	public ErrorResult(String message, Object results) {
		super(message, results);
		super.setStatus(Status.ERROR);
	}
	
}
