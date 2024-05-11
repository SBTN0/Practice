package com.example.VanDepotPrictice;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.*;
import java.io.FileNotFoundException;

@Component
public class VanArray {
    private final List<Van> vanList = new ArrayList<>();
    private final String FILE_NAME = "VansList.txt";

    public void addVan(Van van) {
        if (this.findVanByNum(van.getNum()) != null) throw new IllegalArgumentException("Такой вагон уже существует");
        vanList.add(van);
    }

    public List<Van> getVanList() {
        return vanList;
    }

    /*public void removeVan(int num) {
        vanList.remove(this.findVanByNum(num));
    }*/

    public Van findVanByNum(int num) {
        for (Van van : vanList) {
            if (van.getNum() == num) {
                return van;
            }
        }
        return null;
    }

    public int size() {
        return  vanList.size();
    }

    public void writeToFile(List<Van> vanList) {
        try (Formatter formatter = new Formatter(FILE_NAME)) {
            formatter.format("%-20s | %-20s | %-15s | %-16s | %-11s | %-10s | %-20s%n", "Название", "Заводской номер", "Тип", "Грузоподъемность", "Год выпуска", "Загрузка", "Станция приписки");
            formatter.format("%-20s | %-20s | %-15s | %-16s | %-11s | %-10s | %-20s%n", "--------------------", "--------------------", "---------------", "----------------", "-----------", "----------", "--------------------");
            for (Van van : vanList) {
                formatter.format("%-20s | %-20d | %-15s | %-16d | %-11d | %-10d | %-20s%n",
                        van.getName(), van.getNum(), van.getType(), van.getWeight(),
                        van.getYear(), van.getLoad(), van.getStation());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден: " + e.getMessage());
        }
    }

    public List<Van> readFromFile() {
        List<Van> vanArray = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(FILE_NAME))) {
            scanner.nextLine();
            scanner.nextLine();

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split("\\|");

                String name = parts[0].trim();
                int num = Integer.parseInt(parts[1].trim());
                String type = parts[2].trim();
                int weight = Integer.parseInt(parts[3].trim());
                short year = Short.parseShort(parts[4].trim());
                int load = Integer.parseInt(parts[5].trim());
                String station = parts[6].trim();

                Van van = new Van(name, num, type, weight, year, load, station);
                vanArray.add(van);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден: " + e.getMessage());
        }
        return vanArray;
    }

    public void removeByNum(int numToRemove) {
        try {
            vanList.removeIf(van -> van.getNum() == numToRemove);
            this.writeToFile(vanList);
        } catch (Exception e) {
            throw new IllegalArgumentException("Вагона с таким номером не существует");
        }
    }
}