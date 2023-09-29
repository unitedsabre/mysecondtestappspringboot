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
    private String uid;

    @NotBlank(message = "Поле operationalUid не заполнено")
    @Size(min = 1, max = 32, message = "Поле operationalUid содержит некорректное количество вне диапазона от 1 до 32")
    private String operationUid;

    private String systemName;

    @NotBlank(message = "Поле systemTime не заполнено")
    private String systemTime;

    private String source;

    @Max(value = 100000, message = "Превышено максимальное значение поля communicationId равное 100000")
    @Min(value = 1, message = "Значение поля communicationId меньше минимального равного 1")
    private int communicationId;

    private int templateId;

    private int productCode;

    private int smsCode;

}