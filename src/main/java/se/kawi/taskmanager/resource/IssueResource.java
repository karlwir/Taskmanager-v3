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
	public Response createIssue(@Valid Issue entity) {
		return super.create(entity);
	}

	@GET
	@Path("/{id}")
	public Response getIssue(@PathParam("id") Long id) {
		return super.byId(id);
	}

	@GET
	public Response getIssues(@BeanParam IssueQueryBean issueQuery) {
		return super.get(issueQuery.buildSpecification(), issueQuery.buildPageable());
	}
	
	@GET
	@Path("/count")
	public Response countIssues(@BeanParam IssueQueryBean issueQuery) {
		return super.count(issueQuery.buildSpecification());
	}

	@PUT
	public Response updateIssue(@Valid Issue entity) {
		return super.update(entity);
	}

	@DELETE
	public Response deleteIssue(@Valid Issue entity) {
		return super.delete(entity);
	}

}