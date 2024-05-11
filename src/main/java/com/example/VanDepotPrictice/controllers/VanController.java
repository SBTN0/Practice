package com.example.VanDepotPrictice.controllers;

import com.example.VanDepotPrictice.DTO.AddVanDto;
import com.example.VanDepotPrictice.DTO.LoadDto;
import com.example.VanDepotPrictice.DTO.ReStationDto;
import com.example.VanDepotPrictice.Van;
import com.example.VanDepotPrictice.VanArray;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/vans")
public class VanController {
    VanArray vanArray = new VanArray();

    @PostMapping("/add")
    public ResponseEntity<?> addVan(@RequestBody AddVanDto newVanDto) {
        try {
            Van van = new Van(newVanDto.getName(), newVanDto.getNum(), newVanDto.getType(), newVanDto.getWeight(), newVanDto.getYear(), 0, newVanDto.getStation());
            vanArray.addVan(van);
            return ResponseEntity.ok("Вагон успешно добавлен");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Ошибка добавления объекта: " + e.getMessage());
        }
    }

    @GetMapping("/show")
    public ResponseEntity<?> getVans() {
        try {
            return ResponseEntity.ok(vanArray.getVanList());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Ошибка вывода: " + e.getMessage());
        }
    }

    @PostMapping("/load_van")
    public ResponseEntity<?> loadVan(@RequestBody LoadDto loadDto) {
        try {
            vanArray.findVanByNum(loadDto.getNum()).loadVan(loadDto.getLoad());
            vanArray.writeToFile(vanArray.getVanList());
            return ResponseEntity.ok("Вагон успешно загружен на " + loadDto.getLoad());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Ошибка добавления объекта: " + e.getMessage());
        }
    }

    @PostMapping("/unload_van")
    public ResponseEntity<?> unloadVan(@RequestBody LoadDto loadDto) {
        try {
            vanArray.findVanByNum(loadDto.getNum()).unloadVan(loadDto.getLoad());
            vanArray.writeToFile(vanArray.getVanList());
            return ResponseEntity.ok("Вагон успешно разгружен на " + loadDto.getLoad());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Ошибка добавления объекта: " + e.getMessage());
        }
    }

    @PostMapping("/reStation_van")
    public ResponseEntity<?> reStationVan(@RequestBody ReStationDto reStationDto) {
        try {
            vanArray.findVanByNum(reStationDto.getNum()).reStation(reStationDto.getStation());
            vanArray.writeToFile(vanArray.getVanList());
            return ResponseEntity.ok("Станция приписки успешно изменена на: " + reStationDto.getStation());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Ошибка изменения санции: " + e.getMessage());
        }
    }

    @GetMapping("/read")
    public ResponseEntity<?> readFromFile() {
        try {
            return ResponseEntity.ok(vanArray.readFromFile());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Ошибка загрузки данных из файла: " + e.getMessage());
        }
    }

    @GetMapping("/write")
    public ResponseEntity<?> writeToFile() {
        try {
            vanArray.writeToFile(vanArray.getVanList());
            return ResponseEntity.ok("Данные успешно загружены в файл");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Ошибка загрузки данных в файл: " + e.getMessage());
        }
    }

    @DeleteMapping("/{num}")
    public ResponseEntity<?> removeByNum(@PathVariable int num) {
        try {
            vanArray.removeByNum(num);
            return ResponseEntity.ok("Вагон с номером " + num + " успешно удален.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Ошибка удаления вагона: " + e.getMessage());
        }
    }
}