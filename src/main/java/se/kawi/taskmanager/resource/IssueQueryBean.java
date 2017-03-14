package se.kawi.taskmanager.resource;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.QueryParam;

public class IssueQueryBean extends PagingQueryBean {
	
	@QueryParam("title") @DefaultValue("") private String title;
	@QueryParam("description") @DefaultValue("") private String description;
	@QueryParam("workitemid") @DefaultValue("") private String workitemid;
	@QueryParam("open") @DefaultValue("true") private String open;
	
	public String getTitle() {
		return title;
	}
	
	public String getDescription() {
		return description;
	}
	
	public String getWorkItemId() {
		return workitemid;
	}

	public Boolean isOpenIssue() {
		return Boolean.parseBoolean(open);
	}
}
