package com.example.ordersmanagement.statistics;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("api/statistics")
public class StatisticsController {
    final private StatisticsService statisticsService;

    public StatisticsController(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    @GetMapping
    public Statistics getStatistics() {
        return statisticsService.getStatistics();
    }

}
