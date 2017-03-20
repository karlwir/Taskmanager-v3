package se.kawi.taskmanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import se.kawi.taskmanager.model.WorkItem;
import se.kawi.taskmanager.repository.WorkItemRepository;

@Component
public class WorkItemService extends BaseService<WorkItem, WorkItemRepository> {

	@Autowired
	public WorkItemService(WorkItemRepository workItemRepository, ServiceTransaction serviceTransaction) {
		super(workItemRepository, serviceTransaction);
	}
}