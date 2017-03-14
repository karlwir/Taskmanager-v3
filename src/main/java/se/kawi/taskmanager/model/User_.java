package se.kawi.taskmanager.model;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(User.class)
public class User_ {
	public static volatile SingularAttribute<User, String> firstname;
	public static volatile SingularAttribute<User, String> lastname;
	public static volatile SingularAttribute<User, String> username;
	public static volatile SingularAttribute<User, Boolean> activeUser;
	public static volatile SingularAttribute<User, Team> team;

}