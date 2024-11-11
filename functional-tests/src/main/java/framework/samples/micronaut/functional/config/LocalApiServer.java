package framework.samples.micronaut.functional.config;

import framework.samples.micronaut.Application;
import io.micronaut.context.ApplicationContext;
import io.micronaut.context.annotation.Context;
import io.micronaut.context.annotation.Requires;
import io.micronaut.context.env.Environment;
import io.micronaut.runtime.Micronaut;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Context
@Requires(notEnv = {"production", "api"})
public final class LocalApiServer {

    private Micronaut localApiMicronaut;
    private ApplicationContext localApiContext;

    public LocalApiServer() {
        createLocalApiContext();
    }

    @PostConstruct
    void startup() {
        if (localApiMicronaut != null) {
            log.debug("starting api server from functional tests");
            localApiContext = localApiMicronaut.start();
        }
    }

    @PreDestroy
    void shutdown() {
        log.debug("stopping api server from functional tests");
        if (localApiContext != null) {
            localApiContext.close();
        }
    }

    private void createLocalApiContext() {
        try {
            log.debug("creating api server from functional tests");
            localApiMicronaut = Application.runApplication(Environment.DEVELOPMENT, "api");
            log.debug("api created successfully");
        } catch (Exception e) {
            log.error("***** Failed to create the api server from fts *****");
            log.error("Exception Thrown", e);
            throw e;
        }
    }
}
