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
	public Response save(@Valid User entity) {
		return super.create(entity);
	}

	@GET
	@Path("/{id}")
	public Response get(@PathParam("id") Long id) {
		return super.byId(id);
	}

	@GET
	public Response get(@BeanParam PagingQueryBean pagingQuery) {
		return serviceRequest(() -> {
			List<User> entities = service.query(pagingQuery.getPage(),
											    pagingQuery.getSize(),
											    pagingQuery.getSort());
			return Response.ok().entity(entities).build();
		});
	}

	@PUT
	public Response update(@Valid User entity) {
		return super.update(entity);
	}

	@DELETE
	public Response delete(@Valid User entity) {
		return super.delete(entity);
	}

}