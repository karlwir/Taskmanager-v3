package se.kawi.taskmanager.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import se.kawi.taskmanager.model.Team;

public interface TeamRepository extends PagingAndSortingRepository<Team, Long> {

	@Query("select t from #{#entityName} t where t.teamName like %:teamName% and t.activeTeam = :activeTeam")
	Page<Team> query(Pageable pageAble, @Param("teamName") String teamName, @Param("activeTeam") Boolean activeTeam);

}
