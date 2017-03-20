package se.kawi.taskmanager.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import se.kawi.taskmanager.model.Team;
import se.kawi.taskmanager.model.User;

public interface TeamRepository extends PagingAndSortingRepository<Team, Long>, JpaSpecificationExecutor<Team> {

	@Query("select t from #{#entityName} t where t.teamName like %:teamName% and t.activeTeam = :activeTeam")
	Page<Team> query(Pageable pageAble, @Param("teamName") String teamName, @Param("activeTeam") Boolean activeTeam);

	// @Query("select wi FROM #{#entityName} wi JOIN wi.user u JOIN u.team t WHERE t.id = :teamId
	@Query("select t.users from #{#entityName} t where t.id = :id")
	List<User> findTeamMembers(@Param("id") Long id, Pageable pageable);

}
