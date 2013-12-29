package org.xhome.http.response;

import java.util.List;
import java.util.Map;
import org.xhome.db.query.QueryBase;

/**
 * @project xhome-http
 * @author 	jhat
 * @email 	cpf624@126.com
 * @date 	Dec 29, 20138:40:50 PM
 * @describe 
 */
@SuppressWarnings("rawtypes")
public class DataResult extends MsgResult {

	private static final long serialVersionUID = 1171615341026344950L;
	protected long					start = 0;  // 开始位置
	protected long					limit = 20; // 单页数
	protected long					total = 0;  // 总数
	protected long					page = 1;   // 当前页
	protected long					totalPage	= 0;  // 总页数
	
	protected Map<String, Object>	parameters; // 查询参数
	protected List					results;    // 查询结果

	public DataResult(short status, String message) {
		super(status, message);
	}
	
	public DataResult(String message, QueryBase query) {
		super(message);
		this.setStart(query.getStart());
		this.setLimit(query.getLimit());
		this.setTotal(query.getTotal());
		this.setPage(query.getPage());
		this.setTotalPage(query.getTotalPage());
		this.setParameters(query.getParameters());
		this.setResults(query.getResults());
	}
	
	public DataResult(short status, String message, QueryBase query) {
		this(message, query);
		super.setStatus(status);
	}
	
	public DataResult(String message, List results) {
		super(message);
		this.setResults(results);
	}
	
	public DataResult(short status, String message, List results) {
		super(status, message);
		this.setResults(results);
	}

	public long getStart() {
		return start;
	}

	public void setStart(long start) {
		this.start = start;
	}

	public long getLimit() {
		return limit;
	}

	public void setLimit(long limit) {
		this.limit = limit;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public long getPage() {
		return page;
	}

	public void setPage(long page) {
		this.page = page;
	}
	
	public long getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(long totalPage) {
		this.totalPage = totalPage;
	}

	public Map<String, Object> getParameters() {
		return parameters;
	}

	public void setParameters(Map<String, Object> parameters) {
		this.parameters = parameters;
	}
	
	public List getResults() {
		return results;
	}

	public void setResults(List results) {
		this.results = results;
	}
	
}
