package se.kawi.taskmanager.service;

import org.springframework.stereotype.Component;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;

import se.kawi.taskmanager.model.Team;
import se.kawi.taskmanager.model.User;
import se.kawi.taskmanager.repository.TeamRepository;


@Component
public class TeamService extends BaseService<Team, TeamRepository> {

	@Autowired
	public TeamService(TeamRepository teamRepository, ServiceTransaction serviceTransaction) {
		super(teamRepository, serviceTransaction);
	}

	public List<User> getTeamMembers(Long id, Pageable Pageable) {
		return repository.findTeamMembers(id, Pageable);
	}

}