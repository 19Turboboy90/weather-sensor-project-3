package ru.zhrinov.MeteorologicalSensor.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SensorDTO {
    @NotEmpty(message = "The sensor should not be empty. Название сенсора не должно быть пустым.")
    @Size(min = 3, max = 30, message = "The sensor should be between 3 and 30 characters." +
            "Название сенсора должно быть от 3 до 30 символов длиной.")
    private String name;
}
