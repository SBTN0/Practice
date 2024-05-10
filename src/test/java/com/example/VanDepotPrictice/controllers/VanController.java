package com.example.VanDepotPrictice.controllers;

import com.example.VanDepotPrictice.DTO.NewVanDto;
import com.example.VanDepotPrictice.Van;
import com.example.VanDepotPrictice.VanArray;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/vans")
public class VanController {
    VanArray vanArray = new VanArray();

    @GetMapping("/read")
    public ResponseEntity<?> readFromFile() {
        try {
            List<Van> vanList = vanArray.readFromFile();
            return ResponseEntity.ok(vanList);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Ошибка загрузки данных из файла: " + e.getMessage());
        }
    }

    @PostMapping("/write")
    public ResponseEntity<?> writeToFile(@RequestBody NewVanDto newVanDto) {
        try {
            Van van = new Van(newVanDto.getName(), newVanDto.getNum(), newVanDto.getType(), newVanDto.getWeight(), newVanDto.getYear(), 0, newVanDto.getStation());
            van.writeToFile(van);
            return ResponseEntity.ok("Данные успешно загружены в файл");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Ошибка загрузки данных в файл: " + e.getMessage());
        }
    }

    @DeleteMapping("/{num}")
    public ResponseEntity<?> removeByNum(@RequestBody int num) {
        try {
            vanArray.removeByNum(num);
            return ResponseEntity.ok("Вагон с номером " + num + "успешно удален.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Ошибка удаления вагона: " + e.getMessage());
        }
    }
}
