package se.kawi.taskmanager.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import se.kawi.taskmanager.model.Team;

public interface TeamRepository extends PagingAndSortingRepository<Team, Long>, JpaSpecificationExecutor<Team> {


}
