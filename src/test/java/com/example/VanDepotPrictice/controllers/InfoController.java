package com.example.VanDepotPrictice.controllers;
import com.example.VanDepotPrictice.VanArray;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
public class InfoController {
    @GetMapping("/info")
    public ResponseEntity<?> getInfo(HttpSession session) {
        VanArray bankVanArray = (VanArray) session.getAttribute("VanArray");
        Map<String, Object> information = new LinkedHashMap<>();

        information.put("name", "Van");
        information.put("version", "1.0");
        information.put("javaVersion", "22");
        information.put("author", "Sergey Zubkov");
        information.put("year", "2024");

        if (bankVanArray != null) {
            information.put("Vans", bankVanArray.getVanList());
            information.put("VanCount", bankVanArray.size());
        } else {
            information.put("Services", "[]");
            information.put("ServiceCount", "0");
        }
        return ResponseEntity.ok(information);
    }
}