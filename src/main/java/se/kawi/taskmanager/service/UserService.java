package se.kawi.taskmanager.service;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

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

}