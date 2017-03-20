package se.kawi.taskmanager.service;

import java.util.List;

import javax.ws.rs.WebApplicationException;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import se.kawi.taskmanager.model.AbstractEntity;
import se.kawi.taskmanager.service.ServiceException;
import se.kawi.taskmanager.service.ServiceTransaction.Action;

public abstract class BaseService<E extends AbstractEntity, R extends PagingAndSortingRepository<E, Long> & JpaSpecificationExecutor<E>> {

	protected R repository;
	protected ServiceTransaction serviceTransaction;
	
	protected BaseService(R repository, ServiceTransaction serviceTransaction) {
		this.repository = repository;
		this.serviceTransaction = serviceTransaction;
	}
	
	protected <T> T execute(Action<T> action) throws ServiceException {
		try {
			return action.execute();			
		} catch (DataIntegrityViolationException e) {
			throw new ServiceException("Execute failed: " + e.getMessage(), e, new WebApplicationException(400));
		} catch (DataAccessException e) {
			throw new ServiceException("Execute failed: " + e.getMessage(), e);
		}
	}
	
	protected <T> T transaction(Action<T> action) throws ServiceException {
		try {
			return serviceTransaction.execute(() -> action.execute());			
		} catch (DataIntegrityViolationException e) {
			throw new ServiceException("Transaction failed: " + e.getMessage(), e, new WebApplicationException(400));
		} catch (DataAccessException e) {
			throw new ServiceException("Transaction failed: " + e.getMessage(), e);
		}
	}
	
	public E save(E entity) throws ServiceException {
		return execute(() -> repository.save(entity));
	}
	
	public E getById(Long id) throws ServiceException {
		return execute(() -> repository.findOne(id));
	}
	
	public List<E> query(Specification<E> spec, Pageable pageable) throws ServiceException {
		return execute(() -> repository.findAll(spec, pageable)).getContent();
	}

	public void delete(E entity) throws ServiceException {
		transaction(() -> {
			repository.delete(entity);
			return null;
		});
	}
	
	protected Pageable createPageRequest(int page, int size, String sort) {
		if(sort.equals("desc")) {
			return new PageRequest(page, size, Direction.DESC, "id");
		}
		return new PageRequest(page, size, Direction.ASC, "id");

	}
}
