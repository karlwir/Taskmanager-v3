package se.kawi.taskmanager.model;

import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@Table(name = "issues")
public class Issue extends AbstractEntity {
	
	@Column(nullable = false)
	private String title;

	@Column(nullable = false)
	private String description;
	
	@Column(nullable = false)
	private boolean openIssue;
	
	@ManyToOne
	@JsonIgnoreProperties("issues")
	private WorkItem workItem;

	protected Issue() {}

	public Issue(WorkItem workitem, String title, String description) {
		this.title = title;
		this.description = description;
		this.workItem = workitem;
		this.openIssue = true;
	}
	
	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public boolean isOpenIssue() {
		return openIssue;
	}

	public WorkItem getWorkitem() {
		return workItem;
	}
	
	public void setWorkItem(WorkItem workItem) {
		this.workItem = workItem;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}

	public Issue setDescription(String description) {
		this.description = description;
		return this;
	}

	public Issue setOpenIssue(boolean openIssue) {
		this.openIssue = openIssue;
		return this;
	}

	@Override
	public String toString() {
		return String.format("Issue: %s, %s, %s, open:%s, workitem:%s", getId(), title, description, openIssue, workItem.getId());
	}

}
