package se.kawi.taskmanager.resource;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.QueryParam;

import org.springframework.data.jpa.domain.Specification;

import se.kawi.taskmanager.model.User;

public class UserQueryBean extends PagingQueryBean {
	
	@QueryParam("firstname") @DefaultValue("") private String firstname;
	@QueryParam("lastname") @DefaultValue("") private String lastname;
	@QueryParam("username") @DefaultValue("") private String username;
	@QueryParam("active") @DefaultValue("") private String activeUser;
	@QueryParam("teamid") @DefaultValue("-1") private Long teamId;

	String getFirstname() {
		return firstname;
	}
	
	String getLastname() {
		return lastname;
	}
	
	String getUsername() {
		return username;
	}

	public String getActiveUser() {
		return activeUser.toLowerCase();
	}
	
	public Long getTeamId() {
		return teamId;
	}

	public Specification<User> getSpec() {
		return null;
	}
	
//	final class UserSpecifications {
//		
//		private EntityManager em;
//		
//		public UserSpecifications(EntityManager em) {
//			this.em = em;
//		}
//		
//		String firstname = "sven";
//		String lastname = "svensson";
//
//		CriteriaBuilder builder = em.getCriteriaBuilder();
//		CriteriaQuery<User> query = builder.createQuery(User.class);
//		Root<User> root = query.from(User.class);
//		
//		Predicate harFirstname = builder.equal(root.get(User_.firstName), firstname);
//		Predicate hasLastname = builder.equal(root.get(User_.lastname), lastname); 
//		query.where(builder.and(hasBirthday, isLongTermCustomer));
//		em.createQuery(query.select(root)).getResultList();

//		public Specification<User> userHasBirthday() {
//			return new Specification<User>() {
//				@Override
//				Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
//					return cb.equal(root.get(User_.firstName), firstname);
//			    };
//			}
//		}
		
//		public static Specification<Customer> isLongTermCustomer() {
//		    return new Specification<Customer> {
//		      public Predicate toPredicate(Root<T> root, CriteriaQuery query, CriteriaBuilder cb) {
//		        return cb.lessThan(root.get(Customer_.createdAt), new LocalDate.minusYears(2));
//		      }
//		    };
//		  }
//	}
}
