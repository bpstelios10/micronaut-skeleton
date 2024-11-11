package framework.samples.micronaut.functional.config;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.commons.lang3.reflect.MethodUtils;

import framework.samples.micronaut.functional.steps.LocalStartupManagerStepDefinition;
import io.micronaut.context.ApplicationContext;
import io.micronaut.context.annotation.Property;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Inject;
import lombok.SneakyThrows;
import net.serenitybdd.model.di.DependencyInjector;

public class MicronautDependencyInjector implements DependencyInjector {

    @SneakyThrows
    public void injectDependenciesInto(Object target) {
        injectFields(target);
        injectMethods(target);
    }

    @Override
    public void reset() {}

    private static void injectFields(Object target) throws IllegalAccessException {
        ApplicationContext applicationContext = LocalStartupManagerStepDefinition.applicationContext;

        for (Field declaredField : FieldUtils.getAllFieldsList(target.getClass())) {
            if (declaredField.getAnnotation(Inject.class) != null) {
                Object value = applicationContext.getBean(declaredField.getType());
                if (value != null) {
                    declaredField.setAccessible(true);
                    declaredField.set(target, value);
                }
                continue;
            }

            Property annotation = declaredField.getAnnotation(Property.class);
            if (annotation != null) {
                var maybeValue =
                        applicationContext.getProperty(annotation.name(), declaredField.getType());
                if (maybeValue.isPresent()) {
                    Object value = maybeValue.get();
                    declaredField.setAccessible(true);
                    declaredField.set(target, value);
                }
            }
        }
    }

    private static void injectMethods(Object target)
            throws IllegalAccessException, InvocationTargetException {
        for (Method method :
                MethodUtils.getMethodsListWithAnnotation(
                        target.getClass(), PostConstruct.class, true, true)) {
            method.setAccessible(true);
            method.invoke(target);
        }
    }
}
