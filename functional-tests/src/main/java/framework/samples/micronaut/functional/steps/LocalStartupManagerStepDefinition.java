package framework.samples.micronaut.functional.steps;

import framework.samples.micronaut.Application;
import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import io.micronaut.context.ApplicationContext;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static java.lang.System.exit;

@Slf4j
public class LocalStartupManagerStepDefinition {

    private static final Logger log = LoggerFactory.getLogger(LocalStartupManagerStepDefinition.class);
    private static ApplicationContext applicationContext;

    @BeforeAll
    public static void startup() {
        //ConfiguredEnvironment.getConfiguration();
        try {
            log.info("starting server from functional tests");
            applicationContext = Application.runApplication();
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
