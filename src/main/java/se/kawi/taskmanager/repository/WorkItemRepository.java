package se.kawi.taskmanager.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import se.kawi.taskmanager.model.WorkItem;

public interface WorkItemRepository extends PagingAndSortingRepository<WorkItem, Long>, JpaSpecificationExecutor<WorkItem> {

}
