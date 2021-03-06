package se.kawi.taskmanager.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import se.kawi.taskmanager.model.Issue;

public interface IssueRepository extends PagingAndSortingRepository<Issue, Long>, JpaSpecificationExecutor<Issue> {

	@Query("select i from #{#entityName} i where i.title like %:title% and i.description like %:description% and i.openIssue = :openIssue")
	Page<Issue> query(Pageable createPageRequest, @Param("title") String title, @Param("description") String description, @Param("openIssue") Boolean openIssue);

}