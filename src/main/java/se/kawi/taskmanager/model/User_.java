package se.kawi.taskmanager.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(User.class)
public abstract class User_ extends se.kawi.taskmanager.model.AbstractEntity_ {

	public static volatile SingularAttribute<User, String> firstname;
	public static volatile SingularAttribute<User, String> lastname;
	public static volatile SingularAttribute<User, String> username;
	public static volatile SetAttribute<User, WorkItem> workItems;
	public static volatile SetAttribute<User, Team> teams;
	public static volatile SingularAttribute<User, Boolean> activeUser;

}

