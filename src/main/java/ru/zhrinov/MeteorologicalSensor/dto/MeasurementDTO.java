package ru.zhrinov.MeteorologicalSensor.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MeasurementDTO {
    @NotNull(message = "The measurement of sensor should not be empty. Показание сенсора не должно быть пустым")
    @Min(value = -100, message = "The minimum value should be at least -100. " +
            "Минимальный показатель должен быть не меньше -100")
    @Max(value = 100, message = "The maximum value must be greater than 100. " +
            "Максимальное значение должно быть больше 100")
    private Double value;

    @NotNull(message = "The rain indicator should not be empty. Индикатор дождя не должен быть пустым")
    private Boolean raining;

    @NotNull(message = "The sensor should not be empty. Название сенсора не должно быть пустым.")
    private SensorDTO sensor;
}
