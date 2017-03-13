package se.kawi.taskmanager.resource;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.QueryParam;

class UserQueryBean extends PagingQueryBean {
	
	@QueryParam("firstname") @DefaultValue("") private String firstname;
	@QueryParam("lastname") @DefaultValue("") private String lastname;
	@QueryParam("username") @DefaultValue("") private String username;

	String getFirstname() {
		return firstname;
	}
	
	String getLastname() {
		return lastname;
	}
	
	String getUsername() {
		return username;
	}

}
