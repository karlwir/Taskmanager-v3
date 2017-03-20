package se.kawi.taskmanager.service;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import se.kawi.taskmanager.model.Team;
import se.kawi.taskmanager.repository.TeamRepository;


@Component
public class TeamService extends BaseService<Team, TeamRepository> {

	@Autowired
	public TeamService(TeamRepository teamRepository, ServiceTransaction serviceTransaction) {
		super(teamRepository, serviceTransaction);
	}

}