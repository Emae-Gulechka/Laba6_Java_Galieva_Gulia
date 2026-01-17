package Laba6;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;

// Аннотация @Default
// Целью может быть ТИП или ПОЛЕ
// Доступна во время исполнения программы
// Имеет обязательное свойство value типа Class

@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@interface Default {
    Class<?> value();
}

// Класс с аннотацией @Default
// Демонстрирует использование аннотации на уровне класса и полей

@Default(String.class)
public class Class_1_2 {
    @Default(Integer.class)
    private int id;

    @Default(Double.class)
    private double price;

    @Default(String.class)
    private String name;

    private String description; // Без аннотации


    // Конструктор с параметрами
    public Class_1_2(int id, double price, String name, String description) {
        this.id = id;
        this.price = price;
        this.name = name;
        this.description = description;
    }

    // Получить ID
    public int getId() {
        return id;
    }

    // Получить цену

    public double getPrice() {
        return price;
    }

    // Получить имя

    public String getName() {
        return name;
    }

    // Получить описание
    public String getDescription() {
        return description;
    }

    // Обработчик для аннотации @Default
    public static class DefaultHandler {
        // Выводит информацию о типах по умолчанию из аннотаций
        public static void printDefaultTypes(Object obj) {
            System.out.println("\n=== Обработчик @Default ===");
            Class<?> clazz = obj.getClass();

            // Проверяем аннотацию на классе
            if (clazz.isAnnotationPresent(Default.class)) {
                Default classAnnotation = clazz.getAnnotation(Default.class);
                System.out.println("Тип по умолчанию для класса: " +
                        classAnnotation.value().getSimpleName());
            } else {
                System.out.println("Класс не имеет аннотации @Default");
            }

            // Проверяем аннотации на полях
            System.out.println("\nТипы по умолчанию для полей:");
            for (Field field : clazz.getDeclaredFields()) {
                if (field.isAnnotationPresent(Default.class)) {
                    Default fieldAnnotation = field.getAnnotation(Default.class);
                    System.out.println("  " + field.getName() + " → " +
                            fieldAnnotation.value().getSimpleName());
                } else {
                    System.out.println("  " + field.getName() + " → нет аннотации");
                }
            }
        }
    }
}
