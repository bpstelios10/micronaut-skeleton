package framework.samples.micronaut;

import io.micronaut.context.ApplicationContext;
import io.micronaut.runtime.Micronaut;

public class Application {

    public static void main(String[] args) {
        Micronaut.run(Application.class, args);
    }

    public static ApplicationContext runApplication() {
        return Micronaut.run(Application.class);
    }
}
