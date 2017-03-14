package se.kawi.taskmanager.model;

import java.util.Collection;

import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@Entity
@Table(name = "teams")
public class Team extends AbstractEntity {

	@Column(nullable = false, unique = true)
	private String teamName;
	
	@Column(nullable = false)
	private boolean activeTeam;
	
	@OneToMany(mappedBy = "team", fetch=FetchType.EAGER)
	Collection<User> users;

	protected Team() {}

	public Team(String teamName) {
		this.teamName = teamName;
		this.activeTeam = true;
	}

	public String getTeamName() {
		return teamName;
	}

	public Collection<User> getUsers() {
		return users;
	}

	public boolean isActiveTeam() {
		return activeTeam;
	}

	public Team setTeamName(String teamName) {
		this.teamName = teamName;
		return this;
	}

	public Team setActiveTeam(boolean activeTeam) {
		this.activeTeam = activeTeam;
		return this;
	}

	public void setUsers(Collection<User> users) {
		this.users = users;
	}

	@Override
	public String toString() {
		return String.format("Team: %s, %s, active:%s", getId(), teamName, activeTeam);
	}

}
