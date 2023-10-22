package ru.biryuchev.MySecondTestAppSpringBoot.service;

import ru.biryuchev.MySecondTestAppSpringBoot.model.Positions;

public interface QuarterlyBonusService {
    double calculateQuarterly(Positions position, double salary, double bonus);
}
