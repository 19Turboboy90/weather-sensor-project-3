package ru.zhrinov.MeteorologicalSensor.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.IDENTITY;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "measurement")
public class Measurement {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "value")
    @NotNull(message = "The measurement of sensor should not be empty. Показание сенсора не должно быть пустым")
    @Min(value = -100, message = "The minimum value should be at least -100. " +
            "Минимальный показатель должен быть не меньше -100")
    @Max(value = 100, message = "The maximum value must be greater than 100. " +
            "Максимальное значение должно быть больше 100")
    private Double value;

    @Column(name = "raining")
    @NotNull(message = "The rain indicator should not be empty. Индикатор дождя не должен быть пустым")
    private Boolean raining;

    @Column(name = "date_time_measurement")
    private LocalDateTime localDateTimeOfMeasurement;

    @ManyToOne
    @JoinColumn(name = "sensor_id", referencedColumnName = "id")
    @NotNull(message = "The sensor should not be empty. Название сенсора не должно быть пустым.")
    @JsonBackReference
    private Sensor sensor;
}
