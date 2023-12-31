package ru.biryuchev.MySecondTestAppSpringBoot.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Request {

    @NotBlank(message = "Поле uid не заполнено")
    @Size(min = 1, max = 32, message = "Поле uid содержит некорректное количество вне диапазона от 1 до 32")
    private String uid; // Уникальный идентификатор сообщения

    @NotBlank(message = "Поле operationalUid не заполнено")
    @Size(min = 1, max = 32, message = "Поле operationalUid содержит некорректное количество вне диапазона от 1 до 32")
    private String operationUid; // Уникальный идентификатор операции

    private Systems systemName;

    @NotBlank(message = "Поле systemTime не заполнено")
    private String systemTime;  // Время создания сообщения

    private String source; // Наименование ресурса
    private Positions position; // Должность
    private Double salary; // Зарплата
    private Double bonus; // Коэффициент
    private Integer workDays; // Рабочие дни

    @Max(value = 100000, message = "Превышено максимальное значение поля communicationId равное 100000")
    @Min(value = 1, message = "Значение поля communicationId меньше минимального равного 1")
    private int communicationId; // Уникальный идентификатор коммуникации

    private int templateId; // Уникальный идентификатор шаблона
    private int productCode; // Код продукта
    private int smsCode; // Смс код

    @Override
    public String toString() {
        return "{" +
                "uid='" + uid + '\'' +
                ", operationUid='" + operationUid + '\'' +
                ", systemName='" + systemName + '\'' +
                ", systemTime='" + systemTime + '\'' +
                ", source='" + source + '\'' +
                ", communicationId=" + communicationId +
                ", templateId=" + templateId +
                ", productCode=" + productCode +
                ", smsCode=" + smsCode +
                '}';
    }

}