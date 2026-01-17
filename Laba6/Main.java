package Laba6;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("   ПОЛНАЯ ДЕМОНСТРАЦИЯ ВСЕХ ЗАДАНИЙ ПО АННОТАЦИЯМ");

        // Демонстрация всех заданий по порядку
        Class_1_1(scanner);
        System.out.println();
        Class_1_2(scanner);
        System.out.println();
        Class_1_3(scanner);
        System.out.println();
        Class_1_4(scanner);
        System.out.println();
        Class_1_5(scanner);
        System.out.println();
        Class_1_6(scanner);
        System.out.println();

        scanner.close();
    }

    //ФУНКЦИИ ДЛЯ КАЖДОГО КЛАССА

    public static void Class_1_1(Scanner scanner) {
        System.out.println("\nЗАДАНИЕ 1.1: Аннотация @Invoke");
        System.out.println("Цель: разработать аннотацию @Invoke для автоматического");
        System.out.println("вызова методов с использованием Reflection API.\n");

        System.out.println("1. СОЗДАНИЕ ОБЪЕКТА CLASS_1_1");
        Class_1_1 obj = new Class_1_1();

        System.out.println("\n2. АВТОМАТИЧЕСКИЙ ВЫЗОВ МЕТОДОВ С @INVOKE");
        System.out.println("   Используем обработчик для поиска и вызова всех методов,");
        System.out.println("   помеченных аннотацией @Invoke:");
        System.out.println();

        Class_1_1.InvokeHandler.invokeAnnotatedMethods(obj);
    }

    public static void Class_1_2(Scanner scanner) {
        System.out.println("\nЗАДАНИЕ 1.2: Аннотация @Default");
        System.out.println("Цель: разработать аннотацию @Default для указания");
        System.out.println("типа по умолчанию для класса или поля.\n");

        System.out.println("1. СОЗДАНИЕ ОБЪЕКТА С ПОЛЬЗОВАТЕЛЬСКИМИ ЗНАЧЕНИЯМИ");

        System.out.print("   Введите название товара: ");
        String name = scanner.nextLine().trim();
        while (name.isEmpty()) {
            System.out.print("   Название не может быть пустым. Введите снова: ");
            name = scanner.nextLine().trim();
        }

        int id = getIntInput(scanner, "   Введите ID товара (1-1000): ", 1, 1000);
        double price = getDoubleInput(scanner, "   Введите цену товара (0.01-1000000): ", 0.01, 1000000);

        System.out.print("   Введите описание товара (можно оставить пустым): ");
        String description = scanner.nextLine().trim();

        Class_1_2 product = new Class_1_2(id, price, name, description);

        System.out.println("\n   Объект успешно создан:");
        System.out.println("     • ID: " + product.getId());
        System.out.println("     • Название: " + product.getName());
        System.out.println("     • Цена: " + product.getPrice());
        System.out.println("     • Описание: " + product.getDescription());

        System.out.println("\n2. АНАЛИЗ АННОТАЦИЙ @DEFAULT");
        System.out.println("   Используем обработчик для чтения аннотаций:");
        System.out.println();

        Class_1_2.DefaultHandler.printDefaultTypes(product);
    }

    public static void Class_1_3(Scanner scanner) {
        System.out.println("\nЗАДАНИЕ 1.3: Аннотация @ToString");
        System.out.println("Цель: разработать аннотацию @ToString для управления");
        System.out.println("отображением полей в строковом представлении объекта.\n");

        System.out.println("1. СОЗДАНИЕ ОБЪЕКТА PERSON");

        System.out.print("   Введите имя: ");
        String firstName = scanner.nextLine().trim();
        while (firstName.isEmpty()) {
            System.out.print("   Имя не может быть пустым. Введите снова: ");
            firstName = scanner.nextLine().trim();
        }

        System.out.print("   Введите фамилию: ");
        String lastName = scanner.nextLine().trim();
        while (lastName.isEmpty()) {
            System.out.print("   Фамилия не может быть пустым. Введите снова: ");
            lastName = scanner.nextLine().trim();
        }

        System.out.print("   Введите пароль: ");
        String password = scanner.nextLine().trim();
        while (password.isEmpty()) {
            System.out.print("   Пароль не может быть пустым. Введите снова: ");
            password = scanner.nextLine().trim();
        }

        int age = getIntInput(scanner, "   Введите возраст (0-150): ", 0, 150);

        System.out.print("   Введите email: ");
        String email = scanner.nextLine().trim();
        while (email.isEmpty()) {
            System.out.print("   Email не может быть пустым. Введите снова: ");
            email = scanner.nextLine().trim();
        }

        double salary = getDoubleInput(scanner, "   Введите зарплату (0-1000000): ", 0, 1000000);

        Class_1_3 person = new Class_1_3(firstName, lastName, password, age, email, salary);

        System.out.println("\n  Объект Person успешно создан");

        System.out.println("\n2. СРАВНЕНИЕ МЕТОДОВ TO STRING");
        System.out.println("   Стандартный toString() (все поля видны):");
        System.out.println("   " + person.toString());

        System.out.println("\n  toStringWithAnnotations() (учет аннотаций):");
        System.out.println("   " + person.toStringWithAnnotations());

        System.out.println("\n   Через обработчик ToStringHandler:");
        System.out.println("   " + Class_1_3.ToStringHandler.generateToString(person));

        System.out.println("\n3. ОБЪЯСНЕНИЕ ПОВЕДЕНИЯ");
        System.out.println("   Поля с аннотацией @ToString:");
        System.out.println("   • @ToString(YES) - отображаются: firstName, lastName, age, email");
        System.out.println("   • @ToString(NO) - скрыты: password, salary");
        System.out.println("   Поля без аннотации (по умолчанию YES) - отображаются");
    }

    public static void Class_1_4(Scanner scanner) {
        System.out.println("\nЗАДАНИЕ 1.4: Аннотация @Validate");
        System.out.println("Цель: разработать аннотацию @Validate для указания");
        System.out.println("списка классов, которые должны проверяться.\n");

        System.out.println("1. СОЗДАНИЕ ОБЪЕКТА CLASS_1_4");

        System.out.print("   Введите название элемента: ");
        String name = scanner.nextLine().trim();
        while (name.isEmpty()) {
            System.out.print("   Название не может быть пустым. Введите снова: ");
            name = scanner.nextLine().trim();
        }

        int count = getIntInput(scanner, "   Введите количество (0-1000): ", 0, 1000);
        double price = getDoubleInput(scanner, "   Введите цену (0-10000): ", 0, 10000);

        System.out.print("   Элемент активен? (да/нет): ");
        String activeStr = scanner.nextLine().trim().toLowerCase();
        boolean active = activeStr.equals("да") || activeStr.equals("д") ||
                activeStr.equals("yes") || activeStr.equals("y");

        Class_1_4 item = new Class_1_4(name, count, price, active);

        System.out.println("\n   Объект успешно создан:");
        System.out.println("     • Название: " + item.getName());
        System.out.println("     • Количество: " + item.getCount());
        System.out.println("     • Цена: " + item.getPrice());
        System.out.println("     • Активен: " + item.isActive());

        System.out.println("\n2. АНАЛИЗ АННОТАЦИИ @VALIDATE");
        System.out.println("   Используем обработчик для чтения аннотации:");
        System.out.println();

        Class_1_4.ValidateHandler.printValidationClasses(Class_1_4.class);

        System.out.println("\n3. ПРОВЕРКА СООТВЕТСТВИЯ ТИПОВ");
        System.out.println("   Класс Class_1_4 имеет поля следующих типов:");
        System.out.println("   • name: String");
        System.out.println("   • count: int");
        System.out.println("   • price: double");
        System.out.println("   • active: boolean");
    }

    public static void Class_1_5(Scanner scanner) {
        System.out.println("\nЗАДАНИЕ 1.5: Аннотация @Two");
        System.out.println("Цель: разработать аннотацию @Two с двумя обязательными");
        System.out.println("свойствами: first (String) и second (int).\n");

        System.out.println("1. СОЗДАНИЕ ОБЪЕКТА CLASS_1_5");
        Class_1_5 obj = new Class_1_5();

        System.out.println("   Объект Class_1_5 создан");
        System.out.println("   Начальные значения:");
        System.out.println("   • data: " + obj.getData());
        System.out.println("   • value: " + obj.getValue());

        System.out.println("\n2. ЧТЕНИЕ ЗНАЧЕНИЙ ИЗ АННОТАЦИИ @TWO");
        System.out.println("   Используем обработчик для чтения аннотации:");
        System.out.println();

        Class_1_5.TwoHandler.printAnnotationValues(Class_1_5.class);

        System.out.println("\n3. ЗАГРУЗКА ЗНАЧЕНИЙ В ОБЪЕКТ");
        System.out.print("   Загрузить значения из аннотации в объект? (да/нет): ");
        String answer = scanner.nextLine().trim().toLowerCase();

        if (answer.equals("да") || answer.equals("д") || answer.equals("yes") || answer.equals("y")) {
            obj.loadFromAnnotation();
            System.out.println("\n   Значения загружены из аннотации:");
            System.out.println("   • data: " + obj.getData());
            System.out.println("   • value: " + obj.getValue());

            // Проверка загрузки
            if (obj.getData().equals("Пример строкового значения") && obj.getValue() == 42) {
                System.out.println("    Значения соответствуют аннотации!");
            }
        }
    }

    public static void Class_1_6(Scanner scanner) {
        System.out.println("\nЗАДАНИЕ 1.6: Аннотация @Cache");
        System.out.println("Цель: разработать аннотацию @Cache для указания");
        System.out.println("кешируемых областей с возможностью пустого массива.\n");

        System.out.println("1. АНАЛИЗ АННОТАЦИИ @CACHE НА КЛАССЕ CLASS_1_6");
        System.out.println("   Используем обработчик для чтения аннотации:");
        System.out.println();

        Class_1_6.CacheHandler.printCacheRegions(Class_1_6.class);

        System.out.println("\n2. СОЗДАНИЕ ОБЪЕКТА С КЭШЕМ");
        Class_1_6 cacheService = new Class_1_6();
        System.out.println("    Объект Class_1_6 создан");
        System.out.println("   Кэшируемые области инициализированы");

        System.out.println("\n3. ДЕМОНСТРАЦИЯ РАБОТЫ С КЭШЕМ");
        System.out.print("   Продемонстрировать работу с кэшем? (да/нет): ");
        String answer = scanner.nextLine().trim().toLowerCase();

        if (answer.equals("да") || answer.equals("д") || answer.equals("yes") || answer.equals("y")) {
            System.out.println("\n   Введите данные для добавления в кэш:");

            // Пользователь вводит данные для кэша
            System.out.print("   Ключ для области 'users' (например, user123): ");
            String userKey = scanner.nextLine().trim();
            while (userKey.isEmpty()) {
                System.out.print("   Ключ не может быть пустым. Введите снова: ");
                userKey = scanner.nextLine().trim();
            }

            System.out.print("   Значение (имя пользователя): ");
            String userName = scanner.nextLine().trim();
            while (userName.isEmpty()) {
                System.out.print("   Значение не может быть пустым. Введите снова: ");
                userName = scanner.nextLine().trim();
            }

            System.out.print("   Ключ для области 'orders' (например, order001): ");
            String orderKey = scanner.nextLine().trim();
            while (orderKey.isEmpty()) {
                System.out.print("   Ключ не может быть пустым. Введите снова: ");
                orderKey = scanner.nextLine().trim();
            }

            int orderValue = getIntInput(scanner, "   Значение (номер заказа, 1-99999): ", 1, 99999);

            System.out.print("   Ключ для области 'products' (например, prod001): ");
            String productKey = scanner.nextLine().trim();
            while (productKey.isEmpty()) {
                System.out.print("   Ключ не может быть пустым. Введите снова: ");
                productKey = scanner.nextLine().trim();
            }

            System.out.print("   Значение (название продукта): ");
            String productValue = scanner.nextLine().trim();
            while (productValue.isEmpty()) {
                System.out.print("   Значение не может быть пустым. Введите снова: ");
                productValue = scanner.nextLine().trim();
            }

            // Добавление в кэш
            System.out.println("\n   Добавление данных в кэш:");
            cacheService.put("users", userKey, userName);
            cacheService.put("orders", orderKey, orderValue);
            cacheService.put("products", productKey, productValue);

            // Показать все области
            System.out.println("\n   Все области кэша:");
            for (String region : cacheService.getCacheRegions()) {
                System.out.println("   • " + region);
            }
        }
    }

    // ==================== ВСПОМОГАТЕЛЬНЫЕ МЕТОДЫ ====================

    //Получает целочисленный ввод от пользователя с проверкой.
    private static int getIntInput(Scanner scanner, String prompt, int min, int max) {
        while (true) {
            try {
                System.out.print(prompt);
                String input = scanner.nextLine().trim();

                if (input.isEmpty()) {
                    System.out.println("   Ввод не может быть пустым. Попробуйте снова.");
                    continue;
                }

                int value = Integer.parseInt(input);

                if (value >= min && value <= max) {
                    return value;
                } else {
                    System.out.printf("   Пожалуйста, введите число от %d до %d.\n", min, max);
                }

            } catch (NumberFormatException e) {
                System.out.println("   Ошибка: необходимо ввести целое число!");
            }
        }
    }

    //Получает дробное число от пользователя с проверкой.
    private static double getDoubleInput(Scanner scanner, String prompt, double min, double max) {
        while (true) {
            try {
                System.out.print(prompt);
                String input = scanner.nextLine().trim();

                if (input.isEmpty()) {
                    System.out.println("   Ввод не может быть пустым. Попробуйте снова.");
                    continue;
                }

                // Заменяем запятую на точку для корректного парсинга
                input = input.replace(',', '.');
                double value = Double.parseDouble(input);

                if (value >= min && value <= max) {
                    return value;
                } else {
                    System.out.printf("   Пожалуйста, введите число от %.2f до %.2f.\n", min, max);
                }

            } catch (NumberFormatException e) {
                System.out.println("   Ошибка: необходимо ввести число!");
            }
        }
    }

}