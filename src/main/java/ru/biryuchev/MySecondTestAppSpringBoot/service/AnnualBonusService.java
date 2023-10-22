package ru.biryuchev.MySecondTestAppSpringBoot.service;

import org.springframework.stereotype.Service;
import ru.biryuchev.MySecondTestAppSpringBoot.model.Positions;

@Service
public interface AnnualBonusService {
    double calculate(Positions positions, double salary, double bonus, int workDays);
}
