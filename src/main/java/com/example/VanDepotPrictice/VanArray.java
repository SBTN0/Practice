package com.example.VanDepotPrictice;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.*;
import java.io.FileNotFoundException;

@Component
public class VanArray {
    private final List<Van> vanList = new ArrayList<>();
    private static final String FILE_NAME = "VansList.txt";

    public void addVan(Van van) {
        Optional<Van> existingVan = this.findVanByNumOptional(van.getNum());
        if (existingVan.isPresent()) {
            throw new IllegalArgumentException("Такой вагон уже существует");
        }
        vanList.add(van);
    }


    public List<Van> getVanList() {
        return vanList;
    }

    public void setVanList(List<Van> vanList) { this.vanList.addAll(vanList); }

    public static Map<Integer, Integer> getWeightCounts() {
        Map<Integer, Integer> weightCounts = new HashMap<>();
        for (Van van : readFromFile()) {
            int weight = van.getWeight();
            weightCounts.put(weight, weightCounts.getOrDefault(weight, 0) + 1);
        }
        return weightCounts;
    }

    public static Map<String, Double> calculateKurtosis(Map<Integer, Integer> weightCounts) {
        int n = weightCounts.values().stream().mapToInt(Integer::intValue).sum();
        Map<String, Double> statisticData = new LinkedHashMap<>();

        double mean = weightCounts.entrySet().stream()
                .mapToDouble(e -> e.getKey() * e.getValue())
                .sum() / n;

        double variance = weightCounts.entrySet().stream()
                .mapToDouble(e -> e.getValue() * Math.pow(e.getKey() - mean, 2))
                .sum() / (n - 1);

        double stdDev = Math.sqrt(variance);

        double kurtosisNumerator = weightCounts.entrySet().stream()
                .mapToDouble(e -> e.getValue() * Math.pow((e.getKey() - mean) / stdDev, 4))
                .sum();

        double kurtosis = (n * (n + 1) * kurtosisNumerator) / ((n - 1) * (n - 2) * (n - 3))
                - (3 * Math.pow(n - 1, 2)) / ((n - 2) * (n - 3));

        statisticData.put("Центральный эмпирический момент 4-го порядка", mean);
        statisticData.put("Дисперсия", variance);
        statisticData.put("Среднее квадратическое отклонение", stdDev);
        statisticData.put("Коэффициент эксцесса", kurtosis);
        return statisticData;
    }

    public Optional<Van> findVanByNumOptional(int num) {
        try {
            return Optional.of(findVanByNum(num));
        } catch (NoSuchElementException e) {
            return Optional.empty();
        }
    }


    public Van findVanByNum(int num) {
        for (Van van : vanList) {
            if (van.getNum() == num) {
                return van;
            }
        }
        throw new NoSuchElementException("Вагона с таким номером не существует");
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

    public static List<Van> readFromFile() {
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
        vanList.remove(this.findVanByNum(numToRemove));
        this.writeToFile(vanList);
    }
}