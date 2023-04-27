package ToyShopJava;

import java.io.IOException;
import java.util.Scanner;

public class Menu {
    private static final Scanner scanner = new Scanner(System.in, "CP866");

    public static void menu() {
        try {
            ToyShop.loadToys();
            ToyShop.loadWinners();
        } catch (IOException e) {
            System.out.println("Ошибка при загрузке данных: " + e.getMessage());
        }

        while (true) {
            System.out.println("Магазин игрушек");
            System.out.println("1. Добавить новую игрушку");
            System.out.println("2. Обновить вероятность выигрыша игрушки");
            System.out.println("3. Запустить розыгрыш игрушек");
            System.out.println("4. Посмотреть наличие игрушек");
            System.out.println("5. Посмотреть журнал победителей");
            System.out.println("0. Выход");
            System.out.print("> ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    ToyShop.addNewToy();
                    break;
                case 2:
                    ToyShop.updateToyWeight();
                    break;
                case 3:
                    ToyShop.runToyRaffle();
                    break;
                case 4:
                    ToyShop.viewAllToys();
                    break;
                case 5:
                    ToyShop.viewWinners();
                    break;
                case 0:
                    ToyShop.saveData();
                    System.out.println("До встречи в магазине!");
                    return;
                default:
                    System.out.println("Неверный выбор, попробуйте еще раз.");
            }

            System.out.println();
        }
    }
}
