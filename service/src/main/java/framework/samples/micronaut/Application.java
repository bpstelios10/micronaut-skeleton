package framework.samples.micronaut;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.runtime.Micronaut;

public class Application {

    public static void main(String[] args) {
        Micronaut.run(args);
    }

    public static @NonNull Micronaut runApplication(String... environments) {
        return Micronaut.build(new String[] {}).environments(environments);
    }
}
