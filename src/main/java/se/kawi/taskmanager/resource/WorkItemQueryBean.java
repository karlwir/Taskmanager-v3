package se.kawi.taskmanager.resource;

import java.util.List;
import java.util.ArrayList;

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
	
	public void setUser(User user) {
		this.user = user;
	}

	Specification<WorkItem> buildSpecification() {
		return (root, query, cb) -> {
			final List<Predicate> predicates = new ArrayList<>();

			if (!title.equals("")) {
				predicates.add(cb.like(root.get(WorkItem_.title), "%" + title + "%"));
			}
			if (!description.equals("")) {
				predicates.add(cb.like(root.get(WorkItem_.description), "%" + description + "%"));
			}
			if (!status.equals("")) {
				WorkItem.Status statusEnum;
				try {
					statusEnum = (WorkItem.Status.valueOf(this.status.toUpperCase()));
					predicates.add(cb.equal(root.get(WorkItem_.status), statusEnum));
				} catch (IllegalArgumentException e) {}				
			}	
			if (user != null) {
				predicates.add(cb.isMember(user, root.get(WorkItem_.users)));
			}
			
			return cb.and(predicates.toArray(new Predicate[predicates.size()]));
		};		
	}
	
}
