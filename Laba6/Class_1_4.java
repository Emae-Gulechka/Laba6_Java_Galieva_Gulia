package Laba6;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


// Аннотация @Validate
// Целью может быть ТИП или АННОТАЦИЯ
// Доступна во время исполнения программы
// Имеет обязательное свойство value, типа Class[]

@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@interface Validate {
    Class<?>[] value();
}


// Класс с аннотацией @Validate
// Демонстрирует указание списка типов для проверки

@Validate({String.class, Integer.class, Double.class, Boolean.class})
public class Class_1_4 {
    private String name;
    private int count;
    private double price;
    private boolean active;

    // Конструктор
    public Class_1_4(String name, int count, double price, boolean active) {
        this.name = name;
        this.count = count;
        this.price = price;
        this.active = active;
    }

    // Получить имя

    public String getName() {
        return name;
    }

    // Получить количество

    public int getCount() {
        return count;
    }

    //Получить цену
    public double getPrice() {
        return price;
    }

    // Проверить активность

    public boolean isActive() {
        return active;
    }

    //Обработчик для аннотации @Validate
    public static class ValidateHandler {
        // Выводит список классов, указанных в аннотации @Validate

        public static void printValidationClasses(Class<?> clazz) {
            System.out.println("\n=== Обработчик @Validate ===");

            if (clazz.isAnnotationPresent(Validate.class)) {
                Validate validate = clazz.getAnnotation(Validate.class);
                Class<?>[] classes = validate.value();

                System.out.println("Класс " + clazz.getSimpleName() +
                        " имеет аннотацию @Validate");
                System.out.println("Количество типов для проверки: " + classes.length);

                if (classes.length > 0) {
                    System.out.println("\nСписок типов для проверки:");
                    for (int i = 0; i < classes.length; i++) {
                        System.out.printf("  %d. %s\n", i + 1, classes[i].getSimpleName());
                    }

                    // Проверяем соответствие полей класса указанным типам
                    System.out.println("\nПроверка соответствия полей:");
                    for (java.lang.reflect.Field field : clazz.getDeclaredFields()) {
                        boolean typeFound = false;
                        for (Class<?> validType : classes) {
                            if (field.getType().equals(validType) ||
                                    (field.getType().isPrimitive() &&
                                            getWrapperClass(field.getType()).equals(validType))) {
                                typeFound = true;
                                break;
                            }
                        }
                        System.out.printf("  %s (%s) : %s\n",
                                field.getName(),
                                field.getType().getSimpleName(),
                                typeFound ? " В списке проверки" : " Не в списке");
                    }
                }
            } else {
                System.out.println("Класс " + clazz.getSimpleName() +
                        " не имеет аннотации @Validate");
            }
        }

        // Получает класс-обертку для примитивных типов
        private static Class<?> getWrapperClass(Class<?> primitiveType) {
            if (primitiveType == int.class) return Integer.class;
            if (primitiveType == double.class) return Double.class;
            if (primitiveType == boolean.class) return Boolean.class;
            if (primitiveType == char.class) return Character.class;
            if (primitiveType == byte.class) return Byte.class;
            if (primitiveType == short.class) return Short.class;
            if (primitiveType == long.class) return Long.class;
            if (primitiveType == float.class) return Float.class;
            return primitiveType;
        }
    }
}
