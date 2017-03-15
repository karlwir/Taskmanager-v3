package se.kawi.taskmanager.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import se.kawi.taskmanager.model.WorkItem.Status;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(WorkItem.class)
public abstract class WorkItem_ extends se.kawi.taskmanager.model.AbstractEntity_ {

	public static volatile SingularAttribute<WorkItem, String> description;
	public static volatile SingularAttribute<WorkItem, String> title;
	public static volatile SingularAttribute<WorkItem, User> user;
	public static volatile CollectionAttribute<WorkItem, Issue> issues;
	public static volatile SingularAttribute<WorkItem, Status> status;

}

