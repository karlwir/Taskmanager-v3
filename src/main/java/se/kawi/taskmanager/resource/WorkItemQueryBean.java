package se.kawi.taskmanager.resource;

import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.util.HashSet;

import javax.persistence.criteria.Predicate;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.QueryParam;

import org.springframework.data.jpa.domain.Specification;

import se.kawi.taskmanager.model.User;
import se.kawi.taskmanager.model.WorkItem;
import se.kawi.taskmanager.model.WorkItem_;

public class WorkItemQueryBean extends BaseQueryBean {

	@QueryParam("title") @DefaultValue("") private String title;
	@QueryParam("description") @DefaultValue("") private String description;
	@QueryParam("status") @DefaultValue("") private String status;
	
	private User user;
	
	private Set<User> users;
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public void setUsers(Set<User> users) {
		this.users = users;
	}

	Specification<WorkItem> buildSpecification() {
		return (root, query, cb) -> {
			final List<Predicate> andPredicates = new ArrayList<>();

			if (!title.equals("")) {
				andPredicates.add(cb.like(root.get(WorkItem_.title), "%" + title + "%"));
			}
			if (!description.equals("")) {
				andPredicates.add(cb.like(root.get(WorkItem_.description), "%" + description + "%"));
			}
			if (!status.equals("")) {
				WorkItem.Status statusEnum;
				try {
					statusEnum = (WorkItem.Status.valueOf(this.status.toUpperCase()));
					andPredicates.add(cb.equal(root.get(WorkItem_.status), statusEnum));
				} catch (IllegalArgumentException e) {}				
			}	
			if (user != null) {
				andPredicates.add(cb.isMember(user, root.get(WorkItem_.users)));
			}
			if (users != null) {	
				Set<Predicate> orPredicates = new HashSet<>();
				
				for(User user : users) {
					List<Predicate> pandPedicatesCopy = new ArrayList<>();
					pandPedicatesCopy.addAll(andPredicates);
					pandPedicatesCopy.add(cb.isMember(user, root.get(WorkItem_.users)));
					orPredicates.add(cb.and(pandPedicatesCopy.toArray(new Predicate[pandPedicatesCopy.size()])));
				}
				
				return cb.or(orPredicates.toArray(new Predicate[orPredicates.size()]));
			}
			
			return cb.and(andPredicates.toArray(new Predicate[andPredicates.size()]));
		};		
	}
	
}
