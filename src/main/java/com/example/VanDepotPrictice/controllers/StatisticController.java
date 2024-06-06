package com.example.VanDepotPrictice.controllers;
import com.example.VanDepotPrictice.VanArray;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class StatisticController {
    @GetMapping("/statistic")
    public ResponseEntity<?> getStatistic() {
        return ResponseEntity.ok(VanArray.calculateKurtosis(VanArray.getWeightCounts()));
    }
}
