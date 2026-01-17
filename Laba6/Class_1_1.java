package Laba6;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;

// Аннотация @Invoke
// Целью может быть только МЕТОД
// Доступна во время исполнения программы
// Не имеет свойств

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@interface Invoke {
    // Пустая аннотация без свойств
}

// Класс для демонстрации аннотации @Invoke
// Содержит несколько методов, аннотированных @Invoke
public class Class_1_1 {
    private int executionCount = 0;

    // Метод с аннотацией @Invoke
    @Invoke
    public void annotatedMethod1() {
        executionCount++;
        System.out.println("   Метод annotatedMethod1() выполнен. Вызов №" + executionCount);
    }

    // Второй метод с аннотацией @Invoke
    @Invoke
    public void annotatedMethod2() {
        System.out.println("   Метод annotatedMethod2() выполнен. Сумма 5 + 3 = " + (5 + 3));
    }

    // Обработчик, который находит и вызывает методы с аннотацией @Invoke
    public static class InvokeHandler {
        // Автоматически вызывает все методы с аннотацией @Invoke
        public static void invokeAnnotatedMethods(Object obj) {
            System.out.println("\n=== Обработчик @Invoke ===");
            System.out.println("Класс: " + obj.getClass().getSimpleName());

            int invokedCount = 0;
            Method[] methods = obj.getClass().getDeclaredMethods();

            for (Method method : methods) {
                if (method.isAnnotationPresent(Invoke.class)) {
                    try {
                        System.out.print("Найден метод: " + method.getName() + ":");
                        method.setAccessible(true);

                        if (method.getParameterCount() == 0) {
                            method.invoke(obj);
                        } else if (method.getParameterCount() == 1 &&
                                method.getParameterTypes()[0] == String.class) {
                            method.invoke(obj, "автоматически переданный параметр");
                        }
                        invokedCount++;
                    } catch (Exception e) {
                        System.out.println("Ошибка при вызове метода: " + e.getMessage());
                    }
                }
            }

            System.out.println("Всего вызвано методов с @Invoke: " + invokedCount);
        }
    }
}
