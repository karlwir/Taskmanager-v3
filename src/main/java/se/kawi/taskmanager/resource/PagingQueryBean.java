package se.kawi.taskmanager.resource;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.QueryParam;

class PagingQueryBean {

	@QueryParam("size") @DefaultValue("25") private int size;
	@QueryParam("page") @DefaultValue("0") private int page;
	@QueryParam("sort") @DefaultValue("asc") private String sort;
	
	int getSize() {
		return size;
	}
	
	int getPage() {
		return page;
	}
	
	String getSort() {
		return sort;
	}
	
}
