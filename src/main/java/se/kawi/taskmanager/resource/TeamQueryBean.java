package se.kawi.taskmanager.resource;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.QueryParam;

public class TeamQueryBean extends PagingQueryBean {

	@QueryParam("name") @DefaultValue("") private String name;
	@QueryParam("active") @DefaultValue("true") private String active;

	public String getName() {
		return name;
	}
	
	public boolean isActiveTeam() {
		return Boolean.parseBoolean(active);
	}
}
