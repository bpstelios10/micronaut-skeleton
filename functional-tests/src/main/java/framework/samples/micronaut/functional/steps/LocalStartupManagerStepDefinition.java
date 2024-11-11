package framework.samples.micronaut.functional.steps;

import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import io.micronaut.context.ApplicationContext;
import lombok.extern.slf4j.Slf4j;

import static java.lang.System.exit;

@Slf4j
public class LocalStartupManagerStepDefinition {

    public static ApplicationContext applicationContext;

    @BeforeAll
    public static void startup() {
        try {
            log.info("starting server from functional tests");
            applicationContext = ApplicationContext.run("dev");
            log.info("application started successfully");
        } catch (Exception e) {
            log.error("***** Failed to start the application server from fts *****");
            log.error("Exception Thrown with message [{}]: ", e.getMessage(), e);
            exit(1);
        }
    }

    @AfterAll
    public static void shutdown() {
        log.info("stopping server from functional tests");
        if (applicationContext != null) applicationContext.close();
    }
}
