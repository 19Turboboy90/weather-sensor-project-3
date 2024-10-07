package ru.zhrinov.MeteorologicalSensor.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "sensor")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Sensor {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    @NotEmpty(message = "The sensor should not be empty. Название сенсора не должно быть пустым.")
    @Size(min = 3, max = 30, message = "The sensor should be between 3 and 30 characters." +
            "Название сенсора должно быть от 3 до 30 символов длиной.")
    private String name;

    @OneToMany(mappedBy = "sensor", fetch = LAZY)
    private List<Measurement> measurements;
}
