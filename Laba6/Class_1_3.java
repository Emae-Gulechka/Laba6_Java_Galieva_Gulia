package Laba6;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;

// Режим отображения для аннотации @ToString

enum Mode {
    YES, NO
}

//Аннотация @ToString
// Целью может быть ТИП или ПОЛЕ
// Доступна во время исполнения программы
// Имеет необязательное свойство value с двумя вариантами значений: YES или NO
// Значение свойства по умолчанию: YES

@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@interface ToString {
    Mode value() default Mode.YES;
}

//
// Класс для демонстрации аннотации @ToString
// Показывает, как управлять отображением полей в строковом представлении

@ToString(Mode.YES)
public class Class_1_3 {
    @ToString(Mode.YES)
    private String firstName;

    @ToString(Mode.YES)
    private String lastName;

    @ToString(Mode.NO)
    private String password;

    @ToString(Mode.YES)
    private int age;

    private String email; // Без аннотации - по умолчанию YES

    @ToString(Mode.NO)
    private double salary;

    //Конструктор
    public Class_1_3(String firstName, String lastName, String password,
                     int age, String email, double salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.age = age;
        this.email = email;
        this.salary = salary;
    }

    // Стандартный метод toString()
    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", password='" + password + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", salary=" + salary +
                '}';
    }

    // Метод, формирующий строковое представление с учетом аннотаций @ToString
    // @return строковое представление объекта
    public String toStringWithAnnotations() {
        StringBuilder sb = new StringBuilder();
        sb.append("Person{");

        Class<?> clazz = this.getClass();
        boolean isFirst = true;

        // Проверяем аннотацию на классе
        boolean classToString = true;
        if (clazz.isAnnotationPresent(ToString.class)) {
            ToString classAnnotation = clazz.getAnnotation(ToString.class);
            classToString = (classAnnotation.value() == Mode.YES);
        }

        // Обрабатываем каждое поле
        for (Field field : clazz.getDeclaredFields()) {
            try {
                field.setAccessible(true);

                // Проверяем аннотацию на поле
                boolean fieldToString = true;
                if (field.isAnnotationPresent(ToString.class)) {
                    ToString fieldAnnotation = field.getAnnotation(ToString.class);
                    fieldToString = (fieldAnnotation.value() == Mode.YES);
                }

                // Если и класс и поле разрешают отображение
                if (classToString && fieldToString) {
                    if (!isFirst) {
                        sb.append(", ");
                    }
                    sb.append(field.getName()).append("=").append(field.get(this));
                    isFirst = false;
                }
            } catch (IllegalAccessException e) {
                sb.append(field.getName()).append("=ERROR");
            }
        }

        sb.append("}");
        return sb.toString();
    }

    // Обработчик для аннотации @ToString
    public static class ToStringHandler {
        // Генерирует строковое представление объекта с учетом аннотаций

        public static String generateToString(Object obj) {
            Class<?> clazz = obj.getClass();
            StringBuilder sb = new StringBuilder();
            sb.append(clazz.getSimpleName()).append("{");

            boolean isFirst = true;

            // Проверяем аннотацию на классе
            boolean classToString = true;
            if (clazz.isAnnotationPresent(ToString.class)) {
                ToString classAnnotation = clazz.getAnnotation(ToString.class);
                classToString = (classAnnotation.value() == Mode.YES);
            }

            if (!classToString) {
                return clazz.getSimpleName() + "{...}";
            }

            // Обрабатываем поля
            for (Field field : clazz.getDeclaredFields()) {
                try {
                    field.setAccessible(true);

                    // Проверяем аннотацию на поле
                    boolean fieldToString = true;
                    if (field.isAnnotationPresent(ToString.class)) {
                        ToString fieldAnnotation = field.getAnnotation(ToString.class);
                        fieldToString = (fieldAnnotation.value() == Mode.YES);
                    }

                    if (fieldToString) {
                        if (!isFirst) {
                            sb.append(", ");
                        }
                        sb.append(field.getName()).append("=");

                        // Маскируем пароли
                        if (field.getName().toLowerCase().contains("password")) {
                            sb.append("******");
                        } else {
                            sb.append(field.get(obj));
                        }
                        isFirst = false;
                    }
                } catch (IllegalAccessException e) {
                    // Пропускаем поле при ошибке доступа
                }
            }

            sb.append("}");
            return sb.toString();
        }
    }
}
