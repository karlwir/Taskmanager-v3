package se.kawi.taskmanager.repository;

import java.util.Collection;

import org.springframework.data.repository.PagingAndSortingRepository;

import se.kawi.taskmanager.model.Issue;

public interface IssueRepository extends PagingAndSortingRepository<Issue, Long> {

	Collection<Issue> findByWorkItemId(Long id);
}
