package se.kawi.taskmanager.service;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import se.kawi.taskmanager.model.Team;
import se.kawi.taskmanager.model.User;
import se.kawi.taskmanager.repository.TeamRepository;


@Component
public class TeamService extends BaseService<Team, TeamRepository> {
	
	private static final int TEAM_MAX_SIZE = 10;
	private static final int USER_MAX_TEAMS = 3;

	@Autowired
	public TeamService(TeamRepository teamRepository, ServiceTransaction serviceTransaction) {
		super(teamRepository, serviceTransaction);
	}

	public List<User> getTeamMembers(Specification<User> spec, Pageable pageable) throws ServiceException {
		return userService.query(spec, pageable);
//		return repository.findTeamMembers(id, Pageable);
	}
	
	public Team addTeamMember(User userInput, Team team) throws ServiceException {
		User user = userService.getById(userInput.getId());
		team.addUser(user);
		return save(team);
	}

	public Team removeTeamMember(User userInput, Team team) throws ServiceException {
		User user = userService.getById(userInput.getId());
		team.removeUser(user);
		return save(team);
	}

	@Override
	public Team save(Team team) throws ServiceException {
		Set<User> teamUsers = team.getUsers();
		if (teamUsers.size() >= TEAM_MAX_SIZE) {
			throw new ServiceException("Team is full");
		}
		
		Set<User> usersWithToManyTeams = teamUsers.stream().filter(u -> u.getTeams().size() > USER_MAX_TEAMS).collect(Collectors.toSet());
		if (usersWithToManyTeams.size() > 0){
			throw new ServiceException("User has to many teams: " + usersWithToManyTeams.toString());
		}
		return super.save(team);
	}

}