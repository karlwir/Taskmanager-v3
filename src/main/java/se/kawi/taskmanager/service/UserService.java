package se.kawi.taskmanager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import se.kawi.taskmanager.model.User;
import se.kawi.taskmanager.repository.UserRepository;

@Component
public class UserService extends BaseService<User, UserRepository> {

	@Autowired
	public UserService(UserRepository userRepository, ServiceTransaction serviceTransaction) {
		super(userRepository, serviceTransaction);
	}

	public List<User> query(int page, int size, String sort, String firstname, String lastname, String username, Boolean activeUser) throws ServiceException {
		return execute(() -> repository.query(createPageRequest(page, size, sort), firstname, lastname, username, activeUser)).getContent();
	}
}