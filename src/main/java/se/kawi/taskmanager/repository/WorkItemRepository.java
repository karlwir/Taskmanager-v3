package se.kawi.taskmanager.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import se.kawi.taskmanager.model.WorkItem;

public interface WorkItemRepository extends PagingAndSortingRepository<WorkItem, Long> {

	public Collection<WorkItem> findByStatus(WorkItem.Status status);

	@Query("select wi FROM #{#entityName} wi JOIN wi.user u JOIN u.team t WHERE t.id = :teamId and wi.status != 'ARCHIVED'")
	Collection<WorkItem> getWorkItemsByTeamId(@Param("teamId") Long teamId);

	@Query("Select wi from #{#entityName} wi where wi.user.id = :userId and wi.status != 'ARCHIVED'")
	public Collection<WorkItem> findByUserId(@Param("userId") Long userId);

	@Query("Select wi from #{#entityName} wi where wi.title like %:title% and wi.status != 'ARCHIVED'")
	public Collection<WorkItem> findByDTitle(@Param("title") String title);

	@Query("Select wi from #{#entityName} wi where wi.description like %:desc% and wi.status != 'ARCHIVED'")
	public Collection<WorkItem> findByDescription(@Param("desc") String description);

	@Query("select workItem from Issue i WHERE i.openIssue=1 and i.workItem.status != 'ARCHIVED'")
	public Collection<WorkItem> getAllWorkItemsWithIssues();

	@Query("Select count(wi) from #{#entityName} wi where wi.user.id = :userId and wi.status != 'ARCHIVED'")
	Long countByUserId(@Param("userId") Long userId);

	@Query("select w from #{#entityName} w where w.title like %:title% and w.description like %:description% and w.status in :status")
	Page<WorkItem> query(Pageable pageable, @Param("title") String title, @Param("description") String description, @Param("status") List<WorkItem.Status> status);

}
