package se.kawi.taskmanager.resource;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.QueryParam;
import javax.ws.rs.DefaultValue;
import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import se.kawi.taskmanager.model.Issue;
import se.kawi.taskmanager.model.Issue_;

public class IssueQueryBean extends BaseQueryBean {
	
	@QueryParam("title") @DefaultValue("") private String title;
	@QueryParam("description") @DefaultValue("") private String description;
	@QueryParam("open") @DefaultValue("") private String openissue;

	Specification<Issue> buildSpecification() {
		return (root, query, cb) -> {

			List<Predicate> predicates = new ArrayList<>();

			if (!title.equals("")) {
				predicates.add(cb.like(root.get(Issue_.title), "%" + title + "%"));
			}
			if (!description.equals("")) {
				predicates.add(cb.like(root.get(Issue_.description), "%" + description + "%"));
			}
			if (openissue.toLowerCase().equals("true") || openissue.toLowerCase().equals("false")) {
				predicates.add(cb.equal(root.get(Issue_.openIssue), Boolean.parseBoolean(openissue)));
			}

			return cb.and(predicates.toArray(new Predicate[predicates.size()]));

		};
	}

}
