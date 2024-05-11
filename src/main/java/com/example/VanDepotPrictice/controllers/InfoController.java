package com.example.VanDepotPrictice.controllers;
import com.example.VanDepotPrictice.VanArray;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
public class InfoController {
    @GetMapping("/info")
    public ResponseEntity<?> getInfo() {
        VanArray vanArray = new VanArray();
        Map<String, Object> information = new LinkedHashMap<>();
        information.put("name", "Van");
        information.put("version", "1.0");
        information.put("javaVersion", "22");
        information.put("author", "Sergey Zubkov");
        information.put("year", "2024");
        information.put("Vans", vanArray.readFromFile());
        information.put("VanCount", vanArray.readFromFile().size());

        return ResponseEntity.ok(information);
    }
}