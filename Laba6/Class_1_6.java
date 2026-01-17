package Laba6;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Аннотация @Cache
// Целью может быть ТИП
// Доступна во время исполнения программы
// Имеет необязательное свойство value, типа String[]
// Значение свойства по умолчанию: пустой массив

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@interface Cache {
    String[] value() default {};
}

// Класс с аннотацией @Cache
// Демонстрирует указание кэшируемых областей
@Cache({"users", "orders", "products", "settings"})
public class Class_1_6 {
    private Map<String, Map<String, Object>> cache = new HashMap<>();

    // Конструктор инициализирует кэш

    public Class_1_6() {
        initializeCache();
    }

    //Инициализирует структуру кэша на основе аннотации

    private void initializeCache() {
        if (this.getClass().isAnnotationPresent(Cache.class)) {
            Cache annotation = this.getClass().getAnnotation(Cache.class);
            for (String region : annotation.value()) {
                cache.put(region, new HashMap<>());
                System.out.println("Инициализирована область кэша: " + region);
            }
        }
    }

    // Добавляет данные в кэш

    public void put(String region, String key, Object value) {
        if (cache.containsKey(region)) {
            cache.get(region).put(key, value);
            System.out.println("Добавлено в кэш [" + region + "]: " + key + " = " + value);
        } else {
            System.out.println("Область кэша '" + region + "' не найдена!");
        }
    }

    // Получает данные из кэша

    public Object get(String region, String key) {
        if (cache.containsKey(region)) {
            Object value = cache.get(region).get(key);
            System.out.println("Получено из кэша [" + region + "]: " + key + " = " + value);
            return value;
        }
        System.out.println("Область кэша '" + region + "' не найдена!");
        return null;
    }

    // Получает список всех кэшируемых областей

    public List<String> getCacheRegions() {
        if (this.getClass().isAnnotationPresent(Cache.class)) {
            Cache annotation = this.getClass().getAnnotation(Cache.class);
            List<String> regions = new ArrayList<>();
            for (String region : annotation.value()) {
                regions.add(region);
            }
            return regions;
        }
        return new ArrayList<>();
    }

    //Обработчик для аннотации @Cache

    public static class CacheHandler {
        // Выводит список кэшируемых областей

        public static void printCacheRegions(Class<?> clazz) {
            System.out.println("\n=== Обработчик @Cache ===");

            if (clazz.isAnnotationPresent(Cache.class)) {
                Cache annotation = clazz.getAnnotation(Cache.class);
                String[] regions = annotation.value();

                System.out.println("Класс " + clazz.getSimpleName() +
                        " имеет аннотацию @Cache");

                if (regions.length == 0) {
                    System.out.println("Список кэшируемых областей ПУСТ");
                    System.out.println("(кеширование не активировано)");
                } else {
                    System.out.println("Количество кэшируемых областей: " + regions.length);
                    System.out.println("\nСписок областей:");
                    for (int i = 0; i < regions.length; i++) {
                        System.out.printf("  %d. %s\n", i + 1, regions[i]);
                    }
                }
            } else {
                System.out.println("Класс " + clazz.getSimpleName() +
                        " не имеет аннотации @Cache");
            }
        }

        // Проверяет, активировано ли кэширование для класса

        public static boolean isCachingEnabled(Class<?> clazz) {
            if (clazz.isAnnotationPresent(Cache.class)) {
                Cache annotation = clazz.getAnnotation(Cache.class);
                return annotation.value().length > 0;
            }
            return false;
        }
    }
}
