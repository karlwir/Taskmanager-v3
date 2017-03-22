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
import se.kawi.taskmanager.model.WorkItem;
import se.kawi.taskmanager.service.WorkItemService;

@Component
@Path("/workitems")
@Produces({ MediaType.APPLICATION_JSON + "; charset=UTF-8" })
@Consumes({ MediaType.APPLICATION_JSON + "; charset=UTF-8" })
public class WorkItemResource extends BaseResource<WorkItem, WorkItemService> {

	protected WorkItemResource(WorkItemService service) {
		super(service);
	}

	@POST
	public Response createWorkItem(@Valid WorkItem entity) {
		return super.create(entity);
	}

	@GET
	@Path("/{id}")
	public Response getWorkItem(@PathParam("id") Long id) {
		return super.byId(id);
	}

	@GET
	public Response getWorkItems(@BeanParam WorkItemQueryBean workItemQuery) {
		return super.get(workItemQuery.buildSpecification(), workItemQuery.buildPageable());
	}
	
	@GET
	@Path("/count")
	public Response countWorkItem(@BeanParam WorkItemQueryBean workItemQuery) {
		return super.count(workItemQuery.buildSpecification());
	}

	@PUT
	public Response updateWorkItem(@Valid WorkItem entity) {
		return super.update(entity);
	}

	@DELETE
	public Response deleteWorkItem(@Valid WorkItem entity) {
		return super.delete(entity);
	}
	
	@GET
	@Path("{id}/issues")
	public Response getWorkItemIssues(@BeanParam IssueQueryBean issueQuery, @PathParam("id") Long id) {
		return serviceRequest(() -> {
			WorkItem workItem = service.getById(id);
			if (workItem != null) {
				issueQuery.setWorkItem(workItem);
				List<Issue> workItemsIssues = service.getWorkItemIssues(issueQuery.buildSpecification(), issueQuery.buildPageable());
				return Response.ok().entity(workItemsIssues).build();
			}
			return Response.status(404).build();
		});
	}
	
	@PUT
	@Path("/{id}/issues")
	public Response addIssueToWorkItem(@Valid Issue issue, @PathParam("id") Long id) {
		return serviceRequest(() -> {
			WorkItem workItem = service.getById(id);
			if (workItem != null) {
				service.addIssueToWorkItem(issue, workItem);
				return Response.noContent().build();
			} else {
				return Response.status(404).build();
			}
		});
	}
	
	@DELETE
	@Path("/{id}/issues")
	public Response removeIssueFromWorkItem(@Valid Issue issue, @PathParam("id") Long id) {
		return serviceRequest(() -> {
			WorkItem workItem = service.getById(id);
			if (workItem != null) {
				service.removeIssueFromWorkItem(issue, workItem);
				return Response.noContent().build();
			} else {
				return Response.status(404).build();
			}
		});
	}

}














