package se.kawi.taskmanager.service;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

import javax.ws.rs.WebApplicationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import se.kawi.taskmanager.model.User;
import se.kawi.taskmanager.model.WorkItem;
import se.kawi.taskmanager.model.WorkItem.Status;
import se.kawi.taskmanager.repository.UserRepository;

@Component
public class UserService extends BaseService<User, UserRepository> {

	private static final int USERNAME_MIN_LENGTH = 6;

	@Autowired
	public UserService(UserRepository userRepository, ServiceTransaction serviceTransaction) {
		super(userRepository, serviceTransaction);
	}
	
	public List<WorkItem> getUserWorkItems(Specification<WorkItem> spec, Pageable pageable) throws ServiceException {
		return workItemService.query(spec, pageable);
	}
	
	public User assignWorkItem(WorkItem workItemInput, User user) throws ServiceException {
		if(user.isActiveUser()) {			
			WorkItem workItem = workItemService.getById(workItemInput.getId());
			user.addWorkItem(workItem);
			return save(user);
		} else {
			throw new ServiceException("Cant assign to inactive user", new WebApplicationException("Cant assign to inactive user", 400));
		}
	}
	
	public User withdrawWorkItem(WorkItem workItemInput, User user) throws ServiceException {
		WorkItem workItem = workItemService.getById(workItemInput.getId());
		user.removeWorkItem(workItem);
		return save(user);
	}

	@Override
	public User save(User user) throws ServiceException {
		return transaction(() -> {
			if (user.getUsername().length() < USERNAME_MIN_LENGTH) {
				throw new ServiceException("Invalid username", new WebApplicationException("Invalid username", 400));
			}
			if (!user.isActiveUser()) {
				// Handle users work items before making user inactive 
				Set<WorkItem> workItems = getById(user.getId()).getWorkItems();
				workItems.stream()
					 .filter(w -> !w.getStatus().equals(Status.ARCHIVED))
					 .forEach(w -> {
						try {
							w.setStatus(Status.UNSTARTED);
							user.removeWorkItem(w);
							workItemService.save(w);
						} catch (ServiceException e) {
							e.printStackTrace();
						}
					 });
			}
			return super.save(user);
		});
	}

}