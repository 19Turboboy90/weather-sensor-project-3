package ru.zhrinov.MeteorologicalSensor.util;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.zhrinov.MeteorologicalSensor.models.Sensor;
import ru.zhrinov.MeteorologicalSensor.services.SensorService;

@Component
@RequiredArgsConstructor
public class SensorValidator implements Validator {
    private final SensorService sensorService;

    @Override
    public boolean supports(Class<?> clazz) {
        return Sensor.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Sensor sensor = (Sensor) target;
        if (sensorService.getSensorByName(sensor.getName()).isPresent()) {
            errors.rejectValue("name", "", "a sensor with this name already exists. " +
                    "Датчик с таким названием уже существует.");
        }
    }
}
