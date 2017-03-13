package se.kawi.taskmanager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import se.kawi.taskmanager.model.Issue;
import se.kawi.taskmanager.repository.IssueRepository;


@Component
public class IssueService extends BaseService<Issue, IssueRepository> {

	@Autowired
	public IssueService(IssueRepository issueRepository, ServiceTransaction serviceTransaction) {
		super(issueRepository, serviceTransaction);
	}

	public List<Issue> query(int page, int size, String sort) throws ServiceException {
		return null;
//		TODO
//		return execute(() -> repository.query(createPageRequest(page, size, sort), firstname, lastname, namequery)).getContent();
	}
}