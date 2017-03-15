package se.kawi.taskmanager.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(User.class)
public abstract class User_ extends se.kawi.taskmanager.model.AbstractEntity_ {

	public static volatile SingularAttribute<User, String> firstname;
	public static volatile CollectionAttribute<User, WorkItem> workitems;
	public static volatile SingularAttribute<User, Boolean> activeUser;
	public static volatile SingularAttribute<User, Team> team;
	public static volatile SingularAttribute<User, String> username;
	public static volatile SingularAttribute<User, String> lastname;

}

