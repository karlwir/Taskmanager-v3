package se.kawi.taskmanager.service;

import org.springframework.stereotype.Component;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import se.kawi.taskmanager.model.Team;
import se.kawi.taskmanager.model.WorkItem;
import se.kawi.taskmanager.model.User;
import se.kawi.taskmanager.repository.TeamRepository;


@Component
public class TeamService extends BaseService<Team, TeamRepository> {
	
	private static final int TEAM_MAX_SIZE = 10;
	private static final int USER_MAX_TEAMS = 3;

	@Autowired
	public TeamService(TeamRepository teamRepository) {
		super(teamRepository);
	}

	public List<User> getTeamMembers(Specification<User> spec, Pageable pageable) throws ServiceException {
		return userService.query(spec, pageable);
	}
	
	public Team addTeamMember(User userInput, Team team) throws ServiceException {
		if (team.getUsers().size() >= TEAM_MAX_SIZE) {
			throw new ServiceException("Team is full");
		}
		
		User user = userService.getById(userInput.getId());
		if (user.getTeams().size() >= USER_MAX_TEAMS) {
			throw new ServiceException("User has to many teams: ");
		}
		
		team.addUser(user);
		return super.save(team);
	}

	public Team removeTeamMember(User userInput, Team team) throws ServiceException {
		User user = userService.getById(userInput.getId());
		team.removeUser(user);
		return super.save(team);
	}
	
	public List<WorkItem> getTeamWorkItems(Specification<WorkItem> spec, Pageable pageable) throws ServiceException {
		return workItemService.query(spec, pageable);
	}

}