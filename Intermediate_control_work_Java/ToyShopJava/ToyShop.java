package ToyShopJava;

import java.io.*;
import java.util.*;

public class ToyShop {
    private static ArrayList<Toy> toys = new ArrayList<>();
    private static ArrayList<String> winners = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in, "CP866");

    public static void loadToys() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("toys.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                String name = fields[1];
                int quantity = Integer.parseInt(fields[2]);
                double weight = Double.parseDouble(fields[3]);
                Toy toy = new Toy(name, quantity, weight);
                toys.add(toy);
            }
        }
    }

    public static void loadWinners() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("winners.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                winners.add(line);
            }
        }
    }

    public static void saveData() {
        try {
            saveToys();
            saveWinners();
        } catch (IOException e) {
            System.out.println("Ошибка сохранения данных: " + e.getMessage());
        }
    }

    private static void saveToys() throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("toys.txt"))) {
            for (Toy toy : toys) {
                String line = String.format("%d,%s,%d,%.2f",
                        toy.getId(), toy.getName(), toy.getQuantity(), toy.getWeight());
                writer.write(line);
                writer.newLine();
            }
        }
    }

    private static void saveWinners() throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("winners.txt"))) {
            for (String winner : winners) {
                writer.write(winner);
                writer.newLine();
            }
        }
    }

    public static void addNewToy() {

        System.out.print("Введите название игрушки: ");
        String name = scanner.nextLine();
        System.out.print("Введите количество игрушек: ");
        int quantity = scanner.nextInt();
        System.out.print("Введите вероятность выигрыша игрушки в %: ");
        double weight = scanner.nextDouble();

        toys.add(new Toy(name, quantity, weight));
        System.out.println("Игрушка успешно добавлена.");
        scanner.nextLine();
    }

    public static void updateToyWeight() {
        System.out.print("Введите идентификатор игрушки: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Toy targetToy = null;
        for (Toy toy : toys) {
            if (toy.getId() == id) {
                targetToy = toy;
                break;
            }
        }

        if (targetToy == null) {
            System.out.println("Игрушка не найдена.");
            return;
        }

        System.out.printf("Текущая вероятность выигрыша: %.2f%%\n", targetToy.getWeight());
        System.out.print("Введите новую вероятность выигрыша: ");
        double weight = scanner.nextDouble();
        targetToy.setWeight(weight);
        System.out.println("Вероятность выигрыша игрушки успешно обновлена.");
    }

    public static void runToyRaffle() {
        ArrayList<Double> weights = new ArrayList<>();
        for (Toy toy : toys) {
            weights.add(toy.getWeight());
        }

        int index = getRandomIndex(weights);
        Toy winner = toys.get(index);
        if (winner.getQuantity() == 0) {
            System.out.println("Игрушка победитель закончилась.");
            return;
        }

        System.out.println("Поздравляем! Вы выиграли " + winner.getName() + "!");
        winners.add(winner.getName());
        winner.setQuantity(winner.getQuantity() - 1);
    }

    public static void viewAllToys() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("toys.txt"));
            String line;
            System.out.println("| ID игрушки | Название игрушки | Количество | Вероятность выигрыша |");
            System.out.println("-".repeat(69));
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                System.out.printf("|%12s|%18s|%12s|%20s %%|\n", data[0], data[1], data[2], data[3]);
            }
            System.out.println("-".repeat(69));
            reader.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void viewWinners() {
        System.out.println("Недавние победители: ");
        for (String winner : winners) {
            System.out.println("- " + winner);
        }
    }

    private static int getRandomIndex(ArrayList<Double> weights) {
        double sum = 0;
        for (double weight : weights) {
            sum += weight;
        }

        double rand = Math.random() * sum;

        for (int i = 0; i < weights.size(); i++) {
            rand -= weights.get(i);
            if (rand <= 0) {
                return i;
            }
        }

        return -1;
    }
}
