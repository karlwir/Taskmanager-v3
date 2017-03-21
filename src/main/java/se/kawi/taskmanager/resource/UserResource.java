package se.kawi.taskmanager.resource;

import javax.ws.rs.Path;
import javax.ws.rs.POST;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.BeanParam;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Component;

import se.kawi.taskmanager.model.User;
import se.kawi.taskmanager.model.WorkItem;
import se.kawi.taskmanager.service.UserService;

@Component
@Path("/users")
@Produces({ MediaType.APPLICATION_JSON + "; charset=UTF-8" })
@Consumes({ MediaType.APPLICATION_JSON + "; charset=UTF-8" })
public class UserResource extends BaseResource<User, UserService> {

	protected UserResource(UserService service) {
		super(service);
	}

	@POST
	public Response createUser(@Valid User entity) {
		return super.create(entity);
	}

	@GET
	@Path("/{id}")
	public Response getUser(@PathParam("id") Long id) {
		return super.byId(id);
	}

	@GET
	public Response getUsers(@BeanParam UserQueryBean userQuery) {
		return super.get(userQuery.buildSpecification(), userQuery.buildPageable());
	}
	
	@GET
	@Path("/count")
	public Response countUsers(@BeanParam UserQueryBean userQuery) {
		return super.count(userQuery.buildSpecification());
	}

	@PUT
	public Response updateUser(@Valid User entity) {
		return super.update(entity);
	}

	@DELETE
	public Response deleteUser(@Valid User entity) {
		return super.delete(entity);
	}
	
	@GET
	@Path("/{id}/workitems")
	public Response getuserWorkItems(@BeanParam WorkItemQueryBean workItemQuery, @PathParam("id") Long id) {
		return serviceRequest(() -> {
			User user = service.getById(id);
			if (user != null) {
				workItemQuery.setUser(user);
				List<WorkItem> userWorkItems = service.getUserWorkItems(workItemQuery.buildSpecification(), workItemQuery.buildPageable());
				return Response.ok().entity(userWorkItems).build();
			} else {
				return Response.status(404).build();
			}
			
		});	
	}
	
	@PUT
	@Path("/{id}/workitems")
	public Response assignWorkItem(@Valid WorkItem workItem, @PathParam("id") Long id) {
		return serviceRequest(() -> {
			User user = service.getById(id);
			if (user != null) {
				service.assignWorkItem(workItem, user);
				return Response.noContent().build();
			} else {
				return Response.status(404).build();
			}
		});
	}
	
	@DELETE
	@Path("/{id}/workitems")
	public Response withdrawWorkItem(@Valid WorkItem workItem, @PathParam("id") Long id) {
		return serviceRequest(() -> {
			User user = service.getById(id);
			if (user != null) {
				service.withdrawWorkItem(workItem, user);
				return Response.noContent().build();
			} else {
				return Response.status(404).build();
			}
		});		
	}

}