package ru.biryuchev.MySecondTestAppSpringBoot.service;

import org.junit.jupiter.api.Test;
import ru.biryuchev.MySecondTestAppSpringBoot.model.Positions;

class QuarterlyBonusServiceImplTest {

    @Test
    void calculateQuarterlyBonus() {
        //given
        Positions position = Positions.MANAGER;
        double bonus = 0.1; // Годовой бонус (10% от зарплаты)
        double salary = 50000.0;

        //when
        double result = new QuarterlyBonusServiceImpl().calculateQuarterly(position, salary, bonus);

        //then
        double expected = 547500.0;
    }
}