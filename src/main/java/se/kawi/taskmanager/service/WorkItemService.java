package se.kawi.taskmanager.service;

import java.util.List;

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

	public List<WorkItem> query(int page, int size, String sort, String title, String description, List<WorkItem.Status> status) throws ServiceException {
		return execute(() -> repository.query(createPageRequest(page, size, sort), title, description, status)).getContent();
	}
}