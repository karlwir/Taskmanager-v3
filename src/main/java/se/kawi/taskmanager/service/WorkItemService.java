package se.kawi.taskmanager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import se.kawi.taskmanager.model.WorkItem;
import se.kawi.taskmanager.model.Issue;
import se.kawi.taskmanager.repository.WorkItemRepository;

@Component
public class WorkItemService extends BaseService<WorkItem, WorkItemRepository> {

	@Autowired
	public WorkItemService(WorkItemRepository workItemRepository) {
		super(workItemRepository);
	}

	public List<Issue> getWorkItemIssues(Specification<se.kawi.taskmanager.model.Issue> spec, Pageable pageable) throws ServiceException {
		return issueService.query(spec, pageable);
	}
}