package se.kawi.taskmanager.resource;

import javax.ws.rs.core.Response;

import se.kawi.taskmanager.service.ServiceException;

interface ServiceRequest {
	Response request() throws ServiceException;
}
