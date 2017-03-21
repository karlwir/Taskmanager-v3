package se.kawi.taskmanager.model;

import java.util.Set;

import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.OneToMany;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;

@Entity
@Table(name = "workitems")
public class WorkItem extends AbstractEntity {

	@Column(nullable = false)
	private String title;
	
	@Column(nullable = false)
	private String description;
	
	@Enumerated(EnumType.STRING)
	private Status status;
	
	@ManyToMany(mappedBy = "workItems", fetch=FetchType.EAGER)
	private Set<User> users;

	@OneToMany(mappedBy = "workItem", fetch=FetchType.EAGER)
	@JsonIgnoreProperties("workitem")
	private Set<Issue> issues;

	protected WorkItem() {}

	public WorkItem(String title, String description) {
		this.title = title;
		this.description = description;
		this.status = Status.UNSTARTED;
	}

	public String getTitle() {
		return title;
	}
	
	public WorkItem setTitle(String title) {
		this.title = title;
		return this;
	}

	public String getDescription() {
		return description;
	}
	
	public WorkItem setDescription(String description) {
		this.description = description;
		return this;
	}

	public Status getStatus() {
		return status;
	}
	
	public WorkItem setStatus(Status status) {
		this.status = status;
		return this;
	}
	
	public Set<Issue> getIssues() {
		return issues;
	}

	public Set<User> getUser() {
		return users;
	}
	
	public WorkItem setUser(Set<User> users) {
		this.users = users;
		return this;
	}
	
	public boolean hasAssignUsers() {
		return users != null;
	}

	public enum Status {
		 UNSTARTED, STARTED, DONE, ARCHIVED
	}
	
	@Override
	public String toString() {
		return String.format("Workitem: %s, %s, %s, %s", getId(), title, description, status);
	}
		
}
