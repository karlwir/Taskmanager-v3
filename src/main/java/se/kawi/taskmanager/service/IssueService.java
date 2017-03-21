package se.kawi.taskmanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import se.kawi.taskmanager.model.Issue;
import se.kawi.taskmanager.repository.IssueRepository;


@Component
public class IssueService extends BaseService<Issue, IssueRepository> {

	@Autowired
	public IssueService(IssueRepository issueRepository) {
		super(issueRepository);
	}

}