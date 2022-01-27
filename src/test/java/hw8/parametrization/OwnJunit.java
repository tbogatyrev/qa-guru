package hw8.parametrization;

import org.junit.jupiter.api.*;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static java.util.Arrays.stream;

public class OwnJunit {

    static SimpleTest simpleTest;

    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        globalPrecondition(BeforeAll.class);
        runTest();
        globalPrecondition(AfterAll.class);
    }

    private static void globalPrecondition(Class<? extends Annotation> clazz) {
        stream(SimpleTest.class.getDeclaredMethods())
                .filter(method -> method.getAnnotation(clazz) != null)
                .findFirst()
                .ifPresent(method -> {
                    try {
                        method.invoke(SimpleTest.class.getDeclaredMethod(method.getName()));
                    } catch (InvocationTargetException | NoSuchMethodException | IllegalAccessException e) {
                        if (e.getCause() instanceof AssertionError) {
                            System.out.println(clazz.getSimpleName() + " " + method.getName() + " failed: " + e.getCause().getMessage());
                        }
                    }
                    System.out.println(clazz.getSimpleName() + " " + method.getName() + " passed!");
                });
    }

    private static void localPrecondition(Class<? extends Annotation> clazz) {
        stream(SimpleTest.class.getDeclaredMethods())
                .filter(method -> method.getAnnotation(clazz) != null)
                .findFirst()
                .ifPresent(method -> {
                    try {
                        method.invoke(simpleTest = simpleTest != null ? simpleTest : SimpleTest.class.getConstructor().newInstance());
                    } catch (InvocationTargetException | NoSuchMethodException | IllegalAccessException | InstantiationException e) {
                        if (e.getCause() instanceof AssertionError) {
                            System.out.println(clazz.getSimpleName() + " " + method.getName() + " failed: " + e.getCause().getMessage());
                        }
                    }
                    System.out.println(clazz.getSimpleName() + " " + method.getName() + " passed!");
                });
    }

    private static void runTest() throws IllegalAccessException, InstantiationException {
        for (Method declaredMethod : SimpleTest.class.getDeclaredMethods()) {
            Test test = declaredMethod.getAnnotation(Test.class);
            if (test != null) {
                try {
                    localPrecondition(BeforeEach.class);
                    declaredMethod.invoke(simpleTest = simpleTest != null ? simpleTest : SimpleTest.class.getConstructor().newInstance());
                } catch (InvocationTargetException | NoSuchMethodException e) {
                    if (e.getCause() instanceof AssertionError) {
                        System.out.println(Test.class.getSimpleName() + " " + declaredMethod.getName() + " failed: " + e.getCause().getMessage());
                        localPrecondition(AfterEach.class);
                        simpleTest = null;
                        continue;
                    } else {
                        System.out.println(Test.class.getSimpleName() + " " + declaredMethod.getName() + " broken: " + e.getCause().getMessage());
                        localPrecondition(AfterEach.class);
                        simpleTest = null;
                        continue;
                    }
                }
                System.out.println(Test.class.getSimpleName() + " " + declaredMethod.getName() + " passed!");
                localPrecondition(AfterEach.class);
                simpleTest = null;
            }
        }
    }
}
