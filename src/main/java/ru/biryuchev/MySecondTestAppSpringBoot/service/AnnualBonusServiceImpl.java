package ru.biryuchev.MySecondTestAppSpringBoot.service;

import ru.biryuchev.MySecondTestAppSpringBoot.model.Positions;

import java.time.Year;

public class AnnualBonusServiceImpl implements AnnualBonusService {
    @Override
    public double calculate(Positions positions, double salary, double bonus, int workDays) {

        int year = Year.now().getValue();
        boolean isYear = Year.isLeap(year);

        int days = isYear ? 366 : 365;

        return salary * bonus * days * positions.getPositionCoefficient() / workDays;
    }
}
