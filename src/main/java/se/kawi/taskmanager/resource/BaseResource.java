package se.kawi.taskmanager.resource;

import java.net.URI;

import javax.validation.Valid;
import javax.ws.rs.core.UriInfo;

import se.kawi.taskmanager.model.AbstractEntity;
import se.kawi.taskmanager.service.BaseService;
import se.kawi.taskmanager.service.ServiceException;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

abstract class BaseResource<E extends AbstractEntity, S extends BaseService<E, ?>> {

	protected final S service;

	@Context
	protected UriInfo uriInfo;

	protected BaseResource(S service) {
		this.service = service;
	}

	protected Response serviceRequest(ServiceRequest serviceRequest) {
		try {
			return serviceRequest.request();
		} catch (ServiceException e) {
			e.printStackTrace();
			throw e.getWebApplicationException();
		}
	}

	protected Response create(@Valid E entity) {
		return serviceRequest(() -> {
			E savedEntity = service.save(entity);
			URI location = uriInfo.getAbsolutePathBuilder().path("{id}").resolveTemplate("id", savedEntity.getId()).build();
			return Response.created(location).build();
		});
	}

	protected Response byId(Long id) {
		return serviceRequest(() -> {
			E entity = service.getById(id);
			return entity == null ? Response.status(404).build() : Response.ok(entity).build();
		});
	}
	
	protected Response update(@Valid E entity) {
		return serviceRequest(() -> {
			service.save(entity);
			return Response.noContent().build();
		});
	}

	protected Response delete(@Valid E entity) {
		return serviceRequest(() -> {
			service.delete(entity);
			return Response.noContent().build();
		});
	}

}
