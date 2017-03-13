package se.kawi.taskmanager.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import se.kawi.taskmanager.model.User;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {

	Long countByTeamId(Long teamId);

	@Query("select u from #{#entityName} u where u.firstname like %:fName% and u.lastname like %:lName% and u.username like %:uName%")
	Page<User> query(Pageable pageable, @Param("fName") String firstname, @Param("lName") String lastname, @Param("uName") String username);

}
