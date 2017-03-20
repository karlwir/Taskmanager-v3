package se.kawi.taskmanager.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import se.kawi.taskmanager.model.WorkItem;

public interface WorkItemRepository extends PagingAndSortingRepository<WorkItem, Long>, JpaSpecificationExecutor<WorkItem> {

	@Query("select w from #{#entityName} w where w.title like %:title% and w.description like %:description% and w.status in :status")
	Page<WorkItem> query(Pageable pageable, @Param("title") String title, @Param("description") String description, @Param("status") List<WorkItem.Status> status);

}
