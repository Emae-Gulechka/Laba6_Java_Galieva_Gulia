package Laba6;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// Аннотация @Two
// Целью может быть ТИП
// Доступна во время исполнения программы
// Имеет два обязательных свойства: first типа String и second типа int

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@interface Two {
    String first();
    int second();
}

// Класс с аннотацией @Two
// Демонстрирует аннотацию с двумя обязательными свойствами

@Two(first = "Пример строкового значения", second = 42)
public class Class_1_5 {
    private String data;
    private int value;

    //Конструктор по умолчанию

    public Class_1_5() {
        this.data = "default";
        this.value = 0;
    }

    //Конструктор с параметрами

    public Class_1_5(String data, int value) {
        this.data = data;
        this.value = value;
    }

    //Получить данные

    public String getData() {
        return data;
    }

    //Получить значение

    public int getValue() {
        return value;
    }

    //Установить данные

    public void setData(String data) {
        this.data = data;
    }

    //Установить значение

    public void setValue(int value) {
        this.value = value;
    }

    //Загружает значения из аннотации @Two

    public void loadFromAnnotation() {
        if (this.getClass().isAnnotationPresent(Two.class)) {
            Two annotation = this.getClass().getAnnotation(Two.class);
            this.data = annotation.first();
            this.value = annotation.second();
            System.out.println("Значения загружены из аннотации @Two");
        }
    }

    //Обработчик для аннотации @Two

    public static class TwoHandler {
        // Читает и выводит значения свойств аннотации @Two
        public static void printAnnotationValues(Class<?> clazz) {
            System.out.println("\n=== Обработчик @Two ===");

            if (clazz.isAnnotationPresent(Two.class)) {
                Two annotation = clazz.getAnnotation(Two.class);

                System.out.println("Аннотация @Two найдена на классе: " +
                        clazz.getSimpleName());
                System.out.println("\nСвойства аннотации:");
                System.out.println("  first (String): \"" + annotation.first() + "\"");
                System.out.println("  second (int): " + annotation.second());
            }
        }

        // Создает объект и инициализирует его значениями из аннотации

        public static Object createFromAnnotation(Class<?> clazz) throws Exception {
            if (!clazz.isAnnotationPresent(Two.class)) {
                throw new IllegalArgumentException("Класс не имеет аннотации @Two");
            }

            Two annotation = clazz.getAnnotation(Two.class);
            Object instance = clazz.getDeclaredConstructor().newInstance();

            // Ищем методы setData и setValue
            try {
                java.lang.reflect.Method setDataMethod = clazz.getMethod("setData", String.class);
                setDataMethod.invoke(instance, annotation.first());
            } catch (NoSuchMethodException e) {
                // Метод не найден, попробуем установить через поле
                try {
                    java.lang.reflect.Field dataField = clazz.getDeclaredField("data");
                    dataField.setAccessible(true);
                    dataField.set(instance, annotation.first());
                } catch (NoSuchFieldException ex) {
                    // Поле не найдено
                }
            }

            try {
                java.lang.reflect.Method setValueMethod = clazz.getMethod("setValue", int.class);
                setValueMethod.invoke(instance, annotation.second());
            } catch (NoSuchMethodException e) {
                // Метод не найден, попробуем установить через поле
                try {
                    java.lang.reflect.Field valueField = clazz.getDeclaredField("value");
                    valueField.setAccessible(true);
                    valueField.set(instance, annotation.second());
                } catch (NoSuchFieldException ex) {
                    // Поле не найдено
                }
            }

            return instance;
        }
    }
}
