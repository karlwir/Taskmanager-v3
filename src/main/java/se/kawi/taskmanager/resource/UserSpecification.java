package se.kawi.taskmanager.resource;

import org.springframework.data.jpa.domain.Specification;

import se.kawi.taskmanager.model.User;
import se.kawi.taskmanager.model.User_;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

final class UserSpecification {

	private UserSpecification() {}

	static Specification<User> build(UserQueryBean userQuery) {
		return new Specification<User>() {

			@Override
			public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

				final List<Predicate> predicates = new ArrayList<Predicate>();

				if (!userQuery.getFirstname().equals("")) {
					predicates.add(cb.like(root.get(User_.firstname), "%" + userQuery.getFirstname() + "%"));
				}
				if (!userQuery.getLastname().equals("")) {
					predicates.add(cb.like(root.get(User_.lastname), "%" + userQuery.getLastname() + "%"));
				}
				if (!userQuery.getUsername().equals("")) {
					predicates.add(cb.equal(root.get(User_.username), userQuery.getUsername()));
				}
				if (userQuery.getActiveUser().equals("true") || userQuery.getActiveUser().equals("false")) {
					predicates.add(cb.equal(root.get(User_.activeUser), Boolean.parseBoolean(userQuery.getActiveUser())));
				}
				if (userQuery.getTeamId() > 0) {
					predicates.add(cb.equal(root.get(User_.team), userQuery.getTeamId()));
				}

				return cb.and(predicates.toArray(new Predicate[predicates.size()]));
			}
		};
	}
}