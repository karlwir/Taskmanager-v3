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

import se.kawi.taskmanager.model.Team;
import se.kawi.taskmanager.model.User;
import se.kawi.taskmanager.service.TeamService;

@Component
@Path("/teams")
@Produces({ MediaType.APPLICATION_JSON + "; charset=UTF-8" })
@Consumes({ MediaType.APPLICATION_JSON + "; charset=UTF-8" })
public class TeamResource extends BaseResource<Team, TeamService> {

	protected TeamResource(TeamService service) {
		super(service);
	}

	@POST
	public Response createTeam(@Valid Team entity) {
		return super.create(entity);
	}

	@GET
	@Path("/{id}")
	public Response getTeam(@PathParam("id") Long id) {
		return super.byId(id);
	}

	@GET
	public Response getTeams(@BeanParam TeamQueryBean teamQuery) {
		return super.get(teamQuery.buildSpecification(), teamQuery.buildPageable());
	}

	@GET
	@Path("/count")
	public Response countTeams(@BeanParam TeamQueryBean teamQuery) {
		return super.count(teamQuery.buildSpecification());
	}

	@GET
	@Path("/{id}/users")
	public Response getTeamMembers(@BeanParam TeamQueryBean teamQuery, @PathParam("id") Long id) {
		return serviceRequest(() -> {
			List<User> teamMembers = service.getTeamMembers(id, teamQuery.buildPageable());
			return Response.ok().entity(teamMembers).build();
		});
	}

	@PUT
	@Path("/{id}/users")
	public Response addTeamMember(@Valid User user, @PathParam("id") Long id) {
		return serviceRequest(() -> {
			Team team = service.getById(id);
			if (team != null) {
				team.addUser(user);
				service.save(team);
				return Response.noContent().build();
			} else {
				return Response.status(404).build();
			}
		});
	}

	@DELETE
	@Path("/{id}/users")
	public Response removeTeamMember(@Valid User user, @PathParam("id") Long id) {
		return serviceRequest(() -> {
			Team team = service.getById(id);
			if (team != null) {
				team.removeUser(user);
				service.save(team);
				return Response.noContent().build();
			} else {
				return Response.status(404).build();
			}
		});
	}

	@PUT
	public Response updateTeam(@Valid Team entity) {
		return super.update(entity);
	}

	@DELETE
	public Response deleteTeam(@Valid Team entity) {
		return super.delete(entity);
	}

}