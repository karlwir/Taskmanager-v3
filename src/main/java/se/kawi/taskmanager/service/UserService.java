package se.kawi.taskmanager.service;

import org.springframework.stereotype.Component;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

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

	public User save(User user) throws ServiceException {
		return transaction(() -> {
			if (user.getUsername().length() < USERNAME_MIN_LENGTH) {
				throw new ServiceException("Invalid username");
			}
			if (!user.isActiveUser()) {
				// Handle users work items before making user inactive 
				Set<WorkItem> workItems = getById(user.getId()).getWorkItems();
				workItems.stream()
					 .filter(w -> !w.getStatus().equals(Status.ARCHIVED))
					 .forEach(w -> {
						try {
							w.setStatus(Status.UNSTARTED);
							w.setUser(null);
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