package se.kawi.taskmanager.config;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

import se.kawi.taskmanager.resource.AuthFilter;
import se.kawi.taskmanager.resource.CorsFilter;
import se.kawi.taskmanager.resource.UserResource;
import se.kawi.taskmanager.resource.TeamResource;
import se.kawi.taskmanager.resource.IssueResource;
import se.kawi.taskmanager.resource.WorkItemResource;

@Configuration
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {
        register(UserResource.class);
        register(TeamResource.class);
        register(IssueResource.class);
        register(WorkItemResource.class);
        register(CorsFilter.class);
        register(AuthFilter.class);
    }
}