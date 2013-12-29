package org.xhome.http.response;

import org.xhome.common.constant.Status;

/**
 * @project xhome-http
 * @author jhat
 * @email cpf624@126.com
 * @date Aug 13, 201310:57:05 PM
 * @description 
 */
public class ErrorResult extends MsgResult {
	
	private static final long	serialVersionUID	= -3694537079190994308L;
	
	public ErrorResult(String message) {
		super(message);
		super.setStatus(Status.ERROR);
	}
	
}
