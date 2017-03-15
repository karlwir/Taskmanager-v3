package se.kawi.taskmanager.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Team.class)
public abstract class Team_ extends se.kawi.taskmanager.model.AbstractEntity_ {

	public static volatile SingularAttribute<Team, String> teamName;
	public static volatile SingularAttribute<Team, Boolean> activeTeam;
	public static volatile CollectionAttribute<Team, User> users;

}

