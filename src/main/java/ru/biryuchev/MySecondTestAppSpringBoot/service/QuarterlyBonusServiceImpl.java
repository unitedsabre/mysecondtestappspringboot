package ru.biryuchev.MySecondTestAppSpringBoot.service;

import org.springframework.stereotype.Service;
import ru.biryuchev.MySecondTestAppSpringBoot.model.Positions;

import java.time.Year;

@Service
public class QuarterlyBonusServiceImpl implements QuarterlyBonusService {
    @Override
        public double calculateQuarterly(Positions position, double salary, double bonus) {
            if (position.isManager()) {
                double annualBonus = salary * bonus * 365 * position.getPositionCoefficient() / 365;
                return annualBonus * 0.1;
            } else {
                return 0.0;
            }
        }
    }