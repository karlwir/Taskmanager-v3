package se.kawi.taskmanager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import se.kawi.taskmanager.model.User;
import se.kawi.taskmanager.repository.UserRepository;

@Component
public class UserService extends BaseService<User, UserRepository> {
	
	private static final int USERNAME_MIN_LENGTH = 6;

	@Autowired
	public UserService(UserRepository userRepository, ServiceTransaction serviceTransaction) {
		super(userRepository, serviceTransaction);
	}
	
	public User save(User user) throws ServiceException {
		if (user.getUsername().length() >= USERNAME_MIN_LENGTH) 
			return super.save(user);
		else
			throw new ServiceException("Invalid username");	
	}

	public List<User> query(int page, int size, String sort, String firstname, String lastname, String username, Boolean activeUser) throws ServiceException {
		return execute(() -> repository.query(createPageRequest(page, size, sort), firstname, lastname, username, activeUser)).getContent();
	}
}