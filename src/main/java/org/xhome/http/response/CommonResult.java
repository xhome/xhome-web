package org.xhome.http.response;

/**
 * @project xhome-http
 * @author jhat
 * @email cpf624@126.com
 * @date Aug 13, 201310:57:05 PM
 * @description 
 */
public class CommonResult extends MsgResult {
	
	private static final long	serialVersionUID	= -1196254114557273208L;
	protected Object				data;
	
	public CommonResult(short status, String message) {
		super(status, message);
	}
	
	public CommonResult(String message, Object data) {
		super(message);
		this.setData(data);
	}
	
	public CommonResult(short status, String message, Object data) {
		super(status, message);
		this.setData(data);
	}
	
	public void setData(Object data) {
		this.data = data;
	}
	
	public Object getData() {
		return data;
	}
	
}
