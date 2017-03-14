package se.kawi.taskmanager.resource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.QueryParam;

import se.kawi.taskmanager.model.WorkItem;
import se.kawi.taskmanager.model.WorkItem.Status;

public class WorkItemQueryBean extends PagingQueryBean {

	@QueryParam("title") @DefaultValue("") private String title;
	@QueryParam("description") @DefaultValue("") private String description;
	@QueryParam("status") @DefaultValue("") private String status;

	public String getTitle() {
		return title;
	}
	
	public String getDescription() {
		return description;
	}
	
	public List<Status> getStatus() {
		List<Status> status = new ArrayList<>();;
		if(this.status.equals("")) {
			status = Arrays.asList(Status.values());
			return status;		
		}
		try {
			status.add(WorkItem.Status.valueOf(this.status.toUpperCase()));
			return status;		
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

}
