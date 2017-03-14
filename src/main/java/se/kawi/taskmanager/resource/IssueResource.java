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

import se.kawi.taskmanager.model.Issue;
import se.kawi.taskmanager.service.IssueService;


@Component
@Path("/issues")
@Produces({ MediaType.APPLICATION_JSON + "; charset=UTF-8" })
@Consumes({ MediaType.APPLICATION_JSON + "; charset=UTF-8" })
public class IssueResource extends BaseResource<Issue, IssueService> {

	protected IssueResource(IssueService service) {
		super(service);
	}

	@POST
	public Response save(@Valid Issue entity) {
		return super.create(entity);
	}

	@GET
	@Path("/{id}")
	public Response get(@PathParam("id") Long id) {
		return super.byId(id);
	}

	@GET
	public Response get(@BeanParam IssueQueryBean issueQuery) {
		return serviceRequest(() -> {
			List<Issue> entities = service.query(issueQuery.getPage(),
											    issueQuery.getSize(),
											    issueQuery.getSort(),
											    issueQuery.getTitle(),
											    issueQuery.getDescription(),
											    issueQuery.isOpenIssue());
			return Response.ok().entity(entities).build();
		});
	}

	@PUT
	public Response update(@Valid Issue entity) {
		return super.update(entity);
	}

	@DELETE
	public Response delete(@Valid Issue entity) {
		return super.delete(entity);
	}

}