package se.kawi.taskmanager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import se.kawi.taskmanager.model.Team;
import se.kawi.taskmanager.repository.TeamRepository;


@Component
public class TeamService extends BaseService<Team, TeamRepository> {

	@Autowired
	public TeamService(TeamRepository teamRepository, ServiceTransaction serviceTransaction) {
		super(teamRepository, serviceTransaction);
	}

	public List<Team> query(int page, int size, String sort, String teamName, Boolean activeTeam) throws ServiceException {
		return execute(() -> repository.query(createPageRequest(page, size, sort), teamName, activeTeam)).getContent();
	}
}