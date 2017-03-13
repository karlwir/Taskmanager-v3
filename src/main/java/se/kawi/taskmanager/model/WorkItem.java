package se.kawi.taskmanager.model;

import java.util.Collection;

import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;

@Entity
@Table(name = "workitems")
public class WorkItem extends AbstractEntity {

	@Column(nullable = false)
	private String title;
	
	@Column(nullable = false)
	private String description;
	
	@Enumerated(EnumType.STRING)
	private Status status;
	
	@ManyToOne
	private User user;
	
	@OneToMany(mappedBy = "workItem", fetch=FetchType.EAGER)
	private Collection<Issue> issues;

	protected WorkItem() {}

	public WorkItem(String title, String description) {
		this.title = title;
		this.description = description;
		this.status = Status.UNSTARTED;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public Status getStatus() {
		return status;
	}

	public User getUser() {
		return user;
	}
	
	public boolean hasAssignUser() {
		return user != null;
	}

	public Collection<Issue> getIssues() {
		return issues;
	}

	public enum Status {
		DONE, UNSTARTED, STARTED, ARCHIVED
	}

	@Override
	public String toString() {
		String assignedUser = hasAssignUser() ? user.getUsername() : null;
		return "Workitem, title: " + title 
						+ ", description: " + description 
						+ ", status: " + status 
						+ ", assignedUserId: " + assignedUser;
	}

	public WorkItem setStatus(Status status) {
		this.status = status;
		return this;
	}

	public WorkItem setUser(User user) {
		this.user = user;
		return this;
	}

	public WorkItem setTitle(String title) {
		this.title = title;
		return this;
	}

	public WorkItem setDescription(String description) {
		this.description = description;
		return this;
	}
		
}
